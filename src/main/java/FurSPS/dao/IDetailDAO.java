package FurSPS.dao;

import java.util.List;

import FurSPS.models.DetailModel;

public interface IDetailDAO {
	List<DetailModel> findDetailByProductID(int productID);
	void updateDetail(DetailModel detail);
	public List<DetailModel> listDetail(int orderID);
	DetailModel findDetailByItemID(int orderID, int itemID);
	public List<List<Object>> listBestSeller();
}
