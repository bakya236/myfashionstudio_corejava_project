package in.fssa.myfashionstudioapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.myfashionstudioapp.dto.ProductDTO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.model.Product;
import in.fssa.myfashionstudioapp.util.ConnectionUtil;

public class ProductDAO {

	/**
	 * 
	 * @param newProduct
	 * @return
	 * @throws PersistenceException
	 */
	public int create(Product newProduct) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		// Get the generated product ID
		int productId = -1;

		try {
			String Query = "Insert into products (name, description, categories_id) values(?,?,?)";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, newProduct.getName());
			ps.setString(2, newProduct.getDescription());
			ps.setInt(3, newProduct.getCategory().getId());

			ps.executeUpdate();

			ResultSet generatedKeys = ps.getGeneratedKeys();

			if (generatedKeys.next()) {
				productId = generatedKeys.getInt(1);
			} else {
				throw new PersistenceException("Creating product failed, no ID obtained.");
			}

			System.out.println("Product details created successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}

		return productId;

	}

//	find all products 

	/**
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	public List<ProductDTO> findAllProducts() throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductDTO> productDtoList = new ArrayList<>();

		try {
			String Query = "Select * from products";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(Query);

			rs = ps.executeQuery();

			while (rs.next()) {

				ProductDTO productDto = new ProductDTO();
				productDto.setId(rs.getInt("id"));
				productDto.setName(rs.getString("name"));
				productDto.setDescription(rs.getString("description"));
				productDto.getCategory().setId(rs.getInt("categories_id"));

				productDtoList.add(productDto);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return productDtoList;

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistenceException
	 */
	public ProductDTO findProductDetailsByProductId(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ProductDTO productDto = null;

		try {
			String Query = "Select * from products where id = ?";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(Query);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {

				productDto = new ProductDTO();
				productDto.setId(rs.getInt("id"));
				productDto.setName(rs.getString("name"));
				productDto.setDescription(rs.getString("description"));
				productDto.getCategory().setId(rs.getInt("categories_id"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return productDto;

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistenceException
	 */
	public List<ProductDTO> findAllProductsByCategoryId(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductDTO> productDtoList = new ArrayList<>();

		try {

			String Query = "Select * from products where categories_id = ?";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(Query);

			rs = ps.executeQuery();

			while (rs.next()) {

				ProductDTO productDto = new ProductDTO();
				productDto.setId(rs.getInt("id"));
				productDto.setName(rs.getString("name"));
				productDto.setDescription(rs.getString("description"));
				productDto.getCategory().setId(rs.getInt("categories_id"));

//				how will i set the category name  

				productDtoList.add(productDto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return productDtoList;

	}

	/**
	 * 
	 * @param id
	 * @param updatedProduct
	 * @throws PersistenceException
	 */
	public void updateProductDetails(int id, ProductDTO updatedProduct) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			String Query = "update products set name = ? , description = ? where id = ?";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(Query);

			ps.setString(1, updatedProduct.getName());
			ps.setString(2, updatedProduct.getDescription());
			ps.setInt(3, id);

			ps.executeUpdate();

			System.out.println("Product details updated sucessfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	// business validation - product aldready exists

	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean productAldreadyExists(int id) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag = false;

		try {
			String query = "Select * from products where id = ?";
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
