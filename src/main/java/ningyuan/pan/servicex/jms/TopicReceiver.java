/**
 * 
 */
package ningyuan.pan.servicex.jms;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ningyuan.pan.servicex.util.GlobalObjectName;
import ningyuan.pan.servicex.util.ServiceXUtil;
import ningyuan.pan.util.exception.ExceptionUtils;
import ningyuan.pan.util.persistence.DataSourceManager;

/**
 * An abstract class for receiving topic messages from a topic. Inherit the class
 * and write your own onMessage() method.
 * 
 * @author ningyuan
 *
 */
public abstract class TopicReceiver implements MessageListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(TopicReceiver.class);
	
	private final Properties configProp = new Properties();
	
	private final String topicName;
	
	private Session session;
	
	public TopicReceiver(String propFile,  String topicName) {
		this.topicName = topicName;
		
		try {
        	configProp.load(new InputStreamReader(Sender.class.getClassLoader().getResourceAsStream(propFile)));
        	init();
		} 
		catch (IOException ioe) {
			LOGGER.debug(ExceptionUtils.printStackTraceToString(ioe));
		}
	}
	
	private void init() {
		try {
			@SuppressWarnings("unchecked")
			DataSourceManager<Session> dataSourceManager = (DataSourceManager<Session>)ServiceXUtil.getInstance().getGelobalObject(GlobalObjectName.JMS_DATA_SOURCE_MANAGER);
			
			session = dataSourceManager.createNewConnection();
			Destination topic = session.createTopic(configProp.getProperty(topicName));
			MessageConsumer consumer = session.createConsumer(topic);
			        
			consumer.setMessageListener(this);
		}
		catch (JMSException jmse) {
			LOGGER.debug(ExceptionUtils.printStackTraceToString(jmse));
		}
		catch (Exception e) {
			LOGGER.debug(ExceptionUtils.printStackTraceToString(e));
		}	
	}
	
	/**
	 * Call this method when you do not need to receive messages. It 
	 * closes the session for this receiver.
	 */
	public void destroy() {
		if(session != null) {
			try {
				session.close();
			} 
			catch (JMSException e) {
				LOGGER.debug(ExceptionUtils.printStackTraceToString(e));
			}
		}
	}
	
	@Override
	public abstract void onMessage(Message message);

}
