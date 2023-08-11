package in.fssa.myfashionstudioapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.myfashionstudioapp.model.Gender;
import in.fssa.myfashionstudioapp.util.ConnectionUtil;

public class GenderDAO {

	public List<Gender> findAll() throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Gender> genderList = new ArrayList<Gender>();

		try {
			String query = "select * from genders";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Gender gender = new Gender();
				gender.setName(rs.getString("gender_name"));

				genderList.add(gender);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new SQLException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return genderList;
	}

	public Gender findById(int id) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Gender gender = null;

		try {
			String query = "select * from genders where id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				gender = new Gender();
				gender.setName(rs.getString("gender_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new SQLException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return gender;
	}

}
