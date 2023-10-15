package in.fssa.myfashionstudioapp.address;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.model.Address;
import in.fssa.myfashionstudioapp.model.User;
import in.fssa.myfashionstudioapp.service.AddressService;

public class TestUpdateAddress {

	private AddressService addressService;

	@BeforeEach
	public void setUp() {
		addressService = new AddressService();
	}

	private Address createValidAddress() {

		// Create a sample Address object
		Address address = new Address();
		address.setId(1);
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

//	private void assertValidationExceptionThrown(Address address, String expectedErrorMessage) {
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			addressService.updateAddress(address);
//		});
//
//		String actualErrorMessage = exception.getMessage();
//		assertEquals(expectedErrorMessage, actualErrorMessage);
//		System.out.println(actualErrorMessage);
//	}

	@Test
	public void testCreateAddressWithValidInput() {
		// Create a sample Address object
		Address address = createValidAddress();

		// Use assertDoesNotThrow to ensure that createAddress does not throw an
		// exception
		assertDoesNotThrow(() -> {
			addressService.updateAddress(address);
		});

	}

}
