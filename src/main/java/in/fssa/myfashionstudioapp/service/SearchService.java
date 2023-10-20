package in.fssa.myfashionstudioapp.service;

import java.util.ArrayList;
import java.util.List;

import in.fssa.myfashionstudioapp.dao.ProductDAO;
import in.fssa.myfashionstudioapp.dto.ProductDTO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.model.SearchParameters;

public class SearchService {

	public static List<ProductDTO> peformDynamicSearch(SearchParameters searchParameters) throws PersistenceException {

		String gender = searchParameters.getGender();
		String category = searchParameters.getCategory();
		String color = searchParameters.getColor();
		int minPrice = searchParameters.getMinPrice();
		int maxPrice = searchParameters.getMaxPrice();
		String name = searchParameters.getName();

		List<ProductDTO> productList = new ArrayList<>();

		ProductDAO productDAO = new ProductDAO();

		if (gender != null) {
			if (category == null && color == null && minPrice == 0 && maxPrice == 0 && name == null) {
				productList = productDAO.findByGenderName(gender);
			}
		}

		if (category != null) {
			if (gender == null && color == null && minPrice == 0 && maxPrice == 0 && name == null) {
				productList = productDAO.findByCategoryName(category);
			}
		}

		if (color != null) {
			if (gender == null && category == null && minPrice == 0 && maxPrice == 0 && name == null) {
				productList = productDAO.findByColorNameAndProductName(color);

			}
		}

		return productList;

	}
}
