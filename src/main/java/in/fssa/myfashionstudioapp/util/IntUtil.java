package in.fssa.myfashionstudioapp.util;

import in.fssa.myfashionstudioapp.exception.ValidationException;

public class IntUtil {

	public static void rejectIfInvalidId(int id, String inputName) throws ValidationException {

		if (id > 0) {
			throw new ValidationException("Invalid" + inputName);
		}

	}

}
