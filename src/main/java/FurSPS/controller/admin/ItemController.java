package FurSPS.controller.admin;

import java.io.IOException;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.util.List;
import FurSPS.models.ItemImageModel;
import FurSPS.models.ItemModel;
import FurSPS.models.UserModel;
import FurSPS.service.IItemImageService;
import FurSPS.service.IItemService;
import FurSPS.service.impl.ItemImageServiceImpl;
import FurSPS.service.impl.ItemServiceImpl;
//import FurSPS.other.UploadImage;
import FurSPS.utils.MessageUtil;
import FurSPS.other.ImageUploader;
import java.io.InputStream;

@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,  // 1MB
	    maxFileSize = 1024 * 1024 * 5,   // 5MB
	    maxRequestSize = 1024 * 1024 * 5
	)
@WebServlet(urlPatterns = { "/adminItem", "/admininsertItem", "/admindeleteItem", "/adminupdateItem", "/adminviewItem",
		"/updateimage" })
public class ItemController extends HttpServlet {

	IItemService item = new ItemServiceImpl();
	IItemImageService itemImage = new ItemImageServiceImpl();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI().toString();
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			UserModel user = (UserModel) session.getAttribute("user");
			if (user.getType() == 3) {
				if (url.contains("adminItem")) {
					List(req, resp);
				} else if (url.contains("admininsertItem")) {
					insert(req, resp);
				} else if (url.contains("admindeleteItem")) {
					delete(req, resp);
				} else if (url.contains("adminupdateItem")) {
					update(req, resp);
				} else if (url.contains("adminviewItem")) {
					view(req, resp);
				} else if (url.contains("updateimage")) {
					deleteimage(req, resp);
				}
			} else {
				resp.sendRedirect(req.getContextPath() + "/login");
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}

	private void deleteimage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int Id = Integer.parseInt(req.getParameter("ItemID"));
		itemImage.deleteItemImage(Id);
		resp.sendRedirect("adminupdateItem?ItemID=" + Id);
	}

	private void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int ID = Integer.parseInt(req.getParameter("ProductID"));
		req.setAttribute("listItem", item.findAllByProductID(ID));
		req.setAttribute("ProID", ID);
		req.getRequestDispatcher("/views/admin/item/ListItem.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("admininsertItem")) {
			postinsert(req, resp);
		} else if (url.contains("adminupdateItem")) {
			postupdate(req, resp);
		}
	}

	private void postupdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Part> parts = (List<Part>) req.getParts();
		
		ItemModel itemModel = new ItemModel();

		itemModel.setItemID(Integer.parseInt(req.getParameter("itemID")));
		itemModel.setProductID(Integer.parseInt(req.getParameter("productID")));
		itemModel.setColor(req.getParameter("color"));
		itemModel.setColorCode(req.getParameter("colorCode"));
		itemModel.setSize(req.getParameter("size"));
		itemModel.setStock(Integer.parseInt(req.getParameter("stock")));
		itemModel.setOriginalPrice(Integer.parseInt(req.getParameter("originalPrice")));
		itemModel.setPromotionPrice(Integer.parseInt(req.getParameter("promotionPrice")));
		item.updateItem(itemModel);

//		Image(req, resp, itemModel);
		
		// Xử lý upload ảnh
	    for (Part part : parts) {
	        if (part.getName().equals("file") && part.getSize() > 0) {  // Kiểm tra nếu phần này là ảnh và có kích thước hợp lệ
//	        	String fileName = part.getSubmittedFileName();
//	        	System.out.println("Tên tệp: " + fileName);
//	            System.out.println("Kích thước tệp: " + part.getSize());
	        	
	        	try {
	        		
	        		// Lấy tên tệp và nội dung tệp
	                String fileName = part.getSubmittedFileName();
	                InputStream fileContent = part.getInputStream();
	                
	                // Tạo đối tượng ItemImageModel mới
	                ItemImageModel itemImageModel = new ItemImageModel();
	                itemImageModel.setItemID(itemModel.getItemID());

	                // Tạo ID mới cho ảnh
	                int ImageID = itemImage.CreateItemimageID(itemModel.getItemID()); 
	                itemImageModel.setItemimageID(ImageID);

	                // Sử dụng ImageUploader để tải ảnh lên và nhận URL ảnh
	                String imageUrl = ImageUploader.uploadImage(fileName, fileContent);  // Sử dụng ImageUploader để tải ảnh lên

	                if (!imageUrl.equals("No file uploaded.") && !imageUrl.startsWith("Error")) {
	                    // Lưu URL của ảnh vào đối tượng ItemImageModel
	                    itemImageModel.setImage(imageUrl);

	                    // Chèn thông tin ảnh vào cơ sở dữ liệu
	                    itemImage.insertItemImage(itemImageModel);
	                } else {
	                    System.out.println("Lỗi khi tải ảnh lên: " + imageUrl);
	                }
	            } catch (IOException | ServletException e) {
	                e.printStackTrace();
	                // Xử lý lỗi nếu có khi upload ảnh
	            }
	        }
	    }
	    MessageUtil.showMessage(req, "updateSuccess");
	    resp.sendRedirect("adminviewItem?ProductID=" + itemModel.getProductID());
	}

	private void postinsert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		ItemModel itemModel = new ItemModel();
		int Proid = Integer.parseInt(req.getParameter("productID"));
		itemModel.setProductID(Proid);
		itemModel.setItemID(item.CreateItemID(Proid));
		itemModel.setColor(req.getParameter("color"));
		itemModel.setColorCode(req.getParameter("colorCode"));
		itemModel.setSize(req.getParameter("size"));
		itemModel.setStock(Integer.parseInt(req.getParameter("stock")));
		itemModel.setOriginalPrice(Integer.parseInt(req.getParameter("originalPrice")));
		itemModel.setPromotionPrice(Integer.parseInt(req.getParameter("promotionPrice")));
		item.insertItem(itemModel); // Thêm item

