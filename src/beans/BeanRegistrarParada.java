package beans;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

public class BeanRegistrarParada {
	private Map<String, String> ciudadesOfrecidas = new LinkedHashMap<String, String>();
	private Map<String, String> paradasOfrecidas = new LinkedHashMap<String, String>();
	private String[] ciudadesSeleccionadas = null;
	private String[] paradasSeleccionadas = null;
	private HtmlCommandButton puedeSeleccionar;
	private boolean estadoAccionSeleccionar;

	public BeanRegistrarParada() {
		estadoAccionSeleccionar = true;
		// enumerado de ciudades!
		ciudadesOfrecidas.put("Murcia", "Murcia");
		ciudadesOfrecidas.put("Madrid", "Madrid");
		ciudadesOfrecidas.put("Barcelona", "Barcelona");
		ciudadesOfrecidas.put("Valencia", "Valencia");
	}

	public HtmlCommandButton getPuedeSeleccionar() {
		return puedeSeleccionar;
	}

	public void setPuedeSeleccionar(HtmlCommandButton grid) {
		puedeSeleccionar = grid;
	}

	public boolean isEstadoAccionSeleccionar() {
		return estadoAccionSeleccionar;
	}

	public void setEstadoAccionSeleccionar(boolean estadoAccionSeleccionar) {
		this.estadoAccionSeleccionar = estadoAccionSeleccionar;
	}

	/* Mueve elementos de izquierda a derecha */
	public void moveDtoP(ActionEvent ae) {
		if (ciudadesSeleccionadas != null && ciudadesSeleccionadas.length > 0
				&& (paradasOfrecidas.size() + ciudadesSeleccionadas.length) <= 2) {
			for (String item : ciudadesSeleccionadas) {
				paradasOfrecidas.put(item, ciudadesOfrecidas.remove(item));
			}
		}
	}

	/* Mueve elementos de derecha a izquierda */
	public void movePtoD(ActionEvent ae) {
		if (paradasSeleccionadas != null && paradasSeleccionadas.length > 0) {
			for (String item : paradasSeleccionadas) {
				ciudadesOfrecidas.put(item, paradasOfrecidas.remove(item));
			}
		}
	}

	public void puedeAsignarParada(ValueChangeEvent event) {
		HtmlSelectBooleanCheckbox sender = (HtmlSelectBooleanCheckbox) event.getComponent();
		puedeSeleccionar.setRendered(sender.isSelected());
	}

	public String[] getCiudadesSeleccionadas() {
		return ciudadesSeleccionadas;
	}

	public String[] getParadasSeleccionadas() {
		return paradasSeleccionadas;
	}

	public void setCiudadesSeleccionadas(String[] ciudadesSeleccionadas) {
		this.ciudadesSeleccionadas = ciudadesSeleccionadas;
	}

	public void setParadasSeleccionadas(String[] paradasSeleccionadas) {
		this.paradasSeleccionadas = paradasSeleccionadas;
	}

	public Map<String, String> getCiudadesOfrecidas() {
		return ciudadesOfrecidas;
	}

	public Map<String, String> getParadasOfrecidas() {
		return paradasOfrecidas;
	}
}
