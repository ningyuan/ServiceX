/**
 * 
 */
package ningyuan.pan.servicex.webservice.rs;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ningyuan.pan.servicex.impl.ServiceXImpl;
import ningyuan.pan.servicex.webservice.rs.impl.ServiceXRSImpl;
import ningyuan.pan.util.exception.ExceptionUtils;

/**
 * @author ningyuan
 *
 */
public class ServiceXRSServer {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceXRSServer.class);
	
	public static void main(String[] args) {
		String protocal = "http://";
		String server = "127.0.0.1";
		String port = "1234";
		String basePath = "/rs";
		
		Properties configProp;
		try {
			configProp = new Properties();
        	configProp.load(new InputStreamReader(ServiceXRSServer.class.getClassLoader().getResourceAsStream("conf/webservice-server.properties")));
        	
        	protocal = configProp.getProperty("REST.transport.protocal");
        	server = configProp.getProperty("REST.server");
        	port = configProp.getProperty("REST.server.port");
        		
		} catch (IOException e) {
			LOGGER.error(ExceptionUtils.printStackTraceToString(e));
        }
		
		String uri = protocal + server + ":" + port + basePath;
		ServiceXRS service = new ServiceXRSImpl(new ServiceXImpl());
		
		// start REST server jetty
		JAXRSServerFactoryBean RSserver = new JAXRSServerFactoryBean(); 
		RSserver.setServiceBean(service); 
        RSserver.setAddress(uri);  
        RSserver.create();  
	}
}