//		Image(req, resp, itemModel);
		
		// Trích xuất phần file từ request (Multipart form data)
	    List<Part> parts = (List<Part>) req.getParts();

	    // Xử lý upload ảnh
	    for (Part part : parts) {
	        // Kiểm tra nếu phần này là file ảnh và có kích thước hợp lệ
	        if (part.getName().equals("file") && part.getSize() > 0) {
	            try {
	                // Lấy tên tệp và nội dung tệp
	                String fileName = part.getSubmittedFileName();
	                InputStream fileContent = part.getInputStream();
	                
	                // Tạo đối tượng ItemImageModel mới để lưu thông tin ảnh
	                ItemImageModel itemImageModel = new ItemImageModel();
	                itemImageModel.setItemID(itemModel.getItemID()); // Liên kết ảnh với item

	                // Tạo ID mới cho ảnh
	                int ImageID = itemImage.CreateItemimageID(itemModel.getItemID());
	                itemImageModel.setItemimageID(ImageID);

	                // Sử dụng ImageUploader để tải ảnh lên và nhận URL ảnh
	                String imageUrl = ImageUploader.uploadImage(fileName, fileContent); // Upload ảnh lên và nhận URL

	                // Kiểm tra nếu upload thành công
	                if (!imageUrl.equals("No file uploaded.") && !imageUrl.startsWith("Error")) {
	                    // Lưu URL ảnh vào đối tượng ItemImageModel
	                    itemImageModel.setImage(imageUrl);

	                    // Chèn thông tin ảnh vào cơ sở dữ liệu
	                    itemImage.insertItemImage(itemImageModel);
	                } else {
	                    System.out.println("Lỗi khi tải ảnh lên: " + imageUrl);
	                }
	            } catch (IOException | ServletException e) {
	                e.printStackTrace();
	                // Xử lý lỗi nếu có khi upload ảnh
	            }
	        }
	    }
	    MessageUtil.showMessage(req, "addSuccess");
	 // Sau khi thêm item và ảnh thành công, chuyển hướng người dùng về trang chi tiết sản phẩm
	    resp.sendRedirect("adminviewItem?ProductID=" + itemModel.getProductID());

	}

	private void Image(HttpServletRequest req, HttpServletResponse resp, ItemModel itemModel)
			throws ServletException, IOException {
		List<Part> parts = (List<Part>) req.getParts();
		int ItemID = itemModel.getItemID();
		for (Part part : parts) {

			
			String type = part.getContentType();

			if (type != null) {
				ItemImageModel itemImageModel = new ItemImageModel();				
				itemImageModel.setItemID(ItemID);
				
				int ImageID = itemImage.CreateItemimageID(ItemID);				
				itemImageModel.setItemimageID(ImageID);

	            try {
	                // Upload ảnh và lấy URL ảnh đã upload
	                String imageUrl = ImageUploader.uploadImage(req);  // Sử dụng ImageUploader mới

	                // Lưu URL ảnh vào đối tượng ItemImageModel
	                itemImageModel.setImage(imageUrl);

	                // Chèn thông tin ảnh vào cơ sở dữ liệu
	                itemImage.insertItemImage(itemImageModel);
	            } catch (IOException | ServletException e) {
	                e.printStackTrace();
	                // Xử lý lỗi nếu có khi upload ảnh
	            }
			}
		}
		resp.sendRedirect("adminviewItem?ProductID=" + itemModel.getProductID());
	}

	private void List(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listItem", item.findAll());
		System.out.println("Items: " + item.findAll().size());
		req.getRequestDispatcher("/views/admin/item/ListItem.jsp").forward(req, resp);
	}

	private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("ProID", req.getParameter("ProID"));
		req.getRequestDispatcher("/views/admin/item/insertItem.jsp").forward(req, resp);
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
	        // Lấy tham số từ request
	        int itemID = Integer.parseInt(req.getParameter("ItemID"));
	        int productID = Integer.parseInt(req.getParameter("ProductID"));
	        
	        // Thực hiện xóa item và ảnh liên quan
	        item.deleteItem(itemID);
	        itemImage.deleteItemImage(itemID);

	        // Hiển thị thông báo thành công
	        MessageUtil.showMessage(req, "delSuccess");
	    } catch (Exception ex) {
	        // Nếu có lỗi, hiển thị thông báo lỗi
	        MessageUtil.showMessage(req, "delFail");
	        ex.printStackTrace();  // In lỗi ra console để dễ dàng kiểm tra
	    }

	    
	    resp.sendRedirect("adminItem");
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int itemID = Integer.parseInt(req.getParameter("ItemID"));
		ItemModel model = item.findOne(itemID);
		List<ItemImageModel> images = itemImage.findByProductID(itemID);
		req.setAttribute("item", model);
		req.setAttribute("images", images);
		req.getRequestDispatcher("/views/admin/item/updateItem.jsp").forward(req, resp);
	}
}
