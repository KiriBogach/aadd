package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import controller.Controlador;

@ManagedBean(name = "beanRegistrarCoche")
@SessionScoped
public class BeanRegistrarCoche implements Serializable {

	private static final long serialVersionUID = 1L;
	private String matricula;
	private String modelo;
	private String year;
	private String confort;
	@ManagedProperty(value = "#{beanMessages}")
	private BeanMessages beanMessages;

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

	public BeanMessages getBeanMessages() {
		return beanMessages;
	}

	public void setBeanMessages(BeanMessages beanMessages) {
		this.beanMessages = beanMessages;
	}

	public String registrar() {
		int yearInt;
		int confortInt;
		try {
			yearInt = Integer.valueOf(year);
			confortInt = Integer.valueOf(confort);
		} catch (NumberFormatException ex) {
			limpiarCampos();
			beanMessages.errorCabecera("Los datos introducidos son incorrectos");
			return "faceletsRegistroCoche";
		}

		if (Controlador.getInstance().addCoche(matricula, modelo, yearInt, confortInt)) {
			beanMessages.infoCabecera("Se ha registrado el coche con exito");
		} else {
			beanMessages.errorCabecera("No se ha podido registrar el coche");
		}

		limpiarCampos();
		return "faceletsRegistroCoche";
	}

	private void limpiarCampos() {
		setMatricula("");
		setModelo("");
		setYear("");
		setConfort("");
	}
}
