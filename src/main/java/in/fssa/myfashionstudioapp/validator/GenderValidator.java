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
	public static void rejectIfInvalidGender(int genderId) throws ValidationException {

		if (genderId < 0) {
			throw new ValidationException("Invalid gender input");
		}
	}

	private static boolean checkIfGenderExists(int genderId) throws ValidationException {
		try {
			GenderDAO genderDAO = new GenderDAO();
			return genderDAO.genderAlreadyExists(genderId);
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
	}

	public static void rejectIfGenderNotExists(int genderId) throws ValidationException {
		if (!(checkIfGenderExists(genderId))) {
			throw new ValidationException("Category with ID " + genderId + " does not exist");
		}
	}

}
