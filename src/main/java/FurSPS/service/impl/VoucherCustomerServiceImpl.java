package FurSPS.service.impl;
import FurSPS.dao.IVoucherCustomerDAO;
import FurSPS.dao.impl.VoucherCustomerDAOImpl;
import FurSPS.service.IVoucherCustomerService;

public class VoucherCustomerServiceImpl implements IVoucherCustomerService{
	IVoucherCustomerDAO voucherCustomerDAO = new VoucherCustomerDAOImpl();
	
	@Override
	public void insertVoucherCustomer(int voucherId, int customerId) {
		voucherCustomerDAO.insertVoucherCustomer(voucherId, customerId); 
	}
	
}
