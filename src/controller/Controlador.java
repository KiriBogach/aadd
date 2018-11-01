package controller;



import java.sql.Date;

import dao.CocheDAO;
import dao.FactoriaDAO;
import dao.ParadaDAO;
import dao.UsuarioDAO;
import dao.ViajeDAO;
import model.Coche;
import model.Parada;
import model.Usuario;
import model.Viaje;

public class Controlador {

	private static Controlador unicaInstancia = null;

	private Controlador() {
	}

	public static Controlador getInstance() {
		if (unicaInstancia == null) {
			unicaInstancia = new Controlador();
		}
		return unicaInstancia;
	}
	
	/*Este método recupera el objeto usuario dado el nombre de usuario*/
	public Usuario findUsuario(String usuario) {
		UsuarioDAO dao = FactoriaDAO.getInstancia().getUsuarioDAO();
		return dao.findUsuario(usuario);
	}
	/*Este método recupera el objeto viaje dado el id del viaje*/
	public Viaje findViaje(int id){
		ViajeDAO dao=FactoriaDAO.getInstancia().getViajeDAO();
		return dao.findViaje(id);
	}
	
	/*Este método persiste un usuario y devuelve el objeto usuario que se ha persistido o nulo en otro caso*/
	public Usuario registrarUsuario(String usuario, String password,Date fechaNacimiento, String profesion, String email, String nombre, String apellidos) {
		UsuarioDAO dao = FactoriaDAO.getInstancia().getUsuarioDAO();
		return dao.createUsuario(usuario, password, fechaNacimiento, profesion, email, nombre, apellidos);
	}

	public boolean loginUsuario(String usuario, String password) {
		UsuarioDAO dao = FactoriaDAO.getInstancia().getUsuarioDAO();
		Usuario u = dao.findUsuario(usuario);
		if (u != null) {
			return u.getPassword().equals(password);
		}
		return false;
	}
	
	/*SUGERENCIA:En este metodo comentar que toda la responsabilidad de añadirCoche recae sobre el controlador violando el patrón experto
	 * Sugerencia: Persistir el coche en el controlador. El constructor del coche puede admitir el objeto usuario e implementar
	 * en Usuario un metodo llamado anadirCoche*/
	public boolean registrarCoche(String nombreUsuario, String matricula, String modelo, int year, int confort) {
		CocheDAO daoCoche = FactoriaDAO.getInstancia().getCocheDAO();
		UsuarioDAO daoUsuario = FactoriaDAO.getInstancia().getUsuarioDAO();
		Usuario usuario=daoUsuario.findUsuario(nombreUsuario);
		Coche coche = daoCoche.createCoche(matricula, modelo, year, confort);
		if (coche == null) {
			return false;
		}
		
		coche.setUsuario(usuario);
		usuario.setCoche(coche);
		
		daoCoche.update(coche);
		daoUsuario.update(usuario);
		
		return true;
	}
	/*Este método persiste un viaje y devuelve el objeto viaje que se ha persistido o nulo en otro caso*/
	public Viaje registrarViaje(int plazas, double precio) {
		ViajeDAO dao = FactoriaDAO.getInstancia().getViajeDAO();
		Viaje viaje = dao.createViaje(plazas,precio);
		return viaje;
	}
	
	
	/*Aqui la misma sugerencia que en registrarCoche*/
	public Parada registrarParadaOrigen(int idViaje, String ciudad, String calle, int CP, Date fecha) {
		ParadaDAO daoParada = FactoriaDAO.getInstancia().getParadaDAO();
		ViajeDAO daoViaje = FactoriaDAO.getInstancia().getViajeDAO();
		
		Viaje viaje = daoViaje.findViaje(idViaje);
		Parada paradaOrigen = daoParada.createParada(ciudad, calle, CP, fecha);
		
		viaje.setOrigen(paradaOrigen);
		
		daoViaje.update(viaje);
		
		return paradaOrigen;
	}

}
