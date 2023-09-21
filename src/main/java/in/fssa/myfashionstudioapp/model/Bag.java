package in.fssa.myfashionstudioapp.model;

public class Bag {

	protected Product product;
	protected int quantity;
	protected Price price;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Bag [product=" + product + ", quantity=" + quantity + ", price=" + price + "]";
	}

}
