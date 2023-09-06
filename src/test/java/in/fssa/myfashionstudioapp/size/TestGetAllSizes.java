package in.fssa.myfashionstudioapp.size;

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
			List<Size> sizeList = sizeService.getAllSizes();
			sizeList.forEach(System.out::println);
		});

	}

}
