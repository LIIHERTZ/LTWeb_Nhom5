package FurSPS.service.impl;

import FurSPS.service.IVoucherCustomerService;
import FurSPS.dao.IVoucherCustomerDAO;
import FurSPS.dao.impl.VoucherCustomerDAOImpl;

public class VoucherCustomerServiceImpl implements IVoucherCustomerService{
	IVoucherCustomerDAO voucherCustomerDAO = new VoucherCustomerDAOImpl();
	
	@Override
	public void insertVoucherCustomer(int voucherId, int customerId) {
		voucherCustomerDAO.insertVoucherCustomer(voucherId, customerId); 
	}
	
}
