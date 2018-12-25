package model;

import java.io.Serializable;
import java.lang.String;
import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.*;

@Entity
public class Reserva implements Serializable {
	private static final String ROL_CONDUCTOR = "Conductor";
	private static final String ROL_PASAJERO = "Pasajero";

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	private String comentario;
	@Enumerated(EnumType.STRING)
	private EstadoReserva estado;
	@JoinColumn(name = "USUARIO_RESERVADOR")
	@ManyToOne
	private Usuario usuario;
	@ManyToOne
	private Viaje viaje;
	private Collection<Valoracion> valoraciones;

	public Reserva() {
		super();
	}

	public Reserva(String comentario, EstadoReserva estado) {
		this.comentario = comentario;
		this.estado = estado;
		this.valoraciones = new LinkedList<Valoracion>();
	}

	public Reserva(String comentario) {
		this(comentario, EstadoReserva.PENDIENTE);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public EstadoReserva getEstado() {
		return estado;
	}

	public void setEstado(EstadoReserva estado) {
		this.estado = estado;
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isUsuario(String usuario) {
		return this.usuario.isUsuario(usuario);
	}

	public Collection<Valoracion> getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(Collection<Valoracion> valoraciones) {
		this.valoraciones = valoraciones;
	}

	public String getNombreUsuario() {
		return this.usuario.getUsuario();
	}

	public boolean setEstadoAceptado() {
		boolean aceptado = viaje.aceptarReserva();
		if (aceptado) {
			this.estado = EstadoReserva.ACEPTADA;
		}
		return aceptado;
	}

	public void setEstadoRechazado() {
		this.estado = EstadoReserva.RECHAZADA;
	}

	public void addValoracion(Valoracion valoracion) {
		this.valoraciones.add(valoracion);
	}

	public boolean haValorado(Usuario usuarioLogeado) {
		for (Valoracion valoracion : this.valoraciones) {
			if (valoracion.isEmisor(usuarioLogeado.getUsuario())) {
				return true;
			}
		}
		return false;
	}

	public String rolReceptorValorado(String usuario) {
		if (viaje.isConductor(usuario))
			return ROL_CONDUCTOR;
		return ROL_PASAJERO;
	}

	public boolean isAceptada() {
		return this.estado == EstadoReserva.ACEPTADA;
	}

}