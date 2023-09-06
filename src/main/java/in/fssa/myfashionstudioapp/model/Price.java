package in.fssa.myfashionstudioapp.model;

import java.time.LocalDateTime;

public class Price {

	private int id;
	private Product product = new Product();
	private Size size = new Size();
	private double price;
	private LocalDateTime startedAt;
	private LocalDateTime endedAt;

	public Price() {

	};

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

	public LocalDateTime getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(LocalDateTime startedAt) {
		this.startedAt = startedAt;
	}

	public LocalDateTime getEndedAt() {
		return endedAt;
	}

	public void setEndedAt(LocalDateTime endedAt) {
		this.endedAt = endedAt;
	}

	@Override
	public String toString() {
		return "Price [id=" + id + ", size=" + size + ", price=" + price + ", startedAt=" + startedAt + ", endedAt="
				+ endedAt + "]";
	}

}
