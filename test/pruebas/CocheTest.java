package pruebas;

import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import controller.Controlador;
import model.Usuario;
import servlet.ServletRegistro;

public class CocheTest {

	@Test
	public void testRegistroCoche() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(ServletRegistro.FORMATO_FECHA);
		Controlador controlador = Controlador.getInstance();
		Usuario usuario = controlador.findUsuario("TestUsuario");
		if (usuario == null) {
			Date fechaNacimiento = null;
			try {
				fechaNacimiento = dateFormat.parse("20/01/1994");
			} catch (java.text.ParseException e) {

				e.printStackTrace();
			}
			java.sql.Date sqlDate = null;
			if (fechaNacimiento != null)
				sqlDate = new java.sql.Date(fechaNacimiento.getTime());

			usuario = controlador.registrarUsuario("TestUsuario", "123", sqlDate, "médico", "testUsuario@gmail.com",
					"Carlos", "Martinez Serrano");
		}

		assertNotNull(usuario);
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());
		controlador.addCoche("123A", "Seat", 2009, 10);

	}

}
