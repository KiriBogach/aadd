package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import controller.Controlador;

@ManagedBean(name = "beanRequest")
@RequestScoped
public class BeanRequest {
	private boolean botonActivo;
	
	public BeanRequest() {
		System.out.println("Se crea BeanRequest");
		this.setBotonActivo(Controlador.getInstance().usuarioLogeadoIsConductor());
	}

	public boolean isBotonActivo() {
		return botonActivo;
	}

	public void setBotonActivo(boolean botonActivo) {
		this.botonActivo = botonActivo;
	}
}
