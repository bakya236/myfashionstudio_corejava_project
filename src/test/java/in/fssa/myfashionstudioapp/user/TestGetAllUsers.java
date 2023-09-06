package in.fssa.myfashionstudioapp.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.model.User;
import in.fssa.myfashionstudioapp.service.UserService;

public class TestGetAllUsers {

	@Test
	public void getAllUsers() {

		UserService UserService = new UserService();

		assertDoesNotThrow(() -> {
			List<User> userList = UserService.getAllUsers();
			userList.forEach(System.out::println);
		});
	}

}
