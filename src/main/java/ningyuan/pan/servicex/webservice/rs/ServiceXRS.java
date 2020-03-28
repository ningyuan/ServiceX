/**
 * 
 */
package ningyuan.pan.servicex.webservice.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path; 

/**
 * @author ningyuan
 * 
 */
@Path("/servicex")
public interface ServiceXRS {

	@GET
	@Path("/getName")
	public String getName();

}
