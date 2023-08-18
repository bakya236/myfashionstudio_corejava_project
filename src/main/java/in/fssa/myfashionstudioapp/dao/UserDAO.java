package in.fssa.myfashionstudioapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.myfashionstudioapp.model.User;
import in.fssa.myfashionstudioapp.util.ConnectionUtil;

public class UserDAO {

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<User> findAll() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<User>();

		try {
			String query = "select * from users where is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setIs_active(rs.getBoolean("is_active"));

				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new SQLException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return userList;
	}

}
