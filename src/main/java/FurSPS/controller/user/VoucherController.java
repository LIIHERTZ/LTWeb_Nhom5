package FurSPS.controller.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import FurSPS.models.UserModel;
import FurSPS.models.VoucherModel;
import FurSPS.utils.MessageUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import FurSPS.service.*;
import FurSPS.service.impl.*;

@WebServlet(urlPatterns = { "/listVoucher", "/searchVoucher" })
@MultipartConfig
public class VoucherController extends HttpServlet {

	IVoucherService voucherService = new VoucherServiceImpl();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			String url = req.getRequestURI().toString();
			if (url.contains("listVoucher")) {
				listVoucher(req, resp);
			} else if (url.contains("searchVoucher")) {
				searchVoucher(req, resp);
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}

	private void listVoucher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		UserModel user = (UserModel) session.getAttribute("user");
		List<VoucherModel> listVoucher = voucherService.findVoucherByCustomerID(user.getUserID());
		
		req.setAttribute("listVoucher", listVoucher);
		RequestDispatcher rd = req.getRequestDispatcher("/views/user/voucher/listVoucher.jsp");
		rd.forward(req, resp);

	}

	private void searchVoucher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String keyword = req.getParameter("keyword");
		HttpSession session = req.getSession(false);
		UserModel user = (UserModel) session.getAttribute("user");
		List<VoucherModel> listVoucher = new ArrayList<VoucherModel>();

		if (!voucherService.containsNonDigit(keyword)) {
			int voucherID = Integer.parseInt(keyword);
			VoucherModel voucher = voucherService.findOneByCustomerID(voucherID, user.getUserID());
			VoucherModel voucherEx = voucherService.findOne(voucherID);

			if (voucher.getVoucherID() == 0 && voucherEx.getVoucherID() == voucherID) {
				MessageUtil.showMessage(req, "searchVoucherNull");
				listVoucher(req, resp);
			} else if (voucher.getVoucherID() == 0) {
				MessageUtil.showMessage(req, "searchVoucherFail");
				listVoucher(req, resp);
			} else {
				listVoucher.add(voucher);
				req.setAttribute("listVoucher", listVoucher);
				RequestDispatcher rd = req.getRequestDispatcher("/views/user/voucher/listVoucher.jsp");
				rd.forward(req, resp);
			}
		} else {
			MessageUtil.showMessage(req, "searchVoucherFail");
			listVoucher(req, resp);
		}
	}
}