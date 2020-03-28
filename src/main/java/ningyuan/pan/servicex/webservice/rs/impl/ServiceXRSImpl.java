/**
 * 
 */
package ningyuan.pan.servicex.webservice.rs.impl;

import ningyuan.pan.servicex.ServiceX;
import ningyuan.pan.servicex.webservice.rs.ServiceXRS;

/**
 * @author ningyuan
 *
 */
public class ServiceXRSImpl implements ServiceXRS {
	
	private final ServiceX service;
	
	public ServiceXRSImpl(ServiceX service) {
		this.service = service;
	}
	
	@Override
	public String getName() {
		return service.getName();
	}

}
