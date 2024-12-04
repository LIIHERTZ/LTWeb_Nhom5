package FurSPS.service;

import java.util.List;

import FurSPS.models.UserModel;

public interface IShipperService {
	List<UserModel> findAllShipper();

	void updateShipper(UserModel model);

	UserModel findOne(int id);

	void deleteShipper(int id);

	boolean insertShipper(UserModel model);

	int createShipperID();
}
