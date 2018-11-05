package mvc;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controlador;

public class AccionLogin implements Accion {

	@Override
	public String ejecutar(HttpServletRequest peticion, HttpServletResponse respuesta, ServletContext aplicacion) {
		String usuario = peticion.getParameter("usuario");
		String password = peticion.getParameter("password");
		if (Controlador.getInstance().loginUsuario(usuario, password)) {
			HttpSession sesion = peticion.getSession();
			sesion.setAttribute("usuarioLogeado", (Controlador.getInstance().findUsuario(usuario)));
			// return FrontController.HOME;
			return FrontController.JSP;
		}
		return FrontController.BAD_LOGIN;
	}

}
