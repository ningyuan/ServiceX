/**
 * 
 */
package ningyuan.pan.servicex.webservice.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ningyuan.pan.servicex.persistence.entity.Role;
import ningyuan.pan.servicex.persistence.entity.User; 

/**
 * @author ningyuan
 * 
 */
@Path("/servicex")
public interface RSXService {

	@GET
	@Path("/getName")
	@Produces(MediaType.TEXT_PLAIN)
	public String getName();
	
	@GET
	@Path("/getUser")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser();
	
	@GET
	@Path("/getRole")
	@Produces(MediaType.APPLICATION_JSON)
	public Role getRole();
}
