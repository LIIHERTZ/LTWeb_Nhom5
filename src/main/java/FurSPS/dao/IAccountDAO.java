package FurSPS.dao;

import java.util.List;

import FurSPS.models.AccountModel;

public interface IAccountDAO {
	List<AccountModel> getAllAccount();
	AccountModel getOneAccount(int id); 
	boolean insertAccount(AccountModel account); 
	boolean updateAccount(AccountModel account); 
	boolean deleteAccount(AccountModel account); 
	int getTypeAccount(int id);
	AccountModel findByUsernameAndPass(String username,String password);
	AccountModel findByUsername(String username);
	AccountModel findByEmail(String email);
	
	
	boolean isUserExist(int userID);
	boolean isUserExistInAccount(int userID);
}
