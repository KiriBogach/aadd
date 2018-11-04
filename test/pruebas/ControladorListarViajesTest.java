package pruebas;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import controller.Controlador;
import model.Usuario;
import model.Utils;
import model.Viaje;

public class ControladorListarViajesTest {

	@Test
	public void listarViajes() {
		/*
		 * Hay que tener en cuenta para este test la fecha del Sistema del
		 * controlador FECHA_SISTEMA = "26/02/2018";
		 */
		Controlador controlador = Controlador.getInstance();
		Date sqlDate = Utils.fromStringToDate("20/01/1994");

		/* Registramos al usuario */
		Usuario usuario = controlador.registrarUsuario("usuario14", "123", sqlDate, "m�dico", "testUsuario@gmail.com",
				"Carlos", "Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* Registramos el coche del usuario logueado */
		assertTrue(controlador.addCoche("9999999A", "Seat", 2009, 5));

		Viaje viaje = null;
		Date fechaOrigen = null;
		Date fechaDestino = null;

		/*
		 * Registramos un viaje -> saldr�a en pendientes, no saldr�a en
		 * realizados, propio de usuario14
		 */
		viaje = controlador.registrarViaje(4, 151.0);
		fechaOrigen = Utils.fromStringToDate("10/03/2018");
		controlador.registrarParadaOrigen(viaje.getId(), "Madrid", "Puerta del Sol", 03300, fechaOrigen);
		fechaDestino = Utils.fromStringToDate("12/03/2018");
		controlador.registrarParadaDestino(viaje.getId(), "Albacete", "Calle Lim�n", 15040, fechaDestino);

		/*
		 * Registramos un viaje -> no saldr�a en pendientes, saldr�a en
		 * realizados, propio de usuario14
		 */
		viaje = controlador.registrarViaje(4, 151.0);
		fechaOrigen = Utils.fromStringToDate("10/01/2018");
		controlador.registrarParadaOrigen(viaje.getId(), "Barcelona", "Puerta del Sol", 03300, fechaOrigen);
		fechaDestino = Utils.fromStringToDate("12/01/2018");
		controlador.registrarParadaDestino(viaje.getId(), "C�diz", "Calle Lim�n", 15040, fechaDestino);

		/* Listamos los viajes por el usuario que ha creado dichos viajes */

		/*
		 * (pendientes, realizados, propios, ordenFecha, ordenCiudad)
		 */

		assertEquals(2, controlador.listarViajes(false, false, false, false, false).size());

		assertEquals(1, controlador.listarViajes(true, false, false, false, false).size());

		assertEquals(1, controlador.listarViajes(false, true, false, false, false).size());

		assertEquals(2, controlador.listarViajes(false, false, true, false, false).size());

		assertEquals(2, controlador.listarViajes(true, true, false, false, false).size());

		assertEquals(2, controlador.listarViajes(true, false, true, false, false).size());

		/* Registramos m�s viaje para comprobar la ordenaci�n */
		viaje = controlador.registrarViaje(4, 151.0);
		fechaOrigen = Utils.fromStringToDate("10/08/2018");
		controlador.registrarParadaOrigen(viaje.getId(), "Zaragoza", "Puerta del Sol", 03300, fechaOrigen);
		fechaDestino = Utils.fromStringToDate("12/08/2018");
		controlador.registrarParadaDestino(viaje.getId(), "M�laga", "Calle Lim�n", 15040, fechaDestino);

		fechaOrigen = Utils.fromStringToDate("10/10/2018");
		controlador.registrarParadaOrigen(viaje.getId(), "Almer�a", "Puerta del Sol", 03300, fechaOrigen);
		fechaDestino = Utils.fromStringToDate("12/10/2018");
		controlador.registrarParadaDestino(viaje.getId(), "Pa�s Vasco", "Calle Lim�n", 15040, fechaDestino);

		/* Comprabamos la ordenaci�n por fechas */
		Date fechaAnterior = null;
		for (Viaje v : controlador.listarViajes(false, false, false, true, false)) {
			Date origen = v.getOrigen().getFecha();
			if (fechaAnterior != null) {
				// Puede ser igual que el anterior o mayor
				assertTrue(origen.compareTo(fechaAnterior) != -1);
			}
			fechaAnterior = origen;
		}

		/* Comprabamos la ordenaci�n por ciudad */
		String ciudadAnterior = null;
		for (Viaje v : controlador.listarViajes(false, false, false, false, true)) {
			String ciudad = v.getOrigen().getCiudad();
			if (ciudadAnterior != null) {
				// Puede ser igual que el anterior o mayor (alfab�ticamente)
				assertTrue(ciudad.compareTo(ciudadAnterior) != -1);
			}
			ciudadAnterior = ciudad;
		}

		/* Registramos a otro usuario que no tenga viajes propios */
		usuario = controlador.registrarUsuario("usuario15", "123", sqlDate, "m�dico", "testUsuario@gmail.com", "Carlos",
				"Martinez Serrano");
		assertNotNull(usuario);

		/* Hacemos login con el usuario registrado previamente */
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());

		/* Viajes propios 0 */
		assertEquals(0, controlador.listarViajes(false, false, true, false, false).size());

	}
}
