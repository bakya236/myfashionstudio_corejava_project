package in.fssa.myfashionstudioapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.model.Address;
import in.fssa.myfashionstudioapp.model.User;
import in.fssa.myfashionstudioapp.util.ConnectionUtil;

public class AddressDAO {

	public void create(Address address) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO addresses(title, address, land_mark , state , country , city, pincode, user_id , status) VALUES(?,?,?,?,?,?,?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, address.getTitle());
			ps.setString(2, address.getAddress());
			ps.setString(3, address.getLandMark());
			ps.setString(4, address.getState());
			ps.setString(5, address.getCountry());
			ps.setString(6, address.getCity());
			ps.setInt(7, address.getPincode());
			ps.setInt(8, address.getUser().getId());
			ps.setBoolean(9, address.getStatus());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	public void update(Address updatedAddress) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE addresses SET title = ?, address = ?, land_mark = ?, state = ?, country = ?, city = ?,  pincode = ?, user_id = ?, status = ? WHERE  id = ?;";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, updatedAddress.getTitle());
			ps.setString(2, updatedAddress.getAddress());
			ps.setString(3, updatedAddress.getLandMark());
			ps.setString(4, updatedAddress.getState());
			ps.setString(5, updatedAddress.getCountry());
			ps.setString(6, updatedAddress.getCity());
			ps.setInt(7, updatedAddress.getPincode());
			ps.setInt(8, updatedAddress.getUser().getId());
			ps.setBoolean(9, updatedAddress.getStatus());
			ps.setInt(10, updatedAddress.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	public Address findByUserId(int userId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Address address = null;

		try {
			String query = "SELECT id , title, address, land_mark , state , country , city, pincode, user_id , status FROM addresses WHERE user_id = ? ";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, userId);

			rs = ps.executeQuery();

			if (rs.next()) {

				System.out.println("in rs");

				address = new Address();

				address.setId(rs.getInt("id"));
				address.setTitle(rs.getString("title"));
				address.setAddress(rs.getString("address"));
				address.setLandMark(rs.getString("land_mark"));
				address.setState(rs.getString("state"));
				address.setCountry(rs.getString("country"));
				address.setCity(rs.getString("city"));
				address.setPincode(rs.getInt("pincode"));
				address.setUser(new User(rs.getInt("user_id")));

				System.out.println(address);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		System.out.println(address);

		return address;
	}

	public Address findByAddressId(int addressId) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Address address = null;

		try {
			String query = "SELECT id , title, address, land_mark , state , country , city, pincode, user_id , status FROM addresses WHERE id = ? ";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, addressId);

			rs = ps.executeQuery();

			if (rs.next()) {

				System.out.println("in rs");

				address = new Address();

				address.setId(rs.getInt("id"));
				address.setTitle(rs.getString("title"));
				address.setAddress(rs.getString("address"));
				address.setLandMark(rs.getString("land_mark"));
				address.setState(rs.getString("state"));
				address.setCountry(rs.getString("country"));
				address.setCity(rs.getString("city"));
				address.setPincode(rs.getInt("pincode"));
				address.setUser(new User(rs.getInt("user_id")));

				System.out.println(address);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		System.out.println(address);

		return address;
	}

	public boolean isAddressAlreadyExists(int addressId) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag = false;

		try {
			String query = "SELECT 1 FROM addresses WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, addressId);

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
