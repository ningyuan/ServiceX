/**
 * 
 */
package ningyuan.pan.servicex.impl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ningyuan.pan.servicex.ServiceX;
import ningyuan.pan.servicex.util.ServiceXUtil;
import ningyuan.pan.util.persistence.JDBCDataSourceManager;

/**
 * @author ningyuan
 *
 */
public class TestServiceXImpl {
	
	private static ServiceX SERVICE_X;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//ServiceXUtil.getInstance().setGelobalObject("JDBCDSM", new JDBCDataSourceManager());
		SERVICE_X = new ServiceXImpl();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//ServiceXUtil.getInstance().removeGelobalObject("JDBCDSM");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link ningyuan.pan.servicex.impl.ServiceXImpl#getName()}.
	 */
	@Test
	public void testGetName() {
		Assert.assertEquals("ServiceX", SERVICE_X.getName());
	}

}
