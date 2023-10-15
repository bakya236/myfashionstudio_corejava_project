package in.fssa.myfashionstudioapp.util;

import java.util.Arrays;
import java.util.List;

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

	public static SearchParameters processSearchQuery(String searchInput) {

		SearchParameters searchParameters = new SearchParameters();

		String[] tokens = searchInput.split(" ");

		System.out.println(Arrays.toString(tokens));

		for (String token : tokens) {

			// Define lists of keywords and categories
			List<String> tshirtKeywords = Arrays.asList("t-shirts", "tshirt", "t--shirts", "tshirts");
			List<String> menKeywords = Arrays.asList("men", "male", "boy", "mens", "males");
			List<String> womenKeywords = Arrays.asList("women", "female", "girls", "womens", "females");
			List<String> genderKeywords = Arrays.asList("men", "male", "boy", "mens", "males", "women", "female",
					"girls", "womens", "females");
			List<String> categoryKeywords = Arrays.asList("t-shirts", "tshirt", "tshirts", "shirts", "jeans");

			token = token.toLowerCase();

			if (genderKeywords.contains(token)) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}

			if (genderKeywords.contains(token)) {

				System.out.print("is men" + menKeywords.contains(token));
				System.out.print("is women" + womenKeywords.contains(token));

				if (menKeywords.contains(token)) {
					searchParameters.setGender("men");
					continue;
				} else if (womenKeywords.contains(token)) {
					searchParameters.setGender("women");
					continue;
				}
			} else if (categoryKeywords.contains(token.toLowerCase())) {
				if (searchParameters.getCategory() == null) {

					System.out.println("is tshirt" + tshirtKeywords.contains(token.toLowerCase()));

					if (tshirtKeywords.contains(token.toLowerCase())) {

						searchParameters.setCategory("t-shirts");
						continue;

					}
					searchParameters.setCategory(token);

				}
			}
		}

		return searchParameters;

	}

}
