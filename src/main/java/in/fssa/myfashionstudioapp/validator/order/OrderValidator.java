package in.fssa.myfashionstudioapp.validator.order;

import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.Order;
import in.fssa.myfashionstudioapp.util.IntUtil;
import in.fssa.myfashionstudioapp.util.StringUtil;

public class OrderValidator {

	public static void validateOrder(Order newOrder) throws ValidationException {

		if (newOrder == null) {
			throw new ValidationException(OrderValidatorErrors.INVALID_ORDER);
		}

		StringUtil.rejectIfInvalidString(newOrder.getOrderCode(), "Ordercode");

		IntUtil.rejectIfInvalidId(newOrder.getUser().getId(), "Used Id");

	}

}
