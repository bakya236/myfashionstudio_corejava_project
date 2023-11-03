package in.fssa.myfashionstudioapp.util;

import java.util.ArrayList;
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
	
	
	public static String removeCommonWords(String input) {
		
	    String[] commonWords = {"and","in", "like" , "for" , "less" ,"than" };

	    input = input.toLowerCase();
	    
	    for (String word : commonWords) {
	        input = input.replace(word, "");
	    }
	    return input;
	}

	public static String sanitizeText(String input) {
		
		input  = removeConsecutiveSpaces(removeSpecialCharacters(input));
		
		return removeCommonWords(input);
	}
	

	private static Map<String, String> createGenderKeywordMap() {
		Map<String, String> map = new HashMap<>();
		map.put("men", "men");
		map.put("male", "men");
		map.put("boy", "men");
		map.put("mens", "men");
		map.put("males", "men");
		map.put("boys", "men");
		map.put("women", "women");
		map.put("female", "women");
		map.put("girl", "women");
		map.put("womens", "women");
		map.put("females", "women");
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
	
	private static List<String> createPatternKeyword() {
		List<String> patternList = Arrays.asList("solidcolor","checked");
		return patternList;
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
		
		System.out.println(Arrays.toString(tokens));

		final Map<String, String> genderKeywordMap = createGenderKeywordMap();
		final Map<String, String> categoryKeywordMap = createCategoryKeywordMap();
		final List<String> colorKeyword = createColorKeyword();
		final List<String> patternKeyword = createPatternKeyword();
		

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
			}else {
				searchParameters.setName(token);
				return searchParameters;
			}
		}
		
		if(tokens.length >= 2) {
			List<String> addPattern =  new ArrayList<String>();
			for (String token : tokens) {

				if (genderKeywordMap.containsKey(token)) {
					searchParameters.setGender(genderKeywordMap.get(token));
					
				}else if(patternKeyword.contains(token)){
						
					System.out.println(token);
					addPattern.add(token);
					
						searchParameters.setPattern(addPattern);
				}else if (categoryKeywordMap.containsKey(token)) {
					searchParameters.setCategory(categoryKeywordMap.get(token));
					
				} else if (colorKeyword.contains(token)) {
					searchParameters.setColor(token);
					
				} else if (isValidPrice(token)) {
					
					for (String token2 : tokens) {
					 if ("above".equals(token2)) {
						 
						 searchParameters.setMaxPrice(Integer.parseInt(token));
						 searchParameters.setMinPrice(0);
						 break; 
					    }else {
					        searchParameters.setMinPrice(Integer.parseInt(token));
					        searchParameters.setMaxPrice(0);
					    }
					}
					
				}
	
			}
		}
		else {
			searchParameters.setName(searchInput);
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
