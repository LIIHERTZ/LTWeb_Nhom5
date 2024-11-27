package FurSPS.service;

import java.util.List;

import FurSPS.models.DetailModel;

public interface IDetailService {
	List<DetailModel> findDetailByProductID(int productID);
	DetailModel findDetailByItemID(int orderID, int itemID);
	void updateDetail(DetailModel detail);
	public List<DetailModel> listDetail(int orderID);
	public List<List<Object>> listBestSeller();
}