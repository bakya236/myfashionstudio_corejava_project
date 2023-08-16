package in.fssa.myfashionstudioapp.Product;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.service.ProductService;

public class TestGetAllProductswithFirstSizeAndPrice {

	@Test
	public void getAllProductswithFirstSizeAndPrice() {

		ProductService productService = new ProductService();

		assertDoesNotThrow(() -> {
			productService.findAllProducts();
		});
	}

}
