/**
 * 
 */
package ningyuan.pan.servicex.impl;


import ningyuan.pan.servicex.XService;
import ningyuan.pan.servicex.persistence.dao.UserDAO;
import ningyuan.pan.servicex.persistence.entity.User;


/**
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
