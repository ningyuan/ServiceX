/**
 * 
 */
package ningyuan.pan.servicex.webservice.rs.server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import ningyuan.pan.servicex.impl.ServiceXImpl;
import ningyuan.pan.servicex.webservice.rs.ServiceXRS;
import ningyuan.pan.servicex.webservice.rs.impl.ServiceXRSImpl;
import ningyuan.pan.util.exception.ExceptionUtils;

/**
 * @author ningyuan
 *
 */
public class ServiceXRSCXFServer {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceXRSCXFServer.class);
	
	public static void main(String[] args) {
		String protocal = "http://";
		String server = "127.0.0.1";
		String port = "1234";
		String basePath = "/rs";
		
		Properties configProp;
		try {
			configProp = new Properties();
        	configProp.load(new InputStreamReader(ServiceXRSCXFServer.class.getClassLoader().getResourceAsStream("conf/webservice-server.properties")));
        	
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
        RSserver.setProvider(new JacksonJaxbJsonProvider());
        RSserver.create();  
	}
}
