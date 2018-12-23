package controller;

import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.Collection;

import model.Coche;
import model.Parada;
import model.Reserva;
import model.Usuario;
import model.Utils;
import model.Viaje;

public class Controlador {
	private ControladorRemote controladorRemoto;
	private static Controlador unicaInstancia = null;

	public static final String FECHA_SISTEMA = "26/02/2019";
	public static Date FECHA_SISTEMA_DATE = null;

	private Controlador() {
		FECHA_SISTEMA_DATE = Utils.fromStringToDate(FECHA_SISTEMA);
		try {
			controladorRemoto = (ControladorRemote) new InitialContext()
					.lookup("java:global/BlablacarEJB/ControladorRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/* Patrón Singleton */
	public static Controlador getInstance() {
		if (unicaInstancia == null) {
			unicaInstancia = new Controlador();
		}
		return unicaInstancia;
	}

	public Usuario getUsuarioLogeado() {
		return controladorRemoto.getUsuarioLogeado();
	}

	/*
	 * Este método recupera el objeto usuario dado el nombre de usuario o nulo
	 * en otro caso
	 */
	public Usuario findUsuario(String usuario) {
		return controladorRemoto.findUsuario(usuario);
	}

	/*
	 * Este método recupera el objeto viaje dado el id del viaje o nulo en otro
	 * caso
	 */
	public Viaje findViaje(int id) {
		return controladorRemoto.findViaje(id);
	}

	/*
	 * Este método recupera el objeto coche dado la matricula del coche o nulo
	 * en otro caso
	 */
	public Coche findCoche(String matricula) {
		return controladorRemoto.findCoche(matricula);
	}

	/*
	 * Este método recupera el objeto reserva dado el id de la reserva o nulo en
	 * otro caso
	 */
	public Reserva findReserva(int id) {
		return controladorRemoto.findReserva(id);
	}

	/*
	 * Este método recupera todas las reservas del usuario logeado
	 */
	public Collection<Reserva> getReservasUsuarioLogeado() {
		return controladorRemoto.getReservasUsuarioLogeado();
	}

	/*
	 * Este método persiste un usuario y devuelve el objeto usuario que se ha
	 * persistido o nulo en otro caso
	 */
	public Usuario registrarUsuario(String usuario, String password, Date fechaNacimiento, String profesion,
			String email, String nombre, String apellidos) {

		return controladorRemoto.registrarUsuario(usuario, password, fechaNacimiento, profesion, email, nombre,
				apellidos);
	}

	/*
	 * Este método permite a un usuario poder loguearse. Devuelve true en el
	 * caso de que se haya logueado false en otro caso
	 */
	public boolean loginUsuario(String usuario, String password) {
		return controladorRemoto.loginUsuario(usuario, password);
	}

	/*
	 * Este método permite a un usuario logueado poder registrar su coche en la
	 * aplicación. Devuelve true si se ha conseguido registrar su coche, false
	 * en otro caso
	 */
	public boolean addCoche(String matricula, String modelo, int year, int confort) {
		return controladorRemoto.addCoche(matricula, modelo, year, confort);
	}

	/*
	 * Este método persiste un viaje y devuelve el objeto viaje que se ha
	 * persistido o nulo en otro caso
	 */
	public Viaje registrarViaje(int plazas, double precio) {
		return controladorRemoto.registrarViaje(plazas, precio);
	}

	public boolean addNotaViaje(int idViaje, String nota) {
		return controladorRemoto.addNotaViaje(idViaje, nota);
	}

	/*
	 * Este método permite indicar la parada origen de un viaje dado. Si no se
	 * encuentra el idViaje se devuelve una Parada nula
	 */
	public Parada registrarParadaOrigen(int idViaje, String ciudad, String calle, int CP, Date fecha) {
		return controladorRemoto.registrarParadaOrigen(idViaje, ciudad, calle, CP, fecha);
	}

	/*
	 * Este método permite indicar la parada origen de un viaje dado. Si no se
	 * encuentra el idViaje se devuelve una Parada nula
	 */
	public Parada registrarParadaDestino(int idViaje, String ciudad, String calle, int CP, Date fecha) {
		return controladorRemoto.registrarParadaDestino(idViaje, ciudad, calle, CP, fecha);
	}

	/*
	 * Este método permite crear una reserva para un viaje existente. Devuelve
	 * true si se ha hecho la reserva; false en otro caso.
	 */
	public Reserva reservarViaje(int idViaje, String comentario) {
		return controladorRemoto.reservarViaje(idViaje, comentario);
	}

	/*
	 * Este método permite aceptar la reserva de viaje de un usuario. Devuelve
	 * verdadero si se ha conseguido aceptar la reserva; falso en otro caso
	 */
	public Viaje aceptarViaje(int idViaje, String usuarioReservador) {
		return controladorRemoto.aceptarViaje(idViaje, usuarioReservador);
	}

	/*
	 * Este método permite rechazar la reserva de viaje de un usuario. Devuelve
	 * verdadero si se ha conseguido rechazar la reserva; falso en otro caso
	 */
	public Viaje rechazarViaje(int idViaje, String usuarioReservador) {
		return controladorRemoto.rechazarViaje(idViaje, usuarioReservador);
	}

	/* El conductor valora */
	public boolean valorarViajeConductor(int idViaje, String pasajero, String comentario, int puntuacion) {
		return controladorRemoto.valorarViajeConductor(idViaje, pasajero, comentario, puntuacion);

	}

	/* El pasajero valora */
	public boolean valorarViajePasajero(int idViaje, String conductor, String comentario, int puntuacion) {
		return controladorRemoto.valorarViajePasajero(idViaje, conductor, comentario, puntuacion);

	}

	public Collection<Viaje> listarViajes(boolean pendientes, boolean realizados, boolean propios, boolean ordenFecha,
			boolean ordenCiudad) {

		return controladorRemoto.listarViajes(pendientes, realizados, propios, ordenFecha, ordenCiudad);
	}

	public Collection<Viaje> listarViajes() {
		return controladorRemoto.listarViajes();
	}

	public Collection<Usuario> getAllUsuarios() {
		return controladorRemoto.getAllUsuarios();
	}

	public boolean usuarioLogeadoIsConductor() {
		return controladorRemoto.usuarioLogeadoIsConductor();
	}

	public void logout() {
		controladorRemoto.logout();
	}

	public Collection<Viaje> listen() {
		return controladorRemoto.listen();
	}

}
