package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controller.Controlador;
import model.Parada;
import model.Viaje;

@ManagedBean(name = "beanRegistrarViaje")
@SessionScoped
public class BeanRegistrarViaje implements Serializable {

	private static final long serialVersionUID = 1L;
	private String plazas;
	private String precio;
	public int idViaje;

	@ManagedProperty(value = "#{beanRegistrarParada.origen}")
	private BeanRegistrarParada origen;

	@ManagedProperty(value = "#{beanRegistrarParada.destino}")
	private BeanRegistrarParada destino;

	@ManagedProperty(value = "#{beanMessages}")
	private BeanMessages beanMessages;

	@ManagedProperty(value = "#{beanMisViajes}")
	private BeanMisViajes beanMisViajes;

	public String getPlazas() {
		return plazas;
	}

	public void setPlazas(String plazas) {
		this.plazas = plazas;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public int getIdViaje() {
		return idViaje;
	}

	public void setIdViaje(int idViaje) {
		this.idViaje = idViaje;
	}

	public BeanRegistrarParada getOrigen() {
		return origen;
	}

	public void setOrigen(BeanRegistrarParada origen) {
		this.origen = origen;
	}

	public BeanRegistrarParada getDestino() {
		return destino;
	}

	public void setDestino(BeanRegistrarParada destino) {
		this.destino = destino;
	}

	public BeanMessages getBeanMessages() {
		return beanMessages;
	}

	public void setBeanMessages(BeanMessages beanMessages) {
		this.beanMessages = beanMessages;
	}

	public BeanMisViajes getBeanMisViajes() {
		return beanMisViajes;
	}

	public void setBeanMisViajes(BeanMisViajes beanMisViajes) {
		this.beanMisViajes = beanMisViajes;
	}

	/* Método que se encarga del registro del viaje */
	public String registrarViaje() {
		Controlador controlador = Controlador.getInstance();
		int plazasEntero;
		double precioViaje;
		Viaje viaje;
		try {
			plazasEntero = Integer.parseInt(plazas);
			precioViaje = Double.parseDouble(precio);
		} catch (NumberFormatException e) {
			return "faceletsFallo";
		}
		viaje = controlador.registrarViaje(plazasEntero, precioViaje);
		Parada paradaOrigen, paradaDestino;
		if (viaje != null) {
			idViaje = viaje.getId();
			paradaOrigen = origen.registrarParada(idViaje, true);
			paradaDestino = destino.registrarParada(idViaje, false);
			if (paradaOrigen != null && paradaDestino != null) {
				limpiarCampos();
				origen.limpiarCampos();
				destino.limpiarCampos();
				beanMessages.infoCabecera("Viaje registrado con exito");
				beanMisViajes.reload();
				return "faceletsPublicarViaje";
			}
		}
		beanMessages.errorCabecera("No se ha podido registrar el viaje");
		limpiarCampos();
		origen.limpiarCampos();
		destino.limpiarCampos();
		return "faceletsPublicarViaje";
	}

	public void limpiarCampos() {
		plazas = "";
		precio = "";
	}
}
