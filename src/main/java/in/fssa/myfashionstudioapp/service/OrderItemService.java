package in.fssa.myfashionstudioapp.service;

import in.fssa.myfashionstudioapp.dao.OrderItemsDAO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.exception.ServiceException;
import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.OrderItem;
import in.fssa.myfashionstudioapp.validator.order.OrderItemValidator;

public class OrderItemService {

	public void createOrderItem(OrderItem orderItem) throws ValidationException, ServiceException {

		try {

			OrderItemValidator.validateCreate(orderItem);

			OrderItemsDAO orderItemsDAO = new OrderItemsDAO();
			orderItemsDAO.create(orderItem);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

}
