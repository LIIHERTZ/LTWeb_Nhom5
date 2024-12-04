package FurSPS.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

import FurSPS.configs.DBConnection;
import FurSPS.dao.IDetailDAO;
import FurSPS.dao.IOrderDAO;
import FurSPS.models.OrderModel;
import FurSPS.models.PaymentModel;
import FurSPS.models.UserModel;
import FurSPS.other.Assignment;

public class OrderDAOImpl implements IOrderDAO {

	IDetailDAO detailDAO = new DetailDAOImpl();
	Connection conn = null;

	@Override
	public void updateOrder(OrderModel order) {
		String sql = "UPDATE [ORDER] SET OrderDate = ? , Address = ? , Status = ? , TransportFee = ? , Discount = ? , TotalMoney = ? , Note = ? , DeliveryTime = ? , CustomerConfirmation = ? , CustomerID = ? , SellerID = ? , ShipperID = ? WHERE (OrderID = ? )";
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			ps.setString(1, formatter.format(order.getOrderDate()));
			ps.setString(2, order.getAddress());
			ps.setInt(3, order.getStatus());
			ps.setInt(4, order.getTransportFee());
			ps.setInt(5, order.getDiscount());
			ps.setInt(6, order.getTotalMoney());
			ps.setString(7, order.getNote());
			if (order.getDeliveryTime() != null) ps.setString(8, formatter.format(order.getDeliveryTime()));
			else ps.setString(8, null);
			ps.setInt(9, order.getCustomerConfirmation());
			ps.setInt(10, order.getCustomerID());
			ps.setInt(11, order.getSellerID());
			ps.setInt(12, order.getShipperID());
			ps.setInt(13, order.getOrderID());
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	@Override
	public List<OrderModel> findAllOrder() {
		List<OrderModel> listOrder = new ArrayList<OrderModel>();
//		String sql = "SELECT DISTINCT k.*,p.Method as Method,p.Time as TimePay, p.Bank as Bank, p.CardOwner as CardOwner, p.AccountNumber as AccountNumber, p.Status as StatusPay "
//				+ "FROM PAYMENT AS p " + "INNER JOIN ( "
//				+ "    SELECT DISTINCT o.*, c.FirstName AS FirstNameCustomer, c.LastName AS LastNameCustomer, c.Phone AS PhoneCustomer, c.CID AS CIDCustomer, "
//				+ "		 c.Email AS EmailCustomer,  c.Avatar AS AvatarCustomer, "
//				+ "        se.FirstName AS FirstNameSeller, se.LastName AS LastNameSeller, se.Phone AS PhoneSeller, se.CID AS CIDSeller, "
//				+ "		 se.Email AS EmailSeller, se.Avatar as AvatarSeller, "
//				+ "        sh.FirstName AS FirstNameShipper, sh.LastName AS LastNameShipper, sh.Phone AS PhoneShipper, sh.CID AS CIDShipper, "
//				+ "		 sh.Email AS EmailShipper, sh.Avatar AS AvatarShipper " + "    FROM FurSPS.`ORDER` o "
//				+ "    LEFT JOIN ( "
//				+ "        SELECT DISTINCT USER.UserID, USER.FirstName, USER.LastName , USER.Phone, USER.CID, USER.Email, USER.Avatar "
//				+ "        FROM FurSPS.`ORDER` " + "        INNER JOIN USER ON `ORDER`.CustomerID = USER.UserID "
//				+ "    ) AS c ON o.CustomerID = c.UserID " + "    LEFT JOIN ( "
//				+ "        SELECT DISTINCT USER.UserID, USER.FirstName, USER.LastName, USER.Phone, USER.CID, USER.Email, USER.Avatar "
//				+ "        FROM FurSPS.`ORDER` " + "        INNER JOIN USER ON `ORDER`.SellerID = USER.UserID "
//				+ "    ) AS se ON o.SellerID = se.UserID " + "    LEFT JOIN ( "
//				+ "        SELECT DISTINCT USER.UserID, USER.FirstName, USER.LastName, USER.Phone, USER.CID, USER.Email, USER.Avatar "
//				+ "        FROM FurSPS.`ORDER` " + "        INNER JOIN USER ON `ORDER`.ShipperID = USER.UserID "
//				+ "    ) AS sh ON o.ShipperID = sh.UserID " + ") AS k ON p.OrderID = k.OrderID;";
		
		String sql = "SELECT DISTINCT k.*, " 
	            + "p.Method AS Method, " 
	            + "p.Time AS TimePay, " 
	            + "p.Bank AS Bank, " 
	            + "p.CardOwner AS CardOwner, " 
	            + "p.AccountNumber AS AccountNumber, " 
	            + "p.Status AS StatusPay " 
	            + "FROM [PAYMENT] AS p " 
	            + "INNER JOIN ( " 
	            + "    SELECT DISTINCT o.*, " 
	            + "                c.FirstName AS FirstNameCustomer, " 
	            + "                c.LastName AS LastNameCustomer, " 
	            + "                c.Phone AS PhoneCustomer, " 
	            + "                c.CID AS CIDCustomer, " 
	            + "                c.Email AS EmailCustomer,  " 
	            + "                c.Avatar AS AvatarCustomer, " 
	            + "                se.FirstName AS FirstNameSeller, " 
	            + "                se.LastName AS LastNameSeller, " 
	            + "                se.Phone AS PhoneSeller, " 
	            + "                se.CID AS CIDSeller, " 
	            + "                se.Email AS EmailSeller, " 
	            + "                se.Avatar AS AvatarSeller, " 
	            + "                sh.FirstName AS FirstNameShipper, " 
	            + "                sh.LastName AS LastNameShipper, " 
	            + "                sh.Phone AS PhoneShipper, " 
	            + "                sh.CID AS CIDShipper, " 
	            + "                sh.Email AS EmailShipper, " 
	            + "                sh.Avatar AS AvatarShipper " 
	            + "    FROM [ORDER] o " 
	            + "    LEFT JOIN ( " 
	            + "        SELECT DISTINCT [USER].UserID, " 
	            + "                        [USER].FirstName, " 
	            + "                        [USER].LastName, " 
	            + "                        [USER].Phone, " 
	            + "                        [USER].CID, " 
	            + "                        [USER].Email, " 
	            + "                        [USER].Avatar " 
	            + "        FROM [ORDER] " 
	            + "        INNER JOIN [USER] ON [ORDER].CustomerID = [USER].UserID " 
	            + "    ) AS c ON o.CustomerID = c.UserID " 
	            + "    LEFT JOIN ( " 
	            + "        SELECT DISTINCT [USER].UserID, " 
	            + "                        [USER].FirstName, " 
	            + "                        [USER].LastName, " 
	            + "                        [USER].Phone, " 
	            + "                        [USER].CID, " 
	            + "                        [USER].Email, " 
	            + "                        [USER].Avatar " 
	            + "        FROM [ORDER] " 
	            + "        INNER JOIN [USER] ON [ORDER].SellerID = [USER].UserID " 
	            + "    ) AS se ON o.SellerID = se.UserID " 
	            + "    LEFT JOIN ( " 
	            + "        SELECT DISTINCT [USER].UserID, " 
	            + "                        [USER].FirstName, " 
	            + "                        [USER].LastName, " 
	            + "                        [USER].Phone, " 
	            + "                        [USER].CID, " 
	            + "                        [USER].Email, " 
	            + "                        [USER].Avatar " 
	            + "        FROM [ORDER] " 
	            + "        INNER JOIN [USER] ON [ORDER].ShipperID = [USER].UserID " 
	            + "    ) AS sh ON o.ShipperID = sh.UserID " 
	            + ") AS k ON p.OrderID = k.OrderID;";
		//Where Year(OrderDate)= Year(GETDATE()) ;

		
		
		
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				OrderModel order = new OrderModel();
				UserModel customer = new UserModel();
				UserModel seller = new UserModel();
				UserModel shipper = new UserModel();
				PaymentModel pay = new PaymentModel();
				order.setOrderID(rs.getInt("OrderID"));
				order.setOrderDate(rs.getDate("OrderDate"));
				order.setAddress(rs.getString("Address"));
				order.setStatus(rs.getInt("Status"));
				order.setTransportFee(rs.getInt("TransportFee"));
				order.setDiscount(rs.getInt("Discount"));
				order.setTotalMoney(rs.getInt("TotalMoney"));
				order.setNote(rs.getString("Note"));
				order.setDeliveryTime(rs.getDate("DeliveryTime"));
				order.setCustomerConfirmation(rs.getInt("CustomerConfirmation"));
				order.setCustomerID(rs.getInt("CustomerID"));
				order.setSellerID(rs.getInt("SellerID"));
				order.setShipperID(rs.getInt("ShipperID"));
				customer.setLastName(rs.getString("LastNameCustomer"));
				customer.setFirstName(rs.getString("FirstNameCustomer"));
				customer.setCid(rs.getString("CIDCustomer"));
				customer.setPhone(rs.getString("PhoneCustomer"));
				customer.setEmail(rs.getString("EmailCustomer"));
				customer.setAvatar(rs.getString("AvatarCustomer"));

				seller.setLastName(rs.getString("LastNameSeller"));
				seller.setFirstName(rs.getString("FirstNameSeller"));
				seller.setCid(rs.getString("CIDSeller"));
				seller.setPhone(rs.getString("PhoneSeller"));
				seller.setEmail(rs.getString("EmailSeller"));
				seller.setAvatar(rs.getString("AvatarSeller"));

				shipper.setLastName(rs.getString("LastNameShipper"));
				shipper.setFirstName(rs.getString("FirstNameShipper"));
				shipper.setCid(rs.getString("CIDShipper"));
				shipper.setPhone(rs.getString("PhoneShipper"));
				shipper.setEmail(rs.getString("EmailShipper"));
				shipper.setAvatar(rs.getString("AvatarShipper"));

				pay.setOrderID(rs.getInt("OrderID"));
				pay.setMethod(rs.getInt("Method"));
				pay.setBank(rs.getString("Bank"));
				pay.setTime(rs.getTimestamp("TimePay"));
				pay.setCardOwner(rs.getString("CardOwner"));
				pay.setAccountNumber(rs.getString("AccountNumber"));
				pay.setStatus(rs.getInt("StatusPay"));

				order.setCustomer(customer);
				order.setSeller(seller);
				order.setShipper(shipper);
				order.setPayment(pay);
				listOrder.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOrder;
	}

	@Override
	public void deleteOrder(int orderID) {
		String sql = "DELETE FROM ORDER WHERE (OrderID = ? )";
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, orderID);
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateStatusOrder(int orderID, int status) {
		String sql = "UPDATE ORDER SET Status = ? WHERE OrderID = ?";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, status);
			ps.setInt(2, orderID);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<OrderModel> listOrderByCustomerID(int customerID) {
		List<OrderModel> listOrder = new ArrayList<OrderModel>();
		String sql = "SELECT O.OrderID, O.CustomerID, O.OrderDate, O.Status, O.Discount, O.TotalMoney, O.SellerID, O.ShipperID, O.CustomerConfirmation "
				+ "FROM ORDER O " + "WHERE O.CustomerID = ?";
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, customerID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				OrderModel order = new OrderModel();
				order.setOrderID(rs.getInt(1));
				order.setCustomerID(rs.getInt(2));
				order.setOrderDate(rs.getDate(3));
				order.setStatus(rs.getInt(4));
				order.setDiscount(rs.getInt(5));
				order.setTotalMoney(rs.getInt(6));
				order.setSellerID(rs.getInt(7));
				order.setShipperID(rs.getInt(8));
				order.setCustomerConfirmation(rs.getInt(9));
				listOrder.add(order);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOrder;
	}

	@Override
	public void confirmOrder(int orderID, int confirm) {
		String sql = "UPDATE ORDER SET CustomerConfirmation = ? WHERE OrderID = ?";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, confirm);
			ps.setInt(2, orderID);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public OrderModel getOrderByOrderID(int orderID) {
		OrderModel order = new OrderModel();
		String sql = "SELECT o.*, FirstName, LastName, Phone, p.CardOwner,p.Bank,p.AccountNumber,p.Time as TimePay,	 p.Method, p.Status as PayStatus    "
				+ " FROM ORDER as o   LEFT JOIN PAYMENT as p ON o.OrderID = p.OrderID INNER JOIN USER as c ON  o.CustomerID = c.UserID  "
				+ " WHERE o.OrderID=?";
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, orderID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				order.setOrderID(rs.getInt("OrderID"));
				order.setOrderDate(rs.getDate("OrderDate"));
				order.setAddress(rs.getString("Address"));
				order.setStatus(rs.getInt("Status"));
				order.setTransportFee(rs.getInt("TransportFee"));
				order.setTotalMoney(rs.getInt("TotalMoney"));
				order.setNote(rs.getString("Note"));
				order.setDiscount(rs.getInt("Discount"));
				// order.setCustomerID(rs.getInt("CustomerID"));
				order.setSellerID(rs.getInt("SellerID"));
				order.setShipperID(rs.getInt("ShipperID"));
				order.getCustomer().setFirstName(rs.getString("FirstName"));
				order.getCustomer().setLastName(rs.getString("LastName"));
				order.getCustomer().setPhone(rs.getString("Phone"));

				order.getPayment().setMethod(rs.getInt("Method"));
				order.getPayment().setStatus(rs.getInt("PayStatus"));
				order.getPayment().setBank(rs.getString("Bank"));
				order.getPayment().setCardOwner(rs.getString("CardOwner"));
				order.getPayment().setTime(rs.getTimestamp("TimePay"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public List<OrderModel> findHisOrder(int sellerID) {
		List<OrderModel> listOrder = new ArrayList<OrderModel>();
		String sql = "SELECT o.*, FirstName, LastName, Phone, " + "	   p.Method, p.Status as PayStatus "
				+ "	   FROM ORDER as o " + "    LEFT JOIN PAYMENT as p ON o.OrderID = p.OrderID "
				+ "    INNER JOIN USER as c ON  o.CustomerID = c.UserID " + "WHERE o.Status <> 0 AND SellerID=?;";
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, sellerID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				OrderModel order = new OrderModel();
				order.setOrderID(rs.getInt("OrderID"));
				order.setOrderDate(rs.getDate("OrderDate"));
				order.setAddress(rs.getString("Address"));
				order.setStatus(rs.getInt("Status"));
				order.setTransportFee(rs.getInt("TransportFee"));
				order.setTotalMoney(rs.getInt("TotalMoney"));
				order.setNote(rs.getString("Note"));
				// order.setDiscount(rs.getInt("Discount"));
				// order.setCustomerID(rs.getInt("CustomerID"));
				order.setSellerID(rs.getInt("SellerID"));
				order.setShipperID(rs.getInt("ShipperID"));
				order.getCustomer().setFirstName(rs.getString("FirstName"));
				order.getCustomer().setLastName(rs.getString("LastName"));
				// order.getCustomer().setPhone(rs.getString("Phone"));
				order.getPayment().setMethod(rs.getInt("Method"));
				order.getPayment().setStatus(rs.getInt("PayStatus"));
				listOrder.add(order);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOrder;
	}

	@Override
	public List<OrderModel> findOrderBySeller() {
		List<OrderModel> listOrder = new ArrayList<OrderModel>();
		String sql = "SELECT o.*, FirstName, LastName, Phone, " + "	   p.Method, p.Status as PayStatus "
				+ "	   FROM ORDER as o " + "    LEFT JOIN PAYMENT as p ON o.OrderID = p.OrderID "
				+ "    INNER JOIN USER as c ON  o.CustomerID = c.UserID " + "WHERE o.Status = 0 or o.Status=1;";
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			// ps.setInt(1, customerID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				OrderModel order = new OrderModel();
				order.setOrderID(rs.getInt("OrderID"));
				order.setOrderDate(rs.getDate("OrderDate"));
				order.setAddress(rs.getString("Address"));
				order.setStatus(rs.getInt("Status"));
				order.setTransportFee(rs.getInt("TransportFee"));
				order.setTotalMoney(rs.getInt("TotalMoney"));
				order.setNote(rs.getString("Note"));
				// order.setDiscount(rs.getInt("Discount"));
				// order.setCustomerID(rs.getInt("CustomerID"));
				order.setSellerID(rs.getInt("SellerID"));
				order.setShipperID(rs.getInt("ShipperID"));
				order.getCustomer().setFirstName(rs.getString("FirstName"));
				order.getCustomer().setLastName(rs.getString("LastName"));
				// order.getCustomer().setPhone(rs.getString("Phone"));
				order.getPayment().setMethod(rs.getInt("Method"));
				order.getPayment().setStatus(rs.getInt("PayStatus"));
				listOrder.add(order);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOrder;
	}

	@Override
	public void updateStatusOrder(int OrderID, int sellerID, int status) {
		String sql = "update ORDER set status=? , SellerID=? where OrderID= ?";
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setInt(2, sellerID);
			ps.setInt(3, OrderID);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public OrderModel findByOrderID(int orderID) {
		String sql = "SELECT * FROM ORDER where orderID = ? ";
		OrderModel order = new OrderModel();
		try {
			new DBConnection();
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, orderID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				order.setOrderID(rs.getInt("OrderID"));
				order.setOrderDate(rs.getDate("OrderDate"));
				order.setAddress(rs.getString("Address"));
				order.setStatus(rs.getInt("Status"));
				order.setTransportFee(rs.getInt("TransportFee"));
				order.setDiscount(rs.getInt("Discount"));
				order.setTotalMoney(rs.getInt("TotalMoney"));
				order.setNote(rs.getString("Note"));
				order.setDeliveryTime(rs.getDate("DeliveryTime"));
				order.setCustomerConfirmation(rs.getInt("CustomerConfirmation"));
				order.setCustomerID(rs.getInt("CustomerID"));
				order.setSellerID(rs.getInt("SellerID"));
				order.setShipperID(rs.getInt("ShipperID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public OrderModel getOrderByID(int orderID) {
		OrderModel order = new OrderModel();
		String sql =  "SELECT O.OrderID, O.CustomerID, O.OrderDate, O.Status, O.CustomerConfirmation, O.Discount, O.TotalMoney, O.SellerID, O.ShipperID, O.TransportFee "
					+ "FROM ORDER O "
					+ "WHERE O.OrderID = ?";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, orderID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				order.setOrderID(rs.getInt(1));
				order.setCustomerID(rs.getInt(2));
				order.setOrderDate(rs.getDate(3));
				order.setStatus(rs.getInt(4));
				order.setCustomerConfirmation(rs.getInt(5));
				order.setDiscount(rs.getInt(6));
				order.setTotalMoney(rs.getInt(7));
				order.setSellerID(rs.getInt(8));
				order.setShipperID(rs.getInt(9));
				order.setTransportFee(rs.getInt(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public OrderModel insertOrder(OrderModel order) {
		String sql = "INSERT INTO ORDER " + "(OrderDate, Address, City, Status, TransportFee, "
				+ "Discount, TotalMoney, Note, DeliveryTime, CustomerConfirmation, CustomerID) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(order.getOrderDate().getTime()));
			ps.setString(2, order.getAddress());
			ps.setString(3, order.getCity());
			ps.setInt(4, order.getStatus());
			ps.setInt(5, order.getTransportFee());
			ps.setInt(6, order.getDiscount());
			ps.setInt(7, order.getTotalMoney());
			ps.setString(8, order.getNote());
			ps.setDate(9, (java.sql.Date) order.getDeliveryTime());
			ps.setInt(10, order.getCustomerConfirmation());
			ps.setInt(11, order.getCustomerID());
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.getLastOrderOfCustomer(order.getCustomerID());
	}

	private OrderModel getLastOrderOfCustomer(int customerId) {
		OrderModel order = new OrderModel();
		String sql = "SELECT O.OrderID, O.CustomerID, O.OrderDate, O.Status, O.CustomerConfirmation, O.Discount, O.TotalMoney, O.SellerID, O.ShipperID, O.TransportFee "
				+ "FROM ORDER O " + "WHERE O.CustomerID = ? ORDER BY OrderID DESC LIMIT 1";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, customerId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				order.setOrderID(rs.getInt(1));
				order.setCustomerID(rs.getInt(2));
				order.setOrderDate(rs.getDate(3));
				order.setStatus(rs.getInt(4));
				order.setCustomerConfirmation(rs.getInt(5));
				order.setDiscount(rs.getInt(6));
				order.setTotalMoney(rs.getInt(7));
				order.setSellerID(rs.getInt(8));
				order.setShipperID(rs.getInt(9));
				order.setTransportFee(rs.getInt(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public List<OrderModel> findNeedShipByArea(String area) {
		// Điều kiện SQL
		// String condition = " WHERE o.Status = 2 AND ShipperID IS NULL AND ?;";
//		String condition = " WHERE o.Status = 2 AND ?;";
//		List<String> citys = Assignment.getAssign().get(area);
//		return findDeliveryByCondition(condition, 1).stream()
//				.filter(order -> order.getCity().equals("none") || citys.contains(order.getCity())).toList();

		String condition = "	WHERE o.Status = 2 AND ShipperID IS NULL";
		List<String> citys = Assignment.getAssign().get(area);
		return findDeliveryByCondition(condition, null).stream().filter(order -> order.getCity().equals("none") || citys.contains(order.getCity())).toList();
	}

	@Override
	public List<OrderModel> findShipingByShipperID(int ShipperID) {
		String condition = "	WHERE o.Status = 3 AND ShipperID = ?";
		return findDeliveryByCondition(condition, ShipperID);
	}

	@Override
	public List<OrderModel> findHisDeliveryByShipperID(int ShipperID) {
		String condition = "	WHERE (o.Status = 4 OR o.Status = 5) AND ShipperID = ? "
				+ "ORDER BY o.DeliveryTime DESC";
		return findDeliveryByCondition(condition, ShipperID);
	}

	@Override
	public OrderModel findShipByID(int OrderID) {
		//String condition = "	WHERE o.OrderID = " + OrderID + " AND ?";
		String condition = "	WHERE o.OrderID = " + OrderID;
		return findDeliveryByCondition(condition, null).get(0);
	}

//	private final String sqltemp = "SELECT o.*, FirstName, LastName, Phone, \r\n"
//			+ "	   p.Method, p.Status as PayStatus\r\n" 
//			+ "	   FROM FurSPS.ORDER as o\r\n"
//			+ "    LEFT JOIN FurSPS.PAYMENT as p ON o.OrderID = p.OrderID\r\n"
//			+ "    INNER JOIN USER as c ON  o.CustomerID = c.UserID\r\n";

	private final String sqltemp = "SELECT o.*, c.FirstName, c.LastName, c.Phone, "
			+ "       p.Method, p.Status AS PayStatus " + "FROM [ORDER] AS o "
			+ "LEFT JOIN [PAYMENT] AS p ON o.OrderID = p.OrderID "
			+ "INNER JOIN [USER] AS c ON o.CustomerID = c.UserID";

//	private final String sqltemp = "SELECT o.*, FirstName, LastName, Phone, " + "	   p.Method, p.Status as PayStatus "
//			+ "	   FROM ORDER as o" + "    LEFT JOIN PAYMENT as p ON o.OrderID = p.OrderID"
//			+ "    INNER JOIN USER as c ON  o.CustomerID = c.UserID";

	private List<OrderModel> findDeliveryByCondition(String condition, Integer ShipperID) {
		List<OrderModel> listOrder = new ArrayList<OrderModel>();
		String sql = sqltemp + condition;
		try {
			new DBConnection();
			Connection conn;
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// Nếu cần tham số ShipperID, set giá trị vào preparedStatement
	        if (ShipperID != null) {
	            ps.setInt(1, ShipperID); // set giá trị ShipperID vào vị trí tham số đầu tiên (?)
	        }
			
			//ps.setInt(1, ShipperID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				OrderModel order = new OrderModel();
				order.setOrderID(rs.getInt("OrderID"));
				order.setOrderDate(rs.getDate("OrderDate"));
				order.setAddress(rs.getString("Address"));
				order.setStatus(rs.getInt("Status"));
				order.setDiscount(rs.getInt("Discount"));
				order.setTransportFee(rs.getInt("TransportFee"));
				order.setTotalMoney(rs.getInt("TotalMoney"));
				order.setNote(rs.getString("Note"));
				order.setDeliveryTime(rs.getDate("DeliveryTime"));
				order.setCustomerConfirmation(rs.getInt("CustomerConfirmation"));
				order.setCity(rs.getString("City"));
				order.setCustomerID(rs.getInt("CustomerID"));
				order.setSellerID(rs.getInt("SellerID"));
				order.setShipperID(rs.getInt("ShipperID"));
				order.getCustomer().setFirstName(rs.getString("FirstName"));
				order.getCustomer().setLastName(rs.getString("LastName"));
				order.getCustomer().setPhone(rs.getString("Phone"));
				order.getPayment().setMethod(rs.getInt("Method"));
				order.getPayment().setStatus(rs.getInt("PayStatus"));
				listOrder.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOrder;
	}

	public Object[] findKPIByShipper(int ShipperID) {
//		String sql = "SELECT o.DeliveryTime, s.KPI,\r\n"
//				+ "Count(*) as Oall, SUM(o.Status = 4) as Complete, SUM(o.Status = 5) as Cancel, SUM(o.Status  < 4) as Doing\r\n"
//				+ "	FROM FurSPS.ORDER as o\r\n"
//				+ "		JOIN (SELECT * FROM FurSPS.USER as u WHERE u.userID = ? ) as s\r\n"
//				+ "        ON s.UserID = o.ShipperID\r\n" + " WHERE MONTH(o.DeliveryTime) = MONTH(current_date()) \r\n"
//				+ "		AND YEAR(o.DeliveryTime) = YEAR(current_date()) " + "	GROUP BY o.DeliveryTime "
//				+ " ORDER BY o.DeliveryTime ASC";
		
	
		String sql = "SELECT o.DeliveryTime, s.KPI, " +
                "COUNT(*) AS Oall, " +
                "SUM(CASE WHEN o.Status = 4 THEN 1 ELSE 0 END) AS Complete, " +
                "SUM(CASE WHEN o.Status = 5 THEN 1 ELSE 0 END) AS Cancel, " +
                "SUM(CASE WHEN o.Status < 4 THEN 1 ELSE 0 END) AS Doing " +
                "FROM [ORDER] AS o " +
                "JOIN [USER] AS s ON s.UserID = o.ShipperID " +
                "WHERE MONTH(o.DeliveryTime) = MONTH(GETDATE()) " +
                "AND YEAR(o.DeliveryTime) = YEAR(GETDATE()) " +
                "AND o.ShipperID = ? " +
                "GROUP BY o.DeliveryTime, s.KPI " +
                "ORDER BY o.DeliveryTime ASC";

		Date curday = new Date();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			curday = dateFormat.parse(dateFormat.format(curday));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Integer> listdate = getDatesInNowMonth(new Date());
		List<Integer> listkpi = new ArrayList<>(Collections.nCopies(listdate.size(), 0));
		List<Integer> listall = new ArrayList<>(Collections.nCopies(listdate.size(), 0));
		List<Integer> listcomplete = new ArrayList<>(Collections.nCopies(listdate.size(), 0));
		List<Integer> listcancel = new ArrayList<>(Collections.nCopies(listdate.size(), 0));
		List<Integer> listdoing = new ArrayList<>(Collections.nCopies(listdate.size(), 0));
		int kpi = 0;

		// list
		try {
			new DBConnection();
			Connection conn;
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ShipperID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				//int day = rs.getDate("DeliveryTime").getDate() - 1;
				
				// Lấy ngày từ DeliveryTime và chuyển thành LocalDate
			    LocalDate deliveryDate = rs.getDate("DeliveryTime").toLocalDate();  // Chuyển đổi thành LocalDate
			    int day = deliveryDate.getDayOfMonth() - 1;
				
				
				kpi = rs.getInt("KPI");
				listall.set(day, rs.getInt("Oall"));
				listcomplete.set(day, rs.getInt("Complete"));
				listcancel.set(day, rs.getInt("Cancel"));
				listdoing.set(day, rs.getInt("Doing"));
				// listdate.set(day, rs.getDate("DeliveryTime"));

			}

			listkpi = new ArrayList<>(Collections.nCopies(listdate.size(), kpi));

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}

		Object[] list = { listdate, listkpi, listall, listcomplete, listcancel, listdoing };
		return list;
	}

	public List<Date> getDayInNowWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		List<Date> daysOfWeek = new ArrayList<>();

		// Thêm các ngày trong tuần vào danh sách
		for (int i = 0; i < 7; i++) {
			daysOfWeek.add(calendar.getTime());
			calendar.add(Calendar.DAY_OF_WEEK, 1);
		}
		return daysOfWeek;
	}

	@SuppressWarnings("deprecation")
	public static List<Integer> getDatesInNowMonth(Date date) {

		List<Integer> datesInMonth = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		calendar.set(date.getYear(), date.getMonth(), 1); // Tháng trong Java bắt đầu từ 0
		while (calendar.get(Calendar.MONTH) == (date.getMonth())) {
			datesInMonth.add(calendar.getTime().getDate());
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}

		return datesInMonth;				
	}

	public List<Object[]> findCateForShipper(int ShipperID) {
//		String sql = "SELECT c.CategoryName, Sum(d.Quantity) as Num\r\n" + "	FROM FurSPS.DETAIL as d\r\n"
//				+ "	JOIN FurSPS.ORDER as o ON d.OrderID = o.OrderID\r\n"
//				+ "    JOIN FurSPS.ITEM as i ON d.ItemID = i.ItemID\r\n"
//				+ "    JOIN FurSPS.PRODUCT p ON i.ProductID = p.ProductID\r\n"
//				+ "    JOIN FurSPS.CATEGORY as c ON p.CategoryID = c.CategoryID\r\n" + "    WHERE o.ShipperID = ? \r\n"
//				+ "    GROUP BY c.CategoryName";
		
		
		String sql = "SELECT c.CategoryName, Sum(d.Quantity) as Num\r\n" + "	FROM [DETAIL] as d\r\n"
				+ "	JOIN [ORDER] as o ON d.OrderID = o.OrderID\r\n"
				+ "    JOIN [ITEM] as i ON d.ItemID = i.ItemID\r\n"
				+ "    JOIN [PRODUCT] p ON i.ProductID = p.ProductID\r\n"
				+ "    JOIN [CATEGORY] as c ON p.CategoryID = c.CategoryID\r\n" + "    WHERE o.ShipperID = ? \r\n"
				+ "    GROUP BY c.CategoryName";
		
		List<Object[]> list = new ArrayList<Object[]>();
		Object[] row = { "'Loai hang'", "'SL'" };
		list.add(row);

		try {
			new DBConnection();
			Connection conn;
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ShipperID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String str = "'" + rs.getString("CategoryName") + "'";
				int num = rs.getInt("Num");
				Object[] row1 = { str, num };
				list.add(row1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
