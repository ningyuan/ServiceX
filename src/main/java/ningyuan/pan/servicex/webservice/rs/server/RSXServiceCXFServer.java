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

import ningyuan.pan.servicex.impl.XServiceJDBCImpl;
import ningyuan.pan.servicex.webservice.rs.RSXService;
import ningyuan.pan.servicex.webservice.rs.impl.RSXServiceImpl;
import ningyuan.pan.util.exception.ExceptionUtils;

/**
 * @author ningyuan
 *
 */
public class RSXServiceCXFServer {
	private static final Logger LOGGER = LoggerFactory.getLogger(RSXServiceCXFServer.class);
	
	public static void main(String[] args) {
		String protocal = "http://";
		String server = "127.0.0.1";
		String port = "1234";
		String basePath = "/";
		
		Properties configProp;
		try {
			configProp = new Properties();
        	configProp.load(new InputStreamReader(RSXServiceCXFServer.class.getClassLoader().getResourceAsStream("conf/webservice-server.properties")));
        	
        	protocal = configProp.getProperty("REST.transport.protocal");
        	server = configProp.getProperty("REST.server");
        	port = configProp.getProperty("REST.server.port");
        	basePath = configProp.getProperty("REST.base.path");
        		
		} catch (IOException e) {
			LOGGER.error(ExceptionUtils.printStackTraceToString(e));
        }
		
		String uri = protocal + server + ":" + port + basePath;
		RSXService service = new RSXServiceImpl(new XServiceJDBCImpl());
		
		// start REST server jetty
		JAXRSServerFactoryBean RSserver = new JAXRSServerFactoryBean(); 
		RSserver.setServiceBean(service); 
        RSserver.setAddress(uri);  
        RSserver.setProvider(new JacksonJaxbJsonProvider());
        RSserver.create();  
	}
}
