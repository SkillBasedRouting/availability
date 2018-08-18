package com.routing.availabilityservice.test.mock;

import javax.persistence.EntityManager;

import com.routing.availabilityservice.feature.user.dao.UserDAO;

public class UserDAOMock extends UserDAO {

	public UserDAOMock(final EntityManager em) {
		super.em = em;
	}

	public EntityManager em() {
		return super.em;
	}

}
