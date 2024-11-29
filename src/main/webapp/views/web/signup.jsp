<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">


<!-- Mirrored from live.themewild.com/fameo/register.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 03 Nov 2024 06:40:48 GMT -->
<head>
<!-- meta tags -->
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="keywords" content="">


<!-- favicon -->
<link rel="icon" type="image/x-icon" href="/FurSPS_Nhom5/assets/img/logo/favicon.png">

<!-- css -->
<link rel="stylesheet" href="/FurSPS_Nhom5/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="/FurSPS_Nhom5/assets/css/all-fontawesome.min.css">
<link rel="stylesheet" href="/FurSPS_Nhom5/assets/css/animate.min.css">
<link rel="stylesheet" href="/FurSPS_Nhom5/assets/css/magnific-popup.min.css">
<link rel="stylesheet" href="/FurSPS_Nhom5/assets/css/owl.carousel.min.css">
<link rel="stylesheet" href="/FurSPS_Nhom5/assets/css/jquery-ui.min.css">
<link rel="stylesheet" href="/FurSPS_Nhom5/assets/css/nice-select.min.css">
<link rel="stylesheet" href="/FurSPS_Nhom5/assets/css/style.css">
<style>
.form-check-inline {
	display: inline-block;
	margin-right: 100px; /* Khoảng cách giữa các checkbox */
}

.form-row {
	display: flex;
	gap: 20px; /* Khoảng cách giữa các cột */
}

.col-md-6 {
	flex: 1; /* Chia đều không gian giữa hai cột */
}

.form-group {
	margin-bottom: 15px;
}
</style>
</head>


<body>
	<!-- register area -->
	<div class="login-area py-100">
		<div class="container">
			<div class="col-md-5 mx-auto">
				<div class="login-form">
					<div class="login-header">
						<img src="/FurSPS_Nhom5/assets/img/logo/logo.png" alt="">
						<p>Create your free FurSPS account</p>
					</div>
					<form action="/FurSPS_Nhom5/signup" method="post">
						<c:if test="${exception != null}">
							<label class="form-group">${exception}</label>
						</c:if>
						<div class="form-row">
							<div class="col-lg-12">
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label>Tên đăng nhập</label> <input type="text"
												class="form-control" placeholder="Your User Name"
												name="usernamesignup" value="${usernamesignup}" required>
										</div>
										<div class="form-group">
											<label>Password</label> <input type="password"
												class="form-control" placeholder="Your Password"
												name="passsignup" value="${passsignup}" required>
										</div>
										<div class="form-group">
											<label>Nhập lại Password</label> <input type="password"
												class="form-control" placeholder="Retype Password"
												name="passcheck" value="${passscheck}" required>
										</div>
										<div class="form-group">
											<label>Nhập họ và tên lót</label> <input type="text"
												class="form-control" placeholder="First Name"
												name="firstname" value="${firstname}" required>
										</div>
										<div class="form-group">
											<label>Nhập tên</label> <input type="text"
												class="form-control" placeholder="Last Name" name="lastname"
												value="${lastname}" required>
										</div>
										<div class="form-check form-group">
											<div class="form-check-inline" style="margin-right: 50px;">
												<input class="form-check-input" type="checkbox" value="0"
													name="gender" id="agreeMale"
													onclick="toggleCheckbox('agreeFemale', this)"> <label
													class="form-check-label" for="agreeMale">Nam</label>
											</div>

											<div class="form-check-inline">
												<input class="form-check-input" type="checkbox" value="1"
													name="gender" id="agreeFemale"
													onclick="toggleCheckbox('agreeMale', this)"> <label
													class="form-check-label" for="agreeFemale">Nữ</label>
											</div>
										</div>
									</div>
									<script>
										function toggleCheckbox(otherId,
												checkbox) {
											const otherCheckbox = document
													.getElementById(otherId);
											otherCheckbox.checked = !checkbox.checked;
										}
									</script>
									<div class="col-md-6">
										<div class="form-group">
											<label>Email Address</label> <input type="email"
												class="form-control" placeholder="Your Email" name="email"
												value="${email}">
										</div>
										<div class="form-group">
											<label>BirthDay</label> <input type="date"
												class="form-control" name="dob" value="${dob}">
										</div>
										<div class="form-group">
											<label>Số điện thoại</label> <input type="text"
												class="form-control" placeholder="Your Phone" name="phone"
												value="${phone}">
										</div>
										<div class="form-group">
											<label>Khu vực</label> <input type="text"
												class="form-control" placeholder="Chọn thành phố"
												name="area" list="exampleList" value="${area}">
											<datalist id="exampleList">
												<c:forEach var="city" items="${listcity}">
													<option value="${city}">
												</c:forEach>
											</datalist>
										</div>
										<div class="form-group">
											<label>Địa chỉ</label> <input type="text"
												class="form-control" placeholder="Your Address"
												name="address" value="${address}">
										</div>
									</div>
								</div>
							</div>
							<div class="d-flex align-items-center">
								<button type="submit" class="theme-btn">
									<i class="far fa-paper-plane"></i> Register
								</button>
							</div>
						</div>
					</form>
					<div class="login-footer">
						<p>
							Already have an account? <a
								href="${pageContext.request.contextPath}/login">Login.</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>

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


