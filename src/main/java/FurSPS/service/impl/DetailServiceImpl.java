package FurSPS.service.impl;

import java.util.List;

import FurSPS.dao.IDetailDAO;
import FurSPS.dao.impl.DetailDAOImpl;
import FurSPS.models.DetailModel;
import FurSPS.service.IDetailService;

public class DetailServiceImpl implements IDetailService {
	IDetailDAO detailDAO = new DetailDAOImpl();

	@Override
	public List<DetailModel> findDetailByProductID(int productID) {
		return detailDAO.findDetailByProductID(productID);
	}

	@Override
	public void updateDetail(DetailModel detail) {
		detailDAO.updateDetail(detail);
	}

	@Override
	public DetailModel findDetailByItemID(int orderID, int itemID) {
		return detailDAO.findDetailByItemID(orderID, itemID);
	}
	@Override
	public List<DetailModel> listDetail(int orderID) {
		return detailDAO.listDetail(orderID);
	}
	@Override
	public List<List<Object>> listBestSeller() {
		return detailDAO.listBestSeller();
	}

}
