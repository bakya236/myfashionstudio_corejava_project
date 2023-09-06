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
	public static void rejectIfInvalidCategory(int CategoryId) throws ValidationException {

		if (CategoryId < 0) {
			throw new ValidationException("Invalid catgeory input");
		}

	}

	// create category if the category id exists in category table

	// return true is exists

	private static boolean checkIfCategeoryExits(int CategoryId) throws ValidationException {
		boolean flag;
		try {
			CategoryDAO categoryDAO = new CategoryDAO();
			flag = categoryDAO.categoryAlreadyExists(CategoryId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

		return flag;
	}

	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public static void rejectIfCategoryNotExists(int CategoryId) throws ValidationException {
		if (!(checkIfCategeoryExits(CategoryId))) {
			throw new ValidationException("Category with ID " + CategoryId + " does not exist");
		}
	}

}
