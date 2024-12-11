package FurSPS.controller.user;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import FurSPS.models.CartModel;
import FurSPS.models.CategoryModel;
import FurSPS.models.UserModel;
import FurSPS.service.ICartService;
import FurSPS.service.ICategoryService;
import FurSPS.service.IDetailService;
import FurSPS.service.impl.CartServiceImpl;
import FurSPS.service.impl.CategoryServiceImpl;
import FurSPS.service.impl.DetailServiceImpl;

@WebServlet(urlPatterns = "/user/home")
public class HomeController extends HttpServlet {

	IDetailService detailService = new DetailServiceImpl();
	ICartService cartService = new CartServiceImpl();
	ICategoryService categoryService = new CategoryServiceImpl();
	
	private static final long serialVersionUID = 4317368494648713183L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		
		if (session.getAttribute("user") != null) {
			UserModel user = (UserModel) session.getAttribute("user");
			List<CartModel> listCart = cartService.findByCustomerId(user.getUserID());
			int quantity =0;
			int subTotal = 0;
			for (CartModel cart : listCart) {
				subTotal += cart.getTotalPrice();
				quantity += 1;
			}
			getServletContext().setAttribute("totalQuantity", quantity);
			getServletContext().setAttribute("carts", listCart);
			getServletContext().setAttribute("subTotal", subTotal);
		}
		List<Map<String, Object>> listBestSeller = detailService.listBestSeller();
		List<CategoryModel> listRootCategory = categoryService.getRootCategories();
		getServletContext().setAttribute("list", listBestSeller);
		getServletContext().setAttribute("rootCategories", listRootCategory);
		RequestDispatcher rd = req.getRequestDispatcher("/views/user/home.jsp");
		rd.forward(req, resp);
	}
}
