package in.fssa.myfashionstudioapp.category;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.model.Category;
import in.fssa.myfashionstudioapp.service.CategoryService;

public class TestFindCategoryByCategoryId {

	@Test
	public void findCategoryByCategoryId() {
		CategoryService categoryService = new CategoryService();
		assertDoesNotThrow(() -> {
			Category category = categoryService.findCategoryByCategoryId(1);
			System.out.println(category);
		});

	}

}
