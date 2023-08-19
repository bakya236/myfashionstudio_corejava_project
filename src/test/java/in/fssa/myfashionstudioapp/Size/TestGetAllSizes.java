package in.fssa.myfashionstudioapp.Size;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.model.Size;
import in.fssa.myfashionstudioapp.service.SizeService;

public class TestGetAllSizes {

	@Test
	public void findAllSizes() {
		SizeService sizeService = new SizeService();

		assertDoesNotThrow(() -> {
			List<Size> sizeList = sizeService.FindAllSizes();
			sizeList.forEach(System.out::println);
		});

	}

}
