/**
 * 
 */
package ningyuan.pan.servicex.persistence.dao.impl;


import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ningyuan.pan.servicex.persistence.dao.RoleDAO;
import ningyuan.pan.servicex.persistence.dao.UserDAO;
import ningyuan.pan.servicex.persistence.entity.Role;


/**
 * @author ningyuan
 *
 */
public class TestRoleDAOMybatisImpl {
	
	private SqlSession session;
	
	private RoleDAO dao;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
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
		session = MybatisUtil.getSession();
		dao = session.getMapper(RoleDAO.class);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		session.commit();
		session.close();
	}

	@Test
	public void testFindAllRole() {
		/*List<Role> list = dao.findAllRole();
		
		for(Role role : list) {
			System.out.println(role);
		}*/
	}

	/*@Test(expected = PersistenceException.class)
	public void testAddDup() {
		Role role = new Role();
		role.setId((byte) 0);
		role.setName("non-root");
		
		dao.add(role);
	}*/
	
	@Test
	public void testAdd() {
		/*Role role = new Role();
		role.setId((byte) 3);
		role.setName("testRole");
		
		dao.add(role);*/
	}
	
	@Test
	public void testUpdate() {
		/*Role role = new Role();
		role.setId((byte) 3);
		role.setName("testRole1");
		
		dao.update(role);*/
	}
	
	@Test
	public void testDelete() {
		//dao.delete((byte)3);
	}
	
	@Test
	public void testUpdateNull() {
		/*Role role = new Role();
		role.setId((byte) 4);
		role.setName("testRole2");
		
		dao.update(role);*/
	}
	
	@Test
	public void testDeleteNull() {
		//dao.delete((byte)4);
	}
}