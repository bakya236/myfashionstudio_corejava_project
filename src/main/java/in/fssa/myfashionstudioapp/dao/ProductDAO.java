package in.fssa.myfashionstudioapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.myfashionstudioapp.dto.ProductDTO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.model.Category;
import in.fssa.myfashionstudioapp.model.Color;
import in.fssa.myfashionstudioapp.model.Gender;
import in.fssa.myfashionstudioapp.model.Price;
import in.fssa.myfashionstudioapp.model.Product;
import in.fssa.myfashionstudioapp.model.Size;
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
			String query = "INSERT INTO products (image , name, description, category_id) VALUES(?,?,?,?) ";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, newProduct.getImage());
			ps.setString(2, newProduct.getName());
			ps.setString(3, newProduct.getDescription());
			ps.setInt(4, newProduct.getCategory().getId());

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected < 0) {
				throw new PersistenceException("user has not been created");
			}

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
	 * Retrieves a list of active products from the database.
	 *
	 * This method queries the database to retrieve a list of active products, where
	 * each product is associated with its details, including product ID, image,
	 * name, stock, buyers count, category information, gender information, color
	 * information, price details, and size information. The retrieved products are
	 * filtered based on their status and active prices.
	 * 
	 * @param offset
	 * @param limit
	 * 
	 * @return A list of ProductDTO objects representing active products with their
	 *         associated details.
	 * @throws PersistenceException If an error occurs during the database
	 *                              interaction, such as a SQL query error or
	 *                              database connection issue.
	 */
	public List<ProductDTO> findAll(int startId, int endId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductDTO> productDTOList = new ArrayList<>();

		try {
			String query = "SELECT DISTINCT " + "p.id AS product_id, " + "p.image, " + "p.name AS product_name, "
					+ "p.stock, " + "p.buyers_count, " + "c.id AS category_id, " + "c.category_name, "
					+ "g.id AS gender_id, " + "g.gender_name, " + "col.id AS color_id, " + "col.color_name, "
					+ "pr.id AS price_id, " + "pr.price, " + "pr.offer, " + "pr.ended_at, " + "s.id AS size_id, "
					+ "s.value " + "FROM products AS p " + "JOIN categories AS c ON p.category_id = c.id "
					+ "JOIN genders AS g ON c.gender_id = g.id " + "JOIN colors AS col ON p.color_id = col.id "
					+ "JOIN prices AS pr ON p.id = pr.product_id " + "JOIN sizes AS s ON pr.size_id = s.id "
					+ "WHERE p.status = 1 " + "AND pr.ended_at IS NULL " + "AND p.id BETWEEN ? AND ?";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(query);
			ps.setInt(1, startId);
			ps.setInt(2, endId);

			rs = ps.executeQuery();

			int currentProductId = -1;

			ProductDTO productDTO = null;

			while (rs.next()) {

				int productId = rs.getInt("product_id");

				if (productId != currentProductId) {

					productDTO = new ProductDTO();

					productDTO.setId(productId);
					productDTO.setImage(rs.getString("image"));
					productDTO.setName(rs.getString("product_name"));

					Category category = new Category(rs.getInt("category_id"));
					category.setName(rs.getString("category_name"));

					Gender gender = new Gender(rs.getInt("gender_id"));
					gender.setName(rs.getString("gender_name"));

					category.setGender(gender);

					productDTO.setCategory(category);
					productDTO.setStock(rs.getInt("stock"));
					productDTO.setBuyersCount(rs.getInt("buyers_count"));

					List<Price> priceList = new ArrayList<>();

					productDTO.setPriceList(priceList);

					productDTOList.add(productDTO);

					currentProductId = productId;

				}

				Price price = new Price();
				price.setId(rs.getInt("price_id"));
				price.setPrice(rs.getDouble("price"));
				price.setOffer(rs.getDouble("offer"));

				Size size = new Size();
				size.setId(rs.getInt("size_id"));
				size.setValue(rs.getString("value"));
				price.setSize(size);

				productDTO.getPriceList().add(price);

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
	 * Retrieves a list of active products within a specific category from the
	 * database.
	 *
	 * This method queries the database to retrieve a list of active products that
	 * belong to a specific category based on its unique identifier (ID). The
	 * retrieved products are associated with detailed information, including
	 * product ID, image, name, stock, buyers count, category information, gender
	 * information, color information, price details, and size information. The
	 * products are filtered based on their status and active prices.
	 * 
	 * @param categoryId The unique identifier (ID) of the category for which
	 *                   products are to be retrieved.
	 * @param offset
	 * @param limit
	 * @return A list of ProductDTO objects representing active products within the
	 *         specified category with their associated details.
	 * @throws PersistenceException If an error occurs during the database
	 *                              interaction, such as a SQL query error or a
	 *                              database connection issue.
	 */
	public List<ProductDTO> findAllByCategoryId(int categoryId, int startId, int endId) throws PersistenceException {

		System.out.println("startId" + startId);
		System.out.println("endId" + endId);

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductDTO> productDTOList = new ArrayList<>();

		try {
			String query = "SELECT " + "p.id AS product_id, " + "p.image, " + "p.name AS product_name, " + "p.stock, "
					+ "p.buyers_count, " + "c.id AS category_id, " + "c.category_name, " + "g.id AS gender_id, "
					+ "g.gender_name, " + "col.id AS color_id, " + "col.color_name, " + "pr.id AS price_id, "
					+ "pr.price, " + "pr.offer, " + "pr.ended_at, " + "s.id AS size_id, " + "s.value "
					+ "FROM products AS p " + "JOIN categories AS c ON p.category_id = c.id "
					+ "JOIN genders AS g ON c.gender_id = g.id " + "JOIN colors AS col ON p.color_id = col.id "
					+ "JOIN prices AS pr ON p.id = pr.product_id " + "JOIN sizes AS s ON pr.size_id = s.id "
					+ "WHERE category_id = ? AND p.status = 1 " + "AND pr.ended_at IS NULL "
					+ "AND p.id BETWEEN ? AND ?";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(query);

			ps.setInt(1, categoryId);
			ps.setInt(2, startId);
			ps.setInt(3, endId);

			rs = ps.executeQuery();

			int currentProductId = -1;

			ProductDTO productDTO = null;

			while (rs.next()) {

				int productId = rs.getInt("product_id");

				if (productId != currentProductId) {

					productDTO = new ProductDTO();

					productDTO.setId(productId);
					productDTO.setImage(rs.getString("image"));
					productDTO.setName(rs.getString("product_name"));

					Category category = new Category(categoryId);
					category.setName(rs.getString("category_name"));

					Gender gender = new Gender(rs.getInt("gender_id"));
					gender.setName(rs.getString("gender_name"));

					category.setGender(gender);

					productDTO.setCategory(category);
					productDTO.setStock(rs.getInt("stock"));
					productDTO.setBuyersCount(rs.getInt("buyers_count"));

					List<Price> priceList = new ArrayList<>();

					productDTO.setPriceList(priceList);

					productDTOList.add(productDTO);

					currentProductId = productId;

				}

				Price price = new Price();
				price.setId(rs.getInt("price_id"));
				price.setPrice(rs.getDouble("price"));
				price.setOffer(rs.getDouble("offer"));

				Size size = new Size();
				size.setId(rs.getInt("size_id"));
				size.setValue(rs.getString("value"));
				price.setSize(size);

				productDTO.getPriceList().add(price);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		System.out.println(productDTOList.size());

		return productDTOList;

	}

	public List<ProductDTO> findByName(String name) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductDTO> productDTOList = new ArrayList<>();

		try {
			String query = "SELECT " + "p.id AS product_id, " + "p.image, " + "p.name AS product_name, " + "p.stock, "
					+ "p.buyers_count, " + "c.id AS category_id, " + "c.category_name, " + "g.id AS gender_id, "
					+ "g.gender_name, " + "col.id AS color_id, " + "col.color_name, " + "pr.id AS price_id, "
					+ "pr.price, " + "pr.offer, " + "pr.ended_at, " + "s.id AS size_id, " + "s.value "
					+ "FROM products AS p " + "JOIN categories AS c ON p.category_id = c.id "
					+ "JOIN genders AS g ON c.gender_id = g.id " + "JOIN colors AS col ON p.color_id = col.id "
					+ "JOIN prices AS pr ON p.id = pr.product_id " + "JOIN sizes AS s ON pr.size_id = s.id "
					+ "WHERE p.name LIKE ? " + "AND p.status = 1 " + "AND pr.ended_at IS NULL;";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(query);

			String searchInput = "%" + name + "%";
			ps.setString(1, searchInput);

			rs = ps.executeQuery();

			int currentProductId = -1;

			ProductDTO productDTO = null;

			while (rs.next()) {

				int productId = rs.getInt("product_id");

				if (productId != currentProductId) {

					productDTO = new ProductDTO();

					productDTO.setId(productId);
					productDTO.setImage(rs.getString("image"));
					productDTO.setName(rs.getString("product_name"));

					Category category = new Category(rs.getInt("category_id"));
					category.setName(rs.getString("category_name"));

					Gender gender = new Gender(rs.getInt("gender_id"));
					gender.setName(rs.getString("gender_name"));

					category.setGender(gender);

					productDTO.setCategory(category);
					productDTO.setStock(rs.getInt("stock"));
					productDTO.setBuyersCount(rs.getInt("buyers_count"));

					List<Price> priceList = new ArrayList<>();

					productDTO.setPriceList(priceList);

					productDTOList.add(productDTO);

					currentProductId = productId;

				}

				Price price = new Price();
				price.setId(rs.getInt("price_id"));
				price.setPrice(rs.getDouble("price"));
				price.setOffer(rs.getDouble("offer"));

				Size size = new Size();
				size.setId(rs.getInt("size_id"));
				size.setValue(rs.getString("value"));
				price.setSize(size);

				productDTO.getPriceList().add(price);

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

	public List<ProductDTO> findByColorNameAndProductName(String color) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductDTO> productDTOList = new ArrayList<>();

		try {
			String query = "SELECT " + "p.id AS product_id, " + "p.image, " + "p.name AS product_name, " + "p.stock, "
					+ "p.buyers_count, " + "c.id AS category_id, " + "c.category_name, " + "g.id AS gender_id, "
					+ "g.gender_name, " + "col.id AS color_id, " + "col.color_name, " + "pr.id AS price_id, "
					+ "pr.price, " + "pr.offer, " + "pr.ended_at, " + "s.id AS size_id, " + "s.value "
					+ "FROM products AS p " + "JOIN categories AS c ON p.category_id = c.id "
					+ "JOIN genders AS g ON c.gender_id = g.id " + "JOIN colors AS col ON p.color_id = col.id "
					+ "JOIN prices AS pr ON p.id = pr.product_id " + "JOIN sizes AS s ON pr.size_id = s.id "
					+ "WHERE (p.name LIKE ? OR col.color_name = ?) " + "AND p.status = 1 " + "AND pr.ended_at IS NULL;";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(query);

			String searchInput = "%" + color + "%";
			ps.setString(1, searchInput);
			ps.setString(2, color);

			rs = ps.executeQuery();

			int currentProductId = -1;

			ProductDTO productDTO = null;

			while (rs.next()) {

				System.out.println("1+++");

				int productId = rs.getInt("product_id");

				if (productId != currentProductId) {

					System.out.println("2+++");

					productDTO = new ProductDTO();

					productDTO.setId(productId);
					productDTO.setImage(rs.getString("image"));
					productDTO.setName(rs.getString("product_name"));

					Category category = new Category(rs.getInt("category_id"));
					category.setName(rs.getString("category_name"));

					Gender gender = new Gender(rs.getInt("gender_id"));
					gender.setName(rs.getString("gender_name"));

					category.setGender(gender);

					productDTO.setCategory(category);

					Color color1 = new Color(rs.getInt("color_id"));
					color1.setColorName(rs.getString("color_name"));

					productDTO.setColor(color1);

					productDTO.setStock(rs.getInt("stock"));
					productDTO.setBuyersCount(rs.getInt("buyers_count"));

					List<Price> priceList = new ArrayList<>();

					productDTO.setPriceList(priceList);

					productDTOList.add(productDTO);

					currentProductId = productId;

				}

				Price price = new Price();
				price.setId(rs.getInt("price_id"));
				price.setPrice(rs.getDouble("price"));
				price.setOffer(rs.getDouble("offer"));

				Size size = new Size();
				size.setId(rs.getInt("size_id"));
				size.setValue(rs.getString("value"));
				price.setSize(size);

				productDTO.getPriceList().add(price);

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

	public List<ProductDTO> findByGenderName(String genderName) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductDTO> productDTOList = new ArrayList<>();

		try {
			String query = "SELECT " + "p.id AS product_id, " + "p.image, " + "p.name AS product_name, " + "p.stock, "
					+ "p.buyers_count, " + "c.id AS category_id, " + "c.category_name, " + "g.id AS gender_id, "
					+ "g.gender_name, " + "col.id AS color_id, " + "col.color_name, " + "pr.id AS price_id, "
					+ "pr.price, " + "pr.offer, " + "pr.ended_at, " + "s.id AS size_id, " + "s.value "
					+ "FROM products AS p " + "JOIN categories AS c ON p.category_id = c.id "
					+ "JOIN genders AS g ON c.gender_id = g.id " + "JOIN colors AS col ON p.color_id = col.id "
					+ "JOIN prices AS pr ON p.id = pr.product_id " + "JOIN sizes AS s ON pr.size_id = s.id "
					+ "WHERE g.gender_name = ? " + "AND p.status = 1 " + "AND pr.ended_at IS NULL;";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(query);

			String searchInput = genderName;
			ps.setString(1, searchInput);

			rs = ps.executeQuery();

			int currentProductId = -1;

			ProductDTO productDTO = null;

			while (rs.next()) {

				int productId = rs.getInt("product_id");

				if (productId != currentProductId) {

					productDTO = new ProductDTO();

					productDTO.setId(productId);
					productDTO.setImage(rs.getString("image"));
					productDTO.setName(rs.getString("product_name"));

					Category category = new Category(rs.getInt("category_id"));
					category.setName(rs.getString("category_name"));

					Gender gender = new Gender(rs.getInt("gender_id"));
					gender.setName(rs.getString("gender_name"));

					category.setGender(gender);

					productDTO.setCategory(category);
					productDTO.setStock(rs.getInt("stock"));
					productDTO.setBuyersCount(rs.getInt("buyers_count"));

					List<Price> priceList = new ArrayList<>();

					productDTO.setPriceList(priceList);

					productDTOList.add(productDTO);

					currentProductId = productId;

				}

				Price price = new Price();
				price.setId(rs.getInt("price_id"));
				price.setPrice(rs.getDouble("price"));
				price.setOffer(rs.getDouble("offer"));

				Size size = new Size();
				size.setId(rs.getInt("size_id"));
				size.setValue(rs.getString("value"));
				price.setSize(size);

				productDTO.getPriceList().add(price);

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

	public List<ProductDTO> findByGenderNameAndCategoryName(String genderName, String categoryName)
			throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductDTO> productDTOList = new ArrayList<>();

		try {
			String query = "SELECT " + "p.id AS product_id, " + "p.image, " + "p.name AS product_name, " + "p.stock, "
					+ "p.buyers_count, " + "c.id AS category_id, " + "c.category_name, " + "g.id AS gender_id, "
					+ "g.gender_name, " + "col.id AS color_id, " + "col.color_name, " + "pr.id AS price_id, "
					+ "pr.price, " + "pr.offer, " + "pr.ended_at, " + "s.id AS size_id, " + "s.value "
					+ "FROM products AS p " + "JOIN categories AS c ON p.category_id = c.id "
					+ "JOIN genders AS g ON c.gender_id = g.id " + "JOIN colors AS col ON p.color_id = col.id "
					+ "JOIN prices AS pr ON p.id = pr.product_id " + "JOIN sizes AS s ON pr.size_id = s.id "
					+ "WHERE g.gender_name = ? " + "AND c.category_name = ? " + "AND p.status = 1 "
					+ "AND pr.ended_at IS NULL;";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(query);

			ps.setString(1, genderName);
			ps.setString(2, categoryName);

			rs = ps.executeQuery();

			int currentProductId = -1;

			ProductDTO productDTO = null;

			while (rs.next()) {

				int productId = rs.getInt("product_id");

				if (productId != currentProductId) {

					productDTO = new ProductDTO();

					productDTO.setId(productId);
					productDTO.setImage(rs.getString("image"));
					productDTO.setName(rs.getString("product_name"));

					Category category = new Category(rs.getInt("category_id"));
					category.setName(rs.getString("category_name"));

					Gender gender = new Gender(rs.getInt("gender_id"));
					gender.setName(rs.getString("gender_name"));

					category.setGender(gender);

					productDTO.setCategory(category);
					productDTO.setStock(rs.getInt("stock"));
					productDTO.setBuyersCount(rs.getInt("buyers_count"));

					List<Price> priceList = new ArrayList<>();

					productDTO.setPriceList(priceList);

					productDTOList.add(productDTO);

					currentProductId = productId;

				}

				Price price = new Price();
				price.setId(rs.getInt("price_id"));
				price.setPrice(rs.getDouble("price"));
				price.setOffer(rs.getDouble("offer"));

				Size size = new Size();
				size.setId(rs.getInt("size_id"));
				size.setValue(rs.getString("value"));
				price.setSize(size);

				productDTO.getPriceList().add(price);

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

	public List<ProductDTO> findByCategoryName(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Retrieves detailed information about a specific product based on its unique
	 * identifier (ID) from the database.
	 *
	 * This method queries the database to fetch comprehensive information about a
	 * specific product, including its ID, image, name, pattern, fit, occasion,
	 * length, rise type, closure type, sleeve type, neckline type, stock, buyers
	 * count, color, category, gender, and associated price list. The product is
	 * identified by its unique product ID and must be active with active prices.
	 * 
	 * @param productId The unique identifier (ID) of the product for which details
	 *                  are to be retrieved.
	 * @return A ProductDTO object containing extensive product details, including
	 *         its characteristics, category, gender, color, and associated prices.
	 * @throws PersistenceException If an error occurs during the database
	 *                              interaction, such as a SQL query error or a
	 *                              database connection issue.
	 */
	public ProductDTO findByProducId(int productId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ProductDTO productDTO = null;

		try {

			con = ConnectionUtil.getConnection();

			String query = "SELECT " + "p.id AS product_id, " + "p.image, " + "p.name AS product_name, " + "p.pattern, "
					+ "p.fit, " + "p.material, " + "p.length, " + "p.rise_type, " + "p.closure_type, "
					+ "p.sleeve_type, " + "p.neckline_type, " + "p.occasion, " + "p.care, " + "p.description, "
					+ "p.stock, " + "p.buyers_count, " + "p.color_id, " + "p.category_id, " + "c.id AS category_id, "
					+ "c.category_name, " + "g.id AS gender_id, " + "g.gender_name, " + "col.id AS color_id, "
					+ "col.color_name, " + "col.color_hex_code, " + "pr.id AS price_id, " + "pr.price, " + "pr.offer, "
					+ "pr.ended_at, " + "s.id AS size_id, " + "s.value " + "FROM products AS p "
					+ "INNER JOIN categories AS c ON p.category_id = c.id "
					+ "INNER JOIN genders AS g ON c.gender_id = g.id "
					+ "INNER JOIN colors AS col ON p.color_id = col.id "
					+ "INNER JOIN prices AS pr ON p.id = pr.product_id " + "INNER JOIN sizes AS s ON pr.size_id = s.id "
					+ "WHERE product_id = ? AND p.status = 1 " + "AND pr.ended_at IS NULL;";

			ps = con.prepareStatement(query);
			ps.setInt(1, productId);

			rs = ps.executeQuery();

			int currentProductId = -1;

			while (rs.next()) {

				int productId1 = rs.getInt("product_id");

				if (productId1 != currentProductId) {

					productDTO = new ProductDTO();

					productDTO.setId(productId1);
					productDTO.setImage(rs.getString("image"));
					productDTO.setName(rs.getString("product_name"));

					Category category = new Category(rs.getInt("category_id"));
					category.setName(rs.getString("category_name"));

					Gender gender = new Gender(rs.getInt("gender_id"));
					gender.setName(rs.getString("gender_name"));

					category.setGender(gender);

					productDTO.setCategory(category);

					Color color = new Color(rs.getInt("color_id"));
					color.setColorName(rs.getString("color_name"));
					color.setColorHexCode(rs.getString("color_hex_code"));

					productDTO.setColor(color);

					productDTO.setPattern(rs.getString("pattern"));
					productDTO.setFit(rs.getString("fit"));
					productDTO.setMaterial(rs.getString("material"));
					productDTO.setLength(rs.getString("length"));
					productDTO.setRiseType(rs.getString("rise_type"));
					productDTO.setClosureType(rs.getString("closure_type"));
					productDTO.setSleeveType(rs.getString("sleeve_type"));
					productDTO.setNecklineType(rs.getString("neckline_type"));
					productDTO.setOccasion(rs.getString("occasion"));
					productDTO.setCare(rs.getString("care"));

					productDTO.setDescription(rs.getString("description"));

					productDTO.setStock(rs.getInt("stock"));
					productDTO.setBuyersCount(rs.getInt("buyers_count"));

					List<Price> priceList = new ArrayList<>();

					productDTO.setPriceList(priceList);

					currentProductId = productId1;

				}

				Price price = new Price();
				price.setId(rs.getInt("price_id"));
				price.setPrice(rs.getDouble("price"));
				price.setOffer(rs.getDouble("offer"));

				Size size = new Size();
				size.setId(rs.getInt("size_id"));
				size.setValue(rs.getString("value"));
				price.setSize(size);

				productDTO.getPriceList().add(price);

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
			String query = "UPDATE products SET image = ? ,name = ? , description = ? WHERE id = ? AND status = 1";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(query);

			ps.setString(1, updatedProduct.getImage());
			ps.setString(2, updatedProduct.getName());
			ps.setString(3, updatedProduct.getDescription());
			ps.setInt(4, updatedProduct.getId());

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
	 * Checks if a product with the specified unique identifier (ID) already exists
	 * in the database.
	 *
	 * This method queries the database to determine whether a product with the
	 * given ID is already present in the database. If a product with the specified
	 * ID is found, the method returns true, indicating that the product already
	 * exists; otherwise, it returns false.
	 * 
	 * @param productId The unique identifier (ID) of the product to check for
	 *                  existence.
	 * @return true if a product with the provided ID exists in the database; false
	 *         otherwise.
	 * @throws PersistenceException If an error occurs during the database
	 *                              interaction, such as a SQL query error or a
	 *                              database connection issue.
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

	/**
	 * Deactivates a product by updating its status in the database.
	 *
	 * This method sets the status of a product with the specified ID to 0 in the
	 * database, effectively deactivating the product.
	 *
	 * @param id The unique identifier of the product to be deactivated.
	 * @throws PersistenceException If there is a database-related error while
	 *                              trying to deactivate the product.
	 */

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
