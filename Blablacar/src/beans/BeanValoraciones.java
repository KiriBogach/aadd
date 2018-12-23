package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.naming.NamingException;

import controller.Controlador;
import jms.PublicadorValoraciones;
import jms.SuscriptorValoraciones;
import model.Viaje;

@ManagedBean(name = "beanValoraciones")
@SessionScoped
public class BeanValoraciones implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Collection<String> mensajesRecibidos = new LinkedList<String>();
	private Collection<Integer> suscrito = new LinkedList<Integer>();
	private String valoracion;
	private int viajeToSend;

	@ManagedProperty(value = "#{beanMessages}")
	private BeanMessages beanMessages;

	@PostConstruct
	public void init() {
		Collection<Viaje> viajesToSubscribe = Controlador.getInstance().listen();
		for (Viaje viaje : viajesToSubscribe) {
			try {
				SuscriptorValoraciones.registrarApartado(viaje.getId());
				suscrito.add(viaje.getId());
			} catch (NamingException | JMSException e) {
				e.printStackTrace();
			}
		}
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
			PublicadorValoraciones.enviar(valoracion, viajeToSend);
		} catch (NamingException | JMSException e) {
			beanMessages.errorCabecera("Error durante el envio");
			return;
		}
		beanMessages.infoCabecera("Envio correcto");
		valoracion = "";
	}

	/*
	 * public void recibirTodosTexto(ActionEvent event) { try {
	 * SuscriptorValoraciones.registrarApartado(); } catch (NamingException e) {
	 * e.printStackTrace(); } catch (JMSException e) { e.printStackTrace(); } }
	 */

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

}
