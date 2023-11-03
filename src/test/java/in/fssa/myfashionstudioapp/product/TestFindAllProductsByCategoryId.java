package in.fssa.myfashionstudioapp.product;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.dto.ProductDTO;
import in.fssa.myfashionstudioapp.service.ProductService;

public class TestFindAllProductsByCategoryId {

	@Test
	public void getAllProductsByCategoryId() {

		ProductService productService = new ProductService();

		assertDoesNotThrow(() -> {
			List<ProductDTO> ProductList = productService.findAllProductsByCategoryId(1, 4, 0);
			ProductList.forEach(System.out::println);
		});
	}

}
