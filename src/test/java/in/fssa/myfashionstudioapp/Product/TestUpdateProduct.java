package in.fssa.myfashionstudioapp.Product;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.dto.ProductDTO;
import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.Category;
import in.fssa.myfashionstudioapp.model.Price;
import in.fssa.myfashionstudioapp.model.Product;
import in.fssa.myfashionstudioapp.model.Size;
import in.fssa.myfashionstudioapp.service.ProductService;

public class TestUpdateProduct {

//	@Test
//	public void UpdateProductWithValidInput() {
//		ProductDTO productDto = new ProductDTO();
//
//		productDto.setName("black printed t-shirt");
//		productDto.setDescription("regular black printed rounded neck t-shirt");
//		productDto.getCategory().setId(2);
//
//		List<Price> priceList = productDto.getPriceList();
//
//		// product id
//		int productId = 1;
//		//
//		Price price1 = new Price();// {}
//		price1.getProduct().setId(productId);
//		price1.setPrice(730.00d);
//
//		Size size = new Size();
//		size.setId(2);
//
//		price1.setSize(size);
//
////
////		Price price2 = new Price();// {}
////		price2.getProduct().setId(productId);
////		price2.setPrice(200.00d);
////
////		Size size2 = new Size();
////		size2.setId(1);
////
////		price2.setSize(size2);
//
//		priceList.add(price1);
////		priceList.add(price2);
//
//		productDto.setPriceList(priceList);
//		productDto.setId(productId);
//
//		ProductService productService = new ProductService();
//
//		assertDoesNotThrow(() -> {
//			productService.updateProductDetailsAndPrices(productDto);
//		});
//
//	}

	@Test
	public void updateProductWithValidInput() {

		ProductDTO productDto = new ProductDTO();

		int productId = 3;

		List<Price> priceList = productDto.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(1);

		price1.setSize(size1);
		price1.setPrice(700.00d);

		Product product1 = new Product();
		product1.setId(productId);
		price1.setProduct(product1);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(590.00d);

		Product product2 = new Product();
		product2.setId(productId);
		price2.setProduct(product2);
		//

		priceList.add(price1);

		//

		productDto.setPriceList(priceList);

		Category category = new Category();
		category.setId(1);

		productDto.setCategory(category);
		productDto.setName("Black t-shirt");
		productDto.setDescription("rounded-neck typography printed navy black color T-shirt ");
		productDto.setId(productId);

		ProductService productService = new ProductService();

		assertDoesNotThrow(() -> {
			productService.updateProductDetailsAndPrices(productDto);
		});
	}

