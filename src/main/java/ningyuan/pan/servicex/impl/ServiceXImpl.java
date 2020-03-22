/**
 * 
 */
package ningyuan.pan.servicex.impl;


import java.util.Properties;

import ningyuan.pan.servicex.ServiceX;


/**
 * @author ningyuan
 *
 */
public class ServiceXImpl implements ServiceX {
	
	public String getName() {
		
		Properties configProp = null;

		try {
			configProp.clear();
		} 
		catch (NullPointerException npe) {
			
		}
		
		return "ServiceX";
	}
}
