package FurSPS.other;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.UUID;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import FurSPS.configs.CloudinaryConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class ImageUploader {

	public static String uploadImage(HttpServletRequest request) throws IOException, ServletException {
		// Kiểm tra xem có tệp gửi lên không
		Part filePart = request.getPart("file"); // Đảm bảo "file" là tên của input trong form HTML
		if (filePart == null) {
			return "No file uploaded."; // Trả về thông báo lỗi nếu không có file
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

			// Trả về URL ảnh đã upload
			return imageUrl;

		} catch (Exception e) {
			// Xử lý lỗi nếu có
			return "Error uploading image: " + e.getMessage(); // Trả về thông báo lỗi nếu có lỗi
		}
	}
	
	public static String uploadImage(String fileName, InputStream fileContent) throws IOException, ServletException {
	    if (fileContent == null) {
	        return "No file uploaded."; // Nếu không có file
	    }

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

	        // Trả về URL ảnh đã upload
	        return imageUrl;

	    } catch (Exception e) {
	        return "Error uploading image: " + e.getMessage(); // Trả về thông báo lỗi nếu có lỗi
	    }
	}
}