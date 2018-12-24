package beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controller.Controlador;
import model.*;

@ManagedBean(name = "beanMisViajes")
@SessionScoped
public class BeanMisViajes implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Viaje> viajesPropios;
	private Viaje viajeSeleccionado;
	private Reserva reservaSeleccionada;
	private String comentario;
	private int puntuacion;

	@ManagedProperty(value = "#{beanMessages}")
	private BeanMessages beanMessages;

	@ManagedProperty(value = "#{beanValoraciones}")
	private BeanValoraciones beanValoraciones;

	@ManagedProperty(value = "#{beanListarViaje}")
	private BeanListarViaje beanListarViaje;

	@PostConstruct
	public void init() {
		this.reload();
	}

	public void reload() {
		viajesPropios = (List<Viaje>) Controlador.getInstance().listarViajes(false, false, true, false, false);
	}

	public List<Viaje> getViajesPropios() {
		return viajesPropios;
	}

	public void setViajesPropios(List<Viaje> viajesPropios) {
		this.viajesPropios = viajesPropios;
	}

	public Viaje getViajeSeleccionado() {
		return viajeSeleccionado;
	}

	public void setViajeSeleccionado(Viaje viajeSeleccionado) {
		System.out.println("BeanMisViajes.setViajeSeleccionado()");
		this.viajeSeleccionado = viajeSeleccionado;
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

	public BeanValoraciones getBeanValoraciones() {
		return beanValoraciones;
	}

	public void setBeanValoraciones(BeanValoraciones beanValoraciones) {
		this.beanValoraciones = beanValoraciones;
	}

	public BeanListarViaje getBeanListarViaje() {
		return beanListarViaje;
	}

	public void setBeanListarViaje(BeanListarViaje beanListarViaje) {
		this.beanListarViaje = beanListarViaje;
	}

	public String aceptarReserva() {
		Viaje aceptada = Controlador.getInstance().aceptarViaje(viajeSeleccionado.getId(),
				reservaSeleccionada.getUsuario().getUsuario());
		if (aceptada != null) {
			beanMessages.info("La reserva ha sido aceptada con exito");
			this.reload();
			beanListarViaje.reload();
		} else {
			beanMessages.error("La reserva ya está aceptada o no hay plazas disponibles");
		}
		return "faceletsMisViajes";

	}

	public String rechazarReserva() {
		Viaje rechazada = Controlador.getInstance().rechazarViaje(viajeSeleccionado.getId(),
				reservaSeleccionada.getUsuario().getUsuario());
		if (rechazada != null) {
			beanMessages.info("La reserva ha sido rechazada con exito");
			this.reload();
			beanListarViaje.reload();
		} else {
			beanMessages.error("La reserva no se ha podido rechazar");
		}
		return "faceletsMisViajes";
	}

	public String valorar() {
		Viaje viaje = this.reservaSeleccionada.getViaje();
		if (Controlador.getInstance().valorarViajeConductor(viaje.getId(),
				this.reservaSeleccionada.getUsuario().getUsuario(), comentario, puntuacion)) {
			beanMessages.info("La valoracion ha sido realizada con exito");

			String nombreViaje = viaje.getCiudadOrigen() + "-" + viaje.getCiudadDestino();
			String nombreReceptor = reservaSeleccionada.getNombreUsuario();
			String mensaje = beanValoraciones.buildMessage(nombreViaje, "pasajero", nombreReceptor);

			beanValoraciones.enviarTexto(mensaje, viaje.getId());
			this.reload();
		} else {
			beanMessages.error("La valoracion no se ha podido realizar");
		}

		return "faceletsMisViajes";
	}

}
