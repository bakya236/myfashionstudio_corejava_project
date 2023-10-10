package in.fssa.myfashionstudioapp.validator.address;

import in.fssa.myfashionstudioapp.dao.AddressDAO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.Address;
import in.fssa.myfashionstudioapp.util.IntUtil;
import in.fssa.myfashionstudioapp.util.NullUtil;
import in.fssa.myfashionstudioapp.util.StringUtil;
import in.fssa.myfashionstudioapp.validator.UserValidator;

public class AddressValidator {

	public static void validateAddress(Address address) throws ValidationException {

		NullUtil.rejectIfNull(address, AddressValidatorErrors.INVALID_ADDRESS);

		NullUtil.rejectIfNull(address.getUser(), AddressValidatorErrors.INVALID_USER);
		UserValidator.rejetcIfUserDoesNotExists(address.getUser().getId());

		StringUtil.rejectIfInvalidString(address.getAddress(), "Address");
		StringUtil.rejectIfInvalidString(address.getCity(), "City");
		StringUtil.rejectIfInvalidString(address.getLandMark(), "Landmark");
		StringUtil.rejectIfInvalidString(address.getState(), "State");
		StringUtil.rejectIfInvalidString(address.getCountry(), "Country");

		AddressValidator.rejectIfInvalidPincode(address.getPincode());

	}

	private static boolean checkIfAddressAlreadyExists(int addressId) throws ValidationException {
		boolean flag;
		try {
			IntUtil.rejectIfInvalidId(addressId, "AddressId");
			AddressDAO addressDao = new AddressDAO();
			flag = addressDao.isAddressAlreadyExists(addressId);
		} catch (PersistenceException e) {
			throw new ValidationException(e.getMessage());
		}
		return flag;
	}

	public static void rejectIfAddressDoesNotExists(int addressId) throws ValidationException {

		if (!checkIfAddressAlreadyExists(addressId)) {
			throw new ValidationException("Address with ID " + addressId + " does not exist");
		}
	}

	public static void rejectIfInvalidPincode(int pincode) throws ValidationException {

		if (!(pincode >= 600001 && pincode <= 699999)) {
			throw new ValidationException("Invalid Pincode. This pincode is not available for delivery.");
		}

	}

}
