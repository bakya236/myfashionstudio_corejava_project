package in.fssa.myfashionstudioapp.product;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.dto.ProductDTO;
import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.Category;
import in.fssa.myfashionstudioapp.model.Price;
import in.fssa.myfashionstudioapp.model.Product;
import in.fssa.myfashionstudioapp.model.Size;
import in.fssa.myfashionstudioapp.service.ProductService;

public class TestUpdateProduct {

	@Test
	@Order(1)
	public void updateProductWithValidInput() {

		ProductDTO ProductDTO = new ProductDTO();

		int productId = 28;
		ProductDTO.setId(productId);
		ProductDTO.setImage("https://iili.io/HSI63ua.webp");
		ProductDTO.setName("dy t-shrt");
		ProductDTO.setDescription("collared-neck cotton typography printed white color T-shirt ");

		Category category = new Category(1);
		ProductDTO.setCategory(category);

		List<Price> priceList = ProductDTO.getPriceList();

		Size size1 = new Size(1);
		Product product1 = new Product(productId);
		Price price1 = new Price();
		price1.setSize(size1);
		price1.setPrice(580.00d);
		price1.setProduct(product1);

		// if size is not available then update

		Size size2 = new Size(2);
		Product product2 = new Product(productId);
		Price price2 = new Price();
		price2.setSize(size2);
		price2.setPrice(113.00d);
		price2.setProduct(product2);

		priceList.add(price1);
		priceList.add(price2);

		ProductDTO.setPriceList(priceList);

		//

		ProductService productService = new ProductService();

		assertDoesNotThrow(() -> {
			productService.updateProduct(ProductDTO);
		});
	}

