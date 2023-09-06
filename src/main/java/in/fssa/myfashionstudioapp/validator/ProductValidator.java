package in.fssa.myfashionstudioapp.validator;

import java.util.regex.Pattern;

import in.fssa.myfashionstudioapp.dao.ProductDAO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.Product;
import in.fssa.myfashionstudioapp.util.StringUtil;

public class ProductValidator {

	/**
	 * 
	 * @param newProduct
	 * @throws
	 * @throws RuntimeException
	 */
	public static void validateAll(Product newProduct) throws ValidationException {

		if (newProduct == null) {
			throw new ValidationException("Product cannot be null");
		}

		StringUtil.rejectIfInvalidString(newProduct.getName(), "Product name");
		StringUtil.rejectIfInvalidString(newProduct.getDescription(), "Product description");

		// pattern

		ProductValidator.rejectIfInvalidProductName(newProduct.getName());
		ProductValidator.rejectIfInvalidDescription(newProduct.getDescription());

		int categoryId = newProduct.getCategory().getId();

		// TODO null check

		CategoryValidator.rejectIfInvalidCategory(categoryId);
		CategoryValidator.rejectIfCategoryNotExists(categoryId);

		ProductValidator.rejectIfDuplicateProduct(newProduct);

	}

	/**
	 * 
	 * @param productId
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidProduct(int productId) throws ValidationException {

		if (productId < 0) {
			throw new ValidationException("Invalid product input");
		}

	}

	public static void rejectIfDuplicateProduct(Product product) throws ValidationException {

		if (!(checkIfDuplicateProductExists(product))) {
			throw new ValidationException("This product with same name , description ,category already exists");
		}
	}

	private static boolean checkIfProductExists(int productId) throws ValidationException {
		try {
			ProductDAO productDAO = new ProductDAO();
			return productDAO.productAlreadyExists(productId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param productId
	 * @throws ValidationException
	 */
	public static void rejectIfProductNotExists(int productId) throws ValidationException {

		if (!(checkIfProductExists(productId))) {
			throw new ValidationException("Product with ID " + productId + " does not exist");
		}
	}

	private static void rejectIfInvalidProductName(String productName) throws ValidationException {
		final String NAME_PATTERN = "^[A-Za-z0-9\\s\\-_'.,&()]+$";

		if (!Pattern.matches(NAME_PATTERN, productName)) {
			throw new ValidationException(
					"Invalid product name. product name conatins invalid special characters like @ , &  ,* , $ , # ,\"");
		}

		int minLength = 3;
		int maxLength = 50;

		if (productName.length() < minLength || productName.length() > maxLength) {
			throw new ValidationException(
					"Product name should be between " + minLength + " and " + maxLength + " characters.");
		}
	}

	private static void rejectIfInvalidDescription(String productDescription) throws ValidationException {
		final String DESCRIPTION_PATTERN = "^[A-Za-z0-9\\s\\-_.]+$";

		int minLength = 10;
		int maxLength = 1000;

		if (productDescription.length() < minLength || productDescription.length() > maxLength) {
			throw new ValidationException(
					"Product description should be between " + minLength + " and " + maxLength + " characters.");
		}

		if (!Pattern.matches(DESCRIPTION_PATTERN, productDescription)) {
			throw new ValidationException(
					"Invalid product description. Description should only contain letters, digits, - _ . , () & ! ? \\\" '\"");
		}

	}

	private static boolean checkIfDuplicateProductExists(Product product) throws ValidationException {

		boolean flag;
		try {
			ProductDAO productDAO = new ProductDAO();
			flag = productDAO.DuplicateProductDoesNotAlreadyExists(product);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

		return flag;
	}

}
