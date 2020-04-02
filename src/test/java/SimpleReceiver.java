import javax.jms.Message;

import ningyuan.pan.servicex.jms.MessageReceiver;

/**
 * 
 */

/**
 * @author ningyuan
 *
 */
public class SimpleReceiver extends MessageReceiver {
	
	public SimpleReceiver(String propFile, String queueName) {
		super(propFile, queueName);
	}
	
	@Override
	public void onMessage(Message message) {
		System.out.println("!!!"+message);
	}

}
