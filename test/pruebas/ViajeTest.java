package pruebas;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import controller.Controlador;
import model.Usuario;
import model.Viaje;
import servlet.ServletRegistro;

public class ViajeTest {

	@Test
	public void test() {
		/*SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date fecha = null;

		try {
			fecha = formatoDelTexto.parse("01/01/2020");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());

		SimpleDateFormat dateFormat = new SimpleDateFormat(ServletRegistro.FORMATO_FECHA);
		Controlador controlador = Controlador.getInstance();
		Usuario usuario = controlador.findUsuario("OfertaViaje");
		if (usuario == null) {
			Date fechaNacimiento = null;
			try {
				fechaNacimiento = dateFormat.parse("20/01/1994");
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
			java.sql.Date sqlDate2 = null;
			if (fechaNacimiento != null)
				sqlDate2 = new java.sql.Date(fechaNacimiento.getTime());

			usuario = controlador.registrarUsuario("OfertaViaje", "123", sqlDate2, "médico", "testUsuario@gmail.com",
					"Carlos", "Martinez Serrano");
		}
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());
		if (controlador.findCoche("123A") == null) {
			controlador.addCoche("123A", "Seat", 2009, 10);
		}
		Viaje viaje = Controlador.getInstance().registrarViaje(3, 125.0);
		Controlador.getInstance().registrarParadaOrigen(viaje.getId(), "Murcia", "C/Mayor,25", 30001, sqlDate);
		Viaje viajeJPA = Controlador.getInstance().findViaje(viaje.getId());
		assertNotNull(viajeJPA);*/
	}

	@Test
	public void listarViajes() {
		Controlador controlador = Controlador.getInstance();
		// boolean pendientes, boolean realizados, boolean propios, boolean ordenFecha, boolean ordenCiudad
		
		
		Usuario usuario = controlador.findUsuario("OfertaViaje");
		controlador.loginUsuario(usuario.getUsuario(), usuario.getPassword());
		
		for (Viaje v : controlador.listarViajes(false, false, true, false, false)) {
			System.out.println(v.getId());
		}

	}
}
