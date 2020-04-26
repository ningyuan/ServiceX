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

import ningyuan.pan.servicex.persistence.dao.RoleDAO;
import ningyuan.pan.servicex.persistence.entity.Role;
import ningyuan.pan.util.exception.ExceptionUtils;
import ningyuan.pan.util.persistence.DataSourceManager;

/**
 * @author ningyuan
 *
 */
public class RoleDAOJPAImpl implements RoleDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleDAOJPAImpl.class);
	
	private final DataSourceManager<EntityManager> dataSourceManager;
	
	private String selectAllRole = "SELECT r FROM Role r";
	
	public RoleDAOJPAImpl(DataSourceManager<EntityManager> dataSourceManager) {
		this.dataSourceManager = dataSourceManager;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findAllRole() {
		LOGGER.debug("findAllRole()");
		
		List<Role> ret = new ArrayList<Role>();
		EntityManager con = null;
		
		con = dataSourceManager.getOrInitThreadLocalConnection();
		
		try {
			if(con != null) {
				Query query = con.createQuery(selectAllRole);
				ret = query.getResultList();
			}
		}
		catch(Exception e) {
			LOGGER.debug(ExceptionUtils.printStackTraceToString(e));
		}
			
		return ret;
	}

	@Override
	public Role findRoleByID(byte id) {
		LOGGER.debug("findRoleByID()");
		
		Role ret = null;
		EntityManager con = dataSourceManager.getOrInitThreadLocalConnection();
		
		try {
			if(con != null) {
				ret = con.find(Role.class, id);
			}
		}
		catch (IllegalArgumentException iae) {
			LOGGER.debug(ExceptionUtils.printStackTraceToString(iae));
		}
		
		return ret;
	}

	@Override
	public boolean add(Role role) {
		LOGGER.debug("add()");
		
		EntityManager con = dataSourceManager.getOrInitThreadLocalConnection();
		
		try {
			if(con != null) {
				con.persist(role);
				
				return true;
			}
		}
		catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException e) {
			LOGGER.debug(ExceptionUtils.printStackTraceToString(e));
		}
		
		return false;
	}

	@Override
	public boolean delete(byte id) {
		LOGGER.debug("delete()");
		
		EntityManager con = dataSourceManager.getOrInitThreadLocalConnection();
		
		try {
			if(con != null) {
				Role role = con.find(Role.class, id);
				
				if(role != null) {
					con.remove(role);
					
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
	public boolean update(Role role) {
		LOGGER.debug("update()");
		
		EntityManager con = dataSourceManager.getOrInitThreadLocalConnection();
		
		try {
			if(con != null) {
				Role oldRole = con.find(Role.class, role.getID());
				
				if(oldRole != null) {
					con.merge(role);
					
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
