package pruebas;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import controller.Controlador;
import model.Usuario;
import model.Viaje;
import servlet.ServletRegistro;

public class ValoracionTest {
	private Usuario usuario1;
	private Usuario usuario2;

	@Before
	public void before() {
		/*SimpleDateFormat dateFormat = new SimpleDateFormat(ServletRegistro.FORMATO_FECHA);
		Controlador controlador = Controlador.getInstance();
		this.usuario1 = controlador.findUsuario("OfertaViaje");
		if (this.usuario1 == null) {
			Date fechaNacimiento = null;
			try {
				fechaNacimiento = dateFormat.parse("20/01/1994");
			} catch (java.text.ParseException e) {

				e.printStackTrace();
			}
			java.sql.Date sqlDate = null;
			if (fechaNacimiento != null)
				sqlDate = new java.sql.Date(fechaNacimiento.getTime());

			this.usuario1 = controlador.registrarUsuario("OfertaViaje", "123", sqlDate, "médico",
					"testUsuario@gmail.com", "Carlos", "Martinez Serrano");
		}

		this.usuario2 = controlador.findUsuario("ReservaViaje");
		if (this.usuario2 == null) {
			Date fechaNacimiento = null;
			try {
				fechaNacimiento = dateFormat.parse("20/01/1994");
			} catch (java.text.ParseException e) {

				e.printStackTrace();
			}
			java.sql.Date sqlDate = null;
			if (fechaNacimiento != null)
				sqlDate = new java.sql.Date(fechaNacimiento.getTime());

			this.usuario2 = controlador.registrarUsuario("ReservaViaje", "123", sqlDate, "médico",
					"testUsuario@gmail.com", "Carlos", "Martinez Serrano");
		}*/
	}

	@Test
	public void testValorarViajeConductor() {
		Controlador controlador = Controlador.getInstance();
		
		Usuario usuarioOfertador = this.usuario1;
		assertNotNull(usuarioOfertador);
		
		controlador.loginUsuario(usuarioOfertador.getUsuario(), usuarioOfertador.getPassword());
		if (controlador.findCoche("123A") == null) {
			controlador.addCoche("123A", "Seat", 2009, 10);
		}
		
		Viaje viaje = controlador.registrarViaje(10, 200);

		Usuario usuarioLogeado = this.usuario2;
		assertNotNull(usuarioLogeado);
		controlador.loginUsuario(usuarioLogeado.getUsuario(), usuarioLogeado.getPassword());
		assertTrue(controlador.reservarViaje(viaje.getId(), "Reservamos un viaje muy chulo"));
		
		controlador.loginUsuario(usuarioOfertador.getUsuario(), usuarioOfertador.getPassword());
		assertTrue(controlador.aceptarViaje(viaje.getId(), "ReservaViaje"));
		
		assertTrue(controlador.valorarViajeConductor(viaje.getId(), usuarioLogeado.getUsuario(), "Buen pasajero", 3));

	}

}
