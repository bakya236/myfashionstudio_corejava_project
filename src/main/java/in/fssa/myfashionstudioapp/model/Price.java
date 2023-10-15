package in.fssa.myfashionstudioapp.model;

import java.time.LocalDateTime;

public class Price {

	private int id;
	private Product product;
	private Size size;
	private double price;
	private double offer;
	private LocalDateTime startedAt;
	private LocalDateTime endedAt;

	public Price(int id) {
		this.id = id;
	};

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

	public double getOffer() {
		return offer;
	}

	public void setOffer(double offer) {
		this.offer = offer;
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

	public int getCurrentPrice() {
		double mrpPrice = this.price;
		double currentPrice = mrpPrice - (mrpPrice * (this.offer / 100));
		return (int) Math.round(currentPrice); // Round and convert to int
	}

	@Override
	public String toString() {
		return "Price [id=" + id + ", product=" + product + ", size=" + size + ", price=" + price + ", offer=" + offer
				+ ", startedAt=" + startedAt + ", endedAt=" + endedAt + "]";
	}

}
