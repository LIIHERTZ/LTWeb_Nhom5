<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<title>Đổi mật khẩu</title>
<style>
.update-pass-container {
	display: flex;
	justify-content: center;
}

.container {
	max-width: 600px;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 5px;
	font-family: Arial, sans-serif;
	font-size: 20;
	margin: 40px;
	display: flex;
	flex-direction: row;
	justify-content: end;
}

.update-pass-content-container {
	flex-wrap: none;
	justify-content: center;
}

.update-pass-form-container {
	justify-content: center;
	width: 100%;
}

.update-pass-br {
	border-radius: 15px !important;
}
</style>
<form action="shipper-update-pass" method="post"
	enctype="multipart/form-data">
	<section class="bg0 p-t-52 p-b-20 update-pass-container">

		<div class="container">
			<div class="row update-pass-content-container">
				<div class="col-md-6 col-lg-7 p-b-80">
					<div class="p-r-0-lg">
						<div class="p-t-40">
							<h5 class="mtext-113 cl2 p-b-12 update-pass-container">Thay
								đổi mật khẩu</h5>
							<h5 class="mb-3" style="color: red">
								<Strong>${message}</Strong>
							</h5>
							<input type="hidden" name="UserID" value="${accountModel.userID}">
							<input type="hidden" name="UserName"
								value="${accountModel.userName}">
							<div class="row update-pass-form-container">
								<div class=" m-b-30">
									<label> Mật khẩu cũ </label> <input
										class="bor19 stext-111 cl2 plh3 size-116 p-lr-18 update-pass-br"
										type="password" required name="OldPassWord"
										style="width: 300px;">
								</div>
								<div class=" m-b-30">
									<label> Mật khẩu mới</label> <input
										class="bor19 stext-111 cl2 plh3 size-116 p-lr-18 update-pass-br"
										type="password" required name="Password" style="width: 300px;">
								</div>
								<button
									class="flex-c-m stext-101 cl0 size-125 bg3 bor2 hov-btn3 p-lr-15 trans-04"
									type="submit">Thay đổi</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</form>
</html>