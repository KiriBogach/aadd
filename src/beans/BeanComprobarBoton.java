package beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import controller.Controlador;

/* Este bean se utiliza para saber si el botón "Publicar Viajes" y "Mis viajes"
 * de la sección nav debe estar activo o no"
 */

@ManagedBean(name = "beanComprobarBoton")
@RequestScoped
public class BeanComprobarBoton implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean botonActivo;

	@PostConstruct
	public void init() {
		this.setBotonActivo(Controlador.getInstance().usuarioLogeadoIsConductor());
	}

	public boolean isBotonActivo() {
		return botonActivo;
	}

	public void setBotonActivo(boolean botonActivo) {
		this.botonActivo = botonActivo;
	}
}
