package FurSPS.controller.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import FurSPS.models.submodels.*;
import FurSPS.models.AccountModel;
import FurSPS.models.UserModel;
import FurSPS.service.IAccountService;
import FurSPS.service.ICustomerService;
import FurSPS.service.IReportService;
import FurSPS.service.IUserService;
import FurSPS.service.impl.AccountServiceImpl;
import FurSPS.service.impl.CustomerServiceImpl;
import FurSPS.service.impl.ReportServiceImpl;
import FurSPS.service.impl.UserServiceImpl;
import FurSPS.utils.MessageUtil;

@WebServlet(urlPatterns = { "/adminCustomer", "/adminInsertCustomer", "/adminUpdateCustomer", "/adminDeleteCustomer",
		"/adminInformationCustomer" })
@MultipartConfig
public class CustomerController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ICustomerService customerService = new CustomerServiceImpl();
	IAccountService accountService = new AccountServiceImpl();
	IReportService reportService = new ReportServiceImpl();
	IUserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String url = req.getRequestURI().toString();
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			UserModel user = (UserModel) session.getAttribute("user");
			if (user.getType() == 3) {
				if (url.contains("adminCustomer")) {
					getAllCustomer(req, resp);
				} else if (url.contains("adminInsertCustomer")) {
					RequestDispatcher rd = req.getRequestDispatcher("/views/admin/customer/customerInsert.jsp");
					rd.forward(req, resp);
				} else if (url.contains("adminUpdateCustomer")) {
					getCustomerUpdate(req, resp);
				} else if (url.contains("adminDeleteCustomer")) {
					deleteCustomer(req, resp);
				} else if (url.contains("adminInformationCustomer")) {
					int customerID = Integer.parseInt(req.getParameter("customerID"));
					UserModel customer = customerService.getOneCustomer(customerID);
					req.setAttribute("user", customer);
					RequestDispatcher rd = req.getRequestDispatcher("/views/admin/customer/customerInformation.jsp");
					rd.forward(req, resp);
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

		if (url.contains("adminInsertCustomer")) {
			insertCustomer(req, resp);
		} else if (url.contains("adminUpdateCustomer")) {
			updateCustomer(req, resp);
		}
	}

	private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		try {
			int customerID = Integer.parseInt(req.getParameter("customerID"));
			UserModel customer = customerService.getOneCustomer(customerID);
			customerService.deleteCustomer(customer);
			MessageUtil.showMessage(req, "delSuccess");
		} catch (Exception ex) {
			MessageUtil.showMessage(req, "delFail");
		}
		RequestDispatcher rd = req.getRequestDispatcher("adminCustomer");
		rd.forward(req, resp);

	}

	private void getCustomerUpdate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int customerID = Integer.parseInt(req.getParameter("customerID"));
		UserModel customer = customerService.getOneCustomer(customerID);
		req.setAttribute("customer", customer);
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/customer/customerUpdate.jsp");
		rd.forward(req, resp);

	}

	private void updateCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int customerID = Integer.parseInt(req.getParameter("customerID"));
		try {

			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String address = req.getParameter("address");
			int gender = Integer.parseInt(req.getParameter("gender"));
			String phone = req.getParameter("phone");
			String avatar = req.getParameter("avatar");
			String cid = req.getParameter("cid");
			String email = req.getParameter("email");
			String dobString = req.getParameter("dob");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng của ngày tháng
			Date dob = null;
			try {
				dob = sdf.parse(dobString); // Chuyển đổi kiểu chuỗi thành kiểu Date
			} catch (ParseException e) {
				e.printStackTrace();
			}
			UserModel newUser = new UserModel();
			newUser.setUserID(customerID);
			newUser.setFirstName(firstName);
			newUser.setLastName(lastName);
			newUser.setGender(gender);
			newUser.setAvatar(avatar);
			newUser.setAddress(address);
			newUser.setPhone(phone);
			newUser.setDob(dob);
			newUser.setCid(cid);
			newUser.setEmail(email);
			customerService.updateCustomer(newUser);
			MessageUtil.showMessage(req, "updateSuccess");
		} catch (Exception ex) {
			MessageUtil.showMessage(req, "updateFail");
		}
		resp.sendRedirect("adminInformationCustomer?customerID=" + customerID);
	}

	private void insertCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			int customerID = customerService.createCustomerID();
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String address = req.getParameter("address");
			int gender = Integer.parseInt(req.getParameter("gender"));
			String phone = req.getParameter("phone");
			String avatar = req.getParameter("avatar");
			String cid = req.getParameter("cid");
			String email = req.getParameter("email");
			String dobString = req.getParameter("dob");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng của ngày tháng
			Date dob = null;
			try {
				dob = sdf.parse(dobString); // Chuyển đổi kiểu chuỗi thành kiểu Date
			} catch (ParseException e) {
				e.printStackTrace();
			}
			// dua du lieu vao model
			UserModel newUser = new UserModel();
			newUser.setUserID(customerID);
			newUser.setFirstName(firstName);
			newUser.setLastName(lastName);
			newUser.setGender(gender);
			newUser.setAvatar(avatar);
			newUser.setAddress(address);
			newUser.setPhone(phone);
			newUser.setDob(dob);
			newUser.setCid(cid);
			newUser.setEmail(email);

			boolean checkInsertCustomer = customerService.insertCustomer(newUser);
			if (checkInsertCustomer) {
				AccountModel accountMd = new AccountModel(customerID, "User" + customerID, "12345");
				accountService.insertAccount(accountMd);
			}
			MessageUtil.showMessage(req, "addSuccess");
		} catch (Exception ex) {
			MessageUtil.showMessage(req, "addFail");
		}
		getAllCustomer(req, resp);

	}

	private void getAllCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<UserModel> listCustomer = customerService.getAllCustomer();
		List<Top3Customer> list3 = reportService.reportTop3Customer();
		req.setAttribute("listCustomer", listCustomer);
		req.setAttribute("list3", list3);

		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/customer/customer.jsp");
		rd.forward(req, resp);

	}
}
