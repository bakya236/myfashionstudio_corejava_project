package in.fssa.myfashionstudioapp.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.fssa.myfashionstudioapp.model.SearchParameters;

public class SearchQueryUtil {

	public static String removeSpecialCharacters(String input) {
		String pattern = "[^a-zA-Z0-9\\s]+";

		return input.replaceAll(pattern, "");
	}

	public static String removeConsecutiveSpaces(String input) {

		String pattern = "\\s{2,}";

		// Replace consecutive spaces with a single space
		return input.replaceAll(pattern, " ");
	}

	public static String sanitizeText(String input) {
		return removeConsecutiveSpaces(removeSpecialCharacters(input));
	}

	private static Map<String, String> createGenderKeywordMap() {
		Map<String, String> map = new HashMap<>();
		map.put("men", "men");
		map.put("male", "men");
		map.put("boy", "men");
		map.put("women", "women");
		map.put("female", "women");
		map.put("girls", "women");
		return map;
	}

	private static Map<String, String> createCategoryKeywordMap() {
		Map<String, String> map = new HashMap<>();
		map.put("t-shirts", "t-shirts");
		map.put("tshirt", "t-shirts");
		map.put("tshirts", "t-shirts");
		map.put("shirts", "shirts");
		map.put("jeans", "jeans");
		return map;
	}

	private static List<String> createColorKeyword() {

		List<String> colorList = Arrays.asList("black", "white", "red", "green", "blue", "yellow", "purple", "pink",
				"orange", "gray", "brown", "navy blue", "teal", "lavender", "cyan", "magenta", "olive", "beige",
				"maroon", "turquoise");
		return colorList;
	}

	private static boolean isValidPrice(String token) {
		try {
			Integer.parseInt(token);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static SearchParameters processSearchQuery(String searchInput) {

		SearchParameters searchParameters = new SearchParameters();

		String[] tokens = searchInput.split(" ");

		final Map<String, String> genderKeywordMap = createGenderKeywordMap();
		final Map<String, String> categoryKeywordMap = createCategoryKeywordMap();
		final List<String> colorKeyword = createColorKeyword();

		if (tokens.length == 1) {

			String token = tokens[0].toLowerCase();

			if (genderKeywordMap.containsKey(token)) {
				searchParameters.setGender(genderKeywordMap.get(token));
				return searchParameters;
			} else if (categoryKeywordMap.containsKey(token)) {
				searchParameters.setCategory(categoryKeywordMap.get(token));
				return searchParameters;
			} else if (colorKeyword.contains(token)) {
				searchParameters.setColor(token);
				return searchParameters;
			} else if (isValidPrice(token)) {
				searchParameters.setMinPrice(Integer.parseInt(token));
				return searchParameters;
			} else {
				searchParameters.setName(token);
				return searchParameters;
			}
		}

//		} else {
//
//			for (String token : tokens) {
//
//				if (genderKeywordMap.containsKey(token)) {
//					searchParameters.setGender(genderKeywordMap.get(token));
//					continue;
//				} else if (categoryKeywordMap.containsKey(token)) {
//					searchParameters.setCategory(categoryKeywordMap.get(token));
//					continue;
//				} else if (colorKeyword.contains(token)) {
//					searchParameters.setColor(token);
//					continue;
//				} else if (isValidPrice(token)) {
//					searchParameters.setMinPrice(Integer.parseInt(token));
//					continue;
//				} else {
//					searchParameters.setName(token);
//					continue;
//				}
//			}
//		}
		return searchParameters;

	}

}
