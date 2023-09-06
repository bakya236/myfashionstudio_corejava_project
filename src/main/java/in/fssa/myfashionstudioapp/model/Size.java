package in.fssa.myfashionstudioapp.model;

public class Size {

	private int id;
	private String value;

	public Size(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Size() {

	}

	@Override
	public String toString() {
		return "Size [id=" + id + ", value=" + value + "]";
	}

}
