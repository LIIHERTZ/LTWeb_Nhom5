package FurSPS.service;

import java.util.List;

import FurSPS.models.DetailModel;
import FurSPS.models.OrderModel;

public interface IOrderService {
	List<OrderModel> listOrderByCustomerID(int customerID);
	OrderModel getOrderByOrderID(int orderID);
	void updateStatusOrder (int orderID, int status);
	void confirmOrder (int orderID, int confirm);
	List<OrderModel> findAllOrder();
	OrderModel findByOrderID(int orderID);
	List<OrderModel> findHisOrder(int sellerID);
	void updateStatusOrder(int orderID, int sellerID, int status);
	List<OrderModel> findOrderBySeller();
	void updateOrder (OrderModel order);
	void deleteOrder (int orderID);
	OrderModel getOrderByID(int orderID);
	OrderModel insertOrder (OrderModel order);
	public List<OrderModel> findNeedShipByArea(String area);
	public List<OrderModel> findShipingByShipperID(int ShipperID);
	public List<OrderModel> findHisDeliveryByShipperID(int ShipperID);
	public OrderModel findShipByID(int OrderID);
	int countOrdersByCustomerID(int userID);
	List<OrderModel> listOrderByCustomerID(int customerID, int pageSize);
	List<DetailModel> listDetailsByOrderID(int orderID);
}
