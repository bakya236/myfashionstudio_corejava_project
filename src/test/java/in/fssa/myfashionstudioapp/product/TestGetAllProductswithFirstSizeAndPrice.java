package in.fssa.myfashionstudioapp.product;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.dto.ProductDTO;
import in.fssa.myfashionstudioapp.service.ProductService;

public class TestGetAllProductswithFirstSizeAndPrice {

	@Test
	public void getAllProductswithFirstSizeAndPrice() {

		ProductService productService = new ProductService();

		assertDoesNotThrow(() -> {
			List<ProductDTO> productList = productService.getAllProducts();

			// to sysout the products
			for (ProductDTO productPrice : productList) {

				System.out.println(productPrice);
			}
		});
	}

}
