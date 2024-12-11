package FurSPS.controller.user;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import FurSPS.models.CartModel;
import FurSPS.models.CategoryModel;
import FurSPS.models.DetailModel;
import FurSPS.models.ProductModel;
import FurSPS.models.SearchHistoryModel;
import FurSPS.models.UserModel;
import FurSPS.models.submodels.CategoryLevelModel;
import FurSPS.models.submodels.RatingModel;
import FurSPS.service.ICartService;
import FurSPS.service.ICategoryService;
import FurSPS.service.ICustomerService;
import FurSPS.service.IDetailService;
import FurSPS.service.IProductService;
import FurSPS.service.IRatingService;
import FurSPS.service.ISearchHistoryService;
import FurSPS.service.impl.CartServiceImpl;
import FurSPS.service.impl.CategoryServiceImpl;
import FurSPS.service.impl.CustomerServiceImpl;
import FurSPS.service.impl.DetailServiceImpl;
import FurSPS.service.impl.ProductServiceImpl;
import FurSPS.service.impl.RatingServiceImpl;
import FurSPS.service.impl.SearchHistoryServiceImpl;

@WebServlet(urlPatterns = { "/user/products", "/user/search" })
@MultipartConfig
public class ProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	IProductService productService = new ProductServiceImpl();
	ICustomerService customerService = new CustomerServiceImpl();
	ICategoryService categoryService = new CategoryServiceImpl();

	ISearchHistoryService searchHistoryService = new SearchHistoryServiceImpl();
	ICartService cartService = new CartServiceImpl();
	IDetailService detailService = new DetailServiceImpl();
	IRatingService ratingService = new RatingServiceImpl();
	RequestDispatcher rd = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		showHistorySearch(req, resp);
		String url = req.getRequestURI().toString();
		if (url.contains("products")) {
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

			String idString = req.getParameter("id");
			if (idString != null) {
				int id = Integer.parseInt(req.getParameter("id"));
				ProductModel productModel = productService.findOne(id);

				List<ProductModel> cateProList = productService.findByCategoryID(productModel.getCategoryID());
				List<ProductModel> supProList = productService.findBySupplierID(productModel.getSupplierID());
				List<DetailModel> detailList = detailService.findDetailByProductID(productModel.getProductID());
				List<RatingModel> ratingList = ratingService.findRatinglByProductID(productModel.getProductID());

				req.setAttribute("ratingList", ratingList);
				req.setAttribute("detailList", detailList);
				req.setAttribute("cateProList", cateProList);
				req.setAttribute("supProList", supProList);
				req.setAttribute("product", productModel);

				rd = req.getRequestDispatcher("/views/user/products/productdetail.jsp");
			} else {
				String cateIdString = req.getParameter("cateId");
				String pageString = req.getParameter("page");
				int page = pageString != null ? Integer.parseInt(pageString) : 1;
				int pageSize = 6;
				List<ProductModel> listProduct = new ArrayList<ProductModel>();
				List<CategoryModel> listRootCategory = categoryService.getRootCategories();
				List<CategoryLevelModel> listCategoryLevel = categoryService.getCategoryLevels();

				if (cateIdString != null) {
					int cateId = Integer.parseInt(cateIdString);
					List<CategoryModel> listCateChild = categoryService.geChidlCategories(cateId);
					CategoryModel categoryModel = categoryService.findOne(cateId);
					CategoryModel rootCategory = categoryService.findRootCategoryByCategoryId(cateId);
					listProduct = productService.findByCategoryID(cateId);

					req.setAttribute("childCategories", listCateChild);
					req.setAttribute("category", categoryModel);
					req.setAttribute("rootcategory", rootCategory);
					req.setAttribute("levelCategories", listCategoryLevel);
				} else {
					listProduct = productService.findAll();
				}

				req.setAttribute("cateId", cateIdString);
				String filterPrice = req.getParameter("price");
				String filterRating = req.getParameter("rating");
				String sortProduct = req.getParameter("sort");
				listProduct = filterAndSortProduct(listProduct, filterPrice, filterRating, sortProduct);

				int totalPage = listProduct.size() > 0 ? ((listProduct.size() / pageSize)+1) : 0;
				int start = (page - 1) * pageSize;
				int end = Math.min(start + pageSize, listProduct.size());

				req.setAttribute("price", filterPrice);
				req.setAttribute("sort", sortProduct);
				req.setAttribute("rating", filterRating);

				req.setAttribute("products", listProduct.subList(start, end));
				req.setAttribute("page", page);
				req.setAttribute("totalPage", totalPage);
				req.setAttribute("rootCategories", listRootCategory);
				req.setAttribute("levelCategories", listCategoryLevel);

				rd = req.getRequestDispatcher("/views/user/products/products.jsp");
			}

			rd.forward(req, resp);
		} else if (url.contains("/search")) {
			List<ProductModel> listProduct = new ArrayList<ProductModel>();
			String keyword = req.getParameter("keyword");
			HttpSession session = req.getSession();
			if (keyword != null) {
				if (session != null && session.getAttribute("user") != null) {
					UserModel user = (UserModel) session.getAttribute("user");
					SearchHistoryModel historySearch = new SearchHistoryModel();
					historySearch.setCustomerID(user.getUserID());
					historySearch.setCreatedAt(new Timestamp(new Date().getTime()));
					historySearch.setHistory(keyword);
					searchHistoryService.insertSearchHistory(historySearch);
				}
				listProduct = productService.searchProductByName(keyword);

			}
			String filterPrice = req.getParameter("price");
			String filterRating = req.getParameter("rating");
			String sortProduct = req.getParameter("sort");
			listProduct = filterAndSortProduct(listProduct, filterPrice, filterRating, sortProduct);

			req.setAttribute("keyword", keyword);
			req.setAttribute("price", filterPrice);
			req.setAttribute("sort", sortProduct);
			req.setAttribute("rating", filterRating);
			req.setAttribute("products", listProduct);
			req.getRequestDispatcher("/views/user/products/products.jsp").forward(req, resp);
		}
	}

	private void showHistorySearch(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session != null && session.getAttribute("user") != null) {
			UserModel user = (UserModel) session.getAttribute("user");
			List<SearchHistoryModel> searchHistory = searchHistoryService.getHistorySearchByCID(user.getUserID());
			req.setAttribute("history", searchHistory);
		}
	}

	private List<ProductModel> filterAndSortProduct(List<ProductModel> listProduct, String filterPrice, String rating,
			String sortProduct) {
		if (filterPrice != null && !filterPrice.isEmpty()) {
			listProduct = filterByPrice(listProduct, filterPrice);
		}
		if (rating != null && !rating.isEmpty()) {
			listProduct = filterByRating(listProduct, rating);
		}
		if (sortProduct != null && !sortProduct.isEmpty()) {
			listProduct = sortProduct(listProduct, sortProduct);
		}
		return listProduct;
	}

	private List<ProductModel> filterByPrice(List<ProductModel> listProduct, String priceRange) {
		String[] priceRangeValue = priceRange.split("-");
		int minPrice = Integer.parseInt(priceRangeValue[0]);
		int maxPrice = Integer.parseInt(priceRangeValue[1]);
		return listProduct.stream().filter(ProductModel -> ProductModel.getDisplayedOriginalPrice() >= minPrice
				&& ProductModel.getDisplayedOriginalPrice() <= maxPrice).collect(Collectors.toList());

	}

	private List<ProductModel> filterByRating(List<ProductModel> listProduct, String rating) {
		Double rate = Double.parseDouble(rating);
		return listProduct.stream().filter(ProductModel -> ProductModel.getAvgRating() >= rate)
				.collect(Collectors.toList());
	}

	private List<ProductModel> sortProduct(List<ProductModel> listProduct, String sortProduct) {
		if (sortProduct.equals("asc")) {
			Collections.sort(listProduct, Comparator.comparing(ProductModel::getDisplayedOriginalPrice));
		} else {
			Collections.sort(listProduct, Comparator.comparing(ProductModel::getDisplayedOriginalPrice).reversed());
		}
		return listProduct;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
