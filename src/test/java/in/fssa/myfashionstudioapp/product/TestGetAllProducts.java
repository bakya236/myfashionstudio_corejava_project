package in.fssa.myfashionstudioapp.product;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.dto.ProductDTO;
import in.fssa.myfashionstudioapp.service.ProductService;

public class TestGetAllProducts {

	/**
	 * Tests the retrieval of all products from the ProductService and prints their
	 * details.
	 *
	 * This test method initializes a ProductService, retrieves a list of all
	 * products, and then iterates through the list, printing the details of each
	 * product to the console. It uses the assertDoesNotThrow to handle exceptions
	 * that may occur during the test.
	 */

	@Test
	public void testGetAllProducts() {

		ProductService productService = new ProductService();

		assertDoesNotThrow(() -> {
			List<ProductDTO> productList = productService.getAllProducts();

			for (ProductDTO productPrice : productList) {

				System.out.println(productPrice);
			}

		});

	}

}
