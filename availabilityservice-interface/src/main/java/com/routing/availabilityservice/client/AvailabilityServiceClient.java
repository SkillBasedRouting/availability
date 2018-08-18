package com.routing.availabilityservice.client;

import java.util.Set;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.routing.availabilityservice.AvailabilityService;
import com.routing.availabilityservice.client.auth.AuthRequestFilter;
import com.routing.availabilityservice.dto.user.UserDTO;

/**
 * <b>com.routing.availabilityservice.client.AvailabilityServiceClient</b>
 * <p>
 *   Client for availability api
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class AvailabilityServiceClient {

	private AvailabilityService service;

	public AvailabilityServiceClient(final String url) {
		this(url, null);
	}

	public AvailabilityServiceClient(final String url, final String jwt) {

		final ResteasyClient client = new ResteasyClientBuilder().build();
		if (null != jwt) {
			client.register(new AuthRequestFilter(jwt));
		}
		final ResteasyWebTarget target = client.target(url);
		this.service = target.proxy(AvailabilityService.class);
	}

    /**
     * Get an user
     * @param id the user id
     * @return the user
     */
	public UserDTO getUser(final String id) {
		return this.service.getUser(id);
	}

    /**
     * Create an user
     * @param id the user id
     * @return the created user
     */
	public UserDTO createUser(final String id) {
		return this.service.createUser(id);
	}

    /**
     * Get all available users
     * @return list of all available users
     */
	public Set<UserDTO> getAvailableUsers() {
		return this.service.getAvailableUsers();
	}

    /**
     * Remove an user
     * @param id the user id
     */
	public void remove(final String id) {
		this.service.remove(id);
	}

}
