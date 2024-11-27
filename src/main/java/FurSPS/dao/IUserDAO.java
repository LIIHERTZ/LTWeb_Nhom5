package FurSPS.dao;


import FurSPS.models.AccountModel;
import FurSPS.models.UserModel;

public interface IUserDAO {
	UserModel getInfoUser(int userID);
	void updateUser(UserModel user);
	void updateAvatar(int userID, String avatar);
	AccountModel getInfAccount(int userID);
	void updateAccount(AccountModel account);
}
