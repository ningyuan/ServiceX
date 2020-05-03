/**
 * 
 */
package ningyuan.pan.servicex.persistence.dao.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ningyuan.pan.servicex.persistence.dao.UserDAO;
import ningyuan.pan.servicex.persistence.entity.Role;
import ningyuan.pan.servicex.persistence.entity.RoleType;
import ningyuan.pan.servicex.persistence.entity.User;
import ningyuan.pan.util.persistence.DataSourceManager;
import ningyuan.pan.util.persistence.MybatisDataSourceManager;

/**
 * @author ningyuan
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserDAOMybatisImpl {
	
	private UserDAO dao;
	
	private static DataSourceManager<SqlSession> DATA_SOURCE_MANAGER;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DATA_SOURCE_MANAGER = new MybatisDataSourceManager();
		DATA_SOURCE_MANAGER.getOrInitThreadLocalConnection();
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
	public void test01FindAllUser() {
		/*List<User> list = dao.findAllUser();
		
		for(User user : list) {
			System.out.println(user);
		}*/
	}

	/**
	 * Test method for {@link ningyuan.pan.servicex.persistence.dao.impl.UserDAOMybatisImpl#findUserByID(long)}.
	 */
	@Test
	public void test02FindUserByID() {
		//System.out.println(dao.findUserByID(0));
	}
	
	/*@Test(expected = PersistenceException.class)
	public void test03AddNoCascade() {
		try {
			User user = new User();
			
			user.setID(100l);
			user.setFirstName("one");
			user.setLastName("zhang");
			
			List<Role> roles = new ArrayList<Role>();
			
			Role role = new Role();
			role.setID(RoleType.ADMIN.getID());
			roles.add(role);
			
			role = new Role();
			role.setID(RoleType.COMMON.getID());
			roles.add(role);
			
			// a role with id 100 dose not exist
			// user will not be added
			role = new Role();
			role.setID((byte)100);
			role.setName("test");
			roles.add(role);
			
			user.setRoles(roles);
			
			dao.add(user);
		}
		finally {
			DATA_SOURCE_MANAGER.getThreadLocalConnection().rollback();
		}
	}*/
	
	@Test
	public void test04Add() {
		
		/*User user = new User();
			
		user.setID(100l);
		user.setFirstName("one");
		user.setLastName("zhang");
			
		List<Role> roles = new ArrayList<Role>();
			
		Role role = new Role();
		role.setID(RoleType.ADMIN.getID());
		roles.add(role);
			
		role = new Role();
		role.setID(RoleType.COMMON.getID());
		roles.add(role);
			
		
		user.setRoles(roles);
			
		dao.add(user);*/
	}
	
	@Test
	public void test05Update() {
		
		/*User user = new User();
			
		user.setID(100l);
		user.setFirstName("two");
		user.setLastName("zhang");
			
		List<Role> roles = new ArrayList<Role>();
			
		Role role = new Role();
			
		role.setID(RoleType.GUEST.getID());
		roles.add(role);
			
		user.setRoles(roles);
			
		dao.update(user);*/
	}
	
	@Test
	public void test05Delete() {
		//dao.delete(100l);
	}
}
