/**
 * 
 */
package ningyuan.pan.servicex.impl;

import java.sql.Connection;

import org.apache.ibatis.session.SqlSession;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ningyuan.pan.servicex.XService;
import ningyuan.pan.servicex.persistence.dao.UserDAO;
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
public class TestXServiceImpl {
	
	private Mockery context = new Mockery();

	private XService service;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// register jdbc data source as global variable, transaction aspect use this global variable
		//DataSourceManager<Connection> jdbcDataSourceManager = new JDBCDataSourceManager();
		//ServiceXUtil.getInstance().setGelobalObject(GlobalObjectName.JDBC_DATA_SOURCE_MANAGER, jdbcDataSourceManager);
		
		// register mybatis data source as global variable, transaction aspect use this global variable
		DataSourceManager<SqlSession> mybatisDataSourceManager = new MybatisDataSourceManager();
		ServiceXUtil.getInstance().setGelobalObject(GlobalObjectName.MYBATIS_DATA_SOURCE_MANAGER, mybatisDataSourceManager);
				
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
		UserDAO userDAO = context.mock(UserDAO.class);
		User returnValue = new User();
		returnValue.setFirstName("root");
		
		service = new XServiceJDBCImpl(userDAO);
		
		context.checking(new Expectations() {
			{
				oneOf(userDAO).findUserByID(0);
				will(returnValue(returnValue));
			}
		});
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
	public void testGetName() {
		Assert.assertEquals("root", service.getName());
	}

}
