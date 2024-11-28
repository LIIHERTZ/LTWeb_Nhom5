package FurSPS.controller.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import FurSPS.models.AccountModel;
import FurSPS.models.UserModel;
import FurSPS.service.IAccountService;
import FurSPS.service.impl.AccountServiceImpl;
import FurSPS.utils.MessageUtil;

@WebServlet(urlPatterns = { "/adminAccount", "/adminInsertAccount", "/adminUpdateAccount", "/adminDeleteAccount" })
public class AccountController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IAccountService accountService = new AccountServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI().toString();
		
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			UserModel user = (UserModel) session.getAttribute("user");
			if (user.getType() == 3) {
				if (url.contains("adminAccount")) {
					getAllAccount(req, resp);
				} else if (url.contains("adminInsertAccount")) {
					RequestDispatcher rd = req.getRequestDispatcher("/views/admin/account/accountInsert.jsp");
					rd.forward(req, resp);
				} else if (url.contains("adminUpdateAccount")) {
					getAccountUpdate(req, resp);

				} else if (url.contains("adminDeleteAccount")) {
					deleteAccount(req, resp);
				}

			} else {
				resp.sendRedirect(req.getContextPath() + "/login");
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI().toString();
		if (url.contains("adminInsertAccount")) {
			insertAccount(req, resp);
		}
		if (url.contains("adminUpdateAccount")) {
			updateAccount(req, resp);
		}
	}

	private void insertAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//		try {
//			int userID = Integer.parseInt(req.getParameter("userID"));
//			String userName = req.getParameter("userName");
//			String password = req.getParameter("password");
//			AccountModel account = new AccountModel(userID, userName, password);
//			accountService.insertAccount(account);
//			MessageUtil.showMessage(req, "addSuccess");
//		} catch (Exception ex) {
//			MessageUtil.showMessage(req, "addFail");
//		}
//		getAllAccount(req, resp);
		try {
	        int userID = Integer.parseInt(req.getParameter("userID"));
	        String userName = req.getParameter("userName");
	        String password = req.getParameter("password");

	        // Kiểm tra xem người dùng đã tồn tại chưa
	        if (!accountService.isUserExist(userID)) {
	            MessageUtil.showMessage(req, "userExist");  // Hiển thị thông báo người dùng không tồn tại
	        } else if (accountService.isUserExistInAccount(userID)) {
	            // Kiểm tra nếu UserID đã tồn tại trong bảng Account
	            MessageUtil.showMessage(req, "userExistInAccount");  // Hiển thị thông báo người dùng đã tồn tại trong Account
	        } 
	        else {
	            AccountModel account = new AccountModel(userID, userName, password);
	            accountService.insertAccount(account);
	            MessageUtil.showMessage(req, "addSuccess");
	        }
	    } catch (Exception ex) {
	        MessageUtil.showMessage(req, "addFail");
	    }
	    getAllAccount(req, resp);
	}

	private void deleteAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			int userID = Integer.parseInt(req.getParameter("userID"));
			AccountModel account = accountService.getOneAccount(userID);
			accountService.deleteAccount(account);
			MessageUtil.showMessage(req, "delSuccess");
		} catch (Exception ex) {
			MessageUtil.showMessage(req, "delFail");
		}
		resp.sendRedirect("adminAccount");

	}

	private void getAccountUpdate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int userID = Integer.parseInt(req.getParameter("userID"));
		AccountModel account = accountService.getOneAccount(userID);

		req.setAttribute("account", account);
		req.getRequestDispatcher("/views/admin/account/accountUpdate.jsp").forward(req, resp);
	}

	private void updateAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		try {
			int userID = Integer.parseInt(req.getParameter("userID"));
			String userName = req.getParameter("userName");
			String password = req.getParameter("password");
			AccountModel account = new AccountModel(userID, userName, password);
			accountService.updateAccount(account);
			MessageUtil.showMessage(req, "updateSuccess");
		} catch (Exception ex) {
			MessageUtil.showMessage(req, "updateFail");
		}

		getAllAccount(req, resp);

	}

	private void getAllAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<AccountModel> listAccount = accountService.getAllAccount();
		req.setAttribute("listAccount", listAccount);
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/account/account.jsp");
		rd.forward(req, resp);

	}

}
