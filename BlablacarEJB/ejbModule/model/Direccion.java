package model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Direccion implements Serializable {

	private static final long serialVersionUID = 1L;
	private String calle;
	private int CP;

	public Direccion() {

	}

	public Direccion(String calle, int CP) {
		this.calle = calle;
		this.CP = CP;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getCP() {
		return CP;
	}

	public void setCP(int cP) {
		CP = cP;
	}
}