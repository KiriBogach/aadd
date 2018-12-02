package beans;

import controller.Controlador;
import model.Viaje;

public class BeanRegistrarViaje {
	private String plazas;
	private String precio;
	public static int idViaje;
	
	/*Métodos de consulta y establecimiento*/
	public String getPlazas() {
		return plazas;
	}
	public void setPlazas(String plazas) {
		this.plazas = plazas;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	

	
	
	/*Método que se encarga del registro del viaje*/
	public String registrarViaje(){
		Controlador controlador=Controlador.getInstance();
		int plazasEntero;
		double precioViaje;
		Viaje viaje;
		try{
			plazasEntero= Integer.parseInt(plazas);
			precioViaje=Double.parseDouble(precio);
		}catch(NumberFormatException e){
			return "faceletsFallo";
		}
		viaje=controlador.registrarViaje(plazasEntero,precioViaje);
		if(viaje!=null){
			idViaje=viaje.getId();
			return "faceletsWelcome";	
		}
		else
			return "faceletsFallo";
	}
	
	
	
}
