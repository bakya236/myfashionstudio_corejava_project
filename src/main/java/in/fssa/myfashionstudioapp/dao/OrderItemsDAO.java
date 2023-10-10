package in.fssa.myfashionstudioapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.model.OrderItem;
import in.fssa.myfashionstudioapp.util.ConnectionUtil;

public class OrderItemsDAO {

	public void create(OrderItem orderItem) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String Query = "INSERT INTO order_items (order_id , product_id , price_id , quantity) VALUES (? ,?, ?, ? )";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(Query);

			// get size from price
			ps.setInt(1, orderItem.getOrder().getId());
			ps.setInt(2, orderItem.getProduct().getId());
			ps.setInt(3, orderItem.getPrice().getId());
			ps.setInt(4, orderItem.getQuantity());

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected <= 0) {
				throw new PersistenceException("Order creation failed");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}

	public void updateOrderCancelStatus(OrderItem orderItem) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		System.out.println("in ordern item");

		System.out.println("in ordern item" + orderItem);

		try {
			con = ConnectionUtil.getConnection();
			String query = "UPDATE order_items SET is_cancel = ?, cancel_reason = ? WHERE id = ?";

			ps = con.prepareStatement(query);

			ps.setBoolean(1, orderItem.isCancel());
			ps.setString(2, orderItem.getCancelReason());
			ps.setInt(3, orderItem.getId());

			int rowsAffected = ps.executeUpdate();

			System.out.print(rowsAffected);

			if (rowsAffected <= 0) {
				throw new PersistenceException("cannot cancel this order");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}

}
