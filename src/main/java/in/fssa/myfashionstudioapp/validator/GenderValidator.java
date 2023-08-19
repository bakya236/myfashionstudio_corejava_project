package in.fssa.myfashionstudioapp.validator;

import in.fssa.myfashionstudioapp.dao.GenderDAO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.exception.ValidationException;

public class GenderValidator {

	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidGender(int id) throws ValidationException {

		if (id < 0) {
			throw new ValidationException("Invalid gender input");
		}
	}

	private static boolean checkIfGenderExits(int id) throws ValidationException {
		try {
			GenderDAO genderDao = new GenderDAO();
			return genderDao.genderAlreadyExists(id);
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
	}

	public static void rejectIfGenderNotExists(int id) throws ValidationException {
		if (!(checkIfGenderExits(id))) {
			throw new ValidationException("Category with ID " + id + " does not exist");
		}
	}

}
