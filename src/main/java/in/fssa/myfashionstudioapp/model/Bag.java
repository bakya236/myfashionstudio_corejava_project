package in.fssa.myfashionstudioapp.model;

import in.fssa.myfashionstudioapp.dto.ProductDTO;

public class Bag extends ProductDTO {

	private int quantity;
	private Size size = new Size();

	public Bag() {

	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Bag [quantity=" + quantity + ", size=" + size + ", priceList=" + priceList + ", id=" + id + ", image="
				+ image + ", name=" + name + ", description=" + description + ", category=" + category + ", status="
				+ status + "]";
	}
}
