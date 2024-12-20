package FurSPS.controller.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import FurSPS.models.UserModel;
import FurSPS.models.VoucherModel;
import FurSPS.service.IVoucherService;
import FurSPS.service.impl.VoucherServiceImpl;
import FurSPS.utils.MessageUtil;

@WebServlet(urlPatterns = { "/adminVoucher", "/adminInsertVoucher", "/adminUpdateVoucher" , "/adminDeleteVoucher"})
public class VoucherController extends HttpServlet {
	IVoucherService voucherService = new VoucherServiceImpl();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			UserModel user = (UserModel) session.getAttribute("user");
			if (user.getType() == 3) {
				if (url.contains("adminVoucher")) {
					findAllVoucher(req, resp);
				} else if (url.contains("adminInsertVoucher")) {
					RequestDispatcher rd = req.getRequestDispatcher("/views/admin/voucher/addVoucher.jsp");
					rd.forward(req, resp);
				} else if (url.contains("adminUpdateVoucher")) {
					getInforVoucher(req, resp);
				} else if (url.contains("adminDeleteVoucher")) {
					deleteVoucher(req, resp);
				}
			} else {
				resp.sendRedirect(req.getContextPath() + "/login");
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}

	private void getInforVoucher(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		int id = Integer.parseInt(req.getParameter("voucherID"));

		VoucherModel model = voucherService.findOne(id);

		req.setAttribute("voucher", model);
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/voucher/updateVoucher.jsp");
		rd.forward(req, resp);

	}

	private void findAllVoucher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<VoucherModel> listvoucher = voucherService.findAllVoucher();
		req.setAttribute("listvoucher", listvoucher);

		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/voucher/listvoucher.jsp");
		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		if (url.contains("adminInsertVoucher")) {
			insertVoucher(req, resp);
		} else if (url.contains("adminUpdateVoucher")) {
			updateVoucher(req, resp);
		}
	}

	private void updateVoucher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// thiet lap ngon ngu
			req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");

			// nhan du lieu tu form
			int id = Integer.parseInt(req.getParameter("voucherID"));
			String description = req.getParameter("description");
			int discount = Integer.parseInt(req.getParameter("discount"));
			int minimumPrice = Integer.parseInt(req.getParameter("minimumPrice"));
			int quantity = Integer.parseInt(req.getParameter("quantity"));
			String mfgString = req.getParameter("mfg");
			String expString = req.getParameter("exp");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng của ngày tháng
			Date mfg = null;
			Date exp = null;
			try {
				mfg = sdf.parse(mfgString); // Chuyển đổi kiểu chuỗi thành kiểu Date
				exp = sdf.parse(expString);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			VoucherModel newVoucher = new VoucherModel();
			newVoucher.setVoucherID(id);
			newVoucher.setDescription(description);
			newVoucher.setDiscount(discount);
			newVoucher.setMinimumPrice(minimumPrice);
			newVoucher.setQuantity(quantity);
			newVoucher.setMfg(mfg);
			newVoucher.setExp(exp);

			voucherService.updateVoucher(newVoucher);
			MessageUtil.showMessage(req, "updateSuccess");
		} catch (Exception ex) {
			MessageUtil.showMessage(req, "updateFail");
		}
		findAllVoucher(req, resp);
	}

	private void insertVoucher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// thiet lap ngon ngu
			req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");
			// nhan du lieu tu form

			String description = req.getParameter("description");
			int discount = Integer.parseInt(req.getParameter("discount"));
			int minimumPrice = Integer.parseInt(req.getParameter("minimumPrice"));
			int quantity = Integer.parseInt(req.getParameter("quantity"));
			String mfgString = req.getParameter("mfg");
			String expString = req.getParameter("exp");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng của ngày tháng
			Date mfg = null;
			Date exp = null;
			try {
				mfg = sdf.parse(mfgString); // Chuyển đổi kiểu chuỗi thành kiểu Date
				exp = sdf.parse(expString);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			// dua du lieu vao model
			VoucherModel newVoucher = new VoucherModel();

			newVoucher.setDescription(description);
			newVoucher.setDiscount(discount);
			newVoucher.setMinimumPrice(minimumPrice);
			newVoucher.setQuantity(quantity);
			newVoucher.setMfg(mfg);
			newVoucher.setExp(exp);

			// goi pt insert trong service
			voucherService.insertVoucher(newVoucher);
			MessageUtil.showMessage(req, "addSuccess");
		} catch (Exception ex) {
			MessageUtil.showMessage(req, "addFail");
		}
		findAllVoucher(req, resp);
	}
	
	private void deleteVoucher(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		try {
			int id = Integer.parseInt(req.getParameter("voucherID"));
			voucherService.deleteVoucher(id);
			MessageUtil.showMessage(req, "delSuccess");
		} catch (Exception ex) {
			// In lỗi ra console để kiểm tra
	        System.out.println("Error while deleting customer: " + ex.getMessage());
			MessageUtil.showMessage(req, "delFail");
		}
		RequestDispatcher rd = req.getRequestDispatcher("adminVoucher");
		rd.forward(req, resp);
	}
}
