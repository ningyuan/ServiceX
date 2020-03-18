/**
 * 
 */
package ningyuan.pan.servicex.persistence.dao;

import java.util.List;

import ningyuan.pan.servicex.persistence.entity.Role;

/**
 * @author ningyuan
 *
 */
public interface RoleDAO {
	
	public List<Role> findAllRole();
	
	public boolean add(Role role);
	
	public boolean delete(byte id);
	
	public boolean update(Role role);
}	
