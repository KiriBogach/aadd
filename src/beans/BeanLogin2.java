package beans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controller.Controlador;

@ManagedBean(name = "beanLogin2")
@SessionScoped
public class BeanLogin2 implements Serializable {

	private static final long serialVersionUID = 1L;
	private String usuario;
	private String password;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		try {
			if (Controlador.getInstance().loginUsuario(usuario, password)) {
				return "faceletsWelcome";
			} else {
				setPassword(new String());
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o clave incorrecto."));
				return "faceletsLogin";
			}
		} catch (Exception e) {
			setPassword(new String());
			return "faceletsFallo";
		}
	}

	private void redirectToLogin() {
		try {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(ec.getRequestContextPath() + "/faceletsLogin.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void isUserLogged() {
		System.out.println("Usuario logeado = " + Controlador.getInstance().getUsuarioLogeado() != null);
		if (Controlador.getInstance().getUsuarioLogeado() == null) {
			redirectToLogin();
		}
	}
	
	public String logout() {
		System.out.println("BeanLogin2.logout()");
		Controlador.getInstance().logout();
		return "faceletsRegistro.xhtml";
    }

}