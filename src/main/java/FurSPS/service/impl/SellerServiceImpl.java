package FurSPS.service.impl;

import java.util.List;

import FurSPS.dao.ISellerDAO;
import FurSPS.dao.impl.SellerDAOImpl;
import FurSPS.models.UserModel;
import FurSPS.service.ISellerService;

import FurSPS.dao.IUserDAO;
import FurSPS.dao.impl.UserDAOImpl;

public class SellerServiceImpl implements ISellerService {

	ISellerDAO sellerDAO = new SellerDAOImpl();
	IUserDAO userDAO = new UserDAOImpl();

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
	public boolean insertSeller(UserModel model) {
		//Bên customer có tạo avatar....
		return sellerDAO.insertSeller(model);
	}

	@Override
	public int createSellerID() {
		List<UserModel> listUser = userDAO.findAllUser();
		int id = listUser.get(listUser.size() - 1).getUserID();
		return id + 1;
	}

	@Override
	public List<UserModel> findBestSeller() {
		return sellerDAO.findBestSeller();
	}

}
