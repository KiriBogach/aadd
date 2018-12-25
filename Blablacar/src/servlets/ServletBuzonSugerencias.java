package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanController;

/**
 * Servlet implementation class BuzonSugerencias
 */
@WebServlet("/ServletBuzonSugerencias")
public class ServletBuzonSugerencias extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletBuzonSugerencias() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BeanController beanController = (BeanController) request.getSession().getAttribute("beanController");
		List<String> sugerencias = beanController.getControlador().getSugerencias();
		try {

			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>" + "Buzon Sugerencias" + "</title>");
			out.println("</head>");
			out.println("<body>");
			for (String sugerencia : sugerencias) {
				out.println("<h3>" + sugerencia + "<h3>");
			}
			out.println("<a href=" + "faceletsWelcome.xhtml" + ">volver al HOME</a>");
			out.println("</body>");
			out.println("</html>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * ServletContext app = getServletConfig().getServletContext();
		 * app.setAttribute("sugerencias",
		 * Controlador.getInstance().getSugerencias());
		 */

		/*
		 * String path = getServletConfig().getServletContext().getRealPath(
		 * "sugerencias.xhtml");
		 * 
		 * BufferedReader br = new BufferedReader(new FileReader(path));
		 * 
		 * PrintWriter out = response.getWriter(); String linea; while ((linea =
		 * br.readLine()) != null) { out.println(linea); }
		 * 
		 * br.close();
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
