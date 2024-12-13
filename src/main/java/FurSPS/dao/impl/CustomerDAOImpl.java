package FurSPS.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import FurSPS.configs.DBConnection;
import FurSPS.dao.ICustomerDAO;
import FurSPS.models.UserModel;

public class CustomerDAOImpl implements ICustomerDAO {

	@Override
	public List<UserModel> getAllCustomer() {
		String sql = "SELECT * FROM [USER] WHERE Type=0";
		List<UserModel> listCustomer = new ArrayList<>();
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserModel customer = new UserModel();
				customer.setUserID(rs.getInt(1));
				customer.setFirstName(rs.getString(2));
				customer.setLastName(rs.getString(3));
				customer.setAddress(rs.getString(4));
				customer.setGender(rs.getInt(5));
				customer.setPhone(rs.getString(6));
				customer.setDob(rs.getDate(7));
				customer.setCid(rs.getString(8));
				customer.setAvatar(rs.getString(9));
				customer.setType(rs.getInt(10));
				customer.setKpi(rs.getInt(11));
				customer.setArea(rs.getString(12));
				customer.setEmail(rs.getString(13));
				listCustomer.add(customer);
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return listCustomer;
	}

	@Override
	public UserModel getOneCustomer(int id) {
		String sql = "SELECT * FROM [USER] WHERE UserID = ?";
		UserModel customer = new UserModel();
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				customer.setUserID(rs.getInt(1));
				customer.setFirstName(rs.getString(2));
				customer.setLastName(rs.getString(3));
				customer.setAddress(rs.getString(4));
				customer.setGender(rs.getInt(5));
				customer.setPhone(rs.getString(6));
				customer.setDob(rs.getDate(7));
				customer.setCid(rs.getString(8));
				customer.setAvatar(rs.getString(9));
				customer.setType(rs.getInt(10));
				customer.setKpi(rs.getInt(11));
				customer.setArea(rs.getString(12));
				customer.setEmail(rs.getString(13));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return customer;
	}

	@Override
	public boolean insertCustomer(UserModel customer) {
		String sql = "INSERT INTO [USER] VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? , ? , ? , ? , ?)";
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, customer.getUserID());
			ps.setString(2, customer.getFirstName());
			ps.setString(3, customer.getLastName());
			ps.setString(4, customer.getAddress());
			ps.setInt(5, customer.getGender());
			ps.setString(6, customer.getPhone());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ps.setString(7, sdf.format(customer.getDob()));
			ps.setString(8, customer.getCid());
			ps.setString(9, customer.getAvatar());
			ps.setInt(10, customer.getType());
			ps.setInt(11,customer.getKpi());
			ps.setString(12, customer.getArea());
			ps.setString(13, customer.getEmail());
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

		return true;
	}

	@Override
	public boolean updateCustomer(UserModel customer) {
		String sql = "UPDATE [USER] SET FirstName = ?, LastName = ?, Address = ?, Gender = ?, "
				+ "Phone = ?, DoB = ?, Cid = ?, Email = ? WHERE UserID = ?";
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getAddress());
			ps.setInt(4, customer.getGender());
			ps.setString(5, customer.getPhone());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ps.setString(6, sdf.format(customer.getDob()));
			ps.setString(7, customer.getCid());
