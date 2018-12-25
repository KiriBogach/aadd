package beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/* Este bean se utiliza para saber si el botón "Publicar Viajes" y "Mis viajes"
 * de la sección nav debe estar activo o no"
 */

@ManagedBean(name = "beanComprobarBoton")
@RequestScoped
public class BeanComprobarBoton implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean botonActivo;

	@ManagedProperty(value = "#{beanController}")
	private BeanController beanController;

	public BeanController getBeanController() {
		return beanController;
	}

	public void setBeanController(BeanController beanController) {
		this.beanController = beanController;
	}

	@PostConstruct
	public void init() {
		this.setBotonActivo(beanController.getControlador().usuarioLogeadoIsConductor());
	}

	public boolean isBotonActivo() {
		return botonActivo;
	}

	public void setBotonActivo(boolean botonActivo) {
		this.botonActivo = botonActivo;
	}
}
