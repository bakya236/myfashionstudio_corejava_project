package in.fssa.myfashionstudioapp.model;

public class OrderItem extends Bag {

	private int id;
	private Order order;
	private boolean status;
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

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isCancel() {
		return isCancel;
	}

	public void setCancel(boolean isCancel) {
		this.isCancel = isCancel;
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
