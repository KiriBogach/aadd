package beans;

import controller.Controlador;

public class BeanRegistrarCoche {
	private String matricula;
	private String modelo;
	private String year;
	private String confort;

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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getConfort() {
		return confort;
	}

	public void setConfort(String confort) {
		this.confort = confort;
	}

	public String registrar() {
		// TODO: Probar que sea un int antes de llegar aquí
		int yearInt;
		int confortInt;
		try {
			yearInt = Integer.valueOf(year);
			confortInt = Integer.valueOf(confort);
		} catch (NumberFormatException ex) {
			return "faceletsFallo";
		}

		if (Controlador.getInstance().addCoche(matricula, modelo, yearInt, confortInt)) {
			return "faceletsWelcome";
		} else {
			// para resetear la vista
			setMatricula("");
			setModelo("");
			setYear("");
			setConfort("");
			return "faceletsFallo";
		}
	}
}
