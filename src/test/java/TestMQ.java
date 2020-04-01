import javax.jms.Session;

import ningyuan.pan.servicex.util.GlobalObjectName;
import ningyuan.pan.servicex.util.ServiceXUtil;
import ningyuan.pan.util.persistence.DataSourceManager;
import ningyuan.pan.util.persistence.JMSDataSourceManager;

/**
 * 
 */

/**
 * @author ningyuan
 *
 */
public class TestMQ {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		DataSourceManager<Session> dataSourceManager = new JMSDataSourceManager("conf/activemq.properties");
		
		ServiceXUtil.getInstance().setGelobalObject(GlobalObjectName.JMS_DATA_SOURCE_MANAGER, dataSourceManager);
		
		new SimpleReceiver("activemq.artemis.queue");
		
		for(; ;) {
			
		}
	}

}
