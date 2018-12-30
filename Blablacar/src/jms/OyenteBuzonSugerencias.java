package jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import controller.Controlador;

public class OyenteBuzonSugerencias implements MessageListener {

	private Controlador controlador;

	public OyenteBuzonSugerencias(Controlador controlador) {
		this.controlador = controlador;
	}

	@Override
	public void onMessage(Message mensaje) {
		if (mensaje instanceof TextMessage) {
			TextMessage mensajeTexto = (TextMessage) mensaje;
			try {
				String texto = mensajeTexto.getText();
				controlador.addSugerencia(texto);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

	}

}
