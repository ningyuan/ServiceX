import ningyuan.pan.servicex.impl.XServiceImpl;
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
		
		XService service = new XServiceImpl();
		
		service.getName();
		
		XServiceImpl serviceImpl = new XServiceImpl();
		
		serviceImpl.getName();
	}
}
