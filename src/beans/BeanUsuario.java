package beans;

import java.io.Serializable;

import controller.Controlador;

public class BeanUsuario implements Serializable {

	public boolean isConductor() {
		return Controlador.getInstance().usuarioLogeadoIsConductor();
	}

}
