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
			out.println("<h2>Buzón de Sugerencias<h2>");
			for (String sugerencia : sugerencias) {
				out.println("<h3>" + sugerencia + "<h3>");
			}
			out.println("<a href=" + "faceletsWelcome.xhtml" + ">Volver a HOME</a>");
			out.println("</body>");
			out.println("</html>");
		} catch (IOException e) {
			e.printStackTrace();
		}
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
