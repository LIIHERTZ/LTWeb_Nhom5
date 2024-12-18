package FurSPS.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import FurSPS.configs.DBConnection;
import FurSPS.dao.IShipperDAO;
import FurSPS.models.UserModel;

public class ShipperDAOImpl implements IShipperDAO {

	@Override
	public List<UserModel> findAllShipper() {
		String sql = "SELECT * FROM [USER] WHERE Type = 2";
	    List<UserModel> listShipper = new ArrayList<>();

	    try {
	        Connection conn = DBConnection.getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            UserModel shipper = new UserModel();
	            shipper.setUserID(rs.getInt(1));           // Cột 1: UserID
	            shipper.setFirstName(rs.getString(2));     // Cột 2: FirstName
	            shipper.setLastName(rs.getString(3));      // Cột 3: LastName
	            shipper.setAddress(rs.getString(4));       // Cột 4: Address
	            shipper.setGender(rs.getBoolean(5) ? 1 : 0); // Cột 5: Gender (boolean -> int)
	            shipper.setPhone(rs.getString(6));         // Cột 6: Phone
	            shipper.setDob(rs.getDate(7));             // Cột 7: DoB
	            shipper.setCid(rs.getString(8));           // Cột 8: CID
	            shipper.setAvatar(rs.getString(9));        // Cột 9: Avatar
	            shipper.setType(rs.getInt(10));            // Cột 10: Type
	            shipper.setKpi(rs.getInt(11));             // Cột 11: KPI
	            shipper.setArea(rs.getString(12));         // Cột 12: Area
	            shipper.setEmail(rs.getString(13));        // Cột 13: Email

	            listShipper.add(shipper);
	        }
	        conn.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    if (listShipper.isEmpty()) {
	        System.out.println("No shippers found.");
	    }
	    return listShipper;
	}

	@Override
	public void updateShipper(UserModel model) {
		Connection conn = null;
		String sql = "Update [USER] set FirstName=?, LastName=?, Address=?, Gender=?, Phone=?, DoB=?, CID=?, Avatar=?, Area=?, Email=? where UserID=?";
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
			ps.setString(8, model.getAvatar());
			ps.setString(9, model.getArea());
			ps.setString(10, model.getEmail());
			ps.setInt(11, model.getUserID());

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
		UserModel shipper = new UserModel();

		try {
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				shipper.setUserID(rs.getInt("UserID"));
				shipper.setFirstName(rs.getString("FirstName"));
				shipper.setLastName(rs.getString("LastName"));
				shipper.setAddress(rs.getString("Address"));
				shipper.setGender(rs.getInt("Gender"));
				shipper.setPhone(rs.getString("Phone"));
				shipper.setDob(rs.getDate("DoB"));
				shipper.setCid(rs.getString("CID"));
				shipper.setAvatar(rs.getString("Avatar"));
				shipper.setArea(rs.getString("Area"));
				shipper.setEmail(rs.getString("Email"));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return shipper;
	}

	@Override
	public void deleteShipper(int id) {
		Connection conn = null;

	    try {
	        conn = DBConnection.getConnection(); // Kết nối cơ sở dữ liệu

	        // 1. Xóa các bản ghi trong bảng SearchHistory liên quan đến CustomerID
	        String deleteSearchHistorySQL = "DELETE FROM [SearchHistory] WHERE CustomerID = ?";
	        PreparedStatement psSearchHistory = conn.prepareStatement(deleteSearchHistorySQL);
	        psSearchHistory.setInt(1, id);
	        psSearchHistory.executeUpdate();

	        String deleteDetailSQL = "DELETE FROM [Detail] WHERE OrderID IN (SELECT OrderID FROM [Order] WHERE ShipperID = ?)";
	        PreparedStatement psDetail = conn.prepareStatement(deleteDetailSQL);
	        psDetail.setInt(1, id);
	        psDetail.executeUpdate();

	        // 2. Xóa các bản ghi trong bảng Payment liên quan đến các Order của CustomerID hoặc ShipperID
	        String deletePaymentSQL = "DELETE FROM [Payment] WHERE OrderID IN (SELECT OrderID FROM [Order] WHERE CustomerID = ? OR ShipperID = ?)";
	        PreparedStatement psPayment = conn.prepareStatement(deletePaymentSQL);
	        psPayment.setInt(1, id);
	        psPayment.setInt(2, id);
	        psPayment.executeUpdate();

	        // 3. Xóa các bản ghi trong bảng Order liên quan đến ShipperID
	        String deleteOrderByShipperSQL = "DELETE FROM [Order] WHERE ShipperID = ?";
	        PreparedStatement psOrderByShipper = conn.prepareStatement(deleteOrderByShipperSQL);
	        psOrderByShipper.setInt(1, id);
	        psOrderByShipper.executeUpdate();

	        // 4. Xóa các bản ghi trong bảng Order liên quan đến CustomerID
	        String deleteOrderByCustomerSQL = "DELETE FROM [Order] WHERE CustomerID = ?";
	        PreparedStatement psOrderByCustomer = conn.prepareStatement(deleteOrderByCustomerSQL);
	        psOrderByCustomer.setInt(1, id);
	        psOrderByCustomer.executeUpdate();

	        // 5. Xóa các bản ghi trong bảng Cart liên quan đến CustomerID
	        String deleteCartSQL = "DELETE FROM [Cart] WHERE CustomerID = ?";
	        PreparedStatement psCart = conn.prepareStatement(deleteCartSQL);
	        psCart.setInt(1, id);
	        psCart.executeUpdate();

	        // 6. Xóa các bản ghi trong bảng Vouchercustomer liên quan đến CustomerID
	        String deleteVoucherCustomerSQL = "DELETE FROM [Vouchercustomer] WHERE CustomerID = ?";
	        PreparedStatement psVoucher = conn.prepareStatement(deleteVoucherCustomerSQL);
	        psVoucher.setInt(1, id);
	        psVoucher.executeUpdate();

	        // 7. Xóa các bản ghi trong bảng Account liên quan đến UserID
	        String deleteAccountSQL = "DELETE FROM [Account] WHERE UserID = ?";
	        PreparedStatement psAccount = conn.prepareStatement(deleteAccountSQL);
	        psAccount.setInt(1, id);
	        psAccount.executeUpdate();

	        // 8. Xóa user trong bảng USER
	        String deleteUserSQL = "DELETE FROM [USER] WHERE UserID = ?";
	        PreparedStatement psUser = conn.prepareStatement(deleteUserSQL);
	        psUser.setInt(1, id);
	        psUser.executeUpdate();

	        conn.close(); // Đóng kết nối
	    } catch (Exception e) {
	        e.printStackTrace(); // In ra lỗi nếu có
	    }

	}

	@Override
	public boolean insertShipper(UserModel model) {
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

}
