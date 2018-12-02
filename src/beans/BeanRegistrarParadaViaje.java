package beans;

import java.util.Date;

import javax.faces.event.ActionListener;

import controller.Controlador;
import model.Parada;

public class BeanRegistrarParadaViaje  {

	private String ciudad;
	private String calle;
	private String cp;
	private Date fecha;
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cP) {
		cp = cP;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String registrarParada(int idViaje, boolean paradaOrigen){
		
		Controlador controlador=Controlador.getInstance();
		int CP; 
		Parada parada;
		try{
			CP = Integer.parseInt(cp);
		}catch(NumberFormatException e){
			return "faceletsFallo";
		}
		if(paradaOrigen){
			parada=controlador.registrarParadaOrigen(idViaje, ciudad, calle, CP, fecha);
		}else{
			parada=controlador.registrarParadaDestino(idViaje, ciudad, calle, CP, fecha);
		}
		
		if(parada!=null){
			return "faceletsWelcome";
		}
		
		return "faceletsFallo";
		
	}
	
}
