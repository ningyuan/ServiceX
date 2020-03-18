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

/**
 * @author ningyuan
 *
 */
public class TestUserDAOJDBCImpl {
	
	private static UserDAO dao;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new UserDAOJDBCImpl();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
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
		//dao.findAllUser();
	}

	/**
	 * Test method for {@link ningyuan.pan.servicex.persistence.dao.impl.UserDAOJDBCImpl#getUserByID(long)}.
	 */
	@Test
	public void testGetUserByID() {
		//dao.findUserByID(0);
	}

}
