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
		ResultSet rs = null;

		// Get the generated product ID
		int productId = -1;

		try {
			String Query = "INSERT INTO products (name, description, category_id) VALUES(?,?,?) ";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, newProduct.getName());
			ps.setString(2, newProduct.getDescription());
			ps.setInt(3, newProduct.getCategory().getId());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();

			if (rs.next()) {
				productId = rs.getInt(1);
			} else {
				throw new PersistenceException("Creating product failed, no ID obtained.");
			}

			System.out.println("Created product details ");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return productId;

	}

	/**
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	public List<ProductDTO> findAll() throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductDTO> productDTOList = new ArrayList<>();

		try {
			String Query = "SELECT id , name , description , category_id FROM products WHERE status = 1";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(Query);

			rs = ps.executeQuery();

			while (rs.next()) {

				ProductDTO productDTO = new ProductDTO();
				productDTO.setId(rs.getInt("id"));
				productDTO.setName(rs.getString("name"));
				productDTO.setDescription(rs.getString("description"));
				productDTO.getCategory().setId(rs.getInt("category_id"));

				productDTOList.add(productDTO);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return productDTOList;

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistenceException
	 */
	public List<ProductDTO> findAllByCategoryId(int categoryId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductDTO> productDTOList = new ArrayList<>();

		try {

			String Query = "SELECT id , name , description , category_id FROM products WHERE category_id = ? AND status = 1";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(Query);

			ps.setInt(1, categoryId);

			rs = ps.executeQuery();

			while (rs.next()) {

				ProductDTO productDTO = new ProductDTO();
				productDTO.setId(rs.getInt("id"));
				productDTO.setName(rs.getString("name"));
				productDTO.setDescription(rs.getString("description"));
				productDTO.getCategory().setId(rs.getInt("category_id"));

//				how will i set the category name  

				productDTOList.add(productDTO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return productDTOList;

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistenceException
	 */
	public ProductDTO findByProducId(int productId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ProductDTO productDTO = null;

		try {
			String Query = "SELECT id , name , description , category_id  FROM products WHERE id = ? AND status = 1";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(Query);
			ps.setInt(1, productId);

			rs = ps.executeQuery();

			while (rs.next()) {

				productDTO = new ProductDTO();
				productDTO.setId(rs.getInt("id"));
				productDTO.setName(rs.getString("name"));
				productDTO.setDescription(rs.getString("description"));
				productDTO.getCategory().setId(rs.getInt("category_id"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return productDTO;

	}

	/**
	 * 
	 * @param id
	 * @param updatedProduct
	 * @throws PersistenceException
	 */
	public void updateProductDetails(ProductDTO updatedProduct) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			String Query = "UPDATE products SET name = ? , description = ? WHERE id = ? AND status = 1";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(Query);

			ps.setString(1, updatedProduct.getName());
			ps.setString(2, updatedProduct.getDescription());
			ps.setInt(3, updatedProduct.getId());

			ps.executeUpdate();

			System.out.println("Product details updated sucessfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean productAlreadyExists(int productId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag = false;

		try {
			String query = "SELECT 1 FROM products WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, productId);

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

	public boolean DuplicateProductDoesNotAlreadyExists(Product product) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag = true;

		try {
			String query = "SELECT 1 FROM products WHERE name = ? AND description = ? AND category_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setInt(3, product.getCategory().getId());

			rs = ps.executeQuery();

			if (rs.next()) {
				flag = false;
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

	public void delete(int id) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE products SET status = ? WHERE status = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);

			ps.setInt(1, 0);
			ps.setInt(2, id);
			ps.executeUpdate();

			System.out.println("Product has been successfully deactivated");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps);
		}

	}

}
