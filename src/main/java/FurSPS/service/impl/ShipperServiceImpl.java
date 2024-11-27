package FurSPS.service.impl;

import java.util.List;

import FurSPS.dao.IShipperDAO;
import FurSPS.dao.impl.ShipperDAOImpl;
import FurSPS.models.UserModel;
import FurSPS.service.IShipperService;

public class ShipperServiceImpl implements IShipperService {

	IShipperDAO shipperDAO = new ShipperDAOImpl();

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
	public void insertShipper(UserModel model) {
		shipperDAO.insertShipper(model);

	}

	@Override
	public int createShipperID() {
		List<UserModel> listShipper = shipperDAO.findAllShipper();
		int id = listShipper.get(listShipper.size() - 1).getUserID();
		return id + 1;
	}

}
