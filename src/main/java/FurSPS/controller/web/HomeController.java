package FurSPS.controller.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import FurSPS.models.CategoryModel;
import FurSPS.service.ICartService;
import FurSPS.service.ICategoryService;
import FurSPS.service.IDetailService;
import FurSPS.service.impl.CartServiceImpl;
import FurSPS.service.impl.CategoryServiceImpl;
import FurSPS.service.impl.DetailServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/home")
public class HomeController extends HttpServlet {

	
	private static final long serialVersionUID = 4317368494648713183L;
	IDetailService detailService = new DetailServiceImpl();
	ICartService cartService = new CartServiceImpl();
	ICategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Map<String, Object>> listBestSeller = detailService.listBestSeller();
		List<CategoryModel> listRootCategory = categoryService.getRootCategories();
		getServletContext().setAttribute("list", listBestSeller);
		getServletContext().setAttribute("rootCategories", listRootCategory);
		RequestDispatcher rd = req.getRequestDispatcher("/views/web/home.jsp");
		rd.forward(req, resp);
	}
}
