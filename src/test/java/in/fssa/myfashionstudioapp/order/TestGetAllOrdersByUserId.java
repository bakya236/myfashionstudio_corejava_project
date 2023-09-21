package in.fssa.myfashionstudioapp.order;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.dto.OrderDTO;
import in.fssa.myfashionstudioapp.service.OrderService;

public class TestGetAllOrdersByUserId {

	@Test
	public void getAllOrdersByUserId() {

		OrderService orderService = new OrderService();

		// [[],[]]
		assertDoesNotThrow(() -> {

			List<OrderDTO> orderList = orderService.getAllOrdersByUserId(1);

			for (OrderDTO order : orderList) {
				System.out.print("=======>" + order);
			}
		});
	}

}
