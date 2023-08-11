package in.fssa.myfashionstudioapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.model.Product;
import in.fssa.myfashionstudioapp.util.ConnectionUtil;

public class ProductDAO {

	public int create(Product newProduct) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		// Get the generated product ID
		int productId = -1;

		try {
			String Query = "Insert into products (name, description, categories_id) values(?,?,?)";

			con = ConnectionUtil.getConnection();

//			to prevent the product details to be stored in the db

			// Disable auto-commit

			ps = con.prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, newProduct.getName());
			ps.setString(2, newProduct.getDescription());
			ps.setInt(3, newProduct.getCategory().getId());

			ps.executeUpdate();

			ResultSet generatedKeys = ps.getGeneratedKeys();

			if (generatedKeys.next()) {
				productId = generatedKeys.getInt(1);
			} else {
				throw new SQLException("Creating product failed, no ID obtained.");
			}

			System.out.println("Product details created successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return productId;

	}

}
