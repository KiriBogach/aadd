package beans;

import java.util.Collection;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import controller.Controlador;
import model.Viaje;

public class BeanListarViaje {
	private Collection<Viaje> viajes;
	private String viajeSeleccionado;

	public BeanListarViaje() {
		viajes = Controlador.getInstance().listarViajes();
		// Una vez se disponga del Registrar Viaje, este codigo sobra.
		if (viajes.size() == 0) {
			Controlador.getInstance().registrarViaje(5, 125.0);
			Controlador.getInstance().registrarViaje(3, 25.2);
			Controlador.getInstance().registrarViaje(4, 200.5);
			Controlador.getInstance().registrarViaje(4, 75.0);
			viajes = Controlador.getInstance().listarViajes();
		}
	}

	public Collection<Viaje> getViajes() {
		viajes = Controlador.getInstance().listarViajes();
		return viajes;
	}

	public void setViajes(Collection<Viaje> viajes) {
		this.viajes = viajes;
	}

	public String getViajeSeleccionado() {
		return viajeSeleccionado;
	}

	public void setViajeSeleccionado(String viajeSeleccionado) {
		this.viajeSeleccionado = viajeSeleccionado;
	}

	public void selectViaje(ActionEvent event) {
		System.out.println("BeanListarViaje.selectViaje()");
		UIParameter component = (UIParameter) event.getComponent().findComponent("selectId");
		String id = component.getValue().toString();
		this.setViajeSeleccionado(id);
	}
}