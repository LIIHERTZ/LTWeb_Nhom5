package FurSPS.controller.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import FurSPS.models.CartModel;
import FurSPS.models.ItemModel;
import FurSPS.models.UserModel;
import FurSPS.service.ICartService;
import FurSPS.service.IItemService;
import FurSPS.service.impl.CartServiceImpl;
import FurSPS.service.impl.ItemServiceImpl;

@WebServlet({ "/userCarts", "/userAddToCart", "/userDeleteCart", "/userDeleteCarts", "/userUpdateCart",
		"/userDeleteItem" })
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ICartService cartService = new CartServiceImpl();
	IItemService itemService = new ItemServiceImpl();
	RequestDispatcher rd = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String url = req.getRequestURI().toString();

		if (url.contains("userCarts")) {
			getAllCart(req, resp);
		} else if (url.contains("/userDeleteCarts")) {
			deleteCarts(req, resp);
		} else if (url.contains("/userDeleteCart")) {
			deleteCart(req, resp);
		} else if (url.contains("/userUpdateCart")) {
			updateCart(req, resp);
		}
	}

	private void updateCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int customerID = Integer.parseInt(req.getParameter("customerID"));
		int itemID = Integer.parseInt(req.getParameter("itemID"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));

		CartModel cart = new CartModel();
		cart.setCustomerID(customerID);
		cart.setItemID(itemID);

		ItemModel item = new ItemModel();
		item = itemService.findOne(itemID);
		if (item.getStock() >= quantity) {
			cart.setQuantity(quantity);
			cartService.update(cart);
		}
		resp.sendRedirect("userCarts");
	}

	private void deleteCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int customerID = Integer.parseInt(req.getParameter("customerID"));
		int itemID = Integer.parseInt(req.getParameter("itemID"));

		cartService.delete(customerID, itemID);
		resp.sendRedirect("userCarts");
	}

	private void deleteCarts(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession(true);
		if (session == null || session.getAttribute("user") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		UserModel user = (UserModel) session.getAttribute("user");
		int customerID = user.getUserID();
		cartService.deleteAllByCustomerID(customerID);
		resp.sendRedirect("userCarts");
	}

	private void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			int customerID = Integer.parseInt(req.getParameter("customerID"));
			int itemID = Integer.parseInt(req.getParameter("itemID"));

			cartService.delete(customerID, itemID);
			HttpSession session = req.getSession(true);
			if (session.getAttribute("user") != null) {
				UserModel user = (UserModel) session.getAttribute("user");
				List<CartModel> listCart = cartService.findByCustomerId(user.getUserID());
				int quantity = 0;
				int subTotal = 0;
				for (CartModel cart : listCart) {
					subTotal += cart.getTotalPrice();
					quantity += 1;
				}

				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> jsonResponse = new HashMap<>();
				jsonResponse.put("totalQuantity", quantity);
				jsonResponse.put("subTotal", subTotal);
				jsonResponse.put("carts", listCart);

				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(mapper.writeValueAsString(jsonResponse));
			}
		} catch (NumberFormatException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters");
			e.printStackTrace();
		} catch (Exception e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred");
			e.printStackTrace();
		}
	}

	private void getAllCart(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession(true);
		if (session == null || session.getAttribute("user") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		UserModel user = (UserModel) session.getAttribute("user");
		List<CartModel> listCart = cartService.findByCustomerId(user.getUserID());

		int subTotal = 0;
		for (CartModel cart : listCart) {
			subTotal += cart.getTotalPrice();
		}

		req.setAttribute("carts", listCart);
		req.setAttribute("subTotal", subTotal);
		rd = req.getRequestDispatcher("/views/web/carts/carts.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String url = req.getRequestURI().toString();
		if (url.contains("userAddToCart")) {
			addToCart(req, resp);
		} else if (url.contains("userDeleteItem")) {
			deleteItem(req, resp);
		}
	}

	private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession(true);
		if (session == null || session.getAttribute("user") == null) {
			resp.setContentType("application/json");
			resp.getWriter().write("{\"redirect\":\"" + req.getContextPath() + "/login\"}");
			return;
		}

		UserModel user = (UserModel) session.getAttribute("user");
		CartModel cart = new CartModel();
		CartModel oldCart = new CartModel();
		int customerID = user.getUserID();
		int itemID = Integer.parseInt(req.getParameter("selectedItemID"));
		int quantity = Integer.parseInt(req.getParameter("selectedQuantity"));
		int productID = Integer.parseInt(req.getParameter("productID"));

		cart.setCustomerID(customerID);
		cart.setItemID(itemID);
		oldCart = cartService.findOne(customerID, itemID);
		quantity += oldCart.getQuantity();
		cart.setQuantity(quantity);
		ItemModel item = new ItemModel();
		item = itemService.findOne(itemID);
		if (item.getStock() >= quantity) {
			if (oldCart.getQuantity() != 0) {
				cartService.update(cart);
			} else {
				cartService.insert(cart);
			}
			resp.setContentType("application/json");
		    resp.getWriter().write("{\"success\": true, \"redirect\": \"/FurSPS_Nhom5/user/products?id=" + productID + "\"}");
		}
		else {
		    resp.setContentType("application/json");
		    resp.getWriter().write("{\"success\": false, \"message\": \"Số lượng không đủ!\"}");
		}
	}
}
