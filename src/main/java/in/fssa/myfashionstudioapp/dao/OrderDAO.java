package in.fssa.myfashionstudioapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import in.fssa.myfashionstudioapp.dto.OrderDTO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.model.Order;
import in.fssa.myfashionstudioapp.model.OrderItem;
import in.fssa.myfashionstudioapp.model.Price;
import in.fssa.myfashionstudioapp.model.Product;
import in.fssa.myfashionstudioapp.model.User;
import in.fssa.myfashionstudioapp.util.ConnectionUtil;

public class OrderDAO {

	public int createOrder(Order newOrder) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int orderId = -1;

		try {

			String Query = "INSERT INTO orders (order_code , total_price , user_id, delivery_address_id , delivered_at) VALUES (?, ?, ?, ? ,?)";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, newOrder.getOrderCode());
			ps.setDouble(2, newOrder.getTotalPrice());
			ps.setInt(3, newOrder.getUser().getId());
			ps.setInt(4, newOrder.getAddress().getId()); // for address id
			ps.setTimestamp(5, Timestamp.valueOf(newOrder.getDeliveredAt()));

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected <= 0) {

				throw new PersistenceException("Order creation failed");
			}

			rs = ps.getGeneratedKeys();

			if (rs.next()) {
				orderId = rs.getInt(1);
			} else {
				throw new PersistenceException("Creating order failed, no ID obtained.");
			}

		} catch (SQLException e) {
			e.printStackTrace();

			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return orderId;
	}

	/**
	 * Retrieves a list of OrderDTO objects for a specific user based on their user
	 * ID.
	 *
	 * @param userId The ID of the user for whom to retrieve orders.
	 * @return A list of OrderDTO objects representing the user's orders.
	 * @throws PersistenceException If there is an issue with the database
	 *                              connection or query execution.
	 */

	// [ orderDto{ orderitemlist[] } , { [] }]

	public List<OrderDTO> FindAllOrdersByUserId(int userId) throws PersistenceException {

		List<OrderDTO> orderDTOs = new ArrayList<>();

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = ConnectionUtil.getConnection();

			String query = "SELECT o.id, o.order_code ,o.total_price, o.ordered_at, o.delivered_at , oi.id , oi.order_id , oi.product_id, oi.price_id, oi.quantity , oi.status , oi.is_cancel , oi.cancel_reason "
					+ "FROM orders o " + "INNER JOIN order_items oi ON o.id = oi.order_id "
					+ "WHERE o.user_id = ? ORDER BY o.id DESC ";

			ps = connection.prepareStatement(query);
			ps.setInt(1, userId);

			rs = ps.executeQuery();

			int currentOrderId = -1;

			OrderDTO currentOrderDTO = null;

			while (rs.next()) {

				int orderId = rs.getInt("id");

				if (orderId != currentOrderId) {

					currentOrderDTO = new OrderDTO();
					currentOrderDTO.setId(orderId);
					currentOrderDTO.setOrderCode(rs.getString("order_code"));

					User user = new User(userId);
					currentOrderDTO.setUser(user);
					currentOrderDTO.setTotalPrice(rs.getDouble("total_price"));
					currentOrderDTO.setOrderredAt(rs.getTimestamp("ordered_at").toLocalDateTime());
					currentOrderDTO.setDeliveredAt(rs.getTimestamp("delivered_at").toLocalDateTime());

					List<OrderItem> orderItemList = new ArrayList<>();
					currentOrderDTO.setOrderItemList(orderItemList);

					orderDTOs.add(currentOrderDTO);
					currentOrderId = orderId;
				}

				OrderItem orderItem = new OrderItem();

				Product product = new Product(rs.getInt("product_id"));
				Price price = new Price(rs.getInt("price_id"));
				price.setProduct(product);
				orderItem.setId(rs.getInt("oi.id"));
				orderItem.setProduct(product);
				orderItem.setPrice(price);
				orderItem.setQuantity(rs.getInt("quantity"));
				orderItem.setStatus(rs.getString("status"));
				orderItem.setCancel(rs.getBoolean("is_cancel"));
				orderItem.setCancelReason(rs.getString("cancel_reason"));

				currentOrderDTO.getOrderItemList().add(orderItem);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException("Cannot get all ordered products");
		} finally {
			ConnectionUtil.close(connection, ps, rs);
		}

		System.out.println("====> " + orderDTOs);
		return orderDTOs;
	}

	public OrderDTO FindOrderItemsByOrderId(String orderId) throws PersistenceException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		System.out.println("in find order ");

		OrderDTO orderDTO = null; // Initialize as null

		try {
			connection = ConnectionUtil.getConnection();

			String query = "SELECT o.id, o.order_code, o.user_id, o.total_price, o.ordered_at, o.delivered_at, oi.id , oi.order_id , oi.product_id, oi.price_id, oi.quantity , oi.status , oi.is_cancel , oi.cancel_reason "
					+ "FROM orders o INNER JOIN order_items oi ON o.id =  oi.order_id "
					+ "WHERE o.order_code = ? ORDER BY o.id DESC";

			ps = connection.prepareStatement(query);

			ps.setString(1, orderId);

			rs = ps.executeQuery(); // { [] }

			String currentOrderId = "";

			OrderDTO currentOrderDTO = null;

			while (rs.next()) {

				String getOrderId = rs.getString("order_code");

				if (!getOrderId.equals(currentOrderId)) {

					currentOrderDTO = new OrderDTO();

					currentOrderDTO.setId(rs.getInt("order_id"));
					currentOrderDTO.setOrderCode(rs.getString("order_code"));
					currentOrderDTO.setUser(new User(rs.getInt("user_id")));
					currentOrderDTO.setTotalPrice(rs.getDouble("total_price"));
					currentOrderDTO.setOrderredAt(rs.getTimestamp("ordered_at").toLocalDateTime());
					currentOrderDTO.setDeliveredAt(rs.getTimestamp("delivered_at").toLocalDateTime());

					currentOrderDTO.setOrderItemList(new ArrayList<>());

					orderDTO = currentOrderDTO;
					currentOrderId = getOrderId;
				}

				OrderItem orderItem = new OrderItem();
				Product product = new Product(rs.getInt("product_id"));
				Price price = new Price(rs.getInt("price_id"));

				orderItem.setId(rs.getInt("oi.id"));

				Order order = new Order();
				order.setId(rs.getInt("order_id"));
				orderItem.setOrder(order);

				orderItem.setProduct(product);
				orderItem.setPrice(price);
				orderItem.setQuantity(rs.getInt("quantity"));
				orderItem.setStatus(rs.getString("status"));
				orderItem.setCancelReason(rs.getString("cancel_reason"));
				orderItem.setCancel(rs.getBoolean("is_cancel"));

				currentOrderDTO.getOrderItemList().add(orderItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException("Cannot get order by ID");
		} finally {
			ConnectionUtil.close(connection, ps, rs);
		}

		System.out.println("=====>" + orderDTO);

		return orderDTO;
	}

	public boolean isOrderAlreadyExists(int orderId) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag = false;

		try {
			String query = "SELECT 1 FROM orders WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, orderId);

			rs = ps.executeQuery();

			if (rs.next()) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return flag;
	}

}

//	    public void cancelOrder(int id) {
//	        //boolean result = false;
//	        try {
//	            query = "delete from orders where o_id=?";
//	            pst = this.con.prepareStatement(query);
//	            pst.setInt(1, id);
//	            pst.execute();
//	            //result = true;
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	            System.out.print(e.getMessage());
//	        }
//	        //return result;
//	    }
//
