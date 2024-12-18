package FurSPS.controller.shipper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import FurSPS.dao.impl.ShipperDAOImpl;
import FurSPS.models.AccountModel;
import FurSPS.models.UserModel;
import FurSPS.service.IAccountService;
import FurSPS.service.IUserService;
import FurSPS.service.impl.AccountServiceImpl;
import FurSPS.service.impl.UserServiceImpl;
import FurSPS.other.ImageUploader;
import FurSPS.other.Assignment;

@WebServlet(urlPatterns = { "/shipper-info", "/shipper-update-info", "/shipper-update-avatar", "/shipper-update-pass" })
@MultipartConfig
public class PersonalInfoController extends HttpServlet {
	IUserService userService = new UserServiceImpl();
	IAccountService accountService = new AccountServiceImpl();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			UserModel user = (UserModel) session.getAttribute("user");
			if (user.getType() == 2) {
				updatesesstion(req, resp);
				String url = req.getRequestURI().toString();
				if (url.contains("shipper-info")) {
					showInfoPage(req, resp);
				} else if (url.contains("shipper-update-info")) {
					showUpdateInfoPage(req, resp);
				} else if (url.contains("shipper-update-avatar")) {
					updateInfAccount(req, resp);
				} else if (url.contains("shipper-update-pass")) {
					updatePassword(req, resp);
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
		if (url.contains("shipper-update-info")) {
			createUserModel(req, resp);
		} else if (url.contains("shipper-update-avatar")) {
			updateAvatar(req, resp);
		}else if (url.contains("shipper-update-pass")) {
			createAccountModel(req, resp);
		}else
			resp.sendRedirect("shipper-info");
	}

	private void showInfoPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		UserModel user = (UserModel) session.getAttribute("user");
		UserModel shipper = userService.getInfoUser(user.getUserID());

		req.setAttribute("user", shipper);
		RequestDispatcher rd = req.getRequestDispatcher("/views/shipper/infoShipper.jsp");
		rd.forward(req, resp);
	}

	private void showUpdateInfoPage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		UserModel user = (UserModel) session.getAttribute("user");
		UserModel shipper = userService.getInfoUser(user.getUserID());
		

		List<String> listAssign = new ArrayList<String>(Assignment.getAssign().keySet());
		req.setAttribute("listAssign", listAssign);
		req.setAttribute("shipper", shipper);
		RequestDispatcher rd = req.getRequestDispatcher("/views/shipper/updateInfoShipper.jsp");
		rd.forward(req, resp);
	}

	private void updateInfAccount(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int userID = Integer.parseInt(req.getParameter("userID"));
		AccountModel account = userService.getInfAccount(userID);
		req.setAttribute("accountModel", account);
		RequestDispatcher rd = req.getRequestDispatcher("/views/web/updateAccount.jsp");
		rd.forward(req, resp);
	}

	private void createUserModel(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int userID = Integer.parseInt(req.getParameter("userID"));
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String address = req.getParameter("address");
		int gender = Integer.parseInt(req.getParameter("gender"));
		String phone = req.getParameter("phone");
		String dobString = req.getParameter("dob");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dob = null;
		try {
			dob = sdf.parse(dobString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String cid = req.getParameter("cid");
		// int type = Integer.parseInt(req.getParameter("type"));
		String email = req.getParameter("email");
		// int kpi = Integer.parseInt(req.getParameter("KPI"));
		String area = req.getParameter("area");
		// String avatar = req.getParameter("avatar");

		UserModel user = userService.getInfoUser(userID);
		user.setUserID(userID);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAddress(address);
		user.setGender(gender);
		user.setPhone(phone);
		user.setDob(dob);
		user.setCid(cid);
		// user.setType(type);
		// user.setAvatar(avatar);
		// user.setKpi(kpi);
		user.setArea(area);
		user.setEmail(email);

		new ShipperDAOImpl().updateShipper(user);
		resp.sendRedirect("shipper-info");
	}

	private void createAccountModel(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int userID = Integer.parseInt(req.getParameter("UserID"));
		String userName = req.getParameter("UserName");
		String oldPassword = req.getParameter("OldPassWord");
		String password = req.getParameter("Password");

		if (accountService.checkPassword(userID, oldPassword)) {
			AccountModel newaccount = new AccountModel(userID, userName, password);
			userService.updateAccount(newaccount);
			resp.sendRedirect("shipper-info");
		} else {
			String error = "Mật khẩu cũ không đúng. Vui lòng nhập lại";
			req.setAttribute("message", error);
			req.setAttribute("accountModel", new AccountModel(userID, userName, password));  // Truyền lại accountModel
			req.getRequestDispatcher("/views/shipper/updatePass.jsp").forward(req, resp);
		}
	}

	private void updateAvatar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		HttpSession session = req.getSession();
		UserModel user = (UserModel) session.getAttribute("user");

		// Gọi hàm uploadImage mới
		try {
		    String avatarUrl = ImageUploader.uploadImage(req);  // Gọi hàm uploadImage để lấy URL ảnh đã upload

		    // Cập nhật avatar cho người dùng
		    userService.updateAvatar(user.getUserID(), avatarUrl);
		} catch (IOException | ServletException e) {
		    e.printStackTrace();
		    // Xử lý lỗi nếu cần
		}		
	}



	private void updatePassword(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserModel user = (UserModel) session.getAttribute("user");
		AccountModel account = userService.getInfAccount(user.getUserID());
		req.setAttribute("accountModel", account);
		RequestDispatcher rd = req.getRequestDispatcher("/views/shipper/updatePass.jsp");
		rd.forward(req, resp);
	}

	private void updatesesstion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserModel user = (UserModel) session.getAttribute("user");

		UserModel shipper = userService.getInfoUser(user.getUserID());
		HttpSession session1 = req.getSession();
		session1.setAttribute("user", shipper);

	}
}
