package in.fssa.myfashionstudioapp.validator;

import in.fssa.myfashionstudioapp.dao.SizeDAO;

public class SizeValidator {
	/**
	 * 
	 * @param sizeId
	 * @throws RuntimeException
	 */
	public static void rejectIfInvalidSize(int sizeId) throws RuntimeException {

		if (sizeId < 0) {
			throw new RuntimeException("Invalid size input");
		}

	}

//	business validation

	public static boolean checkIfSizeExits(int sizeId) {
		SizeDAO sizeDao = new SizeDAO();
		return sizeDao.SizeAldreadyExists(sizeId);
	}

	/**
	 * 
	 * @param sizeId
	 * @throws RuntimeException
	 */
	public static void rejectIfSizeNotExists(int sizeId) throws RuntimeException {

		if (!(checkIfSizeExits(sizeId))) {
			throw new RuntimeException("Size with ID " + sizeId + " does not exist");
		}
	}

}
