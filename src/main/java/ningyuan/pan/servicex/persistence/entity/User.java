/**
 * 
 */
package ningyuan.pan.servicex.persistence.entity;

/**
 * @author ningyuan
 *
 */
public class User {
	
	private long id;
	
	private String firstName;
	
	private String lastName;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
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
	
	@Override
	public String toString() {
		return "[id = "+id+", firstName = "+firstName+", lastName = "+lastName+"]";
	}
}
