package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;

/**
 * Servlet implementation class ServletRegistro
 */
@WebServlet("/ServletRegistro")
public class ServletRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TOP = "<!DOCTYPE html>\r\n" + "\r\n" + "<html>\r\n" + "\r\n" + "<head>\r\n" + "\r\n"
			+ "	<meta charset=\"UTF-8\">\r\n" + "	<title>LOGIN FAIL</title>\r\n"
			+ "	<link rel='icon' type='image/png' href=\"icon/favicon.png\" />\r\n"
			+ "	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n" + "\r\n"
			+ "	<!-- Bootstrap CSS -->\r\n"
			+ "	<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\">\r\n"
			+ "	<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.3.1/css/all.css\">\r\n"
			+ "	<link href=\"css/main.css\" rel=\"stylesheet\">\r\n" + "\r\n" + "</head>\r\n" + "\r\n" + "\r\n"
			+ "<body>\r\n" + "\r\n" + "	<div class=\"container\" style=\"padding: 40px; text-align: center\">\r\n"
			+ "		<div class=\"card card-container\" style=\"padding: 40px\"> <h1> ";
	private static final String BOTTOM = "</div>\r\n" + " </h1>	</div>\r\n" + "\r\n"
			+ "	<!-- jQuery first, then Popper.js, then Bootstrap JS -->\r\n"
			+ "	<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n"
			+ "	<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\"></script>\r\n"
			+ "	<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js\"></script>\r\n"
			+ "\r\n" + "	<!-- Optional JavaScript -->\r\n" + "	<script src=\"js/main.js\"></script>\r\n" + "\r\n"
			+ "</body>\r\n" + "\r\n" + "</html>";

	public ServletRegistro() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/* No cacheamos la respuesta */
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		// Instanciamos el Bean
		Usuario u = new Usuario();
		// Obtenemos los parámetros de la llamada
		// No hace falta hacer conversiones de tipos porque todas
		// las propiedades del bean son cadenas.
		u.setUsuario(request.getParameter("usuario"));
		u.setPassword(request.getParameter("password"));
		u.setEmail(request.getParameter("email"));
		u.setTelefono(request.getParameter("telefono"));

		// Recupera el contexto de la aplicación
		ServletContext app = getServletConfig().getServletContext();
		// Intenta localizar la tabla de usuarios
		@SuppressWarnings("unchecked")
		HashMap<String, Usuario> usuarios = (HashMap<String, Usuario>) app.getAttribute("usuarios");
		// Si no existe, la crea
		if (usuarios == null) {
			usuarios = new HashMap<String, Usuario>();
			app.setAttribute("usuarios", usuarios);
		}

		boolean error = false;
		// Intenta guardar un usuario. Si existe el identificador, devuelve un error
		if (usuarios.get(u.getUsuario()) != null) {
			// response.sendError(500, "Identificador de usuario duplicado");
			// return;
			error = true;
		} else {
			usuarios.put(u.getUsuario(), u);
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println(TOP);

		String referer = request.getHeader("referer");
		if (!error) {
			out.println("Registro correcto");
			response.setHeader("refresh", "3; URL=index.html");
		} else {
			out.println("Error: Usuario duplicado");
			response.setHeader("refresh", "3; URL=" + referer);
		}

		out.println(BOTTOM);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
