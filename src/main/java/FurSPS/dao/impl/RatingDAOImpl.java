package FurSPS.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import FurSPS.configs.DBConnection;
import FurSPS.dao.IRatingDAO;
import FurSPS.models.submodels.RatingModel;

public class RatingDAOImpl implements IRatingDAO {

	@Override
	public List<RatingModel> findRatinglByProductID(int productID) {
		String sql  = "SELECT Rating, COUNT(*) AS RatingCount FROM DETAIL d JOIN ITEM i ON i.ItemID = d.ItemID WHERE Rating IN (1, 2, 3, 4, 5) AND i.ProductID = ? GROUP BY Rating ORDER BY Rating DESC;";

		List<RatingModel> listRating = new ArrayList<>();
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, productID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RatingModel rating = new RatingModel();
				rating.setNumOfStar(rs.getInt("Rating"));
				rating.setNumOfRating(rs.getInt("RatingCount"));
				listRating.add(rating);
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return listRating;
	}

}
