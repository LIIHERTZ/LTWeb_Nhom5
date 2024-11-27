<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!doctype html>
<html lang="en">

<head>
</head>
<body>
	<div class="login-area py-100">
		<div class="container">
			<div class="col-md-5 mx-auto">
				<div class="login-form">
					<div class="login-header">
						<img src="/FurSPS_Nhom5/assets/img/logo/logo.png" alt="">
						<p>Reset your FurSPS account password</p>
					</div>
					<form action="/FurSPS_Nhom5/changepass" method="post">
						<c:if test="${mess != null}">
							<label class="form-group">$mess}</label>
						</c:if>
						<input type="hidden" name="formail" value="${formail}" />
						<div class="form-group">
							<label>Mật khẩu mới </label> <input type="password"
								class="form-control" placeholder="New Password" name="passchange" required>
						</div>
						<div class="form-group">
							<label>Nhập lại mật khẩu</label> <input type="password"
								class="form-control" placeholder="Retype Password" name="passcheck" required>
						</div>
						<div class="d-flex align-items-center">
							<button type="submit" class="theme-btn">
								<i class="far fa-key"></i> Xác nhận đổi mật khẩu
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

</html>