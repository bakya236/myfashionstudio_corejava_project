package in.fssa.myfashionstudioapp.order;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.dto.OrderDTO;
import in.fssa.myfashionstudioapp.model.Address;
import in.fssa.myfashionstudioapp.model.OrderItem;
import in.fssa.myfashionstudioapp.model.Price;
import in.fssa.myfashionstudioapp.model.Product;
import in.fssa.myfashionstudioapp.model.Size;
import in.fssa.myfashionstudioapp.model.User;
import in.fssa.myfashionstudioapp.service.OrderService;

public class TestCreateOrder {

	@Test
	public void createOrderWithValidInput() {

		// create the dto instance

		OrderDTO orderDTO = new OrderDTO();

		orderDTO.setTotalPrice(100.00);

		Address DeliveryAdress = new Address(1);
		orderDTO.setAddress(DeliveryAdress);

		User user = new User(1);
		orderDTO.setUser(user);

		List<OrderItem> orderedItemsList = new ArrayList<>(); // []

		OrderItem orderItem = new OrderItem();

		Product product = new Product(1);
		Size size = new Size(1);
		Price price = new Price(1);

		orderItem.setProduct(product);
//		orderItem.setSize(size);
		orderItem.setPrice(price);
		orderItem.setQuantity(90000);

		OrderItem orderItem1 = new OrderItem();

		Product product1 = new Product(1);
		Size size1 = new Size(1);
		Price price1 = new Price(1);

		orderItem1.setProduct(product1);
//		orderItem1.setSize(size1);
		orderItem1.setPrice(price1);
		orderItem1.setQuantity(9008);

		orderedItemsList.add(orderItem);
		orderedItemsList.add(orderItem1);

		orderDTO.setOrderItemList(orderedItemsList);

		OrderService orderService = new OrderService();
		assertDoesNotThrow(() -> {
			orderService.createOrder(orderDTO);
		});
	}

}
