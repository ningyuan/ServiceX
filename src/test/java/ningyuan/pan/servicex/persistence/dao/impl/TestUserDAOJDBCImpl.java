/**
 * 
 */
package ningyuan.pan.servicex.persistence.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import ningyuan.pan.util.persistence.JDBCDataSourceManager;

/**
 * @author ningyuan
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserDAOJDBCImpl {
	
	private static UserDAO DAO;
	
	private static DataSourceManager<Connection> DATA_SOURCE_MANAGER;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DATA_SOURCE_MANAGER = new JDBCDataSourceManager();
		
		// set auto close to false to use transaction
		DAO = new UserDAOJDBCImpl(DATA_SOURCE_MANAGER, false);	
		
		// comment it out when no data source is started
		//DATA_SOURCE_MANAGER.getOrInitThreadLocalConnection();
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
		Connection con = DATA_SOURCE_MANAGER.getThreadLocalConnection();
		
		if(con != null) {
			con.setAutoCommit(false);
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		// commit transaction
		Connection con = DATA_SOURCE_MANAGER.getThreadLocalConnection();
		
		if(con != null) {
			DATA_SOURCE_MANAGER.getThreadLocalConnection().commit();
		}	
	}

	/**
	 * Test method for {@link ningyuan.pan.servicex.persistence.dao.impl.UserDAOJDBCImpl#getAllUser()}.
	 */
	@Test
	public void test01GetAllUser() {
		/*List<User> users = DAO.findAllUser();
		for(User user : users) {
			System.out.println(user);
		}*/
	}

	/**
	 * Test method for {@link ningyuan.pan.servicex.persistence.dao.impl.UserDAOJDBCImpl#getUserByID(long)}.
	 */
	@Test
	public void test02GetUserByID() {
		/*User user = DAO.findUserByID(0L);
		
		System.out.println(user);*/
	}
	
	@Test
	public void test03Add() {
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
			
		if(!DAO.add(user)) {
			try {
				DATA_SOURCE_MANAGER.getThreadLocalConnection().rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
	}
	
	@Test
	public void test04Update() {
		
		/*User user = new User();
		
		user.setID(100l);
		user.setFirstName("two");
		user.setLastName("zhang");
			
		List<Role> roles = new ArrayList<Role>();
		
		Role role = new Role();
			
		role.setID(RoleType.COMMON.getID());
		roles.add(role);
			
		user.setRoles(roles);
			
		if(!DAO.update(user)) {
			try {
				DATA_SOURCE_MANAGER.getThreadLocalConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}*/
	}
	
	@Test
	public void test05Delete() {
		/*if(!DAO.delete(100l)) {
			try {
				DATA_SOURCE_MANAGER.getThreadLocalConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}*/
	}
}
