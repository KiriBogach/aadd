package jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import controller.Controlador;

public class OyenteBuzonSugerencias implements MessageListener {

	@Override
	public void onMessage(Message mensaje) {
		if (mensaje instanceof TextMessage) {
			TextMessage mensajeTexto = (TextMessage) mensaje;

			try {

				String texto = mensajeTexto.getText();

				Controlador.getInstance().addSugerencia(texto);

			} catch (JMSException e) {
				
				e.printStackTrace();
			}
		}

	}

}
