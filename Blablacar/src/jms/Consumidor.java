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

import beans.BeanController;

public class Consumidor {
	// Crea un subscriptor a un topico
	private static List<TopicSubscriber> topicSubscribers = new LinkedList<TopicSubscriber>();

	public static void registrarApartado(String usuario, int idViaje) throws NamingException, JMSException {
		InitialContext iniCtx = new InitialContext();
		Object tmp = iniCtx.lookup("jms/TopicConnectionFactory");
		TopicConnectionFactory qcf = (TopicConnectionFactory) tmp;
		TopicConnection conn = qcf.createTopicConnection();

		Topic topic = (Topic) iniCtx.lookup("topic/valoraciones");
		TopicSession session = conn.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
		TopicSubscriber subscriber = session.createSubscriber(topic, "viaje = " + idViaje, false);

		OyenteValoraciones oyente = new OyenteValoraciones();
		subscriber.setMessageListener(oyente);
		topicSubscribers.add(subscriber);
		conn.start();

	}

	public static void crearConsumidorBuzonSugerencias(BeanController beanControlador)
			throws NamingException, JMSException {
		InitialContext iniCtx = new InitialContext();
		Object tmp = iniCtx.lookup("jms/TopicConnectionFactory");
		TopicConnectionFactory qcf = (TopicConnectionFactory) tmp;
		TopicConnection conn = qcf.createTopicConnection();
		Topic topic = (Topic) iniCtx.lookup("topic/buzonSugerencias");
		TopicSession session = conn.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
		TopicSubscriber subscriber = session.createSubscriber(topic);
		OyenteBuzonSugerencias oyente = new OyenteBuzonSugerencias(beanControlador.getControlador());
		subscriber.setMessageListener(oyente);
		beanControlador.setSuscriptorBuzonSugerencias(subscriber);
		conn.start();
	}

	public static void close() throws JMSException {
		for (TopicSubscriber topicSubscriber : topicSubscribers) {
			topicSubscriber.close();
		}
		topicSubscribers.clear();
	}

	public static void closeSubscriberBuzon(TopicSubscriber subscriber) throws JMSException {
		subscriber.close();
	}
}
