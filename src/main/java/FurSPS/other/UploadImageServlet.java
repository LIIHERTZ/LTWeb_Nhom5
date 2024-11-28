package FurSPS.other;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import FurSPS.configs.CloudinaryConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.util.Map;
import java.util.UUID;

@WebServlet("/upload-image")
@MultipartConfig(location = "D:/images", // Đường dẫn thư mục tạm
		maxFileSize = 1024 * 1024 * 10, // Kích thước tối đa của tệp là 10MB
		maxRequestSize = 1024 * 1024 * 20, // Kích thước tối đa của request là 20MB
		fileSizeThreshold = 0) // Ngưỡng kích thước tệp
public class UploadImageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Kiểm tra xem có tệp gửi lên không
		Part filePart = request.getPart("file"); // Đảm bảo "file" là tên của input trong form HTML
		if (filePart == null) {
			response.getWriter().write("No file uploaded.");
			return;
		}

		String fileName = filePart.getSubmittedFileName(); // Lấy tên tệp từ phần tử form
		InputStream fileContent = filePart.getInputStream();

		// Khởi tạo Cloudinary và cấu hình
		Cloudinary cloudinary = CloudinaryConfig.getCloudinary();

		try {
			// Tạo một tên tệp duy nhất để tránh trùng lặp (sử dụng UUID)
			String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;

			// Tạo File tạm tại thư mục D:/images với tên tệp duy nhất
			File tempFile = new File("D:/images/" + uniqueFileName);
			try (OutputStream out = new FileOutputStream(tempFile)) {
				byte[] buffer = new byte[1024];
				int length;
				while ((length = fileContent.read(buffer)) != -1) {
					out.write(buffer, 0, length);
				}
			}

			// Upload ảnh lên Cloudinary từ File tạm
			Map uploadResult = cloudinary.uploader().upload(tempFile, ObjectUtils.emptyMap());

			// Lấy URL của ảnh đã upload
			String imageUrl = (String) uploadResult.get("url");

			// Xóa file tạm sau khi upload
			tempFile.delete();

			// Gửi URL ảnh về client
			response.getWriter().write("File uploaded successfully. Image URL: " + imageUrl);

		} catch (Exception e) {
			// Xử lý lỗi nếu có
			response.getWriter().write("Error uploading image: " + e.getMessage());
		}
	}
}