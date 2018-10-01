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

	public ServletRegistro() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/* No cacheamos la respuesta */
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader ("Expires", 0);

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
			//response.sendError(500, "Identificador de usuario duplicado");
			//return;
			error = true;
		} else {
			usuarios.put(u.getUsuario(), u);
		}

		// Omitimos el código de respuesta. Por defecto OK
		// Establecemos el tipo MIME de la respuesta
		response.setContentType("text/html");
		// Escribimos la respuesta
		PrintWriter out = response.getWriter();
		// Esto siempre hay que hacerlo para un fichero HTML
		out.println("<html>");
		out.println("<head>");
		// Establecemos el título de la página HTML
		out.println("<title>" + "Procesamiento Datos Cliente" + "</title>");
		out.println("</head>");
		// Cuerpo de la página
		out.println("<body>");
		
		// Obtiene la URL precedente
		String referer = request.getHeader("referer");
		// Establece la cabecera de refresco
		response.setHeader("refresh", "3; URL=" + referer);
		
		if (!error) {
			// Nuestros datos
			out.println("<b><p> Datos Cliente Procesados </p></b>");
		} else {
			out.println("<h1> Error: usuario duplicado </h1>");
		}

		// Cierre de la página
		out.println("</body>");
		out.println("</html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
