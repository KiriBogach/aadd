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
public class BeanRegistrarViaje implements Serializable{

	private static final long serialVersionUID = 1L;
	private String plazas;
	private String precio;
	public int idViaje;

	@ManagedProperty(value = "#{beanRegistrarParadaViaje.origen}")
	private BeanRegistrarParadaViaje origen;

	@ManagedProperty(value = "#{beanRegistrarParadaViaje.destino}")
	private BeanRegistrarParadaViaje destino;

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

	public BeanRegistrarParadaViaje getOrigen() {
		return origen;
	}

	public void setOrigen(BeanRegistrarParadaViaje origen) {
		this.origen = origen;
	}

	public BeanRegistrarParadaViaje getDestino() {
		return destino;
	}

	public void setDestino(BeanRegistrarParadaViaje destino) {
		this.destino = destino;
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
			if (paradaOrigen != null && paradaDestino != null)
				return "faceletsWelcome";
		}
		return "faceletsFallo";
	}

}
