/**
 * 
 */
package ningyuan.pan.servicex.persistence.dao.impl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ningyuan.pan.servicex.persistence.dao.UserDAO;
import ningyuan.pan.util.persistence.JDBCDataSourceUtils;

/**
 * @author ningyuan
 *
 */
public class TestUserDAOJDBCImpl {
	
	private static UserDAO DAO;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DAO = new UserDAOJDBCImpl(false);
		JDBCDataSourceUtils.initAndGetThreadLocalConnection();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		JDBCDataSourceUtils.removeThreadLocalConnection();
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
	 * Test method for {@link ningyuan.pan.servicex.persistence.dao.impl.UserDAOJDBCImpl#getAllUser()}.
	 */
	@Test
	public void testGetAllUser() {
		//DAO.findAllUser();
	}

	/**
	 * Test method for {@link ningyuan.pan.servicex.persistence.dao.impl.UserDAOJDBCImpl#getUserByID(long)}.
	 */
	@Test
	public void testGetUserByID() {
		//DAO.findUserByID(0);
	}

}
