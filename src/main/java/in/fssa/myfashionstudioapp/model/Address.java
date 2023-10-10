package in.fssa.myfashionstudioapp.model;

public class Address {

	private int id;
	private String title;
	private String address;
	private String landMark;
	private String city;
	private String state;
	private String country;
	private int pincode;
	private User user;

	private boolean status;

	public Address(int id) {
		this.id = id;
	}

	public Address() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", title=" + title + ", user=" + user + ", address=" + address + ", landMark="
				+ landMark + ", city=" + city + ", state=" + state + ", country=" + country + ", pincode=" + pincode
				+ ", status=" + status + "]";
	}

}
