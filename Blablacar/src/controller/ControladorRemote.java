package controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import model.Coche;
import model.Parada;
import model.Reserva;
import model.Usuario;
import model.Viaje;

@Remote
public interface ControladorRemote {

	public Usuario getUsuarioLogeado();

	public Usuario findUsuario(String usuario);

	public Viaje findViaje(int id);

	public Coche findCoche(String matricula);

	public Reserva findReserva(int id);

	public Collection<Reserva> getReservasUsuarioLogeado();

	public Usuario registrarUsuario(String usuario, String password, Date fechaNacimiento, String profesion,
			String email, String nombre, String apellidos);

	public boolean loginUsuario(String usuario, String password);

	public boolean addCoche(String matricula, String modelo, int year, int confort);

	public Viaje registrarViaje(int plazas, double precio);

	public boolean addNotaViaje(int idViaje, String nota);

	public Parada registrarParadaOrigen(int idViaje, String ciudad, String calle, int CP, Date fecha);

	public Parada registrarParadaDestino(int idViaje, String ciudad, String calle, int CP, Date fecha);

	public Reserva reservarViaje(int idViaje, String comentario);

	public Viaje aceptarViaje(int idViaje, String usuarioReservador);

	public Viaje rechazarViaje(int idViaje, String usuarioReservador);

	public boolean valorarViajeConductor(int idViaje, String pasajero, String comentario, int puntuacion);

	public boolean valorarViajePasajero(int idViaje, String conductor, String comentario, int puntuacion);

	public Collection<Viaje> listarViajes(boolean pendientes, boolean realizados, boolean propios, boolean ordenFecha,
			boolean ordenCiudad);

	public Collection<Viaje> listarViajes();

	public Collection<Usuario> getAllUsuarios();

	public boolean usuarioLogeadoIsConductor();

	public void logout();

	public Collection<Viaje> listen();

	public void addSugerencia(String sugerencia);

	public List<String> getSugerencias();
}