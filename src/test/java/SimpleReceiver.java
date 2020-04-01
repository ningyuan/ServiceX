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
	
	public SimpleReceiver(String queueName) {
		super(queueName);
	}
	
	@Override
	public void onMessage(Message message) {
		System.out.println("!!!"+message);
	}

}
