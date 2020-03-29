import ningyuan.pan.servicex.impl.ServiceXImpl;
import ningyuan.pan.servicex.XService;

/**
 * 
 */

/**
 * @author ningyuan
 *
 */
public class TestTraceAspect {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		XService service = new ServiceXImpl();
		
		service.getName();
		
		ServiceXImpl serviceImpl = new ServiceXImpl();
		
		serviceImpl.getName();
	}
}
