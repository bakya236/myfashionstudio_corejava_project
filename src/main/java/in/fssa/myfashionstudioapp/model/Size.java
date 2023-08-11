package in.fssa.myfashionstudioapp.model;

public class Size {

	private int id;
	private String Value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

	@Override
	public String toString() {
		return "Size [id=" + id + ", Value=" + Value + "]";
	}

}
