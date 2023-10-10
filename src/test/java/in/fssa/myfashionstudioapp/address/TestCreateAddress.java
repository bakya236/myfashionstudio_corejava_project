package in.fssa.myfashionstudioapp.address;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.Address;
import in.fssa.myfashionstudioapp.model.User;
import in.fssa.myfashionstudioapp.service.AddressService;
import in.fssa.myfashionstudioapp.validator.address.AddressValidatorErrors;

public class TestCreateAddress {

	private AddressService addressService;

	@BeforeEach
	public void setUp() {
		addressService = new AddressService();
	}

	private Address createValidAddress() {

		// Create a sample Address object
		Address address = new Address();
		address.setAddress("no,13 radhakrishnan street");
		address.setState("Tamil nadu");
		address.setCountry("India");
		address.setCity("chennai");
		address.setLandMark("play school");
		address.setTitle("home");
		address.setPincode(600133);
		address.setUser(new User(1));

		return address;
	}

	private void assertValidationExceptionThrown(Address address, String expectedErrorMessage) {
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.createAddress(address);
		});

		String actualErrorMessage = exception.getMessage();
		assertEquals(expectedErrorMessage, actualErrorMessage);
		System.out.println(actualErrorMessage);
	}

	@Test
	public void testCreateAddressWithValidInput() {
		// Create a sample Address object
		Address address = createValidAddress();

		// Use assertDoesNotThrow to ensure that createAddress does not throw an
		// exception
		assertDoesNotThrow(() -> {
			addressService.createAddress(address);
		});

	}

	@Test
	public void testCreateAddressWithInValidInput() {

		Address address = null;

		assertValidationExceptionThrown(address, AddressValidatorErrors.INVALID_ADDRESS);

	}

	@Test
	public void testCreateAddressWithUserAsNull() {
		Address address = createValidAddress();
		address.setUser(null);

		assertValidationExceptionThrown(address, AddressValidatorErrors.INVALID_USER);

	};

	@Test
	public void testCreateAddressWithNonExistingUser() {
		Address address = createValidAddress();
		int id = 90;
		address.getUser().setId(id);

		assertValidationExceptionThrown(address, "User with ID " + id + " does not exist");

	};

	@Test
	public void testCreateAddressWithAddressAsNull() {
		Address address = createValidAddress();
		address.setAddress(null);

		assertValidationExceptionThrown(address, AddressValidatorErrors.EMPTY_ADDRESS_INPUT);

	};

	@Test
	public void testCreateAddressWithAddressAsEmpty() {
		Address address = createValidAddress();
		address.setAddress("");

		assertValidationExceptionThrown(address, AddressValidatorErrors.EMPTY_ADDRESS_INPUT);

	};

	@Test
	public void testCreateAddressWithCityAsNull() {
		Address address = createValidAddress();
		address.setCity(null);

		assertValidationExceptionThrown(address, AddressValidatorErrors.EMPTY_CITY_INPUT);
	}

	@Test
	public void testCreateAddressWithCityAsEmpty() {
		Address address = createValidAddress();
		address.setCity("");

		assertValidationExceptionThrown(address, AddressValidatorErrors.EMPTY_CITY_INPUT);
	}

	@Test
	public void testCreateAddressWithLandmarkAsNull() {
		Address address = createValidAddress();
		address.setLandMark(null);

		assertValidationExceptionThrown(address, AddressValidatorErrors.EMPTY_LANDMARK_INPUT);
	}

	@Test
	public void testCreateAddressWithLandmarkAsEmpty() {
		Address address = createValidAddress();
		address.setLandMark("");

		assertValidationExceptionThrown(address, AddressValidatorErrors.EMPTY_LANDMARK_INPUT);
	}

	@Test
	public void testCreateAddressWithStateAsNull() {
		Address address = createValidAddress();
		address.setState(null);

		assertValidationExceptionThrown(address, AddressValidatorErrors.EMPTY_STATE_INPUT);
	}

	@Test
	public void testCreateAddressWithStateAsEmpty() {
		Address address = createValidAddress();
		address.setState("");

		assertValidationExceptionThrown(address, AddressValidatorErrors.EMPTY_STATE_INPUT);
	}

	@Test
	public void testCreateAddressWithCountryAsNull() {
		Address address = createValidAddress();
		address.setCountry(null);

		assertValidationExceptionThrown(address, AddressValidatorErrors.EMPTY_COUNTRY_INPUT);
	}

	@Test
	public void testCreateAddressWithCountryAsEmpty() {
		Address address = createValidAddress();
		address.setCountry("");

		assertValidationExceptionThrown(address, AddressValidatorErrors.EMPTY_COUNTRY_INPUT);
	}

	@Test
	public void testCreateAddressWithInvalidPincode() {
		Address address = createValidAddress();
		address.setPincode(900121);

		assertValidationExceptionThrown(address, AddressValidatorErrors.INVALID_PINCODE_INPUT);
	}

}
