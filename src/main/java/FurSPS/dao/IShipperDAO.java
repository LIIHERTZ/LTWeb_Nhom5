package FurSPS.dao;

import java.util.List;

import FurSPS.models.UserModel;

public interface IShipperDAO {
	List<UserModel> findAllShipper();

	void updateShipper(UserModel model);

	UserModel findOne(int id);

	void deleteShipper(int id);

	boolean insertShipper(UserModel model);
}
