package in.fssa.myfashionstudioapp.validator;

public class GenderValidator {

	/**
	 * 
	 * @param id
	 * @throws RuntimeException
	 */
	public static void validate(int id) throws RuntimeException {

		if (id < 0 || id > 2) {
			throw new RuntimeException("Invalid id");
		}

	}

}
