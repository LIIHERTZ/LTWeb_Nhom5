package FurSPS.controller.user;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.sql.Timestamp;
import java.time.Instant;

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
import FurSPS.models.AddressModel;
import FurSPS.models.CartModel;
import FurSPS.models.PaymentModel;
import FurSPS.models.VoucherModel;
import FurSPS.service.ICartService;
import FurSPS.service.IDetailService;
import FurSPS.service.IOrderService;
import FurSPS.service.IPaymentService;
import FurSPS.service.IVoucherCustomerService;
import FurSPS.service.IVoucherService;
import FurSPS.service.IAddressService;
import FurSPS.service.impl.CartServiceImpl;
import FurSPS.service.impl.DetailServiceImpl;
import FurSPS.service.impl.OrderServiceImpl;
import FurSPS.service.impl.PaymentServiceImpl;
import FurSPS.service.impl.VoucherCustomerServiceImpl;
import FurSPS.service.impl.VoucherServiceImpl;
import FurSPS.service.impl.AddressServiceImpl;

@WebServlet(urlPatterns = { "/userCheckout" })
public class CheckoutController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String webLink = "/views/user/checkout.jsp";
	RequestDispatcher rd = null;
	ICartService cartService = new CartServiceImpl();
	IVoucherService voucherService = new VoucherServiceImpl();
	IVoucherCustomerService voucherCustomerService = new VoucherCustomerServiceImpl();
	IOrderService orderService = new OrderServiceImpl();
	IPaymentService paymentService = new PaymentServiceImpl();
	IDetailService detailService = new DetailServiceImpl();
	IAddressService addressService = new AddressServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserModel user = getCurrentUser(req, resp);
		if (user == null)
			return;
		String voucherIdString = req.getParameter("voucherId");
		List<CartModel> listCart = cartService.findByCustomerId(user.getUserID());
		List<VoucherModel> listVoucher = voucherService.findVoucherByCustomerID(user.getUserID());
		List<AddressModel> listAddress = addressService.findByCustomerId(user.getUserID());

		double totalCost = 0.0;
		for (CartModel cart : listCart) {
			totalCost += cart.getPromotionPrice() * cart.getQuantity();
		}

		req.setAttribute("totalCost", totalCost);

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
		req.setAttribute("listAddress", listAddress);
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

		String address = req.getParameter("detail") +", "+  req.getParameter("city") ;

		String note = req.getParameter("note");
		int payMethod = Integer.parseInt(req.getParameter("paymentMethod"));
		String voucherIdString = req.getParameter("voucherId");

		int discount = (int) Double.parseDouble(req.getParameter("discount"));
		int totalCost = (int) Double.parseDouble(req.getParameter("TotalMoney"));

		LocalDate deliveryDate = LocalDate.now();
		// Phương thức vận chuyển
		int shippingFee = Integer.parseInt(req.getParameter("method"));
		if (shippingFee == 50000) {
			deliveryDate = deliveryDate.plusDays(7);
		} else if (shippingFee == 100000) {
			deliveryDate = deliveryDate.plusDays(4);
		} else if (shippingFee == 200000) {
			deliveryDate = deliveryDate.plusDays(2);
		} else if (shippingFee == 350000) {
			deliveryDate = deliveryDate.plusDays(1);
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
		session.setAttribute("transportFee", shippingFee);
		session.setAttribute("user", user);
		session.setAttribute("deliveryTime", deliveryTime);

		if (payMethod == 0) {
			// Thanh toán tiền mặt - Lưu thanh toán và chuyển hướng tới trang chi tiết đơn
			// hàng
			OrderModel newOrder = new OrderModel();
			newOrder.setOrderDate(new Date());
			newOrder.setAddress(address);
			newOrder.setCity(city);
			newOrder.setStatus(0);
			newOrder.setTransportFee(shippingFee);
			newOrder.setDiscount(discount);
			newOrder.setTotalMoney(totalCost);
			newOrder.setNote(note);
			newOrder.setDeliveryTime(deliveryTime);
			newOrder.setCustomerConfirmation(0);
			newOrder.setCustomerID(user.getUserID());

			OrderModel createdOrder = orderService.insertOrder(newOrder);

			// Phuc
			detailService.insertDetail(listCart, createdOrder.getOrderID());

			if (voucherIdString != null) {
				int voucherID = Integer.parseInt(voucherIdString);
				voucherCustomerService.insertVoucherCustomer(voucherID, user.getUserID());
			}
			// *INFO: CREATE PAYMENT
			PaymentModel payment = new PaymentModel();
			payment.setMethod(payMethod);
			payment.setOrderID(createdOrder.getOrderID());

			// *INFO: SET DEFAULT STATUS = 0 (NOT CHECKOUT). UPDATE LATER
			payment.setStatus(0);
			Timestamp now = Timestamp.from(Instant.now());
			payment.setTime(now);
			paymentService.insertPayment(payment);
			cartService.deleteAllByCustomerID(user.getUserID());
			req.setAttribute("payment", payment);
			
			resp.sendRedirect(req.getContextPath() + "/userdetailOrder?orderID=" + createdOrder.getOrderID());
		} else if (payMethod == 1) {
			// Thanh toán qua VNPay - Tạo URL thanh toán và chuyển hướng
			String vnp_Version = "2.1.0";
			String vnp_Command = "pay";
			String vnp_OrderInfo = "Thanh toan ve xem phim";
			String orderType = "billpayment";
			String vnp_TxnRef = PaymentConfig.getRandomNumber(8);
			String vnp_IpAddr = PaymentConfig.getIpAddress(req);
			String vnp_TmnCode = PaymentConfig.vnp_TmnCode;

			long price = (long) Double.parseDouble(req.getParameter("TotalMoney"));

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
			// Add Params of 2.1.0 Version
			vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
			// Build data to hash and querystring
			List fieldNames = new ArrayList(vnp_Params.keySet());
			Collections.sort(fieldNames);
			StringBuilder hashData = new StringBuilder();
			StringBuilder query = new StringBuilder();
			Iterator itr = fieldNames.iterator();
			while (itr.hasNext()) {
				String fieldName = (String) itr.next();
				String fieldValue = (String) vnp_Params.get(fieldName);
				if ((fieldValue != null) && (fieldValue.length() > 0)) {
					// Build hash data
					hashData.append(fieldName);
					hashData.append('=');
					hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
					// Build query
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
			resp.sendRedirect(paymentUrl);
		}
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
