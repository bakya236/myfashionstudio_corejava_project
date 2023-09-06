package in.fssa.myfashionstudioapp.model;

public class Category {

	private int id;
	private String name;
	private Gender gender = new Gender();

	public Category() {

	}

	public Category(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", gender_id=" + gender + "]";
	}

}
