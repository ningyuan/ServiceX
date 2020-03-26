/**
 * 
 */
package ningyuan.pan.servicex.persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
public class RoleDAOJDBCImpl implements RoleDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleDAOJDBCImpl.class);
	
	private final DataSourceManager<Connection> dataSourceManager;
	
	private volatile boolean closeConnectionAfterEachCall = true;
	
	private String selectAllRole = "SELECT * FROM role";
	
	private String selectRoleByID = "SELECT name FROM role WHERE id = ?";
	
	private String insertRole = "INSERT INTO role VALUES (?, ?)";
	
	private String deleteRole = "DELETE FROM role WHERE id = ?";
	
	private String updateRole = "UPDATE role SET name = ? WHERE id = ?";
	
	public RoleDAOJDBCImpl(DataSourceManager<Connection> dataSourceManager) {
		this.dataSourceManager = dataSourceManager;
	}
	
	public RoleDAOJDBCImpl(DataSourceManager<Connection> dataSourceManager, boolean closeConnectionAfterEachCall) {
		this(dataSourceManager);
		this.closeConnectionAfterEachCall = closeConnectionAfterEachCall;
	}
	
	@Override
	public List<Role> findAllRole() {
		LOGGER.debug("findAllRole()");
		
		List<Role> ret = new ArrayList<Role>();
		Connection con = null;
		
		try {
			con = dataSourceManager.initAndGetThreadLocalConnection();
			
			if(con != null) {
				PreparedStatement ps = con.prepareStatement(selectAllRole);
				
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					byte id = rs.getByte(1);
					String name = rs.getString(2);
					
					Role role = new Role();
					role.setId(id);
					role.setName(name);
				    
					ret.add(role);
				}	
					
				rs.close();
				ps.close();
			}
		}
		catch (SQLException sqle) {
			LOGGER.debug(ExceptionUtils.printStackTraceToString(sqle));
		}
		finally {
			if(closeConnectionAfterEachCall) {
				dataSourceManager.removeAndCloseThreadLocalConnection();
			}	
		}
		
		return ret;
	}

	@Override
	public Role findRoleByID(byte id) {
		LOGGER.debug("findRoleByID()");
		
		Role ret = null;
		Connection con = null;
		
		try {
			con = dataSourceManager.initAndGetThreadLocalConnection();
			
			if(con != null) {
				PreparedStatement ps = con.prepareStatement(selectRoleByID);
				
				ps.setLong(1, id);
				
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					ret = new Role();
					ret.setId(id);
					ret.setName(rs.getString(1));
				}
			
				rs.close();
				ps.close();
			}
		}
		catch (SQLException sqle) {
			LOGGER.debug(ExceptionUtils.printStackTraceToString(sqle));
		}
		finally {
			if(closeConnectionAfterEachCall) {
				dataSourceManager.removeAndCloseThreadLocalConnection();
			}
		}
		
		return ret;
	}
	
	@Override
	public boolean add(Role role) {
		LOGGER.debug("add()");
		
		Connection con = null;
		
		try {
			con = dataSourceManager.initAndGetThreadLocalConnection();
			
			if(con != null) {
				
				PreparedStatement ps = con.prepareStatement(insertRole);
				
				ps.setLong(1, role.getId());
				ps.setString(2, role.getName());
				
				ps.executeUpdate();
			
				ps.close();
				
				return true;
			}
			
			return false;
		}
		catch (SQLException sqle) {
			LOGGER.debug(ExceptionUtils.printStackTraceToString(sqle));
			return false;
		}
		finally {
			if(closeConnectionAfterEachCall) {
				dataSourceManager.removeAndCloseThreadLocalConnection();
			}
		}
	}

	@Override
	public boolean delete(byte id) {
		LOGGER.debug("delete()");
		
		Connection con = null;
		
		try {
			con = dataSourceManager.initAndGetThreadLocalConnection();
			
			if(con != null) {
				
				PreparedStatement ps = con.prepareStatement(deleteRole);
				
				ps.setLong(1,id);
				
				ps.executeUpdate();
	
				return true;
			}
			
			return false;
		}
		catch (SQLException sqle) {
			LOGGER.debug(ExceptionUtils.printStackTraceToString(sqle));
			return false;
		}
		finally {
			if(closeConnectionAfterEachCall) {
				dataSourceManager.removeAndCloseThreadLocalConnection();
			}
		}
	}

	@Override
	public boolean update(Role role) {
		LOGGER.debug("update()");
		
		Connection con = null;
		
		try {
			con = dataSourceManager.initAndGetThreadLocalConnection();
			
			if(con != null) {
				PreparedStatement ps = con.prepareStatement(updateRole);
				
				ps.setString(1, role.getName());
				ps.setByte(2, role.getId());
				
				ps.executeUpdate();
				
				ps.close();
				
				return true;
			}
			
			return false;
		}
		catch (SQLException sqle) {
			LOGGER.debug(ExceptionUtils.printStackTraceToString(sqle));
			return false;
		}
		finally {
			if(closeConnectionAfterEachCall) {
				dataSourceManager.removeAndCloseThreadLocalConnection();
			}
		}
	}

}
