package controller;

import java.util.Collection;
import java.util.Date;

import javax.ejb.Remote;

import model.Coche;
import model.Parada;
import model.Reserva;
import model.Usuario;
import model.Viaje;

@Remote
public interface ControladorRemote {
	
	public Usuario getUsuarioLogeado();

	/*
	 * Este método recupera el objeto usuario dado el nombre de usuario o nulo
	 * en otro caso
	 */
	public Usuario findUsuario(String usuario);

	/*
	 * Este método recupera el objeto viaje dado el id del viaje o nulo en otro
	 * caso
	 */
	public Viaje findViaje(int id);

	/*
	 * Este método recupera el objeto coche dado la matricula del coche o nulo
	 * en otro caso
	 */
	public Coche findCoche(String matricula);

	/*
	 * Este método recupera el objeto reserva dado el id de la reserva o nulo en
	 * otro caso
	 */
	public Reserva findReserva(int id);

	/*
	 * Este método persiste un usuario y devuelve el objeto usuario que se ha
	 * persistido o nulo en otro caso
	 */
	public Usuario registrarUsuario(String usuario, String password, Date fechaNacimiento, String profesion,
			String email, String nombre, String apellidos);

	/*
	 * Este método permite a un usuario poder loguearse. Devuelve true en el
	 * caso de que se haya logueado false en otro caso
	 */
	public boolean loginUsuario(String usuario, String password);

	/*
	 * Este método permite a un usuario logueado poder registrar su coche en la
	 * aplicación. Devuelve true si se ha conseguido registrar su coche, false
	 * en otro caso
	 */
	public boolean addCoche(String matricula, String modelo, int year, int confort);

	/*
	 * Este método persiste un viaje y devuelve el objeto viaje que se ha
	 * persistido o nulo en otro caso
	 */
	public Viaje registrarViaje(int plazas, double precio);

	public boolean addNotaViaje(int idViaje, String nota);

	/*
	 * Este método permite indicar la parada origen de un viaje dado. Si no se
	 * encuentra el idViaje se devuelve una Parada nula
	 */
	public Parada registrarParadaOrigen(int idViaje, String ciudad, String calle, int CP, Date fecha);

	/*
	 * Este método permite indicar la parada origen de un viaje dado. Si no se
	 * encuentra el idViaje se devuelve una Parada nula
	 */
	public Parada registrarParadaDestino(int idViaje, String ciudad, String calle, int CP, Date fecha);

	/*
	 * Este método permite crear una reserva para un viaje existente. Devuelve
	 * true si se ha hecho la reserva; false en otro caso.
	 */
	public Reserva reservarViaje(int idViaje, String comentario);

	/*
	 * Este método permite aceptar la reserva de viaje de un usuario. Devuelve
	 * verdadero si se ha conseguido aceptar la reserva; falso en otro caso
	 */
	public Viaje aceptarViaje(int idViaje, String usuarioReservador);

	/*
	 * Este método permite rechazar la reserva de viaje de un usuario. Devuelve
	 * verdadero si se ha conseguido rechazar la reserva; falso en otro caso
	 */
	public Viaje rechazarViaje(int idViaje, String usuarioReservador);

	/* El conductor valora */
	public boolean valorarViajeConductor(int idViaje, String pasajero, String comentario, int puntuacion);

	/* El pasajero valora */
	public boolean valorarViajePasajero(int idViaje, String conductor, String comentario, int puntuacion);

	public Collection<Viaje> listarViajes(boolean pendientes, boolean realizados, boolean propios, boolean ordenFecha,
			boolean ordenCiudad);

	public Collection<Viaje> listarViajes();

	public Collection<Usuario> getAllUsuarios();

	public boolean usuarioLogeadoIsConductor();

	public void logout();
}
