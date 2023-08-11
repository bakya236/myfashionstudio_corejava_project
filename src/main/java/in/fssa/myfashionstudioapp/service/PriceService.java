package in.fssa.myfashionstudioapp.service;

import in.fssa.myfashionstudioapp.PriceValidator;
import in.fssa.myfashionstudioapp.dao.PriceDAO;
import in.fssa.myfashionstudioapp.model.Price;

public class PriceService {

	public void createPrice(Price price) throws RuntimeException {

		PriceValidator.Validate(price);

		PriceDAO priceDao = new PriceDAO();
		priceDao.createPrice(price);
	}

}
