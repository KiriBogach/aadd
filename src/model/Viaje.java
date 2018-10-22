package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
//@Table(name = "Viaje")
public class Viaje implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "asientos")
	private int plazas;
	private double precio;
	@CollectionTable(name = "NotasViaje")
	@ElementCollection(fetch = FetchType.EAGER)
	private ArrayList<String> notas;
	@ManyToOne
	private Coche coche;
	@OneToOne(cascade = { CascadeType.REMOVE })
	private Parada origen;
	@OneToOne(cascade = { CascadeType.REMOVE })
	private Parada destino;
	@OneToMany(cascade = { CascadeType.REMOVE }, mappedBy = "viaje")
	@OrderBy("estado ASC")
	private List<Reserva> reservas;

	public Viaje() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public ArrayList<String> getNotas() {
		return notas;
	}

	public void setNotas(ArrayList<String> notas) {
		this.notas = notas;
	}

	public Parada getOrigen() {
		return origen;
	}

	public void setOrigen(Parada origen) {
		this.origen = origen;
	}

	public Parada getDestino() {
		return destino;
	}

	public void setDestino(Parada destino) {
		this.destino = destino;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
}