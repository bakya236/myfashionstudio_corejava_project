package in.fssa.myfashionstudioapp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.myfashionstudioapp.dao.UserDAO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.User;
import in.fssa.myfashionstudioapp.util.StringUtil;

public class UserValidator {
	/**
	 * 
	 * @param newUser
	 * @throws ValidationException
	 */
	public static void validateCreate(User newUser) throws ValidationException {

		if (newUser == null) {
			throw new ValidationException("Invalid user Input");
		}

		StringUtil.rejectIfInvalidString(newUser.getUserName(), "User name");
		StringUtil.rejectIfInvalidString(newUser.getEmail(), "Email");
		StringUtil.rejectIfInvalidString(newUser.getPassword(), "Password");

		// pattern validation

		UserValidator.rejectIfInvalidUserName(newUser.getUserName());
		UserValidator.rejectIfInvalidPhoneNumber(newUser.getPhoneNumber());
		UserValidator.rejectIfInvalidEmail(newUser.getEmail());
		UserValidator.rejectIfInvalidPassword(newUser.getPassword());

		// business validation
		UserValidator.rejetcIfEmailAldreadyExists(newUser.getEmail());
	}

	public static void validateUpdate(User newUser) throws ValidationException {

		if (newUser == null) {
			throw new ValidationException("Invalid user Input");
		}

		StringUtil.rejectIfInvalidString(newUser.getUserName(), "User name");
		StringUtil.rejectIfInvalidString(newUser.getEmail(), "Email");
		StringUtil.rejectIfInvalidString(newUser.getPassword(), "Password");

		// pattern validation

		UserValidator.rejectIfInvalidUserName(newUser.getUserName());
		UserValidator.rejectIfInvalidPhoneNumber(newUser.getPhoneNumber());
		UserValidator.rejectIfInvalidEmail(newUser.getEmail());
		UserValidator.rejectIfInvalidPassword(newUser.getPassword());

		// business validation
		UserValidator.rejetcIfEmailNotExists(newUser.getEmail());
	}

	public static void validateDelete(int id) throws ValidationException {
		rejectIfInvalidUserId(id);
		rejetcIfUserDoesNotExists(id);

	}

	public static void validateLogIn(String email, String password) throws ValidationException {

		StringUtil.rejectIfInvalidString(email, "Email");
		StringUtil.rejectIfInvalidString(password, "Password");

		UserValidator.rejectIfInvalidEmail(email);
	}

	private static void rejectIfInvalidUserName(String userName) throws ValidationException {
		final String NAME_PATTERN = "^[a-zA-Z ]+$";

		if (!Pattern.matches(NAME_PATTERN, userName)) {
			throw new ValidationException("User name should contain only alphabetic characters");
		}

		if (userName.length() < 3 || userName.length() > 20) {
			throw new ValidationException("User name must be between 3 and 20 characters in length.");
		}
	}

	private static void rejectIfInvalidPhoneNumber(long phoneNumber) throws ValidationException {

		String phoneNumberStr = String.valueOf(phoneNumber);

		if (phoneNumberStr.length() != 10) {
			throw new ValidationException("Phone number must consist of exactly 10 digits.");
		}

		if (phoneNumber < 6000000000L || phoneNumber > 9999999999L) {
			throw new ValidationException("Invalid phone number");
		}

	}

	/**
	 * 
	 * @param email
	 * @throws ValidationException
	 */
	private static void rejectIfInvalidEmail(String email) throws ValidationException {

		String regexPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$";

		Pattern pattern = Pattern.compile(regexPattern);

		Matcher matcher = pattern.matcher(email);

		if (!matcher.matches()) {
			throw new ValidationException(
					"Email address doesn't match the required pattern.The email should be in the format 'username@example.com'.");
		}
	}

	/**
	 * 
	 * @param password
	 * @throws ValidationException
	 */

	private static void rejectIfInvalidPassword(String password) throws ValidationException {

		String regexPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

		Pattern pattern = Pattern.compile(regexPattern);

		Matcher matcher = pattern.matcher(password);

		if (!matcher.matches()) {

			throw new ValidationException(
					"Password doesn't match the required format.The password must contain atleast one Uppercase,one Lowercase,one Special character,one number");
		}
	}

	// checkIfEmailNotAlreadyExists

	/**
	 * 
	 * @param email
	 * @throws ValidationException
	 */

	// return true if email exists
	private static boolean checkIfEmailAlreadyExists(String email) throws ValidationException {
		boolean flag;
		try {
			StringUtil.rejectIfInvalidString(email, "Email");
			UserValidator.rejectIfInvalidEmail(email);

			UserDAO userdao = new UserDAO();
			flag = userdao.EmailAlreadyExists(email); // if not return false

		} catch (PersistenceException e) {
			throw new ValidationException(e.getMessage());
		}
		return flag;
	}

	// rejetcIfEmailAldreadyExists
	/**
	 * 
	 * @param email
	 * @throws ValidationException
	 */
	private static void rejetcIfEmailAldreadyExists(String email) throws ValidationException {

		if (checkIfEmailAlreadyExists(email)) {
			throw new ValidationException("Email aldready exists try with other email address");
		}

	}

	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */

	private static boolean checkIfUserAlreadyExists(int userId) throws ValidationException {
		boolean flag;
		try {

			UserValidator.rejectIfInvalidUserId(userId);
			UserDAO userdao = new UserDAO();
			flag = userdao.userAlreadyExists(userId);

		} catch (PersistenceException e) {
			throw new ValidationException(e.getMessage());
		}

		return flag;

	}

	public static void rejetcIfUserDoesNotExists(int userId) throws ValidationException {

		if (!checkIfUserAlreadyExists(userId)) {
			throw new ValidationException("User with ID " + userId + " does not exist");
		}

	}

	private static void rejetcIfEmailNotExists(String email) throws ValidationException {

		System.out.println(checkIfEmailAlreadyExists(email));
		if (checkIfEmailAlreadyExists(email)) {
			throw new ValidationException("Email " + email + " does not exist");
		}

	}

	private static void rejectIfInvalidUserId(int userId) throws ValidationException {
		if (userId <= 0) {
			throw new ValidationException("Invalid UserId");
		}
	}

}
