package beans;

import controller.Controlador;

public class BeanLogin {
	private String usuario;
	private String password;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return password;
	}

	public void setClave(String clave) {
		this.password = clave;
	}

	public String validacion() {
		if (Controlador.getInstance().loginUsuario(usuario, password)) {
			return "success";
		} else {
			// para resetear la vista
			setUsuario("");
			setClave("");
			return "failure";
		}
	}
}
