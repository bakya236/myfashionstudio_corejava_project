package in.fssa.myfashionstudioapp.validator;

import in.fssa.myfashionstudioapp.dao.ProductDAO;
import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.Product;

public class ProductValidator {

	/**
	 * 
	 * @param productId
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidproduct(int productId) throws ValidationException {

		if (productId < 0) {
			throw new ValidationException("Invalid product input");
		}

	}

	private static boolean checkIfProductExits(int productId) {
		ProductDAO productDao = new ProductDAO();
		return productDao.productAldreadyExists(productId);
	}

	/**
	 * 
	 * @param productId
	 * @throws ValidationException
	 */
	public static void rejectIfProductNotExists(int productId) throws ValidationException {

		if (!(checkIfProductExits(productId))) {
			throw new ValidationException("Product Id with ID " + productId + " does not exist");
		}
	}

	/**
	 * 
	 * @param newProduct
	 * @throws RuntimeException
	 */
	public static void validateAll(Product newProduct) throws ValidationException {

		if (newProduct == null) {
			throw new ValidationException("Product cannot be Null");
		}
		if (newProduct.getName() == null || "".equals(newProduct.getName())) {
			throw new ValidationException("Product name cannot be null or empty");
		}
		if (newProduct.getDescription() == null || "".equals(newProduct.getDescription())) {
			throw new ValidationException("Product description cannot be null or empty");
		}

	}

}
