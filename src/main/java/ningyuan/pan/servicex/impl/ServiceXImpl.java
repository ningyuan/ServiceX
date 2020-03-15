/**
 * 
 */
package ningyuan.pan.servicex.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ningyuan.pan.servicex.service.ServiceX;

/**
 * @author ningyuan
 *
 */
public class ServiceXImpl implements ServiceX {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceXImpl.class);
	
	public String getName() {
		LOGGER.debug("getName()");
		
		return "ServiceX";
	}

}
