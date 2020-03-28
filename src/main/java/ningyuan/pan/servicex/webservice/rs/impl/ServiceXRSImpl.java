/**
 * 
 */
package ningyuan.pan.servicex.webservice.rs.impl;

import java.util.ArrayList;
import java.util.List;

import ningyuan.pan.servicex.ServiceX;
import ningyuan.pan.servicex.persistence.entity.Role;
import ningyuan.pan.servicex.persistence.entity.User;
import ningyuan.pan.servicex.webservice.rs.ServiceXRS;

/**
 * @author ningyuan
 *
 */
public class ServiceXRSImpl implements ServiceXRS {
	
	private final ServiceX service;
	
	public ServiceXRSImpl(ServiceX service) {
		this.service = service;
	}
	
	@Override
	public String getName() {
		return service.getName();
	}

	@Override
	public User getUser() {
		User user = new User();
		user.setID(1);
		user.setFirstName("hello");
		user.setLastName("world");
		
		List<Role> roles = new ArrayList<Role>();
		Role role = new Role();
		role.setID((byte)1);
		role.setName("x");
		
		roles.add(role);
		
		role = new Role();
		role.setID((byte)2);
		role.setName("y");
		
		roles.add(role);
		
		user.setRoles(roles);
		
		return user;
	}
	
	@Override
	public Role getRole() {
		Role role = new Role();
		role.setID((byte)1);
		role.setName("x");
		
		return role;
	}
}
