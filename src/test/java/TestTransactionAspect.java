import java.sql.Connection;

import javax.persistence.EntityManager;

import org.apache.ibatis.session.SqlSession;

import ningyuan.pan.servicex.XService;
import ningyuan.pan.servicex.impl.XServiceDAOImpl;
import ningyuan.pan.servicex.impl.XServiceMybatisImpl;
import ningyuan.pan.servicex.persistence.dao.RoleDAO;
import ningyuan.pan.servicex.persistence.dao.UserDAO;
import ningyuan.pan.servicex.persistence.dao.impl.RoleDAOJDBCImpl;
import ningyuan.pan.servicex.persistence.dao.impl.RoleDAOJPAImpl;
import ningyuan.pan.servicex.persistence.dao.impl.UserDAOJDBCImpl;
import ningyuan.pan.servicex.persistence.dao.impl.UserDAOJPAImpl;
import ningyuan.pan.servicex.util.GlobalObjectName;
import ningyuan.pan.servicex.util.ServiceXUtil;
import ningyuan.pan.servicex.webservice.rs.RSXService;
import ningyuan.pan.servicex.webservice.rs.impl.RSXServiceImpl;
import ningyuan.pan.util.persistence.DataSourceManager;
import ningyuan.pan.util.persistence.JDBCDataSourceManager;
import ningyuan.pan.util.persistence.JPADataSourceManager;
import ningyuan.pan.util.persistence.MybatisDataSourceManager;

/**
 * 
 */

/**
 * @author ningyuan
 *
 */
public class TestTransactionAspect {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*RSXService service = new RSXServiceImpl(new XServiceDAOImpl());
		
		service.getUser();
		
		service.getName();*/
		
		/*DataSourceManager<Connection> dataSourceManager = new JDBCDataSourceManager();
		ServiceXUtil.getInstance().setGelobalObject(GlobalObjectName.JDBC_DATA_SOURCE_MANAGER, dataSourceManager);
		UserDAO userDAO = new UserDAOJDBCImpl(dataSourceManager);
		RoleDAO roleDAO = new RoleDAOJDBCImpl(dataSourceManager);
		
		XService xService = new XServiceDAOImpl(userDAO, roleDAO);
		
		System.out.println("!!!"+xService.getName());*/
		
		/*DataSourceManager<EntityManager> dataSourceManager = new JPADataSourceManager();
		ServiceXUtil.getInstance().setGelobalObject(GlobalObjectName.JPA_DATA_SOURCE_MANAGER, dataSourceManager);
		UserDAO userDAO = new UserDAOJPAImpl(dataSourceManager);
		RoleDAO roleDAO = new RoleDAOJPAImpl(dataSourceManager);
		
		XService xService = new XServiceDAOImpl(userDAO, roleDAO);
		
		System.out.println("!!!"+xService.getName());*/
		
		DataSourceManager<SqlSession> dataSourceManager = new MybatisDataSourceManager();
		ServiceXUtil.getInstance().setGelobalObject(GlobalObjectName.MYBATIS_DATA_SOURCE_MANAGER, dataSourceManager);
		
		XService xService = new XServiceMybatisImpl();
		
		System.out.println("!!!"+xService.getName());
	}
}
