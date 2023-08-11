package in.fssa.myfashionstudioapp.Product;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.dto.ProductDTO;
import in.fssa.myfashionstudioapp.model.Price;
import in.fssa.myfashionstudioapp.model.Size;
import in.fssa.myfashionstudioapp.service.ProductService;

public class TestCreateProduct {

	@Test
	public void createProductWithPrices() {

		// create the dto instance

		ProductDTO productDto = new ProductDTO();

		List<Price> priceList = productDto.getPriceList();

		//
		Price price1 = new Price();

		Size size1 = new Size();
		size1.setId(1);

		price1.setSize(size1);
		price1.setPrice(800.00d);
		//

		//
		Price price2 = new Price();

		Size size2 = new Size();
		size2.setId(2);

		price2.setSize(size2);
		price2.setPrice(1000.00d);
		//

		priceList.add(price1);
		priceList.add(price2);

		//

		productDto.setPriceList(priceList);
		productDto.getCategory().setId(1);
		productDto.setName("Round neck T-shirt");
		productDto.setDescription("Round neck fitted red color T-shirt ");

		ProductService productService = new ProductService();

		assertDoesNotThrow(() -> {
			productService.createProductWithPrices(productDto);
		});
	}

}
