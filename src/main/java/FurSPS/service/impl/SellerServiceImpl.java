package FurSPS.service.impl;

import java.util.List;

import FurSPS.dao.ISellerDAO;
import FurSPS.dao.impl.SellerDAOImpl;
import FurSPS.models.UserModel;
import FurSPS.service.ISellerService;

public class SellerServiceImpl implements ISellerService {

	ISellerDAO sellerDAO = new SellerDAOImpl();

	@Override
	public List<UserModel> findAllSeller() {
		return sellerDAO.findAllSeller();
	}

	@Override
	public void updateSeller(UserModel model) {
		sellerDAO.updateSeller(model);
	}

	@Override
	public UserModel findOne(int id) {
		return sellerDAO.findOne(id);
	}

	@Override
	public void deleteSeller(int id) {
		sellerDAO.deleteSeller(id);

	}

	@Override
	public void insertSeller(UserModel model) {
		sellerDAO.insertSeller(model);

	}

	@Override
	public int createSellerID() {
		List<UserModel> listSeller = sellerDAO.findAllSeller();
		int id = listSeller.get(listSeller.size() - 1).getUserID();
		return id + 1;
	}

	@Override
	public List<UserModel> findBestSeller() {
		return sellerDAO.findBestSeller();
	}

}
