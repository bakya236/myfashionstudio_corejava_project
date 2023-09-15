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
import in.fssa.myfashionstudioapp.model.Gender;
import in.fssa.myfashionstudioapp.model.Price;
import in.fssa.myfashionstudioapp.model.Product;
import in.fssa.myfashionstudioapp.model.Size;
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
	 * 
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */

	public List<ProductDTO> getAllProducts() throws ValidationException, ServiceException {

		List<ProductDTO> productDTOList = new ArrayList<>();

		try {

			ProductDAO productDAO = new ProductDAO();

			List<ProductDTO> ProductList = productDAO.findAll(); // [{id,name,description,category},{},{}]

			for (Product product : ProductList) {

				// product ==> {id,name,description,category}

				ProductDTO productDTO = new ProductDTO();

				// set category name
				CategoryService categoryService = new CategoryService();
				Category category = categoryService.findCategoryByCategoryId(product.getCategory().getId());
				// set gender name
				GenderService genderService = new GenderService();

				Gender gender = genderService.findGenderBygenderId(category.getGender().getId());

				// set the gender name
				gender.setId(gender.getId());
				gender.setName(gender.getName());

				category.setGender(gender);

				List<Price> priceList = productDTO.getPriceList();

				PriceService priceService = new PriceService();
				Price eachPrice = priceService.FindFirstPriceByProductId(product.getId()); // {price,products_id,sizes_id,started_at,ended_at}

				// set size value
				SizeService sizeService = new SizeService();
				Size size = sizeService.FindSizeBySizeId(eachPrice.getSize().getId());
				eachPrice.setSize(size);

				priceList.add(eachPrice);

				productDTO.setId(product.getId());
				productDTO.setImage(product.getImage());
				productDTO.setName(product.getName());
				productDTO.setDescription(product.getDescription());
				productDTO.setCategory(category);
				productDTO.setPriceList(priceList);

				productDTOList.add(productDTO);

			}

			System.out.println("All products are sucessfully retrieved");

		} catch (ValidationException e) {
			// Handle validation errors (e.g., provide user-friendly error messages)
			throw new ValidationException("Validation error: " + e.getMessage());
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Error finding all products : " + e.getMessage());
		}

		return productDTOList;

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public ProductDTO findProductDetailsByProductId(int id) throws ValidationException, ServiceException {

		ProductDTO productDTO = null;

		try {

			ProductValidator.rejectIfInvalidProduct(id);
			ProductValidator.rejectIfProductNotExists(id);

			ProductDAO productDAO = new ProductDAO();

			productDTO = productDAO.findByProducId(id); // {name,description,category,pricelist[]}

			// set category name
			CategoryService categoryService = new CategoryService();
			Category category = categoryService.findCategoryByCategoryId(productDTO.getCategory().getId());

			category.setId(category.getId());
			category.setName(category.getName());

			// set gender name
			GenderService genderService = new GenderService();
			Gender gender = genderService.findGenderBygenderId(category.getGender().getId());
			category.setGender(gender);

			productDTO.setCategory(category);

			PriceService PriceService = new PriceService();
			productDTO.setPriceList(PriceService.FindAllPricesByProductId(id)); // pricelist[{id,size_id,price},{},{}]

			List<Price> priceList = productDTO.getPriceList();

			for (Price price : priceList) {
				SizeService sizeService = new SizeService();
				Size eachSize = sizeService.FindSizeBySizeId(price.getSize().getId());

				price.getSize().setId(eachSize.getId());
				price.getSize().setValue(eachSize.getValue());
			}

			// to sysout the product details
			System.out.println(productDTO);

			System.out.println("sucessfully retrieved the product details");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Error find product details : " + e.getMessage());
		}
		return productDTO;

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */

	public List<ProductDTO> findAllProductsByCategoryId(int id) throws ValidationException, ServiceException {

		List<ProductDTO> productDTOList = new ArrayList<>();

		try {

			CategoryValidator.rejectIfInvalidCategory(id);

			// business validation
			CategoryValidator.rejectIfCategoryNotExists(id);

			ProductDAO productDAO = new ProductDAO();

			List<ProductDTO> ProductList = productDAO.findAllByCategoryId(id); // [{id,name,description,category,pricelist},{},{}]

			for (Product product : ProductList) {

				ProductDTO productDTO = new ProductDTO();

				List<Price> priceList = productDTO.getPriceList(); // []

				PriceService priceService = new PriceService();
				Price eachPrice = priceService.FindFirstPriceByProductId(product.getId());
				eachPrice.getSize().getId();

//				

				priceList.add(eachPrice);

				productDTO.setId(product.getId());
				productDTO.setImage(product.getImage());
				productDTO.setName(product.getName());
				productDTO.setDescription(product.getDescription());
				productDTO.setCategory(product.getCategory());
				productDTO.setPriceList(priceList);

				productDTOList.add(productDTO);

			}

			System.out.println("All products are sucessfully retrieved");

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Error find all products : " + e.getMessage());
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
