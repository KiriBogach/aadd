package beans;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controller.Controlador;
import model.Viaje;

@ManagedBean(name = "beanViaje")
@SessionScoped
public class BeanViaje implements Serializable {

	private static final long serialVersionUID = 1L;
	private Collection<Viaje> viajes;
	private String comentario;
	private int idViajeSeleccionado;

	public BeanViaje() {
		System.out.println("BeanViaje.BeanViaje()");
		viajes = Controlador.getInstance().listarViajes();
	}

	public Collection<Viaje> getViajes() {
		viajes = Controlador.getInstance().listarViajes();
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
		//Controlador.getInstance().reservarViaje(idViajeSeleccionado, this.comentario);
		//return "faceletesWelcome";
	}

}
