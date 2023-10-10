package in.fssa.myfashionstudioapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.model.Price;
import in.fssa.myfashionstudioapp.util.ConnectionUtil;

public class PriceDAO {

	/**
	 * 
	 * @param price
	 * @throws PersistenceException
	 */
	public void create(Price price) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "INSERT INTO prices (product_id, size_id, price) VALUES (?,?,?)";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(query);

			ps.setInt(1, price.getProduct().getId());
			ps.setInt(2, price.getSize().getId());
			ps.setDouble(3, price.getPrice());

			ps.executeUpdate();

			System.out.println("created price");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistenceException
	 */
	public Price FindFirstPriceByProductId(int ProductId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Price price = null;

		try {
			String query = "SELECT id , price , started_at , ended_at , product_id , size_id FROM prices WHERE product_id = ? AND ended_at IS NULL LIMIT 1";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(query);

			ps.setInt(1, ProductId);

			rs = ps.executeQuery();

			if (rs.next()) {
				// There is at least one row
				price = new Price();

				price.setId(rs.getInt("id"));

				//
				price.getProduct().setId(rs.getInt("product_id"));
				price.getSize().setId(rs.getInt("size_id"));
				price.setPrice(rs.getDouble("price"));

				System.out.println("Retrieved the price details");
			} else {
				System.out.println("No price details found");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return price;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistenceException
	 */
	public List<Price> FindAllPricesByProductId(int ProductId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Price> priceList = new ArrayList<>();

		try {

			String query = "SELECT  id , price , started_at , ended_at , product_id , size_id  FROM prices WHERE product_id = ? AND ended_at IS NULL";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(query);

			ps.setInt(1, ProductId);

			rs = ps.executeQuery();

			while (rs.next()) {
				Price price = new Price();

				price.setId(rs.getInt("id"));
				price.getProduct().setId(rs.getInt("product_id"));
				price.getSize().setId(rs.getInt("size_id"));
				price.setPrice(rs.getDouble("price"));

				priceList.add(price);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return priceList;
	}

	/**
	 * 
	 * @param priceId
	 * @param dateTime
	 * @throws PersistenceException
	 */
	public void update(int priceId, LocalDateTime dateTime) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "UPDATE prices SET ended_at = ? WHERE id = ?";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(query);

			Timestamp sqlDateTime = Timestamp.valueOf(dateTime);

			ps.setTimestamp(1, sqlDateTime);
			ps.setInt(2, priceId);

			ps.executeUpdate();

			System.out.println("updated end date to current date");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

	}

	/**
	 * 
	 * @param productId
	 * @param sizeId
	 * @return
	 * @throws PersistenceException
	 */

	public Price findPriceByProductIdAndSizeId(int productId, int sizeId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Price price = null;

		try {
			String query = "SELECT  id , price , started_at , ended_at , product_id , size_id  FROM prices WHERE product_id = ? AND size_id = ? AND ended_at IS NULL";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(query);

			ps.setInt(1, productId);
			ps.setDouble(2, sizeId);

			rs = ps.executeQuery();

			if (rs.next()) {
				price = new Price();
				price.setId(rs.getInt("id"));
				price.setPrice(rs.getDouble("price"));
				price.getProduct().setId(rs.getInt("product_id"));
				price.getSize().setId(rs.getInt("size_id"));

				System.out.println(price);

				System.out.println("found the price with end date as null");
			} else {
				System.out.println("price not found with end date null");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return price;
	}
//
//	private LocalDateTime convertToLocalDateTime(Timestamp timestamp) {
//		return timestamp.toLocalDateTime();
//	}

	public Price findById(int priceId) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Price price = null;

		try {
			String query = "SELECT  id , price , started_at , ended_at , product_id , size_id  FROM prices WHERE id = ?";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(query);

			ps.setInt(1, priceId);

			rs = ps.executeQuery();

			if (rs.next()) {
				price = new Price();
				price.setId(rs.getInt("id"));
				price.setPrice(rs.getDouble("price"));
				price.getProduct().setId(rs.getInt("product_id"));
				price.getSize().setId(rs.getInt("size_id"));

				System.out.println(price);

				System.out.println("found the price");
			} else {
				System.out.println("price not found ");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return price;
	}

	public boolean isPriceAlreadyExists(int priceId) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag = false;

		try {
			String query = "SELECT 1 FROM prices WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, priceId);

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
