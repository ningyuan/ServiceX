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

import ningyuan.pan.servicex.persistence.dao.UserDAO;
import ningyuan.pan.servicex.persistence.entity.Role;
import ningyuan.pan.servicex.persistence.entity.User;
import ningyuan.pan.util.exception.ExceptionUtils;
import ningyuan.pan.util.persistence.DataSourceManager;

/**
 * @author ningyuan
 *
 */
public class UserDAOJDBCImpl implements UserDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOJDBCImpl.class);
	
	private final DataSourceManager<Connection> dataSourceManager;
	
	private volatile boolean closeConnectionAfterEachCall = true;
	
	private String selectAllUser = "SELECT * FROM user";
	
	private String selectUserByID = "SELECT firstName, lastName FROM user WHERE id = ?";
	
	private String selectAllRolesByUser = "SELECT role.id, role.name FROM user_role JOIN role ON user_role.rid = role.id WHERE user_role.uid = ?";
	
	private String insertUser = "INSERT INTO user VALUES (?, ?, ?)";
			
	private String insertUserRole = "INSERT INTO user_role VALUES (?, ?)";
	
	private String deleteUserRole = "DELETE FROM user_role WHERE uid = ?";
			
	private String updateUser = "UPDATE user SET firstName = ?, lastName = ? WHERE id = ?";
	
	private String deleteUser = "DELETE FROM user WHERE id = ?";
			
	public UserDAOJDBCImpl(DataSourceManager<Connection> dataSourceManager) {
		this.dataSourceManager = dataSourceManager;
	}
	
	public UserDAOJDBCImpl(DataSourceManager<Connection> dataSourceManager, boolean closeConnectionAfterEachCall) {
		this(dataSourceManager);
		this.closeConnectionAfterEachCall = closeConnectionAfterEachCall;
	}
	
	public void setCloseConnectionAfterEachCall(boolean closeConnectionAfterEachCall) {
		this.closeConnectionAfterEachCall = closeConnectionAfterEachCall;
	}
	
	@Override 
	public List<User> findAllUser() {
		LOGGER.debug("findAllUser()");
		
		List<User> ret = new ArrayList<User>();
		Connection con = null;
		
		try {
			con = dataSourceManager.initAndGetThreadLocalConnection();
			
			if(con != null) {
				PreparedStatement ps = con.prepareStatement(selectAllUser);
				PreparedStatement ps1 = con.prepareStatement(selectAllRolesByUser);
				
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					long id = rs.getLong(1);
					String firstName = rs.getString(2);
					String lastName = rs.getString(3);
					
					User user = new User();
					user.setID(id);
					user.setFirstName(firstName);
					user.setLastName(lastName);
					
					List<Role> roles = new ArrayList<Role>();
					
					ps1.setLong(1, id);
					ResultSet rs1 = ps1.executeQuery();
					
					while(rs1.next()){
						byte rid = rs1.getByte(1);
						String name = rs1.getString(2);
						
						Role role = new Role();
						role.setID(rid);
						role.setName(name);
						
						roles.add(role);
					}
					
					user.setRoles(roles);
					
					ret.add(user);
					
					rs1.close();
				}
				
				ps1.close();
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
	public User findUserByID(long id) {
		LOGGER.debug("findUserByID()");
		
		User ret = null;
		Connection con = null;
		
		try {
			con = dataSourceManager.initAndGetThreadLocalConnection();
			
			if(con != null) {
				PreparedStatement ps = con.prepareStatement(selectUserByID);
				PreparedStatement ps1 = con.prepareStatement(selectAllRolesByUser);
				
				ps.setLong(1, id);
				
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					String firstName = rs.getString(1);
					String lastName = rs.getString(2);
					
					ret = new User();
					ret.setID(id);
					ret.setFirstName(firstName);
					ret.setLastName(lastName);
					
					List<Role> roles = new ArrayList<Role>();
					
					ps1.setLong(1, id);
					ResultSet rs1 = ps1.executeQuery();
					
					while(rs1.next()){
						byte rid = rs1.getByte(1);
						String name = rs1.getString(2);
						
						Role role = new Role();
						role.setID(rid);
						role.setName(name);
						
						roles.add(role);
					}
					
					ret.setRoles(roles);
					
					rs1.close();
				}
				
				ps1.close();
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
	public boolean add(User user) {
		LOGGER.debug("add()");
		
		Connection con = null;
		
		try {
			con = dataSourceManager.initAndGetThreadLocalConnection();
			
			if(con != null) {
				PreparedStatement ps = con.prepareStatement(insertUser);
				PreparedStatement ps1 = con.prepareStatement(insertUserRole);
				
				ps.setLong(1, user.getID());
				ps.setString(2, user.getFirstName());
				ps.setString(3, user.getLastName());
				
				ps.executeUpdate();
			
				List<Role> roles = user.getRoles();
				for(Role role : roles) {
					ps1.setLong(1, user.getID());
					ps1.setByte(2, role.getID());
					
					ps1.executeUpdate();
				}
				
				ps1.close();
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
	public boolean delete(long id) {
		LOGGER.debug("delete()");
		
		Connection con = null;
		
		try {
			con = dataSourceManager.initAndGetThreadLocalConnection();
			
			if(con != null) {
				PreparedStatement ps = con.prepareStatement(deleteUser);
				PreparedStatement ps1 = con.prepareStatement(deleteUserRole);
				
				ps.setLong(1,id);
				
				ps.executeUpdate();
				
				ps1.setLong(1, id);
				ps1.executeUpdate();
				
				ps1.close();
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
	public boolean update(User user) {
		LOGGER.debug("update()");
		
		Connection con = null;
		
		try {
			con = dataSourceManager.initAndGetThreadLocalConnection();
			
			if(con != null) {
				PreparedStatement ps = con.prepareStatement(updateUser);
				PreparedStatement ps1 = con.prepareStatement(deleteUserRole);
				PreparedStatement ps2 = con.prepareStatement(insertUserRole);
				
				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setLong(3, user.getID());
				
				ps.executeUpdate();
				
				ps1.setLong(1, user.getID());
				ps1.executeUpdate();
				
				List<Role> roles = user.getRoles();
				for(Role role : roles) {
					ps2.setLong(1, user.getID());
					ps2.setByte(2, role.getID());
					
					ps2.executeUpdate();
				}
				
				ps2.close();
				ps1.close();
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
