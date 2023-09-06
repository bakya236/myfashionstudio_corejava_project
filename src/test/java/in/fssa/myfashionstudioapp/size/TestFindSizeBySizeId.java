package in.fssa.myfashionstudioapp.size;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.model.Size;
import in.fssa.myfashionstudioapp.service.SizeService;

public class TestFindSizeBySizeId {

	@Test
	public void FindSizeBySizeId() {

		SizeService sizeService = new SizeService();
		assertDoesNotThrow(() -> {
			Size size = sizeService.FindSizeBySizeId(1);
		});
	}

}
