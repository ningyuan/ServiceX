/**
 * 
 */
package ningyuan.pan.servicex.jms;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ningyuan.pan.servicex.util.GlobalObjectName;
import ningyuan.pan.servicex.util.ServiceXUtil;
import ningyuan.pan.util.exception.ExceptionUtils;
import ningyuan.pan.util.persistence.DataSourceManager;

/**
 * @author ningyuan
 *
 */
public class XASender {
	private static final Logger LOGGER = LoggerFactory.getLogger(XASender.class);
	
	private final Properties configProp = new Properties();
	
	public XASender(String propFile) {
		try {
			configProp.load(new InputStreamReader(Sender.class.getClassLoader().getResourceAsStream(propFile)));
        } 
		catch (IOException ioe) {
			LOGGER.debug(ExceptionUtils.printStackTraceToString(ioe));
		}
	}
	
	public void sendMessages(List<String> msgs, String queueName) throws JMSException {
		
		@SuppressWarnings("unchecked")
		DataSourceManager<Session> dataSourceManager = (DataSourceManager<Session>)ServiceXUtil.getInstance().getGelobalObject(GlobalObjectName.JMS_XA_DATA_SOURCE_MANAGER);
		
		if(dataSourceManager != null) {
			Session session = dataSourceManager.getOrInitThreadLocalConnection();
			
			Destination queue = session.createQueue(configProp.getProperty(queueName));
				
			MessageProducer producer = session.createProducer(queue);
				
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
				
			for(String msg : msgs) {
				TextMessage message = session.createTextMessage(msg);
				producer.send(message);
			}

			producer.close();
		}
		else {
			throw new JMSException("No JMS XA Datasource");
		}
	}
	
	public void publishMessages(List<String> msgs, String topicName) throws JMSException {
		
		@SuppressWarnings("unchecked")
		DataSourceManager<Session> dataSourceManager = (DataSourceManager<Session>)ServiceXUtil.getInstance().getGelobalObject(GlobalObjectName.JMS_XA_DATA_SOURCE_MANAGER);
		
		if(dataSourceManager != null) {
			Session session = dataSourceManager.getOrInitThreadLocalConnection();
			
			Destination topic = session.createTopic(configProp.getProperty(topicName));
				
			MessageProducer producer = session.createProducer(topic);
				
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
				
			for(String msg : msgs) {
				TextMessage message = session.createTextMessage(msg);
				producer.send(message);
			}
				
			producer.close();
		}
		else {
			throw new JMSException("No JMS XA Datasource");
		}
	}
}
