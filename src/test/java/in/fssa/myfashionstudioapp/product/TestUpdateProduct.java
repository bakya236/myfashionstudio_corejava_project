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

		ProductDTO productDto = new ProductDTO();

		int productId = 28;
		productDto.setId(productId);
		productDto.setName("dy t-shrt");
		productDto.setDescription("collared-neck cotton typography printed white color T-shirt ");

		Category category = new Category(1);
		productDto.setCategory(category);

		List<Price> priceList = productDto.getPriceList();

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

		productDto.setPriceList(priceList);

		//

		ProductService productService = new ProductService();

		assertDoesNotThrow(() -> {
			productService.updateProduct(productDto);
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

		ProductDTO productDto = new ProductDTO();

		int productId = 3;
		productDto.setId(productId);
		productDto.setName(null);
		productDto.setDescription("collared-neck cotton typography printed white color T-shirt ");

		Category category = new Category(1);
		productDto.setCategory(category);

		List<Price> priceList = productDto.getPriceList();

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

		productDto.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(productDto);

		});

		String expectedMessage = "Product name cannot be empty";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductNameWithEmpty() {

		ProductDTO productDto = new ProductDTO();

		int productId = 3;
		productDto.setId(productId);
		productDto.setName("");
		productDto.setDescription("collared-neck cotton typography printed white color T-shirt ");

		Category category = new Category(1);
		productDto.setCategory(category);

		List<Price> priceList = productDto.getPriceList();

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

		productDto.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(productDto);

		});

		String expectedMessage = "Product name cannot be empty";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	// pattern check

	@Test
	public void updateProductNameWithInvalidFormat() {

		ProductDTO productDTO = new ProductDTO();

		int productId = 3;
		productDTO.setId(productId);
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

			productService.updateProduct(productDTO);

		});

		String expectedMessage = "Invalid product name. product name conatins invalid special characters like @ , &  ,* , $ , # ,\"";

		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void updateProductNameWithInvalidLength() {

		ProductDTO productDTO = new ProductDTO();

		int productId = 3;
		productDTO.setId(productId);
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

			productService.updateProduct(productDTO);

		});

		String expectedMessage = "Product name should be between 3 and 50 characters.";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void updateProductDescriptionWithNull() {

		ProductDTO productDto = new ProductDTO();

		int productId = 3;
		productDto.setId(productId);
		productDto.setName("white t-shirt");
		productDto.setDescription(null);

		Category category = new Category(1);
		productDto.setCategory(category);

		List<Price> priceList = productDto.getPriceList();

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

		productDto.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(productDto);

		});

		String expectedMessage = "Product description cannot be empty";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductDescriptionWithEmpty() {

		ProductDTO productDto = new ProductDTO();

		int productId = 3;
		productDto.setId(productId);
		productDto.setName("white t-shirt");
		productDto.setDescription("");

		Category category = new Category(1);
		productDto.setCategory(category);

		List<Price> priceList = productDto.getPriceList();

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

		productDto.setPriceList(priceList);
		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(productDto);

		});

		String expectedMessage = "Product description cannot be empty";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductDescriptionWithInvalidFormat() {

		ProductDTO productDTO = new ProductDTO();

		productDTO.setName("v-neck neck T-shirt");
		productDTO.setDescription("v-neck ??? + (typography printed navy blue color T-shirt || s)");

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

			productService.updateProduct(productDTO);

		});

		String expectedMessage = "Invalid product description. Description should only contain letters, digits, - _ . , () & ! ? \\\" '\"";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void updateProductDescriptionWithInvalidLength() {

		ProductDTO productDTO = new ProductDTO();

		int productId = 3;
		productDTO.setId(productId);
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

			productService.updateProduct(productDTO);

		});

		String expectedMessage = "Product description should be between 10 and 1000 characters.";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void updateProductWithInvalidProductId() {

		ProductDTO productDto = new ProductDTO();

		int invalidProductId = -1;
		productDto.setId(invalidProductId);
		productDto.setName("white t-shirt");
		productDto.setDescription("collared-neck cotton typography printed white color T-shirt ");

		Category category = new Category(1);
		productDto.setCategory(category);

		List<Price> priceList = productDto.getPriceList();

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

		productDto.setPriceList(priceList);
		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(productDto);

		});

		String expectedMessage = "Invalid product input";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductWithProductDoesNotExists() {

		ProductDTO productDto = new ProductDTO();

		int invalidProductId = 110;
		productDto.setId(invalidProductId);
		productDto.setName("white t-shirt");
		productDto.setDescription("collared-neck cotton typography printed white color T-shirt ");

		Category category = new Category(1);
		productDto.setCategory(category);

		List<Price> priceList = productDto.getPriceList();

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

		productDto.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(productDto);

		});

		String expectedMessage = "Product with ID " + invalidProductId + " does not exist";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductWithInvalidCategory() {

		ProductDTO productDto = new ProductDTO();

		int productId = 3;
		productDto.setId(productId);
		productDto.setName("white t-shirt");
		productDto.setDescription("collared-neck cotton typography printed white color T-shirt ");

		int invalidCategoryId = -1;
		Category category = new Category(invalidCategoryId);
		productDto.setCategory(category);

		List<Price> priceList = productDto.getPriceList();

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

		productDto.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(productDto);

		});

		String expectedMessage = "Invalid catgeory input";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductWithCategoryDoesNotExist() {

		ProductDTO productDto = new ProductDTO();

		int productId = 3;
		productDto.setId(productId);
		productDto.setName("white t-shirt");
		productDto.setDescription("collared-neck cotton typography printed white color T-shirt ");

		int invalidCategoryId = 80;
		Category category = new Category(invalidCategoryId);
		productDto.setCategory(category);

		List<Price> priceList = productDto.getPriceList();

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

		productDto.setPriceList(priceList);
		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(productDto);

		});

		String expectedMessage = "Category with ID " + invalidCategoryId + " does not exist";
		;
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductWithInvalidPriceNull() {
		ProductDTO productDto = new ProductDTO();

		int productId = 3;
		productDto.setId(productId);
		productDto.setName("white t-shirt");
		productDto.setDescription("collared-neck cotton typography printed white color T-shirt ");

		Category category = new Category(1);
		productDto.setCategory(category);

		List<Price> priceList = productDto.getPriceList();

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

		productDto.setPriceList(priceList);
		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(productDto);

		});

		String expectedMessage = "price cannot be null";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductWithInvalidPrice() {

		ProductDTO productDto = new ProductDTO();

		int productId = 3;
		productDto.setId(productId);
		productDto.setName("white t-shirt");
		productDto.setDescription("collared-neck cotton typography printed white color T-shirt ");

		Category category = new Category(1);
		productDto.setCategory(category);

		List<Price> priceList = productDto.getPriceList();

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

		productDto.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(productDto);

		});

		String expectedMessage = "Invalid price input";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductWithInvalidPriceLimit() {

		ProductDTO productDto = new ProductDTO();

		int productId = 3;
		productDto.setId(productId);
		productDto.setName("white t-shirt");
		productDto.setDescription("collared-neck cotton typography printed white color T-shirt ");

		Category category = new Category(1);
		productDto.setCategory(category);

		List<Price> priceList = productDto.getPriceList();

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

		productDto.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(productDto);

		});

		String expectedMessage = "Product price must not exceed 1,000,000 rupees.";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductWithInvalidSizeInput() {
		ProductDTO productDto = new ProductDTO();

		int productId = 3;
		productDto.setId(productId);
		productDto.setName("white t-shirt");
		productDto.setDescription("collared-neck cotton typography printed white color T-shirt ");

		Category category = new Category(1);
		productDto.setCategory(category);

		List<Price> priceList = productDto.getPriceList();

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

		productDto.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(productDto);

		});

		String expectedMessage = "Invalid size input";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductWithSizeDoesNotExist() {

		ProductDTO productDto = new ProductDTO();

		int productId = 3;
		productDto.setId(productId);
		productDto.setName("white t-shirt");
		productDto.setDescription("collared-neck cotton typography printed white color T-shirt ");

		Category category = new Category(1);
		productDto.setCategory(category);

		List<Price> priceList = productDto.getPriceList();

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

		productDto.setPriceList(priceList);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProduct(productDto);

		});

		String expectedMessage = "Size with ID " + invalidSizeId + " does not exist";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

}
