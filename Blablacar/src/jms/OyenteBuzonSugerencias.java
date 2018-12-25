package jms;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import beans.BeanController;

public class OyenteBuzonSugerencias implements MessageListener {

	@Override
	public void onMessage(Message mensaje) {
		if (mensaje instanceof TextMessage) {
			TextMessage mensajeTexto = (TextMessage) mensaje;
			try {
				String texto = mensajeTexto.getText();
				Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
				BeanController beanController = (BeanController) session.get("beanController");
				beanController.getControlador().addSugerencia(texto);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

	}

}
