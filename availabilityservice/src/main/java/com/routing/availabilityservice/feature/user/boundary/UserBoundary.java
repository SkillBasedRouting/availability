package com.routing.availabilityservice.feature.user.boundary;

import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.routing.availabilityservice.dto.user.UserDTO;
import com.routing.availabilityservice.feature.user.dao.UserDAO;
import com.routing.availabilityservice.feature.user.model.User;

/**
 * <b>com.routing.availabilyservice.feature.user.boundary.UserBoundary</b>
 * <p>
 *   Boundary for all user actions, start a new transaction
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UserBoundary {

	@Inject
	private UserDAO userDAO;

	@Inject
	private UserMapper userMapper;

    /**
     * Create an user
     * @param id the user id
     * @return the created user
     */
	public UserDTO put(final String id) {

		final User user = this.userDAO.put(id);

		return this.userMapper.toDTO(user);
	}

    /**
     * Get an user
     * @param id the user id
     * @return the user
     */
	public UserDTO get(final String id) {

		final User user = this.userDAO.get(id);

		return this.userMapper.toDTO(user);
	}

    /**
     * Get all users
     * @return
     */
	public Set<UserDTO> getAll() {

		final Set<User> userSet = this.userDAO.getAll();

		return this.userMapper.toDTO(userSet);
	}

    /**
     * Remove an user
     * @param id the user id
     */
	public void remove(final String id) {

		final User user = this.userDAO.get(id);

		this.userDAO.remove(user);
	}

}
