/**
 * 
 */
package ningyuan.pan.servicex.persistence.dao.impl;


import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ningyuan.pan.servicex.persistence.dao.RoleDAO;
import ningyuan.pan.servicex.persistence.entity.Role;

/**
 * @author ningyuan
 *
 */
public class TestRoleDAOMybatisImpl {
	
	private static RoleDAO DAO;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DAO = DAOMybatisUtil.getRoleDAO();
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

	@Test
	public void testFindAllRole() {
		/*List<Role> list = DAO.findAllRole();
		
		for(Role role : list) {
			System.out.println(role);
		}*/
	}

	/*@Test(expected = PersistenceException.class)
	public void testAddDup() {
		Role role = new Role();
		role.setId((byte) 0);
		role.setName("non-root");
		
		DAO.add(role);
	}*/
	
	@Test
	public void testAdd() {
		/*Role role = new Role();
		role.setId((byte) 3);
		role.setName("testRole");
		
		DAO.add(role);*/
	}
	
	@Test
	public void testUpdate() {
		/*Role role = new Role();
		role.setId((byte) 3);
		role.setName("testRole1");
		
		DAO.update(role);*/
	}
	
	@Test
	public void testDelete() {
		//DAO.delete((byte)3);
	}
	
	@Test
	public void testUpdateNull() {
		/*Role role = new Role();
		role.setId((byte) 4);
		role.setName("testRole2");
		
		DAO.update(role);*/
	}
	
	@Test
	public void testDeleteNull() {
		//DAO.delete((byte)4);
	}
}
