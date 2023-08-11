package in.fssa.myfashionstudioapp.validator;

import in.fssa.myfashionstudioapp.model.Product;

public class ProductValidator {

	public static void validateAll(Product newProduct) {

		if (newProduct == null) {
			throw new RuntimeException("Product cannot be Null");
		}
		if (newProduct.getName() == null || "".equals(newProduct.getName())) {
			throw new RuntimeException("product name cannot be null or empty");
		}
		if (newProduct.getDescription() == null || "".equals(newProduct.getDescription())) {
			throw new RuntimeException("product description cannot be null or empty");
		}

		// business validation - category aldready exists
		CategoryValidator.rejectIfCategoryNotExists(newProduct.getCategory().getId());

	}

}
