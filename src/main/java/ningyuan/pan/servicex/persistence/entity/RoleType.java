/**
 * 
 */
package ningyuan.pan.servicex.persistence.entity;


/**
 * @author ningyuan
 *
 */
public enum RoleType {
	ROOT((byte)0), 
	ADMIN((byte)1),
	COMMON((byte)2),
	GUEST((byte)3),
	TEST((byte)127);
	
	private byte id;
	
	private RoleType(byte id) {
		this.id = id;
	} 
	
	public byte getID() {
		return id;
	}
}
