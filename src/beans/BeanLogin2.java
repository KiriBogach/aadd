package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controller.Controlador;

@ManagedBean(name = "beanLogin2")
@SessionScoped
public class BeanLogin2 {
	private String usuario;
	private String password;

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setPassword(String clave) {
		this.password = clave;
	}

	public String getPassword() {
		return password;
	}

	public String login() {
		try {
			if (Controlador.getInstance().loginUsuario(usuario, password)) {
				return "faceletsWelcome";
			} else {
				setPassword(new String());
				return "faceletsFallo";
			}
		} catch (Exception e) {
			setPassword(new String());
			return "faceletsFallo";
		}
	}
}