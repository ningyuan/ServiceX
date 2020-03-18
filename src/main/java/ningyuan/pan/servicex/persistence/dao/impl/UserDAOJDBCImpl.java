/**
 * 
 */
package ningyuan.pan.servicex.persistence.dao.impl;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import ningyuan.pan.servicex.persistence.dao.UserDAO;
import ningyuan.pan.servicex.persistence.entity.Role;
import ningyuan.pan.servicex.persistence.entity.User;

/**
 * @author ningyuan
 *
 */
public class UserDAOJDBCImpl implements UserDAO {
	
	private static final Logger LOOGER = LoggerFactory.getLogger(UserDAOJDBCImpl.class);
	 
	private static DataSource DATASOURCE;
	
	private String selectAllUser = "SELECT * FROM user";
	
	private String selectUserByID = "SELECT firstName, lastName FROM user WHERE id = ?";
	
	private String selectAllRolesByUser = "SELECT role.id, role.name FROM user_role JOIN role ON user_role.rid = role.id WHERE user_role.uid = ?";
	
	static {
		DATASOURCE = new ComboPooledDataSource();
		
		/*Properties configProp;
		try {
			configProp = new Properties();
        	configProp.load(new InputStreamReader(UserDAOJDBCImpl.class.getClassLoader().getResourceAsStream("conf/c3p0.properties")));
	
        	((ComboPooledDataSource)dataSource).setDriverClass(configProp.getProperty("c3p0.driverClass"));
        	((ComboPooledDataSource)dataSource).setJdbcUrl(configProp.getProperty("c3p0.jdbcUrl"));
        	((ComboPooledDataSource)dataSource).setUser(configProp.getProperty("c3p0.user"));
        	((ComboPooledDataSource)dataSource).setPassword(configProp.getProperty("c3p0.password"));
        	((ComboPooledDataSource)dataSource).setMaxPoolSize(Integer.parseInt(configProp.getProperty("c3p0.maxPoolSize")));
        	((ComboPooledDataSource)dataSource).setMinPoolSize(Integer.parseInt(configProp.getProperty("c3p0.minPoolSize")));
        	((ComboPooledDataSource)dataSource).setMaxIdleTime(Integer.parseInt(configProp.getProperty("c3p0.maxIdleTime")));
        		
		} catch (IOException | PropertyVetoException e) {
			e.printStackTrace();
        }	*/
	}
	
	@Override
	public List<User> findAllUser() {
		List<User> ret = new ArrayList<User>();
		Connection con = null;
		
		try {
			con = DATASOURCE.getConnection();
			
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
			
			LOOGER.debug("findAllUser()");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}

	@Override
	public User findUserByID(long id) {
		User ret = null;
		Connection con = null;
		
		try {
			con = DATASOURCE.getConnection();
			
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
			
			LOOGER.debug("findUserByID()");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return ret;
	}
}
