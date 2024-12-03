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

import FurSPS.models.UserModel;
import FurSPS.other.ImageUploader;
import FurSPS.service.IShipperService;
import FurSPS.service.IUserService;
import FurSPS.service.impl.ShipperServiceImpl;
import FurSPS.service.impl.UserServiceImpl;
import FurSPS.utils.MessageUtil;

import FurSPS.models.AccountModel;
import FurSPS.service.IAccountService;
import FurSPS.service.impl.AccountServiceImpl;
@WebServlet(urlPatterns = { "/adminShipper", "/adminUpdateShipper", "/adminDeleteShipper", "/adminInsertShipper",
		"/adminInformationShipper", "/shipper-update-avatar" })

@MultipartConfig
public class ShipperController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IShipperService shipperService = new ShipperServiceImpl();
	IAccountService accountService = new AccountServiceImpl();
	IUserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			UserModel user = (UserModel) session.getAttribute("user");
			if (user.getType() == 3) {
				if (url.contains("adminShipper")) {
					findAllShipper(req, resp);
				} else if (url.contains("adminUpdateShipper")) {
					getInforShipper(req, resp);
				} else if (url.contains("adminDeleteShipper")) {
					deleteShipper(req, resp);
					RequestDispatcher rd = req.getRequestDispatcher("adminShipper");
					rd.forward(req, resp);
				} else if (url.contains("adminInsertShipper")) {
					RequestDispatcher rd = req.getRequestDispatcher("/views/admin/shipper/addShipper.jsp");
					rd.forward(req, resp);
				} else if (url.contains("adminInformationShipper")) {
					int userID = Integer.parseInt(req.getParameter("userID"));
					UserModel shipper = shipperService.findOne(userID);
					session.setAttribute("userID", userID);
					req.setAttribute("user", shipper);
					RequestDispatcher rd = req.getRequestDispatcher("/views/admin/shipper/informationShipper.jsp");
					rd.forward(req, resp);
				}
			} else {
				resp.sendRedirect(req.getContextPath() + "/login");
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}

	private void deleteShipper(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int id = Integer.parseInt(req.getParameter("userID"));
			shipperService.deleteShipper(id);
			MessageUtil.showMessage(req, "delSuccess");
		} catch (Exception ex) {
			MessageUtil.showMessage(req, "delFail");
		}
	}

	private void getInforShipper(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		int id = Integer.parseInt(req.getParameter("userID"));

		UserModel model = shipperService.findOne(id);

		req.setAttribute("shipper", model);
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/shipper/updateShipper.jsp");
		rd.forward(req, resp);

	}

	private void findAllShipper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<UserModel> listShipper = shipperService.findAllShipper();
		req.setAttribute("listshipper", listShipper);

		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/shipper/listShipper.jsp");
		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String url = req.getRequestURI().toString();

		if (url.contains("adminUpdateShipper")) {
			updateShipper(req, resp);
		} else if (url.contains("adminInsertShipper")) {
			insertShipper(req, resp);
		} else if (url.contains("shipper-update-avatar")) {
	        updateAvatar(req, resp);  // Gọi phương thức updateAvatar
	    }else
			resp.sendRedirect("adminInformationShipper");
	}

	private void insertShipper(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		try {
			// thiet lap ngon ngu
			req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");

			// nhan du lieu tu form
			int id = shipperService.createShipperID();
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String address = req.getParameter("address");
			int gender = Integer.parseInt(req.getParameter("gender"));
			String phone = req.getParameter("phone");
			String avatar = req.getParameter("avatar");
			String cid = req.getParameter("cid");
			String area = req.getParameter("area");
			String email = req.getParameter("email");
			String dobString = req.getParameter("dob");
			
			// Biểu thức chính quy để kiểm tra dữ liệu
	        String phonePattern = "^[0-9]+$"; // Chỉ chứa chữ số
	        String cidPattern = "^[0-9]+$"; // Chỉ chứa chữ số
	        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$"; // Kiểm tra email

	        // Kiểm tra số điện thoại
	        if (!phone.matches(phonePattern)) {
	            MessageUtil.showMessage(req, "phoneInvalid");
	            req.setAttribute("firstName", firstName);
	            req.setAttribute("lastName", lastName);
	            req.setAttribute("address", address);
	            req.setAttribute("gender", gender);
	            req.setAttribute("phone", phone);
	            req.setAttribute("avatar", avatar);
	            req.setAttribute("cid", cid);
	            req.setAttribute("email", email);
	            req.setAttribute("dob", dobString);
	            req.setAttribute("area", area);
	            req.getRequestDispatcher("/views/admin/shipper/addShipper.jsp").forward(req, resp);
	            return;
	        }

	        // Kiểm tra căn cước công dân
	        if (!cid.matches(cidPattern)) {
	            MessageUtil.showMessage(req, "cidInvalid");
	            req.setAttribute("firstName", firstName);
	            req.setAttribute("lastName", lastName);
	            req.setAttribute("address", address);
	            req.setAttribute("gender", gender);
	            req.setAttribute("phone", phone);
	            req.setAttribute("avatar", avatar);
	            req.setAttribute("cid", cid);
	            req.setAttribute("email", email);
	            req.setAttribute("dob", dobString);
	            req.setAttribute("area", area);
	            req.getRequestDispatcher("/views/admin/shipper/addShipper.jsp").forward(req, resp);
	            return;
	        }

	        // Kiểm tra email
	        if (!email.matches(emailPattern)) {
	            MessageUtil.showMessage(req, "emailInvalid");
	            req.setAttribute("firstName", firstName);
	            req.setAttribute("lastName", lastName);
	            req.setAttribute("address", address);
	            req.setAttribute("gender", gender);
	            req.setAttribute("phone", phone);
	            req.setAttribute("avatar", avatar);
	            req.setAttribute("cid", cid);
	            req.setAttribute("email", email);
	            req.setAttribute("dob", dobString);
	            req.setAttribute("area", area);
	            req.getRequestDispatcher("/views/admin/shipper/addShipper.jsp").forward(req, resp);
	            return;
	        }
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng của ngày tháng
			Date dob = null;
			try {
				dob = sdf.parse(dobString); // Chuyển đổi kiểu chuỗi thành kiểu Date
			} catch (ParseException e) {
				e.printStackTrace();
			}

			// dua du lieu vao model
			UserModel newUser = new UserModel();
			newUser.setUserID(id);
			newUser.setFirstName(firstName);
			newUser.setLastName(lastName);
			newUser.setGender(gender);
			newUser.setAvatar(avatar);
			newUser.setAddress(address);
			newUser.setPhone(phone);
			newUser.setDob(dob);
			newUser.setCid(cid);
			newUser.setType(2);
			newUser.setArea(area);
			newUser.setEmail(email);
			// goi pt insert trong service
			boolean checkInsertSeller = shipperService.insertShipper(newUser);
			if (checkInsertSeller) {
				AccountModel accountMd = new AccountModel(id, "Shipper" + id, "123");  // Đặt tên tài khoản theo định dạng Seller + ID
				accountService.insertAccount(accountMd);
			}
			MessageUtil.showMessage(req, "addSuccess");
		} catch (Exception ex) {
			MessageUtil.showMessage(req, "addFail");
		}
		findAllShipper(req, resp);
	}

	private void updateShipper(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("userID"));
		try {
			// thiet lap ngon ngu

			// nhan du lieu tu form

			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String address = req.getParameter("address");
			int gender = Integer.parseInt(req.getParameter("gender"));
			String phone = req.getParameter("phone");
//			String avatar = req.getParameter("avatar");
			String cid = req.getParameter("cid");
			String area = req.getParameter("area");
			String email = req.getParameter("email");
			String dobString = req.getParameter("dob");
			
			// Biểu thức chính quy để kiểm tra dữ liệu
	        String phonePattern = "^[0-9]+$"; // Chỉ chứa chữ số
	        String cidPattern = "^[0-9]+$"; // Chỉ chứa chữ số
	        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$"; // Biểu thức chính quy kiểm tra email

	        // Kiểm tra số điện thoại
	        if (!phone.matches(phonePattern)) {
	            MessageUtil.showMessage(req, "phoneInvalid");
	            getInforShipper(req, resp);
	            return;
	        }

	        // Kiểm tra căn cước công dân
	        if (!cid.matches(cidPattern)) {
	            MessageUtil.showMessage(req, "cidInvalid");
	            getInforShipper(req, resp);
	            return;
	        }

	        // Kiểm tra email
	        if (!email.matches(emailPattern)) {
	            MessageUtil.showMessage(req, "emailInvalid");
	            getInforShipper(req, resp);
	            return;
	        }
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng của ngày tháng
			Date dob = null;
			try {
				dob = sdf.parse(dobString); // Chuyển đổi kiểu chuỗi thành kiểu Date
			} catch (ParseException e) {
				e.printStackTrace();
			}

			UserModel newUser = new UserModel();
			newUser.setUserID(id);
			newUser.setFirstName(firstName);
			newUser.setLastName(lastName);
			newUser.setGender(gender);
//			newUser.setAvatar(avatar);
			newUser.setAddress(address);
			newUser.setPhone(phone);
			newUser.setDob(dob);
			newUser.setCid(cid);
			newUser.setArea(area);
			newUser.setEmail(email);

			shipperService.updateShipper(newUser);
			MessageUtil.showMessage(req, "updateSuccess");
		} catch (Exception ex) {
			MessageUtil.showMessage(req, "updateFail");
		}
		resp.sendRedirect("adminInformationShipper?userID=" + id);

	}
	
	
	private void updateAvatar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int id = (int) session.getAttribute("userID");
// Gọi hàm uploadImage mới
		try {
		    String avatarUrl = ImageUploader.uploadImage(req);  // Gọi hàm uploadImage để lấy URL ảnh đã upload

		    // Cập nhật avatar cho người dùng
		    userService.updateAvatar(id, avatarUrl);
		} catch (IOException | ServletException e) {
		    e.printStackTrace();
		    // Xử lý lỗi nếu cần
		}	
	}
	
}