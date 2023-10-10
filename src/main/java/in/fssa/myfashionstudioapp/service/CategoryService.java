package in.fssa.myfashionstudioapp.service;

import java.util.ArrayList;
import java.util.List;

import in.fssa.myfashionstudioapp.dao.CategoryDAO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.exception.ServiceException;
import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.Category;
import in.fssa.myfashionstudioapp.model.Gender;
import in.fssa.myfashionstudioapp.validator.CategoryValidator;

public class CategoryService {

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */

	public List<Category> findAllCategoriesByGenderId(int genderId) throws ValidationException, ServiceException {

		// return null
		List<Category> categoryList = new ArrayList<>();
		try {
			CategoryValidator.rejectIfCategoryNotExists(genderId);

			CategoryDAO categoryDAO = new CategoryDAO();

			categoryList = categoryDAO.findAllByGenderId(genderId);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return categoryList;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */

	public Category findCategoryByCategoryId(int categoryId) throws ValidationException, ServiceException {

		Category category = null;
		try {
			CategoryValidator.rejectIfCategoryNotExists(categoryId);

			CategoryDAO categoryDAO = new CategoryDAO();

			category = categoryDAO.findByCategoryId(categoryId);

			GenderService genderService = new GenderService();
			Gender gender = genderService.findGenderBygenderId(category.getGender().getId());

			category.getGender().setName(gender.getName());

		} catch (PersistenceException e) {

			e.printStackTrace();

			throw new ServiceException(e.getMessage());
		}

		return category;

	}

}
