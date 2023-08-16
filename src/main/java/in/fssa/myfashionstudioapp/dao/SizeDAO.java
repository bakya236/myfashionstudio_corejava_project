package in.fssa.myfashionstudioapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.model.Size;
import in.fssa.myfashionstudioapp.util.ConnectionUtil;

public class SizeDAO {

	public Size FindSizeBySizeId(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Size size = null;

		try {
			String Query = "Select * from sizes where id = ?";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(Query);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				size = new Size();

				size.setId(id);
				size.setValue(rs.getString("value"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return size;
	}

	public boolean sizeAldreadyExists(int id) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag = false;

		try {
			String query = "Select * from sizes where id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}

		return flag;

	}
}
