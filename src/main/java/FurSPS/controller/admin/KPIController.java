package FurSPS.controller.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import FurSPS.models.submodels.MyItem;
import FurSPS.models.UserModel;
import FurSPS.service.IReportService;
import FurSPS.service.ISellerService;
import FurSPS.service.impl.ReportServiceImpl;
import FurSPS.service.impl.SellerServiceImpl;

@WebServlet(urlPatterns = { "/adminKPI", "/admin-SellerKPIYear" })
public class KPIController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ISellerService sellerService = new SellerServiceImpl();
	IReportService reportService = new ReportServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			UserModel user = (UserModel) session.getAttribute("user");
			if (user.getType() == 3) {
				if (url.contains("adminKPI")) {
					findBestSeller(req, resp);
				} else if (url.contains("admin-SellerKPIYear")) {
					showLineChartKPIYear(req, resp);
				}
			} else {
				resp.sendRedirect(req.getContextPath() + "/login");
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}

	private void showLineChartKPIYear(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("userID"));
		Date date = new Date();
		List<MyItem> listItem = reportService.reportKPISeller(date, id);
		req.setAttribute("listReceipt", listItem);
		List<MyItem> listProduct = reportService.reportBestItemSeller(id);
		req.setAttribute("listItem", listProduct);
		RequestDispatcher rd = req.getRequestDispatcher("adminKPI");
		rd.forward(req, resp);
	}

	private void findBestSeller(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<UserModel> listseller = sellerService.findBestSeller();
		req.setAttribute("listseller", listseller);
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/seller/kpi.jsp");
		rd.forward(req, resp);
	}
}
