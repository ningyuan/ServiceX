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
import org.junit.Test;

import ningyuan.pan.servicex.persistence.dao.UserDAO;
import ningyuan.pan.servicex.persistence.entity.Role;
import ningyuan.pan.servicex.persistence.entity.User;
import ningyuan.pan.util.persistence.DataSourceManager;
import ningyuan.pan.util.persistence.JDBCDataSourceManager;

/**
 * @author ningyuan
 *
 */
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
		//DATA_SOURCE_MANAGER.initAndGetThreadLocalConnection();
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
	
	@Test
	public void testAdd() {
		/*User user = new User();
			
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
			
		if(!DAO.add(user)) {
			try {
				DATA_SOURCE_MANAGER.getThreadLocalConnection().rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
	}
	
	@Test
	public void testUpdate() {
		
		/*User user = new User();
		
		user.setID(3l);
		user.setFirstName("two");
		user.setLastName("zhang");
			
		List<Role> roles = new ArrayList<Role>();
		
		Role role = new Role();
			
		role.setId((byte)2);
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
	public void testDelete() {
		/*if(!DAO.delete(3l)) {
			try {
				DATA_SOURCE_MANAGER.getThreadLocalConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}*/
	}
}
