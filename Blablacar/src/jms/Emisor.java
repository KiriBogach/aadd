package jms;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TopicSession;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Emisor {

	public static void publicar(String texto, int idViaje) throws NamingException, JMSException {
		InitialContext iniCtx = new InitialContext();
		Object tmp = iniCtx.lookup("jms/TopicConnectionFactory");
		TopicConnectionFactory qcf = (TopicConnectionFactory) tmp;
		TopicConnection conn = qcf.createTopicConnection();
		Topic topic = (Topic) iniCtx.lookup("topic/valoraciones");
		TopicSession session = conn.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
		TextMessage message = session.createTextMessage();
		System.out.println("TEXTO=" + texto + ", IDVIAJE= " + idViaje);
		message.setText(texto);
		message.setIntProperty("viaje", idViaje);
		TopicPublisher topicPublisher = session.createPublisher(topic);
		topicPublisher.publish(message, DeliveryMode.PERSISTENT, Message.DEFAULT_PRIORITY, 7 * 24 * 3600 * 1000L);
		conn.close();
	}

	public static void enviarBuzonSugerencias(String texto) throws NamingException, JMSException {
		InitialContext iniCtx = new InitialContext();
		Object tmp = iniCtx.lookup("jms/TopicConnectionFactory");
		TopicConnectionFactory qcf = (TopicConnectionFactory) tmp;
		TopicConnection conn = qcf.createTopicConnection();
		Topic topic = (Topic) iniCtx.lookup("topic/buzonSugerencias");
		TopicSession session = conn.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
		TextMessage message = session.createTextMessage();
		message.setText(texto);
		TopicPublisher topicPublisher = session.createPublisher(topic);
		topicPublisher.publish(message, DeliveryMode.PERSISTENT, Message.DEFAULT_PRIORITY, 7 * 24 * 3600 * 1000L);
		conn.close();
	}

}
