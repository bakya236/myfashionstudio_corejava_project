package in.fssa.myfashionstudioapp.service;

import java.util.List;

import in.fssa.myfashionstudioapp.dao.SizeDAO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.exception.ServiceException;
import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.Size;
import in.fssa.myfashionstudioapp.validator.SizeValidator;

public class SizeService {

	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<Size> getAllSizes() throws ServiceException {

		try {
			SizeDAO sizeDAO = new SizeDAO();
			return sizeDAO.findAll();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ValidationException
	 */
	public Size FindSizeBySizeId(int SizeId) throws ValidationException, ServiceException {

		try {
			SizeValidator.rejectIfInvalidSize(SizeId);

			SizeDAO sizeDAO = new SizeDAO();
			return sizeDAO.FindById(SizeId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
}
