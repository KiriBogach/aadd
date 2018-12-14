package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

import controller.Controlador;
import model.Viaje;

@ManagedBean(name = "beanListarViaje")
@ViewScoped
public class BeanListarViaje implements Serializable {
	private static final long serialVersionUID = 1L;
	private Collection<Viaje> viajes;
	private Viaje viajeSeleccionado;
	private String comentario;
	private boolean filtroPendiente;
	private boolean filtroRealizado;
	private boolean filtroPropio;
	private boolean filtroOrdenFecha;
	private boolean filtroOrdenCiudad;
	@ManagedProperty(value = "#{beanMessages}")
	private BeanMessages beanMessages;

	private void setFirstElement() {
		ArrayList<Viaje> viajesList = new ArrayList<Viaje>(viajes);
		viajeSeleccionado = (viajesList.isEmpty() ? null : viajesList.get(0));
	}

	@PostConstruct
	public void init() {
		viajes = Controlador.getInstance().listarViajes();
		// Ponemos el primer elemento de la colección como el selecionado por
		// defecto
		setFirstElement();
	}

	public Collection<Viaje> getViajes() {
		viajes = Controlador.getInstance().listarViajes();
		return viajes;
	}

	public void setViajes(Collection<Viaje> viajes) {
		this.viajes = viajes;
	}

	public Viaje getViajeSelected() {
		return viajeSeleccionado;
	}

	public void setViajeSelected(Viaje viajeSelected) {
		this.viajeSeleccionado = viajeSelected;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Viaje getViajeSeleccionado() {
		return viajeSeleccionado;
	}

	public void setViajeSeleccionado(Viaje viajeSeleccionado) {
		this.viajeSeleccionado = viajeSeleccionado;
	}

	public boolean isFiltroPendiente() {
		return filtroPendiente;
	}

	public void setFiltroPendiente(boolean filtroPendientes) {
		this.filtroPendiente = filtroPendientes;
	}

	public boolean isFiltroRealizado() {
		return filtroRealizado;
	}

	public void setFiltroRealizado(boolean filtroRealizados) {
		this.filtroRealizado = filtroRealizados;
	}

	public boolean isFiltroPropio() {
		return filtroPropio;
	}

	public void setFiltroPropio(boolean filtroPropio) {
		this.filtroPropio = filtroPropio;
	}

	public boolean isFiltroOrdenFecha() {
		return filtroOrdenFecha;
	}

	public void setFiltroOrdenFecha(boolean filtroOrdenFecha) {
		this.filtroOrdenFecha = filtroOrdenFecha;
	}

	public boolean isFiltroOrdenCiudad() {
		return filtroOrdenCiudad;
	}

	public void setFiltroOrdenCiudad(boolean filtroOrdenCiudad) {
		this.filtroOrdenCiudad = filtroOrdenCiudad;
	}

	public BeanMessages getBeanMessages() {
		return beanMessages;
	}

	public void setBeanMessages(BeanMessages beanMessages) {
		this.beanMessages = beanMessages;
	}

	public String contratarSeleccionado() {
		if (viajeSeleccionado == null) {
			
			beanMessages.error("Seleccione un viaje");
			return "faceletsListarViajes";
		}
		if (Controlador.getInstance().reservarViaje(viajeSeleccionado.getId(), this.comentario) == null) {
			
			beanMessages.error("No se puede contratar el viaje seleccionado");
		} else {
			
			beanMessages.info("Exito","Viaje contratado");
		}
		return "faceletsListarViajes";
	}

	public void filtrar() {
		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent("form:dataTable");

		viajes = Controlador.getInstance().listarViajes(filtroPendiente, filtroRealizado, filtroPropio,
				filtroOrdenFecha, filtroOrdenCiudad);
		setFirstElement();
		dataTable.updateValue(viajes);
	}

}