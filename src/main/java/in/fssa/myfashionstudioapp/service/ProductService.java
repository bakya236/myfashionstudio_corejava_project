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
	 * @throws com.google.protobuf.ServiceException
	 * @throws com.google.protobuf.ServiceException
	 */
	public void createProductWithPrices(ProductDTO newProduct) throws ValidationException, ServiceException {

		try {

			// validation
			ProductValidator.validateAll(newProduct);

			int categoryId = newProduct.getCategory().getId();

			CategoryValidator.rejectIfInvalidCategory(categoryId);
			CategoryValidator.rejectIfCategoryNotExists(categoryId);

			// find the category

			CategoryService categoryService = new CategoryService();
			Category category = categoryService.findCategoryByCategoryId(categoryId);

			int genderId = category.getGender().getId();

			GenderValidator.rejectIfInvalidGender(genderId);
			GenderValidator.rejectIfGenderNotExists(genderId);

			List<Price> priceList = newProduct.getPriceList();
			PriceValidator.ValidateAll(priceList);

			ProductDAO productDao = new ProductDAO();

			// create the product, get the generated product id

			int productId = productDao.create(newProduct);

			PriceService priceService = new PriceService();

			for (Price price : priceList) {

				Product product = new Product();
				product.setId(productId);

				price.setProduct(product);

				priceService.createPrice(price);
			}

			System.out.println("product and its prices created successfully");
		} catch (ValidationException e) {
			e.printStackTrace();
			// Handle validation errors (e.g., provide user-friendly error messages)
			throw new ValidationException("Validation error: " + e.getMessage());
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Error creating product and prices: " + e.getMessage());
		}

	}

	// need to check

	// findAllProductsWithFirstPriceAndSize

	/**
	 * 
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public List<ProductDTO> findAllProducts() throws ValidationException, ServiceException {

		List<ProductDTO> productDtoList = new ArrayList<>();

		try {

			ProductDAO productDao = new ProductDAO();

			List<ProductDTO> ProductList = productDao.findAllProducts(); // [{id,name,description,category},{},{}]

			for (Product product : ProductList) {

				// product ==> {id,name,description,category}

				ProductDTO productDto = new ProductDTO();

				// set category name
				CategoryService categoryService = new CategoryService();
				Category category = categoryService.findCategoryByCategoryId(product.getCategory().getId());

				// set gender name
				GenderService genderService = new GenderService();
				Gender gender = genderService.findGenderBygenderId(category.getGender().getId());
				category.setGender(gender);

				// set the gender name

				List<Price> priceList = productDto.getPriceList();

				PriceService priceService = new PriceService();
				Price eachPrice = priceService.FindFirstPriceByProductId(product.getId()); // {price,products_id,sizes_id,started_at,ended_at}

				// set size value
				SizeService sizeService = new SizeService();
				Size size = sizeService.FindSizeBySizeId(eachPrice.getSize().getId());
				eachPrice.setSize(size);

				priceList.add(eachPrice);

				productDto.setId(product.getId());
				productDto.setName(product.getName());
				productDto.setDescription(product.getDescription());
				productDto.setCategory(category);
				productDto.setPriceList(priceList);

				productDtoList.add(productDto);

			}

			// to sysout the products
			for (ProductDTO productPrice : productDtoList) {

				System.out.println(productPrice);
			}

			System.out.println("All products are sucessfully retrieved");

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Error find all products : " + e.getMessage());
		} catch (Exception e) {
			// Handle validation errors (e.g., provide user-friendly error messages)
			throw new ValidationException("Validation error: " + e.getMessage());
		}

		return productDtoList;

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public ProductDTO findProductDetailsByProductId(int id) throws ValidationException, ServiceException {

		ProductDTO productDto = null;

		try {

			ProductValidator.rejectIfInvalidproduct(id);
			ProductValidator.rejectIfProductNotExists(id);

			ProductDAO productDAO = new ProductDAO();

			productDto = productDAO.findProductDetailsByProductId(id); // {name,description,category,pricelist[]}

			// set category name
			CategoryService categoryService = new CategoryService();
			Category category = categoryService.findCategoryByCategoryId(productDto.getCategory().getId());

			// set gender name
			GenderService genderService = new GenderService();
			Gender gender = genderService.findGenderBygenderId(category.getGender().getId());

			category.setGender(gender);

			productDto.setCategory(category);

			PriceService PriceService = new PriceService();
			productDto.setPriceList(PriceService.FindAllPricesByProductId(id)); // pricelist[{id,size_id,price},{},{}]

			List<Price> priceList = productDto.getPriceList();

			for (Price price : priceList) {
				SizeService sizeService = new SizeService();
				Size eachSize = sizeService.FindSizeBySizeId(price.getSize().getId());

				price.getSize().setId(eachSize.getId());
				price.getSize().setValue(eachSize.getValue());
			}

			// to sysout the product details
			System.out.println(productDto);

			System.out.println("sucessfully retrieved the product details");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Error find product details : " + e.getMessage());
		} catch (Exception e) {
			// Handle validation errors (e.g., provide user-friendly error messages)
			throw new ValidationException("Validation error: " + e.getMessage());
		}

		return productDto;

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */

	public List<ProductDTO> findAllProductsByCategoryId(int id) throws ValidationException, ServiceException {

		List<ProductDTO> productDtoList = new ArrayList<>();

		try {

			CategoryValidator.rejectIfInvalidCategory(id);

			// business validation
			CategoryValidator.rejectIfCategoryNotExists(id);

			ProductDAO productDao = new ProductDAO();

			List<ProductDTO> ProductList = productDao.findAllProducts(); // [{id,name,description,category,pricelist},{},{}]

			for (Product product : ProductList) {

				ProductDTO productDto = new ProductDTO();

				List<Price> priceList = productDto.getPriceList(); // []

				PriceService priceService = new PriceService();
				Price eachPrice = priceService.FindFirstPriceByProductId(product.getId());

				priceList.add(eachPrice);

				productDto.setId(product.getId());
				productDto.setName(product.getName());
				productDto.setDescription(product.getDescription());
				productDto.setCategory(product.getCategory());
				productDto.setPriceList(priceList);

				productDtoList.add(productDto);

			}

			// to sysout the products
			for (ProductDTO productPrice : productDtoList) {

				System.out.println(productPrice);
			}

			System.out.println("All products are sucessfully retrieved");

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Error find all products : " + e.getMessage());
		} catch (Exception e) {
			// Handle validation errors (e.g., provide user-friendly error messages)
			throw new ValidationException("Validation error: " + e.getMessage());
		}

		return productDtoList;

	}

	/**
	 * 
	 * @param id
	 * @param updatedProduct
	 * @throws ValidationException
	 * @throws ServiceException
	 * @throws com.google.protobuf.ServiceException
	 */
	public void updateProductDetailsAndPrices(ProductDTO updatedProduct) // updatedproduct
																			// {name,description,pricelist}
			throws ValidationException, ServiceException, com.google.protobuf.ServiceException {

		try {

			ProductValidator.validateAll(updatedProduct);

			int productId = updatedProduct.getId();

			// validation
			ProductValidator.rejectIfInvalidproduct(productId);

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

			PriceValidator.ValidateAll(priceList);

			ProductDAO productDao = new ProductDAO();

			productDao.updateProductDetails(updatedProduct);

			PriceService priceService = new PriceService();

			for (Price price : priceList) {

				int sizeId = price.getSize().getId();

				Price pricefromProdIdAndSizeId = priceService.findPriceBypProductIdAndSizeId(productId, sizeId);

				LocalDateTime date = LocalDateTime.now();
				java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(date);

				if (pricefromProdIdAndSizeId.getPrice() != price.getPrice()) {
					int priceId = pricefromProdIdAndSizeId.getId(); // null error

					priceService.updateprice(priceId, dateTime); // update enddate = current date;

					priceService.createPrice(price);
				}

			}

			System.out.println("product and its prices updated successfully");
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ValidationException("Validation error: " + e.getMessage());
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Error updating product and prices: " + e.getMessage());
		}

	}

}
