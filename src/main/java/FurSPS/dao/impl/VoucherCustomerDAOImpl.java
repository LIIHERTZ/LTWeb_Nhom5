package FurSPS.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import FurSPS.configs.DBConnection;
import FurSPS.dao.IVoucherCustomerDAO;

public class VoucherCustomerDAOImpl implements IVoucherCustomerDAO {
	Connection conn = null;

	@Override
	public void insertVoucherCustomer(int voucherId, int customerId) {
		String sql = "Insert into [VOUCHERCUSTOMER](VoucherID, CustomerID) Values (?,?)";
		try {
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, voucherId);
			ps.setInt(2,customerId);
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
