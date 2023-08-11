package in.fssa.myfashionstudioapp;

import java.util.List;

import in.fssa.myfashionstudioapp.model.Price;
import in.fssa.myfashionstudioapp.validator.SizeValidator;

public class PriceValidator {

	// rejectIfInvalidPrice

	public static void rejectIfInvalidPrice(Double price) {
		if (price == null) {
			throw new RuntimeException("Price cannot be null");
		}
		if (price < 0.0d || price > 10000.0d) {
			throw new RuntimeException("Invalid price input");
		}
	}

	// validate price for creating price
	public static void Validate(Price price) { // {} => { price, size }

		PriceValidator.rejectIfInvalidPrice(price.getPrice());

	}

	public static void ValidateAll(List<Price> priceList) {

		for (Price price : priceList) { // [ {}, {}, {} ]

			PriceValidator.rejectIfInvalidPrice(price.getPrice()); // {} => { price size }
			SizeValidator.rejectIfInvalidSize(price.getSize().getId());
			SizeValidator.rejectIfSizeNotExists(price.getSize().getId());

		}
	}

}
