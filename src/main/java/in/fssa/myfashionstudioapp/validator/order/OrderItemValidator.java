package in.fssa.myfashionstudioapp.validator.order;

import in.fssa.myfashionstudioapp.dao.OrderDAO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.OrderItem;
import in.fssa.myfashionstudioapp.util.NullUtil;
import in.fssa.myfashionstudioapp.validator.PriceValidator;
import in.fssa.myfashionstudioapp.validator.ProductValidator;

public class OrderItemValidator {

	public static void validateCreate(OrderItem newOrderItem) throws ValidationException {

		NullUtil.rejectIfNull(newOrderItem, OrderItemValidatorErrors.INVALID_ORDER_ITEMS);

		NullUtil.rejectIfNull(newOrderItem.getOrder(), OrderItemValidatorErrors.INVALID_ORDER);
		NullUtil.rejectIfNull(newOrderItem.getOrder().getId(), OrderItemValidatorErrors.INVALID_ORDER_ID);
		OrderItemValidator.rejectIfOrderDoesNotExists(newOrderItem.getOrder().getId());

		NullUtil.rejectIfNull(newOrderItem.getProduct(), OrderItemValidatorErrors.INVALID_PRODUCT);
		NullUtil.rejectIfNull(newOrderItem.getProduct().getId(), OrderItemValidatorErrors.INVALID_PRODUCT_ID);
		ProductValidator.rejectIfProductNotExists(newOrderItem.getProduct().getId());

		NullUtil.rejectIfNull(newOrderItem.getPrice(), OrderItemValidatorErrors.INVALID_PRICE);
		NullUtil.rejectIfNull(newOrderItem.getPrice().getId(), OrderItemValidatorErrors.INVALID_PRICE_ID);

		// price aldredy exist in price table

		PriceValidator.rejectIfPriceNotExists(newOrderItem.getPrice().getId());

		OrderItemValidator.rejectIfInvalidQuantity(newOrderItem.getQuantity());

	}

	private static void rejectIfInvalidQuantity(int quantity) throws ValidationException {

		if (quantity < 1) {
			throw new ValidationException(OrderItemValidatorErrors.INVALID_MIN_QTY);
		}

		if (quantity > 10) {
			throw new ValidationException(OrderItemValidatorErrors.INVALID_MAX_QTY);
		}

	}

	public static void rejectIfOrderDoesNotExists(int orderId) throws ValidationException {

		if (!checkIfOrderAlreadyExists(orderId)) {
			throw new ValidationException("User with ID " + orderId + " does not exist");
		}

	}

	private static boolean checkIfOrderAlreadyExists(int orderId) throws ValidationException {
		boolean flag;
		try {

			OrderDAO orderDAO = new OrderDAO();
			flag = orderDAO.isOrderAlreadyExists(orderId);

		} catch (PersistenceException e) {
			throw new ValidationException(e.getMessage());
		}

		return flag;
	}

}
