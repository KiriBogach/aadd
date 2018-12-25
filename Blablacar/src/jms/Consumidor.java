package jms;

import java.util.LinkedList;
import java.util.List;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.JMSException;

import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Consumidor {
	// Crea un subscriptor a un topico
	private static List<TopicSubscriber> topicSubscribers = new LinkedList<TopicSubscriber>();

	private static QueueReceiver queueReceiver = null;
	// private static final String NOMBRE_SUSCRIPTOR = "MySub";
	// private static List<OyenteValoraciones> oyentesValoraciones = new
	// LinkedList<OyenteValoraciones>();

	public static void registrarApartado(String usuario, int idViaje) throws NamingException, JMSException {
		InitialContext iniCtx = new InitialContext();
		Object tmp = iniCtx.lookup("jms/TopicConnectionFactory");
		TopicConnectionFactory qcf = (TopicConnectionFactory) tmp;
		TopicConnection conn = qcf.createTopicConnection();
		// conn.setClientID(usuario+idViaje);
		Topic topic = (Topic) iniCtx.lookup("topic/valoraciones");
		TopicSession session = conn.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
		TopicSubscriber subscriber = session.createSubscriber(topic, "viaje = " + idViaje, false);
		/*
		 * TopicSubscriber subscriber = session.createDurableSubscriber(topic,
		 * NOMBRE_SUSCRIPTOR + idViaje, "viaje = " + idViaje, false);
		 */
		OyenteValoraciones oyente = new OyenteValoraciones();
		subscriber.setMessageListener(oyente);
		topicSubscribers.add(subscriber);
		conn.start();

		// oyentesValoraciones.add(oyente);
	}

	public static void crearConsumidorBuzonSugerencias() throws NamingException, JMSException {
		if (queueReceiver == null) {
			InitialContext iniCtx = new InitialContext();
			Object tmp = iniCtx.lookup("jms/QueueConnectionFactory");
			QueueConnectionFactory qcf = (QueueConnectionFactory) tmp;
			QueueConnection conn = qcf.createQueueConnection();
			Queue queue = (Queue) iniCtx.lookup("queue/buzonSugerencias");
			QueueSession session = conn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			queueReceiver = session.createReceiver(queue);
			OyenteBuzonSugerencias oyenteBuzonSugerencias = new OyenteBuzonSugerencias();
			queueReceiver.setMessageListener(oyenteBuzonSugerencias);
			conn.start();

		}
	}

	public static void close() throws JMSException {
		for (TopicSubscriber topicSubscriber : topicSubscribers) {
			topicSubscriber.close();
		}
		topicSubscribers.clear();
	}
}
