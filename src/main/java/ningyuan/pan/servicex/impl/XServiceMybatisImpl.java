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
				// so we can use get instead of initAndGet
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
}
