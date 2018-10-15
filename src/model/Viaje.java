package model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name = "ViajeCocheJPA")
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
}