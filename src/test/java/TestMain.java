
import java.sql.SQLException;
import java.util.List;

import ningyuan.pan.servicex.persistence.dao.UserDAO;
import ningyuan.pan.servicex.persistence.dao.impl.UserDAOJDBCImpl;
import ningyuan.pan.servicex.persistence.entity.User;
import ningyuan.pan.util.persistence.JDBCDataSourceUtils;

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
		
		JDBCDataSourceUtils.initAndGetThreadLocalConnection();
		
		UserDAO dao = new UserDAOJDBCImpl(false);
		
		List<User> list = dao.findAllUser();
		
		System.out.println("getAllUser():");
		for(User user : list) {
			System.out.println(user);
		}
		
		User user = dao.findUserByID(0);
		
		System.out.println("getUserByID():\n"+user);
		
		JDBCDataSourceUtils.removeThreadLocalConnection();
	}

}
