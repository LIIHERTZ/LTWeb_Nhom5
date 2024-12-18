package FurSPS.controller.web;

import java.io.IOException;
import java.util.Random;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import FurSPS.models.AccountModel;
import FurSPS.service.IAccountService;
import FurSPS.service.ICustomerService;
import FurSPS.service.impl.AccountServiceImpl;
import FurSPS.service.impl.CustomerServiceImpl;

import FurSPS.other.SendMail;

@WebServlet(urlPatterns = { "/forgetpass", "/changepass" })
public class ForgetPassController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IAccountService accService = new AccountServiceImpl();
	ICustomerService cusService = new CustomerServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		if (url.contains("forgetpass"))
			showPageForget(req, resp);
		else if (url.contains("changepass"))
			showPageChangePass(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		if (url.contains("forgetpass"))
			checkEmailForget(req, resp);
		else if (url.contains("changepass"))
			changePassCus(req, resp);
	}

	private void showPageForget(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/views/web/forgetpass.jsp");
		rd.forward(req, resp);
	}

	private void showPageChangePass(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("verification")) {
					String verification = cookie.getValue();
					String code = req.getParameter("code");
					String formail = req.getParameter("formail");
					if (formail != null && verification.equals(code)) {
						req.setAttribute("formail", formail);
						RequestDispatcher rd = req.getRequestDispatcher("/views/web/changepass.jsp");
						rd.forward(req, resp);
					}

				}
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}

	private void checkEmailForget(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try {
			cusService.checkValidEmail(req.getParameter("formail"));
			req.removeAttribute("exception");
			sendForgetPassEmail(req, resp, req.getParameter("formail"));
			req.setAttribute("exception", "Vào email để lấy link đổi mật khẩu <br> Đường link chỉ tồn tại trong 5 phút");
			req.setAttribute("formail", req.getParameter("formail"));
			showPageForget(req, resp);

		} catch (IllegalArgumentException e) {
			req.setAttribute("exception", e.getMessage());
			req.setAttribute("formail", req.getParameter("formail"));
			showPageForget(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void changePassCus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String formail = req.getParameter("formail");
		String passchange = req.getParameter("passchange");
		String passcheck = req.getParameter("passcheck");
		if (passchange != null && passcheck != null && passchange.equals(passcheck)) {
			AccountModel acc = accService.findByEmail(formail);
			acc.setPassword(passchange);
			accService.updateAccount(acc);
			session.invalidate();
			resp.sendRedirect("login");
		} else {
			req.setAttribute("formail", formail);
			req.setAttribute("mess", "Mã khẩu xác nhận không trùng nhau");
			RequestDispatcher rd = req.getRequestDispatcher("/views/web/changepass.jsp");
			rd.forward(req, resp);
		}
	}

	private void sendForgetPassEmail(HttpServletRequest req, HttpServletResponse resp, String formail) {
		Random rnd = new Random();
		String verification = String.valueOf(rnd.nextInt(100000, 999999));
		SendMail.sendMail(formail, "link đổi password:\n"+ "http://localhost:8080" + req.getContextPath() + "/changepass?code="
				+ verification + "&formail=" + formail);
		Cookie vercookie = new Cookie("verification", verification);
		vercookie.setMaxAge(15 * 60);
		resp.addCookie(vercookie);
	}

}
