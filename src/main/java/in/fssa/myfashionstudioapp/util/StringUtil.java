package in.fssa.myfashionstudioapp.util;

import in.fssa.myfashionstudioapp.exception.ValidationException;

public class StringUtil {
	/**
	 * 
	 * @param input
	 * @param inputName
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidString(String input, String inputName) throws ValidationException {
		if (input == null || "".equals(input.trim())) {
			throw new ValidationException(inputName.concat(" cannot be empty"));
		}
	}

	/**
	 * 
	 * @param newString
	 * @return
	 */
	public static boolean isValidString(String newString) {

		if (newString == null || "".equals(newString.trim())) {

			return false;
		}
		return true;

	}

	/**
	 * 
	 * @param newString
	 * @return
	 */
	public static boolean isInvalidString(String newString) {

		if (!isValidString(newString)) {

			return true;
		}
		return false;

	}
}
