package FurSPS.controller.user;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import FurSPS.models.DetailModel;
import FurSPS.models.OrderModel;
import FurSPS.models.UserModel;
import FurSPS.other.ImageUploader;
import FurSPS.service.IDetailService;
import FurSPS.service.IOrderService;
import FurSPS.service.IPaymentService;
import FurSPS.service.impl.DetailServiceImpl;
import FurSPS.service.impl.OrderServiceImpl;
import FurSPS.service.impl.PaymentServiceImpl;

@WebServlet(urlPatterns = { "/userlistOrder", "/usercustomerConfirm", "/userdetailOrder", "/useritemRating",
		"/usertrack", "/userConfirmDelivery" })
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
			if (url.contains("userlistOrder")) {
				listOrder(req, resp);
			} else if (url.contains("userdetailOrder")) {
				detailOrder(req, resp);
			} else if (url.contains("usertrack")) {
				track(req, resp);
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURI().toString();
		if (action.contains("/useritemRating")) {
			rating(req, resp);
		}
		else if (action.contains("/userConfirmDelivery")) {
			confirm(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "URL không được hỗ trợ");
		}
	}

	private void listOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		UserModel user = (UserModel) session.getAttribute("user");

		// Lấy tham số trang từ request, nếu không có thì mặc định là trang 1
		int page = 1;
		try {
			page = Integer.parseInt(req.getParameter("page"));
		} catch (NumberFormatException e) {
			page = 1;
		}
		// Gọi service để lấy danh sách đơn hàng phân trang
		List<OrderModel> listOrder = orderService.listOrderByCustomerID(user.getUserID(), page);
		req.setAttribute("listOrder", listOrder);

		// Tính tổng số trang
		int totalOrders = orderService.countOrdersByCustomerID(user.getUserID());
		int pageSize = 5; // Mỗi trang 5 đơn hàng
		int totalPages = (int) Math.ceil((double) totalOrders / pageSize);

		// Set các attribute cho JSP sử dụng
		req.setAttribute("currentPage", page);
		req.setAttribute("totalPages", totalPages);

		RequestDispatcher rd = req.getRequestDispatcher("/views/user/order/listOrder.jsp");
		rd.forward(req, resp);
	}

	private void track(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int orderID = Integer.parseInt(req.getParameter("id"));
		OrderModel order = orderService.getOrderByOrderID(orderID);
		List<DetailModel> details = detailService.listDetailsByOrderID(orderID);
		req.setAttribute("details", details);
		req.setAttribute("order", order);
		RequestDispatcher rd = req.getRequestDispatcher("/views/user/order/track.jsp");
		rd.forward(req, resp);
	}
	private void confirm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int orderID = Integer.parseInt(req.getParameter("orderID"));
		int confirm = 1;
		orderService.confirmOrder(orderID, confirm);
		OrderModel order = orderService.getOrderByOrderID(orderID);
		List<DetailModel> details = detailService.listDetailsByOrderID(orderID);
		req.setAttribute("details", details);
		req.setAttribute("order", order);
		RequestDispatcher rd = req.getRequestDispatcher("/views/user/order/track.jsp");
		rd.forward(req, resp);
	}

	private void detailOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int orderID = Integer.parseInt(req.getParameter("orderID"));
		OrderModel order = orderService.getOrderByOrderID(orderID);
		List<DetailModel> details = detailService.listDetailsByOrderID(orderID);
		req.setAttribute("order", order);
		req.setAttribute("details", details);
		RequestDispatcher rd = req.getRequestDispatcher("/views/user/order/detailOrder.jsp");
		rd.forward(req, resp);
	}

	private void rating(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			String orderIDParam = req.getParameter("orderID");
			String itemIDParam = req.getParameter("itemID");
			String ratingParam = req.getParameter("rating");
			String quantityParam = req.getParameter("quantity");

			int itemID = Integer.parseInt(itemIDParam);
			int orderID = Integer.parseInt(orderIDParam);
			int quantity = Integer.parseInt(quantityParam);
			String reviewImage = ImageUploader.uploadImage(req);
			String reviewText = req.getParameter("reviewText");
			java.sql.Date evaluationDate = java.sql.Date.valueOf(LocalDate.now());
			int rating = Integer.parseInt(ratingParam);
			DetailModel review = new DetailModel();
			review.setItemID(itemID);
			review.setOrderID(orderID);
			review.setQuantity(quantity);
			review.setLink(reviewImage);
			review.setContent(reviewText);
			review.setEvaluationDate(evaluationDate);
			review.setRating(rating);
			detailService.updateDetail(review);
			resp.sendRedirect(req.getContextPath() + "/usertrack?id=" + orderID);
		} catch (Exception e) {
			resp.sendRedirect(req.getContextPath() + "/usertrack");
		}
	}
}
