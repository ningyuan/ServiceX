/**
 * 
 */
package ningyuan.pan.servicex.impl;

import java.util.List;

import ningyuan.pan.servicex.persistence.entity.User;

/**
 * @author ningyuan
 *
 */
class RoleWithUser {
	
	private byte id;
	
	private String name;
	
	private List<User> users;
	
	/**
	 * @return the id
	 */
	public byte getID() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setID(byte id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

}