	@Test
	public void updateProductWithInvalidInput() {

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(null);

		});

		String expectedMessage = "Product cannot be null";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void updateProductNameWithNull() {

		ProductDTO ProductDTO = new ProductDTO();

		int productId = 3;
		ProductDTO.setId(productId);
		ProductDTO.setName(null);
		ProductDTO.setDescription("collared-neck cotton typography printed white color T-shirt ");

		Category category = new Category(1);
		ProductDTO.setCategory(category);

		List<Price> priceList = ProductDTO.getPriceList();

		Size size1 = new Size(1);
		Product product1 = new Product(productId);
		Price price1 = new Price();
		price1.setSize(size1);
		price1.setPrice(540.00d);
		price1.setProduct(product1);

		Size size2 = new Size(2);
		Product product2 = new Product(productId);
		Price price2 = new Price();
		price2.setSize(size2);
		price2.setPrice(1134.00d);
		price2.setProduct(product2);

		priceList.add(price1);
		priceList.add(price2);

		ProductDTO.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(ProductDTO);

		});

		String expectedMessage = "Product name cannot be empty";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductNameWithEmpty() {

		ProductDTO ProductDTO = new ProductDTO();

		int productId = 3;
		ProductDTO.setId(productId);
		ProductDTO.setName("");
		ProductDTO.setDescription("collared-neck cotton typography printed white color T-shirt ");

		Category category = new Category(1);
		ProductDTO.setCategory(category);

		List<Price> priceList = ProductDTO.getPriceList();

		Size size1 = new Size(1);
		Product product1 = new Product(productId);
		Price price1 = new Price();
		price1.setSize(size1);
		price1.setPrice(540.00d);
		price1.setProduct(product1);

		Size size2 = new Size(2);
		Product product2 = new Product(productId);
		Price price2 = new Price();
		price2.setSize(size2);
		price2.setPrice(1134.00d);
		price2.setProduct(product2);

		priceList.add(price1);
		priceList.add(price2);

		ProductDTO.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(ProductDTO);

		});

		String expectedMessage = "Product name cannot be empty";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	// pattern check

	@Test
	public void updateProductNameWithInvalidFormat() {

		ProductDTO ProductDTO = new ProductDTO();

		int productId = 3;
		ProductDTO.setId(productId);
		ProductDTO.setName("v-neck ?% neck T-shirt");
		ProductDTO.setDescription("v-neck typography printed navy blue color T-shirt ");

		Category category = new Category(1);
		ProductDTO.setCategory(category);

		List<Price> priceList = ProductDTO.getPriceList();

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

		ProductDTO.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(ProductDTO);

		});

		String expectedMessage = "Invalid product name";

		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void updateProductNameWithInvalidLength() {

		ProductDTO ProductDTO = new ProductDTO();

		int productId = 3;
		ProductDTO.setId(productId);
		ProductDTO.setName("v-");
		ProductDTO.setDescription("v-neck typography printed navy blue color T-shirt ");

		Category category = new Category(1);
		ProductDTO.setCategory(category);

		List<Price> priceList = ProductDTO.getPriceList();

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

		ProductDTO.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(ProductDTO);

		});

		String expectedMessage = "Product name should be between 3 and 50 characters.";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void updateProductDescriptionWithNull() {

		ProductDTO ProductDTO = new ProductDTO();

		int productId = 3;
		ProductDTO.setId(productId);
		ProductDTO.setName("white t-shirt");
		ProductDTO.setDescription(null);

		Category category = new Category(1);
		ProductDTO.setCategory(category);

		List<Price> priceList = ProductDTO.getPriceList();

		Size size1 = new Size(1);
		Product product1 = new Product(productId);
		Price price1 = new Price();
		price1.setSize(size1);
		price1.setPrice(540.00d);
		price1.setProduct(product1);

		Size size2 = new Size(2);
		Product product2 = new Product(productId);
		Price price2 = new Price();
		price2.setSize(size2);
		price2.setPrice(1134.00d);
		price2.setProduct(product2);

		priceList.add(price1);
		priceList.add(price2);

		ProductDTO.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(ProductDTO);

		});

		String expectedMessage = "Product description cannot be empty";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductDescriptionWithEmpty() {

		ProductDTO ProductDTO = new ProductDTO();

		int productId = 3;
		ProductDTO.setId(productId);
		ProductDTO.setName("white t-shirt");
		ProductDTO.setDescription("");

		Category category = new Category(1);
		ProductDTO.setCategory(category);

		List<Price> priceList = ProductDTO.getPriceList();

		Size size1 = new Size(1);
		Product product1 = new Product(productId);
		Price price1 = new Price();
		price1.setSize(size1);
		price1.setPrice(540.00d);
		price1.setProduct(product1);

		Size size2 = new Size(2);
		Product product2 = new Product(productId);
		Price price2 = new Price();
		price2.setSize(size2);
		price2.setPrice(1134.00d);
		price2.setProduct(product2);

		priceList.add(price1);
		priceList.add(price2);

		ProductDTO.setPriceList(priceList);
		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(ProductDTO);

		});

		String expectedMessage = "Product description cannot be empty";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductDescriptionWithInvalidLength() {

		ProductDTO ProductDTO = new ProductDTO();

		int productId = 3;
		ProductDTO.setId(productId);
		ProductDTO.setName("v-neck neck T-shirt");
		ProductDTO.setDescription("v-n");

		Category category = new Category(1);
		ProductDTO.setCategory(category);

		List<Price> priceList = ProductDTO.getPriceList();

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

		ProductDTO.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(ProductDTO);

		});

		String expectedMessage = "Product description should be between 10 and 1000 characters.";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void updateProductWithInvalidProductId() {

		ProductDTO ProductDTO = new ProductDTO();

		int invalidProductId = -1;
		ProductDTO.setId(invalidProductId);
		ProductDTO.setName("white t-shirt");
		ProductDTO.setDescription("collared-neck cotton typography printed white color T-shirt ");

		Category category = new Category(1);
		ProductDTO.setCategory(category);

		List<Price> priceList = ProductDTO.getPriceList();

		Size size1 = new Size(1);
		Product product1 = new Product(invalidProductId);
		Price price1 = new Price();
		price1.setSize(size1);
		price1.setPrice(540.00d);
		price1.setProduct(product1);

		Size size2 = new Size(2);
		Product product2 = new Product(invalidProductId);
		Price price2 = new Price();
		price2.setSize(size2);
		price2.setPrice(1134.00d);
		price2.setProduct(product2);

		priceList.add(price1);
		priceList.add(price2);

		ProductDTO.setPriceList(priceList);
		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(ProductDTO);

		});

		String expectedMessage = "Invalid product input";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductWithProductDoesNotExists() {

		ProductDTO ProductDTO = new ProductDTO();

		int invalidProductId = 110;
		ProductDTO.setId(invalidProductId);
		ProductDTO.setName("white t-shirt");
		ProductDTO.setDescription("collared-neck cotton typography printed white color T-shirt ");

		Category category = new Category(1);
		ProductDTO.setCategory(category);

		List<Price> priceList = ProductDTO.getPriceList();

		Size size1 = new Size(1);
		Product product1 = new Product(invalidProductId);
		Price price1 = new Price();
		price1.setSize(size1);
		price1.setPrice(540.00d);
		price1.setProduct(product1);

		Size size2 = new Size(2);
		Product product2 = new Product(invalidProductId);
		Price price2 = new Price();
		price2.setSize(size2);
		price2.setPrice(1134.00d);
		price2.setProduct(product2);

		priceList.add(price1);
		priceList.add(price2);

		ProductDTO.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(ProductDTO);

		});

		String expectedMessage = "Product with ID " + invalidProductId + " does not exist";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductWithInvalidCategory() {

		ProductDTO ProductDTO = new ProductDTO();

		int productId = 3;
		ProductDTO.setId(productId);
		ProductDTO.setName("white t-shirt");
		ProductDTO.setDescription("collared-neck cotton typography printed white color T-shirt ");

		int invalidCategoryId = -1;
		Category category = new Category(invalidCategoryId);
		ProductDTO.setCategory(category);

		List<Price> priceList = ProductDTO.getPriceList();

		Size size1 = new Size(1);
		Product product1 = new Product(productId);
		Price price1 = new Price();
		price1.setSize(size1);
		price1.setPrice(540.00d);
		price1.setProduct(product1);

		Size size2 = new Size(2);
		Product product2 = new Product(productId);
		Price price2 = new Price();
		price2.setSize(size2);
		price2.setPrice(1134.00d);
		price2.setProduct(product2);

		priceList.add(price1);
		priceList.add(price2);

		ProductDTO.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(ProductDTO);

		});

		String expectedMessage = "Invalid catgeory input";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductWithCategoryDoesNotExist() {

		ProductDTO ProductDTO = new ProductDTO();

		int productId = 3;
		ProductDTO.setId(productId);
		ProductDTO.setName("white t-shirt");
		ProductDTO.setDescription("collared-neck cotton typography printed white color T-shirt ");

		int invalidCategoryId = 80;
		Category category = new Category(invalidCategoryId);
		ProductDTO.setCategory(category);

		List<Price> priceList = ProductDTO.getPriceList();

		Size size1 = new Size(1);
		Product product1 = new Product(productId);
		Price price1 = new Price();
		price1.setSize(size1);
		price1.setPrice(540.00d);
		price1.setProduct(product1);

		Size size2 = new Size(2);
		Product product2 = new Product(productId);
		Price price2 = new Price();
		price2.setSize(size2);
		price2.setPrice(1134.00d);
		price2.setProduct(product2);

		priceList.add(price1);
		priceList.add(price2);

		ProductDTO.setPriceList(priceList);
		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(ProductDTO);

		});

		String expectedMessage = "Category with ID " + invalidCategoryId + " does not exist";
		;
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductWithInvalidPriceNull() {
		ProductDTO ProductDTO = new ProductDTO();

		int productId = 3;
		ProductDTO.setId(productId);
		ProductDTO.setName("white t-shirt");
		ProductDTO.setDescription("collared-neck cotton typography printed white color T-shirt ");

		Category category = new Category(1);
		ProductDTO.setCategory(category);

		List<Price> priceList = ProductDTO.getPriceList();

		Size size1 = new Size(1);
		Product product1 = new Product(productId);
		Price price1 = new Price();
		price1.setSize(size1);
		price1.setPrice(540.00d);
		price1.setProduct(product1);

		Size size2 = new Size(2);
		Product product2 = new Product(productId);
		Price price2 = new Price();
		price2.setSize(size2);
		price2.setPrice(1134.00d);
		price2.setProduct(product2);

		priceList.add(null);
		priceList.add(price2);

		ProductDTO.setPriceList(priceList);
		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(ProductDTO);

		});

		String expectedMessage = "price cannot be null";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductWithInvalidPrice() {

		ProductDTO ProductDTO = new ProductDTO();

		int productId = 3;
		ProductDTO.setId(productId);
		ProductDTO.setName("white t-shirt");
		ProductDTO.setDescription("collared-neck cotton typography printed white color T-shirt ");

		Category category = new Category(1);
		ProductDTO.setCategory(category);

		List<Price> priceList = ProductDTO.getPriceList();

		Size size1 = new Size(1);
		Product product1 = new Product(productId);
		Price price1 = new Price();
		price1.setSize(size1);
		price1.setPrice(-1);
		price1.setProduct(product1);

		Size size2 = new Size(2);
		Product product2 = new Product(productId);
		Price price2 = new Price();
		price2.setSize(size2);
		price2.setPrice(1134.00d);
		price2.setProduct(product2);

		priceList.add(price1);
		priceList.add(price2);

		ProductDTO.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(ProductDTO);

		});

		String expectedMessage = "Invalid price input";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductWithInvalidPriceLimit() {

		ProductDTO ProductDTO = new ProductDTO();

		int productId = 3;
		ProductDTO.setId(productId);
		ProductDTO.setName("white t-shirt");
		ProductDTO.setDescription("collared-neck cotton typography printed white color T-shirt ");

		Category category = new Category(1);
		ProductDTO.setCategory(category);

		List<Price> priceList = ProductDTO.getPriceList();

		Size size1 = new Size(1);
		Product product1 = new Product(productId);
		Price price1 = new Price();
		price1.setSize(size1);
		price1.setPrice(1000000000.00d);
		price1.setProduct(product1);

		Size size2 = new Size(2);
		Product product2 = new Product(productId);
		Price price2 = new Price();
		price2.setSize(size2);
		price2.setPrice(1134.00d);
		price2.setProduct(product2);

		priceList.add(price1);
		priceList.add(price2);

		ProductDTO.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(ProductDTO);

		});

		String expectedMessage = "Product price must not exceed 1,000,000 rupees.";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductWithInvalidSizeInput() {
		ProductDTO ProductDTO = new ProductDTO();

		int productId = 3;
		ProductDTO.setId(productId);
		ProductDTO.setName("white t-shirt");
		ProductDTO.setDescription("collared-neck cotton typography printed white color T-shirt ");

		Category category = new Category(1);
		ProductDTO.setCategory(category);

		List<Price> priceList = ProductDTO.getPriceList();

		int invalidSizeId = -1;
		Size size1 = new Size(invalidSizeId);
		Product product1 = new Product(productId);
		Price price1 = new Price();
		price1.setSize(size1);
		price1.setPrice(540.00d);
		price1.setProduct(product1);

		Size size2 = new Size(2);
		Product product2 = new Product(productId);
		Price price2 = new Price();
		price2.setSize(size2);
		price2.setPrice(1134.00d);
		price2.setProduct(product2);

		priceList.add(price1);
		priceList.add(price2);

		ProductDTO.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(ProductDTO);

		});

		String expectedMessage = "Invalid size input";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductWithSizeDoesNotExist() {

		ProductDTO ProductDTO = new ProductDTO();

		int productId = 3;
		ProductDTO.setId(productId);
		ProductDTO.setName("white t-shirt");
		ProductDTO.setDescription("collared-neck cotton typography printed white color T-shirt ");

		Category category = new Category(1);
		ProductDTO.setCategory(category);

		List<Price> priceList = ProductDTO.getPriceList();

		int invalidSizeId = 70;
		Size size1 = new Size(invalidSizeId);
		Product product1 = new Product(productId);
		Price price1 = new Price();
		price1.setSize(size1);
		price1.setPrice(540.00d);
		price1.setProduct(product1);

		Size size2 = new Size(2);
		Product product2 = new Product(productId);
		Price price2 = new Price();
		price2.setSize(size2);
		price2.setPrice(1134.00d);
		price2.setProduct(product2);

		priceList.add(price1);
		priceList.add(price2);

		ProductDTO.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(ProductDTO);

		});

		String expectedMessage = "Size with ID " + invalidSizeId + " does not exist";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

}
