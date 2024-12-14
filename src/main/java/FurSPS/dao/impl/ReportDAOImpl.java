package FurSPS.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import FurSPS.models.submodels.MyItem;
import FurSPS.models.submodels.Top3Customer;
import FurSPS.configs.DBConnection;
import FurSPS.dao.IReportDAO;

public class ReportDAOImpl implements IReportDAO {

	@Override
	public List<MyItem> reportReceipt(Date date, int limit) {
		List<MyItem> list = new ArrayList<>();
		for (int i = limit - 1; i >= 0; i--) {
			Date d = subDays(date, i);
			MyItem myItem = new MyItem();
			myItem.setTime(covertD2S(d));
			myItem.setValue(countItemByDate(d));
			list.add(myItem);
		}
		return list;
	}

	private int countItemByDate(Date d) {
//		String sql = "SELECT COUNT(*) FROM ORDER WHERE DATE_FORMAT(OrderDate, '%Y-%m-%d') = ?";
		String sql = "SELECT COUNT(*) FROM [ORDER] WHERE CONVERT(varchar, OrderDate, 23) = ?";
		int itemCount = 0;
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(d.getTime()));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				itemCount = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemCount;
	}

	private String covertD2S(Date d) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyy");
		return df.format(d);
	}

	private Date subDays(Date date, int i) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -i);
		return cal.getTime();
	}

	@Override
	public List<List<Object>> reportTotalMoneyInMonth() {
		String sql = "SELECT  " 
				+ "   OrderDate AS Ngay, " 
				+ "    SUM(TotalMoney) AS Tong, "
				+ "    COUNT(OrderID) AS SL " 
				+ "FROM [ORDER] WHERE MONTH(OrderDate) = MONTH(GETDATE()) AND YEAR(OrderDate) = YEAR(GETDATE()) " + "GROUP BY OrderDate " 
				+ "ORDER BY OrderDate   ";

		List<List<Object>> list = new ArrayList<List<Object>>();
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				List<Object> row = new ArrayList<Object>();
				row.add(rs.getDate("Ngay"));
				row.add(rs.getLong("Tong"));
				row.add(rs.getInt("SL"));
				list.add(row);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<Top3Customer> reportTop3Customer() {

		
		// Câu lệnh SQL đã được sửa lại cho SQL Server
	    String sql = "SELECT TOP 3 "
	               + "   U.UserID, "
	               + "   U.FirstName, "
	               + "   U.LastName, "
	               + "   SUM(O.TotalMoney) AS TotalMoney "
	               + "FROM [ORDER] O "
	               + "JOIN [USER] U ON U.UserID = O.CustomerID "
	               + "GROUP BY U.UserID, U.FirstName, U.LastName "
	               + "ORDER BY TotalMoney DESC";
		
		List<Top3Customer> list = new ArrayList<Top3Customer>();
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Top3Customer top3 = new Top3Customer();
				top3.setId(rs.getInt(1));
				top3.setFirstName(rs.getString(2));
				top3.setLastName(rs.getString(3));
				top3.setTotalMoney(rs.getLong(4));
				list.add(top3);
			}
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<MyItem> reportKPISeller(Date date, int id) {
		List<MyItem> list = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
		for (int i = 1; i <= 12; i++) {					
			MyItem myItem = new MyItem();
			myItem.setTime(covertMY(i,year));
			myItem.setValue(countItemByMonth(year,i,id));
			list.add(myItem);
		}
		return list;
	}
	private String covertMY(int month, int year) {
		 // Adjust the month since Calendar.MONTH is zero-based
	    month--;

	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.MONTH, month);
	    calendar.set(Calendar.YEAR, year);
	    Date modifiedDate = calendar.getTime();
	    // Use SimpleDateFormat to format the date
	    DateFormat df = new SimpleDateFormat("MM/yyyy");
	    String formattedDate = df.format(modifiedDate);
	    return formattedDate;
	}
	private int countItemByMonth(int year, int month, int id) {
		String sql = "SELECT COUNT(*)\r\n"
				+ "FROM [ORDER]\r\n"
				+ "JOIN [DETAIL] ON [ORDER].OrderID = [DETAIL].OrderID\r\n"
				+ "WHERE FORMAT([ORDER].OrderDate, 'yyyy-MM') = ? and SellerID = ? and Status = 4";

		int itemCount = 0;
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, String.format("%d-%02d", year, month));
			ps.setInt(2, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				itemCount = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemCount;
	}
	
	@Override
	public List<MyItem> reportBestItemSeller(int id) {
		List<MyItem> list = new ArrayList<>();
		String sql ="SELECT TOP 5 [PRODUCT].ProductID, [PRODUCT].ProductName, COUNT([ORDER].OrderID) AS OrderCount "
				+ "FROM [ORDER] "
				+ "JOIN [DETAIL] ON [ORDER].OrderID = [DETAIL].OrderID "
				+ "join [ITEM] on [DETAIL].ItemID = [ITEM].ItemID "
				+ "join [PRODUCT] on [ITEM].ProductID = [PRODUCT].ProductID "
				+ "WHERE [ORDER].SellerID = ? AND [ORDER].Status=4 "
				+ "GROUP BY [PRODUCT].ProductID, [PRODUCT].ProductName "
				+ "ORDER BY OrderCount DESC";
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MyItem item = new MyItem();	
				item.setItem(rs.getString("ProductName"));
				item.setValue(rs.getInt("OrderCount"));			
				list.add(item);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
    public List<List<Object>> reportSellerOrderByYear(int sellerID) {
        String sql
        = "SELECT FORMAT(OrderDate, 'yyyy-MM') AS Thang, SUM(TotalMoney) AS Tong, COUNT(OrderID) AS SL FROM [ORDER] WHERE YEAR(OrderDate) = YEAR(GETDATE()) AND SellerID = ? GROUP BY FORMAT(OrderDate, 'yyyy-MM') ORDER BY FORMAT(OrderDate, 'yyyy-MM')";
        List<List<Object>> list = new ArrayList<List<Object>>();
        try {
            new DBConnection();
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, sellerID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List<Object> row = new ArrayList<Object>();
                row.add(rs.getString("Thang"));
                row.add(rs.getLong("Tong"));
                row.add(rs.getInt("SL"));
                list.add(row);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
