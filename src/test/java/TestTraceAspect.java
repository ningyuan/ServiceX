import ningyuan.pan.servicex.impl.XServiceJDBCImpl;
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
		
		XService service = new XServiceJDBCImpl();
		
		service.getName();
		
		XServiceJDBCImpl serviceImpl = new XServiceJDBCImpl();
		
		serviceImpl.getName();
	}
}
