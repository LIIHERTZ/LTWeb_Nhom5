<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">


<!-- Mirrored from live.themewild.com/fameo/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 03 Nov 2024 06:40:48 GMT -->
<head>
<!-- meta tags -->
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="keywords" content="">


<!-- favicon -->
<link rel="icon" type="image/x-icon"
	href="/FurSPS_Nhom5/assets/img/logo/favicon.png">

<!-- css -->
<link rel="stylesheet" href="/FurSPS_Nhom5/assets/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/FurSPS_Nhom5/assets/css/all-fontawesome.min.css">
<link rel="stylesheet" href="/FurSPS_Nhom5/assets/css/animate.min.css">
<link rel="stylesheet"
	href="/FurSPS_Nhom5/assets/css/magnific-popup.min.css">
<link rel="stylesheet"
	href="/FurSPS_Nhom5/assets/css/owl.carousel.min.css">
<link rel="stylesheet" href="/FurSPS_Nhom5/assets/css/jquery-ui.min.css">
<link rel="stylesheet"
	href="/FurSPS_Nhom5/assets/css/nice-select.min.css">
<link rel="stylesheet" href="/FurSPS_Nhom5/assets/css/style.css">

</head>

<body>

	<!-- login area -->
	<div class="login-area py-90">
		<div class="container">
			<div class="col-md-7 col-lg-5 mx-auto">
				<div class="login-form">
					<div class="login-header">
						<img src="/FurSPS_Nhom5/assets/img/logo/logo.png" alt="">
						<p>Login with your FurSPS account</p>
					</div>
					<form action="/FurSPS_Nhom5/login" method="post">
						<c:if test="${mess != null}">
							<label class="form-group">${mess}</label>
						</c:if>
						<div class="form-group">
							<label>Username</label> <input type="text" class="form-control"
								placeholder="User Name" value="${username}" name="username"
								required>
						</div>
						<div class="form-group">
							<label>Password</label> <input type="password"
								class="form-control" placeholder="Your Password"
								value="${password}" name="password" required>
						</div>
						<div class="d-flex justify-content-between mb-4">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" id="remember" name="remember"
									value="on" checked> <label class="form-check-label"
									for="remember">Remember Me</label>
							</div>
							<a href="${pageContext.request.contextPath}/forgetpass">Quên
								mật khẩu</a>
						</div>
						<div class="d-flex align-items-center">
							<button type="submit" class="theme-btn">
								<i class="far fa-sign-in"></i> Đăng nhập
							</button>
						</div>
					</form>
					<div class="login-footer">
						<p>
							Don't have an account? <a
								href="${pageContext.request.contextPath}/signup">Đăng ký</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- login area end -->

	<!-- scroll-top -->
	<a href="#" id="scroll-top"><i class="far fa-arrow-up-from-arc"></i></a>
	<!-- scroll-top end -->


	<!-- js -->
	<script data-cfasync="false"
		src="../cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>
	<script src="/FurSPS_Nhom5/assets/js/jquery-3.7.1.min.js"></script>
	<script src="/FurSPS_Nhom5/assets/js/modernizr.min.js"></script>
	<script src="/FurSPS_Nhom5/assets/js/bootstrap.bundle.min.js"></script>
	<script src="/FurSPS_Nhom5/assets/js/imagesloaded.pkgd.min.js"></script>
	<script src="/FurSPS_Nhom5/assets/js/jquery.magnific-popup.min.js"></script>
	<script src="/FurSPS_Nhom5/assets/js/isotope.pkgd.min.js"></script>
	<script src="/FurSPS_Nhom5/assets/js/jquery.appear.min.js"></script>
	<script src="/FurSPS_Nhom5/assets/js/jquery.easing.min.js"></script>
	<script src="/FurSPS_Nhom5/assets/js/owl.carousel.min.js"></script>
	<script src="/FurSPS_Nhom5/assets/js/counter-up.js"></script>
	<script src="/FurSPS_Nhom5/assets/js/jquery-ui.min.js"></script>
	<script src="/FurSPS_Nhom5/assets/js/jquery.nice-select.min.js"></script>
	<script src="/FurSPS_Nhom5/assets/js/countdown.min.js"></script>
	<script src="/FurSPS_Nhom5/assets/js/wow.min.js"></script>
	<script src="/FurSPS_Nhom5/assets/js/main.js"></script>

</body>

</html>