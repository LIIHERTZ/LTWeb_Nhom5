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
import FurSPS.service.ISellerService;
import FurSPS.service.IUserService;
import FurSPS.service.impl.SellerServiceImpl;
import FurSPS.service.impl.UserServiceImpl;
import FurSPS.utils.MessageUtil;

import FurSPS.models.AccountModel;
import FurSPS.service.IAccountService;
import FurSPS.service.impl.AccountServiceImpl;

@WebServlet(urlPatterns = { "/adminSeller", "/adminUpdateSeller", "/adminDeleteSeller", "/adminInsertSeller",
		"/adminInformationSeller", "/seller-update-avatar" })
@MultipartConfig
public class SellerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ISellerService sellerService = new SellerServiceImpl();
	IAccountService accountService = new AccountServiceImpl();
	IUserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String url = req.getRequestURI().toString();
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			UserModel user = (UserModel) session.getAttribute("user");
			if (user.getType() == 3) {
				if (url.contains("adminSeller")) {
					findAllSeller(req, resp);
				} else if (url.contains("adminUpdateSeller")) {
					getInforSeller(req, resp);
				} else if (url.contains("adminDeleteSeller")) {
					deleteSeller(req, resp);
					RequestDispatcher rd = req.getRequestDispatcher("adminSeller");
					rd.forward(req, resp);
				} else if (url.contains("adminInsertSeller")) {
					RequestDispatcher rd = req.getRequestDispatcher("/views/admin/seller/addSeller.jsp");
					rd.forward(req, resp);
				} else if (url.contains("adminInformationSeller")) {
					int userID = Integer.parseInt(req.getParameter("userID"));
					UserModel seller = sellerService.findOne(userID);
					session.setAttribute("userID", userID);
					req.setAttribute("user", seller);
					RequestDispatcher rd = req.getRequestDispatcher("/views/admin/seller/informationSeller.jsp");
					rd.forward(req, resp);
				}
			} else {
				resp.sendRedirect(req.getContextPath() + "/login");
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}
private void deleteSeller(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int id = Integer.parseInt(req.getParameter("userID"));
			sellerService.deleteSeller(id);
			MessageUtil.showMessage(req, "delSuccess");
		} catch (Exception ex) {
			MessageUtil.showMessage(req, "delFail");
		}

	}

	private void getInforSeller(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		int id = Integer.parseInt(req.getParameter("userID"));

		UserModel model = sellerService.findOne(id);

		req.setAttribute("seller", model);
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/seller/updateSeller.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String url = req.getRequestURI().toString();

		if (url.contains("adminUpdateSeller")) {
			updateSeller(req, resp);
		} else if (url.contains("adminInsertSeller")) {
			insertSeller(req, resp);
		} else if (url.contains("seller-update-avatar")) {
	        updateAvatar(req, resp);  // Gọi phương thức updateAvatar
	    }
	}

	private void insertSeller(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		try {
			// thiet lap ngon ngu
			req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");
			// nhan du lieu tu form
			int id = sellerService.createSellerID();
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String address = req.getParameter("address");
			int gender = Integer.parseInt(req.getParameter("gender"));
			String phone = req.getParameter("phone");
			String avatar = req.getParameter("avatar");
			String cid = req.getParameter("cid");		
			String kpiStr = req.getParameter("kpi"); // Nhận giá trị KPI dạng chuỗi
			int kpi = 0;
			String email = req.getParameter("email");
			String dobString = req.getParameter("dob");
			
			// Biểu thức chính quy để kiểm tra dữ liệu
	        String phonePattern = "^[0-9]+$"; // Chỉ chứa chữ số
	        String cidPattern = "^[0-9]+$"; // Chỉ chứa chữ số
	        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$"; // Biểu thức chính quy kiểm tra email
	        String kpiPattern = "^[0-9]+$"; // Chỉ chứa chữ số cho KPI

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
	            req.setAttribute("kpi", kpi);
	            
	            req.getRequestDispatcher("/views/admin/seller/addSeller.jsp").forward(req, resp);
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
	            req.setAttribute("kpi", kpi);
	            
	            req.getRequestDispatcher("/views/admin/seller/addSeller.jsp").forward(req, resp);
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
	            req.setAttribute("kpi", kpi);
	            
	            req.getRequestDispatcher("/views/admin/seller/addSeller.jsp").forward(req, resp);
	            return;
	        }
	        
	     // Kiểm tra KPI
	        if (!kpiStr.matches(kpiPattern)) {
	            MessageUtil.showMessage(req, "kpiInvalid");
	            req.setAttribute("firstName", firstName);
	            req.setAttribute("lastName", lastName);
	            req.setAttribute("address", address);
	            req.setAttribute("gender", gender);
	            req.setAttribute("phone", phone);
	            req.setAttribute("avatar", avatar);
	            req.setAttribute("cid", cid);
	            req.setAttribute("email", email);
	            req.setAttribute("dob", dobString);
	            req.setAttribute("kpi", kpi);
	            
	            req.getRequestDispatcher("/views/admin/seller/addSeller.jsp").forward(req, resp);
	            return;
	        } else {
	            kpi = Integer.parseInt(kpiStr); // Chuyển đổi KPI thành kiểu int
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
			newUser.setType(1);
			newUser.setKpi(kpi);
			newUser.setEmail(email);
			// goi pt insert trong service
			boolean checkInsertSeller = sellerService.insertSeller(newUser);
			if (checkInsertSeller) {
	        AccountModel accountMd = new AccountModel(id, "Seller" + id, "12345");  // Đặt tên tài khoản theo định dạng Seller + ID
	        accountService.insertAccount(accountMd);
			}
	        
			MessageUtil.showMessage(req, "addSuccess");
		} catch (Exception ex) {
			MessageUtil.showMessage(req, "addFail");
		}
		findAllSeller(req, resp);
	}

	private void updateSeller(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
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
			String kpiStr = req.getParameter("kpi"); // Nhận giá trị KPI dạng chuỗi
			int kpi = 0;
			String dobString = req.getParameter("dob");
			String email = req.getParameter("email");
			
			
			// Biểu thức chính quy để kiểm tra dữ liệu
	        String phonePattern = "^[0-9]+$"; // Chỉ chứa chữ số
	        String cidPattern = "^[0-9]+$"; // Chỉ chứa chữ số
	        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$"; // Biểu thức chính quy kiểm tra email
	        String kpiPattern = "^[0-9]+$"; // Chỉ chứa chữ số cho KPI

	        // Kiểm tra số điện thoại
	        if (!phone.matches(phonePattern)) {
	            MessageUtil.showMessage(req, "phoneInvalid");
	            getInforSeller(req, resp);
	            return;
	        }

	        // Kiểm tra căn cước công dân
	        if (!cid.matches(cidPattern)) {
	            MessageUtil.showMessage(req, "cidInvalid");
	            getInforSeller(req, resp);
	            return;
	        }

	        // Kiểm tra email
	        if (!email.matches(emailPattern)) {
	            MessageUtil.showMessage(req, "emailInvalid");
	            getInforSeller(req, resp);
	            return;
	        }
	        
	     // Kiểm tra KPI
	        if (!kpiStr.matches(kpiPattern)) {
	            MessageUtil.showMessage(req, "kpiInvalid");
	            getInforSeller(req, resp);
	            return;
	        } else {
	        	kpi = Integer.parseInt(kpiStr); // Chuyển đổi KPI thành kiểu int
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
			newUser.setKpi(kpi);
			newUser.setEmail(email);

			sellerService.updateSeller(newUser);
			MessageUtil.showMessage(req, "updateSuccess");
		} catch (Exception ex) {
			MessageUtil.showMessage(req, "updateFail");
		}
		resp.sendRedirect("adminInformationSeller?userID=" + id);
	}

	private void findAllSeller(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<UserModel> listseller = sellerService.findAllSeller();
		req.setAttribute("listseller", listseller);

		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/seller/listSeller.jsp");
		rd.forward(req, resp);
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