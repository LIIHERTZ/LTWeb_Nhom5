package FurSPS.controller.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import FurSPS.models.AddressModel;
import FurSPS.models.CartModel;
import FurSPS.models.ItemModel;
import FurSPS.models.UserModel;
import FurSPS.other.City;
import FurSPS.service.IAddressService;
import FurSPS.service.impl.AddressServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet({ "/userAddress", "/userDeleteAddress", "/userUpdateAddress", "/userAddAddress" })
public class AddressController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IAddressService addressService = new AddressServiceImpl();
	RequestDispatcher rd = null;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String url = req.getRequestURI().toString();

		if (url.contains("userAddress")) {
			getAllAddress(req, resp);
		} else if (url.contains("userAddAddress")) {
			showPageAddAddress(req, resp);
		} else if (url.contains("userUpdateAddress")) {
			showPageUpdateAddress(req, resp);
		} else if (url.contains("userDeleteAddress")) {
			deleteAddress(req, resp);
		}
	}

	private void showPageUpdateAddress(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		HttpSession session = req.getSession(true);
		if (session == null || session.getAttribute("user") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		int userID = Integer.parseInt(req.getParameter("userID"));
		String city = req.getParameter("city");
		String detail = req.getParameter("detail");
		AddressModel address = addressService.findExistAddress(userID, city, detail);
		req.setAttribute("address", address);
		List<String> listcity = City.getListCity();
		req.setAttribute("listcity", listcity);
		RequestDispatcher rd = req.getRequestDispatcher("/views/user/address/updateAddress.jsp");
		rd.forward(req, resp);

	}

	private void showPageAddAddress(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		HttpSession session = req.getSession(true);
		if (session == null || session.getAttribute("user") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		List<String> listcity = City.getListCity();
		req.setAttribute("listcity", listcity);
		RequestDispatcher rd = req.getRequestDispatcher("/views/user/address/addAddress.jsp");
		rd.forward(req, resp);

	}

	private void getAllAddress(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession(true);
		if (session == null || session.getAttribute("user") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		UserModel user = (UserModel) session.getAttribute("user");
		List<AddressModel> listAddress = addressService.findByCustomerId(user.getUserID());
		req.setAttribute("listAddress", listAddress);
		rd = req.getRequestDispatcher("/views/user/address/address.jsp");
		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String url = req.getRequestURI().toString();
		if (url.contains("userAddAddress")) {
			addAddress(req, resp);
		} else if (url.contains("userUpdateAddress")) {
			updateAddress(req, resp);
		}
	}

	private void updateAddress(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		int userID = Integer.parseInt(req.getParameter("userID"));
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String city = req.getParameter("city");
		String detail = req.getParameter("detail");

		AddressModel address = new AddressModel();
		address.setName(name);
		address.setUserID(userID);
		address.setPhone(phone);
		address.setCity(city);
		address.setDetail(detail);

		
		addressService.updateAddress(address);
		resp.sendRedirect("userAddress");

	}

	private void deleteAddress(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		int userID = Integer.parseInt(req.getParameter("userID"));
		String city = req.getParameter("city");
		String detail = req.getParameter("detail");

		addressService.deleteAddress(userID, city, detail);
		resp.sendRedirect("userAddress");

	}

	private void addAddress(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession(true);
		if (session == null || session.getAttribute("user") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		UserModel user = (UserModel) session.getAttribute("user");
		AddressModel address = new AddressModel();
		int userID = user.getUserID();
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String city = req.getParameter("city");
		String detail = req.getParameter("detail");

		address.setName(name);
		address.setUserID(userID);
		address.setPhone(phone);
		address.setCity(city);
		address.setDetail(detail);

		AddressModel add = addressService.findExistAddress(userID, city, detail);
		if (add.getCity() != null) {
			req.setAttribute("mess", "Địa chỉ đã tồn tại!!!");
			showPageAddAddress(req, resp);
		} else {
			addressService.addAddress(address);
			resp.sendRedirect("userAddress");
		}

	}

}
