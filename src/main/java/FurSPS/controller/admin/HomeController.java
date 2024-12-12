package FurSPS.controller.admin;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import FurSPS.models.submodels.MyItem;
import FurSPS.models.OrderModel;
import FurSPS.models.UserModel;
import FurSPS.service.IOrderService;
import FurSPS.service.IPaymentService;
import FurSPS.service.IProductService;
import FurSPS.service.IReportService;
import FurSPS.service.IUserService;
import FurSPS.service.impl.OrderServiceImpl;
import FurSPS.service.impl.PaymentServiceImpl;
import FurSPS.service.impl.ProductServiceImpl;
import FurSPS.service.impl.ReportServiceImpl;
import FurSPS.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = { "/adminHome" })
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IReportService reportService = new ReportServiceImpl();
	IOrderService orderService = new OrderServiceImpl();
	IUserService userService = new UserServiceImpl();
	IPaymentService paymentService = new PaymentServiceImpl();
	IProductService prod = new ProductServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI().toString();
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			UserModel user = (UserModel) session.getAttribute("user");
			if (user.getType() == 3) {

				if (url.contains("adminHome")) {

					List<OrderModel> listOrder = orderService.findAllOrder();
					List<OrderModel> listOrder1 = new ArrayList<>();
					List<List<Object>> listOrder2 = new ArrayList<>();
					List<List<Object>> listOrderSta = new ArrayList<>();
					List<List<Object>> listTotal = reportService.reportTotalMoneyInMonth();
					
					
					req.setAttribute("productrating", prod.ProductRating());
					
									
					
									
					int sumTotal = 0;
					int sumOrder = 0;
					int countPaymentCard = 0;
					int countPaymentNormal = 0;
					int totalPaymentCard = 0;
					int totalPayMentNormal = 0;
					int countNoPay = 0;
					
					
					session = req.getSession(true);
					
					
					Date currentDate = new Date();
					int monthNow = currentDate.getMonth() + 1;
					
//					// Thay vì sử dụng Date, ta sử dụng LocalDate để lấy tháng hiện tại
//	                LocalDate currentDate = LocalDate.now();
//	                int monthNow = currentDate.getMonthValue(); // Trả về tháng từ 1-12

					for (List<Object> list : listTotal) {
						sumTotal += (long) list.get(1);
						sumOrder += (int) list.get(2);
					}
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(currentDate); // Đặt ngày trong tuần về ngày đầu tiên (Chủ Nhật) và trừ đi số ngày
													// đã qua
//					calendar.setTime(new Date());	// trong tuần
//					calendar.setTime(new Date());	// trong tuần
					calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
					calendar.add(Calendar.DATE, -calendar.get(Calendar.DAY_OF_WEEK)); // Lấy ngày đầu tiên của tuần
					Date firstDayOfWeek = calendar.getTime();
					
					
					
					
					for (OrderModel list : listOrder) {
						//////////////////////////////////////////
						// Chuyển java.sql.Date sang LocalDate
//					    LocalDate orderLocalDate = new java.sql.Date(list.getOrderDate().getTime()).toInstant()
//					                                      .atZone(ZoneId.systemDefault())
//					                                      .toLocalDate();
//						// Chuyển java.sql.Date sang LocalDate
//					    java.sql.Date sqlDate = list.getOrderDate(); // Giả sử getOrderDate() trả về java.sql.Date
//					    Calendar calendar = Calendar.getInstance();
//					    calendar.setTime(sqlDate); // Chuyển sqlDate thành thời gian Calendar
//
//					    // Sau đó chuyển Calendar thành LocalDate
//					    LocalDate orderLocalDate = calendar.toInstant()
//					                                      .atZone(ZoneId.systemDefault())
//					                                      .toLocalDate();
//					    // Lấy tháng và năm từ LocalDate
//					    int orderMonth = orderLocalDate.getMonthValue();  // Tháng từ 1 đến 12
//					    int orderYear = orderLocalDate.getYear();         // Năm
						
						///////////////////////////////////////////
					    
					    if (list.getOrderDate().compareTo(firstDayOfWeek) > 0)
						//////////////////////////////////////////
						// Chuyển java.sql.Date sang LocalDate
//					    LocalDate orderLocalDate = new java.sql.Date(list.getOrderDate().getTime()).toInstant()
//					                                      .atZone(ZoneId.systemDefault())
//					                                      .toLocalDate();
//						// Chuyển java.sql.Date sang LocalDate
//					    java.sql.Date sqlDate = list.getOrderDate(); // Giả sử getOrderDate() trả về java.sql.Date
//					    Calendar calendar = Calendar.getInstance();
//					    calendar.setTime(sqlDate); // Chuyển sqlDate thành thời gian Calendar
//
//					    // Sau đó chuyển Calendar thành LocalDate
//					    LocalDate orderLocalDate = calendar.toInstant()
//					                                      .atZone(ZoneId.systemDefault())
//					                                      .toLocalDate();
//					    // Lấy tháng và năm từ LocalDate
//					    int orderMonth = orderLocalDate.getMonthValue();  // Tháng từ 1 đến 12
//					    int orderYear = orderLocalDate.getYear();         // Năm
						
						///////////////////////////////////////////
					    
					    if (list.getOrderDate().compareTo(firstDayOfWeek) > 0)
							listOrder1.add(list);
						
						
						if (list.getOrderDate().getMonth() > currentDate.getMonth() - 6
								&& list.getOrderDate().getYear() == currentDate.getYear()) {
							List<Object> row = new ArrayList<>();
							row.add((list.getOrderDate().getMonth()) + 1); // Ban đầu là (list.getOrderDate().getMonth()) + 1
							row.add((list.getOrderDate().getMonth()) + 1); // Ban đầu là (list.getOrderDate().getMonth()) + 1
							row.add(list.getStatus());
							listOrder2.add(row);
						}
						if (list.getOrderDate().getMonth() == currentDate.getMonth()
								&& list.getOrderDate().getYear() == currentDate.getYear()) {
							if (list.getPayment().getStatus() == 1) {
								if (list.getPayment().getMethod() == 1) {
									countPaymentCard += 1;
									totalPaymentCard += list.getTotalMoney();
								} else {
									countPaymentNormal += 1;
									totalPayMentNormal += list.getTotalMoney();
								}
							}
							if (list.getStatus() != 5 && list.getPayment().getStatus() == 0) {
								countNoPay += 1;
							}
						}
					}
					for (int i = monthNow - 6; i <= monthNow; i++) {
						List<Object> row = new ArrayList<>();
						row.add(i);
						int countHuy = 0;
						int countTC = 0;
						for (List<Object> list : listOrder2) {
							if ((int) list.get(1) == 5 && (int) list.get(0) == i) {
								countHuy += 1;
							}
							if ((int) list.get(1) == 4 && (int) list.get(0) == i) {
								countTC += 1;
							}
						}
						row.add(countTC);
						row.add(countHuy);
						listOrderSta.add(row);
					}

					int chXN = listOrder1.stream().filter(OrderModel -> OrderModel.getStatus() == 0)
							.collect(Collectors.toList()).size();
					int daXN = listOrder1.stream().filter(OrderModel -> OrderModel.getStatus() == 1)
							.collect(Collectors.toList()).size();
					int chDG = listOrder1.stream().filter(OrderModel -> OrderModel.getStatus() == 2)
							.collect(Collectors.toList()).size();
					int dVC = listOrder1.stream().filter(OrderModel -> OrderModel.getStatus() == 3)
							.collect(Collectors.toList()).size();
					int thCong = listOrder1.stream().filter(OrderModel -> OrderModel.getStatus() == 4)
							.collect(Collectors.toList()).size();
					int daHuy = listOrder1.stream().filter(OrderModel -> OrderModel.getStatus() == 5)
							.collect(Collectors.toList()).size();
					session.setAttribute("chXN", chXN);
					session.setAttribute("daXN", daXN);
					session.setAttribute("chDG", chDG);
					session.setAttribute("dVC", dVC);
					session.setAttribute("thCong", thCong);
					session.setAttribute("daHuy", daHuy);
					session.setAttribute("sumTotal", sumTotal);
					session.setAttribute("sumOrder", sumOrder);
					session.setAttribute("listPayMentInMonth", listTotal);
					session.setAttribute("listOrderSta", listOrderSta);
					session.setAttribute("countPaymentCard", countPaymentCard);
					session.setAttribute("countPaymentNormal", countPaymentNormal);
					session.setAttribute("totalPaymentCard", totalPaymentCard);
					session.setAttribute("totalPayMentNormal", totalPayMentNormal);
					session.setAttribute("countNoPay", countNoPay);
					req.setAttribute("listOrder", listOrder);

					Date date = new Date();
					List<MyItem> listItem = reportService.reportReceipt(date, 7);
					req.setAttribute("listReceipt", listItem);

					session.setAttribute("chDG", chDG);
					session.setAttribute("dVC", dVC);
					session.setAttribute("thCong", thCong);
					session.setAttribute("daHuy", daHuy);

					req.setAttribute("sumTotal", sumTotal);
					req.setAttribute("listPayMentInMonth", listTotal);

					RequestDispatcher rd = req.getRequestDispatcher("/views/admin/home.jsp");
					rd.forward(req, resp);
				}
			} else {
				resp.sendRedirect(req.getContextPath() + "/login");
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}
}