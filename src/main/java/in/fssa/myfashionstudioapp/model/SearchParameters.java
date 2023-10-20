package in.fssa.myfashionstudioapp.model;

public class SearchParameters {

	private String gender;
	private String category;
	private String color;
	private int minPrice;
	private int maxPrice;
	private String name;

	public SearchParameters() {

	}

	public SearchParameters(String gender, String category, String color, int minPrice, int maxPrice, String name) {
		super();
		this.gender = gender;
		this.category = category;
		this.color = color;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SearchParameters [gender=" + gender + ", category=" + category + ", color=" + color + ", minPrice="
				+ minPrice + ", maxPrice=" + maxPrice + ", name=" + name + "]";
	}

}
