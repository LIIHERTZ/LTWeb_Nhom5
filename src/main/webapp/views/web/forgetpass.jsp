<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">


<!-- Mirrored from live.themewild.com/fameo/forgot-password.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 03 Nov 2024 06:40:48 GMT -->
<head>


</head>

<body>
	<!-- forgot password -->
	<div class="login-area py-100">
		<div class="container">
			<div class="col-md-5 mx-auto">
				<div class="login-form">
					<div class="login-header">
						<img src="/FurSPS_Nhom5/assets/img/logo/logo.png" alt="">
						<p>Reset your FurSPS account password</p>
					</div>
					<form action="/FurSPS_Nhom5/forgetpass" method="post">
						<c:if test="${exception != null}">
							<label class="form-group">${exception}</label>
						</c:if>
						<div class="form-group">
							<label>Email Address</label> <input type="email"
								class="form-control" placeholder="Your Email" name="formail"
								value="${formail}" required>
						</div>
						<div class="d-flex align-items-center">
							<button type="submit" class="theme-btn">
								<i class="far fa-key"></i> Send Reset Link
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- forgot password end -->

</body>


<!-- Mirrored from live.themewild.com/fameo/forgot-password.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 03 Nov 2024 06:40:48 GMT -->
</html>
