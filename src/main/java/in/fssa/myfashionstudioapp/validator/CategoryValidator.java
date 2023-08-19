package in.fssa.myfashionstudioapp.validator;

import in.fssa.myfashionstudioapp.dao.CategoryDAO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.exception.ValidationException;

public class CategoryValidator {

	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidCategory(int id) throws ValidationException {

		if (id < 0) {
			throw new ValidationException("Invalid catgeory input");
		}

	}

	// create category if the category id exists in category table

	// return true is exists

	private static boolean checkIfCategeoryExits(int id) throws ValidationException {
		try {
			CategoryDAO categoryDao = new CategoryDAO();
			return categoryDao.categoryAlreadyExists(id);
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public static void rejectIfCategoryNotExists(int id) throws ValidationException {
		if (!(checkIfCategeoryExits(id))) {
			throw new ValidationException("Category with ID " + id + " does not exist");
		}
	}

}
