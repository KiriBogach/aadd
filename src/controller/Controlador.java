package controller;

import java.util.Date;

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

	public Usuario findUsuario(String usuario) {
		UsuarioDAO dao = FactoriaDAO.getInstancia().getUsuarioDAO();
		return dao.findUsuario(usuario);
	}

	public Usuario createUsuario(String usuario, String password, String email, String telefono) {
		UsuarioDAO dao = FactoriaDAO.getInstancia().getUsuarioDAO();
		return dao.createUsuario(usuario, password, email, telefono);
	}

	public boolean loginUsuario(String usuario, String password) {
		UsuarioDAO dao = FactoriaDAO.getInstancia().getUsuarioDAO();
		Usuario u = dao.findUsuario(usuario);
		if (u != null) {
			return u.getPassword().equals(password);
		}
		return false;
	}

	public boolean registrarCoche(Usuario usuario, String matricula, String modelo, int year, int confort) {
		CocheDAO daoCoche = FactoriaDAO.getInstancia().getCocheDAO();
		UsuarioDAO daoUsuario = FactoriaDAO.getInstancia().getUsuarioDAO();
		
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

	public Viaje registrarViaje(int plazas, double precio) {
		ViajeDAO dao = FactoriaDAO.getInstancia().getViajeDAO();
		Viaje viaje = dao.createViaje(plazas,precio);
		return viaje;
	}

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
