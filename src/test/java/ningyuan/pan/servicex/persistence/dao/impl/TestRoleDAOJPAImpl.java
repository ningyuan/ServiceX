/**
 * 
 */
package ningyuan.pan.servicex.persistence.dao.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ningyuan.pan.servicex.persistence.dao.RoleDAO;
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
public class TestRoleDAOJPAImpl {
	
	private static RoleDAO DAO;
	
	private static DataSourceManager<EntityManager> DATA_SOURCE_MANAGER;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		/*DATA_SOURCE_MANAGER = new JPADataSourceManager();
		
		DAO = new RoleDAOJPAImpl(DATA_SOURCE_MANAGER);*/
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
	public void test01GetRole() {
		/*Role role = DAO.findRoleByID((byte)2);
		System.out.println("!!!"+role);*/
	}
	
	@Test
	public void test02GetAllRole() {
		/*List<Role> roles = DAO.findAllRole();
		for(Role role : roles) {
			System.out.println("!!!"+role);
		}*/
	}
	
	@Test
	public void test03AddDup() {
		/*Role role = new Role();
		role.setID(RoleType.ROOT.getID());
		role.setName("non-root");
		
		assertFalse(DAO.add(role));*/
	}
	
	@Test
	public void test04Add() {
		/*Role role = new Role();
		role.setID((byte) 100);
		role.setName("test");
		
		assertTrue(DAO.add(role));*/
	}
	
	@Test
	public void test05Update() {
		/*Role role = new Role();
		role.setID((byte) 100);
		role.setName("test1");
		
		assertTrue(DAO.update(role));*/
	}
	
	@Test
	public void test06Delete() {
		//assertTrue(DAO.delete((byte)100));
	}
}
