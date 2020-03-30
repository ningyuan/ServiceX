/**
 * 
 */
package ningyuan.pan.servicex.impl;

import org.apache.ibatis.session.SqlSession;

import ningyuan.pan.servicex.XService;
import ningyuan.pan.servicex.persistence.dao.UserDAO;
import ningyuan.pan.servicex.persistence.entity.User;
import ningyuan.pan.servicex.util.GlobalObjectName;
import ningyuan.pan.servicex.util.ServiceXUtil;
import ningyuan.pan.util.persistence.DataSourceManager;

/**
 * @author ningyuan
 *
 */
public class XServiceMybatisImpl implements XService {

	public String getName() {
		@SuppressWarnings("unchecked")
		DataSourceManager<SqlSession> dataSourceManager = (DataSourceManager<SqlSession>)ServiceXUtil.getInstance().getGelobalObject(GlobalObjectName.MYBATIS_DATA_SOURCE_MANAGER);
		
		if(dataSourceManager != null) {
			try {
				// transaction aspect will weave the code which initiates the thread local connection
				// so we can use get instead of initAndGet
				UserDAO userDAO = dataSourceManager.getThreadLocalConnection().getMapper(UserDAO.class);
				User user = userDAO.findUserByID(0);
				return user.getFirstName();
			}
			catch(Exception e) {
				return "No user";
			}
		}
		else {
			return "No data source manager set in context";
		}
	}
}
