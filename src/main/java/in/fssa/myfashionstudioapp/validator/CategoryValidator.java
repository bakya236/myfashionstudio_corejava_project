package in.fssa.myfashionstudioapp.validator;

import in.fssa.myfashionstudioapp.dao.CategoryDAO;

public class CategoryValidator {

	public static void rejectIfInvalidCategory(int id) throws Exception {

		if (id < 0) {
			throw new RuntimeException("Invalid id");
		}

	}

	// create category if the category id exists in category table

	// return true is exists

	private static boolean checkIfCategeoryExits(int id) {
		CategoryDAO categoryDao = new CategoryDAO();
		return categoryDao.categoryAldreadyExists(id);
	}

	public static void rejectIfCategoryNotExists(int id) {
		if (!(checkIfCategeoryExits(id))) {
			throw new RuntimeException("Category with ID " + id + " does not exist");
		}
	}

}
