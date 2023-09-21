package in.fssa.myfashionstudioapp.model;

import java.time.LocalDateTime;

public class Order {

	protected int id;
	protected String orderCode;
	protected double totalPrice;
	protected LocalDateTime orderredAt;
	protected LocalDateTime deliveredAt;
	protected User user;
	protected Address deliveryAddress;
	protected boolean isDelivered;
	protected boolean isActive;

	public Order(int id) {
		this.id = id;
	}

	public Order() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDateTime getOrderredAt() {
		return orderredAt;
	}

	public void setOrderredAt(LocalDateTime orderredAt) {
		this.orderredAt = orderredAt;
	}

	public LocalDateTime getDeliveredAt() {
		return deliveredAt;
	}

	public void setDeliveredAt(LocalDateTime deliveredAt) {
		this.deliveredAt = deliveredAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return deliveryAddress;
	}

	public void setAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public boolean isDelivered() {
		return isDelivered;
	}

	public void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderCode=" + orderCode + ", totalPrice=" + totalPrice + ", orderredAt="
				+ orderredAt + ", deliveredAt=" + deliveredAt + ", user=" + user + ", deliveryAddress="
				+ deliveryAddress + ", isDelivered=" + isDelivered + ", isActive=" + isActive + "]";
	}

}
