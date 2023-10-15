package in.fssa.myfashionstudioapp.model;

public class Color {

	private int id;
	private String colorName;
	private String colorHexCode;

	public Color() {

	}

	public Color(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getColorHexCode() {
		return colorHexCode;
	}

	public void setColorHexCode(String colorHexCode) {
		this.colorHexCode = colorHexCode;
	}

	@Override
	public String toString() {
		return "Color [id=" + id + ", colorName=" + colorName + ", colorHexCode=" + colorHexCode + "]";
	}

}
