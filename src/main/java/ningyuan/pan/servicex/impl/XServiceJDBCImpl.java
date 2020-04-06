/**
 * 
 */
package ningyuan.pan.servicex.impl;


import java.util.List;

import ningyuan.pan.servicex.XService;
import ningyuan.pan.servicex.persistence.dao.RoleDAO;
import ningyuan.pan.servicex.persistence.dao.UserDAO;
import ningyuan.pan.servicex.persistence.entity.Role;
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
	
	private RoleDAO roleDAO;
	
	public XServiceJDBCImpl() {}
	
	public XServiceJDBCImpl(UserDAO userDAO, RoleDAO roleDAO) {
		this.userDAO = userDAO;
		this.roleDAO = roleDAO;
	}
	
	public String getName() {
		if(userDAO != null) {
			try {
				User user = userDAO.findUserByID(0);
				List<Role> roles = roleDAO.findAllRole();
				return user.getFirstName()+ " "+roles.get(1).getName();
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
