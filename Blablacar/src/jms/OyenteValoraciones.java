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
		Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		beanValoraciones = (BeanValoraciones) session.get("beanValoraciones");
	}

	@Override
	public void onMessage(Message mensaje) {
		if (mensaje instanceof TextMessage) {
			TextMessage mensajeTexto = (TextMessage) mensaje;
			try {
				String texto = mensajeTexto.getText();
				beanValoraciones.addMensajeRecibido(texto);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
