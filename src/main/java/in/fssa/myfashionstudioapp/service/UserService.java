package in.fssa.myfashionstudioapp.service;

import java.util.List;

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
	 * @return
	 * @throws ServiceException
	 */

	public List<User> getAllUsers() throws ServiceException {

		List<User> userList;
		try {

			userList = userDAO.findAll();

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to retrieve all users" + e.getMessage());
		}

		return userList;

	}

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
			userId = userDAO.logIn(email, Password);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to findById");
		}
		return userId;
	}
}
