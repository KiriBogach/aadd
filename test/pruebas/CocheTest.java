package pruebas;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import controller.Controlador;
import model.Usuario;

public class CocheTest {

	@Test
	public void testRegistroCoche() {
		Controlador controlador = Controlador.getInstance();
		Usuario usuario = controlador.findUsuario("TestUsuario");
		if (usuario == null) {
			usuario = controlador.createUsuario("TestUsuario", "123", "email", "123");
		}
		
		assertNotNull(usuario);
		controlador.registrarCoche(usuario, "123A", "Seat", 2009, 10);
		

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
