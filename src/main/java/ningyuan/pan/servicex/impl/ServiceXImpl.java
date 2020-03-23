/**
 * 
 */
package ningyuan.pan.servicex.impl;


import java.util.Properties;

import ningyuan.pan.servicex.ServiceX;
import ningyuan.pan.servicex.persistence.dao.UserDAO;


/**
 * @author ningyuan
 *
 */
public class ServiceXImpl implements ServiceX {
	
	private UserDAO userDAO;
	
	public ServiceXImpl() {}
	
	public ServiceXImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public String getName() {
		if(userDAO != null) {
			return userDAO.findUserByID(0).getFirstName();
		}
		else {
			Properties configProp = null;

			try {
				configProp.clear();
			} 
			catch (NullPointerException npe) {
				
			}
			
			return "no user";
		}
	}
}
