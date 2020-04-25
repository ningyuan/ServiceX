/**
 * 
 */
package ningyuan.pan.servicex.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author ningyuan
 *
 */
@Entity
public class Role {
	
	@Id
	private byte id;
	
	private String name;
	
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
	
	@Override
	public String toString() {
		return "Role [id = "+id+", name = "+name+"]";
	}
}
