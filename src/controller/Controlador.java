package controller;

import java.sql.Date;

import dao.CocheDAO;
import dao.FactoriaDAO;
import dao.ParadaDAO;
import dao.ReservaDAO;
import dao.UsuarioDAO;
import dao.ViajeDAO;
import model.Coche;
import model.Parada;
import model.Reserva;
import model.Usuario;
import model.Viaje;

public class Controlador {

	private static Controlador unicaInstancia = null;
	private Usuario usuarioLogeado = null;

	private Controlador() {
	}

	public static Controlador getInstance() {
		if (unicaInstancia == null) {
			unicaInstancia = new Controlador();
		}
		return unicaInstancia;
	}

	/* Este método recupera el objeto usuario dado el nombre de usuario */
	public Usuario findUsuario(String usuario) {
		UsuarioDAO daoUsuario = FactoriaDAO.getInstancia().getUsuarioDAO();
		return daoUsuario.findUsuario(usuario);
	}

	/* Este método recupera el objeto viaje dado el id del viaje */
	public Viaje findViaje(int id) {
		ViajeDAO daoViaje = FactoriaDAO.getInstancia().getViajeDAO();
		return daoViaje.findViaje(id);
	}

	public Coche findCoche(String matricula) {
		CocheDAO daoCoche = FactoriaDAO.getInstancia().getCocheDAO();
		return daoCoche.findCoche(matricula);
	}

	/*
	 * Este método persiste un usuario y devuelve el objeto usuario que se ha
	 * persistido o nulo en otro caso
	 */
	public Usuario registrarUsuario(String usuario, String password, Date fechaNacimiento, String profesion,
			String email, String nombre, String apellidos) {
		UsuarioDAO dao = FactoriaDAO.getInstancia().getUsuarioDAO();
		return dao.createUsuario(usuario, password, fechaNacimiento, profesion, email, nombre, apellidos);
	}

	public boolean loginUsuario(String usuario, String password) {
		UsuarioDAO dao = FactoriaDAO.getInstancia().getUsuarioDAO();
		Usuario u = dao.findUsuario(usuario);
		usuarioLogeado = u;
		if (u != null) {
			return u.getPassword().equals(password);
		}
		return false;
	}

	/*
	 * SUGERENCIA:En este metodo comentar que toda la responsabilidad de añadirCoche
	 * recae sobre el controlador violando el patrón experto Sugerencia: Persistir
	 * el coche en el controlador. El constructor del coche puede admitir el objeto
	 * usuario e implementar en Usuario un metodo llamado anadirCoche
	 */
	public boolean addCoche(String matricula, String modelo, int year, int confort) {
		CocheDAO daoCoche = FactoriaDAO.getInstancia().getCocheDAO();
		UsuarioDAO daoUsuario = FactoriaDAO.getInstancia().getUsuarioDAO();
		Coche coche = daoCoche.createCoche(matricula, modelo, year, confort);
		if (coche == null) {
			return false;
		}

		coche.setUsuario(this.usuarioLogeado);
		daoCoche.update();

		this.usuarioLogeado.setCoche(coche);
		daoUsuario.update();

		return true;
	}

	/*
	 * Este método persiste un viaje y devuelve el objeto viaje que se ha persistido
	 * o nulo en otro caso
	 */
	public Viaje registrarViaje(int plazas, double precio) {
		ViajeDAO daoViaje = FactoriaDAO.getInstancia().getViajeDAO();
		Viaje viaje = daoViaje.createViaje(plazas, precio);

		Coche coche = this.usuarioLogeado.getCoche();
		viaje.setCoche(coche);
		coche.addViaje(viaje);

		daoViaje.update();

		return viaje;
	}

	/* Si no se encuentra el idViaje se devuelve una Parada nula */
	public Parada registrarParadaOrigen(int idViaje, String ciudad, String calle, int CP, Date fecha) {
		CocheDAO daoCoche = FactoriaDAO.getInstancia().getCocheDAO();
		ParadaDAO daoParada = FactoriaDAO.getInstancia().getParadaDAO();
		ViajeDAO daoViaje = FactoriaDAO.getInstancia().getViajeDAO();

		Viaje viaje = daoViaje.findViaje(idViaje);
		if (viaje == null) {
			return null;
		}

		Parada paradaOrigen = daoParada.createParada(ciudad, calle, CP, fecha);

		viaje.setOrigen(paradaOrigen);
		daoViaje.update();
		daoCoche.update();

		return paradaOrigen;
	}

	/* Si no se encuentra el idViaje se devuelve false */
	public boolean reservarViaje(int idViaje, String comentario) {
		ReservaDAO daoReserva = FactoriaDAO.getInstancia().getReservaDAO();
		UsuarioDAO daoUsuario = FactoriaDAO.getInstancia().getUsuarioDAO();
		ViajeDAO daoViaje = FactoriaDAO.getInstancia().getViajeDAO();

		Viaje viaje = daoViaje.findViaje(idViaje);
		if (viaje == null) {
			return false;
		}

		if (viaje.isConductor(this.usuarioLogeado)) {
			return false;
		}

		Reserva reserva = daoReserva.createReserva(comentario);
		reserva.setUsuario(this.usuarioLogeado);
		this.usuarioLogeado.addReserva(reserva);

		reserva.setViaje(viaje);
		viaje.addReserva(reserva);

		daoReserva.update();
		daoUsuario.update();
		daoViaje.update();

		return true;

	}
	
	public boolean aceptarViaje(int idViaje, String usuarioReservador) {
		ViajeDAO daoViaje = FactoriaDAO.getInstancia().getViajeDAO();
		ReservaDAO daoReserva = FactoriaDAO.getInstancia().getReservaDAO();
		
		Viaje viaje = daoViaje.findViaje(idViaje);
		if (viaje == null) {
			return false;
		}
		
		Reserva reserva = viaje.getUsuarioReserva(usuarioReservador);
		if (reserva == null) {
			return false;
		}
		
		reserva.setEstadoAceptado();
		daoReserva.update(reserva);
		
		return true;
	}

}
