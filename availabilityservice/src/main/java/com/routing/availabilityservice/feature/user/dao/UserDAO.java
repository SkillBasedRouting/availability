package com.routing.availabilityservice.feature.user.dao;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.routing.availabilityservice.feature.user.exception.UserException;
import com.routing.availabilityservice.feature.user.exception.UserNotFoundException;
import com.routing.availabilityservice.feature.user.model.User;

/**
 * <b>com.routing.availabilyservice.feature.user.dao.UserDAO</b>
 * <p>
 *   DataAccessObject for all user relevant database operations
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationScoped
public class UserDAO {

	@PersistenceContext
	protected EntityManager em;

    /**
     * Put an user id to database and returns it or return existing user
     * @param id the user id
     * @return created or existent user
     * @throws {@link UserException} when id is null
     */
	public User put(final String id) {

		if (null == id) {
			throw UserException.ID_REQUIRED;
		}

		// try to get existent user, on error create new
		User returnUser = null;
		try {
			returnUser = this.get(id);
		} catch (UserNotFoundException e) {
			returnUser = new User();
			returnUser.setId(id);
			this.em.persist(returnUser);
		}

		return returnUser;
	}

    /**
     * Query a user by its id
     * @param id the user id
     * @return the user
     * @throws {@link UserNotFoundException} when user does not exist
     */
	public User get(final String id) {

		final TypedQuery<User> query = this.em.createQuery("Select u from User u where u.id = :parId", User.class);
		query.setParameter("parId", id);

		try {
			final User user = query.getSingleResult();
			if (null == user) {
			    throw new NoResultException();
            }
            return user;
		} catch (NoResultException e) {
			throw new UserNotFoundException(id);
		}

	}

    /**
     * Check if user exists
     * @param id the user id
     * @return true when user exists otherwise false
     */
	public boolean exists(final String id) {

		try {
			this.get(id);
			return true;
		} catch (UserNotFoundException e) {
			return false;
		}

	}

    /**
     * Returns all users
     * @return all users
     */
	public Set<User> getAll() {

		final TypedQuery<User> query = this.em.createQuery("Select u from User u", User.class);

		return new LinkedHashSet<>(query.getResultList());
	}

    /**
     * Remove a user
     * @param user the user id
     * @throws {@link NullPointerException} when user is null
     */
	public void remove(final User user) {

        Objects.requireNonNull(user);

		this.em.remove(user);
	}

}
