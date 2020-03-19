
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ningyuan.pan.servicex.persistence.dao.UserDAO;
import ningyuan.pan.servicex.persistence.dao.impl.UserDAOJDBCImpl;
import ningyuan.pan.servicex.persistence.entity.User;
import ningyuan.pan.util.persistence.DataSourceManager;
import ningyuan.pan.util.persistence.JDBCDataSourceManager;

/**
 * 
 */

/**
 * @author ningyuan
 *
 */
public class TestMain {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
		/*
		 *  each data source manager has its own thread local variable
		 */
		
		DataSourceManager<Connection> dataSourceManager = new JDBCDataSourceManager();
		DataSourceManager<Connection> dataSourceManager1 = new JDBCDataSourceManager();
		
		UserDAO dao = new UserDAOJDBCImpl(dataSourceManager, false);
		UserDAO dao1 = new UserDAOJDBCImpl(dataSourceManager1, true);
		
		List<User> list = dao.findAllUser();
		
		System.out.println("dao.getAllUser():");
		for(User user : list) {
			System.out.println(user);
		}
		
		list = dao1.findAllUser();
		
		System.out.println("dao1.getAllUser():");
		for(User u : list) {
			System.out.println(u);
		}
		
		User user = dao.findUserByID(0);
		
		System.out.println("dao.getUserByID():\n"+user);
		
		User user1 = dao1.findUserByID(0);
		
		System.out.println("dao1.getUserByID():\n"+user1);
		
		dataSourceManager.removeThreadLocalConnection();
	
		dataSourceManager1.removeThreadLocalConnection();
	}
}
