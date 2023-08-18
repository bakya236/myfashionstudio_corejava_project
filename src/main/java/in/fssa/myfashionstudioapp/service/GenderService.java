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
	public List<Gender> findAll() throws ServiceException {

		try {
			GenderDAO genderDao = new GenderDAO();
			return genderDao.findAll();
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
	 * @throws ValidationException
	 * @throws ServiceException
	 */

	public Gender findGenderBygenderId(int id) throws ValidationException, ServiceException {

		try {
			GenderValidator.validate(id);
			GenderDAO genderDao = new GenderDAO();

			return genderDao.findById(id);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

}
