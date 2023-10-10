package in.fssa.myfashionstudioapp.order;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.service.OrderService;

public class TestFindAllOrderItemsByOrderId {

	@Test
	public void findOrderByOrderId() {

		OrderService orderService = new OrderService();

		assertDoesNotThrow(() -> {
			orderService.FindOrderByOrderId("WZYC4ENYKO");
		});

	}

}
