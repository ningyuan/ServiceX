/**
 * 
 */
package ningyuan.pan.servicex.persistence.dao.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ningyuan.pan.servicex.persistence.dao.UserDAO;
import ningyuan.pan.servicex.persistence.entity.Role;
import ningyuan.pan.servicex.persistence.entity.User;
import ningyuan.pan.util.persistence.DataSourceManager;
import ningyuan.pan.util.persistence.MybatisDataSourceManager;

/**
 * @author ningyuan
 *
 */
public class TestUserDAOMybatisImpl {
	
	private UserDAO dao;
	
	private static DataSourceManager<SqlSession> DATA_SOURCE_MANAGER;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DATA_SOURCE_MANAGER = new MybatisDataSourceManager();
		DATA_SOURCE_MANAGER.initAndGetThreadLocalConnection();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		DATA_SOURCE_MANAGER.removeAndCloseThreadLocalConnection();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// start transaction
		dao = DATA_SOURCE_MANAGER.getThreadLocalConnection().getMapper(UserDAO.class);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		// commit transaction
		DATA_SOURCE_MANAGER.getThreadLocalConnection().commit();
	}

	/**
	 * Test method for {@link ningyuan.pan.servicex.persistence.dao.impl.UserDAOMybatisImpl#findAllUser()}.
	 */
	@Test
	public void testFindAllUser() {
		/*List<User> list = dao.findAllUser();
		
		for(User user : list) {
			System.out.println(user);
		}*/
	}

	/**
	 * Test method for {@link ningyuan.pan.servicex.persistence.dao.impl.UserDAOMybatisImpl#findUserByID(long)}.
	 */
	@Test
	public void testFindUserByID() {
		//System.out.println(dao.findUserByID(0));
	}
	
	@Test
	public void testAdd() {
		/*try {
			User user = new User();
			
			user.setID(3l);
			user.setFirstName("one");
			user.setLastName("zhang");
			
			List<Role> roles = new ArrayList<Role>();
			
			Role role = new Role();
			role.setId((byte)1);
			roles.add(role);
			
			role = new Role();
			role.setId((byte)2);
			roles.add(role);
			
			user.setRoles(roles);
			
			dao.add(user);
		}
		catch(Exception e) {
			e.printStackTrace();
			DATA_SOURCE_MANAGER.getThreadLocalConnection().rollback();
		}*/
	}
	
	@Test
	public void testUpdate() {
		/*try {
			User user = new User();
			
			user.setID(3l);
			user.setFirstName("two");
			user.setLastName("zhang");
			
			List<Role> roles = new ArrayList<Role>();
			
			Role role = new Role();
			
			role.setId((byte)2);
			roles.add(role);
			
			user.setRoles(roles);
			
			dao.update(user);
		}
		catch(Exception e) {
			e.printStackTrace();
			DATA_SOURCE_MANAGER.getThreadLocalConnection().rollback();
		}*/
	}
	
	@Test
	public void testDelete() {
		//dao.delete(3l);
	}
}
