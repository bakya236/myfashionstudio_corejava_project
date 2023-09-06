package in.fssa.myfashionstudioapp.category;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.model.Category;
import in.fssa.myfashionstudioapp.service.CategoryService;

public class TestGetAllCategoriesByGenderId {

	@Test
	public void findAllCategoryByGenderId() {
		CategoryService categoryService = new CategoryService();

		assertDoesNotThrow(() -> {
			List<Category> categoryList = categoryService.findAllCategoriesByGenderId(1);
			categoryList.forEach(System.out::println);
		});
	}

}
