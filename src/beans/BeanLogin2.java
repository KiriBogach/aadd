package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controller.Controlador;

@ManagedBean(name = "beanLogin2")
@SessionScoped
public class BeanLogin2 {
	private String usuario;
	private String password;
	private boolean isConductor;
	private boolean disablePublicarViaje;

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
	public boolean isConductor() {
		return isConductor;
	}

	public void setConductor(boolean isConductor) {
		this.isConductor = isConductor;
	}
	
	public boolean isDisablePublicarViaje() {
		return disablePublicarViaje;
	}

	public void setDisablePublicarViaje(boolean disablePublicarViaje) {
		this.disablePublicarViaje = disablePublicarViaje;
	}
	

	public String login() {
		try {
			if (Controlador.getInstance().loginUsuario(usuario, password)) {
				isConductor=Controlador.getInstance().usuarioLogeadoIsConductor();
				disablePublicarViaje=!isConductor;
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