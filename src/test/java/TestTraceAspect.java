import ningyuan.pan.servicex.impl.XServiceDAOImpl;
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
		
		XService service = new XServiceDAOImpl();
		
		service.getName();
		
		XServiceDAOImpl serviceImpl = new XServiceDAOImpl();
		
		serviceImpl.getName();
	}
}
