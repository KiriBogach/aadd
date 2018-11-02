package pruebas;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import controller.Controlador;
import model.Coche;
import model.Usuario;
import model.UtilClass;
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
		assertTrue(controlador.addCoche("123A", "Seat", 2009, 10));

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
		assertFalse(controlador.addCoche("123A", "Ferrari", 2017, 10));
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
		assertTrue(controlador.addCoche("12345A", "Seat", 2009, 10));
		/* Registramos un viaje */
		assertNotNull(controlador.registrarViaje(3, 125.0));
		
		Coche coche = controlador.findCoche("12345A");
		
		
		assertEquals(1,coche.getViajes().size());

		/* Registramos al usuario */
		Usuario usuarioSinCoche = controlador.registrarUsuario("usuario6", "123", sqlDate, "médico",
				"testUsuario@gmail.com", "Carlos", "Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuarioSinCoche.getUsuario(), usuarioSinCoche.getPassword());
		/* Registramos un viaje */
		assertNull(controlador.registrarViaje(3, 125.0));

	}

}
