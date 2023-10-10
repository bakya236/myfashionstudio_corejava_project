package in.fssa.myfashionstudioapp.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import in.fssa.myfashionstudioapp.dao.UserDAO;
import in.fssa.myfashionstudioapp.exception.PersistenceException;
import in.fssa.myfashionstudioapp.exception.ServiceException;
import in.fssa.myfashionstudioapp.exception.ValidationException;
import in.fssa.myfashionstudioapp.model.User;
import in.fssa.myfashionstudioapp.util.StringUtil;
import in.fssa.myfashionstudioapp.validator.UserValidator;

public class UserService {
	UserDAO userDAO = new UserDAO();

	/**
	 * 
	 * @param newUser
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public int createUser(User newUser) throws ValidationException, ServiceException {

		int userId;

		try {
			UserValidator.validateCreate(newUser);

			newUser.setPassword(hashPassword(newUser.getPassword()));

			userId = userDAO.create(newUser);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return userId;
	}

	public void updateUser(User newUser) throws ValidationException, ServiceException {

		try {
			UserValidator.validateUpdate(newUser);
			UserValidator.rejetcIfUserDoesNotExists(newUser.getId());

			userDAO.update(newUser);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to Update User " + e.getMessage());
		}

	}

	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void deleteUser(int id) throws ValidationException, ServiceException {

		try {

			UserValidator.validateDelete(id);

			userDAO.delete(id);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to Delete User");
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public User findById(int id) throws ValidationException, ServiceException {

		try {

			UserValidator.rejetcIfUserDoesNotExists(id);

			return userDAO.findById(id);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to findById");
		}
	}

	/**
	 * 
	 * @param email
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public User findByEmail(String email) throws ValidationException, ServiceException {

		try {

			StringUtil.rejectIfInvalidString(email, "Email");

			return userDAO.findByEmail(email);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to findById");
		}
	}

	/**
	 * 
	 * @param email
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public int logIn(String email, String Password) throws ValidationException, ServiceException {

		int userId;

		try {
			UserValidator.validateLogIn(email, Password);

			UserDAO userDAO = new UserDAO();

			userId = userDAO.logIn(email, hashPassword(Password));

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to findById");
		}
		return userId;
	}

// encryption

	private static String hashPassword(String password) throws ServiceException {
		try {
			
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

			// Convert the byte array to a hexadecimal string
			StringBuilder sb = new StringBuilder();

			for (byte b : hashedBytes) {
				sb.append(String.format("%02x", b));
			}

			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
