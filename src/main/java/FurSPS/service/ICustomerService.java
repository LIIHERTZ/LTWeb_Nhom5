package FurSPS.service;

import java.util.List;

import FurSPS.models.UserModel;

public interface ICustomerService {
	List<UserModel> getAllCustomer();
	UserModel getOneCustomer(int id);
	boolean insertCustomer(UserModel customerMd);
	boolean updateCustomer(UserModel customerMd);
	boolean deleteCustomer(UserModel customerMd);
	int createCustomerID();	
	public void checkValidInfoCustomer(String firstName, String lastName, String address, String gender, String phone,
			String dob, String area, String email, String username, String pass, String passcheck);
	public void checkValidEmail(String email);
}
