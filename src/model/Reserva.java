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

	public Reserva() {
		super();
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
}