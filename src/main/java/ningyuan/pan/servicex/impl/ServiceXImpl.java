/**
 * 
 */
package ningyuan.pan.servicex.impl;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
