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
			Date fechaNacimiento=null;
			try {
				fechaNacimiento = dateFormat.parse("20/01/1994");
			} catch (java.text.ParseException e) {
				
				e.printStackTrace();
			}
			java.sql.Date sqlDate=null;
			if(fechaNacimiento!=null)
			sqlDate = new java.sql.Date(fechaNacimiento.getTime());
			
			controlador.registrarUsuario("TestUsuario", "123", sqlDate, "médico", "testUsuario@gmail.com", "Carlos", "Martinez Serrano");
		}
		
		assertNotNull(usuario);
		controlador.registrarCoche(usuario.getUsuario(), "123A", "Seat", 2009, 10);
		

		/*
		 * SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		 * java.util.Date fecha = null; try { fecha =
		 * formatoDelTexto.parse("01/01/2014"); } catch (ParseException ex) {
		 * ex.printStackTrace(); } java.sql.Date sqlDate = new
		 * java.sql.Date(fecha.getTime()); int idViaje =
		 * Controlador.getInstance().registrarViaje(3, 125.0);
		 * Blablacar.registrarPradaOrigen(idViaje, "Murcia", "C/Mayor,25", 30001,
		 * sqlDate); EntityManagerFactory emf =
		 * Persistence.createEntityManagerFactory("PracticaFinal"); EntityManager em =
		 * emf.createEntityManager(); ViajeJPA viajeJPA = em.find(ViajeJPA.class,
		 * idViaje); assertNotNull(viajeJPA);
		 */
	}

}
