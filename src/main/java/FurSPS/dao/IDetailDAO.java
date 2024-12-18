package FurSPS.dao;

import java.util.List;
import java.util.Map;

import FurSPS.models.CartModel;
import FurSPS.models.DetailModel;

public interface IDetailDAO {
	List<DetailModel> findDetailByProductID(int productID);
	void updateDetail(DetailModel detail);
	public List<DetailModel> listDetail(int orderID);
	DetailModel findDetailByItemID(int orderID, int itemID);
	public List<Map<String, Object>> listBestSeller();
	void insertDetail(List<CartModel> listCart, int orderID);
	List<DetailModel> listDetailsByOrderID(int orderID);
	boolean hasReviewed(int orderID, int itemID);
	boolean addReview(DetailModel review);
}
