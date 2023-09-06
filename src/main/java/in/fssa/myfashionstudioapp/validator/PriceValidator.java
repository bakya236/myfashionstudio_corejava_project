package in.fssa.myfashionstudioapp.validator;

import java.util.List;

import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.Price;

public class PriceValidator {

	// rejectIfInvalidPrice

	/**
	 * 
	 * @param price
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidPrice(Price price) throws ValidationException {
		if (price == null) {
			throw new ValidationException("price cannot be null");
		}

		if (price.getPrice() <= 0.0d) {
			throw new ValidationException("Invalid price input");
		}

		if (price.getPrice() >= 1000000.0d) {
			throw new ValidationException("Product price must not exceed 1,000,000 rupees.");
		}
	}

	// validate price for creating price

	/**
	 * 
	 * @param price
	 * @throws ValidationException
	 */
	public static void validate(Price price) throws ValidationException { // {} => { price, size }

		PriceValidator.rejectIfInvalidPrice(price);

	}

	/**
	 * 
	 * @param priceList
	 * @throws ValidationException
	 */

	public static void validateAll(List<Price> priceList) throws ValidationException {

		for (Price price : priceList) { // [ {}, {}, {} ]
			PriceValidator.rejectIfInvalidPrice(price); // {} => { price size }

			SizeValidator.rejectIfInvalidSize(price.getSize().getId());
			// business validation - reject If Size Not Exists
			SizeValidator.rejectIfSizeNotExists(price.getSize().getId());
		}
	}

}
