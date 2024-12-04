package FurSPS.service.impl;

import java.util.List;

import FurSPS.dao.IShipperDAO;
import FurSPS.dao.IUserDAO;
import FurSPS.dao.impl.ShipperDAOImpl;
import FurSPS.dao.impl.UserDAOImpl;
import FurSPS.models.UserModel;
import FurSPS.service.IShipperService;

public class ShipperServiceImpl implements IShipperService {

	IShipperDAO shipperDAO = new ShipperDAOImpl();
	IUserDAO userDAO = new UserDAOImpl();

	@Override
	public List<UserModel> findAllShipper() {
		return shipperDAO.findAllShipper();
	}

	@Override
	public void updateShipper(UserModel model) {
		shipperDAO.updateShipper(model);

	}

	@Override
	public UserModel findOne(int id) {
		return shipperDAO.findOne(id);
	}

	@Override
	public void deleteShipper(int id) {
		shipperDAO.deleteShipper(id);

	}

	@Override
	public boolean insertShipper(UserModel model) {
		return shipperDAO.insertShipper(model);
	}

	@Override
	public int createShipperID() {
		List<UserModel> listUser = userDAO.findAllUser();
		int id = listUser.get(listUser.size() - 1).getUserID();
		return id + 1;
	}

}
