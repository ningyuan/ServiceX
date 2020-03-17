
import java.util.List;

import ningyuan.pan.servicex.persistence.dao.UserDAO;
import ningyuan.pan.servicex.persistence.dao.impl.UserDAOJDBCImpl;
import ningyuan.pan.servicex.persistence.entity.User;

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
	 */
	public static void main(String[] args) {
		UserDAO dao = new UserDAOJDBCImpl();
		
		List<User> list = dao.getAllUser();
		
		System.out.println("getAllUser():");
		for(User user : list) {
			System.out.println(user);
		}
		
		User user = dao.getUserByID(0);
		
		System.out.println("getUserByID():\n"+user);
	}

}
