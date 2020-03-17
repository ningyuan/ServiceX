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
	
	public List<User> getAllUser();
	
	public User getUserByID(long id);
	
}
