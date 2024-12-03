package FurSPS.utils;

import jakarta.servlet.http.HttpServletRequest;

public class MessageUtil {
	public static void showMessage(HttpServletRequest request, String typeMessage) {
		String messageResponse = "";
		String alert = "";
		if (typeMessage.equals("delSuccess")) {
			messageResponse = "Xóa thành công";
			alert = "success";
		} else if (typeMessage.equals("delFail")) {
			messageResponse = "Xóa thất bại";
			alert = "danger";
		} else if (typeMessage.equals("addSuccess")) {
			messageResponse = "Thêm thành công";
			alert = "success";
		} else if (typeMessage.equals("addFail")) {
			messageResponse = "Thêm thất bại";
			alert = "danger";
		} else if (typeMessage.equals("updateSuccess")) {
			messageResponse = "Cập nhật thành công";
			alert = "success";
		} else if (typeMessage.equals("updateFail")) {
			messageResponse = "Cập nhật thất bại";
			alert = "danger";
		} else if(typeMessage.equals("updateAccountTrue")) {
			messageResponse = "Thay đổi mật khẩu thành công";
			alert = "success";
		}
		else if (typeMessage.equals("updateAccountFail")) {
			messageResponse = "Mật khẩu cũ không đúng. Vui lòng nhập lại";
			alert = "danger";
		}else if(typeMessage.equals("searchVoucherFail")) {
			messageResponse = "Mã voucher không tồn tại";
			alert = "danger";
		} else if(typeMessage.equals("searchVoucherNull")) {
			messageResponse = "Bạn đã dùng voucher này rồi";
			alert = "danger";
		} else if(typeMessage.equals("userExistInAccount")) {
			messageResponse = "Tài khoản cho người dùng này đã tồn tại. Thêm thất bại";
			alert = "danger";
		} else if(typeMessage.equals("userExist")) {
			messageResponse = "Người dùng không tồn tại. Thêm thất bại";
			alert = "danger";
		} else if(typeMessage.equals("phoneInvalid")) {
			messageResponse = "Sai định dạng của số điện thoại";
			alert = "danger";
		} else if(typeMessage.equals("cidInvalid")) {
			messageResponse = "Sai định dạng căn cước công dân";
			alert = "danger";
		} else if(typeMessage.equals("emailInvalid")) {
			messageResponse = "Sai định dạng email";
			alert = "danger";
		} else if(typeMessage.equals("kpiInvalid")) {
			messageResponse = "Sai định dạng KPI";
			alert = "danger";
		}
		request.setAttribute("message", messageResponse);
		request.setAttribute("alert", alert);
	}
}
