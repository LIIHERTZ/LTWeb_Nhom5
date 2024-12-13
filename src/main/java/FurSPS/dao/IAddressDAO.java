package FurSPS.dao;

import java.util.List;

import FurSPS.models.AddressModel;

public interface IAddressDAO {

	List<AddressModel> findByCustomerId(int userID);
	void addAddress(AddressModel address);
	void updateAddress(AddressModel address);
	void deleteAddress(int userID, String city, String detail);
	AddressModel findExistAddress(int userID, String city, String detail);


}
