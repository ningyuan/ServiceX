/**
 * 
 */
package ningyuan.pan.servicex.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import ningyuan.pan.servicex.XService;
import ningyuan.pan.servicex.jms.Sender;
import ningyuan.pan.servicex.persistence.dao.RoleDAO;
import ningyuan.pan.servicex.persistence.dao.UserDAO;
import ningyuan.pan.servicex.persistence.entity.Role;
import ningyuan.pan.servicex.persistence.entity.User;
import ningyuan.pan.servicex.util.GlobalObjectName;
import ningyuan.pan.servicex.util.ServiceXUtil;
import ningyuan.pan.util.persistence.DataSourceManager;
import ningyuan.pan.util.text.TextObjectConverter;
import ningyuan.pan.util.text.TextObjectConverterFactory;

/**
 * The service using Mybatis session. All public methods in service interface will be
 * woven with transaction codes. Pay attention to weaving this service implementation
 * with the corresponding transaction aspect (MybatisTransactionAspect).
 * 
 * @author ningyuan
 *
 */
public class XServiceMybatisImpl implements XService {
	
	public String getName() {
		@SuppressWarnings("unchecked")
		DataSourceManager<SqlSession> dataSourceManager = (DataSourceManager<SqlSession>)ServiceXUtil.getInstance().getGelobalObject(GlobalObjectName.MYBATIS_DATA_SOURCE_MANAGER);
		
		String msg = null;
		if(dataSourceManager != null) {
			try {
				// transaction aspect will weave the code which initiates the thread local connection
				// so we can use get instead of getOrInit
				UserDAO userDAO = dataSourceManager.getThreadLocalConnection().getMapper(UserDAO.class);
				User user = userDAO.findUserByID(0);
				
				RoleDAO roleDAO = dataSourceManager.getThreadLocalConnection().getMapper(RoleDAO.class);
				List<Role> roles = roleDAO.findAllRole();
				
				msg = user.getFirstName()+" "+roles.get(0).getName();
			}
			catch(Exception e) {
				msg = "No user";
			}
		}
		else {
			msg = "No data source manager set in context";
		}
		
		List<String> msgs = new ArrayList<String>();
		msgs.add(msg);
		
		Sender sender = new Sender("conf/activemq.properties");
		sender.sendMessages(msgs, "activemq.artemis.queue");
		
		return msg;
	}


	@Override
	public String getAllUsers(String format) {
		@SuppressWarnings("unchecked")
		DataSourceManager<SqlSession> dataSourceManager = (DataSourceManager<SqlSession>)ServiceXUtil.getInstance().getGelobalObject(GlobalObjectName.MYBATIS_DATA_SOURCE_MANAGER);
		
		TextObjectConverter converter = TextObjectConverterFactory.newInstance(format);
		
		if(dataSourceManager != null) {
			// transaction aspect will weave the code which initiates the thread local connection
			// so we can use get instead of getOrInit
			UserDAO userDAO = dataSourceManager.getThreadLocalConnection().getMapper(UserDAO.class);
			List<User> users = userDAO.findAllUser();
			
			return converter.marshall(users);
		}
		else { 
			return converter.getDefaultText(new ArrayList<>());
		}
	}


	@Override
	public String getAllRoles(String format) {
		@SuppressWarnings("unchecked")
		DataSourceManager<SqlSession> dataSourceManager = (DataSourceManager<SqlSession>)ServiceXUtil.getInstance().getGelobalObject(GlobalObjectName.MYBATIS_DATA_SOURCE_MANAGER);
		
		TextObjectConverter converter = TextObjectConverterFactory.newInstance(format);
		
		if(dataSourceManager != null) {
			List<RoleWithUser> ret = new ArrayList<RoleWithUser>();
			
			RoleDAO roleDAO = dataSourceManager.getThreadLocalConnection().getMapper(RoleDAO.class);
			
			List<Role> roles = roleDAO.findAllRole();
			
			UserDAO userDAO = dataSourceManager.getThreadLocalConnection().getMapper(UserDAO.class);
			
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
