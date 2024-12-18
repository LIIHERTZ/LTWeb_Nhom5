	package FurSPS.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import FurSPS.dao.IAccountDAO;
import FurSPS.models.AccountModel;

import FurSPS.configs.DBConnection;
public class AccountDAOImpl implements IAccountDAO{

	@Override
	public List<AccountModel> getAllAccount() {
		String sql = "SELECT * FROM ACCOUNT";
		List<AccountModel> listAccount = new ArrayList<>();
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{
				AccountModel account = new AccountModel();
				account.setUserID(rs.getInt(1));
				account.setUserName(rs.getString(2));
				account.setPassword(rs.getString(3));
				listAccount.add(account);
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}		
		return listAccount;
	}

	@Override
	public AccountModel getOneAccount(int id) {
		String sql = "SELECT * FROM ACCOUNT WHERE USERID=?";
		AccountModel account = new AccountModel();
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{				
				account.setUserID(rs.getInt(1));
				account.setUserName(rs.getString(2));
				account.setPassword(rs.getString(3));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}		
		return account;
	}

	@Override
	public boolean insertAccount(AccountModel account) {
		String sql = "INSERT INTO ACCOUNT VALUES (?, ?,?);";
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, account.getUserID());
			ps.setString(2, account.getUserName());
			ps.setString(3,account.getPassword());
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean updateAccount(AccountModel account) {
		String sql = "UPDATE ACCOUNT SET [UserName] = ?, [Password] = ? WHERE [UserID] = ?";
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, account.getUserName());
			ps.setString(2,account.getPassword());
			ps.setInt(3, account.getUserID());
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteAccount(AccountModel account) {
		String sql = "DELETE FROM ACCOUNT WHERE (UserID = ?)";
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, account.getUserID());
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	@Override
	public int getTypeAccount(int id) {
		String sql = "SELECT Type FROM USER WHERE USERID=?";
		int type = -1;
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				type = rs.getInt("type");
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return type;
	}

	@Override
	public AccountModel findByUsernameAndPass(String username, String password) {
		String sql = "SELECT * FROM ACCOUNT WHERE UserName=? AND Password=?";
		AccountModel account = new AccountModel();
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{				
				account.setUserID(rs.getInt(1));
				account.setUserName(rs.getString(2));
				account.setPassword(rs.getString(3));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}		
		return account;
	}

	@Override
	public AccountModel findByUsername(String username) {
		String sql = "SELECT * FROM ACCOUNT WHERE UserName=?";
		AccountModel account = new AccountModel();
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{				
				account.setUserID(rs.getInt(1));
				account.setUserName(rs.getString(2));
				account.setPassword(rs.getString(3));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}		
		return account;
	}
	
	@Override
	public AccountModel findByEmail(String email) {
		String sql = "SELECT * FROM ACCOUNT JOIN [USER] ON ACCOUNT.UserID = [USER].UserID WHERE [USER].Email =?";
		AccountModel account = new AccountModel();
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{				
				account.setUserID(rs.getInt(1));
				account.setUserName(rs.getString(2));
				account.setPassword(rs.getString(3));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return account;
	}

	@Override
	public boolean isUserExist(int userID) {
		// Truy vấn kiểm tra sự tồn tại của userID trong bảng User
	    String sql = "SELECT COUNT(*) FROM [USER] WHERE UserID = ?";
	    try {
	        // Tạo kết nối cơ sở dữ liệu
	        new DBConnection();
	        Connection conn = DBConnection.getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, userID);  // Gắn giá trị cho tham số
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            // Kiểm tra nếu số lượng người dùng tìm thấy lớn hơn 0
	            return rs.getInt(1) > 0;
	        }

	        conn.close();
	    } catch (Exception e) {
	        System.out.println("Error in isUserExist: " + e.getMessage());
	    }
	    return false;
	}

	@Override
	public boolean isUserExistInAccount(int userID) {
		String sql = "SELECT COUNT(*) FROM ACCOUNT JOIN [USER] ON ACCOUNT.UserID = [USER].UserID WHERE [USER].UserID = ?";
	    try {
	        // Tạo kết nối cơ sở dữ liệu
	        new DBConnection();
	        Connection conn = DBConnection.getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, userID);  // Gắn giá trị cho tham số
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            // Kiểm tra nếu số lượng người dùng tìm thấy lớn hơn 0
	            return rs.getInt(1) > 0;
	        }

	        conn.close();
	    } catch (Exception e) {
	        System.out.println("Error in isUserExist: " + e.getMessage());
	    }
	    return false;
	}
	

}
