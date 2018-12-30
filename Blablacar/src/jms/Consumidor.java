package jms;

import java.util.LinkedList;
import java.util.List;
import javax.jms.JMSException;

import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import controller.Controlador;

public class Consumidor {
	// Crea un subscriptor a un topico
	private static List<TopicSubscriber> topicSubscribers = new LinkedList<TopicSubscriber>();
	private static List<TopicSubscriber> topicBuzonSugerencias = new LinkedList<TopicSubscriber>();
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

	public static void crearConsumidorBuzonSugerencias(Controlador controlador) throws NamingException, JMSException {
		InitialContext iniCtx = new InitialContext();
		Object tmp = iniCtx.lookup("jms/TopicConnectionFactory");
		TopicConnectionFactory qcf = (TopicConnectionFactory) tmp;
		TopicConnection conn = qcf.createTopicConnection();
		// conn.setClientID(usuario+idViaje);
		Topic topic = (Topic) iniCtx.lookup("topic/buzonSugerencias");
		TopicSession session = conn.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
		TopicSubscriber subscriber = session.createSubscriber(topic);
		OyenteBuzonSugerencias oyente = new OyenteBuzonSugerencias(controlador);
		subscriber.setMessageListener(oyente);
		topicBuzonSugerencias.add(subscriber);
		conn.start();

		// oyentesValoraciones.add(oyente);
	}

	public static void close() throws JMSException {
		for (TopicSubscriber topicSubscriber : topicSubscribers) {
			topicSubscriber.close();
		}
		topicSubscribers.clear();
	}
}
