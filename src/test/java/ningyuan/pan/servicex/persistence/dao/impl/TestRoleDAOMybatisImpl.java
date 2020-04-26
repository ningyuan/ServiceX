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
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ningyuan.pan.servicex.persistence.dao.RoleDAO;
import ningyuan.pan.servicex.persistence.dao.UserDAO;
import ningyuan.pan.servicex.persistence.entity.Role;
import ningyuan.pan.util.persistence.DataSourceManager;
import ningyuan.pan.util.persistence.MybatisDataSourceManager;


/**
 * @author ningyuan
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRoleDAOMybatisImpl {
	
	private RoleDAO dao;
	
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
		dao = DATA_SOURCE_MANAGER.getThreadLocalConnection().getMapper(RoleDAO.class);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		DATA_SOURCE_MANAGER.getThreadLocalConnection().commit();
	}

	@Test
	public void test01FindAllRole() {
		/*List<Role> list = dao.findAllRole();
		
		for(Role role : list) {
			System.out.println(role);
		}*/
	}

	/*@Test(expected = PersistenceException.class)
	public void test02AddDup() {
		Role role = new Role();
		role.setID((byte) 0);
		role.setName("non-root");
		
		dao.add(role);
	}*/
	
	@Test
	public void test03Add() {
		/*Role role = new Role();
		role.setID((byte) 3);
		role.setName("testRole");
		
		dao.add(role);*/
	}
	
	@Test
	public void test04Update() {
		/*Role role = new Role();
		role.setID((byte) 3);
		role.setName("testRole1");
		
		dao.update(role);*/
	}
	
	@Test
	public void test05Delete() {
		//dao.delete((byte)3);
	}
	
	@Test
	public void test06UpdateNull() {
		/*Role role = new Role();
		role.setID((byte) 4);
		role.setName("testRole2");
		
		dao.update(role);*/
	}
	
	@Test
	public void test07DeleteNull() {
		//dao.delete((byte)4);
	}
}
