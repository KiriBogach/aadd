package pruebas;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import controller.Controlador;
import model.Coche;
import model.EstadoReserva;
import model.Reserva;
import model.Usuario;
import model.Utils;
import model.Viaje;

public class ControladorTest {

	@Test
	public void testRegistrarUsuario() {

		Controlador controlador = Controlador.getInstance();

		Date date = Utils.fromStringToDate("20/01/1994");

		/* Registramos el usuario por primera vez */
		Usuario usuario = controlador.registrarUsuario("usuario1", "123", date, "médico", "testUsuario@gmail.com",
				"Carlos", "Martinez Serrano");

		assertNotNull(usuario);

		/* Volvemos a registrar el mismo usuario registrado previamente */
		usuario = controlador.registrarUsuario("usuario1", "123", date, "médico", "testUsuario@gmail.com", "Carlos",
				"Martinez Serrano");

		assertNull(usuario);
	}

	@Test
	public void testLogin() {

		Controlador controlador = Controlador.getInstance();

		Date date = Utils.fromStringToDate("20/01/1994");

		/* Registramos el usuario por primera vez */
		Usuario usuario = controlador.registrarUsuario("usuario2", "123", date, "médico", "testUsuario@gmail.com",
				"Carlos", "Martinez Serrano");

		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */

		assertTrue(controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword()));

		/* Hacemos login de un usuario inexistente en la aplicación */

