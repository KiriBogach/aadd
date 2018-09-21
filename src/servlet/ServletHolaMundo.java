package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletHolaMundo
 */
@WebServlet(urlPatterns = { "/HolaMundo" }, // El mapping de URL's
		initParams = { @WebInitParam(name = "autor", value = "Kiri", description = "Parametro de inicializacion") })
public class ServletHolaMundo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletHolaMundo() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("ServletHolaMundo.init()");
	}

	@Override
	public void destroy() {
		System.out.println("ServletHolaMundo.destroy()");
		super.destroy();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ServletHolaMundo.service()");
		super.service(req, resp);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("ServletHolaMundo.doGet()");

		// response.sendError(HttpServletResponse.SC_FORBIDDEN, "Recurso Inalcanzable"); // error 404...
		// response.sendRedirect(request.getHeader("referer")); // redirect

		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		String referer = request.getHeader("referer"); // de donde viene la petición

		HttpSession sesion = request.getSession();
		Integer contador = (Integer) sesion.getAttribute("contador");

		if (contador == null) {
			sesion.setAttribute("contador", 0);
		} else {
			sesion.setAttribute("contador", ++contador);
		}

		//response.setHeader("refresh", "3;" + referer); // vuelvo a de donde viene la petición en 10 segundos
		response.getWriter()
				.append("Usuario: " + usuario).append("\nPassword: " + password)
				.append("\nAutor: " + getInitParameter("autor")).append("\nReferer: " + referer)
				.append("\nContador: " + contador);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("ServletHolaMundo.doPost()");

		doGet(request, response);
	}

}
