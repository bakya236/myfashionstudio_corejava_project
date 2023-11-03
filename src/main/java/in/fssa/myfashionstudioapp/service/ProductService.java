package in.fssa.myfashionstudioapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import in.fssa.myfashionstudioapp.dao.ProductDAO;
import in.fssa.myfashionstudioapp.dto.ProductDTO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.exception.ServiceException;
import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.Category;
import in.fssa.myfashionstudioapp.model.Price;
import in.fssa.myfashionstudioapp.model.Product;
import in.fssa.myfashionstudioapp.validator.CategoryValidator;
import in.fssa.myfashionstudioapp.validator.GenderValidator;
import in.fssa.myfashionstudioapp.validator.PriceValidator;
import in.fssa.myfashionstudioapp.validator.ProductValidator;

public class ProductService {

	/**
	 * 
	 * @param newProduct
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void createProduct(ProductDTO newProduct) throws ValidationException, ServiceException {

		try {

			// validation
			ProductValidator.validateAll(newProduct);

			List<Price> priceList = newProduct.getPriceList();
			PriceValidator.validateAll(priceList);

			ProductDAO productDAO = new ProductDAO();

			// create the product, get the generated product id

			int productId = productDAO.create(newProduct);

			PriceService priceService = new PriceService();

			for (Price price : priceList) {

				Product product = new Product();
				product.setId(productId);

				price.setProduct(product);

				priceService.createPrice(price);
			}

			System.out.println("product and its prices crceated successfully");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Error creating product and prices: " + e.getMessage());
		}

	}

	/**
	 * Retrieves a list of all products from the database.
	 * 
	 * @param offset
	 * @param limit
	 *
	 * @return A list of ProductDTO objects containing product information.
	 * @throws ValidationException If validation of data fails.
	 * @throws ServiceException    If an error occurs while retrieving products.
	 */

	public List<ProductDTO> getAllProducts(int limit, int offset) throws ValidationException, ServiceException {

		List<ProductDTO> productDTOList = new ArrayList<>();

		try {

			ProductDAO productDAO = new ProductDAO();

			productDTOList = productDAO.findAll(limit, offset); // [{id,name,description,category},{},{}]

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Error finding all products : " + e.getMessage());
		}

		return productDTOList;

	}

	/**
	 * Retrieves detailed information about a product based on its unique identifier
	 * (ID) from the database.
	 *
	 * This method fetches detailed information about a product, including its name,
	 * description, category,color and associated price list, from the database. It
	 * performs validation checks to ensure the provided product ID is valid and
	 * that the product exists in the database.
	 *
	 * @param productId The unique identifier (ID) of the product for which details
	 *                  are to be retrieved.
	 * @return A ProductDTO object containing comprehensive product information,
	 *         including its name, description, category, color and a list of
	 *         prices.
	 * @throws ValidationException If the provided product ID is invalid or does not
	 *                             meet the required validation criteria.
	 * @throws ServiceException    If an error occurs during the retrieval of
	 *                             product details from the database, such as a
	 *                             database connection issue or query error.
	 */
	public ProductDTO findProductDetailsByProductId(int productId) throws ValidationException, ServiceException {

		ProductDTO productDTO = null;

		try {

			ProductValidator.rejectIfInvalidProduct(productId);
			ProductValidator.rejectIfProductNotExists(productId);

			ProductDAO productDAO = new ProductDAO();

			productDTO = productDAO.findByProducId(productId); // {name,description,category,pricelist[]}

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Error finding product details : " + e.getMessage());
		}
		return productDTO;

	}

	/**
	 * Retrieves a list of products that belong to a specific category from the
	 * database.
	 *
	 * This method retrieves a list of products that are categorized under a
	 * specific category based on its unique identifier (ID) from the database. It
	 * performs validation checks to ensure the provided category ID is valid and
	 * that the category exists in the database.
	 *
	 * @param categoryId The unique identifier (ID) of the category for which
	 *                   products are to be retrieved.
	 * @return A list of ProductDTO objects containing information about products
	 *         within the specified category.
	 * @throws ValidationException If the provided category ID is invalid or does
	 *                             not meet the required validation criteria.
	 * @throws ServiceException    If an error occurs during the retrieval of
	 *                             products by category ID, such as a database
	 *                             connection issue or query error.
	 */

	public List<ProductDTO> findAllProductsByCategoryId(int categoryId, int startId, int endId)
			throws ValidationException, ServiceException {

		List<ProductDTO> productDTOList = new ArrayList<>();

		try {

			CategoryValidator.rejectIfInvalidCategory(categoryId);

			// business validation
			CategoryValidator.rejectIfCategoryNotExists(categoryId);

			ProductDAO productDAO = new ProductDAO();

			productDTOList = productDAO.findAllByCategoryId(categoryId, startId, endId);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Error finding all products by category id: " + e.getMessage());
		}

		return productDTOList;

	}

	/**
	 * 
	 * @param id
	 * @param updatedProduct
	 * @throws ValidationException
	 * @throws ServiceException
	 * @throws com.google.protobuf.ServiceException
	 */
	public void updateProduct(ProductDTO updatedProduct) // updatedproduct
															// {name,description,pricelist}
			throws ValidationException, ServiceException {

		try {

			ProductValidator.validateAll(updatedProduct);

			int productId = updatedProduct.getId();

			// validation
			ProductValidator.rejectIfInvalidProduct(productId);

//			business validation - reject If Product Not Exists
			ProductValidator.rejectIfProductNotExists(productId);

			int categoryId = updatedProduct.getCategory().getId();

			CategoryValidator.rejectIfInvalidCategory(categoryId);
			CategoryValidator.rejectIfCategoryNotExists(categoryId);

			// find the category

			CategoryService categoryService = new CategoryService();
			Category category = categoryService.findCategoryByCategoryId(categoryId);

			int genderId = category.getGender().getId();

			GenderValidator.rejectIfInvalidGender(genderId);
			GenderValidator.rejectIfGenderNotExists(genderId);

			// validating the price in price service - update price()
			List<Price> priceList = updatedProduct.getPriceList();

			PriceValidator.validateAll(priceList);

			ProductDAO productDAO = new ProductDAO();

			productDAO.updateProductDetails(updatedProduct);

			PriceService priceService = new PriceService();

			for (Price price : priceList) {

				int sizeId = price.getSize().getId();

				Price pricefromProdIdAndSizeId = priceService.findPriceByProductIdAndSizeId(productId, sizeId);

				if (pricefromProdIdAndSizeId != null) {
					if (pricefromProdIdAndSizeId.getPrice() != price.getPrice()) {

						System.out.println(pricefromProdIdAndSizeId);

						LocalDateTime date = LocalDateTime.now();

						int priceId = pricefromProdIdAndSizeId.getId(); // null error
						priceService.changePrice(priceId, price, date);
					}
				} else {
					priceService.createPrice(price);
				}

			}

			System.out.println("product and its prices updated successfully");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Error updating product and prices: " + e.getMessage());
		}

	}

	public void deleteProduct(int id) throws ServiceException, ValidationException {

		// validation
		ProductValidator.rejectIfInvalidProduct(id);

		ProductValidator.rejectIfProductNotExists(id);

		ProductDAO productDAO = new ProductDAO();

		try {
			productDAO.delete(id);
			System.out.println("product has been deactivated successfully");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Error deleting product: " + e.getMessage());
		}

	}

}
