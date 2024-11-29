package FurSPS.service;

import java.util.List;

import FurSPS.models.PaymentModel;

public interface IPaymentService {
	List<PaymentModel> findAllPayment();
	PaymentModel findPaymentByID (int orderID);
	boolean insertPayment(PaymentModel pay);
	boolean updatePayment(PaymentModel pay);
	boolean deletePayment(int orderID);
}
