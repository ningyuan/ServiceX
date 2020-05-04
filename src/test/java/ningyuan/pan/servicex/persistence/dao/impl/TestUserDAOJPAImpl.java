/**
 * 
 */
package ningyuan.pan.servicex.persistence.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.RollbackException;

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
import ningyuan.pan.util.persistence.JPADataSourceManager;

/**
 * @author ningyuan
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserDAOJPAImpl {
	private static UserDAO DAO;
	
	private static DataSourceManager<EntityManager> DATA_SOURCE_MANAGER;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		/*DATA_SOURCE_MANAGER = new JPADataSourceManager();
	
		DAO = new UserDAOJPAImpl(DATA_SOURCE_MANAGER);*/
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//DATA_SOURCE_MANAGER.close();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//DATA_SOURCE_MANAGER.getOrInitThreadLocalConnection().getTransaction().begin();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		/*if(DATA_SOURCE_MANAGER.getThreadLocalConnection().getTransaction().isActive()) {
			try {
				DATA_SOURCE_MANAGER.getThreadLocalConnection().getTransaction().commit();
			}
			catch(RollbackException re) {}
		}*/
	}

	@Test
	public void test01GetUser() {
		/*User user = DAO.findUserByID(1L);
		
		System.out.println("!!!"+user);*/
	}
	
	@Test
	public void test02GetAllUser() {
		/*List<User> users = DAO.findAllUser();
		for(User user : users) {
			System.out.println("!!!"+user);
		}*/
	}
	
	@Test
	public void test03AddDup() {
		/*User user = new User();
		user.setID(0L);
		user.setFirstName("test");
		user.setLastName("test");
		
		assertFalse(DAO.add(user));*/
	}
	
	/*@Test(expected = AssertionError.class)
	public void test04AddNoCascade() {
		User user = new User();
		user.setID(100L);
		user.setFirstName("test");
		user.setLastName("test");
		
		// a role with id 100 dose not exist
		// the user will be not added
		// no cascade persist
		Role role = new Role();
		role.setID((byte)100);
		role.setName("test");
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		
		user.setRoles(roles);
		
		assertFalse(DAO.add(user));
	}*/
	
	@Test
	public void test05AddNoCascade() {
		/*User user = new User();
		user.setID(100L);
		user.setFirstName("test");
		user.setLastName("test");
		
		Role role = new Role();
		// the role admin exists
		role.setID(RoleType.ADMIN.getID());
		// the name will be not updated
		role.setName("test");
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		
		user.setRoles(roles);
		
		assertTrue(DAO.add(user));*/
	}
	
	@Test
	public void test06Delete() {
		//assertTrue(DAO.delete(100L));
	} 
	
	@Test
	public void test07Add() {
		/*User user = new User();
		user.setID(101L);
		user.setFirstName("test");
		user.setLastName("test");
		
		List<Role> roles = new ArrayList<Role>();
		
		Role role = new Role();
		// the role admin exists
		role.setID(RoleType.ADMIN.getID());
		
		roles.add(role);
		
		role = new Role();
		// the role common exists
		role.setID(RoleType.COMMON.getID());
		
		roles.add(role);
		
		user.setRoles(roles);
		
		assertTrue(DAO.add(user));*/
	}
	
	/*@Test(expected = EntityNotFoundException.class)
	public void test08UpdateNoCascade() {
		User user = new User();
		user.setID(101L);
		user.setFirstName("test1");
		user.setLastName("test1");
		
		Role role = new Role();
		// a role with id 100 dose not exist
		// the user will be not updated
		// no cascade merge
		role.setID((byte)100);
		role.setName("test1");
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		
		user.setRoles(roles);
		
		DAO.update(user);
	}*/
	
	@Test
	public void test09UpdateNoCascade() {
		/*User user = new User();
		user.setID(101L);
		user.setFirstName("test1");
		user.setLastName("test1");
		
		Role role = new Role();
		// the role admin exists
		role.setID(RoleType.ADMIN.getID());
		// the name will not be updated
		// no cascade merge
		role.setName("test1");
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		
		user.setRoles(roles);
		
		assertTrue(DAO.update(user));*/
	}
	
	@Test
	public void test09Delete() {
		//assertTrue(DAO.delete(101L));
	} 
	
	@Test
	public void test10FindUsersByRole() {
		/*List<User> users = DAO.findAllUserByRole(RoleType.ADMIN.getID());
		
		for(User user : users) {
			System.out.println("!!!"+user);
		}
		
		users = DAO.findAllUserByRole(RoleType.TEST.getID());
		
		for(User user : users) {
			System.out.println("!!!"+user);
		}
		
		users = DAO.findAllUserByRole((byte)100);
		
		for(User user : users) {
			System.out.println("!!!"+user);
		}*/
	}
}
