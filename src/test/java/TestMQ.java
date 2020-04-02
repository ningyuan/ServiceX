import javax.jms.Session;

import ningyuan.pan.servicex.util.GlobalObjectName;
import ningyuan.pan.servicex.util.ServiceXUtil;
import ningyuan.pan.util.persistence.ActiveMQDataSourceManager;
import ningyuan.pan.util.persistence.DataSourceManager;


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
		
		DataSourceManager<Session> dataSourceManager = new ActiveMQDataSourceManager("conf/activemq.properties");
		
		ServiceXUtil.getInstance().setGelobalObject(GlobalObjectName.JMS_DATA_SOURCE_MANAGER, dataSourceManager);
		
		new SimpleReceiver("conf/activemq.properties", "activemq.artemis.queue");
		
		for(; ;) {
			
		}
	}

}
