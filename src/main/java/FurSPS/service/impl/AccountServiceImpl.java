package FurSPS.service.impl;

import java.util.List;

import FurSPS.dao.IAccountDAO;
import FurSPS.dao.ICustomerDAO;
import FurSPS.dao.impl.AccountDAOImpl;
import FurSPS.dao.impl.CustomerDAOImpl;
import FurSPS.models.AccountModel;
import FurSPS.models.UserModel;
import FurSPS.service.IAccountService;

public class AccountServiceImpl implements IAccountService {

	IAccountDAO accountDao = new AccountDAOImpl();
	ICustomerDAO cusDAO = new CustomerDAOImpl();
	@Override
	public List<AccountModel> getAllAccount() {
		return accountDao.getAllAccount();
	}

	@Override
	public AccountModel getOneAccount(int id) {
		return accountDao.getOneAccount(id);
	}
	@Override
	public boolean insertAccount(AccountModel account) {
		return accountDao.insertAccount(account);
	}

	@Override
	public boolean updateAccount(AccountModel account) {
		return accountDao.updateAccount(account);
		
	}

	@Override
	public boolean deleteAccount(AccountModel account) {
		return accountDao.deleteAccount(account);
		
	}

	@Override
	public int getTypeAccount(String username,String password) {
		AccountModel acc = accountDao.findByUsernameAndPass(username, password);
		if(acc != null)
			return accountDao.getTypeAccount(acc.getUserID());
		else
			return -1;
	}
	
	@Override
	public UserModel findUserByUsername(String username) {
		AccountModel acc =  accountDao.findByUsername(username);
		return cusDAO.getOneCustomer(acc.getUserID());
	}

	@Override
	public UserModel login(String username, String password) {
		AccountModel acc =  accountDao.findByUsername(username);
		if(acc.getUserID() == 0 )
			acc = accountDao.findByEmail(username);
		if(acc.getUserID() != 0 && password.equals(acc.getPassword())) {
			return cusDAO.getOneCustomer(acc.getUserID());
		}
		return null;
	}
	
	@Override
	public AccountModel findByEmail(String email) {
		return accountDao.findByEmail(email);
	}

	@Override
	public boolean checkPassword(int userID, String password) {
		AccountModel account=accountDao.getOneAccount(userID);
		if(account!=null && account.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	
	
}
