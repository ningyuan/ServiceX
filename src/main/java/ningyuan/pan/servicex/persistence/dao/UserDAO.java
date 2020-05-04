/**
 * 
 */
package ningyuan.pan.servicex.persistence.dao;

import java.util.List;

import ningyuan.pan.servicex.persistence.entity.User;

/**
 * @author ningyuan
 *
 */
public interface UserDAO {
	
	public List<User> findAllUser();
	
	public User findUserByID(long userID);
	
	public boolean add(User user);
	
	public boolean delete(long userID);
	
	public boolean update(User user);
	
	public List<User> findAllUserByRole(byte roleID);
	
}
