package in.fssa.myfashionstudioapp.service;

import java.util.ArrayList;
import java.util.List;

import in.fssa.myfashionstudioapp.dao.ProductDAO;
import in.fssa.myfashionstudioapp.dto.ProductDTO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.exception.ServiceException;
import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.Price;
import in.fssa.myfashionstudioapp.model.Product;
import in.fssa.myfashionstudioapp.model.Size;
import in.fssa.myfashionstudioapp.validator.CategoryValidator;
import in.fssa.myfashionstudioapp.validator.PriceValidator;
import in.fssa.myfashionstudioapp.validator.ProductValidator;

public class ProductService {

	public void createProductWithPrices(ProductDTO newProduct) throws ValidationException, ServiceException {

		try {

			// validation
			ProductValidator.validateAll(newProduct);
			CategoryValidator.rejectIfCategoryNotExists(newProduct.getCategory().getId());

			List<Price> priceList = newProduct.getPriceList();
			PriceValidator.ValidateAll(priceList);

			ProductDAO productDao = new ProductDAO();

			// create the product, get the generated product id

			int productId = productDao.create(newProduct);
			PriceService priceService = new PriceService();

			for (Price price : priceList) {

				price.getProduct().setId(productId);

				priceService.createPrice(price);
			}

			System.out.println("product and its prices created successfully");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Error creating product and prices: " + e.getMessage());
		} catch (Exception e) {
			// Handle validation errors (e.g., provide user-friendly error messages)
			throw new ValidationException("Validation error: " + e.getMessage());
		}

	}

	// need to check

	// findAllProductsWithFirstPriceAndSize

	public List<ProductDTO> findAllProducts() throws ValidationException, ServiceException {

		List<ProductDTO> productDtoList = new ArrayList<>();

		try {

			ProductDAO productDao = new ProductDAO();

			List<ProductDTO> ProductList = productDao.findAllProducts(); // [{id,name,description,category},{},{}]

			for (Product product : ProductList) {

				ProductDTO productDto = new ProductDTO();

				List<Price> priceList = productDto.getPriceList();

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

	public ProductDTO findProductDetailsByProductId(int id) throws ValidationException, ServiceException {

		ProductDTO productDto = null;

		try {

			ProductValidator.rejectIfInvalidproduct(id);
			ProductValidator.rejectIfProductNotExists(id);

			ProductDAO productDAO = new ProductDAO();

			productDto = productDAO.findProductDetailsByProductId(id); // {name,description,category,pricelist[]}

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

	public void updateproductDetails(ProductDTO updatedProduct) throws ValidationException, ServiceException {

	}

	public void updateProductDetailsAndPrices(int id, ProductDTO updatedProduct) // updatedproduct
																					// {name,description,pricelist}
			throws ValidationException, ServiceException {

		try {

			// validation
			ProductValidator.rejectIfInvalidproduct(id);

//			business validation - reject If Product Not Exists
			ProductValidator.rejectIfProductNotExists(id);

			ProductValidator.validateAll(updatedProduct);

//			business validation - reject If Category Not Exists in category table
			// CategoryValidator.rejectIfCategoryNotExists(newProduct.getCategory().getId());

			// validating the price in price service - update price()
			List<Price> priceList = updatedProduct.getPriceList();

			System.out.println("priceList" + priceList);

			PriceValidator.ValidateAll(priceList);

			ProductDAO productDao = new ProductDAO();

			productDao.updateProductDetails(id, updatedProduct);

			PriceService priceService = new PriceService();

			for (Price price : priceList) {

				// return the price id where price product // price dao ()

				int productId = id;
				int sizeId = price.getSize().getId();

				System.out.println(productId + " " + sizeId);

				Price pricefromProdIdAndSizeId = priceService.findPriceBypProductIdAndSizeId(productId, sizeId);

				System.out.println(pricefromProdIdAndSizeId);

				// got price id from the price table

				int priceId = pricefromProdIdAndSizeId.getId(); // null error

				System.out.println(priceId);

				priceService.updateprice(priceId); // update enddate = current date;

				System.out.println(price.toString());
				priceService.createPrice(price);
			}

			System.out.println("product and its prices updated successfully");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Error updating product and prices: " + e.getMessage());
		} catch (Exception e) {
			// Handle validation errors (e.g., provide user-friendly error messages)
			throw new ValidationException("Validation error: " + e.getMessage());
		}

	}

}
