package in.fssa.myfashionstudioapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {
	/**
	 * 
	 * @return
	 * @throws RuntimeException
	 */
	public static Connection getConnection() throws RuntimeException {

		String url;
		String userName;
		String passWord;

		url = System.getenv("DATABASE_HOSTNAME");
		userName = System.getenv("DATABASE_USERNAME");
		passWord = System.getenv("DATABASE_PASSWORD");

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, userName, passWord);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return conn;

	}

	/**
	 * 
	 * @param connection
	 * @param ps
	 */
	public static void close(Connection connection, PreparedStatement ps) {

		try {
			if (ps != null) {
				ps.close();
			}

			if (connection != null) {
				connection.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	/**
	 * 
	 * @param connection
	 * @param ps
	 * @param rs
	 */
	public static void close(Connection connection, PreparedStatement ps, ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}

			if (ps != null) {
				ps.close();
			}

			if (connection != null) {
				connection.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("closed connection");

		}
	}

}
