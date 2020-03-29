import ningyuan.pan.servicex.impl.XServiceImpl;
import ningyuan.pan.servicex.webservice.rs.RSXService;
import ningyuan.pan.servicex.webservice.rs.impl.RSXServiceImpl;

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
		RSXService service = new RSXServiceImpl(new XServiceImpl());
		
		service.getUser();
		
		service.getName();
		
	}
}
