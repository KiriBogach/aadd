package pruebas;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import controller.Controlador;
import model.Viaje;

public class ViajeTest {

	@Test
	public void test() {
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date fecha = null;

		try {
			fecha = formatoDelTexto.parse("01/01/2014");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
		Viaje viaje = Controlador.getInstance().registrarViaje(3, 125.0);
		Controlador.getInstance().registrarParadaOrigen(viaje.getId(), "Murcia", "C/Mayor,25", 30001, sqlDate);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aadd");
		EntityManager em = emf.createEntityManager();
		Viaje viajeJPA = em.find(Viaje.class, viaje.getId());
		assertNotNull(viajeJPA);
	}

}
