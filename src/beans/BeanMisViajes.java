package beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import controller.Controlador;
import model.*;

@ManagedBean(name = "beanMisViajes")
@ViewScoped
public class BeanMisViajes implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Viaje> viajesPropios;

	private Viaje viajeSeleccionado;

	private Reserva reservaSeleccionada;

	@ManagedProperty(value = "#{beanMessages}")
	private BeanMessages beanMessages;

	@PostConstruct
	public void init() {
		viajesPropios = new LinkedList<>();
	}

	public List<Viaje> getViajesPropios() {
		viajesPropios = (List<Viaje>) Controlador.getInstance().listarViajes(false, false, true, false, false);
		return viajesPropios;
	}

	public void setViajesPropios(List<Viaje> viajesPropios) {
		this.viajesPropios = viajesPropios;
	}

	public Viaje getViajeSeleccionado() {
		return viajeSeleccionado;
	}

	public void setViajeSeleccionado(Viaje viajeSeleccionado) {
		this.viajeSeleccionado = viajeSeleccionado;
	}

	public Reserva getReservaSeleccionada() {
		return reservaSeleccionada;
	}

	public void setReservaSeleccionada(Reserva reservaSeleccionada) {
		this.reservaSeleccionada = reservaSeleccionada;
	}

	public BeanMessages getBeanMessages() {
		return beanMessages;
	}

	public void setBeanMessages(BeanMessages beanMessages) {
		this.beanMessages = beanMessages;
	}

	public String aceptarReserva() {
		System.out.println("BeanMisViajes.aceptarReserva()");
		Viaje aceptada = Controlador.getInstance().aceptarViaje(viajeSeleccionado.getId(),
				reservaSeleccionada.getUsuario().getUsuario());
		if (aceptada != null) {
			beanMessages.info("La reserva ha sido aceptada con exito");
		}
		else {
			beanMessages.info("La reserva no se ha podido aceptar");
		}
		return "faceletsMisViajes";

	}

	public String rechazarReserva() {
		System.out.println("BeanMisViajes.rechazarReserva()");
		Viaje rechazada = Controlador.getInstance().rechazarViaje(viajeSeleccionado.getId(),
				reservaSeleccionada.getUsuario().getUsuario());
		if (rechazada != null) {
			beanMessages.info("La reserva ha sido rechazada con exito");
		}
		else {
			beanMessages.info("La reserva no se ha podido rechazar");
		}
		return "faceletsMisViajes";
	}

}
