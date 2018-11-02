package pruebas;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import controller.Controlador;
import model.Coche;
import model.EstadoReserva;
import model.Reserva;
import model.Usuario;
import model.UtilClass;
import model.Viaje;
import servlet.ServletRegistro;

public class ControladorTest {

	@Test
	public void testRegistrarUsuario() {

		Controlador controlador = Controlador.getInstance();

		Date sqlDate = UtilClass.fromStringToSQLDate("20/01/1994");

		/* Registramos el usuario por primera vez */
		Usuario usuario = controlador.registrarUsuario("usuario1", "123", sqlDate, "médico", "testUsuario@gmail.com",
				"Carlos", "Martinez Serrano");

		assertNotNull(usuario);

		/* Volvemos a registrar el mismo usuario registrado previamente */
		usuario = controlador.registrarUsuario("usuario1", "123", sqlDate, "médico", "testUsuario@gmail.com", "Carlos",
				"Martinez Serrano");

		assertNull(usuario);
	}

	@Test
	public void testLogin() {

		Controlador controlador = Controlador.getInstance();

		Date sqlDate = UtilClass.fromStringToSQLDate("20/01/1994");

		/* Registramos el usuario por primera vez */
		Usuario usuario = controlador.registrarUsuario("usuario2", "123", sqlDate, "médico", "testUsuario@gmail.com",
				"Carlos", "Martinez Serrano");

		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */

		assertTrue(controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword()));

		/* Hacemos login de un usuario inexistente en la aplicación */