//			ps.setString(8, customer.getAvatar());
			ps.setString(8, customer.getEmail());
			ps.setInt(9, customer.getUserID());
			ps.executeUpdate();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteCustomer(UserModel customerMd) {
//		// 1. Xóa các bản ghi trong bảng ACCOUNT có liên quan đến UserID của khách hàng
//	    String deleteAccountSQL = "DELETE FROM [Account] WHERE UserID = ?";
//	    try {
//	        new DBConnection();
//	        Connection conn = DBConnection.getConnection();
//	        PreparedStatement psAccount = conn.prepareStatement(deleteAccountSQL);
//	        psAccount.setInt(1, customerMd.getUserID());
//	        psAccount.executeUpdate();  // Xóa tài khoản trong bảng Account
//
//	        // 2. Xóa khách hàng trong bảng USER
//	        String deleteUserSQL = "DELETE FROM [USER] WHERE UserID = ?";
//	        PreparedStatement psUser = conn.prepareStatement(deleteUserSQL);
//	        psUser.setInt(1, customerMd.getUserID());
//	        psUser.executeUpdate();  // Xóa khách hàng trong bảng USER
//
//	        conn.close();
//	    } catch (Exception e) {
//	        System.out.println(e);
//	        return false;
//	    }
//	    return true;
		
		Connection conn = null;

	    try {
	        conn = DBConnection.getConnection(); // Kết nối cơ sở dữ liệu

	        // 1. Xóa các bản ghi trong bảng SearchHistory liên quan đến CustomerID
	        String deleteSearchHistorySQL = "DELETE FROM [SearchHistory] WHERE CustomerID = ?";
	        PreparedStatement psSearchHistory = conn.prepareStatement(deleteSearchHistorySQL);
	        psSearchHistory.setInt(1, customerMd.getUserID());
	        psSearchHistory.executeUpdate();

	        // 2. Xóa các bản ghi trong bảng Detail liên quan đến Order của CustomerID
	        String deleteDetailSQL = "DELETE FROM [Detail] WHERE OrderID IN (SELECT OrderID FROM [Order] WHERE CustomerID = ?)";
	        PreparedStatement psDetail = conn.prepareStatement(deleteDetailSQL);
	        psDetail.setInt(1, customerMd.getUserID());
	        psDetail.executeUpdate();

	        // 3. Xóa các bản ghi trong bảng Payment liên quan đến các Order của CustomerID
	        String deletePaymentSQL = "DELETE FROM [Payment] WHERE OrderID IN (SELECT OrderID FROM [Order] WHERE CustomerID = ?)";
	        PreparedStatement psPayment = conn.prepareStatement(deletePaymentSQL);
	        psPayment.setInt(1, customerMd.getUserID());
	        psPayment.executeUpdate();

	        // 4. Xóa các bản ghi trong bảng Order liên quan đến CustomerID
	        String deleteOrderSQL = "DELETE FROM [Order] WHERE CustomerID = ?";
	        PreparedStatement psOrder = conn.prepareStatement(deleteOrderSQL);
	        psOrder.setInt(1, customerMd.getUserID());
	        psOrder.executeUpdate();

	        // 5. Xóa các bản ghi trong bảng Cart liên quan đến CustomerID
	        String deleteCartSQL = "DELETE FROM [Cart] WHERE CustomerID = ?";
	        PreparedStatement psCart = conn.prepareStatement(deleteCartSQL);
	        psCart.setInt(1, customerMd.getUserID());
	        psCart.executeUpdate();

	        // 6. Xóa các bản ghi trong bảng Account liên quan đến UserID
	        String deleteAccountSQL = "DELETE FROM [Account] WHERE UserID = ?";
	        PreparedStatement psAccount = conn.prepareStatement(deleteAccountSQL);
	        psAccount.setInt(1, customerMd.getUserID());
	        psAccount.executeUpdate();

	        // 7. Xóa các bản ghi trong bảng Vouchercustomer nếu customer có liên quan
	        String deleteVoucherCustomerSQL = "DELETE FROM [Vouchercustomer] WHERE CustomerID = ?";
	        PreparedStatement psVoucher = conn.prepareStatement(deleteVoucherCustomerSQL);
	        psVoucher.setInt(1, customerMd.getUserID());
	        psVoucher.executeUpdate();

	        // 8. Xóa khách hàng trong bảng USER
	        String deleteUserSQL = "DELETE FROM [USER] WHERE UserID = ?";
	        PreparedStatement psUser = conn.prepareStatement(deleteUserSQL);
	        psUser.setInt(1, customerMd.getUserID());
	        psUser.executeUpdate();

	        conn.close(); // Đóng kết nối
	    } catch (Exception e) {
	        System.out.println(e);
	        return false; // Trả về false nếu có lỗi xảy ra
	    }
	    return true; // Trả về true nếu xóa thành công
	}

}
