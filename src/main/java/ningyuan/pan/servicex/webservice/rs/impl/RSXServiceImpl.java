/**
 * 
 */
package ningyuan.pan.servicex.webservice.rs.impl;

import java.util.ArrayList;
import java.util.List;

import ningyuan.pan.servicex.XService;
import ningyuan.pan.servicex.persistence.entity.Role;
import ningyuan.pan.servicex.persistence.entity.User;
import ningyuan.pan.servicex.webservice.rs.RSXService;

/**
 * The implementation of RESTful web service. All public methods in web service interface will be
 * woven with transaction codes. Pay attention to weaving this web service implementation
 * with the same service implementation transaction aspect.
 * 
 * @author ningyuan
 *
 */
public class RSXServiceImpl implements RSXService {
	
	private final XService service;
	
	public RSXServiceImpl(XService service) {
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
