package FurSPS.service.impl;

import java.util.List;

import FurSPS.dao.IPaymentDAO;
import FurSPS.dao.impl.PaymentDAOImpl;
import FurSPS.models.PaymentModel;
import FurSPS.service.IPaymentService;

public class PaymentServiceImpl implements IPaymentService{
	IPaymentDAO paymentDAO = new PaymentDAOImpl();
	@Override
	public List<PaymentModel> findAllPayment() {
		// TODO Auto-generated method stub
		return paymentDAO.findAllPayment();
	}

	@Override
	public PaymentModel findPaymentByID(int orderID) {
		// TODO Auto-generated method stub
		return paymentDAO.findPaymentByID(orderID);
	}

	@Override
	public boolean insertPayment(PaymentModel pay) {
		// TODO Auto-generated method stub
		return paymentDAO.insertPayment(pay);
	}

	@Override
	public boolean updatePayment(PaymentModel pay) {
		// TODO Auto-generated method stub
		return paymentDAO.updatePayment(pay);
	}

	@Override
	public boolean deletePayment(int orderID) {
		// TODO Auto-generated method stub
		return paymentDAO.deletePayment(orderID);
	}

}