	@Test
	public void updateProductWithInvalidInput() {

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProductDetailsAndPrices(null);

		});

		String ExpectedMessage = "Validation error: Product cannot be Null";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));
	}

	@Test
	public void updateProductNameWithNull() {

		ProductDTO productDto = new ProductDTO();

		int productId = 3;

		List<Price> priceList = productDto.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(1);

		price1.setSize(size1);
		price1.setPrice(700.00d);

		Product product1 = new Product();
		product1.setId(productId);
		price1.setProduct(product1);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(590.00d);

		Product product2 = new Product();
		product2.setId(productId);
		price2.setProduct(product2);
		//

		priceList.add(price1);

		//

		productDto.setPriceList(priceList);

		Category category = new Category();
		category.setId(1);

		productDto.setCategory(category);
		productDto.setName(null);
		productDto.setDescription("rounded-neck typography printed navy black color T-shirt ");
		productDto.setId(productId);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProductDetailsAndPrices(productDto);

		});

		String ExpectedMessage = "Validation error: Product name cannot be null or empty";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductNameWithEmpty() {

		ProductDTO productDto = new ProductDTO();

		int productId = 3;

		List<Price> priceList = productDto.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(1);

		price1.setSize(size1);
		price1.setPrice(700.00d);

		Product product1 = new Product();
		product1.setId(productId);
		price1.setProduct(product1);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(590.00d);

		Product product2 = new Product();
		product2.setId(productId);
		price2.setProduct(product2);
		//

		priceList.add(price1);

		//

		productDto.setPriceList(priceList);

		Category category = new Category();
		category.setId(1);

		productDto.setCategory(category);
		productDto.setName("");
		productDto.setDescription("rounded-neck typography printed navy black color T-shirt ");
		productDto.setId(productId);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProductDetailsAndPrices(productDto);

		});

		String ExpectedMessage = "Validation error: Product name cannot be null or empty";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductDescriptionWithNull() {

		ProductDTO productDto = new ProductDTO();

		int productId = 3;

		List<Price> priceList = productDto.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(1);

		price1.setSize(size1);
		price1.setPrice(700.00d);

		Product product1 = new Product();
		product1.setId(productId);
		price1.setProduct(product1);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(590.00d);

		Product product2 = new Product();
		product2.setId(productId);
		price2.setProduct(product2);
		//

		priceList.add(price1);

		//

		productDto.setPriceList(priceList);

		Category category = new Category();
		category.setId(1);

		productDto.setCategory(category);
		productDto.setName("Black t-shirt");
		productDto.setDescription(null);
		productDto.setId(productId);
		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProductDetailsAndPrices(productDto);

		});

		String ExpectedMessage = "Validation error: Product description cannot be null or empty";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductDescriptionWithEmpty() {

		ProductDTO productDto = new ProductDTO();

		int productId = 3;

		List<Price> priceList = productDto.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(1);

		price1.setSize(size1);
		price1.setPrice(700.00d);

		Product product1 = new Product();
		product1.setId(productId);
		price1.setProduct(product1);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(590.00d);

		Product product2 = new Product();
		product2.setId(productId);
		price2.setProduct(product2);
		//

		priceList.add(price1);

		//

		productDto.setPriceList(priceList);

		Category category = new Category();
		category.setId(1);

		productDto.setCategory(category);
		productDto.setName("Black t-shirt");
		productDto.setDescription("");
		productDto.setId(productId);
		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProductDetailsAndPrices(productDto);

		});

		String ExpectedMessage = "Validation error: Product description cannot be null or empty";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductWithInvalidproductId() {

		ProductDTO productDto = new ProductDTO();

		int productId = -1;

		List<Price> priceList = productDto.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(1);

		price1.setSize(size1);
		price1.setPrice(700.00d);

		Product product1 = new Product();
		product1.setId(productId);
		price1.setProduct(product1);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(590.00d);

		Product product2 = new Product();
		product2.setId(productId);
		price2.setProduct(product2);
		//

		priceList.add(price1);

		//

		productDto.setPriceList(priceList);

		Category category = new Category();
		category.setId(1);

		productDto.setCategory(category);
		productDto.setName("Black t-shirt");
		productDto.setDescription("rounded-neck typography printed navy black color T-shirt ");
		productDto.setId(productId);
		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProductDetailsAndPrices(productDto);

		});

		String ExpectedMessage = "Validation error: Invalid product input";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductWithProductDoesNotExists() {

		ProductDTO productDto = new ProductDTO();

		int productId = 110;

		List<Price> priceList = productDto.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(1);

		price1.setSize(size1);
		price1.setPrice(700.00d);

		Product product1 = new Product();
		product1.setId(productId);
		price1.setProduct(product1);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(590.00d);

		Product product2 = new Product();
		product2.setId(productId);
		price2.setProduct(product2);
		//

		priceList.add(price1);

		//

		productDto.setPriceList(priceList);

		Category category = new Category();
		category.setId(1);

		productDto.setCategory(category);
		productDto.setName("Black t-shirt");
		productDto.setDescription("rounded-neck typography printed navy black color T-shirt ");
		productDto.setId(productId);
		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProductDetailsAndPrices(productDto);

		});

		String ExpectedMessage = "Validation error: Product Id with ID " + productId + " does not exist";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductWithInvalidCategory() {

		ProductDTO productDto = new ProductDTO();

		int productId = 1;

		List<Price> priceList = productDto.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(1);

		price1.setSize(size1);
		price1.setPrice(700.00d);

		Product product1 = new Product();
		product1.setId(productId);
		price1.setProduct(product1);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(590.00d);

		Product product2 = new Product();
		product2.setId(productId);
		price2.setProduct(product2);
		//

		priceList.add(price1);
		priceList.add(price2);

		//

		productDto.setPriceList(priceList);

		int categoryId = -1; //
		Category category = new Category();
		category.setId(categoryId);

		productDto.setCategory(category);
		productDto.setName("Black t-shirt");
		productDto.setDescription("rounded-neck typography printed navy black color T-shirt ");
		productDto.setId(productId);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProductDetailsAndPrices(productDto);

		});

		String ExpectedMessage = "Validation error: Invalid catgeory input";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductCategoryDoesNotExist() {

		ProductDTO productDto = new ProductDTO();

		int productId = 1;

		List<Price> priceList = productDto.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(1);

		price1.setSize(size1);
		price1.setPrice(700.00d);

		Product product1 = new Product();
		product1.setId(productId);
		price1.setProduct(product1);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(590.00d);

		Product product2 = new Product();
		product2.setId(productId);
		price2.setProduct(product2);
		//

		priceList.add(price1);
		priceList.add(price2);

		//

		productDto.setPriceList(priceList);

		int categoryId = 79; //
		Category category = new Category();
		category.setId(categoryId);

		productDto.setCategory(category);
		productDto.setName("Black t-shirt");
		productDto.setDescription("rounded-neck typography printed navy black color T-shirt ");
		productDto.setId(productId);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProductDetailsAndPrices(productDto);

		});

		String ExpectedMessage = "Validation error: Category with ID " + categoryId + " does not exist";
		;
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));

	}

	// testing the gender_id forign key

	@Test
	public void updateProductwithInvalidPriceNull() {
		ProductDTO productDto = new ProductDTO();

		int productId = 3;

		List<Price> priceList = productDto.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(1);

		price1.setSize(size1);
		price1.setPrice(700.00d);

		Product product1 = new Product();
		product1.setId(productId);
		price1.setProduct(product1);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(590.00d);

		Product product2 = new Product();
		product2.setId(productId);
		price2.setProduct(product2);
		//

		priceList.add(null); //
		priceList.add(price2);

		//

		productDto.setPriceList(priceList);

		Category category = new Category();
		category.setId(1);

		productDto.setCategory(category);
		productDto.setName("Black t-shirt");
		productDto.setDescription("rounded-neck typography printed navy black color T-shirt ");
		productDto.setId(productId);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProductDetailsAndPrices(productDto);

		});

		String ExpectedMessage = "Validation error: price cannot be null";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductWithInvalidPricebelow100() {

		ProductDTO productDto = new ProductDTO();

		int productId = 3;

		List<Price> priceList = productDto.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(1);

		price1.setSize(size1);
		price1.setPrice(0.000d);

		Product product1 = new Product();
		product1.setId(productId);
		price1.setProduct(product1);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(590.00d);

		Product product2 = new Product();
		product2.setId(productId);
		price2.setProduct(product2);
		//

		priceList.add(price1);

		//

		productDto.setPriceList(priceList);

		Category category = new Category();
		category.setId(1);

		productDto.setCategory(category);
		productDto.setName("Black t-shirt");
		productDto.setDescription("rounded-neck typography printed navy black color T-shirt ");
		productDto.setId(productId);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProductDetailsAndPrices(productDto);

		});

		String ExpectedMessage = "Validation error: Invalid price input , price must be between 100 to 10000";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductWithInvalidPriceAbove10000() {

		ProductDTO productDto = new ProductDTO();

		int productId = 3;

		List<Price> priceList = productDto.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(1);

		price1.setSize(size1);
		price1.setPrice(10000000.000d);

		Product product1 = new Product();
		product1.setId(productId);
		price1.setProduct(product1);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(590.00d);

		Product product2 = new Product();
		product2.setId(productId);
		price2.setProduct(product2);
		//

		priceList.add(price1);

		//

		productDto.setPriceList(priceList);

		Category category = new Category();
		category.setId(1);

		productDto.setCategory(category);
		productDto.setName("Black t-shirt");
		productDto.setDescription("rounded-neck typography printed navy black color T-shirt ");
		productDto.setId(productId);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProductDetailsAndPrices(productDto);

		});

		String ExpectedMessage = "Validation error: Invalid price input , price must be between 100 to 10000";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductWithInvalidSizeInput() {
		ProductDTO productDto = new ProductDTO();

		int productId = 3;

		List<Price> priceList = productDto.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(-1);

		price1.setSize(size1);
		price1.setPrice(700.00d);

		Product product1 = new Product();
		product1.setId(productId);
		price1.setProduct(product1);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(590.00d);

		Product product2 = new Product();
		product2.setId(productId);
		price2.setProduct(product2);
		//

		priceList.add(price1);
		priceList.add(price2);

		//

		productDto.setPriceList(priceList);

		Category category = new Category();
		category.setId(1);

		productDto.setCategory(category);
		productDto.setName("Black t-shirt");
		productDto.setDescription("rounded-neck typography printed navy black color T-shirt ");
		productDto.setId(productId);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProductDetailsAndPrices(productDto);

		});

		String ExpectedMessage = "Validation error: Invalid size input";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));

	}

	@Test
	public void updateProductSizeDoesNotExist() {

		ProductDTO productDto = new ProductDTO();

		int productId = 3;

		List<Price> priceList = productDto.getPriceList();

		int SizeInput = 100;
		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(SizeInput);

		price1.setSize(size1);
		price1.setPrice(700.00d);

		Product product1 = new Product();
		product1.setId(productId);
		price1.setProduct(product1);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(590.00d);

		Product product2 = new Product();
		product2.setId(productId);
		price2.setProduct(product2);
		//

		priceList.add(price1);
		priceList.add(price2);

		//

		productDto.setPriceList(priceList);

		Category category = new Category();
		category.setId(1);

		productDto.setCategory(category);
		productDto.setName("Black t-shirt");
		productDto.setDescription("rounded-neck typography printed navy black color T-shirt ");
		productDto.setId(productId);
		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.updateProductDetailsAndPrices(productDto);

		});

		String ExpectedMessage = "Validation error: Size with ID " + SizeInput + " does not exist";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));

	}

}
