package FurSPS.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import FurSPS.configs.PaymentConfig;
import FurSPS.models.CartModel;
import FurSPS.models.OrderModel;
import FurSPS.models.PaymentModel;
import FurSPS.models.UserModel;
import FurSPS.service.ICartService;
import FurSPS.service.IDetailService;
import FurSPS.service.IOrderService;
import FurSPS.service.IPaymentService;
import FurSPS.service.IVoucherCustomerService;
import FurSPS.service.impl.CartServiceImpl;
import FurSPS.service.impl.DetailServiceImpl;
import FurSPS.service.impl.OrderServiceImpl;
import FurSPS.service.impl.PaymentServiceImpl;
import FurSPS.service.impl.VoucherCustomerServiceImpl;

@WebServlet("/userPaymentReturn")
public class PaymentReturnController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	IPaymentService paymentService = new PaymentServiceImpl();
	IOrderService orderService = new OrderServiceImpl();
	IVoucherCustomerService voucherCustomerService = new VoucherCustomerServiceImpl();
	ICartService cartService = new CartServiceImpl();
	IDetailService detailService = new DetailServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Map<String, String> vnpParams = new HashMap<>();
        req.getParameterMap().forEach((key, value) -> vnpParams.put(key, value[0]));

        String responseCode = vnpParams.get("vnp_ResponseCode");
        if ("00".equals(responseCode)) {
			// Thanh toán thành công
			String payDate = vnpParams.get("vnp_PayDate");
			String city = (String) session.getAttribute("city");
			String address = (String) session.getAttribute("address");
			String note = (String) session.getAttribute("note");
			int payMethod = (int) session.getAttribute("payMethod");
			String voucherId = (String) session.getAttribute("voucherId");
			int discount = (int) session.getAttribute("discount");
			int totalCost = (int) session.getAttribute("totalCost");
			String shippingMethod = (String) session.getAttribute("shippingMethod");
			int transportFee = (int) session.getAttribute("transportFee");
			UserModel user =(UserModel)session.getAttribute("user");
			Date deliveryTime = (Date) session.getAttribute("deliveryTime");
			
			@SuppressWarnings("unchecked")
		    List<CartModel> listCart = (List<CartModel>) session.getAttribute("listCart");
			
			// SAVE DB
			try {
				OrderModel newOrder = new OrderModel();
				newOrder.setOrderDate(new Date());
				newOrder.setAddress(address);
				newOrder.setCity(city);
				newOrder.setStatus(0);
				newOrder.setTransportFee(transportFee);
				newOrder.setDiscount(discount);
				newOrder.setTotalMoney(totalCost);
				newOrder.setNote(note);
				newOrder.setDeliveryTime(deliveryTime);
				newOrder.setCustomerConfirmation(0);
				newOrder.setCustomerID(user.getUserID());

				OrderModel createdOrder = orderService.insertOrder(newOrder);
				
				detailService.insertDetail(listCart, createdOrder.getOrderID());

				if (voucherId != null) {
					int voucherID = Integer.parseInt(voucherId);
					voucherCustomerService.insertVoucherCustomer(voucherID, user.getUserID());
				}
				// *INFO: CREATE PAYMENT
				PaymentModel payment = new PaymentModel();
				payment.setMethod(payMethod);
				payment.setOrderID(createdOrder.getOrderID());
				payment.setBank("NCB");
				payment.setAccountNumber("9704198526191432198");
				payment.setCardOwner(user.getFirstName() + user.getLastName());

				// *INFO: SET DEFAULT STATUS = 0 (NOT CHECKOUT). UPDATE LATER
				payment.setStatus(1);
				payment.setTime(new Timestamp(new Date().getTime()));
				
		        paymentService.insertPayment(payment);
		        
		        cartService.deleteAllByCustomerID(user.getUserID());
		        req.setAttribute("payment", payment);
				
		        
		        req.setAttribute("success", true);
	            req.setAttribute("orderID", createdOrder.getOrderID());
			} catch (Exception e) {
				e.printStackTrace();
	            req.setAttribute("success", false);
	            req.setAttribute("errorMessage", "An error occurred while processing your payment. Please try again later.");
			}
			// Chuyển hướng tới JSP hiển thị kết quả
		
//			req.setAttribute("vnp_Params", vnpParams);
//			req.getRequestDispatcher("/views/user/payment-status.jsp").forward(req, resp);
		} else {
			// Thanh toán thất bại
	        req.setAttribute("success", false);
	        req.setAttribute("errorMessage", "Payment failed with Response Code: " + responseCode);
		}
        req.getRequestDispatcher("/views/user/payment-status.jsp").forward(req, resp);
	}
}
