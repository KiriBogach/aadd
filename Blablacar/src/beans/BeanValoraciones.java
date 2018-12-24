package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

import javax.jms.JMSException;
import javax.naming.NamingException;

import controller.Controlador;
import jms.Emisor;
import jms.Consumidor;
import model.Viaje;

@ManagedBean(name = "beanValoraciones")
@SessionScoped
public class BeanValoraciones implements Serializable {
	private static final long serialVersionUID = 1L;

	private Collection<String> mensajesRecibidos = new LinkedList<String>();
	private Collection<Integer> suscrito = new LinkedList<Integer>();
	private String valoracion;
	private int viajeToSend;
	private String sugerencia;

	@ManagedProperty(value = "#{beanMessages}")
	private BeanMessages beanMessages;

	public Collection<String> getMensajesRecibidos() {
		return mensajesRecibidos;
	}

	public void setMensajesRecibidos(Collection<String> mensajesRecibidos) {
		this.mensajesRecibidos = mensajesRecibidos;
	}

	public void addMensajeRecibido(String mensaje) {
		System.out.println("BeanValoraciones.addMensajeRecibido()");
		System.out.println(mensaje);
		this.mensajesRecibidos.add(mensaje);
	}

	public int getViajeToSend() {
		return viajeToSend;
	}

	public void setViajeToSend(int viajeToSend) {
		this.viajeToSend = viajeToSend;
	}

	public String getValoracion() {
		return valoracion;
	}

	public void setValoracion(String valoracion) {
		this.valoracion = valoracion;
	}

	public String getSugerencia() {
		return sugerencia;
	}

	public void setSugerencia(String sugerencia) {
		this.sugerencia = sugerencia;
	}

	public Collection<Integer> getSuscrito() {
		return suscrito;
	}

	public void setSuscrito(Collection<Integer> suscrito) {
		this.suscrito = suscrito;
	}

	public BeanMessages getBeanMessages() {
		return beanMessages;
	}

	public void setBeanMessages(BeanMessages beanMessages) {
		this.beanMessages = beanMessages;
	}

	public void crearOyenteBuzonSugerencias() {
		try {
			Consumidor.crearConsumidorBuzonSugerencias();
		} catch (NamingException | JMSException e) {
			e.printStackTrace();
		}
	}

	public void suscripciones(String usuario) {
		Collection<Viaje> viajesToSubscribe = Controlador.getInstance().listen();
		for (Viaje viaje : viajesToSubscribe) {
			try {
				Consumidor.registrarApartado(usuario, viaje.getId());
				suscrito.add(viaje.getId());
			} catch (NamingException | JMSException e) {
				e.printStackTrace();
			}
		}
	}

	public void enviarTexto(String mensaje, int idViaje) {
		System.out.println("BeanValoraciones.enviarTexto()");
		if (mensaje == null || mensaje.equals("")) {
			beanMessages.errorCabecera("No se puede enviar un mensaje vacio");
			return;
		}
		try {

			Emisor.publicar(mensaje, idViaje);
		} catch (NamingException | JMSException e) {
			beanMessages.errorCabecera("Error durante el envio");
			return;
		}
		beanMessages.infoCabecera("Envio correcto");

	}

	public void enviarTextoBuzonSugerencias() {
		if (sugerencia == null || sugerencia.equals("")) {
			beanMessages.errorCabecera("No se puede enviar un mensaje vacio");
			return;
		}
		try {

			Emisor.enviarBuzonSugerencias(sugerencia);
		} catch (NamingException | JMSException e) {
			beanMessages.errorCabecera("Error durante el envio");
			return;
		}
		beanMessages.infoCabecera("Se ha enviado su sugerencia");

	}

	public void enviarTexto() {
		System.out.println("BeanValoraciones.enviarTexto()");
		if (valoracion == null || valoracion.equals("")) {
			beanMessages.errorCabecera("No se puede enviar un mensaje vacio");
			return;
		}
		try {
			System.out.println("viajeToSend=" + viajeToSend);
			System.out.println("valoracion=" + valoracion);
			Emisor.publicar(valoracion, viajeToSend);
		} catch (NamingException | JMSException e) {
			beanMessages.errorCabecera("Error durante el envio");
			return;
		}
		beanMessages.infoCabecera("Envio correcto");
		valoracion = "";
	}

	public String buildMessage(String nombreViaje, String rolReceptor, String usuarioReceptor) {
		return "En el viaje: " + nombreViaje + " se ha valorado al " + rolReceptor + " " + usuarioReceptor;
	}

	public void close() {
		try {
			Consumidor.close();
		} catch (JMSException e) {

			e.printStackTrace();
		}
	}

}
