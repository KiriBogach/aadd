package jms;

import java.util.Map;
import javax.faces.context.FacesContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import beans.BeanValoraciones;

public class OyenteValoraciones implements MessageListener {
	private BeanValoraciones beanValoraciones;

	public OyenteValoraciones() {
		System.out.println("construcotrOyente");
	}

	@Override
	public void onMessage(Message mensaje) {
		System.out.println("onMessage");
		if (mensaje != null) {
			System.out.println(mensaje.toString());
		} else {
			System.out.println("MENSAJE NULL!!!!");
		}
		if (mensaje instanceof TextMessage) {
			TextMessage mensajeTexto = (TextMessage) mensaje;
			System.out.println("OyenteSuscripcion.onMessage()");
			try {
				System.out.println(mensajeTexto.getIntProperty("viaje"));
				String texto = mensajeTexto.getText();
				Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
				beanValoraciones = (BeanValoraciones) session.get("beanValoraciones");
				if (beanValoraciones != null) {
					System.out.println(beanValoraciones.toString());
				} else {
					System.out.println("NULL BLYAT SUKA SAYUIBAL!");
				}
				beanValoraciones.addMensajeRecibido(texto);
			} catch (JMSException e) {
				System.out.println("CATCH!");
				e.printStackTrace();
			}
		}
	}
}
