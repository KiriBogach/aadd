package beans;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import controller.Controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "beanLogin")
@SessionScoped
public class BeanLogin implements Serializable {

	private static final long serialVersionUID = 1L;
	private String usuario;
	private String password;

	@ManagedProperty(value = "#{beanMessages}")
	private BeanMessages beanMessages;

	@ManagedProperty(value = "#{beanValoraciones}")
	private BeanValoraciones beanValoraciones;

	@ManagedProperty(value = "#{beanController}")
	private BeanController beanController;

	public BeanController getBeanController() {
		return beanController;
	}

	public void setBeanController(BeanController beanController) {
		this.beanController = beanController;
	}

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

	public BeanValoraciones getBeanValoraciones() {
		return beanValoraciones;
	}

	public void setBeanValoraciones(BeanValoraciones beanValoraciones) {
		this.beanValoraciones = beanValoraciones;
	}

	public String login() {
		try {
			Controlador controlador = beanController.getControlador();
			if (controlador.loginUsuario(usuario, password)) {
				beanValoraciones.crearOyenteBuzonSugerencias(controlador);
				beanValoraciones.suscripciones(usuario);
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
		if (beanController.getControlador().getUsuarioLogeado() == null) {
			redirectToLogin();
		}
	}

	public String logout() {
		beanValoraciones.close();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		System.out.println("BeanLogin.logout()");
		beanController.getControlador().logout();
		return "faceletsLogin.xhtml";
	}

}