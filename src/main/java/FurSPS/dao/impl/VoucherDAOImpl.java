package FurSPS.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import FurSPS.configs.DBConnection;
import FurSPS.dao.IVoucherDAO;
import FurSPS.models.VoucherModel;

public class VoucherDAOImpl implements IVoucherDAO {
	Connection conn = null;

	@Override
	public List<VoucherModel> findAllVoucher() {
		String sql = "SELECT * FROM [VOUCHER]";
		List<VoucherModel> listVoucher = new ArrayList<VoucherModel>();
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				VoucherModel voucher = new VoucherModel();

				voucher.setVoucherID(rs.getInt("VoucherID"));
				voucher.setDescription(rs.getString("Description"));
				voucher.setDiscount(rs.getInt("Discount"));
				voucher.setMinimumPrice(rs.getInt("MinimumPrice"));
				voucher.setQuantity(rs.getInt("Quantity"));
				voucher.setMfg(rs.getDate("Mfg"));
				voucher.setExp(rs.getDate("Exp"));

				listVoucher.add(voucher);
			}
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listVoucher;
	}

	@Override
	public void insertVoucher(VoucherModel model) {
		String sql = "Insert into [VOUCHER](Description, Discount, MinimumPrice, Quantity, Mfg, Exp) Values (?,?,?,?,?,?)";
		try {
			conn = DBConnection.getConnection();// ket noi csdl
			PreparedStatement ps = conn.prepareStatement(sql);// nem cau lenh sql bang phat bieu prepare
			// gan gia tri tham so
			ps.setString(1, model.getDescription());
			ps.setInt(2, model.getDiscount());
			ps.setInt(3, model.getMinimumPrice());
			ps.setInt(4, model.getQuantity());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ps.setString(5, sdf.format(model.getMfg()));
			ps.setString(6, sdf.format(model.getExp()));

			ps.executeUpdate();// thuc thi cau query va tra ve Resultset
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateVoucher(VoucherModel model) {
		String sql = "Update [VOUCHER] set Description=?, Discount=?, MinimumPrice=?, Quantity=?, Mfg=?, Exp=? where VoucherID=?";
		try {
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			// gan gia tri tham so
			ps.setString(1, model.getDescription());
			ps.setInt(2, model.getDiscount());
			ps.setInt(3, model.getMinimumPrice());
			ps.setInt(4, model.getQuantity());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ps.setString(5, sdf.format(model.getMfg()));
			ps.setString(6, sdf.format(model.getExp()));
			ps.setInt(7, model.getVoucherID());

			ps.executeUpdate();// thuc thi cau query va tra ve Resultset
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public VoucherModel findOne(int id) {
		String sql = "Select * from [VOUCHER] where VoucherID=?";
		VoucherModel voucher = new VoucherModel();

		try {
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				voucher.setVoucherID(rs.getInt("VoucherID"));
				voucher.setDescription(rs.getString("Description"));
				voucher.setDiscount(rs.getInt("Discount"));
				voucher.setMinimumPrice(rs.getInt("MinimumPrice"));
				voucher.setQuantity(rs.getInt("Quantity"));
				voucher.setMfg(rs.getDate("Mfg"));
				voucher.setExp(rs.getDate("Exp"));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return voucher;
	}

	@Override
	public List<VoucherModel> findVoucherByCustomerID(int customerID) {
		List<VoucherModel> listVoucher = new ArrayList<VoucherModel>();
		String sql = "SELECT * FROM [VOUCHER] WHERE VoucherID "
				   + "NOT IN (SELECT VoucherID FROM [VOUCHERCUSTOMER] WHERE CustomerID = ? )";
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, customerID);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				VoucherModel voucher = new VoucherModel();

				voucher.setVoucherID(rs.getInt("VoucherID"));
				voucher.setDescription(rs.getString("Description"));
				voucher.setDiscount(rs.getInt("Discount"));
				voucher.setMinimumPrice(rs.getInt("MinimumPrice"));
				voucher.setQuantity(rs.getInt("Quantity"));
				voucher.setMfg(rs.getDate("Mfg"));
				voucher.setExp(rs.getDate("Exp"));

				listVoucher.add(voucher);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listVoucher;
	}

	@Override
	public VoucherModel findOneByCustomerID(int voucherID, int customerID) {
		Connection conn = null;
		String sql = "SELECT * FROM VOUCHER WHERE VoucherID = ?  "
				   + "AND VoucherID NOT IN ( SELECT VoucherID FROM VOUCHERCUSTOMER WHERE CustomerID = ? )";
		VoucherModel voucher = new VoucherModel();

		try {
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, voucherID);
			ps.setInt(2, customerID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				voucher.setVoucherID(rs.getInt("VoucherID"));
				voucher.setDescription(rs.getString("Description"));
				voucher.setDiscount(rs.getInt("Discount"));
				voucher.setMinimumPrice(rs.getInt("MinimumPrice"));
				voucher.setQuantity(rs.getInt("Quantity"));
				voucher.setMfg(rs.getDate("Mfg"));	
				voucher.setExp(rs.getDate("Exp"));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return voucher;
	}

	@Override
	public void deleteVoucher(int id) {		
		Connection conn = null;

	    try {
	        conn = DBConnection.getConnection(); // Kết nối cơ sở dữ liệu

	        // 1. Xóa các bản ghi trong bảng Vouchercustomer liên quan đến VoucherID
	        String deleteVoucherCustomerSQL = "DELETE FROM [Vouchercustomer] WHERE VoucherID = ?";
	        PreparedStatement psVoucherCustomer = conn.prepareStatement(deleteVoucherCustomerSQL);
	        psVoucherCustomer.setInt(1, id);
	        psVoucherCustomer.executeUpdate();

	        // 2. Xóa bản ghi trong bảng Voucher
	        String deleteVoucherSQL = "DELETE FROM [Voucher] WHERE VoucherID = ?";
	        PreparedStatement psVoucher = conn.prepareStatement(deleteVoucherSQL);
	        psVoucher.setInt(1, id);
	        psVoucher.executeUpdate();

	        conn.close(); // Đóng kết nối
	    } catch (Exception e) {
	        e.printStackTrace(); // In ra lỗi nếu có
	    }
		
	}

}
