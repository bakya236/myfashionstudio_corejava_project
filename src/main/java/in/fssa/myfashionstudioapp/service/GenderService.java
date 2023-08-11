package in.fssa.myfashionstudioapp.service;

import java.util.List;

import in.fssa.myfashionstudioapp.dao.GenderDAO;
import in.fssa.myfashionstudioapp.model.Gender;
import in.fssa.myfashionstudioapp.validator.GenderValidator;

public class GenderService {

	public List<Gender> findAll() throws Exception {

		GenderDAO genderDao = new GenderDAO();
		return genderDao.findAll();

	}

	public Gender findById(int id) throws Exception {

		GenderValidator.validate(id);
		GenderDAO genderDao = new GenderDAO();

		return genderDao.findById(id);
	}

}
