package in.fssa.myfashionstudioapp.Product;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.service.ProductService;

public class TestFindAllProductsByCategoryId {

	@Test
	public void findAllProductsByCategoryId() {

		ProductService productService = new ProductService();

		assertDoesNotThrow(() -> {
			productService.findAllProductsByCategoryId(1);
		});
	}

}