<!-- Mirrored from live.themewild.com/fameo/register.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 03 Nov 2024 06:40:48 GMT -->
</html>
<%-- <!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap"
	rel="stylesheet">
<link rel="icon" type="image/png"
	href="https://storage.googleapis.com/web-budget/Image/FE/website-logo.png">
<!-- Bootstrap CSS -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/templates/web/vendor/bootstrap/css/bootstrap.min.css"/>">
<!-- Style -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/templates/web/css/login.css"/>">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>


<title>Đăng ký</title>

</head>

<body>
<body>
	<div class="wrapper signup">
		<h1>ĐĂNG KÝ TÀI KHOẢN</h1>
		<h4>${exception}</h4>
		<form action="signup" method="post">
			<div class="input-box">
				<input type="text" placeholder="Nhập tên đăng nhập"
					name="usernamesignup" value="${usernamesignup}" required><i
					class='bx bxs-user-circle'></i>
			</div>
			<div class="input-box">
				<div class="input-field">
					<input type="password" placeholder="Nhập mật khẩu"
						name="passsignup" required> <i class='bx bxs-lock-alt'></i>
				</div>
				<div class="input-field">
					<input type="password" placeholder="Nhập lại mật khẩu"
						name="passcheck" required> <i class='bx bxs-lock-alt'></i>
				</div>
			</div>
			<div class="input-box">
				<div class="input-field">
					<input type="text" placeholder="Nhập họ" name="firstname"
						value="${firstname}" required> <i
						class='bx bxs-user-detail bx-flip-horizontal'></i>
				</div>
				<div class="input-field">
					<input type="text" placeholder="Nhập tên" name="lastname"
						value="${lastname}" required> <i
						class='bx bxs-user-detail bx-flip-horizontal'></i>
				</div>
			</div>
			<div class="input-box">
				<div class="input-field">
					<input type="text" placeholder="Nhập email" name="email"
						value="${email}"> <i
						class='bx bxs-envelope bx-flip-horizontal'></i>
				</div>
				<div class="input-field">
					<select name="gender"
						style="padding: 0 0 0 20px; margin-left: 20px; width: 180px; height: 43px; background: transparent; border: none; outline: none; border: 2px solid rgba(255, 255, 255, .2); border-radius: 40px; font-size: 16px; color: white">
						<option style="background: transparent" value="0">Nam</option>
						<option style="background: transparent" value="1">Nữ</option>
					</select>
				</div>
				<div class="input-field">
					<input type="date" name="dob" value="${dob}">
				</div>
			</div>
			<div class="input-box">
				<div class="input-field">
					<input type="text" placeholder="Nhập số điện thoại" name="phone"
						value="${phone}"> <i class='bx bxs-phone-call'></i>
				</div>
				<div class="input-field">
					<input type="text" placeholder="Chọn thành phố" name="area"
						list="exampleList" value="${area}"> <i class='bx bxs-city'></i>
					<datalist id="exampleList">
						<c:forEach var="city" items="${listcity}">
							<option value="${city}">
						</c:forEach>
					</datalist>
				</div>
			</div>
			<div class="input-box">
				<input type="text" placeholder="Nhập địa chỉ" name="address"
					value="${address}"> <i class='bx bxs-building-house'></i>

			</div>
			<input type="submit" value="Đăng ký"
				class="btn btn-block btn-primary mb-3">
			<div class="register-link">
				<p>
					Nếu bạn đã có tài khoản <a
						href="${pageContext.request.contextPath}/login">Đăng nhập tại
						đây </a>
				</p>
			</div>
		</form>
	</div>
	
	<style>
	body, html {
    height: 100%;
    margin: 0;
    padding: 0;
    display: flex;
    flex-direction: column;
}

/* Đặt container của form thành flex để căn giữa */
.form-container {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-grow: 1; /* Đẩy form vào giữa không gian giữa header và footer */
}

/* Thiết lập giao diện của form */
.wrapper.signup {
    width: 100%;
    max-width: 500px;
    padding: 20px;
    background-color: #ffffff;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    text-align: center;
}

/* Đặt lại khoảng cách trong các phần tử form */
.input-box, .input-field {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-bottom: 15px;
}

.input-box input[type="text"],
.input-box input[type="password"],
.input-box input[type="date"],
.input-box select,
.btn {
    width: 100%;
    padding: 10px;
    margin: 5px 0;
    border-radius: 5px;
    border: 1px solid #ccc;
    font-size: 16px;
}

input[type="submit"] {
    width: 100%;
    padding: 10px;
    font-size: 18px;
}

.register-link {
    margin-top: 20px;
}
	</style>
</body>

</html> --%>