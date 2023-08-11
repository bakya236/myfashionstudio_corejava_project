package in.fssa.myfashionstudioapp.model;

import java.time.LocalDateTime;

public class Price {

	private int id;
	private Product product = new Product();
	private Size size = new Size();
	private double price;
	private LocalDateTime startDate;
	private LocalDateTime endDate;

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

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Price [id=" + id + ", product=" + product + ", size=" + size + ", price=" + price + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}

}
