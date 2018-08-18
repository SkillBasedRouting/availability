package com.routing.availabilityservice.feature.user.resource;

import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.spi.HttpResponse;
import org.jboss.resteasy.util.HttpResponseCodes;

import com.routing.availabilityservice.dto.user.UserDTO;
import com.routing.availabilityservice.feature.user.boundary.UserBoundary;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <b>com.routing.availabilyservice.feature.user.resource.UserResource</b>
 * <p>
 *   REST Endpoint for all user actions
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(tags = { "User" })
public class UserResource {

	@Inject
	private UserBoundary userBoundary;

	@Context
	private HttpResponse httpResonse;

	/**
	 * Get a user
	 * @param id user id
	 * @return the user
	 */
	@GET
	@Path("/{id}")
	@ApiOperation("get a user")
	public UserDTO get(@ApiParam("the id of the user") @PathParam("id") final String id) {
		return this.userBoundary.get(id);
	}

	/**
	 * create a user if it does not exist
	 * @param id the user id
	 * @return created user or existent
	 */
	@PUT
	@Path("/{id}")
	@ApiOperation("create a user")
	public UserDTO put(@ApiParam("the id of the user") @PathParam("id") final String id) {

		final UserDTO createdUser = this.userBoundary.put(id);

		this.httpResonse.setStatus(HttpResponseCodes.SC_CREATED);

		return createdUser;
	}

	/**
	 * get all users
	 * @return all users
	 */
	@GET
	@Path("/")
	@ApiOperation("get all users")
	public Set<UserDTO> getAll() {
		return this.userBoundary.getAll();
	}

	/**
	 * delete user
	 * @param id the user id
	 */
	@DELETE
	@Path("/{id}")
	@ApiOperation("remove a user")
	public void remove(@ApiParam("the id of the user") @PathParam("id") final String id) {
		this.userBoundary.remove(id);
	}

}
