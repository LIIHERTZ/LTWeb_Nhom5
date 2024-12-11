package FurSPS.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import FurSPS.dao.IDetailDAO;
import FurSPS.models.DetailModel;

import FurSPS.configs.DBConnection;

public class DetailDAOImpl implements IDetailDAO {

	@Override
	public List<DetailModel> findDetailByProductID(int productID) {
		String sql = "SELECT d.ItemID, d.Content, d.Rating, d.EvaluationDate, u.Avatar, CONCAT(u.FirstName, ' ', u.LastName) AS Name " +
	             "FROM DETAIL d " +
	             "JOIN [ORDER] o ON d.OrderID = o.OrderID " +
	             "JOIN [USER] u ON u.UserID = o.CustomerID " +
	             "JOIN ITEM i ON i.ItemID = d.ItemID " +
	             "WHERE i.ProductID = ?;";
		List<DetailModel> listDetail = new ArrayList<>();
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, productID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DetailModel Detail = new DetailModel();
				Detail.setAvatar(rs.getString("Avatar"));
				Detail.setContent(rs.getString("Content"));
				Detail.setEvaluationDate(rs.getDate("EvaluationDate"));
				Detail.setItemID(rs.getInt("ItemID"));
				Detail.setRating(rs.getInt("Rating"));
				Detail.setName(rs.getString("Name"));
				listDetail.add(Detail);
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return listDetail;
	}

	@Override
	public void updateDetail(DetailModel detail) {
		String sql = "UPDATE `DETAIL` SET Rating = ?, Content = ?, EvaluationDate = ? WHERE ItemID = ? AND OrderID = ?";
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, detail.getRating());
			ps.setString(2, detail.getContent());
			ps.setDate(3, new java.sql.Date(detail.getEvaluationDate().getTime()));
			ps.setInt(4, detail.getItemID());
			ps.setInt(5, detail.getOrderID());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<DetailModel> listDetail(int orderID) {
		List<DetailModel> listDetail = new ArrayList<DetailModel>();
		String sql =  "SELECT  P.ProductID,P.Description, I.ItemID, O.OrderID, P.ProductName, I.Color, I.Size, D.Quantity, I.OriginalPrice, I.PromotionPrice, IM.Image\r\n"
					+ "FROM PRODUCT AS P \r\n"
					+ "			INNER JOIN ITEM I ON P.ProductID = I.ProductID \r\n"
					+ "			INNER JOIN DETAIL D on I.ItemID = D.ItemID\r\n"
					+ "			INNER JOIN `ORDER` O on O.OrderID = D.OrderID\r\n"
					+ "    		INNER JOIN (SELECT MIN(II.ItemImageID) AS ItemImageID, II.ItemID, MIN(II.Image) AS Image\r\n"
					+ "						FROM ITEMIMAGE II, ITEM IT\r\n"
					+ "						WHERE II.ItemID = IT.ItemID\r\n"
					+ "						GROUP BY II.ItemID) IM ON IM.ItemID = I.ItemID \r\n"
					+ "WHERE O.OrderID = ?";
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, orderID);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				DetailModel detail = new DetailModel();
				detail.getProduct().setProductID(rs.getInt(1));
				detail.getProduct().setDescription(rs.getString(2));
				detail.setItemID(rs.getInt(3));
				detail.setOrderID(rs.getInt(4));
				detail.getProduct().setProductName(rs.getString(5));
				detail.getItem().setColor(rs.getString(6));
				detail.getItem().setSize(rs.getString(7));
				detail.setQuantity(rs.getInt(8));
				detail.getItem().setOriginalPrice(rs.getInt(9));
				detail.getItem().setPromotionPrice(rs.getInt(10));
				detail.getItem().setImage(rs.getString(11));
				
				listDetail.add(detail);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listDetail;
	}

	@Override
	public DetailModel findDetailByItemID(int orderID, int itemID) {
		String sql = "SELECT  P.ProductID, P.Description, I.ItemID, O.OrderID, P.ProductName, I.Color, I.Size, D.Quantity, I.OriginalPrice, I.PromotionPrice, IM.Image \r\n"
				+ "FROM PRODUCT AS P  INNER JOIN ITEM I ON P.ProductID = I.ProductID \r\n"
				+ "				   INNER JOIN DETAIL D on I.ItemID = D.ItemID \r\n"
				+ "				   INNER JOIN `ORDER` O on O.OrderID = D.OrderID \r\n"
				+ "				   INNER JOIN (SELECT MIN(II.ItemImageID) AS ItemImageID, II.ItemID, MIN(II.Image) AS Image \r\n"
				+ "								FROM ITEMIMAGE II, ITEM IT WHERE II.ItemID = IT.ItemID \r\n"
				+ "								GROUP BY II.ItemID) IM ON IM.ItemID = I.ItemID \r\n"
				+ "WHERE O.OrderID = ? and I.ItemID = ? ";
		DetailModel detail = new DetailModel();
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, orderID);
			ps.setInt(2, itemID);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				detail.getProduct().setProductID(rs.getInt(1));
				detail.getProduct().setDescription(rs.getString(2));
				detail.setItemID(rs.getInt(3));
				detail.setOrderID(rs.getInt(4));
				detail.getProduct().setProductName(rs.getString(5));
				detail.getItem().setColor(rs.getString(6));
				detail.getItem().setSize(rs.getString(7));
				detail.setQuantity(rs.getInt(8));
				detail.getItem().setOriginalPrice(rs.getInt(9));
				detail.getItem().setPromotionPrice(rs.getInt(10));
				detail.getItem().setImage(rs.getString(11));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return detail;
	}
	@Override
	public List<Map<String, Object>> listBestSeller() {
		List<Map<String, Object>> listBestSeller = new ArrayList<>();
		String sql =  "SELECT P.ProductID, I.ItemID, P.ProductName, P.Description, I.OriginalPrice, I.PromotionPrice, " +
	             "IM.Image, COALESCE(D.AverageRating, 0) AS Rating " +
	             "FROM PRODUCT AS P " +
	             "INNER JOIN ITEM I ON P.ProductID = I.ProductID " +
	             "INNER JOIN (SELECT itemID AS ItemID, COUNT(ItemID) AS SL, AVG(Rating) AS AverageRating FROM DETAIL GROUP BY itemID) AS D ON I.ItemID = D.ItemID " +
	             "INNER JOIN (SELECT MIN(II.ItemImageID) AS ItemImageID, II.ItemID, MIN(II.Image) AS Image FROM ITEMIMAGE II INNER JOIN ITEM IT ON II.ItemID = IT.ItemID GROUP BY II.ItemID) AS IM ON IM.ItemID = I.ItemID " +
	             "ORDER BY D.SL DESC OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY;";
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Map<String, Object> row = new HashMap<>();
			    row.put("ProductID", rs.getInt("ProductID"));
			    row.put("ItemID", rs.getInt("ItemID"));
			    row.put("ProductName", rs.getString("ProductName"));
			    row.put("Description", rs.getString("Description"));
			    row.put("OriginalPrice", rs.getInt("OriginalPrice"));
			    row.put("PromotionPrice", rs.getInt("PromotionPrice"));
			    row.put("Image", rs.getString("Image"));
			    row.put("Rating", rs.getString("Rating"));
				
				listBestSeller.add(row);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listBestSeller;
	}
}