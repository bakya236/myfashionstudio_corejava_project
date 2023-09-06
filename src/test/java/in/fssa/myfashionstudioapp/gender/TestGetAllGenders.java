package in.fssa.myfashionstudioapp.gender;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.model.Gender;
import in.fssa.myfashionstudioapp.service.GenderService;

public class TestGetAllGenders {

	@Test
	public void findAll() {

		GenderService genderService = new GenderService();

		assertDoesNotThrow(() -> {
			List<Gender> genderList = genderService.getAllGenders();
			genderList.forEach(System.out::println);
		});

	}
}
