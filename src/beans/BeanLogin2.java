package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import controller.Controlador;

@ManagedBean(name = "beanLogin2")
@SessionScoped
public class BeanLogin2 {
	private String usuario;
	private String password;
	private boolean conductor;

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

	public boolean isConductor() {
		return conductor;
	}

	public void setConductor(boolean conductor) {
		this.conductor = conductor;
	}

	public String login() {
		
		Controlador controlador = Controlador.getInstance();
		try {
			if (controlador.loginUsuario(usuario, password)) {
				conductor = controlador.usuarioLogeadoIsConductor();
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

	public void comprobarTieneCoche(ActionEvent event) {
		conductor = Controlador.getInstance().usuarioLogeadoIsConductor();
	}

}