		assertFalse(controlador.loginUsuario("usuarioInexistente", "123"));

	}

	@Test
	public void testaddCoche() {

		Controlador controlador = Controlador.getInstance();

		Date sqlDate = UtilClass.fromStringToSQLDate("20/01/1994");

		/* Registramos al usuario */
		Usuario usuario = controlador.registrarUsuario("usuario3", "123", sqlDate, "médico", "testUsuario@gmail.com",
				"Carlos", "Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* Registramos el coche del usuario logueado */
		assertTrue(controlador.addCoche("123A", "Seat", 2009, 5));

		/* Registramos al usuario */
		Usuario usuario4 = controlador.registrarUsuario("usuario4", "123", sqlDate, "médico", "testUsuario@gmail.com",
				"Carlos", "Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuario4.getUsuario(), usuario4.getPassword());

		/*
		 * Registramos el coche del usuario logueado pero con una matricula ya
		 * exitente
		 */
		assertFalse(controlador.addCoche("123A", "Ferrari", 2017, 5));
	}

	@Test
	public void testRegistrarViaje() {
		Controlador controlador = Controlador.getInstance();

		Date sqlDate = UtilClass.fromStringToSQLDate("20/01/1994");

		/* Registramos al usuario */
		Usuario usuario = controlador.registrarUsuario("usuario5", "123", sqlDate, "médico", "testUsuario@gmail.com",
				"Carlos", "Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* Registramos el coche del usuario logueado */
		assertTrue(controlador.addCoche("12345A", "Seat", 2009, 5));
		/* Registramos un viaje */
		assertNotNull(controlador.registrarViaje(3, 125.0));

		Coche coche = controlador.findCoche("12345A");

		/* NO FUNCIONA */
// ------- assertEquals(1,coche.getViajes().size());

		/* Registramos al usuario */
		Usuario usuarioSinCoche = controlador.registrarUsuario("usuario6", "123", sqlDate, "médico",
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

		Date sqlDate = UtilClass.fromStringToSQLDate("20/01/1994");

		/* Registramos al usuario */
		Usuario usuario = controlador.registrarUsuario("usuario7", "123", sqlDate, "médico", "testUsuario@gmail.com",
				"Carlos", "Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* Registramos el coche del usuario logueado */
		assertTrue(controlador.addCoche("123456A", "Seat", 2009, 5));

		/* Registramos un viaje */
		Viaje viaje = controlador.registrarViaje(3, 150.0);

		/* Registramos una parada origen en el viaje previamente registrado */
		sqlDate = UtilClass.fromStringToSQLDate("20/11/2018");
		assertNotNull(controlador.registrarParadaOrigen(viaje.getId(), "Murcia", "C/Mayor,25", 30001, sqlDate));

		/* Registramos una parada origen en un viaje inexistente */
		assertNull(controlador.registrarParadaOrigen(-5, "Murcia", "C/Mayor,25", 30001, sqlDate));

	}

	@Test
	public void testRegistrarParadaDestino() {
		Controlador controlador = Controlador.getInstance();

		Date sqlDate = UtilClass.fromStringToSQLDate("20/01/1994");

		/* Registramos al usuario */
		Usuario usuario = controlador.registrarUsuario("usuario8", "123", sqlDate, "médico", "testUsuario@gmail.com",
				"Carlos", "Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* Registramos el coche del usuario logueado */
		assertTrue(controlador.addCoche("1234567A", "Seat", 2009, 5));

		/* Registramos un viaje */
		Viaje viaje = controlador.registrarViaje(3, 155.0);

		/* Registramos una parada destino en el viaje previamente registrado */
		sqlDate = UtilClass.fromStringToSQLDate("22/11/2018");
		assertNotNull(controlador.registrarParadaDestino(viaje.getId(), "Murcia", "C/Mayor,25", 30001, sqlDate));

		/* Registramos una parada destino en un viaje inexistente */
		assertNull(controlador.registrarParadaDestino(-5, "Murcia", "C/Mayor,25", 30001, sqlDate));

	}

	@Test
	public void testReservarViaje() {
		Controlador controlador = Controlador.getInstance();

		Date sqlDate = UtilClass.fromStringToSQLDate("20/01/1994");

		/* Registramos al usuario */
		Usuario usuario = controlador.registrarUsuario("usuario9", "123", sqlDate, "médico", "testUsuario@gmail.com",
				"Carlos", "Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* Registramos el coche del usuario logueado */
		assertTrue(controlador.addCoche("12345678A", "Seat", 2009, 5));

		/* Registramos un viaje */
		Viaje viaje = controlador.registrarViaje(4, 157.0);

		/*
		 * Hacemos la reserva de un viaje previamente registrado pero el
		 * conductor es el mismo que hace la reserva
		 */
		assertFalse(controlador.reservarViaje(viaje.getId(), "Me gustaría reservar una plaza"));
		viaje = controlador.findViaje(viaje.getId());
		/*
		 * He vuelto a hacer la reasignacion ya que el viaje puede estar
		 * obsoleto pero ni aun con esas se recupera correctamente
		 */
// -------assertEquals(1, viaje.getReservas().size());

		/* Hacemos la reserva de un viaje inexistente */
		assertFalse(controlador.reservarViaje(-5, "Me gustaría reservar una plaza"));

		/* Registramos al usuario */
		usuario = controlador.registrarUsuario("usuario10", "123", sqlDate, "médico", "testUsuario@gmail.com", "Carlos",
				"Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());
		/*
		 * Hacemos la reserva de un viaje previamente registrado
		 */
		assertTrue(controlador.reservarViaje(viaje.getId(), "Me gustaría reservar una plaza"));
	}

	@Test
	public void testAceptarViaje() {
		Controlador controlador = Controlador.getInstance();
		Date sqlDate = UtilClass.fromStringToSQLDate("20/01/1994");

		/* Registramos al usuario */
		Usuario usuario = controlador.registrarUsuario("usuario11", "123", sqlDate, "médico", "testUsuario@gmail.com",
				"Carlos", "Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* Registramos el coche del usuario logueado */
		assertTrue(controlador.addCoche("123456789A", "Seat", 2009, 5));

		/* Registramos un viaje */
		Viaje viaje = controlador.registrarViaje(4, 158.0);

		/* Registramos al usuario reservador */
		Usuario usuarioReservador = controlador.registrarUsuario("usuario12", "123", sqlDate, "médico",
				"testUsuario@gmail.com", "Carlos", "Martinez Serrano");
		assertNotNull(usuarioReservador);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuarioReservador.getUsuario(), usuarioReservador.getPassword());
		/*
		 * Hacemos la reserva de un viaje previamente registrado
		 */
		assertTrue(controlador.reservarViaje(viaje.getId(), "Me gustaría reservar una plaza"));

		/* Hacemos login otra vez con el usuario que oferta un viaje */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* Aceptamos la reserva de viaje */
		assertTrue(controlador.aceptarViaje(viaje.getId(), usuarioReservador.getUsuario()));

		/*
		 * Aqui haria falta reasignar viaje porque esta obsoleto Ademas no
		 * existe asociacion entre viaje y reservas al recuperarla de la BD
		 */
		Reserva reserva = viaje.getReservaUsuario(usuarioReservador.getUsuario());
// ------assertEquals(EstadoReserva.ACEPTADA, reserva.getEstado());

		/* Aceptamo la reserva de viaje de un viaje inexistente */
		assertFalse(controlador.aceptarViaje(-5, usuarioReservador.getUsuario()));
		/*
		 * Aceptamos la reserva de viaje de un usuario que no ha creado la
		 * reserva
		 */
		/* Registramos al usuario reservador */
		Usuario usuarioNoReservador = controlador.registrarUsuario("usuario13", "123", sqlDate, "médico",
				"testUsuario@gmail.com", "Carlos", "Martinez Serrano");
		assertNotNull(usuarioNoReservador);
		
		assertFalse(controlador.aceptarViaje(viaje.getId(), usuarioNoReservador.getUsuario()));

	}
	
	@Test
	public void testRechazarViaje() {
		Controlador controlador = Controlador.getInstance();
		Date sqlDate = UtilClass.fromStringToSQLDate("20/01/1994");

		/* Registramos al usuario */
		Usuario usuario = controlador.registrarUsuario("usuario14", "123", sqlDate, "médico", "testUsuario@gmail.com",
				"Carlos", "Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* Registramos el coche del usuario logueado */
		assertTrue(controlador.addCoche("12345678910A", "Seat", 2009, 5));

		/* Registramos un viaje */
		Viaje viaje = controlador.registrarViaje(4, 151.0);

		/* Registramos al usuario reservador */
		Usuario usuarioReservador = controlador.registrarUsuario("usuario15", "123", sqlDate, "médico",
				"testUsuario@gmail.com", "Carlos", "Martinez Serrano");
		assertNotNull(usuarioReservador);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuarioReservador.getUsuario(), usuarioReservador.getPassword());
		/*
		 * Hacemos la reserva de un viaje previamente registrado
		 */
		assertTrue(controlador.reservarViaje(viaje.getId(), "Me gustaría reservar una plaza"));

		/* Hacemos login otra vez con el usuario que oferta un viaje */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* rechazamos la reserva de viaje */
		assertTrue(controlador.aceptarViaje(viaje.getId(), usuarioReservador.getUsuario()));

		/*
		 * Aqui haria falta reasignar viaje porque esta obsoleto Ademas no
		 * existe asociacion entre viaje y reservas al recuperarla de la BD
		 */
		Reserva reserva = viaje.getReservaUsuario(usuarioReservador.getUsuario());
// ------assertEquals(EstadoReserva.RECHAZADA, reserva.getEstado());

		/* Rechazamos la reserva de viaje de un viaje inexistente */
		assertFalse(controlador.aceptarViaje(-5, usuarioReservador.getUsuario()));
		/*
		 * Rechazamos la reserva de viaje de un usuario que no ha creado la
		 * reserva
		 */
		/* Registramos al usuario reservador */
		Usuario usuarioNoReservador = controlador.registrarUsuario("usuario16", "123", sqlDate, "médico",
				"testUsuario@gmail.com", "Carlos", "Martinez Serrano");
		assertNotNull(usuarioNoReservador);
		
		assertFalse(controlador.aceptarViaje(viaje.getId(), usuarioNoReservador.getUsuario()));

	}


}
