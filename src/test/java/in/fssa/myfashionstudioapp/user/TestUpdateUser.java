package in.fssa.myfashionstudioapp.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.User;
import in.fssa.myfashionstudioapp.service.UserService;

public class TestUpdateUser {

	@Test
	public void testUpdateUserWithValidInput() {

		User user = new User();
		user.setId(1);
		user.setUserName("baya");
		user.setEmail("bakyalakshmi623@gmail.com");
		user.setPassword("12345@Bka");
		user.setPhoneNumber(9789853625L);

		UserService userService = new UserService();

		assertDoesNotThrow(() -> {
			userService.updateUser(user);
		});
	}

	@Test
	void testUpdateUserWithInvalidInput() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.updateUser(null);
		});
		String expectedMessage = "Invalid user Input";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testUpdateUserWithNonExistingEmail() {
		UserService userService = new UserService();

		String NonExistingEmail = "bakyalaksi623@gmail.com";
		Exception exception = assertThrows(ValidationException.class, () -> {

			User user = new User();
			user.setId(1);
			user.setUserName("bakyalakshmi");
			user.setEmail(NonExistingEmail);
			user.setPassword("12345@Bkya");
			user.setPhoneNumber(9789853625L);

			userService.updateUser(user);
		});
		String expectedMessage = "Email " + NonExistingEmail + " does not exist";

		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	void testUpdateUserEmailWithNull() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			User user = new User();
			user.setId(1);
			user.setUserName("bakyalakshmi");
			user.setEmail(null);
			user.setPassword("12345@Bkya");
			user.setPhoneNumber(9789853625L);

			userService.updateUser(user);

		});

		String expectedMessage = "Email cannot be empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testUpdateUserWithEmailEmpty() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			User user = new User();
			user.setId(1);
			user.setUserName("bakyalakshmi");
			user.setEmail("");
			user.setPhoneNumber(9789853625L);
			user.setPassword("Bakya@623b");

			userService.updateUser(user);

		});

		String expectedMessage = "Email cannot be empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testUpdateUserWithPasswordNull() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User user = new User();
			user.setId(1);
			user.setUserName("bakyalakshmi");
			user.setEmail("bakyalakshmi@gmail.com");
			user.setPhoneNumber(9789853625L);
			user.setPassword(null);

			userService.updateUser(user);
		});
		String expectedMessage = "Password cannot be empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testUpdateUserWithPasswordEmpty() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User user = new User();
			user.setId(1);
			user.setUserName("bakyalakshmi");
			user.setEmail("bakyalakshmi@gmail.com");
			user.setPhoneNumber(9789853625L);
			user.setPassword("");

			userService.updateUser(user);
		});
		String expectedMessage = "Password cannot be empty";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testUpdateUserWithWrongPasswordPattern() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User user = new User();
			user.setId(1);
			user.setUserName("bakyalakshmi");
			user.setEmail("bakyalakshmi@gmail.com");
			user.setPhoneNumber(9789853625L);
			user.setPassword("fghjk34567");

			userService.updateUser(user);
		});
		String expectedMessage = "Password doesn't match the required format.The password must contain atleast one Uppercase,one Lowercase,one Special character,one number";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testUpdateUserWithInvalidPhoneNumber() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			User user = new User();
			user.setId(1);
			user.setUserName("bakyalakshmi");
			user.setEmail("bakyalakshmi@gmail.com");
			user.setPhoneNumber(1237649873L);
			user.setPassword("Bakya@623b");

			userService.updateUser(user);
		});

		String expectedMessage = "Invalid phone number";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	// user name test case
	@Test
	void testUpdateUserWithUserNameNull() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			User user = new User();
			user.setId(1);
			user.setUserName(null);
			user.setEmail("bakyalakshmi@gmail.com");
			user.setPhoneNumber(9789853625L);
			user.setPassword("Bakya@623b");

			userService.updateUser(user);
		});
		String expectedMessage = "User name cannot be empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testUpdateUserWithUserNameEmpty() { //
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			User user = new User();
			user.setId(1);
			user.setUserName("");
			user.setEmail("bakyalakshmi@gmail.com");
			user.setPhoneNumber(9789853625L);
			user.setPassword("Bakya@623b");

			userService.updateUser(user);
		});
		String expectedMessage = "User name cannot be empty";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testUpdateUserWithInvalidUserName() { //
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User user = new User();
			user.setId(1);
			user.setUserName("12345sdfg");
			user.setEmail("bakyalakshmi@gmail.com");
			user.setPhoneNumber(9789853625L);
			user.setPassword("Bakya@623b");

			userService.updateUser(user);
		});
		String expectedMessage = "User name should contain only alphabetic characters";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertEquals(expectedMessage, actualMessage);
	}

}
