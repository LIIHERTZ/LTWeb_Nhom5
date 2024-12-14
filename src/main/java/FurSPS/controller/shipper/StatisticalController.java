package FurSPS.controller.shipper;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import FurSPS.dao.impl.OrderDAOImpl;
import FurSPS.models.UserModel;
import FurSPS.service.IOrderService;
import FurSPS.service.IShipperService;
import FurSPS.service.impl.OrderServiceImpl;
import FurSPS.service.impl.ShipperServiceImpl;

@WebServlet(urlPatterns = { "/shipper-satistical" })
public class StatisticalController extends HttpServlet {

	IOrderService orderService = new OrderServiceImpl();
	IShipperService shipperDAO = new ShipperServiceImpl();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			UserModel user = (UserModel) session.getAttribute("user");
			if (user.getType() == 2) {
				Object[] list1 = new OrderDAOImpl().findKPIByShipper(user.getUserID());
				// Object[] list2 = new OrderDAOImpl().findCateForShipper(user.getUserID());
				List<Object[]> list3 = new OrderDAOImpl().findCateForShipper(user.getUserID());

				req.setAttribute("list1", list1);
				// req.setAttribute("list2", list2);
				req.setAttribute("list3", list3);
				RequestDispatcher rd = req.getRequestDispatcher("/views/shipper/statistical.jsp");
				rd.forward(req, resp);
			} else {
				resp.sendRedirect(req.getContextPath() + "/login");
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}

	}
}