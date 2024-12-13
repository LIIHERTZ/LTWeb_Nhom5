package FurSPS.controller.user;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import FurSPS.models.OrderModel;
import FurSPS.models.UserModel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import FurSPS.configs.PaymentConfig;
import FurSPS.models.CartModel;
import FurSPS.models.OrderModel;
import FurSPS.models.PaymentModel;
import FurSPS.models.UserModel;
import FurSPS.models.VoucherModel;
import FurSPS.service.ICartService;
import FurSPS.service.IDetailService;
import FurSPS.service.IOrderService;
import FurSPS.service.IPaymentService;
import FurSPS.service.IVoucherCustomerService;
import FurSPS.service.IVoucherService;
import FurSPS.service.impl.CartServiceImpl;
import FurSPS.service.impl.DetailServiceImpl;
import FurSPS.service.impl.OrderServiceImpl;
import FurSPS.service.impl.PaymentServiceImpl;
import FurSPS.service.impl.VoucherCustomerServiceImpl;
import FurSPS.service.impl.VoucherServiceImpl;

@WebServlet(urlPatterns = { "/checkout" }) //để nguyên checkout, đừng sửa thành /user/checkout
public class CheckoutController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String webLink = "/views/user/checkout.jsp";
	RequestDispatcher rd = null;
	ICartService cartService = new CartServiceImpl();
	IVoucherService voucherService = new VoucherServiceImpl();
	IVoucherCustomerService voucherCustomerService = new VoucherCustomerServiceImpl();
	IOrderService orderService = new OrderServiceImpl();
	IPaymentService paymentService = new PaymentServiceImpl();
	//Phuc
	IDetailService detailService = new DetailServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserModel user = getCurrentUser(req, resp);
		if (user == null)
			return;
		String voucherIdString = req.getParameter("voucherId");
		List<CartModel> listCart = cartService.findByCustomerId(user.getUserID());
		List<VoucherModel> listVoucher = voucherService.findVoucherByCustomerID(user.getUserID());
		String shippingMethod = req.getParameter("shippingMethod");
		int transportFee = 200000;
		
		// if ("express".equals(shippingMethod)) int transportFee = 200000
		if ("standard".equals(shippingMethod)) {
			transportFee = 50000;
		}

		double totalCost = 0.0;
		for (CartModel cart : listCart) {
			totalCost += cart.getPromotionPrice()  * cart.getQuantity() ;
		}
		req.setAttribute("rawPrice", totalCost);
		req.setAttribute("totalCost", totalCost + transportFee);

		if (voucherIdString != null) {
			int voucherId = Integer.parseInt(voucherIdString);
			VoucherModel voucher = voucherService.findOne(voucherId);

			if (totalCost < voucher.getMinimumPrice()) {
				req.setAttribute("minimumPrice", voucher.getMinimumPrice());
			} else {
				double discount = totalCost * voucher.getDiscount() / 100;
				totalCost -= discount;
				req.setAttribute("discount", discount);
				req.setAttribute("totalCost", totalCost);
			}
		}

		req.setAttribute("listVoucher", listVoucher);
		req.setAttribute("user", user);
		req.setAttribute("listCart", listCart);
		
		HttpSession session = req.getSession();
		session.setAttribute("listCart", listCart);

		redirectCheckoutPage(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserModel user = getCurrentUser(req, resp);
		if (user == null) {
			return;
		}
		resp.setContentType("text/htm");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String city = req.getParameter("city");
		
		String address = req.getParameter("address");
		String note = req.getParameter("note");
		int payMethod = Integer.parseInt(req.getParameter("pay-method"));
		String voucherIdString = req.getParameter("voucherId");

		int discount = (int) Double.parseDouble(req.getParameter("discount"));
		int totalCost = (int) Double.parseDouble(req.getParameter("totalCost"));

		LocalDate deliveryDate = LocalDate.now();
//		if (city.equals("none")) {
//			req.setAttribute("cityError", "Thành phố không được để trống");
//			req.setAttribute("address", address);
//			req.setAttribute("note", note);
//			doGet(req, resp);
//			return;
//		}
		// Phương thức vận chuyển
		String shippingMethod = req.getParameter("shippingMethod");
		int transportFee = 200000;	
		// if ("express".equals(shippingMethod)) int transportFee = 200000
		if ("standard".equals(shippingMethod)) {
			transportFee = 50000;
			deliveryDate = deliveryDate.plusDays(15);
		}else
		{
			deliveryDate = deliveryDate.plusDays(5);
		}
		Date deliveryTime = java.sql.Date.valueOf(deliveryDate);
		
		
		
		HttpSession session = req.getSession();
		
		@SuppressWarnings("unchecked")
	    List<CartModel> listCart = (List<CartModel>) session.getAttribute("listCart");
		

		// Lưu các giá trị vào session
		session.setAttribute("city", city);
		session.setAttribute("address", address);
		session.setAttribute("note", note);
		session.setAttribute("payMethod", payMethod);
		session.setAttribute("voucherId", voucherIdString);
		session.setAttribute("discount", discount);
		session.setAttribute("totalCost", totalCost);
		session.setAttribute("shippingMethod", shippingMethod);
		session.setAttribute("transportFee", transportFee);
		session.setAttribute("user", user);
		session.setAttribute("deliveryTime", deliveryTime);
		
//		OrderModel newOrder = new OrderModel();
//		newOrder.setOrderDate(new Date());
//		newOrder.setAddress(address);
//		newOrder.setCity(city);
//		newOrder.setStatus(0);
//		newOrder.setTransportFee(transportFee);
//		newOrder.setDiscount(discount);
//		newOrder.setTotalMoney(totalCost);
//		newOrder.setNote(note);
//		newOrder.setDeliveryTime(null);
//		newOrder.setCustomerConfirmation(0);
//		newOrder.setCustomerID(user.getUserID());
//
//		OrderModel createdOrder = orderService.insertOrder(newOrder);
//
//		if (voucherIdString != null) {
//			int voucherID = Integer.parseInt(voucherIdString);
//			voucherCustomerService.insertVoucherCustomer(voucherID, totalCost);
//		}
//		// *INFO: CREATE PAYMENT
//		PaymentModel payment = new PaymentModel();
//		payment.setMethod(payMethod);
//		payment.setOrderID(createdOrder.getOrderID());
//
//		// *INFO: SET DEFAULT STATUS = 0 (NOT CHECKOUT). UPDATE LATER
//		payment.setStatus(0);
//		payment.setTime(null);
//		if (payMethod == 1) {
//			String numCard = req.getParameter("AccountNumber");
//			String cardOwner = req.getParameter("CardOwner");
//			String bank = req.getParameter("Bank");
//			payment.setAccountNumber(numCard);
//			payment.setCardOwner(cardOwner);
//			payment.setBank(bank);
//			payment.setTime(new Timestamp(new Date().getTime()));
//		}
		
		
		if (payMethod == 0) {
	        // Thanh toán tiền mặt - Lưu thanh toán và chuyển hướng tới trang chi tiết đơn hàng
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
			
			//Phuc
			detailService.insertDetail(listCart, createdOrder.getOrderID());

			if (voucherIdString != null) {
				int voucherID = Integer.parseInt(voucherIdString);
				voucherCustomerService.insertVoucherCustomer(voucherID, totalCost);
			}
			// *INFO: CREATE PAYMENT
			PaymentModel payment = new PaymentModel();
			payment.setMethod(payMethod);
			payment.setOrderID(createdOrder.getOrderID());

			// *INFO: SET DEFAULT STATUS = 0 (NOT CHECKOUT). UPDATE LATER
			payment.setStatus(0);
			payment.setTime(null);
	        paymentService.insertPayment(payment);
	        req.setAttribute("payment", payment);
	        resp.sendRedirect(req.getContextPath() + "/detailOrder?orderID=" + createdOrder.getOrderID());
	    } else if (payMethod == 1) {
	        // Thanh toán qua VNPay - Tạo URL thanh toán và chuyển hướng
	    	String vnp_Version = "2.1.0";
            String vnp_Command = "pay";
            String vnp_OrderInfo = "Thanh toan ve xem phim";
            String orderType = "billpayment";
            String vnp_TxnRef = PaymentConfig.getRandomNumber(8);
            String vnp_IpAddr = PaymentConfig.getIpAddress(req);
            String vnp_TmnCode = PaymentConfig.vnp_TmnCode;
//            HttpSession session = req.getSession();
//            String price = req.getParameter("totalCost");
//            session.setAttribute("amountPayable", price);
//            String couponId = req.getParameter("selectedCouponId");
//            session.setAttribute("selectedCouponId", couponId);
            long price = (long) Double.parseDouble(req.getParameter("totalCost"));
            
            long amount = (price) * 100;
            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", vnp_Version);
            vnp_Params.put("vnp_Command", vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(amount));
            vnp_Params.put("vnp_CurrCode", "VND");
            vnp_Params.put("vnp_BankCode", "NCB");
            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
            vnp_Params.put("vnp_OrderType", orderType);
            vnp_Params.put("vnp_Locale", "vn");
            vnp_Params.put("vnp_ReturnUrl", PaymentConfig.vnp_ReturnUrl);
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
    
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(cld.getTime());
    
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());
            //Add Params of 2.1.0 Version
            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
            //Build data to hash and querystring
            List fieldNames = new ArrayList(vnp_Params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) vnp_Params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    //Build hash data
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            String queryUrl = query.toString();
            String vnp_SecureHash = PaymentConfig.hmacSHA512(PaymentConfig.secretKey, hashData.toString());
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = PaymentConfig.vnp_PayUrl + "?" + queryUrl;
            com.google.gson.JsonObject job = new JsonObject();
            job.addProperty("code", "00");
            job.addProperty("message", "success");
            job.addProperty("data", paymentUrl);
            Gson gson = new Gson();
//            resp.getWriter().write(gson.toJson(job));
            resp.sendRedirect(paymentUrl);
	    }
		
//		paymentService.insertPayment(payment);
//		req.setAttribute("payment", payment);
//		resp.sendRedirect(req.getContextPath() + "/detailOrder?orderID=" + createdOrder.getOrderID());
//		return;
	}

	private void redirectCheckoutPage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		rd = req.getRequestDispatcher(this.webLink);
		rd.forward(req, resp);
	}

	private UserModel getCurrentUser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(true);

		if (session == null || session.getAttribute("user") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return null;
		}

		return (UserModel) session.getAttribute("user");
	}
}
