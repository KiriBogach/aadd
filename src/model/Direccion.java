package model;

import javax.persistence.Embeddable;

@Embeddable
public class Direccion {
	private String calle;
	private int CP;

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