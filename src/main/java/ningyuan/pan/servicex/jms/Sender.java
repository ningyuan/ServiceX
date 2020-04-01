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
 * A util class to send queue messages or topic messages.
 * 
 * @author ningyuan
 *
 */
public class Sender {
	private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);
	
	private static final Properties CONFIG_PROP = new Properties();
	
	static {
		try {
			CONFIG_PROP.load(new InputStreamReader(Sender.class.getClassLoader().getResourceAsStream("conf/activemq.properties")));
        } 
		catch (IOException ioe) {
			LOGGER.debug(ExceptionUtils.printStackTraceToString(ioe));
		}
	}
	
	public static void sendMessage(List<String> msgs, String queueName) {
		@SuppressWarnings("unchecked")
		DataSourceManager<Session> dataSourceManager = (DataSourceManager<Session>)ServiceXUtil.getInstance().getGelobalObject(GlobalObjectName.JMS_DATA_SOURCE_MANAGER);
		
		if(dataSourceManager != null) {
			Session session = dataSourceManager.initAndGetThreadLocalConnection();
			
			try {
				Destination queue = session.createQueue(CONFIG_PROP.getProperty(queueName));
				
				MessageProducer producer = session.createProducer(queue);
				
				producer.setDeliveryMode(DeliveryMode.PERSISTENT);
				
				for(String msg : msgs) {
					TextMessage message = session.createTextMessage(msg);
					producer.send(message);
				}

				producer.close();
			}
			catch (JMSException jmse) {
				LOGGER.debug(ExceptionUtils.printStackTraceToString(jmse));
			}
			catch (Exception e) {
				LOGGER.debug(ExceptionUtils.printStackTraceToString(e));
			}
			finally {
				dataSourceManager.removeAndCloseThreadLocalConnection();
			}
		}
	}
	
	public static void publishMessage(List<String> msgs, String topicName) {
		@SuppressWarnings("unchecked")
		DataSourceManager<Session> dataSourceManager = (DataSourceManager<Session>)ServiceXUtil.getInstance().getGelobalObject(GlobalObjectName.JMS_DATA_SOURCE_MANAGER);
		
		if(dataSourceManager != null) {
			Session session = dataSourceManager.initAndGetThreadLocalConnection();
			
			try {
				Destination topic = session.createTopic(CONFIG_PROP.getProperty(topicName));
				
				MessageProducer producer = session.createProducer(topic);
				
				producer.setDeliveryMode(DeliveryMode.PERSISTENT);
				
				for(String msg : msgs) {
					TextMessage message = session.createTextMessage(msg);
					producer.send(message);
				}
				
				producer.close();
			}
			catch (JMSException jmse) {
				LOGGER.debug(ExceptionUtils.printStackTraceToString(jmse));
			}
			catch (Exception e) {
				LOGGER.debug(ExceptionUtils.printStackTraceToString(e));
			}
			finally {
				dataSourceManager.removeAndCloseThreadLocalConnection();
			}
		}
	}
}
