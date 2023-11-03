package in.fssa.myfashionstudioapp.service;

import java.util.ArrayList;
import java.util.List;

import in.fssa.myfashionstudioapp.dao.ProductDAO;
import in.fssa.myfashionstudioapp.dto.ProductDTO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.model.SearchParameters;

public class SearchService {

	public static List<ProductDTO> peformDynamicSearch(SearchParameters searchParameters, int limit, int offset)
			throws PersistenceException {

		String gender = searchParameters.getGender();
		String category = searchParameters.getCategory();
		String color = searchParameters.getColor();
		int minPrice = searchParameters.getMinPrice();
		int maxPrice = searchParameters.getMaxPrice();
		String name = searchParameters.getName();
		List<String> pattern = searchParameters.getPattern();

		List<ProductDTO> productList = new ArrayList<>();

		ProductDAO productDAO = new ProductDAO();

		
		if (pattern != null) {
			if (gender == null && category == null && color == null && minPrice == 0 && maxPrice == 0 && name == null) {
				productList = productDAO.findByPattern(pattern,limit, offset);
			}
		}

		if (gender != null) {
			if (category == null && color == null && minPrice == 0 && maxPrice == 0 && name == null && pattern== null) {
				productList = productDAO.findByGenderName(gender,limit, offset);
			}else if (category != null && color == null && minPrice == 0 && maxPrice == 0 && name == null && pattern== null){
				productList = productDAO.findByGenderNameAndCategoryName(gender, category ,limit, offset);
			}
		}

		if (category != null) {
			if (gender == null && color == null && minPrice == 0 && maxPrice == 0 && name == null&& pattern== null) {
				productList = productDAO.findByCategoryName(category , limit , offset);
			}
			
		}

		if (color != null) {
			if (gender == null && category == null && minPrice == 0 && maxPrice == 0 && name == null && pattern== null) {
				productList = productDAO.findByColorNameAndProductName(color, limit, offset);
			}else if (gender == null && category != null && minPrice == 0 && maxPrice == 0 && name == null && pattern== null) {
				productList = productDAO.findByColorNameAndProductNameWithcategoryName(color,category, limit, offset);
			}
		}

		if (minPrice != 0) {
			if (gender == null && category == null && color == null && maxPrice == 0 && name == null && pattern== null) {
				productList = productDAO.findByMinPrice(minPrice);
			}else if (gender == null && category != null && color == null && maxPrice == 0 && name == null && pattern== null) {
				productList = productDAO.findByMinPriceWithCategoryName(category , minPrice , limit ,offset);
			}
		}
		
		if (maxPrice != 0) {
			if (gender == null && category != null && color == null && minPrice == 0 && name == null && pattern== null) {
				productList = productDAO.findByMaxPriceWithCategoryName(category , maxPrice , limit ,offset);
			}
		}
		
		if(name != null || name == null) {
			if (gender == null && category == null && color == null && minPrice == 0 && maxPrice == 0 && pattern== null) {
				productList = productDAO.findByName(name, limit, offset);
			}
		}

		return productList;

	}
}
