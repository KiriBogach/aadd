package controller;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import java.util.Collection;

import dao.CocheDAO;

import dao.FactoriaDAO;
import dao.FactoriaDAOLocal;
import dao.ParadaDAO;
import dao.ReservaDAO;
import dao.UsuarioDAO;
import dao.ViajeDAO;
import model.Coche;
import model.Parada;
import model.Reserva;
import model.Usuario;
import model.Valoracion;
import model.Viaje;

@Stateful(name = "ControladorRemote")
public class Controlador implements ControladorRemote {

	private Usuario usuarioLogeado = null;
	public static final String FECHA_SISTEMA = "26/02/2019";
	public static Date FECHA_SISTEMA_DATE = null;
	@EJB(beanName = "Contador")
	private ContadorEJB contador;
	@EJB(beanName = "Factoria")
	private FactoriaDAOLocal factoria;
	@Resource
	private SessionContext contexto;

	@PostConstruct
	public void configurarBlaBlaCarEJB() {
		// Configurar la instancia de DAOFactoria
		System.out.println("Controlador.configurarBlaBlaCarEJB()");
		factoria.setDAOFactoria(FactoriaDAO.JPA);
	}

	public Usuario getUsuarioLogeado() {
		return this.usuarioLogeado;
	}

	/*
	 * Este método recupera el objeto usuario dado el nombre de usuario o nulo
	 * en otro caso
	 */
	public Usuario findUsuario(String usuario) {
		UsuarioDAO daoUsuario = factoria.getUsuarioDAO();
		return daoUsuario.findUsuario(usuario);
	}

	/*
	 * Este método recupera el objeto viaje dado el id del viaje o nulo en otro
	 * caso
	 */
	public Viaje findViaje(int id) {
		ViajeDAO daoViaje = factoria.getViajeDAO();
		return daoViaje.findViaje(id);
	}

	/*
	 * Este método recupera el objeto coche dado la matricula del coche o nulo
	 * en otro caso
	 */
	public Coche findCoche(String matricula) {
		CocheDAO daoCoche = factoria.getCocheDAO();
		return daoCoche.findCoche(matricula);
	}

	/*
	 * Este método recupera el objeto reserva dado el id de la reserva o nulo en
	 * otro caso
	 */
	public Reserva findReserva(int id) {
		ReservaDAO daoReserva = factoria.getReservaDAO();
		return daoReserva.findReserva(id);
	}

	/*
	 * Este método recupera todas las reservas del usuario logeado
	 */
	public Collection<Reserva> getReservasUsuarioLogeado() {
		return this.usuarioLogeado.getReservas();
	}

	/*
	 * Este método persiste un usuario y devuelve el objeto usuario que se ha
	 * persistido o nulo en otro caso
	 */
	public Usuario registrarUsuario(String usuario, String password, Date fechaNacimiento, String profesion,
			String email, String nombre, String apellidos) {

		if (findUsuario(usuario) != null)
			return null;

		UsuarioDAO dao = factoria.getUsuarioDAO();
		return dao.createUsuario(usuario, password, fechaNacimiento, profesion, email, nombre, apellidos);
	}

	/*
	 * Este método permite a un usuario poder loguearse. Devuelve true en el
	 * caso de que se haya logueado false en otro caso
	 */
	public boolean loginUsuario(String usuario, String password) {
		Usuario u = findUsuario(usuario);
		if (u != null && u.getPassword().equals(password)) {
			this.usuarioLogeado = u;
			return true;
		}
		return false;
	}

	/*
	 * Este método permite a un usuario logueado poder registrar su coche en la
	 * aplicación. Devuelve true si se ha conseguido registrar su coche, false
	 * en otro caso
	 */
	public boolean addCoche(String matricula, String modelo, int year, int confort) {
		if (findCoche(matricula) != null) {
			return false;
		}

		CocheDAO daoCoche = factoria.getCocheDAO();
		UsuarioDAO daoUsuario = factoria.getUsuarioDAO();

		Coche coche = daoCoche.createCoche(matricula, modelo, year, confort);
		if (coche == null) {
			return false;
		}

		coche.setUsuario(this.usuarioLogeado);
		daoCoche.update();

		this.usuarioLogeado.setCoche(coche);
		daoUsuario.update(this.usuarioLogeado);

		return true;
	}

