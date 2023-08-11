package in.fssa.myfashionstudioapp.category;

import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.myfashionstudioapp.model.Category;
import in.fssa.myfashionstudioapp.service.CategoryService;

public class TestGetAllCategoriesByGenderId {

	@Test
	public void findAllCategoryByGenderId() throws Exception {
		CategoryService categoryService = new CategoryService();
		List<Category> categoryList = categoryService.findAllCatgegoryByGenderId(1);

		categoryList.forEach(System.out::println);
	}

}
