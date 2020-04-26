/**
 * 
 */
package ningyuan.pan.servicex.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ningyuan.pan.servicex.XService;
import ningyuan.pan.servicex.persistence.dao.RoleDAO;
import ningyuan.pan.servicex.persistence.dao.UserDAO;
import ningyuan.pan.servicex.persistence.entity.Role;
import ningyuan.pan.servicex.persistence.entity.User;
import ningyuan.pan.servicex.util.GlobalObjectName;
import ningyuan.pan.servicex.util.ServiceXUtil;
import ningyuan.pan.util.persistence.DataSourceManager;
import ningyuan.pan.util.persistence.JDBCDataSourceManager;
import ningyuan.pan.util.persistence.MybatisDataSourceManager;


/**
 * @author ningyuan
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestXServiceDAOImpl {
	
	private Mockery context = new Mockery();

	private XService service;
	
	private UserDAO userDAO;
	
	private RoleDAO roleDAO;
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
		userDAO = context.mock(UserDAO.class);
		roleDAO = context.mock(RoleDAO.class);
		
		service = new XServiceDAOImpl(userDAO, roleDAO);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link ningyuan.pan.servicex.impl.ServiceXImpl#getName()}.
	 */
	@Test
	public void test01GetName() {
		User user = new User();
		user.setFirstName("pan");
		
		List<Role> roles = new ArrayList<Role>();
		
		Role role = new Role();
		role.setName("root");
		roles.add(role);
		
		role = new Role();
		role.setName("admin");
		roles.add(role);
		
		context.checking(new Expectations() {
			{
				oneOf(userDAO).findUserByID(0);
				will(returnValue(user));
				
				oneOf(roleDAO).findAllRole();
				will(returnValue(roles));
			}
		});
		
		Assert.assertEquals("pan root", service.getName());
		
		user.setFirstName("lee");
		context.checking(new Expectations() {
			{	
				// no sequence between these 2 excpectations
				oneOf(roleDAO).findAllRole();
				will(returnValue(roles));
				
				oneOf(userDAO).findUserByID(0);
				will(returnValue(user));
			}
		});
		
		Assert.assertEquals("lee root", service.getName());
	}
	
	@Test
	public void test02GetName() {
		
		context.checking(new Expectations() {
			{
				// parameter matcher
				oneOf(userDAO).findUserByID(with(equal(0L)));
				will(throwException(new Exception()));
			}
		});
		
		Assert.assertEquals("No user", service.getName());
	}
	
	@Test
	public void test03GetName() {
		User user = new User();
		user.setFirstName("pan");
		
		List<Role> roles = new ArrayList<Role>();
		
		Role role = new Role();
		role.setName("root");
		roles.add(role);
		
		role = new Role();
		role.setName("admin");
		roles.add(role);
		
		context.checking(new Expectations() {
			{	
				// 0 or more many times
				allowing(userDAO).findUserByID(0);
				will(returnValue(user));
				
				oneOf(roleDAO).findAllRole();
				will(returnValue(roles));
			}
		});
		
		Assert.assertEquals("pan root", service.getName());
		
		user.setFirstName("lee");
		context.checking(new Expectations() {
			{	
				// 0 or more times
				ignoring(userDAO).findUserByID(1);
				will(returnValue(user));
				
				// 0 time
				never(roleDAO).findRoleByID((byte)0);
				
				oneOf(roleDAO).findAllRole();
				will(returnValue(roles));
			}
		});
		
		Assert.assertEquals("lee root", service.getName());
	}
	
	@Test
	public void test04GetName() {
		final Sequence seq = context.sequence("seq");
		
		User user = new User();
		user.setFirstName("pan");
		
		List<Role> roles = new ArrayList<Role>();
		
		Role role = new Role();
		role.setName("root");
		roles.add(role);
		
		role = new Role();
		role.setName("admin");
		roles.add(role);
		
		context.checking(new Expectations() {
			{	
				// sequence of these 2 expectations is important
				
				// firstly findUserByID(0)
				oneOf(userDAO).findUserByID(0);
				inSequence(seq);
				will(returnValue(user));
				
				// secondly findAllRole()
				oneOf(roleDAO).findAllRole();
				inSequence(seq);
				will(returnValue(roles));
			}
		});
		
		Assert.assertEquals("pan root", service.getName());
	}
}
