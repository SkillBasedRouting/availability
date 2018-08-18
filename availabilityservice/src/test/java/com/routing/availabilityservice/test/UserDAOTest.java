package com.routing.availabilityservice.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.routing.availabilityservice.test.builder.UserBuilder;
import com.routing.availabilityservice.test.mock.UserDAOMock;
import com.routing.availabilityservice.feature.user.exception.UserException;
import com.routing.availabilityservice.feature.user.exception.UserNotFoundException;
import com.routing.availabilityservice.feature.user.model.User;

public class UserDAOTest {

	private static UserDAOMock userDAO;

	@BeforeClass
	public static void init() {
		final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("ExampleDS");
		UserDAOTest.userDAO = new UserDAOMock(emFactory.createEntityManager());
	}

	@Before
	public void startTransaction() {
		UserDAOTest.userDAO.em().getTransaction().begin();
	}

	@After
	public void endTransaction() {
		UserDAOTest.userDAO.em().getTransaction().commit();
	}

	private UserBuilder getDefaultBuilder() {
		return UserBuilder.defaultBuilder();
	}

	@Test
	public void should_CreateUser_On_Create() {

		User user = this.getDefaultBuilder().build();

		user = userDAO.put(user.getId());

		System.out.println(user);

		assertNotNull(user.getInternId());
	}

	@Test
	public void should_Throw_IdRequiredException_When_IdIsNull_On_Create() {

		try {
			userDAO.put(null);
			fail("can create user with null withId");
		} catch (UserException e) {
			assertEquals(UserException.ID_REQUIRED, e);
		}
	}

	@Test
	public void should_ReturnUser_After_Create_On_Get() {

		User user = this.getDefaultBuilder().build();

		user = userDAO.put(user.getId());
		final User createdUser = userDAO.get(user.getId());

		assertEquals(user, createdUser);
	}

	@Test
	public void should_ReturnCreatedUser_When_UserAlreadyExists_On_Create() {

		User user = this.getDefaultBuilder().build();

		user = userDAO.put(user.getId());
		final User secondUser = userDAO.put(user.getId());

		assertEquals(user, secondUser);
	}

	@Test
	public void should_ReturnAllUsers_On_GetAll() {

		final User user = userDAO.put(this.getDefaultBuilder().build().getId());
		final Set<User> allUsers = userDAO.getAll();

		assertTrue(allUsers.contains(user));
	}

	@Test
	public void should_ReturnTrue_When_UserExists_On_Exists() {

		final User user = userDAO.put(this.getDefaultBuilder().build().getId());

		assertTrue(userDAO.exists(user.getId()));
	}

	@Test
	public void should_ReturnFalse_When_UserDoesNotExists_On_Exists() {
		assertFalse(userDAO.exists(UUID.randomUUID().toString()));
	}

	@Test
	public void should_Throw_UserNotFound_When_UserDoesNotExist_On_Get() {

		try {
			userDAO.get(UUID.randomUUID().toString());
			fail("can get non existing user");
		} catch (UserNotFoundException e) {

		}

	}

	@Test
	public void should_RemoveUser_On_Remove() {

		final User user = userDAO.put(this.getDefaultBuilder().build().getId());

		userDAO.remove(user);

		assertFalse(userDAO.exists(user.getId()));
	}

	@Test
	public void should_Throw_NullPointerException_When_UserIsNull_On_Remove() {

		try {
			userDAO.remove(null);
			fail("can remove null user");
		} catch (NullPointerException e) {
			// ok
		}
	}

}
