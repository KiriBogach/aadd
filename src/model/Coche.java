package model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Coche implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String matricula; // PK
	private String modelo;
	private int confort;
	private int year;
	@OneToOne
	private Usuario usuario;
	@OneToMany(mappedBy = "coche")
	private Collection<Viaje> viajes;
	
	public Coche() {
		
	}
	
	public Coche(String matricula, String modelo, int confort, int year) {
		this.matricula = matricula;
		this.modelo = modelo;
		this.confort = confort;
		this.year = year;
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

	// crear las relaciones que tiene; con usuario y con viaje

	// EN EL MODELO DE LA PRACTICA TENEMOS BIDIRECCIONALIDAD EN LAS ENTIDADES.
	// PUEDE SER QUE NO SEA NECESARIO!! ESTUDIAR TODOS LOS CASOS
}
