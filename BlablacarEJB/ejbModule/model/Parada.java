package model;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Parada implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	private String ciudad;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	@Embedded
	private Direccion direccion;

	public Parada() {

	}

	public Parada(String ciudad, String calle, int CP, Date fecha) {
		this.ciudad = ciudad;
		this.direccion = new Direccion(calle, CP);
		this.fecha = fecha;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public boolean isAnterior(Date fecha) {
		if (this.fecha != null && fecha != null) {
			return this.fecha.before(fecha);
		}
		return false;
	}
}