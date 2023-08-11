package in.fssa.myfashionstudioapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.myfashionstudioapp.model.Category;
import in.fssa.myfashionstudioapp.util.ConnectionUtil;

public class CategoryDAO {

	public List<Category> findAllCatgegoryByGenderId(int id) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Category> categoryList = new ArrayList<>();

		try {
			con = ConnectionUtil.getConnection();
			String query = "Select * From categories where gender_id = ?";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {

				Category category = new Category();
				category.setName(rs.getString("category_name"));

				categoryList.add(category);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return categoryList;

	}

	// business validation

//	connect with db
// check the category aldready exists

	public boolean categoryAldreadyExists(int id) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag = false;

		try {
			String query = "Select * from categories where id = ?";
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
