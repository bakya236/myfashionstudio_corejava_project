package in.fssa.myfashionstudioapp.service;

import in.fssa.myfashionstudioapp.dao.SizeDAO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.exception.ServiceException;
import in.fssa.myfashionstudioapp.model.Size;
import in.fssa.myfashionstudioapp.validator.SizeValidator;

public class SizeService {
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public Size FindSizeBySizeId(int id) throws ServiceException {

		try {
			SizeValidator.rejectIfInvalidSize(id);
			SizeDAO sizeDao = new SizeDAO();
			return sizeDao.FindSizeBySizeId(id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
}
