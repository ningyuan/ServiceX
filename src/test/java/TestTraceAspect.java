import ningyuan.pan.servicex.impl.ServiceXImpl;
import ningyuan.pan.servicex.ServiceX;

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
		
		ServiceX service = new ServiceXImpl();
		
		service.getName();
		
		ServiceXImpl serviceImpl = new ServiceXImpl();
		
		serviceImpl.getName();
	}
}
