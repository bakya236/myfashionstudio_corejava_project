package in.fssa.myfashionstudioapp.gender;

import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.model.Gender;
import in.fssa.myfashionstudioapp.service.GenderService;

public class TestGetAllGenders {

	@Test
	public void findAll() throws Exception {

		GenderService genderService = new GenderService();
		List<Gender> genderList = genderService.findAll();

//		for (Gender name : genderList) {
//			System.out.println(name.toString());
//		}

		genderList.forEach(System.out::println);

	}

	@Test
	public void findById() throws Exception {

		GenderService genderService = new GenderService();
		Gender gender = genderService.findById(1);

		System.out.println(gender);

	}
}
