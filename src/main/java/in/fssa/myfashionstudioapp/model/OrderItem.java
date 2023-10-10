package in.fssa.myfashionstudioapp.model;

public class OrderItem extends Bag {

	private int id;
	private Order order;
	private String status;
	private boolean isCancel;
	private String cancelReason;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String string) {
		this.status = string;
	}

	public boolean isCancel() {
		return isCancel;
	}

	public void setCancel(boolean b) {
		this.isCancel = b;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", order=" + order + ", status=" + status + ", isCancel=" + isCancel
				+ ", cancelReason=" + cancelReason + ", product=" + product + ", quantity=" + quantity + ", price="
				+ price + "]";
	}

}
