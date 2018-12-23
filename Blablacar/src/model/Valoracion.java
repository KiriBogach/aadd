package model;

import java.io.Serializable;

public class Valoracion implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int MIN_PUNTUACION = 1;
	public static final int MAX_PUNTUACION = 10;

	private String comentario;
	private int puntuacion;
	private String idReceptor;
	private String idEmisor;
	private Reserva reserva;
	private String rolReceptor;

	public Valoracion(String comentario, int puntuacion, String idReceptor, String idEmisor, Reserva reserva) {
		this(comentario, puntuacion);
		this.idReceptor = idReceptor;
		this.idEmisor = idEmisor;
		this.reserva = reserva;
		this.rolReceptor = reserva.rolReceptorValorado(idReceptor);
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

	public String getIdReceptor() {
		return idReceptor;
	}

	public void setIdReceptor(String idReceptor) {
		this.idReceptor = idReceptor;
	}

	public String getIdEmisor() {
		return idEmisor;
	}

	public void setIdEmisor(String idEmisor) {
		this.idEmisor = idEmisor;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public String getRolReceptor() {
		return rolReceptor;
	}

	public void setRolReceptor(String rolReceptor) {
		this.rolReceptor = rolReceptor;
	}

	// ¿Usar equals superficial?
	public boolean isEmisor(String idUsuarioLogeado) {
		return idUsuarioLogeado.equals(this.idEmisor);
	}

}
