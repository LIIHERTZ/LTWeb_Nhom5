package FurSPS.dao;

import java.util.List;

import FurSPS.models.VoucherModel;

public interface IVoucherDAO {
	List<VoucherModel> findAllVoucher();
	List<VoucherModel> findVoucherByCustomerID(int customerID);
	VoucherModel findOne(int id);
	VoucherModel findOneByCustomerID(int voucherID, int customerID);
	void insertVoucher(VoucherModel model);
	void updateVoucher(VoucherModel model);
	void deleteVoucher(int id);
}
