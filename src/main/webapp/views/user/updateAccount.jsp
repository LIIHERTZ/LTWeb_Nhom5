<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">


<!-- Mirrored from live.themewild.com/fameo/user-profile.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 03 Nov 2024 06:40:54 GMT -->
<head>
<body>
	<!-- user dashboard -->
	<div class="user-area bg pt-100 pb-80">
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="sidebar">
						<div class="sidebar-top">
							<div class="sidebar-profile-img">
								<img src="/FurSPS_Nhom5/assets/img/account/02.jpg" alt="">
								<input type="file" class="profile-img-file">
							</div>
							<h5>${user.firstName}${user.lastName}</h5>
						</div>
						<ul class="sidebar-list">
							<li><a class="active"><i class="fas fa-key"></i> Đổi mật
									khẩu</a></li>
							<li><a href="<c:url value='/user/infoUser'/>"><i
									class="far fa-sign-out"></i> Trở về</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-9">
					<div class="user-wrapper">
						<div class="row">
							<div class="col-lg-12">
								<div class="user-card">
									<h4 class="user-card-title">Đổi mật khẩu</h4>
									<div class="col-lg-12">
										<div class="user-form">
											<form action="/FurSPS_Nhom5/user/updateAccount" method="post">
												<c:if test="${not empty message }">
													<div class="alert alert-${alert}">
														<strong>${message}!</strong>
													</div>
												</c:if>
												<input type="hidden" name="UserID"
													value="${accountModel.userID}"> <input
													type="hidden" name="UserName"
													value="${accountModel.userName}">
												<div class="row">
													<div class="col-md-12">
														<div class="form-group">
															<label>Mật khẩu cũ</label> <input type="password"
																name="OldPassWord" value="${OldPassWord}"
																class="form-control" placeholder="Old Password">
														</div>
													</div>
													<div class="col-md-12">
														<div class="form-group">
															<label>Mật khẩu mới</label> <input type="password"
																name="Password" value="${Password}" class="form-control"
																placeholder="New Password">
														</div>
													</div>
													<div class="col-md-12">
														<div class="form-group">
															<label>Nhập lại mật khẩu mới</label> <input
																type="password" name="RetypePassword"
																value="${RetypePassword}" class="form-control"
																placeholder="Re-Type Password">
														</div>
													</div>
												</div>
												<button type="submit" class="theme-btn">
													<span class="far fa-key"></span> Xác nhận đổi mật khẩu
												</button>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- user dashboard end -->




	<!-- scroll-top -->
	<a href="#" id="scroll-top"><i class="far fa-arrow-up-from-arc"></i></a>
	<!-- scroll-top end -->
</body>


<!-- Mirrored from live.themewild.com/fameo/user-profile.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 03 Nov 2024 06:40:54 GMT -->
</html>
