import ningyuan.pan.servicex.impl.ServiceXImpl;
import ningyuan.pan.servicex.webservice.rs.ServiceXRS;
import ningyuan.pan.servicex.webservice.rs.impl.ServiceXRSImpl;

/**
 * 
 */

/**
 * @author ningyuan
 *
 */
public class TestTransactionAspect {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServiceXRS service = new ServiceXRSImpl(new ServiceXImpl());
		
		service.getUser();
		
		service.getName();
		
	}
}
