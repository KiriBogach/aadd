package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Parada;
import model.Viaje;

/**
 * Servlet implementation class ServletListadosTestJPA
 */
@WebServlet("/ServletListadosTestJPA")
public class ServletListadosTestJPA extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletListadosTestJPA() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aadd");
		EntityManager em = emf.createEntityManager();
		out.println("<head></head><body>");
		Query q = em.createQuery("SELECT v FROM Viaje v");
		List<Viaje> viajes = q.getResultList();
		out.println("Viajes<br>");
		for (Viaje viaje : viajes) {
			out.println(" -> " + viaje.getId() + " : " + viaje.getPlazas() + " : " + viaje.getPrecio() + "<br>");
			Parada parada = viaje.getOrigen();
			out.println(" -> " + parada.getId() + " : " + parada.getCiudad() + "<br>");
		}
		out.println("<br>");
		int ultimoIdviaje = -1;
		out.println("Viajes con consulta nativa<br>");
		Query q1 = em.createNativeQuery("SELECT * FROM Viaje", Viaje.class);
		viajes = q1.getResultList();
		for (Viaje viaje : viajes) {
			out.println(" -> " + viaje.getId() + " : " + viaje.getPlazas() + " : " + viaje.getPrecio() + "<br>");
			ultimoIdviaje = viaje.getId();
		}
		out.println("<br>");
		out.println("Viajes con consulta nombrada<br>");
		Query p1 = em.createNamedQuery("findViajeById").setParameter("id", ultimoIdviaje);
		viajes = p1.getResultList();
		for (Viaje viaje : viajes) {
			out.println(" -> " + viaje.getId() + " : " + viaje.getPlazas() + " : " + viaje.getPrecio() + "<br>");
		}
		out.println("<br>");
		out.println("</body>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
