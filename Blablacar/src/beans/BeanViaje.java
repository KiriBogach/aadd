package beans;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import model.Viaje;

@ManagedBean(name = "beanViaje")
@SessionScoped
public class BeanViaje implements Serializable {

	private static final long serialVersionUID = 1L;
	private Collection<Viaje> viajes;
	private String comentario;
	private int idViajeSeleccionado;

	@ManagedProperty(value = "#{beanController}")
	private BeanController beanController;

	public BeanController getBeanController() {
		return beanController;
	}

	public void setBeanController(BeanController beanController) {
		this.beanController = beanController;
	}

	public BeanViaje() {
		System.out.println("BeanViaje.BeanViaje()");
		viajes = beanController.getControlador().listarViajes();
	}

	public Collection<Viaje> getViajes() {
		viajes = beanController.getControlador().listarViajes();
		return viajes;
	}

	public void setViajes(Collection<Viaje> viajes) {
		this.viajes = viajes;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getIdViajeSeleccionado() {
		return idViajeSeleccionado;
	}

	public void setIdViajeSeleccionado(int idViajeSeleccionado) {
		this.idViajeSeleccionado = idViajeSeleccionado;
	}

	public void reservar() {
		System.out.println("BeanViaje.reservar()");
		// beanController.getControlador().reservarViaje(idViajeSeleccionado,
		// this.comentario);
		// return "faceletesWelcome";
	}

}
