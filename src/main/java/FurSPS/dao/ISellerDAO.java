package FurSPS.dao;

import java.util.List;

import FurSPS.models.UserModel;

public interface ISellerDAO {
	List<UserModel> findAllSeller();
	void updateSeller(UserModel model);
	UserModel findOne(int id);
	void deleteSeller(int id);
	boolean insertSeller(UserModel model);
	List<UserModel> findBestSeller();
}
