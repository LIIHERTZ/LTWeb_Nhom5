package FurSPS.service;

import java.util.List;
import java.util.Map;

import FurSPS.models.DetailModel;

public interface IDetailService {
	List<DetailModel> findDetailByProductID(int productID);
	DetailModel findDetailByItemID(int orderID, int itemID);
	void updateDetail(DetailModel detail);
	public List<DetailModel> listDetail(int orderID);
	public List<Map<String, Object>> listBestSeller();
}