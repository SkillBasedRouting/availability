package com.routing.availabilityservice.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.ws.rs.WebApplicationException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.routing.availabilityservice.client.AvailabilityServiceClient;
import com.routing.availabilityservice.dto.user.UserDTO;

@RunWith(Arquillian.class)
public class UserApiTest {

	private static AvailabilityServiceClient client;

	@Deployment
	public static WebArchive createDeployment() {

		final File[] libs = Maven.resolver().loadPomFromFile("pom.xml").importCompileAndRuntimeDependencies().resolve()
				.withTransitivity().asFile();

		final WebArchive deployment = ShrinkWrap.create(WebArchive.class).addAsLibraries(libs)
				.addPackages(true, "com.routing")
				.addAsResource("META-INF/persistence-integration.xml", "META-INF/persistence.xml")
				.addAsResource("project-defaults.yml");

		return deployment;
	}

	@BeforeClass
	public static void init() {
		client = new AvailabilityServiceClient("http://localhost:8080");
	}

	@Test
	@RunAsClient
	public void should_CreateUser_On_Put() {

		final String id = UUID.randomUUID().toString();
		final UserDTO user = client.createUser(id);

		assertEquals(id, user.getId());
	}

	@Test
	@RunAsClient
	public void should_Return405_When_IdIsEmpty_On_Create() {

		try {
			client.createUser("");
			fail("can create user with no withId");
		} catch (WebApplicationException e) {
			assertEquals(405, e.getResponse().getStatus());
		}

	}

	@Test
	@RunAsClient
	public void should_Return404_When_UserDoesNotExists_On_Get() {

		final String id = UUID.randomUUID().toString();

		try {
			client.getUser(id);
			fail("can get unexisting user");
		} catch (WebApplicationException e) {
			assertEquals(404, e.getResponse().getStatus());
		}

	}

	@Test
	@RunAsClient
	public void should_ReturnUser_When_UserExists_On_Get() {

		final String id = UUID.randomUUID().toString();
		final UserDTO user = client.createUser(id);

		final UserDTO getUser = client.getUser(id);

		assertEquals(user, getUser);
	}

	@Test
	@RunAsClient
	public void should_ReturnNoUser_When_NoUserExists_On_GetAvailable() {

		final Set<UserDTO> users = client.getAvailableUsers();

		assertEquals(0, users.size());
	}

	@Test
	@RunAsClient
	public void should_ReturnAllUsers_On_GetAvailable() {

		final Set<UserDTO> users = new HashSet<>(3);
		users.add(client.createUser(UUID.randomUUID().toString()));
		users.add(client.createUser(UUID.randomUUID().toString()));
		users.add(client.createUser(UUID.randomUUID().toString()));

		final Set<UserDTO> usersResp = client.getAvailableUsers();

		users.forEach((user) -> {
			assertTrue(usersResp.contains(user));
		});
	}

	@Test
	@RunAsClient
	public void should_RemoveUser_When_UserExists_On_Delete() {

		final UserDTO user = client.createUser(UUID.randomUUID().toString());

		client.remove(user.getId());

		try {
			client.getUser(user.getId());
			fail("can get removed user");
		} catch (WebApplicationException e) {
			assertEquals(404, e.getResponse().getStatus());
		}
	}

	@Test
	@RunAsClient
	public void should_Return404_When_UserDoesNotExists_On_Delete() {

		try {
			client.remove(UUID.randomUUID().toString());
			fail("can remove non existing user");
		} catch (WebApplicationException e) {
			assertEquals(404, e.getResponse().getStatus());
		}

	}

	@Test
	@RunAsClient
	public void should_Return405_When_UserIdIsEmpty_On_Delete() {

		try {
			client.remove("");
			fail("can remove non existing user");
		} catch (WebApplicationException e) {
			assertEquals(405, e.getResponse().getStatus());
		}

	}
}
