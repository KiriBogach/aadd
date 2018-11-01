package model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

@Entity
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	private String comentario;
	@Enumerated(EnumType.STRING)
	private EstadoReserva estado;
	@ManyToOne
	private Usuario usuario;
	@ManyToOne
	private Viaje viaje;

	public Reserva() {
		super();
	}

	public Reserva(String comentario, EstadoReserva estado) {
		this.comentario = comentario;
		this.estado = estado;
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
	
	public void setEstadoAceptado() {
		this.estado = EstadoReserva.ACEPTADA;
	}

}