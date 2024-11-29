package FurSPS.service;


import FurSPS.models.AccountModel;
import FurSPS.models.UserModel;

public interface IUserService {
	UserModel getInfoUser(int userID);
	void updateUser(UserModel userMd);
	void updateAvatar(int userID, String avatar);
	AccountModel getInfAccount(int userID);
	void updateAccount(AccountModel accountMd);
	boolean checkPassword(String oldPassword, String newPassword);
}
