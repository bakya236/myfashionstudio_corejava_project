package in.fssa.myfashionstudioapp.Product;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.dto.ProductDTO;
import in.fssa.myfashionstudioapp.model.Price;
import in.fssa.myfashionstudioapp.model.Size;
import in.fssa.myfashionstudioapp.service.ProductService;

public class TestUpdateProductDetailsAndPrices {

	@Test
	public void UpdateProductDetailsAndPrices() {
		ProductDTO productDto = new ProductDTO();

		productDto.setName("black printed t-shirt");
		productDto.setDescription("regular black printed rounded neck t-shirt");
		productDto.getCategory().setId(2);

		List<Price> priceList = productDto.getPriceList();

		// product id
		int productId = 1;
		//
		Price price1 = new Price();// {}
		price1.getProduct().setId(productId);
		price1.setPrice(780.00d);

		Size size = new Size();
		size.setId(2);

		price1.setSize(size);

//		Price price2 = new Price();
//		price2.setPrice(720.00d);
//
//		Size size1 = new Size();
//		size1.setId(2);
//		price2.setSize(size1);
		//

		priceList.add(price1);
//		priceList.add(price2);

		//

		productDto.setPriceList(priceList);
		productDto.setId(productId);

		ProductService productService = new ProductService();

		assertDoesNotThrow(() -> {
			productService.updateProductDetailsAndPrices(productId, productDto);
		});

	}

}
