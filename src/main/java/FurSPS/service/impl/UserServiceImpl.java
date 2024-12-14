package FurSPS.service.impl;

import java.util.List;

import FurSPS.dao.IUserDAO;
import FurSPS.dao.impl.UserDAOImpl;
import FurSPS.models.AccountModel;
import FurSPS.models.UserModel;
import FurSPS.service.IUserService;

public class UserServiceImpl implements IUserService {
	IUserDAO userDAO = new UserDAOImpl();	
	
	@Override
	public UserModel getInfoUser(int userID) {
		return userDAO.getInfoUser(userID);
	}

	@Override
	public void updateUser(UserModel userMd) {
		 userDAO.updateUser(userMd);
	}
	
	@Override
	public AccountModel getInfAccount(int userID) {
		return userDAO.getInfAccount(userID);
	}

	@Override
	public void updateAccount(AccountModel accountMd) {
		userDAO.updateAccount(accountMd);
	}
	
	@Override
	public boolean checkPassword(String oldPassword, String newPassword) {
		if(oldPassword.equals(newPassword)) {
			return true;
		}
		else return false;
	}
	@Override
	public void updateAvatar(int userID, String avatar) {
		userDAO.updateAvatar(userID, avatar);
	}

	@Override
	public List<UserModel> findAllUser() {
		return userDAO.findAllUser();
	}

	
}
