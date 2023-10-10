package in.fssa.myfashionstudioapp.order;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.dto.OrderDTO;
import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.Address;
import in.fssa.myfashionstudioapp.model.OrderItem;
import in.fssa.myfashionstudioapp.model.Price;
import in.fssa.myfashionstudioapp.model.Product;
import in.fssa.myfashionstudioapp.model.User;
import in.fssa.myfashionstudioapp.service.OrderService;
import in.fssa.myfashionstudioapp.validator.order.OrderItemValidatorErrors;
import in.fssa.myfashionstudioapp.validator.order.OrderValidatorErrors;

public class TestCreateOrder {

	private OrderService orderService;

	@BeforeEach
	public void setUp() {
		orderService = new OrderService();
	}

	private OrderDTO createValidOrderDTO() {

		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setTotalPrice(100.00);

		Address DeliveryAdress = new Address(2);
		orderDTO.setAddress(DeliveryAdress);

		User user = new User(1);
		orderDTO.setUser(user);

		List<OrderItem> orderedItemsList = new ArrayList<>(); // []

		OrderItem orderItem = new OrderItem();
		Product product = new Product(1);
		Price price = new Price(1);

		orderItem.setProduct(product);
		orderItem.setPrice(price);
		orderItem.setQuantity(8);

		orderedItemsList.add(orderItem);
		orderDTO.setOrderItemList(orderedItemsList);

		return orderDTO;
	}

	private void assertValidationExceptionThrown(OrderDTO orderDTO, String expectedErrorMessage) {
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.createOrder(orderDTO);
		});

		String actualErrorMessage = exception.getMessage();
		assertEquals(expectedErrorMessage, actualErrorMessage);
		System.out.println(actualErrorMessage);
	}

	@Test
	public void createOrderWithValidInput() {

		OrderDTO orderDTO = createValidOrderDTO();

		OrderService orderService = new OrderService();
		assertDoesNotThrow(() -> {
			orderService.createOrder(orderDTO);
		});
	}

	@Test
	public void createOrderWithInvalidUserId() {

		OrderDTO orderDTO = createValidOrderDTO();

		orderDTO.setUser(new User(0));

		assertValidationExceptionThrown(orderDTO, "Invalid user id");

	}

	@Test
	public void createOrderWithUserAsNull() {

		OrderDTO orderDTO = createValidOrderDTO();
		orderDTO.setUser(null);

		assertValidationExceptionThrown(orderDTO, OrderValidatorErrors.INVALID_USER);

	}

	@Test
	public void createOrderWithUserIdNotExists() {

		OrderDTO orderDTO = createValidOrderDTO();

		int Id = 90;
		orderDTO.setUser(new User(Id));

		assertValidationExceptionThrown(orderDTO, "User with ID " + Id + " does not exist");

	}

	@Test
	public void createOrderWithAddressAsNull() {

		OrderDTO orderDTO = createValidOrderDTO();

		orderDTO.setAddress(null);

		assertValidationExceptionThrown(orderDTO, OrderValidatorErrors.INVALID_ADDRESS);

	}

	// to wiork

	@Test
	public void createOrderWithAddressIdNotExists() {

		OrderDTO orderDTO = createValidOrderDTO();

		int Id = 90;
		orderDTO.setAddress(new Address(Id));

		assertValidationExceptionThrown(orderDTO, "Address with ID " + Id + " does not exist");

	}

	@Test
	public void createOrderWithItemListAsNull() {

		OrderDTO orderDTO = createValidOrderDTO();

		orderDTO.setOrderItemList(null);

		assertValidationExceptionThrown(orderDTO, OrderValidatorErrors.INVALID_ORDER_ITEMS);

	}

	@Test
	public void createOrderWithEmptyItemList() {

		OrderDTO orderDTO = createValidOrderDTO();

		orderDTO.setOrderItemList(Collections.emptyList());

		assertValidationExceptionThrown(orderDTO, OrderValidatorErrors.EMPTY_ORDER_ITEMS);

	}

	@Test
	public void createOrderItemsWithProductAsNull() {

		OrderDTO orderDTO = createValidOrderDTO();

		orderDTO.getOrderItemList().get(0).setProduct(null);

		assertValidationExceptionThrown(orderDTO, OrderItemValidatorErrors.INVALID_PRODUCT);

	}

	@Test
	public void createOrderItemsWithProductIdDoesNotExist() {

		OrderDTO orderDTO = createValidOrderDTO();

		orderDTO.getOrderItemList().get(0).getProduct().setId(0);
		;

		assertValidationExceptionThrown(orderDTO, "Product with ID " + 0 + " does not exist");

	}

	@Test
	public void createOrderItemsWithPriceAsNull() {

		OrderDTO orderDTO = createValidOrderDTO();

		orderDTO.getOrderItemList().get(0).setPrice(null);
		assertValidationExceptionThrown(orderDTO, OrderItemValidatorErrors.INVALID_PRICE);

	}

	@Test
	public void createOrderItemsWithPriceIdDoesNotExist() {

		OrderDTO orderDTO = createValidOrderDTO();

		orderDTO.getOrderItemList().get(0).getPrice().setId(0);

		assertValidationExceptionThrown(orderDTO, "Price with ID " + 0 + " does not exist");

	}

	@Test
	public void createOrderItemsWithInvalidMMinQuantity() {

		OrderDTO orderDTO = createValidOrderDTO();

		orderDTO.getOrderItemList().get(0).setQuantity(-1);

		assertValidationExceptionThrown(orderDTO, OrderItemValidatorErrors.INVALID_MIN_QTY);

	}

	@Test
	public void createOrderItemsWithInvalidMaxQuantity() {

		OrderDTO orderDTO = createValidOrderDTO();

		orderDTO.getOrderItemList().get(0).setQuantity(11);

		assertValidationExceptionThrown(orderDTO, OrderItemValidatorErrors.INVALID_MAX_QTY);

	}

}
