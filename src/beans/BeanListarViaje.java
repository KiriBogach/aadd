package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import controller.Controlador;
import model.Viaje;

@ManagedBean(name = "beanListarViaje")
@ViewScoped
public class BeanListarViaje implements Serializable {
	private static final long serialVersionUID = 1L;
	private Collection<Viaje> viajes;
	private Viaje viajeSelected;
	private String comentario;

	@PostConstruct
	public void init() {
		viajes = Controlador.getInstance().listarViajes();
		// Ponemos el primer elemento de la colección como el selecionado por defecto
		ArrayList<Viaje> viajesList = (ArrayList<Viaje>) viajes;
		viajeSelected = (viajesList.isEmpty() ? null : viajesList.get(0));
		System.out.println("BeanListarViaje.BeanListarViaje()");
	}

	public Collection<Viaje> getViajes() {
		viajes = Controlador.getInstance().listarViajes();
		return viajes;
	}

	public void setViajes(Collection<Viaje> viajes) {
		this.viajes = viajes;
	}

	public Viaje getViajeSelected() {
		return viajeSelected;
	}

	public void setViajeSelected(Viaje viajeSelected) {
		this.viajeSelected = viajeSelected;
		System.out.println("BeanListarViaje.setViajeSelected()" + viajeSelected.getId());
	}

	public String contratarSeleccionado() {
		if (viajeSelected == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Seleccione un viaje"));
			return "faceletsMisViajes";
		}
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Viaje contratado"));
		Controlador.getInstance().reservarViaje(viajeSelected.getId(), this.comentario);
		System.out.println("contratarSeleccionado() " + viajeSelected.getId());
		return "faceletsMisViajes";
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

}