package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

import controller.Controlador;
import model.Viaje;

@ManagedBean(name = "beanListarViaje")
@SessionScoped
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
	@ManagedProperty(value = "#{beanMisReservas}")
	private BeanMisReservas beanMisReservas;

	private void setFirstElement() {
		ArrayList<Viaje> viajesList = new ArrayList<Viaje>(viajes);
		viajeSeleccionado = (viajesList.isEmpty() ? null : viajesList.get(0));
	}

	@PostConstruct
	public void init() {
		System.out.println("BeanListarViaje.init()");
		viajes = Controlador.getInstance().listarViajes(filtroPendiente, filtroRealizado, filtroPropio,
				filtroOrdenFecha, filtroOrdenCiudad);
		System.out.println(filtroPropio);
		// Ponemos el primer elemento de la colección como el selecionado por defecto
		setFirstElement();
	}

	public Collection<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(Collection<Viaje> viajes) {
		this.viajes = viajes;
	}

	public Viaje getViajeSelected() {
		return viajeSeleccionado;
	}

	public void setViajeSelected(Viaje viajeSelected) {
		System.out.println("BeanListarViaje.setViajeSelected()");
		if (viajeSelected != null) {
			System.out.println(viajeSelected.getId());
		}
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
		System.out.println("BeanListarViaje.setViajeSeleccionado()");
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

	public BeanMisReservas getBeanMisReservas() {
		return beanMisReservas;
	}

	public void setBeanMisReservas(BeanMisReservas beanMisReservas) {
		this.beanMisReservas = beanMisReservas;
	}

	public String contratarSeleccionado() {
		if (viajeSeleccionado == null) {
			beanMessages.error("Seleccione un viaje");
			return "faceletsListarViajes";
		}
		if (Controlador.getInstance().reservarViaje(viajeSeleccionado.getId(), this.comentario) == null) {
			beanMessages.error("No se puede contratar el viaje seleccionado");
		} else {
			beanMessages.info("Exito", "Viaje contratado");
			beanMisReservas.reload();
		}
		return "faceletsListarViajes";
	}
	public void reload(){
		
		viajes = Controlador.getInstance().listarViajes(filtroPendiente, filtroRealizado, filtroPropio,
				filtroOrdenFecha, filtroOrdenCiudad);
	
	}
	public String filtrar() {
		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent("form:dataTable");

		viajes = Controlador.getInstance().listarViajes(filtroPendiente, filtroRealizado, filtroPropio,
				filtroOrdenFecha, filtroOrdenCiudad);
		dataTable.updateValue(viajes);
		setFirstElement();

		return "faceletsListarViajes";
	}

}