package in.fssa.myfashionstudioapp.validator;

import in.fssa.myfashionstudioapp.dao.SizeDAO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.exception.ValidationException;

public class SizeValidator {
	/**
	 * 
	 * @param sizeId
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidSize(int sizeId) throws ValidationException {

		if (sizeId < 0) {
			throw new ValidationException("Invalid size input");
		}

	}

//	business validation

	private static boolean checkIfSizeExists(int sizeId) throws ValidationException {
		boolean flag;
		try {
			SizeDAO sizeDAO = new SizeDAO();
			flag = sizeDAO.sizeAlreadyExists(sizeId);
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
		return flag;
	}

	/**
	 * 
	 * @param sizeId
	 * @throws ValidationException
	 */
	public static void rejectIfSizeNotExists(int sizeId) throws ValidationException {

		if (!(checkIfSizeExists(sizeId))) {
			throw new ValidationException("Size with ID " + sizeId + " does not exist");
		}
	}

}
