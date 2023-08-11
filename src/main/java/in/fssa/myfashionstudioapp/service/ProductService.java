package in.fssa.myfashionstudioapp.service;

import java.util.List;

import com.google.protobuf.ServiceException;

import in.fssa.myfashionstudioapp.PriceValidator;
import in.fssa.myfashionstudioapp.dao.ProductDAO;
import in.fssa.myfashionstudioapp.dto.ProductDTO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.Price;
import in.fssa.myfashionstudioapp.validator.ProductValidator;

public class ProductService {

	public void createProductWithPrices(ProductDTO newProduct) throws ValidationException, ServiceException {

		try {
			List<Price> priceList = newProduct.getPriceList();

			// validation
			ProductValidator.validateAll(newProduct);

			// CategoryValidator.rejectIfCategoryNotExists(newProduct.getCategory().getId());

			PriceValidator.ValidateAll(priceList);

			ProductDAO productDao = new ProductDAO();

			PriceService priceService = new PriceService();

			// create the product, get the generated product id

			int productId = productDao.create(newProduct);

			// business validation - product and size with end date = null already exist
			// throws exception

			for (Price price : priceList) {
				price.getProduct().setId(productId);

				priceService.createPrice(price);
			}

			System.out.println("product and its prices created successfully");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}

	}

}
