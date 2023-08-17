package in.fssa.myfashionstudioapp.Product;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.dto.ProductDTO;
import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.Price;
import in.fssa.myfashionstudioapp.model.Size;
import in.fssa.myfashionstudioapp.service.ProductService;

public class TestCreateProduct {

	@Test
	public void createProductAndPricesWithValidInput() {

		// create the dto instance

		ProductDTO productDto = new ProductDTO();

		List<Price> priceList = productDto.getPriceList();

		//
		Price price1 = new Price();

//		Size size1 = new Size();
//		size1.setId(1);

		price1.getSize().setId(1);
		price1.setPrice(600.00d);
		//

		//
		Price price2 = new Price();

		price2.getSize().setId(2);
		price2.setPrice(290.00d);
		//

		priceList.add(price1);
		priceList.add(price2);

		//

		productDto.setPriceList(priceList);
		productDto.getCategory().setId(1);
		productDto.setName("v-neck neck T-shirt");
		productDto.setDescription("v-neck typography printed navy blue color T-shirt ");

		ProductService productService = new ProductService();

		assertDoesNotThrow(() -> {
			productService.createProductWithPrices(productDto);
		});
	}

	@Test
	public void createProductAndPricesWithInvalidInput() {

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProductWithPrices(null);

		});

		String ExpectedMessage = "Validation error: Product cannot be Null";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));
	}

	@Test
	public void createProductAndPricesNameWithNull() {

		// create the dto instance

		ProductDTO productDto = new ProductDTO();

		List<Price> priceList = productDto.getPriceList();

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

		productDto.setPriceList(priceList);
		productDto.getCategory().setId(1);
		productDto.setName(null);
		productDto.setDescription("v-neck typography printed navy blue color T-shirt ");

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProductWithPrices(productDto);

		});

		String ExpectedMessage = "Validation error: Product name cannot be null or empty";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));

	}

	@Test
	public void createProductAndPricesNameWithEmpty() {

		// create the dto instance

		ProductDTO productDto = new ProductDTO();

		List<Price> priceList = productDto.getPriceList();

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

		productDto.setPriceList(priceList);
		productDto.getCategory().setId(1);
		productDto.setName("");
		productDto.setDescription("v-neck typography printed navy blue color T-shirt ");

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProductWithPrices(productDto);

		});

		String ExpectedMessage = "Validation error: Product name cannot be null or empty";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));

	}

	@Test
	public void createProductAndPricesDescriptionWithNull() {

		// create the dto instance

		ProductDTO productDto = new ProductDTO();

		List<Price> priceList = productDto.getPriceList();

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

		productDto.setPriceList(priceList);
		productDto.getCategory().setId(1);
		productDto.setName("v-neck neck T-shirt");
		productDto.setDescription(null);

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProductWithPrices(productDto);

		});

		String ExpectedMessage = "Validation error: Product description cannot be null or empty";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));

	}

	@Test
	public void createProductAndPricesDescriptionWithEmpty() {

		// create the dto instance

		ProductDTO productDto = new ProductDTO();

		List<Price> priceList = productDto.getPriceList();

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

		productDto.setPriceList(priceList);
		productDto.getCategory().setId(1);
		productDto.setName("v-neck neck T-shirt");
		productDto.setDescription("");

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProductWithPrices(productDto);

		});

		String ExpectedMessage = "Validation error: Product description cannot be null or empty";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));

	}

	@Test
	public void createProductAndPricesCategoryDoesNotExist() {

		// create the dto instance

		ProductDTO productDto = new ProductDTO();

		List<Price> priceList = productDto.getPriceList();

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

		int invalidCategoryId = 15;

		productDto.setPriceList(priceList);
		productDto.getCategory().setId(invalidCategoryId);
		productDto.setName("v-neck neck T-shirt");
		productDto.setDescription("v-neck typography printed navy blue color T-shirt");

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProductWithPrices(productDto);

		});

		String ExpectedMessage = "Validation error: Category with ID " + invalidCategoryId + " does not exist";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));

	}

	@Test
	public void createProductAndPricesWithInvalidPricebelow0() {

		// create the dto instance

		ProductDTO productDto = new ProductDTO();

		List<Price> priceList = productDto.getPriceList();

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

		int invalidCategoryId = 1;

		productDto.setPriceList(priceList);
		productDto.getCategory().setId(invalidCategoryId);
		productDto.setName("v-neck neck T-shirt");
		productDto.setDescription("v-neck typography printed navy blue color T-shirt");

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProductWithPrices(productDto);

		});

		String ExpectedMessage = "Validation error: Invalid price input";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));

	}

	@Test
	public void createProductAndPricesWithInvalidPriceAbove10000() {

		// create the dto instance

		ProductDTO productDto = new ProductDTO();

		List<Price> priceList = productDto.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(1);

		price1.setSize(size1);
		price1.setPrice(100000);
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

		int invalidCategoryId = 1;

		productDto.setPriceList(priceList);
		productDto.getCategory().setId(invalidCategoryId);
		productDto.setName("v-neck neck T-shirt");
		productDto.setDescription("v-neck typography printed navy blue color T-shirt");

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProductWithPrices(productDto);

		});

		String ExpectedMessage = "Validation error: Invalid price input";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));

	}

	@Test
	public void createProductAndPricesWithInvalidSizeInput() {

		// create the dto instance

		ProductDTO productDto = new ProductDTO();

		List<Price> priceList = productDto.getPriceList();

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

		int invalidCategoryId = 1;

		productDto.setPriceList(priceList);
		productDto.getCategory().setId(invalidCategoryId);
		productDto.setName("v-neck neck T-shirt");
		productDto.setDescription("v-neck typography printed navy blue color T-shirt");

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProductWithPrices(productDto);

		});

		String ExpectedMessage = "Validation error: Invalid size input";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));

	}

	@Test
	public void createProductAndPricesSizeDoesNotExist() {

		// create the dto instance

		ProductDTO productDto = new ProductDTO();

		List<Price> priceList = productDto.getPriceList();

		//
		Price price1 = new Price();

		int invalidSizeInput = 19;
		Size size1 = new Size();
		size1.setId(invalidSizeInput);

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

		int invalidCategoryId = 1;

		productDto.setPriceList(priceList);
		productDto.getCategory().setId(invalidCategoryId);
		productDto.setName("v-neck neck T-shirt");
		productDto.setDescription("v-neck typography printed navy blue color T-shirt");

		ProductService productService = new ProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			productService.createProductWithPrices(productDto);

		});

		String ExpectedMessage = "Validation error: Size with ID " + invalidSizeInput + " does not exist";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);

		assertTrue(ExpectedMessage.equals(actualMessage));

	}

}
