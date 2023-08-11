package in.fssa.myfashionstudioapp.validator;

public class GenderValidator {

	public static void validate(int id) throws Exception {

		if (id < 0 || id > 2) {
			throw new RuntimeException("Invalid id");
		}

	}

}
