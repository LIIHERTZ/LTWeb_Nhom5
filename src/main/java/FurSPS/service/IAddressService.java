package FurSPS.service;

import java.util.List;

import FurSPS.models.AddressModel;

public interface IAddressService {

	List<AddressModel> findByCustomerId(int userID);
	void addAddress(AddressModel address);
	void updateAddress(AddressModel address);
	void deleteAddress(int userID, String city, String detail);
	AddressModel findExistAddress(int userID, String city, String detail);

}
