import java.util.ArrayList;
import java.util.List;

import javax.jms.Session;

import ningyuan.pan.servicex.jms.Sender;
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
public class TestSender {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DataSourceManager<Session> dataSourceManager = new ActiveMQDataSourceManager("conf/activemq.properties");
		
		ServiceXUtil.getInstance().setGelobalObject(GlobalObjectName.JMS_DATA_SOURCE_MANAGER, dataSourceManager);
		
		List<String> msgs = new ArrayList<String>();
		msgs.add("hello");
		msgs.add("world");
		
		Sender.sendMessage(msgs, "activemq.artemis.queue");

	}

}
