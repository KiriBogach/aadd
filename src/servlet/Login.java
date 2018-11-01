package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controlador;

/**
 * Servlet implementation class ServletHolaMundo
 */
@WebServlet(urlPatterns = { "/Login" }, // El mapping de URL's. Puedo poner asyncSupported=true
		initParams = { @WebInitParam(name = "autor", value = "Kiri", description = "Parametro de inicializacion") })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		// System.out.println("ServletHolaMundo.init()");
	}

	@Override
	public void destroy() {
		// System.out.println("ServletHolaMundo.destroy()");
		super.destroy();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// System.out.println("ServletHolaMundo.service()");
		super.service(req, resp);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// System.out.println("ServletHolaMundo.doGet()");

		PrintWriter out = response.getWriter();

		// Comprobamos si el navegador nos env�a una cookie con nombre 'aadd'
		Cookie[] cookies = request.getCookies();
		Cookie cookieAADD = null;
		if (cookies != null) { // Por si no tenemos ninguna cookie a�n
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("aadd"))
					cookieAADD = cookies[i];
			}
			// Damos la salida en texto normal
			if (cookieAADD != null) {
				out.println("Identificaci�n correcta utilizando cookie: " + cookieAADD.getValue());
				return;
			}
		}

		// response.sendError(HttpServletResponse.SC_FORBIDDEN, "Recurso Inalcanzable");
		// // error 404...
		// response.sendRedirect(request.getHeader("referer")); // redirect

		/*
		 * boolean identificado = false; // Recuperamos el objeto sesi�n HttpSession
		 * sesion = request.getSession(true); // Obtenemos el n�mero de identificaciones
		 * fallidas Integer numFallos = (Integer) sesion.getAttribute("fallos"); if
		 * (numFallos == null) { // No se ha identificado numFallos = new Integer(0);
		 * sesion.setAttribute("fallos", numFallos); } else if (numFallos.intValue() ==
		 * 3) { out.println("N�mero fallos identificaci�n excedido"); return; }
		 */

		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		String referer = request.getHeader("referer"); // de donde viene la petici�n

		Controlador controlador = Controlador.getInstance();
		// controlador.test();

		if (controlador.loginUsuario(usuario, password)) {
			out.println("<b><p>Identificaci�n correcta</b></p>");
		} else {
			out.println("<b><p>Error de identificaci�n</b></p>");
			response.setHeader("refresh", "3;" + referer);
		}

		/*
		 * // Obtenemos el objeto ServletContext ServletContext app =
		 * getServletConfig().getServletContext(); // Accedemos a la referencia de la
		 * tabla hash HashMap usuarios = (HashMap) app.getAttribute("usuarios"); Usuario
		 * u = null; if (usuarios == null) // No hay usuarios registrados identificado =
		 * false; else { // Obtenemos el objeto cliente por usuario u = (Usuario)
		 * usuarios.get(usuario); if (u == null) // No est� registrado identificado =
		 * false; else { // Comprobamos la clave if (u.getPassword().equals(password))
		 * identificado = true; else identificado = false; } }
		 * 
		 * // Establecemos el tipo MIME de la respuesta
		 * response.setContentType("text/html"); // Escribimos la respuesta // Cabecera
		 * HTML out.println("<html> <head> <title> Login </title> </head> <body>"); //
		 * Nuestros datos if (identificado) {
		 * out.println("<b><p>Identificaci�n correcta</b></p>"); } else {
		 * out.println("<b><p>Error de identificaci�n</b></p>");
		 * response.setHeader("refresh", "3;" + referer); } // Cierre de la p�gina
		 * out.println("</body>"); out.println("</html>"); // Actualizamos la sesi�n if
		 * (identificado) { // Guardamos el objeto usuario en la sesi�n
		 * sesion.setAttribute("usuario", u); // Reseteamos n�mero fallos
		 * sesion.setAttribute("fallos", new Integer(0)); } else { // Incrementamos el
		 * n�mero de fallos int fallos = ((Integer)
		 * sesion.getAttribute("fallos")).intValue(); sesion.setAttribute("fallos", new
		 * Integer(++fallos)); }
		 * 
		 * if (identificado) { // Enviamos una cookie al navegador web Cookie cookie =
		 * new Cookie("aadd", u.getUsuario()); cookie.setMaxAge(60 * 60 * 24 * 7); //
		 * C�lculo segundos semana response.addCookie(cookie); }
		 */

		/*
		 * if (usuario.equals("admin") && password.equals("admin")) {
		 * response.sendRedirect("welcome.html"); // redirect } else {
		 * response.sendRedirect("badlogin.html"); }
		 */

		/*
		 * 
		 * HttpSession session = request.getSession(); Integer contador = (Integer)
		 * session.getAttribute("contador");
		 * 
		 * if (contador == null) { sesion.setAttribute("contador", 0); } else {
		 * sesion.setAttribute("contador", ++contador); }
		 * 
		 * // Si no tienes las cookies activadas te mete el jsessionid en las url String
		 * urlEncoded = response.encodeURL("index.html"); //
		 * index.html;jsessionid=1E99A60C4837E927C479F4805CC44D0A
		 * 
		 * // Ojo, la primera llamada que no tenemos cookies tambi�n met� el jsessionid
		 * if (urlEncoded.equals("index.html")) { // Las cookies est�n activadas } else
		 * { // Las cookies est�n desactivadas }
		 * 
		 * // response.setHeader("refresh", "3;" + referer); // vuelvo a de donde viene
		 * la // petici�n en 10 segundos response.getWriter().append("Usuario: " +
		 * usuario).append("\nPassword: " + password).append("\nAutor: " +
		 * 
		 * getInitParameter("autor")).append("\nReferer: " +
		 * referer).append("\nContador: " + contador) .append("\nurlEncoded: " +
		 * urlEncoded);
		 */

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// System.out.println("ServletHolaMundo.doPost()");

		doGet(request, response);
	}

}
