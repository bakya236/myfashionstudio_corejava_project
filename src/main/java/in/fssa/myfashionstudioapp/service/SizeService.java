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
	public List<Size> FindAllSizes() throws ServiceException {

		try {
			SizeDAO sizeDao = new SizeDAO();
			return sizeDao.findAllSize();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public Size FindSizeBySizeId(int id) throws ValidationException, ServiceException {

		try {
			SizeValidator.rejectIfInvalidSize(id);

			SizeDAO sizeDao = new SizeDAO();
			return sizeDao.FindSizeBySizeId(id);
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
}
