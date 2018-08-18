package com.routing.availabilityservice.feature.user.boundary;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import com.routing.availabilityservice.dto.user.UserDTO;
import com.routing.availabilityservice.feature.user.model.User;

@ApplicationScoped
public class UserMapper {

	public UserDTO toDTO(final User user) {

		if (null == user) {
			return null;
		}

		final UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());

		return userDTO;
	}

	public Set<UserDTO> toDTO(final Set<User> userSet) {

		if (null == userSet) {
			return null;
		}

		final Set<UserDTO> userDTOSet = new LinkedHashSet<>(userSet.size());
		userSet.forEach((user) -> {
			userDTOSet.add(this.toDTO(user));
		});

		return userDTOSet;
	}

}
