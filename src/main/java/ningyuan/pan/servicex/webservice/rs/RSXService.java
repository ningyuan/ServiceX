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
 * Interface of RESTful a web service. All methods will be woven
 * with transaction codes.
 * 
 * @author ningyuan
 * 
 */
@Path("/xservice")
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
