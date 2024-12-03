package FurSPS.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import FurSPS.configs.DBConnection;
import FurSPS.dao.ISellerDAO;
import FurSPS.models.UserModel;

public class SellerDAOImpl implements ISellerDAO {

	@Override
	public List<UserModel> findAllSeller() {
		String sql = "SELECT * FROM [USER] WHERE Type=1";  // Lọc seller (Type=1)
	    List<UserModel> listSeller = new ArrayList<>();
	    try {
	        // Kết nối đến database
	        new DBConnection();
	        Connection conn = DBConnection.getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();  // Sử dụng executeQuery mà không cần truyền sql

	        // Duyệt qua kết quả trả về và ánh xạ vào đối tượng UserModel
	        while (rs.next()) {
	            UserModel seller = new UserModel();
	            seller.setUserID(rs.getInt("UserID"));
	            seller.setFirstName(rs.getString("FirstName"));
	            seller.setLastName(rs.getString("LastName"));
	            seller.setAddress(rs.getString("Address"));
	            seller.setGender(rs.getInt("Gender"));
	            seller.setPhone(rs.getString("Phone"));
	            seller.setDob(rs.getDate("DoB"));
	            seller.setCid(rs.getString("CID"));
	            seller.setAvatar(rs.getString("Avatar"));
	            seller.setKpi(rs.getInt("KPI"));
	            seller.setEmail(rs.getString("Email"));
	            seller.setType(rs.getInt("Type"));  // Lưu ý thêm dòng này nếu cần ánh xạ thêm Type
	            listSeller.add(seller);  // Thêm seller vào danh sách
	        }
	        conn.close();  // Đóng kết nối sau khi hoàn thành

	    } catch (Exception e) {
	        e.printStackTrace();  // In ra lỗi nếu có
	    }
	    return listSeller;  // Trả về danh sách seller
	}

	@Override
	public void updateSeller(UserModel model) {
		Connection conn = null;
		String sql = "Update [USER] set FirstName=?, LastName=?, Address=?, Gender=?, Phone=?, DoB=?, CID=?, KPI=?, Email=? where UserID=?";
		try {
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			// gan gia tri tham so
			ps.setString(1, model.getFirstName());
			ps.setString(2, model.getLastName());
			ps.setString(3, model.getAddress());
			ps.setInt(4, model.getGender());
			ps.setString(5, model.getPhone());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ps.setString(6, sdf.format(model.getDob()));
			ps.setString(7, model.getCid());
//			ps.setString(8, model.getAvatar());
			ps.setInt(8, model.getKpi());
			ps.setString(9, model.getEmail());
			ps.setInt(10, model.getUserID());

			ps.executeUpdate();// thuc thi cau query va tra ve Resultset
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public UserModel findOne(int id) {
		Connection conn = null;
		String sql = "Select * from [USER] where UserID=?";
		UserModel seller = new UserModel();

		try {
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				seller.setUserID(rs.getInt("UserID"));
				seller.setFirstName(rs.getString("FirstName"));
				seller.setLastName(rs.getString("LastName"));
				seller.setAddress(rs.getString("Address"));
				seller.setGender(rs.getInt("Gender"));
				seller.setPhone(rs.getString("Phone"));
				seller.setDob(rs.getDate("DoB"));
				seller.setCid(rs.getString("CID"));
				seller.setAvatar(rs.getString("Avatar"));
				seller.setKpi(rs.getInt("KPI"));
				seller.setEmail(rs.getString("Email"));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return seller;
	}

	@Override
	public void deleteSeller(int id) {
		Connection conn = null;

	    // 1. Xóa các bản ghi trong bảng ACCOUNT có liên quan đến UserID của seller
	    String deleteAccountSQL = "DELETE FROM [Account] WHERE UserID = ?";
	    try {
	        conn = DBConnection.getConnection(); // kết nối cơ sở dữ liệu

	        // Xóa tài khoản trong bảng Account
	        PreparedStatement psAccount = conn.prepareStatement(deleteAccountSQL);
	        psAccount.setInt(1, id);
	        psAccount.executeUpdate();  // Xóa tài khoản trong bảng Account

	        // 2. Xóa seller trong bảng USER
	        String deleteUserSQL = "DELETE FROM [USER] WHERE UserID = ?";
	        PreparedStatement psUser = conn.prepareStatement(deleteUserSQL);
	        psUser.setInt(1, id);
	        psUser.executeUpdate();  // Xóa seller trong bảng USER

	        conn.close(); // đóng kết nối
	    } catch (Exception e) {
	        e.printStackTrace(); // In ra lỗi nếu có
	    }
	}

	@Override
	public boolean insertSeller(UserModel model) {
		String sql = "INSERT INTO [USER] VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? , ? , ? , ? , ?)";
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, model.getUserID());
			ps.setString(2, model.getFirstName());
			ps.setString(3, model.getLastName());
			ps.setString(4, model.getAddress());
			ps.setInt(5, model.getGender());
			ps.setString(6, model.getPhone());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ps.setString(7, sdf.format(model.getDob()));
			ps.setString(8, model.getCid());
			ps.setString(9, model.getAvatar());
			ps.setInt(10, model.getType());
			ps.setInt(11,model.getKpi());
			ps.setString(12, model.getArea());
			ps.setString(13, model.getEmail());
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

		return true;

	}

	@Override
	public List<UserModel> findBestSeller() {
		Connection conn = null;
		String sql = "Select AZShop.USER.*, count(AZShop.DETAIL.ItemID) as SL\r\n"
				+ "from AZShop.USER \r\n"
				+ "join AZShop.ORDER on AZShop.USER.UserID = AZShop.ORDER.SellerID\r\n"
				+ "join AZShop.DETAIL on AZShop.ORDER.OrderID = AZShop.DETAIL.OrderID\r\n"
				+ "where AZShop.USER.Type=1 AND AZShop.ORDER.Status=4\r\n"
				+ "GROUP BY AZShop.USER.UserID\r\n"
				+ "ORDER BY SL DESC LIMIT 10;";
		List<UserModel> listSeller = new ArrayList<UserModel>();
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				UserModel seller = new UserModel();

				seller.setUserID(rs.getInt("UserID"));
				seller.setFirstName(rs.getString("FirstName"));
				seller.setLastName(rs.getString("LastName"));
				seller.setAddress(rs.getString("Address"));
				seller.setGender(rs.getInt("Gender"));
				seller.setPhone(rs.getString("Phone"));
				seller.setDob(rs.getDate("DoB"));
				seller.setCid(rs.getString("CID"));
				seller.setAvatar(rs.getString("Avatar"));
				seller.setKpi(rs.getInt("KPI"));
				seller.setEmail(rs.getString("Email"));

				listSeller.add(seller);
			}
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listSeller;
	}

}
