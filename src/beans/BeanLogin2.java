package beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
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
		Controlador controlador = Controlador.getInstance();
		try {
			if (controlador.loginUsuario(usuario, password)) {
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

	public String logout() {
		Controlador.getInstance().logout();
		return "faceletsLogin";
	}



}