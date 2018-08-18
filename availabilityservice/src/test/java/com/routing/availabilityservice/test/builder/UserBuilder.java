package com.routing.availabilityservice.test.builder;

import java.util.UUID;

import com.routing.availabilityservice.feature.user.model.User;

public class UserBuilder {

	public static UserBuilder defaultBuilder() {
		return new UserBuilder().withId(UUID.randomUUID().toString());
	}

	private User user;

	public UserBuilder() {
		this.user = new User();
	}

	public UserBuilder(final User user) {
		this.user = user;
	}

	public UserBuilder withId(final String id) {
		this.user.setId(id);
		return this;
	}

	public User build() {
		return this.user;
	}

}
