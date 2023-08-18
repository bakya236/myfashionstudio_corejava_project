package in.fssa.myfashionstudioapp.service;

import java.util.List;

import in.fssa.myfashionstudioapp.dao.CategoryDAO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.exception.ServiceException;
import in.fssa.myfashionstudioapp.model.Category;
import in.fssa.myfashionstudioapp.validator.CategoryValidator;

public class CategoryService {

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 */

	public List<Category> findAllCatgegoryByGenderId(int id) throws ServiceException {

		try {
			CategoryValidator.rejectIfCategoryNotExists(id);

			CategoryDAO categoryDao = new CategoryDAO();

			List<Category> categorylist = categoryDao.findAllCatgegoryByGenderId(id);

			return categorylist;
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 */

	public Category findCategoryByCategoryId(int id) throws ServiceException {

		try {
			CategoryValidator.rejectIfCategoryNotExists(id);

			CategoryDAO categoryDao = new CategoryDAO();

			return categoryDao.findCategoryByCategoryId(id);

		} catch (PersistenceException e) {

			e.printStackTrace();

			throw new ServiceException(e.getMessage());
		}

	}

}
