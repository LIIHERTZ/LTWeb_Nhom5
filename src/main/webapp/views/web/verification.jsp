<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!doctype html>
<html lang="en">

<head>
</head>

<body>

	<div class="login-area py-90">
		<div class="container">
			<div class="col-md-7 col-lg-5 mx-auto">
				<div class="login-form">
					<div class="login-header">
						<img src="/FurSPS_Nhom5/assets/img/logo/logo.png" alt="">
						<p>Verification</p>
					</div>
					<form action="/FurSPS_Nhom5/verification" method="post">
						<c:if test="${mess != null}">
							<label class="form-group">${mess}</label>
						</c:if>
						<div class="form-group">
							<label>Nhập mã xác nhận</label> <input type="text" class="form-control"
								placeholder="Mã đã được gởi tới email!" value="${usercode}" name="usercode"
								required>
						</div>
						<div class="d-flex align-items-center">
							<button type="submit" class="theme-btn">
								<i class="far fa-sign-in"></i> Xác nhận
							</button>
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
	<%-- 	<div class="wrapper">
			<h1>
				XÁC NHẬN
				</h1>
					<h4 class="xac-nhan">Mã xác nhận đã được gửi tới email của bạn!</h4>
					<h4>${mess}</h4>
					<form action="verification" method="post">
						<div class="input-box">
							<input type="text" placeholder="Nhập mã xác nhận trên email"
								name="usercode" required>
						</div>
						<button type="submit" class="btn">Đăng nhập</button>
					</form>
					<span class="caption">Nếu sau 5 phút chưa có mã?</span> <a
						href="${pageContext.request.contextPath}/resend">Gửi lại mã</a>
	</div> --%>
</body>

</html>