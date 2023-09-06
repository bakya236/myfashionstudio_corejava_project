package in.fssa.myfashionstudioapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.model.Size;
import in.fssa.myfashionstudioapp.util.ConnectionUtil;

public class SizeDAO {

	/**
	 * 
	 * @return
	 * @throws PersistenceException
	 */

	public List<Size> findAll() throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Size> sizeList = new ArrayList<>();

		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT id , value From sizes";

			ps = con.prepareStatement(query);

			rs = ps.executeQuery();

			while (rs.next()) {

				Size size = new Size();

				size.setId(rs.getInt("id"));
				size.setValue(rs.getString("value"));

				sizeList.add(size);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return sizeList;

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistenceException
	 */
	public Size FindById(int sizeId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Size size = null;

		try {
			String Query = "SELECT id , value FROM sizes WHERE id = ?";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(Query);

			ps.setInt(1, sizeId);

			rs = ps.executeQuery();

			if (rs.next()) {
				size = new Size();

				size.setId(rs.getInt("id"));
				size.setValue(rs.getString("value"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return size;
	}

	// business validation

	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean sizeAlreadyExists(int sizeId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag = false;

		try {
			String query = "SELECT 1 FROM sizes WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, sizeId);

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
