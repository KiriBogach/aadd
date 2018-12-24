package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import controller.Controlador;

@Entity
public class Coche implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int MIN_CONFORT = 1;
	public static final int MAX_CONFORT = 5;

	@Id
	private String matricula; // PK
	private String modelo;
	private int confort;
	private int year;
	@OneToOne
	private Usuario usuario;
	@OneToMany(mappedBy = "coche", fetch=FetchType.EAGER)
	private Collection<Viaje> viajes;

	public Coche() {

	}

	public Coche(String matricula, String modelo, int confort, int year) {
		if (confort < MIN_CONFORT || confort > MAX_CONFORT) {
			throw new IllegalArgumentException(
					"El confort tiene que estar en un intervalo de [" + MIN_CONFORT + "-" + MAX_CONFORT + "]");
		}

		this.matricula = matricula;
		this.modelo = modelo;
		this.confort = confort;
		this.year = year;
		this.viajes = new LinkedList<>();
	}

	public Collection<Viaje> getViajes() {
		return viajes;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getConfort() {
		return confort;
	}

	public void setConfort(int confort) {
		this.confort = confort;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isConductor(String usuario) {
		return this.usuario.isUsuario(usuario);
	}

	public void addViaje(Viaje viaje) {
		viaje.setCoche(this);
		this.viajes.add(viaje);
	}
	public Collection<Viaje> getViajesFinalizados(){
		Collection<Viaje> viajesFinalizados= new LinkedList<>();
		for (Viaje viaje : viajes) {
			if (viaje.isFinalizado(Controlador.FECHA_SISTEMA_DATE))
				viajesFinalizados.add(viaje);
		}
		return viajesFinalizados;
	}

}
