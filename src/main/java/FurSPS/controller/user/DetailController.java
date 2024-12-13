package FurSPS.controller.user;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import FurSPS.models.DetailModel;
import FurSPS.models.OrderModel;
import FurSPS.models.UserModel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import FurSPS.service.IDetailService;
import FurSPS.service.IOrderService;
import FurSPS.service.impl.DetailServiceImpl;
import FurSPS.service.impl.OrderServiceImpl;

@WebServlet("/submitReview") //giu nguyen anotation
public class DetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IDetailService detailService = new DetailServiceImpl();
	IOrderService orderService = new OrderServiceImpl();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		int itemID = Integer.parseInt(req.getParameter("itemID"));
		int orderID = Integer.parseInt(req.getParameter("orderID"));
		int rating = Integer.parseInt(req.getParameter("rating"));
		String content = req.getParameter("content");

		DetailModel detail = new DetailModel();
		detail.setItemID(itemID);
		detail.setOrderID(orderID);
		detail.setRating(rating);
		detail.setContent(content);
		detail.setEvaluationDate(new Date());
		detailService.updateDetail(detail);

		HttpSession session = req.getSession(false);
		UserModel user = (UserModel) session.getAttribute("user");
		List<OrderModel> listOrder = orderService.listOrderByCustomerID(user.getUserID());

		req.setAttribute("listOrder", listOrder);
		RequestDispatcher rd = req.getRequestDispatcher("/views/user/order/listOrder.jsp");
		rd.forward(req, resp);
	}
}
