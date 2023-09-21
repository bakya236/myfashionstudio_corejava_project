package in.fssa.myfashionstudioapp.order;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.service.OrderService;

public class TestUpdateCancelOrder {

	// create the dto instance

	@Test
	public void updateCancelOrder() {

		OrderService orderService = new OrderService();

		assertDoesNotThrow(() -> {
			orderService.cancelOrder(1, "ordered by mistake");
		});

	}

}
