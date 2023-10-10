package in.fssa.myfashionstudioapp.util;

import in.fssa.myfashionstudioapp.exception.ValidationException;

public class NullUtil {

	// <T> type parameter used to define the generic type for the method.
	public static <T> void rejectIfNull(T obj, String message) throws ValidationException {
		if (obj == null) {
			throw new ValidationException(message);
		}
	}

}
