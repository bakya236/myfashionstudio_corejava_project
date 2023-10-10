package in.fssa.myfashionstudioapp.validator.order;

import java.util.List;

import in.fssa.myfashionstudioapp.dto.OrderDTO;
import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.OrderItem;
import in.fssa.myfashionstudioapp.util.IntUtil;
import in.fssa.myfashionstudioapp.util.NullUtil;
import in.fssa.myfashionstudioapp.validator.UserValidator;
import in.fssa.myfashionstudioapp.validator.address.AddressValidator;

public class OrderValidator {

	public static void validateCreate(OrderDTO newOrder) throws ValidationException {

		NullUtil.rejectIfNull(newOrder, OrderValidatorErrors.INVALID_ORDER);

		// check order in order table
		NullUtil.rejectIfNull(newOrder.getUser(), OrderValidatorErrors.INVALID_USER);
		NullUtil.rejectIfNull(newOrder.getUser().getId(), OrderValidatorErrors.INVALID_USER_ID);
		IntUtil.rejectIfInvalidId(newOrder.getUser().getId(), "user id");

		// check user in user table
		UserValidator.rejetcIfUserDoesNotExists(newOrder.getUser().getId());

		NullUtil.rejectIfNull(newOrder.getAddress(), OrderValidatorErrors.INVALID_ADDRESS);
		NullUtil.rejectIfNull(newOrder.getAddress().getId(), OrderValidatorErrors.INVALID_ADDRESS);

		// check address in address table
		AddressValidator.rejectIfAddressDoesNotExists(newOrder.getAddress().getId());

		NullUtil.rejectIfNull(newOrder.getOrderItemList(), OrderValidatorErrors.INVALID_ORDER_ITEMS);

		OrderValidator.rejectIfOrderItemsIsEmpty(newOrder.getOrderItemList());

	}

	private static void rejectIfOrderItemsIsEmpty(List<OrderItem> orderItemList) throws ValidationException {

		if (orderItemList.size() <= 0) {
			throw new ValidationException(OrderValidatorErrors.EMPTY_ORDER_ITEMS);
		}

	}

}