	/*
	 * Este método persiste un viaje y devuelve el objeto viaje que se ha
	 * persistido o nulo en otro caso
	 */
	public Viaje registrarViaje(int plazas, double precio) {
		if (!this.usuarioLogeado.tieneCoche()) {
			return null;
		}

		CocheDAO daoCoche = factoria.getCocheDAO();
		ViajeDAO daoViaje = factoria.getViajeDAO();

		Viaje viaje = daoViaje.createViaje(plazas, precio);
		this.usuarioLogeado.registrarViaje(viaje);

		daoViaje.update();
		daoCoche.update(this.usuarioLogeado.getCoche());

		return viaje;
	}

	public boolean addNotaViaje(int idViaje, String nota) {
		ViajeDAO daoViaje = factoria.getViajeDAO();

		Viaje viaje = daoViaje.findViaje(idViaje);
		if (viaje == null) {
			return false;
		}

		viaje.addNota(nota);
		daoViaje.update();

		return true;
	}

	/*
	 * Este método permite indicar la parada origen de un viaje dado. Si no se
	 * encuentra el idViaje se devuelve una Parada nula
	 */
	public Parada registrarParadaOrigen(int idViaje, String ciudad, String calle, int CP, Date fecha) {
		CocheDAO daoCoche = factoria.getCocheDAO();
		ParadaDAO daoParada = factoria.getParadaDAO();
		ViajeDAO daoViaje = factoria.getViajeDAO();

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

	/*
	 * Este método permite indicar la parada origen de un viaje dado. Si no se
	 * encuentra el idViaje se devuelve una Parada nula
	 */
	public Parada registrarParadaDestino(int idViaje, String ciudad, String calle, int CP, Date fecha) {
		CocheDAO daoCoche = factoria.getCocheDAO();
		ParadaDAO daoParada = factoria.getParadaDAO();
		ViajeDAO daoViaje = factoria.getViajeDAO();

		Viaje viaje = daoViaje.findViaje(idViaje);
		if (viaje == null) {
			return null;
		}

		Parada paradaDestino = daoParada.createParada(ciudad, calle, CP, fecha);

		viaje.setDestino(paradaDestino);
		daoViaje.update();
		daoCoche.update();

		return paradaDestino;
	}

	/*
	 * Este método permite crear una reserva para un viaje existente. Devuelve
	 * true si se ha hecho la reserva; false en otro caso.
	 */
	public Reserva reservarViaje(int idViaje, String comentario) {
		ReservaDAO daoReserva = factoria.getReservaDAO();
		UsuarioDAO daoUsuario = factoria.getUsuarioDAO();
		ViajeDAO daoViaje = factoria.getViajeDAO();

		Viaje viaje = daoViaje.findViaje(idViaje);
		if (viaje == null) {
			return null;
		}

		if (viaje.isConductor(this.usuarioLogeado.getUsuario())) {
			return null;
		}

		Reserva reserva = daoReserva.createReserva(comentario);
		reserva.setUsuario(this.usuarioLogeado);
		this.usuarioLogeado.addReserva(reserva);

		reserva.setViaje(viaje);
		viaje.addReserva(reserva);

		daoReserva.update();
		daoUsuario.update(this.usuarioLogeado);
		daoViaje.update();

		return reserva;

	}

	/*
	 * Este método permite aceptar la reserva de viaje de un usuario. Devuelve
	 * verdadero si se ha conseguido aceptar la reserva; falso en otro caso
	 */
	public Viaje aceptarViaje(int idViaje, String usuarioReservador) {
		ViajeDAO daoViaje = factoria.getViajeDAO();
		ReservaDAO daoReserva = factoria.getReservaDAO();

		Viaje viaje = daoViaje.findViaje(idViaje);
		if (viaje == null) {
			return null;
		}

		Reserva reserva = viaje.getReservaUsuario(usuarioReservador);
		if (reserva == null) {
			return null;
		}

		if (!reserva.setEstadoAceptado()) {
			return null;
		}

		daoReserva.update(reserva);

		return viaje;
	}

	/*
	 * Este método permite rechazar la reserva de viaje de un usuario. Devuelve
	 * verdadero si se ha conseguido rechazar la reserva; falso en otro caso
	 */
	public Viaje rechazarViaje(int idViaje, String usuarioReservador) {
		ViajeDAO daoViaje = factoria.getViajeDAO();
		ReservaDAO daoReserva = factoria.getReservaDAO();

		Viaje viaje = daoViaje.findViaje(idViaje);
		if (viaje == null) {
			return null;
		}

		Reserva reserva = viaje.getReservaUsuario(usuarioReservador);
		if (reserva == null) {
			return null;
		}

		reserva.setEstadoRechazado();
		daoReserva.update(reserva);

		return viaje;
	}

	/* El conductor valora */
	public boolean valorarViajeConductor(int idViaje, String pasajero, String comentario, int puntuacion) {
		ViajeDAO daoViaje = factoria.getViajeDAO();
		UsuarioDAO daoUsuario = factoria.getUsuarioDAO();
		ReservaDAO daoReserva = factoria.getReservaDAO();

		Viaje viaje = daoViaje.findViaje(idViaje);
		if (viaje == null) {
			return false;
		}

		if (!viaje.isFinalizado(FECHA_SISTEMA_DATE)) {
			return false;
		}

		Usuario usuarioPasajero = daoUsuario.findUsuario(pasajero);
		if (usuarioPasajero == null) {
			return false;
		}

		Reserva reserva = viaje.getReservaUsuario(pasajero);
		if (reserva == null) {
			return false;
		}

		if (reserva.haValorado(this.usuarioLogeado) || !reserva.isAceptada()) {
			return false;
		}

		Valoracion valoracion = this.usuarioLogeado.createValoracion(comentario, puntuacion, usuarioPasajero, reserva);
		if (valoracion == null) {
			return false;
		}

		// Hacemos merge de usuarioLogeado y actualizamos usuarioConductor
		daoUsuario.update(this.usuarioLogeado);

		daoReserva.update(reserva);
		daoViaje.update();

		return true;

	}

	/* El pasajero valora */
	public boolean valorarViajePasajero(int idViaje, String conductor, String comentario, int puntuacion) {
		ViajeDAO daoViaje = factoria.getViajeDAO();
		UsuarioDAO daoUsuario = factoria.getUsuarioDAO();
		ReservaDAO daoReserva = factoria.getReservaDAO();

		Viaje viaje = daoViaje.findViaje(idViaje);
		if (viaje == null) {
			return false;
		}

		if (!viaje.isFinalizado(FECHA_SISTEMA_DATE)) {
			return false;
		}

		Usuario usuarioConductor = daoUsuario.findUsuario(conductor);
		if (usuarioConductor == null) {
			return false;
		}

		Reserva reserva = viaje.getReservaUsuario(this.usuarioLogeado.getUsuario());
		if (reserva == null) {
			return false;
		}

		if (reserva.haValorado(this.usuarioLogeado) || !reserva.isAceptada()) {
			return false;
		}

		Valoracion valoracion = this.usuarioLogeado.createValoracion(comentario, puntuacion, usuarioConductor, reserva);
		if (valoracion == null) {
			return false;
		}

		// Hacemos merge de usuarioLogeado y actualizamos usuarioConductor
		daoUsuario.update(this.usuarioLogeado);

		daoReserva.update(reserva);
		daoViaje.update();

		return true;

	}

	public Collection<Viaje> listarViajes(boolean pendientes, boolean realizados, boolean propios, boolean ordenFecha,
			boolean ordenCiudad) {

		/*
		 * La construcción de la query se delega al DAO concreto para abstraer
		 * la sintaxis de la BBDD. Si la contruyéramos aquí, en JPQL, por
		 * ejemplo, estaríamos obligados a usar JPQL en nuestro sistema o usar
		 * otro método de listarViajes diferente en función del lenguaje
		 */
		String usuario = (propios) ? this.getUsuarioLogeado().getUsuario() : null;

		ViajeDAO daoViaje = factoria.getViajeDAO();
		return daoViaje.getAllViajesBy(pendientes, realizados, usuario, ordenFecha, ordenCiudad);

	}

	public Collection<Viaje> listarViajes() {
		return listarViajes(false, false, false, false, false);
	}

	public Collection<Usuario> getAllUsuarios() {
		UsuarioDAO daoUsuario = factoria.getUsuarioDAO();
		return daoUsuario.getAllUsuarios();
	}

	public boolean usuarioLogeadoIsConductor() {
		if (this.usuarioLogeado != null) {
			return this.usuarioLogeado.tieneCoche();
		}
		return false;
	}

	public void logout() {
		this.usuarioLogeado = null;
	}

	@Override
	public Collection<Viaje> listen() {
		Collection<Viaje> viajesToListen = this.usuarioLogeado.getCoche().getViajes();
		viajesToListen.addAll(this.usuarioLogeado.getViajesReservasAceptadas());
		return viajesToListen;
	}

}
