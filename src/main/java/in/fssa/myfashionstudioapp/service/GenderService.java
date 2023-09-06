package in.fssa.myfashionstudioapp.service;

import java.util.List;

import in.fssa.myfashionstudioapp.dao.GenderDAO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.exception.ServiceException;
import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.Gender;
import in.fssa.myfashionstudioapp.validator.GenderValidator;

public class GenderService {
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<Gender> getAllGenders() throws ServiceException {

		List<Gender> genderList;
		try {
			GenderDAO genderDAO = new GenderDAO();
			genderList = genderDAO.findAll();
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return genderList;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */

	public Gender findGenderBygenderId(int id) throws ValidationException, ServiceException {

		try {
			GenderValidator.rejectIfInvalidGender(id);
			GenderDAO genderDAO = new GenderDAO();

			return genderDAO.findById(id);
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

}
