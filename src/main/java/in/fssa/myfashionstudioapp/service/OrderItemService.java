package in.fssa.myfashionstudioapp.service;

import in.fssa.myfashionstudioapp.dao.OrderItemsDAO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.exception.ServiceException;
import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.OrderItem;

public class OrderItemService {

	public void createOrderItem(OrderItem orderItem) throws ValidationException, ServiceException {

		try {

			// to incude validations

//			PriceValidator.validate(price);

			System.out.println("in service");

			OrderItemsDAO orderItemsDAO = new OrderItemsDAO();
			orderItemsDAO.create(orderItem);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

}
