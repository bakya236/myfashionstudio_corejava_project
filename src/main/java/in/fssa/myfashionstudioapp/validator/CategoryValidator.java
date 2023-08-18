package in.fssa.myfashionstudioapp.validator;

import in.fssa.myfashionstudioapp.dao.CategoryDAO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;

public class CategoryValidator {

	/**
	 * 
	 * @param id
	 * @throws Exception
	 */
	public static void rejectIfInvalidCategory(int id) throws Exception {

		if (id < 0) {
			throw new RuntimeException("Invalid id");
		}

	}

	// create category if the category id exists in category table

	// return true is exists

	private static boolean checkIfCategeoryExits(int id) throws PersistenceException {
		CategoryDAO categoryDao = new CategoryDAO();
		return categoryDao.categoryAldreadyExists(id);
	}

	/**
	 * 
	 * @param id
	 * @throws PersistenceException
	 */
	public static void rejectIfCategoryNotExists(int id) throws PersistenceException {
		if (!(checkIfCategeoryExits(id))) {
			throw new RuntimeException("Category with ID " + id + " does not exist");
		}
	}

}
