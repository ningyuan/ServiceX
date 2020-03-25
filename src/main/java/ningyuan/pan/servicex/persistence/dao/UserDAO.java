/**
 * 
 */
package ningyuan.pan.servicex.persistence.dao;

import java.util.List;
import java.util.Map;

import ningyuan.pan.servicex.persistence.entity.User;

/**
 * @author ningyuan
 *
 */
public interface UserDAO {
	
	public List<User> findAllUser();
	
	public User findUserByID(long id);
	
	public boolean add(User user);
	
	public boolean delete(long id);
	
	public boolean update(User user);
	
}
