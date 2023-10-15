package in.fssa.myfashionstudioapp.product;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.dto.ProductDTO;
import in.fssa.myfashionstudioapp.service.ProductService;

public class TestFindProductDetailsByProductId {

	/**
	 * Tests the retrieval of product details by a specific product ID and prints
	 * the result.
	 *
	 * This test method initializes a ProductService, retrieves product details for
	 * a specific product identified by its unique ID, and prints the resulting
	 * ProductDTO object to the console. It uses the assertDoesNotThrow to handle
	 * exceptions that may occur during the test.
	 */

	@Test
	public void findProductDetailsByProductId() {
		ProductService productService = new ProductService();

		assertDoesNotThrow(() -> {
			ProductDTO ProductDTO = productService.findProductDetailsByProductId(1);
			System.out.print(ProductDTO);
		});
	}

}
