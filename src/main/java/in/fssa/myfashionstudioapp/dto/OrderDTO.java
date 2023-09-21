package in.fssa.myfashionstudioapp.dto;

import java.util.List;

import in.fssa.myfashionstudioapp.model.Order;
import in.fssa.myfashionstudioapp.model.OrderItem;

public class OrderDTO extends Order {

	private List<OrderItem> orderItemList;

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	@Override
	public String toString() {
		return "OrderDTO [orderItemList=" + orderItemList + ", id=" + id + ", orderCode=" + orderCode + ", totalPrice="
				+ totalPrice + ", orderredAt=" + orderredAt + ", deliveredAt=" + deliveredAt + ", user=" + user
				+ ", deliveryAddress=" + deliveryAddress + ", isDelivered=" + isDelivered + ", isActive=" + isActive
				+ "]";
	}

}
