package in.fssa.myfashionstudioapp.validator;

import in.fssa.myfashionstudioapp.dao.ProductDAO;
import in.fssa.myfashionstudioapp.model.Product;

public class ProductValidator {

	/**
	 * 
	 * @param productId
	 * @throws RuntimeException
	 */
	public static void rejectIfInvalidproduct(int productId) throws RuntimeException {

		if (productId < 0) {
			throw new RuntimeException("Invalid product id input");
		}

	}

	public static boolean checkIfProductExits(int productId) {
		ProductDAO productDao = new ProductDAO();
		return productDao.productAldreadyExists(productId);
	}

	/**
	 * 
	 * @param productId
	 */
	public static void rejectIfProductNotExists(int productId) {

		if (!(checkIfProductExits(productId))) {
			throw new RuntimeException("Product Id with ID " + productId + " does not exist");
		}
	}

	/**
	 * 
	 * @param newProduct
	 * @throws RuntimeException
	 */
	public static void validateAll(Product newProduct) throws RuntimeException {

		if (newProduct == null) {
			throw new RuntimeException("Product cannot be Null");
		}
		if (newProduct.getName() == null || "".equals(newProduct.getName())) {
			throw new RuntimeException("Product name cannot be null or empty");
		}
		if (newProduct.getDescription() == null || "".equals(newProduct.getDescription())) {
			throw new RuntimeException("Product description cannot be null or empty");
		}

	}

}
