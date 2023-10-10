package in.fssa.myfashionstudioapp.service;

import in.fssa.myfashionstudioapp.dao.AddressDAO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.exception.ServiceException;
import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.Address;
import in.fssa.myfashionstudioapp.validator.address.AddressValidator;

public class AddressService {

	public void createAddress(Address newAddress) throws ValidationException, ServiceException {

		AddressValidator.validateAddress(newAddress);

		AddressDAO addressDAO = new AddressDAO();

		try {
			addressDAO.create(newAddress);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Error creating address : " + e.getMessage());
		}

		System.out.println("Address created sucessfully");

	}

	public void updateAddress(Address newAddress) throws ValidationException, ServiceException {

//		TODO:VALIDATE ADDRESS

		/* AddressValidator.validateAddress(newAddress); */

		System.out.println("in service===========================>" + newAddress);

		AddressDAO addressDAO = new AddressDAO();

		try {
			addressDAO.create(newAddress);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Error creating address : " + e.getMessage());
		}

		System.out.println("Address created sucessfully");

	}

	public Address findAddressByUserId(int userId) throws ServiceException {

//		TODO: VALIDATE USER ID

		AddressDAO addressDAO = new AddressDAO();
		Address address = null;
		try {
			address = addressDAO.findByUserId(userId);
		} catch (PersistenceException e) {

			e.printStackTrace();

			throw new ServiceException(e.getMessage());
		}
		return address;

	}

}
