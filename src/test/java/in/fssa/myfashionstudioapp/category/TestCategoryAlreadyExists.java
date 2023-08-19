package in.fssa.myfashionstudioapp.category;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.service.CategoryService;

public class TestCategoryAlreadyExists {

	@Test
	public void categoryAlreadyExists() {

		CategoryService categoryService = new CategoryService();

		assertDoesNotThrow(() -> {
			boolean category = categoryService.categoryAlreadyExists(76);
			System.out.println(category);
		});

	}

}
