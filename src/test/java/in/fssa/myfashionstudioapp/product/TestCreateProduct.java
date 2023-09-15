package in.fssa.myfashionstudioapp.product;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.dto.ProductDTO;
import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.Category;
import in.fssa.myfashionstudioapp.model.Price;
import in.fssa.myfashionstudioapp.model.Size;
import in.fssa.myfashionstudioapp.service.ProductService;

public class TestCreateProduct {

	// TODO create generator

	@Test
	public void createProductWithValidInput() {

		// create the dto instance

		ProductDTO productDTO = new ProductDTO();

		productDTO.setImage("https://iili.io/HSI63ua.webp");
		productDTO.setName("crew neck t-shirt");
		productDTO.setDescription("graphitte printed navy blue colored With crew neck cotton T-shirt ");

		Category category = new Category(1);
		productDTO.setCategory(category);

		List<Price> priceList = productDTO.getPriceList(); // []

		Size size1 = new Size(1);
		Price price1 = new Price();
		price1.setSize(size1);
		price1.setPrice(600.00d);

		Size size2 = new Size(2);
		Price price2 = new Price();
		price2.setSize(size2);
		price2.setPrice(400.00d);

		priceList.add(price1);
		priceList.add(price2);

		productDTO.setPriceList(priceList);

		ProductService productService = new ProductService();

		assertDoesNotThrow(() -> {
			productService.createProduct(productDTO);
		});
	}

	@Test

