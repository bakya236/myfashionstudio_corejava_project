package in.fssa.myfashionstudioapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.fssa.myfashionstudioapp.model.Price;
import in.fssa.myfashionstudioapp.util.ConnectionUtil;

public class PriceDAO {

	public void createPrice(Price price) {
		System.out.println(price);
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String Query = "Insert into prices (products_id, sizes_id, price) values(?,?,?)";

			con = ConnectionUtil.getConnection();

			ps = con.prepareStatement(Query);

			System.out.println(price.getProduct().getId());
			System.out.println(price.getSize().getId());

			ps.setInt(1, price.getProduct().getId());
			ps.setInt(2, price.getSize().getId());
			ps.setDouble(3, price.getPrice());

			ps.executeUpdate();

			System.out.println("created price");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

//	pricealdreadyexists method <business validation>

}
