package beans;

import controller.Controlador;

public class BeanUsuario {

	public boolean isConductor() {
		return Controlador.getInstance().usuarioLogeadoIsConductor();
	}

}
