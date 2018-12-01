package mvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FactoriaDAO;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.ctrl")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected static final String HOME = "welcome.html";
	protected static final String LOGIN = "login.html";
	protected static final String BAD_LOGIN = "badlogin.html";
	protected static final String JSP = "index.jsp";

	// Redefinici�n m�todo init
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// Obtiene una factoria DAO y la almacena en la aplicaci�n
		try {
			FactoriaDAO daoFactoria = FactoriaDAO.getInstancia();
			ServletContext aplicacion = config.getServletContext();
			aplicacion.setAttribute("DAOFactoria", daoFactoria);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest peticion, HttpServletResponse respuesta)
			throws ServletException, java.io.IOException {
		procesa(peticion, respuesta);
	}

	public void doPost(HttpServletRequest peticion, HttpServletResponse respuesta)
			throws ServletException, java.io.IOException {
		procesa(peticion, respuesta);
	}

	/*
	 * CON METAPROGRAMACION LA URL TIENE QUE SER ID�NTICA, CON LA SOLUCI�N DEL
	 * PROFESOR NO!
	 */

	// M�todo de procesamiento
	protected void procesa(HttpServletRequest peticion, HttpServletResponse respuesta)
			throws ServletException, java.io.IOException {
		/*
		 * // Obtiene la ruta f�sica de la aplicaci�n para que el // objeto
		 * PeticionHelper pueda acceder a sus ficheros de // propiedades String
		 * dirAplicacion = getServletConfig().getServletContext().getRealPath("/");
		 */
		// Utiliza una clase Helper para analizar la acci�n a realizar
		PeticionHelper peticionHelper = new PeticionHelper(peticion);
		// Acciones comunes de la aplicaci�n
		// 1. Si rompe el flujo de navegaci�n, se env�a
		// a la p�gina de inicio
		if (peticionHelper.errorNavegacion()) {
			visualizar(HOME, peticion, respuesta);
			return;
		}
		// 2. Si accede a un recurso para el que necesita estar validado,
		// se env�a a la p�gina de login
		if (peticionHelper.necesitaValidacion()) {
			// Convendria guardar la URI solicita en la sesi�n para que
			// el servlet Login navegara hacia ella despu�s de la
			// validaci�n ...
			visualizar(LOGIN, peticion, respuesta);
			return;
		}
		// 3. Cualquier otra acci�n com�n
		// peticionHelper.log() ...
		// Obtiene la acci�n a ejecutar asociada a la petici�n
		Accion acc = peticionHelper.getAccion();
		// Recupera el objeto aplicaci�n para la acci�n
		ServletContext aplicacion = getServletConfig().getServletContext();
		// Ejecuta la acci�n y obtiene la vista
		String vista = acc.ejecutar(peticion, respuesta, aplicacion);
		// Visualiza el resultado
		visualizar(vista, peticion, respuesta);
	}

	protected void visualizar(String vista, HttpServletRequest peticion, HttpServletResponse respuesta)
			throws ServletException, java.io.IOException {
		// Transforma la URL en relativa al contexto
		// Utilizamos URLs relativas al contexto para ilustrar su uso
		// Reenv�a la petici�n a la vista
		RequestDispatcher rd = peticion.getRequestDispatcher(vista);
		rd.forward(peticion, respuesta);
	}
}
