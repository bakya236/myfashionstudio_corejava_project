package in.fssa.myfashionstudioapp.model;

public class Product {

	protected int id;
	protected String image;
	protected String name;
	protected String pattern;
	protected String fit;
	protected String material;
	protected String length;
	protected String riseType;
	protected String closureType;
	protected String sleeveType;
	protected String necklineType;
	protected String occasion;
	protected String care;
	protected int stock;
	protected int buyersCount;
	protected String description;
	protected Color color;
	protected Category category;
	protected boolean status;
	private Price price;

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getFit() {
		return fit;
	}

	public void setFit(String fit) {
		this.fit = fit;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getRiseType() {
		return riseType;
	}

	public void setRiseType(String riseType) {
		this.riseType = riseType;
	}

	public String getClosureType() {
		return closureType;
	}

	public void setClosureType(String closureType) {
		this.closureType = closureType;
	}

	public String getSleeveType() {
		return sleeveType;
	}

	public void setSleeveType(String sleeveType) {
		this.sleeveType = sleeveType;
	}

	public String getNecklineType() {
		return necklineType;
	}

	public void setNecklineType(String necklineType) {
		this.necklineType = necklineType;
	}

	public String getOccasion() {
		return occasion;
	}

	public void setOccasion(String occasion) {
		this.occasion = occasion;
	}

	public String getCare() {
		return care;
	}

	public void setCare(String care) {
		this.care = care;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getBuyersCount() {
		return buyersCount;
	}

	public void setBuyersCount(int buyersCount) {
		this.buyersCount = buyersCount;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Product() {

	}

	public Product(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", image=" + image + ", name=" + name + ", pattern=" + pattern + ", fit=" + fit
				+ ", material=" + material + ", length=" + length + ", riseType=" + riseType + ", closureType="
				+ closureType + ", sleeveType=" + sleeveType + ", necklineType=" + necklineType + ", occasion="
				+ occasion + ", care=" + care + ", stock=" + stock + ", buyersCount=" + buyersCount + ", description="
				+ description + ", color=" + color + ", category=" + category + ", status=" + status + ", price="
				+ price + "]";
	}

}
