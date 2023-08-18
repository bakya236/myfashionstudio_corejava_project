package in.fssa.myfashionstudioapp.gender;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.model.Gender;
import in.fssa.myfashionstudioapp.service.GenderService;

public class TestFindGenderByGenderId {

	@Test
	public void FindGenderByGenderId() {
		GenderService genderService = new GenderService();

		assertDoesNotThrow(() -> {
			Gender gender = genderService.findGenderBygenderId(1);
			System.out.println(gender);
		});
	}

}
