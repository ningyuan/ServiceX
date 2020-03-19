/**
 * 
 */
package ningyuan.pan.servicex.persistence.dao.impl;


import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ningyuan.pan.servicex.persistence.dao.UserDAO;
import ningyuan.pan.servicex.persistence.entity.User;
import ningyuan.pan.util.persistence.MybatisDataSourceUtils;

/**
 * @author ningyuan
 *
 */
public class TestUserDAOMybatisImpl {
	
	private UserDAO dao;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MybatisDataSourceUtils.initAndGetThreadLocalConnection();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		MybatisDataSourceUtils.removeThreadLocalConnection();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = MybatisDataSourceUtils.initAndGetThreadLocalConnection().getMapper(UserDAO.class);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		MybatisDataSourceUtils.initAndGetThreadLocalConnection().commit();
	}

	/**
	 * Test method for {@link ningyuan.pan.servicex.persistence.dao.impl.UserDAOMybatisImpl#findAllUser()}.
	 */
	@Test
	public void testFindAllUser() {
		List<User> list = dao.findAllUser();
		
		for(User user : list) {
			System.out.println(user);
		}
	}

	/**
	 * Test method for {@link ningyuan.pan.servicex.persistence.dao.impl.UserDAOMybatisImpl#findUserByID(long)}.
	 */
	@Test
	public void testFindUserByID() {
		System.out.println(dao.findUserByID(0));
	}

}
