package in.fssa.myfashionstudioapp.model;

public class User {

	int user_name;
	String email;
	String password;
	boolean is_active;

	public int getUser_name() {
		return user_name;
	}

	public void setUser_name(int user_name) {
		this.user_name = user_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

}
