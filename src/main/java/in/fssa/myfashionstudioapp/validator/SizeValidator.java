package in.fssa.myfashionstudioapp.validator;

import in.fssa.myfashionstudioapp.dao.SizeDAO;

public class SizeValidator {

	public static void rejectIfInvalidSize(int sizeId) {

		if (sizeId < 0) {
			throw new RuntimeException("Invalid size input");
		}

	}

//	business validation

	public static boolean checkIfSizeExits(int sizeId) {
		SizeDAO sizeDao = new SizeDAO();
		return sizeDao.sizeAldreadyExists(sizeId);
	}

	public static void rejectIfSizeNotExists(int sizeId) {

		if (!(checkIfSizeExits(sizeId))) {
			throw new RuntimeException("Size with ID " + sizeId + " does not exist");
		}
	}

}
