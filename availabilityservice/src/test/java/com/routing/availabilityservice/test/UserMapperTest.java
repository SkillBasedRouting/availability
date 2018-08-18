package com.routing.availabilityservice.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.routing.availabilityservice.dto.user.UserDTO;
import com.routing.availabilityservice.test.builder.UserBuilder;
import com.routing.availabilityservice.feature.user.boundary.UserMapper;
import com.routing.availabilityservice.feature.user.model.User;

public class UserMapperTest {

	private static UserMapper userMapper;

	@BeforeClass
	public static void init() {
		UserMapperTest.userMapper = new UserMapper();
	}

	private UserBuilder getDefaultBuilder() {
		return UserBuilder.defaultBuilder();
	}

	@Test
	public void should_MapId_On_ToDTOSingle() {

		final User user = this.getDefaultBuilder().build();

		final UserDTO userDTO = userMapper.toDTO(user);

		assertEquals(user.getId(), userDTO.getId());
	}

	@Test
	public void should_MapAll_On_ToDTOList() {

		final Set<User> users = new LinkedHashSet<User>(3);
		users.add(this.getDefaultBuilder().build());
		users.add(this.getDefaultBuilder().build());
		users.add(this.getDefaultBuilder().build());

		final Set<UserDTO> usersDTO = userMapper.toDTO(users);

		usersDTO.forEach((userDTO) -> {
			final boolean exists = users.stream().filter(user -> null != user.getId()).findAny().isPresent();
			assertTrue(exists);
		});
	}

}
