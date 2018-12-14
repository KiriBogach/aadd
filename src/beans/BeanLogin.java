package beans;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controller.Controlador;

@ManagedBean(name = "beanLogin")
@SessionScoped
public class BeanLogin implements Serializable {

	private static final long serialVersionUID = 1L;
	private String usuario;
	private String password;

	@ManagedProperty(value = "#{beanMessages}")
	private BeanMessages beanMessages;

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

	public BeanMessages getBeanMessages() {
		return beanMessages;
	}

	public void setBeanMessages(BeanMessages beanMessages) {
		this.beanMessages = beanMessages;
	}

	public String login() {
		try {
			if (Controlador.getInstance().loginUsuario(usuario, password)) {
				return "faceletsWelcome";
			} else {

				setPassword(new String());
				setUsuario(new String());
				 
				beanMessages.errorCabecera("Usuario o clave incorrecto.");
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
		if (Controlador.getInstance().getUsuarioLogeado() == null) {
			redirectToLogin();
		}
	}

	public String logout() {
		System.out.println("BeanLogin.logout()");
		Controlador.getInstance().logout();
		return "faceletsLogin.xhtml";
	}

}