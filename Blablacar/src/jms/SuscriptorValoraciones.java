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

public class SuscriptorValoraciones {
	// Crea un subscriptor a un topico
	private static List<TopicSubscriber> topicSubscribers = new LinkedList<TopicSubscriber>();
	private static List<OyenteValoraciones> oyentesValoraciones = new LinkedList<OyenteValoraciones>();

	public static void registrarApartado(int idViaje) throws NamingException, JMSException {
		InitialContext iniCtx = new InitialContext();
		Object tmp = iniCtx.lookup("jms/TopicConnectionFactory");
		TopicConnectionFactory qcf = (TopicConnectionFactory) tmp;
		TopicConnection conn = qcf.createTopicConnection();
		Topic topic = (Topic) iniCtx.lookup("topic/valoraciones");
		TopicSession session = conn.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
		conn.start();
		
		TopicSubscriber suscriber = session.createSubscriber(topic, "viaje = " + idViaje, false);
		OyenteValoraciones oyente = new OyenteValoraciones();
		suscriber.setMessageListener(oyente);
		
		topicSubscribers.add(suscriber);
		oyentesValoraciones.add(oyente);
	}
}
