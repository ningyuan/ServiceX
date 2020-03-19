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
					user.setId(id);
					user.setFirstName(firstName);
					user.setLastName(lastName);
					
					List<Role> roles = new ArrayList<Role>();
					
					ps1.setLong(1, id);
					ResultSet rs1 = ps1.executeQuery();
					
					while(rs1.next()){
						byte rid = rs1.getByte(1);
						String name = rs1.getString(2);
						
						Role role = new Role();
						role.setId(rid);
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
				dataSourceManager.removeThreadLocalConnection();
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
					ret.setId(id);
					ret.setFirstName(firstName);
					ret.setLastName(lastName);
					
					List<Role> roles = new ArrayList<Role>();
					
					ps1.setLong(1, id);
					ResultSet rs1 = ps1.executeQuery();
					
					while(rs1.next()){
						byte rid = rs1.getByte(1);
						String name = rs1.getString(2);
						
						Role role = new Role();
						role.setId(rid);
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
				dataSourceManager.removeThreadLocalConnection();
			}
		}
		
		return ret;
	}
}
