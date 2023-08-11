package in.fssa.myfashionstudioapp.service;

import java.util.List;

import in.fssa.myfashionstudioapp.dao.CategoryDAO;
import in.fssa.myfashionstudioapp.model.Category;
import in.fssa.myfashionstudioapp.validator.CategoryValidator;

public class CategoryService {

	public List<Category> findAllCatgegoryByGenderId(int id) throws Exception {

		CategoryValidator.validate(id);
		CategoryDAO categoryDao = new CategoryDAO();

		List<Category> categorylist = categoryDao.findAllCatgegoryByGenderId(id);

		return categorylist;

	}

}
