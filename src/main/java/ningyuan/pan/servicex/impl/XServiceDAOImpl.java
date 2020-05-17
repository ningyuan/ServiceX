/**
 * 
 */
package ningyuan.pan.servicex.impl;


import java.util.ArrayList;
import java.util.List;

import ningyuan.pan.servicex.XService;
import ningyuan.pan.servicex.jms.XASender;
import ningyuan.pan.servicex.persistence.dao.RoleDAO;
import ningyuan.pan.servicex.persistence.dao.UserDAO;
import ningyuan.pan.servicex.persistence.entity.Role;
import ningyuan.pan.servicex.persistence.entity.User;
import ningyuan.pan.util.text.TextObjectConverter;
import ningyuan.pan.util.text.TextObjectConverterFactory;


/**
 * The service using DAO implemented with JDBC or JPA or ... . All public methods in 
 * service interface will be woven with transaction codes. Pay attention to weaving this 
 * service implementation with the corresponding transaction aspect (JDBCTransactionAspect
 * or JPATransactionAspect or ...).
 * 
 * @author ningyuan
 *
 */
public class XServiceDAOImpl implements XService {
	
	private UserDAO userDAO;
	
	private RoleDAO roleDAO;
	
	public XServiceDAOImpl() {}
	
	public XServiceDAOImpl(UserDAO userDAO, RoleDAO roleDAO) {
		this.userDAO = userDAO;
		this.roleDAO = roleDAO;
	}
	
	public String getName() {
		String msg = null;
		
		if(userDAO != null && roleDAO != null) {
			try {
				User user = userDAO.findUserByID(0);
				List<Role> roles = roleDAO.findAllRole();
				
				msg = user.getFirstName()+ " "+roles.get(0).getName();
			}
			catch(Exception e) {
				msg = "No user";
			}
		}
		else {
			msg = "No user";
		}
		
		
		List<String> msgs = new ArrayList<String>();
		msgs.add(msg);
		
		try {
			XASender sender = new XASender("conf/activemq.properties");
			sender.sendMessages(msgs, "activemq.artemis.queue");
		}
		catch (Exception e) {}
		
		return msg;
	}

	@Override
	public String getAllUsers(String format) {
		TextObjectConverter converter = TextObjectConverterFactory.newInstance(format);
		
		if(userDAO != null) {
			List<User> users = userDAO.findAllUser();
			
			return converter.marshall(users);
		}
		else {
			return converter.getDefaultText(new ArrayList<>());
		}
	}

	@Override
	public String getAllRoles(String format) {
		TextObjectConverter converter = TextObjectConverterFactory.newInstance(format);
		
		if(userDAO != null && roleDAO != null) {
			List<RoleWithUser> ret = new ArrayList<RoleWithUser>();
			
			List<Role> roles = roleDAO.findAllRole();
			
			for(Role role : roles) {
				RoleWithUser rwu = new RoleWithUser();
				rwu.setID(role.getID());
				rwu.setName(role.getName());
				rwu.setUsers(userDAO.findAllUserByRole(role.getID()));
				
				ret.add(rwu);
			}
			 
			return converter.marshall(ret);
		}
		else {
			return converter.getDefaultText(new ArrayList<>());
		}
	}
}
