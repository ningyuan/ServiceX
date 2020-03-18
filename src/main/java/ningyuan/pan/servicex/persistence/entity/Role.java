/**
 * 
 */
package ningyuan.pan.servicex.persistence.entity;

/**
 * @author ningyuan
 *
 */
public class Role {
	
	private byte id;
	
	private String name;

	/**
	 * @return the id
	 */
	public byte getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(byte id) {
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
	
	@Override
	public String toString() {
		return "Role [id = "+id+", name = "+name+"]";
	}
}
