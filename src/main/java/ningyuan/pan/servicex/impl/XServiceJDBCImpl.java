/**
 * 
 */
package ningyuan.pan.servicex.impl;


import ningyuan.pan.servicex.XService;
import ningyuan.pan.servicex.persistence.dao.UserDAO;
import ningyuan.pan.servicex.persistence.entity.User;


/**
 * The service using JDBC DAO. All public methods in service interface will be
 * woven with transaction codes. Pay attention to weaving this service implementation
 * with the corresponding transaction aspect (JDBCTransactionAspect).
 * 
 * @author ningyuan
 *
 */
public class XServiceJDBCImpl implements XService {
	
	private UserDAO userDAO;
	
	public XServiceJDBCImpl() {}
	
	public XServiceJDBCImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public String getName() {
		if(userDAO != null) {
			try {
				User user = userDAO.findUserByID(0);
				return user.getFirstName();
			}
			catch(Exception e) {
				return "No user";
			}
		}
		else {
			return "No user";
		}
	}
}
