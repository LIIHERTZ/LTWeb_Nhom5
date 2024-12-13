package FurSPS.service.impl;

import java.util.List;

import FurSPS.dao.IAddressDAO;
import FurSPS.dao.impl.AddressDAOImpl;
import FurSPS.models.AddressModel;
import FurSPS.service.IAddressService;

public class AddressServiceImpl implements IAddressService{
	IAddressDAO addressDao = new AddressDAOImpl();
	@Override
	public List<AddressModel> findByCustomerId(int userID) {
		return addressDao.findByCustomerId(userID);
	}
	@Override
	public AddressModel findExistAddress(int userID, String city, String detail) {
		return addressDao.findExistAddress(userID,city,detail);
	}
	@Override
	public void addAddress(AddressModel address) {
		addressDao.addAddress(address);
		
	}
	@Override
	public void updateAddress(AddressModel address) {
		addressDao.updateAddress(address);
		
	}
	@Override
	public void deleteAddress(int userID, String city, String detail) {
		addressDao.deleteAddress(userID, city, detail);
		
	}

}
