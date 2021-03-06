/**
 * 
 */
package ningyuan.pan.servicex.webservice.rs.impl.server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import ningyuan.pan.servicex.impl.XServiceMybatisImpl;
import ningyuan.pan.servicex.webservice.rs.RSXService;
import ningyuan.pan.servicex.webservice.rs.impl.RSXServiceImpl;
import ningyuan.pan.util.exception.ExceptionUtils;

/**
 * Jetty server for publishing a RESTful web service.
 * 
 * @author ningyuan
 *
 */
public class RSXServiceCXFServer {
	private static final Logger LOGGER = LoggerFactory.getLogger(RSXServiceCXFServer.class);
	
	public static void main(String[] args) {
		String url = null;
		
		Properties configProp;
		try {
			configProp = new Properties();
        	configProp.load(new InputStreamReader(RSXServiceCXFServer.class.getClassLoader().getResourceAsStream("conf/webservice-server.properties")));
        	
        	url = configProp.getProperty("restful.webservice.uri");
        		
		} catch (IOException e) {
			LOGGER.error(ExceptionUtils.printStackTraceToString(e));
        }
	
		// use the correct service implementation woven with the corresponding transaction aspect
		RSXService service = new RSXServiceImpl(new XServiceMybatisImpl());
		
		// start REST server jetty
		JAXRSServerFactoryBean RSserver = new JAXRSServerFactoryBean(); 
		RSserver.setServiceBean(service); 
        RSserver.setAddress(url);  
        RSserver.setProvider(new JacksonJaxbJsonProvider());
        RSserver.create();  
	}
}
