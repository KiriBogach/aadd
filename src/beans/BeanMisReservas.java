package beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controller.Controlador;
import model.*;

@ManagedBean(name = "beanMisReservas")
@SessionScoped
public class BeanMisReservas implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Reserva> reservas;

	private Reserva reservaSeleccionada;

	private String comentario;

	private int puntuacion;

	@ManagedProperty(value = "#{beanMessages}")
	private BeanMessages beanMessages;

	@PostConstruct
	public void init() {
		reservas = (List<Reserva>) Controlador.getInstance().getReservasUsuarioLogeado();
	}

	public void reload() {
		reservas = (List<Reserva>) Controlador.getInstance().getReservasUsuarioLogeado();
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Reserva getReservaSeleccionada() {
		return reservaSeleccionada;
	}

	public void setReservaSeleccionada(Reserva reservaSeleccionada) {
		this.reservaSeleccionada = reservaSeleccionada;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public BeanMessages getBeanMessages() {
		return beanMessages;
	}

	public void setBeanMessages(BeanMessages beanMessages) {
		this.beanMessages = beanMessages;
	}

	public String valorar() {
		System.out.println("BeanMisReservas.valorar()");
		Viaje viaje = this.reservaSeleccionada.getViaje();
		if (Controlador.getInstance().valorarViajePasajero(viaje.getId(), viaje.getCoche().getUsuario().getUsuario(),
				comentario, puntuacion)) {
			System.out.println("EXITO");
			beanMessages.info("La valoracion ha sido realizada con exito");
			reservas = (List<Reserva>) Controlador.getInstance().getReservasUsuarioLogeado();
		} else {
			System.out.println("ERROR");
			beanMessages.error("La valoracion no se ha podido realizar");
		}

		return "faceletsMisReservas";
	}

}
