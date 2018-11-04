package model;

import java.io.Serializable;

public class Valoracion implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int MIN_PUNTUACION = 1;
	public static final int MAX_PUNTUACION = 10;

	private String comentario;
	private int puntuacion;
	private Usuario receptor;
	private Usuario emisor;
	private Reserva reserva;

	public Valoracion(String comentario, int puntuacion, Usuario receptor, Usuario emisor, Reserva reserva) {
		this(comentario, puntuacion);
		this.receptor = receptor;
		this.emisor = emisor;
		this.reserva = reserva;
	}

	public Valoracion() {

	}

	public Valoracion(String comentario, int puntuacion) {
		if (puntuacion < MIN_PUNTUACION || puntuacion > MAX_PUNTUACION) {
			throw new IllegalArgumentException(
					"La puntuación tiene que estar en un intervalo de [" + MIN_PUNTUACION + "-" + MAX_PUNTUACION + "]");
		}
		this.comentario = comentario;
		this.puntuacion = puntuacion;
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

	public Usuario getReceptor() {
		return receptor;
	}

	public void setReceptor(Usuario receptor) {
		this.receptor = receptor;
	}

	public Usuario getEmisor() {
		return emisor;
	}

	public void setEmisor(Usuario emisor) {
		this.emisor = emisor;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	// ¿Usar equals superficial?
	public boolean isEmisor(Usuario usuarioLogeado) {
		return usuarioLogeado.getUsuario().equals(this.emisor.getUsuario());
	}

}
