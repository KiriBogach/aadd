package beans;


import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import controller.Controlador;

/*Este bean se utiliza para saber si el botón "Publicar Viajes"
 * de la sección nav debe estar activo o no"*/
@ManagedBean(name = "beanRequest")
@RequestScoped
public class BeanRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean botonActivo;
	
	public BeanRequest() {
		//System.out.println("Se crea BeanRequest");
		this.setBotonActivo(Controlador.getInstance().usuarioLogeadoIsConductor());
	}

	public boolean isBotonActivo() {
		return botonActivo;
	}

	public void setBotonActivo(boolean botonActivo) {
		this.botonActivo = botonActivo;
	}
}
