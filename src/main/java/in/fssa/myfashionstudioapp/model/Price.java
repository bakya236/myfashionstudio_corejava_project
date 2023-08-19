package in.fssa.myfashionstudioapp.model;

import java.sql.Timestamp;

public class Price {

	private int id;
	private Product product = new Product();
	private Size size = new Size();
	private double price;
	private Timestamp startedAt;
	private Timestamp endedAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Timestamp getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(Timestamp startedAt) {
		this.startedAt = startedAt;
	}

	public Timestamp getEndedAt() {
		return endedAt;
	}

	public void setEndedAt(Timestamp endedAt) {
		this.endedAt = endedAt;
	}

	@Override
	public String toString() {
		return "Price [id=" + id + ", size=" + size + ", price=" + price + ", startedAt=" + startedAt + ", endedAt="
				+ endedAt + "]";
	}

}
