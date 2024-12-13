package FurSPS.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import FurSPS.configs.DBConnection;
import FurSPS.dao.IAddressDAO;
import FurSPS.models.AccountModel;
import FurSPS.models.AddressModel;

public class AddressDAOImpl implements IAddressDAO{

	@Override
	public List<AddressModel> findByCustomerId(int userID) {
		String sql = "SELECT * FROM ADDRESS WHERE USERID = ?";
		List<AddressModel> listAddress = new ArrayList<>();
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{
				AddressModel address = new AddressModel();
				address.setUserID(rs.getInt(1));
				address.setName(rs.getString(2));
				address.setPhone(rs.getString(3));
				address.setCity(rs.getString(4));
				address.setDetail(rs.getString(5));
				listAddress.add(address);
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}		
		return listAddress;
	}
	@Override
	public AddressModel findExistAddress(int userID, String city, String detail) {
		String sql = "SELECT * FROM ADDRESS WHERE UserID=? AND City=? AND Detail=?";
		AddressModel addressModel = new AddressModel();
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			ps.setString(2, city);
			ps.setString(3, detail);
			ResultSet rs= ps.executeQuery();
			if(rs.next())
			{				
				addressModel.setUserID(rs.getInt("UserId"));
	            addressModel.setName(rs.getString("Name"));
	            addressModel.setPhone(rs.getString("Phone"));
	            addressModel.setCity(rs.getString("City"));
	            addressModel.setDetail(rs.getString("Detail"));

			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}		
		return addressModel;
	}
	@Override
	public void addAddress(AddressModel address) {
		String sql = "INSERT INTO ADDRESS VALUES (?, ? , ? , ? , ? );";
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, address.getUserID());
			ps.setString(2, address.getName());
			ps.setString(3, address.getPhone());
			ps.setString(4,address.getCity());
			ps.setString(5,address.getDetail());
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}	
	}
	@Override
	public void updateAddress(AddressModel address) {
		String sql = "UPDATE ADDRESS SET Name = ?, Phone = ? WHERE UserID = ? AND City = ? AND Detail = ?";
		try {

			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, address.getName());
			ps.setString(2, address.getPhone());
			ps.setString(4,address.getCity());
			ps.setString(5,address.getDetail());
			ps.setInt(3, address.getUserID());
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	@Override
	public void deleteAddress(int userID, String city, String detail) {
		String sql = "DELETE FROM ADDRESS WHERE UserID = ? AND City =? AND Detail =?";
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			ps.setString(2, city);
			ps.setString(3, detail);
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
