package in.fssa.myfashionstudioapp.product;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.service.ProductService;

public class TestFindProductDetailsByProductId {

	@Test
	public void findProductDetailsByProductId() {
		ProductService productService = new ProductService();

		assertDoesNotThrow(() -> {
			productService.findProductDetailsByProductId(5);
		});
	}

}
