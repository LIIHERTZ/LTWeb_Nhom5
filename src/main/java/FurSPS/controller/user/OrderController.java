package FurSPS.controller.user;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import FurSPS.models.OrderModel;
import FurSPS.models.UserModel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import FurSPS.models.CartModel;
import FurSPS.models.DetailModel;
import FurSPS.models.OrderModel;
import FurSPS.models.PaymentModel;
import FurSPS.models.UserModel;
import FurSPS.models.VoucherModel;
import FurSPS.service.ICartService;
import FurSPS.service.IOrderService;
import FurSPS.service.IPaymentService;
import FurSPS.service.IVoucherCustomerService;
import FurSPS.service.IVoucherService;
import FurSPS.service.IDetailService;
import FurSPS.service.impl.CartServiceImpl;
import FurSPS.service.impl.OrderServiceImpl;
import FurSPS.service.impl.PaymentServiceImpl;
import FurSPS.service.impl.VoucherCustomerServiceImpl;
import FurSPS.service.impl.VoucherServiceImpl;
import FurSPS.service.impl.DetailServiceImpl;


@WebServlet(urlPatterns = { "/listOrder", "/customerConfirm", "/detailOrder", "/itemRating" }) //giu nguyen anotation dung sua
@MultipartConfig
public class OrderController extends HttpServlet {

	IOrderService orderService = new OrderServiceImpl();
	IDetailService detailService = new DetailServiceImpl();
	IPaymentService paymentService = new PaymentServiceImpl();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			String url = req.getRequestURI().toString();
			if (url.contains("listOrder")) {
				listOrder(req, resp);
			} else if (url.contains("detailOrder")) {
				detailOrder(req, resp);
			} else if (url.contains("itemRating")) {
				itemRating(req, resp);
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		if (url.contains("customerConfirm")) {
			String act = req.getParameter("action");
			String conf = req.getParameter("confirm");
			int orderID = Integer.parseInt(req.getParameter("orderID"));
			if ("cancelOrder".equals(act)) {
				orderService.updateStatusOrder(orderID, 5);
				listOrder(req, resp);
			} else if ("confirmOrder".equals(act)) {
				orderService.confirmOrder(orderID, 1);
				listOrder(req, resp);
			} else if ("confirmDetailOrder".equals(act)) {
				orderService.confirmOrder(orderID, 1);
				detailOrder(req, resp);
			} else if ("cancelDetailOrder".equals(act)) {
				orderService.updateStatusOrder(orderID, 5);
				detailOrder(req, resp);
			} 
		}
	}

	private void listOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		UserModel user = (UserModel) session.getAttribute("user");
		List<OrderModel> listOrder = orderService.listOrderByCustomerID(user.getUserID());

		req.setAttribute("listOrder", listOrder);
		RequestDispatcher rd = req.getRequestDispatcher("/views/user/order/listOrder.jsp");
		rd.forward(req, resp);
	}

	private void detailOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int orderID = Integer.parseInt(req.getParameter("orderID"));
		
		HttpSession session = req.getSession();
		
		UserModel user =(UserModel)session.getAttribute("user");

		OrderModel order = orderService.getOrderByID(orderID);
		List<DetailModel> listDetail = order.getDetails();
		double totalCost = 0.0;
		for (DetailModel detail : listDetail) {
			if (detail.getItem().getPromotionPrice() == 0) {
				totalCost += detail.getItem().getOriginalPrice() * detail.getQuantity();
			} else {
				totalCost += detail.getItem().getPromotionPrice() * detail.getQuantity();
			}
		}
		PaymentModel payment = paymentService.findPaymentByID(orderID);
		req.setAttribute("payment", payment);
		req.setAttribute("rawPrice", totalCost);
		req.setAttribute("order", order);
		
		req.setAttribute("user", user);
		
		session.removeAttribute("city");
		session.removeAttribute("address");
		session.removeAttribute("note");
		session.removeAttribute("payMethod");
		session.removeAttribute("voucherId");
		session.removeAttribute("discount");
		session.removeAttribute("totalCost");
		session.removeAttribute("shippingMethod");
		session.removeAttribute("transportFee");
		session.removeAttribute("deliveryTime");
		session.removeAttribute("listCart");
//		session.removeAttribute("user");
		
		RequestDispatcher rd = req.getRequestDispatcher("/views/user/order/detailOrder.jsp");
		rd.forward(req, resp);
	}

	private void itemRating(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int orderID = Integer.parseInt(req.getParameter("orderID"));
		int itemID = Integer.parseInt(req.getParameter("itemID"));

		DetailModel detail = detailService.findDetailByItemID(orderID, itemID);
		OrderModel order = orderService.getOrderByOrderID(orderID);

		req.setAttribute("detail", detail);
		req.setAttribute("order", order);
		RequestDispatcher rd = req.getRequestDispatcher("/views/user/order/rating.jsp");
		rd.forward(req, resp);
	}

}