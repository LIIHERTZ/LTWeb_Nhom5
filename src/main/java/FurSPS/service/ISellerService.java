package FurSPS.service;

import java.util.List;

import FurSPS.models.UserModel;

public interface ISellerService {
	List<UserModel> findAllSeller();
	void updateSeller(UserModel model);
	UserModel findOne(int id);
	void deleteSeller(int id);
	boolean insertSeller(UserModel model);
	int createSellerID();
	List<UserModel> findBestSeller();
}
