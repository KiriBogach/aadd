package beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "beanMessages")
@SessionScoped
public class BeanMessages implements Serializable {

	private static final long serialVersionUID = 1L;

	public void info(String cabecera, String mensaje) {

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, cabecera, mensaje));
	}

	public void info(String mensaje) {
		info("INFO", mensaje);
	}

	public void infoCabecera(String cabecera) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, cabecera, ""));
	}

	public void warn(String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", mensaje));
	}

	public void error(String mensaje) {

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", mensaje));
	}
	public void errorCabecera(String cabecera) {

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, cabecera, ""));
	}

	public void fatal(String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", mensaje));
	}

}
