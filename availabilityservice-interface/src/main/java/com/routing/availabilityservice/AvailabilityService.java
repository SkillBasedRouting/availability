package com.routing.availabilityservice;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.routing.availabilityservice.dto.user.UserDTO;

/**
 * <b>com.routing.availabilityservice.AvailabilityService</b>
 * <p>
 *   Interface that represents availability api
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Path("/api/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface AvailabilityService {

	@GET
	@Path("/users/{id}")
	public UserDTO getUser(@PathParam("id") final String id);

	@PUT
	@Path("/users/{id}")
	public UserDTO createUser(@PathParam("id") final String id);

	@GET
	@Path("/users")
	public Set<UserDTO> getAvailableUsers();

	@DELETE
	@Path("/users/{id}")
	public void remove(@PathParam("id") final String id);

}
