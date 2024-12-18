package FurSPS.controller.user;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import FurSPS.models.AccountModel;
import FurSPS.models.UserModel;
import FurSPS.service.IUserService;
import FurSPS.service.impl.UserServiceImpl;
import FurSPS.utils.MessageUtil;
import FurSPS.other.ImageUploader;

@WebServlet(urlPatterns = { "/user/infoUser", "/user/updateUser", "/user/updateAccount", "/user/updateAvatar" })
@MultipartConfig
public class PersonalInformationController extends HttpServlet {
	IUserService userService = new UserServiceImpl();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			String url = req.getRequestURI().toString();
			if (url.contains("infoUser")) {
				getInfUser(req, resp);
			} else if (url.contains("updateUser")) {
				updateInfUser(req, resp);
			} else if (url.contains("updateAccount")) {
				updateInfAccount(req, resp);
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
		if (url.contains("updateUser")) {
			createUserModel(req, resp);
			resp.sendRedirect("infoUser");
		} else if (url.contains("updateAccount")) {
			createAccountModel(req, resp);
			updateInfAccount(req,resp);
		} else if (url.contains("updateAvatar")) {
			updateAvatar(req, resp);
			resp.sendRedirect("infoUser");
		}
	}

	private void getInfUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		UserModel user = (UserModel) session.getAttribute("user");
		req.setAttribute("userModel", user);
		RequestDispatcher rd = req.getRequestDispatcher("/views/user/infoUser.jsp");
		rd.forward(req, resp);
	}

	private void updateInfUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int userID = Integer.parseInt(req.getParameter("userID"));
		UserModel user = userService.getInfoUser(userID);

		req.setAttribute("userModel", user);
		RequestDispatcher rd = req.getRequestDispatcher("/views/user/updateUser.jsp");
		rd.forward(req, resp);
	}

	private void updateInfAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		UserModel user = (UserModel) session.getAttribute("user");
		AccountModel account = userService.getInfAccount(user.getUserID());
		
		req.setAttribute("accountModel", account);
		RequestDispatcher rd = req.getRequestDispatcher("/views/user/updateAccount.jsp");
		rd.forward(req, resp);
	}

	private void createUserModel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int userID = Integer.parseInt(req.getParameter("UserID"));
		String firstName = req.getParameter("FirstName");
		String lastName = req.getParameter("LastName");
		String address = req.getParameter("Address");
		int gender = Integer.parseInt(req.getParameter("Gender"));
		String phone = req.getParameter("Phone");
		String dobString = req.getParameter("Dob");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dob = null;
		try {
			dob = sdf.parse(dobString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String cid = req.getParameter("Cid");
		int type = Integer.parseInt(req.getParameter("Type"));
		String email = req.getParameter("Email");
		int kpi = Integer.parseInt(req.getParameter("KPI"));
		String area = req.getParameter("Area");
		String avatar = req.getParameter("image");

		UserModel user = new UserModel();
		user.setUserID(userID);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAddress(address);
		user.setGender(gender);
		user.setPhone(phone);
		user.setDob(dob);
		user.setCid(cid);
		user.setType(type);
		user.setAvatar(avatar);
		user.setKpi(kpi);
		user.setArea(area);
		user.setEmail(email);

		userService.updateUser(user);
		HttpSession session = req.getSession(true);
		session.setAttribute("user",userService.getInfoUser(userID));
		getInfUser(req, resp);
	}

	private void createAccountModel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int userID = Integer.parseInt(req.getParameter("UserID"));
		String userName = req.getParameter("UserName");
		String oldPassword = req.getParameter("OldPassWord");
		String password = req.getParameter("Password");
		String retypePassword = req.getParameter("RetypePassword");
		AccountModel account = userService.getInfAccount(userID);

		if (!password.equals(retypePassword)) {
			MessageUtil.showMessage(req,"Password Not Match");
		}
		else if (userService.checkPassword(oldPassword, account.getPassword())) {
			AccountModel newaccount = new AccountModel(userID, userName, password);
			userService.updateAccount(newaccount);
			MessageUtil.showMessage(req,"updateAccountTrue");
		} else {
			MessageUtil.showMessage(req,"updateAccountFail");
		}
	}

	private void updateAvatar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int userID = Integer.parseInt(req.getParameter("UserID"));
		String imageUrl = ImageUploader.uploadImage(req);
		userService.updateAvatar(userID, imageUrl);
		HttpSession session = req.getSession(true);
		session.setAttribute("user",userService.getInfoUser(userID));
		getInfUser(req, resp);
	}
}
