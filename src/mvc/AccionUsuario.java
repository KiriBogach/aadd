package mvc;

import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controlador;
import model.Usuario;

public class AccionUsuario implements Accion {

	@Override
	public String ejecutar(HttpServletRequest peticion, HttpServletResponse respuesta, ServletContext aplicacion) {
		Collection<Usuario> usuarios;
		usuarios = Controlador.getInstance().getAllUsuarios();
		HttpSession sesion = peticion.getSession();
		sesion.setAttribute("usuarios", usuarios);
		// return FrontController.HOME;
		return "listadoUsuarios.jsp";

	}

}
