package in.fssa.myfashionstudioapp.model;

import java.sql.Date;

public class Price {

	private int id;
	private Product product = new Product();
	private Size size = new Size();
	private double price;
	private Date startDate;
	private Date endDate;

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date date) {
		this.startDate = date;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date date) {
		this.endDate = date;
	}

	@Override
	public String toString() {
		return "Price [id=" + id + " size=" + size + ", price=" + price + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}

}
