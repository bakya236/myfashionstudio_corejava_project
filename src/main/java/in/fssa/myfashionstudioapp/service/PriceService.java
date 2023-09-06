package in.fssa.myfashionstudioapp.service;

import java.time.LocalDateTime;
import java.util.List;

import in.fssa.myfashionstudioapp.dao.PriceDAO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.exception.ServiceException;

// cannot import the service exception

import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.Price;
import in.fssa.myfashionstudioapp.validator.PriceValidator;

public class PriceService {

	/**
	 * 
	 * @param price
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void createPrice(Price price) throws ValidationException, ServiceException {

		try {
			PriceValidator.validate(price);

			PriceDAO priceDAO = new PriceDAO();
			priceDAO.create(price);
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	// check this method

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public Price FindFirstPriceByProductId(int id) throws ServiceException {
		Price price = null;
		try {
			PriceDAO priceDAO = new PriceDAO();
			price = priceDAO.FindFirstPriceByProductId(id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return price;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 */

	public List<Price> FindAllPricesByProductId(int id) throws ServiceException {

		try {
			PriceDAO priceDAO = new PriceDAO();
			return priceDAO.FindAllPricesByProductId(id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param priceId
	 * @param dateTime
	 * @throws ServiceException
	 */

	private void updateprice(int priceId, LocalDateTime dateTime) throws ServiceException {

		// ask - need for form validation
		// ask - is there any need to again check whether the product and size exists in
		// their tables or not

		try {

			// again checking form validation

			PriceDAO priceDAO = new PriceDAO();

			priceDAO.update(priceId, dateTime);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Error update price :" + e.getMessage());
		}

	}

	/**
	 * 
	 * @param productId
	 * @param sizeId
	 * @return
	 * @throws ServiceException
	 */
	public Price findPriceByProductIdAndSizeId(int productId, int sizeId) throws ServiceException {
		Price price = null;

		try {

			// need to check the productid in product table

//			ProductValidator.rejectIfInvalidproduct(productId); // change

			// need to check the sizeid in size table
//
//			SizeValidator.rejectIfSizeNotExists(sizeId);

			PriceDAO priceDAO = new PriceDAO();
			price = priceDAO.findPriceByProductIdAndSizeId(productId, sizeId);
			System.out.println("found the price with the given product id and size id");

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Error finding product details :" + e.getMessage());
		}

		return price;
	}

	public void changePrice(int priceId, Price price, LocalDateTime dateTime)
			throws ServiceException, ValidationException {
		PriceService priceService = new PriceService();
		priceService.updateprice(priceId, dateTime); // update enddate = current date;
		priceService.createPrice(price);
	}

}
