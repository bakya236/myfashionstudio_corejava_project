package in.fssa.myfashionstudioapp.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

import in.fssa.myfashionstudioapp.dao.OrderDAO;
import in.fssa.myfashionstudioapp.dao.OrderItemsDAO;
import in.fssa.myfashionstudioapp.dto.OrderDTO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.exception.ServiceException;
import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.Order;
import in.fssa.myfashionstudioapp.model.OrderItem;
import in.fssa.myfashionstudioapp.model.Price;
import in.fssa.myfashionstudioapp.model.Product;
import in.fssa.myfashionstudioapp.model.Size;
import in.fssa.myfashionstudioapp.model.User;
import in.fssa.myfashionstudioapp.validator.order.OrderValidator;

public class OrderService {

	public void createOrder(OrderDTO newOrder) throws ValidationException, ServiceException {

		try {

			OrderValidator.validateCreate(newOrder);

			String orderCode = generateOrderCode();

			LocalDateTime currentTimestamp = LocalDateTime.now();
			LocalDateTime deliveredAt = currentTimestamp.plus(5, ChronoUnit.MINUTES);

			newOrder.setOrderCode(orderCode.toString());
			newOrder.setDeliveredAt(deliveredAt);

			newOrder.setAddress(newOrder.getAddress());

			OrderDAO orderDAO = new OrderDAO();

			//

			int OrderId = orderDAO.createOrder(newOrder);

			List<OrderItem> orderItemsList = newOrder.getOrderItemList();

			OrderItemService orderItemService = new OrderItemService();

			for (OrderItem orderedItem : orderItemsList) {

				OrderItem orderItem = new OrderItem();

				Order order = new Order(OrderId);
				orderItem.setOrder(order);
				orderItem.setProduct(orderedItem.getProduct());
				orderItem.setPrice(orderedItem.getPrice());
				orderItem.setQuantity(orderedItem.getQuantity());

				orderItemService.createOrderItem(orderItem);
			}

		} catch (PersistenceException e) {

			e.printStackTrace();
			throw new ServiceException("Error creating order and it items: " + e.getMessage());
		}

	}

	public List<OrderDTO> getAllOrdersByUserId(int UserId) throws ValidationException, ServiceException {

		List<OrderDTO> orderDTOList = null;

		try {

			OrderDAO orderDAO = new OrderDAO();

			orderDTOList = orderDAO.FindAllOrdersByUserId(UserId);

			for (OrderDTO orderDTO : orderDTOList) {

				int orderId = orderDTO.getId();

				// find order by orderId

				List<OrderItem> orderItemList = orderDTO.getOrderItemList();

				for (OrderItem orderItem : orderItemList) {

					// have a join query to join these three table and give the product_details by
					// product_id

					int productId = orderItem.getProduct().getId();

					ProductService productService = new ProductService();

					Product product = productService.findProductDetailsByProductId(productId);

					orderItem.setProduct(product);

					int PriceId = orderItem.getPrice().getId();

					PriceService priceService = new PriceService();
					Price price = priceService.findPriceByPriceId(PriceId);

					int sizeId = price.getSize().getId();

					SizeService sizeService = new SizeService();
					Size size = sizeService.FindSizeBySizeId(sizeId);

					price.setSize(size);

					orderItem.setPrice(price);

				}
			}
		} catch (PersistenceException e) {

			e.printStackTrace();
			throw new ServiceException("Error getting all orders and it items: " + e.getMessage());
		}

		return orderDTOList;

	}

	public OrderDTO FindOrderByOrderId(String orderId) throws ValidationException, ServiceException {

		OrderDAO orderDAO = new OrderDAO();

		OrderDTO orderDTO = null;

		try {

			orderDTO = orderDAO.FindOrderItemsByOrderId(orderId);

			int getUserId = orderDTO.getUser().getId();

			UserService userService = new UserService();
			User user = userService.findById(getUserId);

			orderDTO.setUser(user);

			// find order by orderId

			List<OrderItem> orderItemList = orderDTO.getOrderItemList();

			for (OrderItem orderItem : orderItemList) {

				// have a join query to join these three table and give the product_details by
				// product_id

				orderItem.setId(orderItem.getId());

				int productId = orderItem.getProduct().getId();

				ProductService productService = new ProductService();

				Product product = productService.findProductDetailsByProductId(productId);

				orderItem.setProduct(product);

				int PriceId = orderItem.getPrice().getId();

				PriceService priceService = new PriceService();
				Price price = priceService.findPriceByPriceId(PriceId);

				int sizeId = price.getSize().getId();

				SizeService sizeService = new SizeService();
				Size size = sizeService.FindSizeBySizeId(sizeId);

				price.setSize(size);

				orderItem.setPrice(price);
			}
		}

		catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return orderDTO;
	}

	public void cancelOrder(int orderItemId, String cancelReason) throws ServiceException {

		OrderItemsDAO orderItemsDAO = new OrderItemsDAO();
		// You can perform validation or additional checks here if needed

		OrderItem orderItem = new OrderItem();

		orderItem.setId(orderItemId);
		orderItem.setCancel(true);
		orderItem.setCancelReason(cancelReason);

		try {
			orderItemsDAO.updateOrderCancelStatus(orderItem);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	private static String generateOrderCode() {
		int length = 10;
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // Characters to choose from
		Random random = new Random();
		StringBuilder code = new StringBuilder();

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(characters.length());
			char randomChar = characters.charAt(randomIndex);
			code.append(randomChar);
		}

		return code.toString();
	}
}