		assertFalse(controlador.loginUsuario("usuarioInexistente", "123"));

	}

	@Test
	public void testAddCoche() {

		Controlador controlador = Controlador.getInstance();

		Date date = Utils.fromStringToDate("20/01/1994");

		/* Registramos al usuario */
		Usuario usuario = controlador.registrarUsuario("usuario3", "123", date, "médico", "testUsuario@gmail.com",
				"Carlos", "Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* Registramos un coche inválido */
		assertFalse(controlador.addCoche("123A", "Seat", 2009, -1));

		/* Registramos un coche inválido */
		assertFalse(controlador.addCoche("123A", "Seat", 2009, 6));

		/* Registramos el coche del usuario logueado */
		assertTrue(controlador.addCoche("123A", "Seat", 2009, 5));

		/* Registramos al usuario */
		Usuario usuario4 = controlador.registrarUsuario("usuario4", "123", date, "médico", "testUsuario@gmail.com",
				"Carlos", "Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuario4.getUsuario(), usuario4.getPassword());

		/*
		 * Registramos el coche del usuario logueado pero con una matricula ya exitente
		 */
		assertFalse(controlador.addCoche("123A", "Ferrari", 2017, 5));
		
		/* Comprobamos que se han guardado correctamente los datos en la base de datos */
		assertEquals("123A", controlador.findUsuario("usuario3").getCoche().getMatricula());
	}

	@Test
	public void testRegistrarViaje() {
		Controlador controlador = Controlador.getInstance();

		Date date = Utils.fromStringToDate("20/01/1994");

		/* Registramos al usuario */
		Usuario usuario = controlador.registrarUsuario("usuario5", "123", date, "médico", "testUsuario@gmail.com",
				"Carlos", "Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* Registramos el coche del usuario logueado */
		assertTrue(controlador.addCoche("12345A", "Seat", 2009, 5));
		/* Registramos un viaje */
		assertNotNull(controlador.registrarViaje(3, 125.0));

		Coche coche = controlador.findCoche("12345A");

		assertEquals(1, coche.getViajes().size());

		/* Registramos al usuario */
		Usuario usuarioSinCoche = controlador.registrarUsuario("usuario6", "123", date, "médico",
				"testUsuario@gmail.com", "Carlos", "Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuarioSinCoche.getUsuario(), usuarioSinCoche.getPassword());
		/* Registramos un viaje */
		assertNull(controlador.registrarViaje(3, 125.0));
	}

	@Test
	public void testRegistrarParadaOrigen() {
		Controlador controlador = Controlador.getInstance();

		Date date = Utils.fromStringToDate("20/01/1994");

		/* Registramos al usuario */
		Usuario usuario = controlador.registrarUsuario("usuario7", "123", date, "médico", "testUsuario@gmail.com",
				"Carlos", "Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* Registramos el coche del usuario logueado */
		assertTrue(controlador.addCoche("123456A", "Seat", 2009, 5));

		/* Registramos un viaje */
		Viaje viaje = controlador.registrarViaje(3, 150.0);

		/* Registramos una parada origen en el viaje previamente registrado */
		date = Utils.fromStringToDate("20/11/2018");
		assertNotNull(controlador.registrarParadaOrigen(viaje.getId(), "Murcia", "C/Mayor,25", 30001, date));

		/* Registramos una parada origen en un viaje inexistente */
		assertNull(controlador.registrarParadaOrigen(-5, "Murcia", "C/Mayor,25", 30001, date));

	}

	@Test
	public void testRegistrarParadaDestino() {
		Controlador controlador = Controlador.getInstance();

		Date date = Utils.fromStringToDate("20/01/1994");

		/* Registramos al usuario */
		Usuario usuario = controlador.registrarUsuario("usuario8", "123", date, "médico", "testUsuario@gmail.com",
				"Carlos", "Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* Registramos el coche del usuario logueado */
		assertTrue(controlador.addCoche("1234567A", "Seat", 2009, 5));

		/* Registramos un viaje */
		Viaje viaje = controlador.registrarViaje(3, 155.0);

		/* Registramos una parada destino en el viaje previamente registrado */
		date = Utils.fromStringToDate("22/11/2018");
		assertNotNull(controlador.registrarParadaDestino(viaje.getId(), "Murcia", "C/Mayor,25", 30001, date));

		/* Registramos una parada destino en un viaje inexistente */
		assertNull(controlador.registrarParadaDestino(-5, "Murcia", "C/Mayor,25", 30001, date));

	}

	@Test
	public void testReservarViaje() {
		Controlador controlador = Controlador.getInstance();

		Date date = Utils.fromStringToDate("20/01/1994");

		/* Registramos al usuario */
		Usuario usuario = controlador.registrarUsuario("usuario9", "123", date, "médico", "testUsuario@gmail.com",
				"Carlos", "Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* Registramos el coche del usuario logueado */
		assertTrue(controlador.addCoche("12345678A", "Seat", 2009, 5));

		/* Registramos un viaje */
		Viaje viaje = controlador.registrarViaje(4, 157.0);

		/*
		 * Hacemos la reserva de un viaje previamente registrado pero el conductor es el
		 * mismo que hace la reserva
		 */
		assertNull(controlador.reservarViaje(viaje.getId(), "Me gustaría reservar una plaza en mi propio coche"));
		viaje = controlador.findViaje(viaje.getId());
		/*
		 * He vuelto a hacer la reasignacion ya que el viaje puede estar obsoleto pero
		 * ni aun con esas se recupera correctamente
		 */
		assertEquals(0, viaje.getReservas().size());

		/* Hacemos la reserva de un viaje inexistente */
		assertNull(controlador.reservarViaje(-5, "Me gustaría reservar una plaza"));

		/* Registramos al usuario */
		usuario = controlador.registrarUsuario("usuario10", "123", date, "médico", "testUsuario@gmail.com", "Carlos",
				"Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());
		/*
		 * Hacemos la reserva de un viaje previamente registrado
		 */
		assertNotNull(controlador.reservarViaje(viaje.getId(), "Me gustaría reservar una plaza"));
	}

	@Test
	public void testAceptarViaje() {
		Controlador controlador = Controlador.getInstance();
		Date date = Utils.fromStringToDate("20/01/1994");

		/* Registramos al usuario */
		Usuario usuario = controlador.registrarUsuario("usuario11", "123", date, "médico", "testUsuario@gmail.com",
				"Carlos", "Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* Registramos el coche del usuario logueado */
		assertTrue(controlador.addCoche("123456789A", "Seat", 2009, 5));

		/* Registramos un viaje */
		Viaje viaje = controlador.registrarViaje(4, 158.0);

		/* Registramos al usuario reservador */
		Usuario usuarioReservador = controlador.registrarUsuario("usuario12", "123", date, "médico",
				"testUsuario@gmail.com", "Carlos", "Martinez Serrano");
		assertNotNull(usuarioReservador);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuarioReservador.getUsuario(), usuarioReservador.getPassword());
		/*
		 * Hacemos la reserva de un viaje previamente registrado
		 */

		Reserva reserva = controlador.reservarViaje(viaje.getId(), "Me gustaría reservar una plaza");
		assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado());

		/* Hacemos login otra vez con el usuario que oferta un viaje */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* Aceptamos la reserva de viaje */
		viaje = controlador.aceptarViaje(viaje.getId(), usuarioReservador.getUsuario());
		assertNotNull(viaje);

		/*
		 * Aqui haria falta reasignar viaje porque esta obsoleto Ademas no existe
		 * asociacion entre viaje y reservas al recuperarla de la BD
		 */
		reserva = viaje.getReservaUsuario(usuarioReservador.getUsuario());
		assertEquals(EstadoReserva.ACEPTADA, reserva.getEstado());

		/* Aceptamo la reserva de viaje de un viaje inexistente */
		assertNull(controlador.aceptarViaje(-5, usuarioReservador.getUsuario()));

		/*
		 * Aceptamos la reserva de viaje de un usuario que no ha creado la reserva
		 * Registramos al usuario reservador
		 */
		Usuario usuarioNoReservador = controlador.registrarUsuario("usuario13", "123", date, "médico",
				"testUsuario@gmail.com", "Carlos", "Martinez Serrano");
		assertNotNull(usuarioNoReservador);

		viaje = controlador.aceptarViaje(viaje.getId(), usuarioNoReservador.getUsuario());
		assertNull(viaje);

	}

	@Test
	public void testRechazarViaje() {
		Controlador controlador = Controlador.getInstance();
		Date date = Utils.fromStringToDate("20/01/1994");

		/* Registramos al usuario */
		Usuario usuario = controlador.registrarUsuario("usuario14", "123", date, "médico", "testUsuario@gmail.com",
				"Carlos", "Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* Registramos el coche del usuario logueado */
		assertTrue(controlador.addCoche("12345678910A", "Seat", 2009, 5));

		/* Registramos un viaje */
		Viaje viaje = controlador.registrarViaje(4, 151.0);

		/* Registramos al usuario reservador */
		Usuario usuarioReservador = controlador.registrarUsuario("usuario15", "123", date, "médico",
				"testUsuario@gmail.com", "Carlos", "Martinez Serrano");
		assertNotNull(usuarioReservador);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuarioReservador.getUsuario(), usuarioReservador.getPassword());
		/*
		 * Hacemos la reserva de un viaje previamente registrado
		 */
		Reserva reserva = controlador.reservarViaje(viaje.getId(), "Me gustaría reservar una plaza");
		assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado());

		/* Hacemos login otra vez con el usuario que oferta un viaje */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* rechazamos la reserva de viaje */
		viaje = controlador.rechazarViaje(viaje.getId(), usuarioReservador.getUsuario());
		assertNotNull(viaje);

		/*
		 * Aqui haria falta reasignar viaje porque esta obsoleto Ademas no existe
		 * asociacion entre viaje y reservas al recuperarla de la BD
		 */
		reserva = viaje.getReservaUsuario(usuarioReservador.getUsuario());
		assertEquals(EstadoReserva.RECHAZADA, reserva.getEstado());

		/* Rechazamos la reserva de viaje de un viaje inexistente */
		assertNull(controlador.aceptarViaje(-5, usuarioReservador.getUsuario()));
		/*
		 * Rechazamos la reserva de viaje de un usuario que no ha creado la reserva
		 */
		/* Registramos al usuario reservador */
		Usuario usuarioNoReservador = controlador.registrarUsuario("usuario16", "123", date, "médico",
				"testUsuario@gmail.com", "Carlos", "Martinez Serrano");
		assertNotNull(usuarioNoReservador);

		assertNull(controlador.aceptarViaje(viaje.getId(), usuarioNoReservador.getUsuario()));

	}

	@Test
	public void testValorarViajePasajero() {
		Controlador controlador = Controlador.getInstance();
		Date date = Utils.fromStringToDate("20/01/1994");

		/* Registramos al usuario */
		Usuario usuario = controlador.registrarUsuario("usuario20", "123", date, "médico", "testUsuario@gmail.com",
				"Carlos", "Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* Registramos el coche del usuario logueado */
		assertTrue(controlador.addCoche("12344677910A", "Seat", 2009, 5));

		/* Registramos un viaje que ha terminado */
		Viaje viaje1 = controlador.registrarViaje(2, 155.0);
		/* registramos una parada origen en el viaje */
		Date fechaOrigen = Utils.fromStringToDate("10/01/2018");
		controlador.registrarParadaOrigen(viaje1.getId(), "Barcelona", "Puerta del Sol", 03300, fechaOrigen);
		/* registramos una parada destino en el viaje */
		Date fechaDestino = Utils.fromStringToDate("12/01/2018");
		controlador.registrarParadaDestino(viaje1.getId(), "Cádiz", "Calle Limón", 15040, fechaDestino);

		/* Registramos un viaje que no ha terminado */
		Viaje viaje2 = controlador.registrarViaje(3, 151.0);
		fechaOrigen = Utils.fromStringToDate("10/03/2018");
		controlador.registrarParadaOrigen(viaje2.getId(), "Madrid", "Puerta del Sol", 03300, fechaOrigen);
		fechaDestino = Utils.fromStringToDate("12/03/2018");
		controlador.registrarParadaDestino(viaje2.getId(), "Albacete", "Calle Limón", 15040, fechaDestino);

		/* Registramos al usuario reservador */
		Usuario usuarioReservador = controlador.registrarUsuario("usuario21", "123", date, "médico",
				"testUsuario@gmail.com", "Carlos", "Martinez Serrano");
		assertNotNull(usuarioReservador);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuarioReservador.getUsuario(), usuarioReservador.getPassword());
		/*
		 * Hacemos la reserva del viaje1
		 */
		controlador.reservarViaje(viaje1.getId(), "Me gustaría reservar una plaza");

		/*
		 * Hacemos la reserva del viaje2
		 */
		controlador.reservarViaje(viaje2.getId(), "Me gustaría reservar una plaza");

		/* El usuario pasajero valora al conductor de un viaje terminado */
		assertTrue(controlador.valorarViajePasajero(viaje1.getId(), usuario.getUsuario(), "Muy amigable", 7));
		/* El usuario pasajero valora al conductor de un viaje no terminado */
		assertFalse(controlador.valorarViajePasajero(viaje2.getId(), usuario.getUsuario(), "Muy amigable", 6));
		/* El usuario pasajero valora al conductor de un viaje inexistente */
		assertFalse(controlador.valorarViajePasajero(-5, usuario.getUsuario(), "Muy amigable", 7));

		/* Un usuario que no ha reservado intenta valorar el viaje */
		/* Registramos al usuario reservador */
		Usuario usuarioNoReservador = controlador.registrarUsuario("usuario22", "123", date, "médico",
				"testUsuario@gmail.com", "Carlos", "Martinez Serrano");
		assertNotNull(usuarioNoReservador);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuarioNoReservador.getUsuario(), usuarioNoReservador.getPassword());

		assertFalse(controlador.valorarViajePasajero(viaje1.getId(), usuario.getUsuario(), "Muy amigable", 7));

		/* Hacemos login otra vez con el usuario que oferta un viaje */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* El usuario pasajero valora al conductor de un viaje terminado */
		assertTrue(
				controlador.valorarViajeConductor(viaje1.getId(), usuarioReservador.getUsuario(), "Muy amigable", 7));
		/* El usuario pasajero valora al conductor de un viaje no terminado */
		assertFalse(
				controlador.valorarViajeConductor(viaje2.getId(), usuarioReservador.getUsuario(), "Muy amigable", 7));
		/* El usuario pasajero valora al conductor de un viaje inexistente */
		assertFalse(controlador.valorarViajeConductor(-5, usuarioReservador.getUsuario(), "Muy amigable", 7));

	}

	@Test
	public void testValorarViajeConductor() {
		Controlador controlador = Controlador.getInstance();
		Date date = Utils.fromStringToDate("20/01/1994");

		/* Registramos al usuario */
		Usuario usuario = controlador.registrarUsuario("usuario23", "123", date, "médico", "testUsuario@gmail.com",
				"Carlos", "Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* Registramos el coche del usuario logueado */
		assertTrue(controlador.addCoche("1234467567910A", "Seat", 2009, 5));

		/* Registramos un viaje que ha terminado */
		Viaje viaje1 = controlador.registrarViaje(2, 155.0);
		/* registramos una parada origen en el viaje */
		Date fechaOrigen = Utils.fromStringToDate("10/01/2018");
		controlador.registrarParadaOrigen(viaje1.getId(), "Barcelona", "Puerta del Sol", 03300, fechaOrigen);
		/* registramos una parada destino en el viaje */
		Date fechaDestino = Utils.fromStringToDate("12/01/2018");
		controlador.registrarParadaDestino(viaje1.getId(), "Cádiz", "Calle Limón", 15040, fechaDestino);

		/* Registramos un viaje que no ha terminado */
		Viaje viaje2 = controlador.registrarViaje(3, 151.0);
		/* registramos una parada origen en el viaje */
		fechaOrigen = Utils.fromStringToDate("10/03/2018");
		controlador.registrarParadaOrigen(viaje2.getId(), "Madrid", "Puerta del Sol", 03300, fechaOrigen);
		/* registramos una parada destino en el viaje */
		fechaDestino = Utils.fromStringToDate("12/03/2018");
		controlador.registrarParadaDestino(viaje2.getId(), "Albacete", "Calle Limón", 15040, fechaDestino);

		/* Registramos al usuario reservador */
		Usuario usuarioReservador = controlador.registrarUsuario("usuario24", "123", date, "médico",
				"testUsuario@gmail.com", "Carlos", "Martinez Serrano");
		assertNotNull(usuarioReservador);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuarioReservador.getUsuario(), usuarioReservador.getPassword());
		/*
		 * Hacemos la reserva del viaje1
		 */
		controlador.reservarViaje(viaje1.getId(), "Me gustaría reservar una plaza");

		/*
		 * Hacemos la reserva del viaje2
		 */
		controlador.reservarViaje(viaje2.getId(), "Me gustaría reservar una plaza");

		/* Hacemos login otra vez con el usuario que oferta un viaje */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* El usuario conductor valora al pasajero de un viaje terminado */
		assertTrue(
				controlador.valorarViajeConductor(viaje1.getId(), usuarioReservador.getUsuario(), "Muy amigable", 7));
		/* El usuario conductor valora al pasajero de un viaje no terminado */
		assertFalse(
				controlador.valorarViajeConductor(viaje2.getId(), usuarioReservador.getUsuario(), "Muy amigable", 7));
		/* El usuario conductor valora al pasajero de un viaje inexistente */
		assertFalse(controlador.valorarViajeConductor(-5, usuarioReservador.getUsuario(), "Muy amigable", 7));

	}
	

	/*
	 * Separamos el test listarViajes porque no sabemos el orden de ejecución de los
	 * testUnitarios y no podríamos contar el número de viajes correctamente en cada
	 * caso. Véase la clase test 'ControladroListaViajesTest'
	 */
}
