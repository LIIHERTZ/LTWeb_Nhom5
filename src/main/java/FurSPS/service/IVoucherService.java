package FurSPS.service;

import java.util.List;

import FurSPS.models.VoucherModel;

public interface IVoucherService {
	List<VoucherModel> findAllVoucher();
	List<VoucherModel> findVoucherByCustomerID(int customerID);
	void insertVoucher(VoucherModel model);
	void updateVoucher(VoucherModel model);
	VoucherModel findOne(int id);
	VoucherModel findOneByCustomerID(int voucherID, int customerID);
	boolean containsNonDigit(String input);
	void deleteVoucher(int id);
}
