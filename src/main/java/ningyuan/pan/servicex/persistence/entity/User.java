/**
 * 
 */
package ningyuan.pan.servicex.persistence.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author ningyuan
 *
 */
@Entity
public class User {
	
	@Id
	private long id;
	
	private String firstName;
	
	private String lastName;

	private List<Role> roles;
	
	/**
	 * @return the id
	 */
	public long getID() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setID(long id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder("User [id = "+id+", firstName = "+firstName+", lastName = "+lastName+", ");
		
		ret.append("roles = [");
		
		for(Role role : roles) {
			ret.append(role.toString()+", ");
		}
		
		ret.append("]]");
		
		return ret.toString();
	}
}
