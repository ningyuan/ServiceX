/**
 * 
 */
package ningyuan.pan.servicex.impl;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.ibatis.session.SqlSession;

import ningyuan.pan.servicex.XService;
import ningyuan.pan.servicex.persistence.dao.UserDAO;
import ningyuan.pan.servicex.persistence.entity.User;
import ningyuan.pan.servicex.util.GlobalObjectName;
import ningyuan.pan.servicex.util.ServiceXUtil;
import ningyuan.pan.util.persistence.DataSourceManager;

/**
 * The service using Mybatis session. All public methods in service interface will be
 * woven with transaction codes. Pay attention to weaving this service implementation
 * with the corresponding transaction aspect (MybatisTransactionAspect).
 * 
 * @author ningyuan
 *
 */
public class XServiceMybatisImpl implements XService {

	public String getName() {
		@SuppressWarnings("unchecked")
		DataSourceManager<SqlSession> dataSourceManager = (DataSourceManager<SqlSession>)ServiceXUtil.getInstance().getGelobalObject(GlobalObjectName.MYBATIS_DATA_SOURCE_MANAGER);
		
		String msg = null;
		if(dataSourceManager != null) {
			try {
				// transaction aspect will weave the code which initiates the thread local connection
				// so we can use get instead of initAndGet
				UserDAO userDAO = dataSourceManager.getThreadLocalConnection().getMapper(UserDAO.class);
				User user = userDAO.findUserByID(0);
				msg = user.getFirstName();
			}
			catch(Exception e) {
				msg = "No user";
			}
		}
		else {
			msg = "No data source manager set in context";
		}
		
		sendJMSMessage(msg);
		
		return msg;
	}
	
	private void sendJMSMessage(String msg) {
		@SuppressWarnings("unchecked")
		DataSourceManager<Session> dataSourceManager = (DataSourceManager<Session>)ServiceXUtil.getInstance().getGelobalObject(GlobalObjectName.JMS_DATA_SOURCE_MANAGER);
		
		if(dataSourceManager != null) {
			Session session = dataSourceManager.initAndGetThreadLocalConnection();
			
			try {
				Destination queue = session.createQueue("anycast");
				
				MessageProducer producer = session.createProducer(queue);
				
				producer.setDeliveryMode(DeliveryMode.PERSISTENT);
				
				TextMessage message = session.createTextMessage(msg);
				
				producer.send(message);
				
				producer.close();
			}
			catch (JMSException e) {
				
			}
			finally {
				dataSourceManager.removeAndCloseThreadLocalConnection();
			}
		}
	}
}
