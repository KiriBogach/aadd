package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQueries({ @NamedQuery(name = "findViajeById", query = "SELECT v FROM Viaje v where v.id=:id") })
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
	@OneToMany(cascade = { CascadeType.REMOVE }, mappedBy = "viaje", fetch = FetchType.EAGER)
	@OrderBy("estado ASC")
	private List<Reserva> reservas;

	public Viaje() {

	}

	public Viaje(int plazas, double precio) {
		this.plazas = plazas;
		this.precio = precio;
		this.notas = new ArrayList<>();
		this.reservas = new LinkedList<>();
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

	public void addNota(String nota) {
		this.notas.add(nota);
	}

	public boolean removeNota(String nota) {
		return this.notas.remove(nota);
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

	public void addReserva(Reserva reserva) {
		this.reservas.add(reserva);
	}

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}

	public String getCiudadOrigen() {
		return this.origen.getCiudad();
	}

	public String getCiudadDestino() {
		return this.destino.getCiudad();
	}

	public String getUsuarioConductor() {
		return coche.getUsuarioConductor();
	}

	public Reserva getReservaUsuario(String usuario) {
		for (Reserva reserva : this.reservas) {
			if (reserva.isUsuario(usuario)) {
				return reserva;
			}
		}
		return null;
	}

	public boolean isConductor(String usuario) {
		if (this.coche == null) {
			return false;
		}
		return this.coche.isConductor(usuario);
	}

	public boolean isFinalizado(Date fecha) {
		return this.destino.isAnterior(fecha);
	}

	public boolean aceptarReserva() {
		if (plazas > 0) {
			plazas--;
			return true;
		}

		return false;
	}

}