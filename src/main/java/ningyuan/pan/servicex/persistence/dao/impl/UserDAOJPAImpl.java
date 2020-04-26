/**
 * 
 */
package ningyuan.pan.servicex.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ningyuan.pan.servicex.persistence.dao.UserDAO;
import ningyuan.pan.servicex.persistence.entity.User;
import ningyuan.pan.util.exception.ExceptionUtils;
import ningyuan.pan.util.persistence.DataSourceManager;

/**
 * @author ningyuan
 *
 */
public class UserDAOJPAImpl implements UserDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOJPAImpl.class);
	
	private final DataSourceManager<EntityManager> dataSourceManager;
	
	private String selectAllUser = "SELECT u FROM User u";
	
	public UserDAOJPAImpl(DataSourceManager<EntityManager> dataSourceManager) {
		this.dataSourceManager = dataSourceManager;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUser() {
		LOGGER.debug("findAllUser()");
		
		List<User> ret = new ArrayList<User>();
		EntityManager con = dataSourceManager.getOrInitThreadLocalConnection();
		
		try {
			if(con != null) {
				Query query = con.createQuery(selectAllUser);
				ret = query.getResultList();
			}
		}
		catch(Exception e) {
			LOGGER.debug(ExceptionUtils.printStackTraceToString(e));
		}
		
		return ret;
	}

	@Override
	public User findUserByID(long id) {
		LOGGER.debug("findUserByID()");
		
		User ret = null;
		EntityManager con = dataSourceManager.getOrInitThreadLocalConnection();
		
		try {
			if(con != null) {
				ret = con.find(User.class, id);
			}
		}
		catch(IllegalArgumentException iae) {
			LOGGER.debug(ExceptionUtils.printStackTraceToString(iae));
		}
		
		return ret;
	}

	@Override
	public boolean add(User user) {
		LOGGER.debug("add()");
		
		EntityManager con = dataSourceManager.getOrInitThreadLocalConnection();
		
		try {
			if(con != null) {
				con.persist(user);
				
				return true;
			}
		}
		catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException e) {
			LOGGER.debug(ExceptionUtils.printStackTraceToString(e));
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(long id) {
		LOGGER.debug("delete()");
		
		EntityManager con = dataSourceManager.getOrInitThreadLocalConnection();
		
		try {
			if(con != null) {
				User user = con.find(User.class, id);
				
				if(user != null) {
					con.remove(user);
					
					return true;
				}
			}
		}
		catch (IllegalArgumentException | TransactionRequiredException e) {
			LOGGER.debug(ExceptionUtils.printStackTraceToString(e));
		}
		
		return false;
	}

	@Override
	public boolean update(User user) {
		LOGGER.debug("update()");
		
		EntityManager con = dataSourceManager.getOrInitThreadLocalConnection();
		
		try {
			if(con != null) {
				User oldUser = con.find(User.class, user.getID());
				
				if(oldUser != null) {
					con.merge(user);
					
					return true;
				}
			}
		}
		catch (IllegalArgumentException | TransactionRequiredException e) {
			LOGGER.debug(ExceptionUtils.printStackTraceToString(e));
		}
		
		return false;
	}

}