	public void createProductWithInvalidInput() {

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProduct(null);

		});

		String expectedMessage = "Product cannot be null";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test

	public void createProductNameWithNull() {

		// create the dto instance

		ProductDTO productDTO = new ProductDTO();

		List<Price> priceList = productDTO.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(1);

		price1.setSize(size1);
		price1.setPrice(600.00d);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(290.00d);
		//

		priceList.add(price1);
		priceList.add(price2);

		//

		productDTO.setPriceList(priceList);

		Category category = new Category();
		category.setId(1);
		productDTO.setImage("https://iili.io/HSI63ua.webp");
		productDTO.setCategory(category);
		productDTO.setName(null);
		productDTO.setDescription("v-neck typography printed navy blue color T-shirt ");

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProduct(productDTO);

		});

		String expectedMessage = "Product name cannot be empty";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test

	public void createProductNameWithEmpty() {

		// create the dto instance

		ProductDTO productDTO = new ProductDTO();

		List<Price> priceList = productDTO.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(1);

		price1.setSize(size1);
		price1.setPrice(600.00d);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(290.00d);
		//

		priceList.add(price1);
		priceList.add(price2);

		//

		productDTO.setPriceList(priceList);

		Category category = new Category();
		category.setId(1);

		productDTO.setCategory(category);
		productDTO.setImage("https://iili.io/HSI63ua.webp");
		productDTO.setName("");
		productDTO.setDescription("v-neck typography printed navy blue color T-shirt ");

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProduct(productDTO);

		});

		String expectedMessage = "Product name cannot be empty";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	// pattern check
	@Test
	public void createProductNameWithInvalidFormat() {

		// create the dto instance

		ProductDTO productDTO = new ProductDTO();
		productDTO.setImage("https://iili.io/HSI63ua.webp");
		productDTO.setName("v-neck ?% neck T-shirt");
		productDTO.setDescription("v-neck typography printed navy blue color T-shirt ");

		Category category = new Category(1);
		productDTO.setCategory(category);

		List<Price> priceList = productDTO.getPriceList();

		Size size1 = new Size(1);
		Price price1 = new Price();
		price1.setSize(size1);
		price1.setPrice(600.00d);

		Size size2 = new Size(2);
		Price price2 = new Price();
		price2.setSize(size2);
		price2.setPrice(400.00d);

		priceList.add(price1);
		priceList.add(price2);

		productDTO.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProduct(productDTO);

		});

		String expectedMessage = "Invalid product name";

		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void createProductNameWithInvalidLengths() {

		// create the dto instance

		ProductDTO productDTO = new ProductDTO();
		productDTO.setImage("https://iili.io/HSI63ua.webp");
		productDTO.setName("v-");
		productDTO.setDescription("v-neck typography printed navy blue color T-shirt ");

		Category category = new Category(1);
		productDTO.setCategory(category);

		List<Price> priceList = productDTO.getPriceList();

		Size size1 = new Size(1);
		Price price1 = new Price();
		price1.setSize(size1);
		price1.setPrice(600.00d);

		Size size2 = new Size(2);
		Price price2 = new Price();
		price2.setSize(size2);
		price2.setPrice(400.00d);

		priceList.add(price1);
		priceList.add(price2);

		productDTO.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProduct(productDTO);

		});

		String expectedMessage = "Product name should be between 3 and 50 characters.";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test

	public void createProductDescriptionWithNull() {

		// create the dto instance

		ProductDTO productDTO = new ProductDTO();

		List<Price> priceList = productDTO.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(1);

		price1.setSize(size1);
		price1.setPrice(600.00d);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(290.00d);
		//

		priceList.add(price1);
		priceList.add(price2);

		//

		productDTO.setPriceList(priceList);

		Category category = new Category();
		category.setId(1);
		productDTO.setImage("https://iili.io/HSI63ua.webp");
		productDTO.setCategory(category);
		productDTO.setName("v-neck neck T-shirt");
		productDTO.setDescription(null);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProduct(productDTO);

		});

		String expectedMessage = "Product description cannot be empty";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void createProductDescriptionWithEmpty() {

		// create the dto instance

		ProductDTO productDTO = new ProductDTO();

		List<Price> priceList = productDTO.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size(1);
		// size1.setId(1);

		price1.setSize(size1);
		price1.setPrice(600.00d);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size(2);

		price2.setSize(size2);
		price2.setPrice(290.00d);
		//

		priceList.add(price1);
		priceList.add(price2);

		//

		productDTO.setPriceList(priceList);

		Category category = new Category();
		category.setId(1);
		productDTO.setImage("https://iili.io/HSI63ua.webp");
		productDTO.setCategory(category);
		productDTO.setName("v-neck neck T-shirt");
		productDTO.setDescription("");

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProduct(productDTO);

		});

		String expectedMessage = "Product description cannot be empty";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void createProductDescriptionWithInvalidLength() {

		// create the dto instance

		ProductDTO productDTO = new ProductDTO();
		productDTO.setImage("https://iili.io/HSI63ua.webp");
		productDTO.setName("v-neck neck T-shirt");
		productDTO.setDescription("v-n");

		Category category = new Category(1);
		productDTO.setCategory(category);

		List<Price> priceList = productDTO.getPriceList();

		Size size1 = new Size(1);
		Price price1 = new Price();
		price1.setSize(size1);
		price1.setPrice(600.00d);

		Size size2 = new Size(2);
		Price price2 = new Price();
		price2.setSize(size2);
		price2.setPrice(400.00d);

		priceList.add(price1);
		priceList.add(price2);

		productDTO.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProduct(productDTO);

		});

		String expectedMessage = "Product description should be between 10 and 1000 characters.";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void createProductWithInvalidCategory() {

		// create the dto instance

		ProductDTO productDTO = new ProductDTO();

		List<Price> priceList = productDTO.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(1);

		price1.setSize(size1);
		price1.setPrice(600.00d);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(290.00d);
		//

		priceList.add(price1);
		priceList.add(price2);

		//

		productDTO.setPriceList(priceList);

		int invalidCategoryId = -1;
		Category category = new Category();
		category.setId(invalidCategoryId);

		productDTO.setCategory(category);
		productDTO.setImage("https://iili.io/HSI63ua.webp");
		productDTO.setName("v-neck neck T-shirt");
		productDTO.setDescription("v-neck typography printed navy blue color T-shirt");

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProduct(productDTO);

		});

		String expectedMessage = "Invalid catgeory input";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void createProductCategoryDoesNotExist() {

		// create the dto instance

		ProductDTO productDTO = new ProductDTO();

		List<Price> priceList = productDTO.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(1);

		price1.setSize(size1);
		price1.setPrice(600.00d);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(290.00d);
		//

		priceList.add(price1);
		priceList.add(price2);

		//

		productDTO.setPriceList(priceList);

		int invalidCategoryId = 15;
		Category category = new Category();
		category.setId(invalidCategoryId);

		productDTO.setCategory(category);
		productDTO.setImage("https://iili.io/HSI63ua.webp");
		productDTO.setName("v-neck neck T-shirt");
		productDTO.setDescription("v-neck typography printed navy blue color T-shirt");

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProduct(productDTO);

		});

		String expectedMessage = "Category with ID " + invalidCategoryId + " does not exist";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	// testing the gender_id forign key

	@Test
	public void createProductwithInvalidPriceNull() {

		// create the dto instance

		ProductDTO productDTO = new ProductDTO();

		List<Price> priceList = productDTO.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(1);

		price1.setSize(size1);
		price1.setPrice(700.00d);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(290.00d);
		//

		priceList.add(null);
		priceList.add(price2);

		//

		productDTO.setPriceList(priceList);
		productDTO.getCategory().setId(1);

		productDTO.setImage("https://iili.io/HSI63ua.webp");
		productDTO.setName("v-neck neck T-shirt");
		productDTO.setDescription("v-neck typography printed navy blue color T-shirt");

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProduct(productDTO);

		});

		String expectedMessage = "price cannot be null";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void createProductWithInvalidPrice() {

		// create the dto instance

		ProductDTO productDTO = new ProductDTO();

		List<Price> priceList = productDTO.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(1);

		price1.setSize(size1);
		price1.setPrice(0.0);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(290.00d);
		//

		priceList.add(price1);
		priceList.add(price2);

		//

		productDTO.setPriceList(priceList);
		int CategoryId = 1;

		productDTO.setPriceList(priceList);
		Category category = new Category();
		category.setId(CategoryId);
		productDTO.setImage("https://iili.io/HSI63ua.webp");
		productDTO.setCategory(category);
		productDTO.setName("v-neck neck T-shirt");
		productDTO.setDescription("v-neck typography printed navy blue color T-shirt");

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProduct(productDTO);

		});

		String expectedMessage = "Invalid price input";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void createProductWithInvalidPriceLimit() {

		// create the dto instance

		ProductDTO productDTO = new ProductDTO();

		List<Price> priceList = productDTO.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(1);

		price1.setSize(size1);
		price1.setPrice(10000000.00d);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(290.00d);
		//

		priceList.add(price1);
		priceList.add(price2);

		//

		productDTO.setPriceList(priceList);
		int CategoryId = 1;

		productDTO.setPriceList(priceList);
		Category category = new Category();
		category.setId(CategoryId);

		productDTO.setCategory(category);
		productDTO.setImage("https://iili.io/HSI63ua.webp");
		productDTO.setName("v-neck neck T-shirt");
		productDTO.setDescription("v-neck typography printed navy blue color T-shirt");

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProduct(productDTO);

		});

		String expectedMessage = "Product price must not exceed 1,000,000 rupees.";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void createProductWithInvalidSizeInput() {

		// create the dto instance

		ProductDTO productDTO = new ProductDTO();

		List<Price> priceList = productDTO.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(-1);

		price1.setSize(size1);
		price1.setPrice(700);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(290.00d);
		//

		priceList.add(price1);
		priceList.add(price2);

		//

		productDTO.setPriceList(priceList);
		int CategoryId = 1;

		productDTO.setPriceList(priceList);
		Category category = new Category();
		category.setId(CategoryId);

		productDTO.setCategory(category);
		productDTO.setImage("https://iili.io/HSI63ua.webp");
		productDTO.setName("v-neck neck T-shirt");
		productDTO.setDescription("v-neck typography printed navy blue color T-shirt");

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProduct(productDTO);

		});

		String expectedMessage = "Invalid size input";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void createProductSizeDoesNotExist() {

		// create the dto instance

		ProductDTO productDTO = new ProductDTO();

		List<Price> priceList = productDTO.getPriceList();

		//
		Price price1 = new Price();

		int invalidSizeInput = 19;

		Size size1 = new Size();
		size1.setId(invalidSizeInput);

		price1.setSize(size1);
		price1.setPrice(600.00);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(290.00);
		//

		priceList.add(price1);
		priceList.add(price2);

		//

		int CategoryId = 1;

		productDTO.setPriceList(priceList);
		Category category = new Category();
		category.setId(CategoryId);

		productDTO.setCategory(category);
		productDTO.setImage("https://iili.io/HSI63ua.webp");
		productDTO.setName("v-neck neck T-shirt");
		productDTO.setDescription("v-neck typography printed navy blue color T-shirt");

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProduct(productDTO);

		});

		String expectedMessage = "Size with ID " + invalidSizeInput + " does not exist";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	/*
	 * @Test
	 * 
	 * public void createProductWithDuplicateData() {
	 * 
	 * // create the dto instance
	 * 
	 * ProductDTO productDTO = new ProductDTO();
	 * productDTO.setImage("https://iili.io/HSI63ua.webp");
	 * productDTO.setName("v-neck neck T-shirt"); productDTO.
	 * setDescription("v-neck typography printed navy blue color T-shirt ");
	 * 
	 * Category category = new Category(1); productDTO.setCategory(category);
	 * 
	 * List<Price> priceList = productDTO.getPriceList();
	 * 
	 * Size size1 = new Size(1); Price price1 = new Price(); price1.setSize(size1);
	 * price1.setPrice(600.00d);
	 * 
	 * Size size2 = new Size(2); Price price2 = new Price(); price2.setSize(size2);
	 * price2.setPrice(400.00d);
	 * 
	 * priceList.add(price1); priceList.add(price2);
	 * 
	 * productDTO.setPriceList(priceList);
	 * 
	 * ProductService productService = new ProductService();
	 * 
	 * Exception exception = assertThrows(ValidationException.class, () -> {
	 * 
	 * productService.updateProduct(productDTO);
	 * 
	 * });
	 * 
	 * String expectedMessage =
	 * "This product with same name , description ,category already exists"; String
	 * actualMessage = exception.getMessage();
	 * 
	 * System.out.println(actualMessage);
	 * 
	 * assertTrue(expectedMessage.equals(actualMessage)); }
	 */

}
