<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">


<!-- Mirrored from live.themewild.com/fameo/user-profile.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 03 Nov 2024 06:40:54 GMT -->
<head>
</head>
<body>

	<!-- user dashboard -->
	<div class="user-area bg pt-100 pb-80">
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="sidebar">
						<div class="sidebar-top">
							<div class="sidebar-profile-img">
								<img src="${user.avatar}" alt="Profile Image"
									class="profile-img"> <input type="file"
									class="profile-img-file">
								<style>
.sidebar-profile-img {
	position: relative;
	width: 100px; /* Kích thước cố định cho phần tử chứa */
	height: 100px; /* Kích thước cố định cho phần tử chứa */
	overflow: hidden; /* Đảm bảo ảnh không tràn ra ngoài */
	border-radius: 50%; /* Làm phần tử chứa thành hình tròn */
}

/* Định dạng ảnh */
.profile-img {
	width: 100%; /* Ảnh chiếm toàn bộ diện tích của phần tử chứa */
	height: 100%; /* Ảnh chiếm toàn bộ diện tích của phần tử chứa */
	object-fit: cover; /* Đảm bảo ảnh không bị méo và phù hợp với tỷ lệ */
	border-radius: 50%; /* Làm ảnh thành hình tròn */
}

/* Định dạng input file */
.profile-img-file {
	position: absolute;
	bottom: 5px; /* Đặt input file ở dưới cùng */
	right: 5px; /* Đặt input file ở bên phải */
	opacity: 0; /* Ẩn input file */
	width: 30px; /* Kích thước của input file */
	height: 30px; /* Kích thước của input file */
	background-color: rgba(0, 0, 0, 0.5); /* Nền cho input file */
	border-radius: 50%; /* Làm nút input thành hình tròn */
	cursor: pointer; /* Hiển thị con trỏ dạng tay */
}

.sidebar-profile-img:hover .profile-img-file {
	opacity: 1; /* Hiển thị input file khi hover */
}
</style>
							</div>
							<h5>${user.firstName} ${user.lastName}</h5>
						</div>
						<ul class="sidebar-list">
							<li><a href="user-dashboard.html"><i
									class="far fa-gauge-high"></i> Dashboard</a></li>
							<li><a class="active"><i class="far fa-user"></i> My
									Profile</a></li>
							<li><a href="order-list.html"><i
									class="far fa-shopping-bag"></i> My Order List <span
									class="badge badge-danger">02</span></a></li>
							<li><a href="/FurSPS_Nhom5/userAddress"><i
									class="far fa-location-dot"></i> Address List</a></li>
							<li><a href="track-order.html"><i
									class="far fa-map-location-dot"></i> Track My Order</a></li>
							<li><a href="payment-method.html"><i
									class="far fa-wallet"></i> Payment Methods</a></li>
							<li><a href="user-setting.html"><i class="far fa-gear"></i>
									Settings</a></li>
							<li><a href="#"><i class="far fa-sign-out"></i> Logout</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-9">
					<div class="user-wrapper">
						<div class="row">
							<div class="col-lg-12">


								<div class="user-card" style="pointer-events: none;">
									<h4 class="user-card-title">Thông tin cá nhân</h4>
									<div class="user-form">
										<form action="#">
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label>Họ và đệm</label> <input type="text"
															class="form-control" value="${user.firstName}"
															name="FirstName" placeholder="First Name">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label>Tên</label> <input type="text" class="form-control"
															value="${user.lastName}" name="LastName"
															placeholder="Last Name">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label>Email</label> <input type="text"
															class="form-control" name="Email" value="${user.email}"
															placeholder="Email">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label>Phone</label> <input type="text"
															class="form-control" name="Phone" value="${user.phone}"
															placeholder="Phone">
													</div>
												</div>

												<div class="col-md-6">
													<div class="form-group">
														<label>CCCD</label> <input type="text"
															class="form-control" name="Cid" value="${user.cid}"
															placeholder="CID">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label>BirthDay</label> <input type="date"
															class="form-control" name="Dob"
															value="${user.dob != null ? user.dob.toLocalDate().toString() : ''}"
															placeholder="DOB">
													</div>
												</div>

												<div class="col-md-6">
													<div class="form-group">
														<label>Quê quán</label> <input type="text" name="Address"
															class="form-control" value="${user.address}"
															placeholder="Address">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label>Địa chỉ</label> <input type="text" name="Area"
															class="form-control" value="${user.area}"
															placeholder="Area">
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-check-inline" style="margin-left: 60px;">
													<input class="form-check-input" type="checkbox" value="Nam"
														name="Gender" id="agreeMale"
														${user.gender != 1 ? 'checked' : ''}
														onclick="toggleCheckbox('agreeFemale', this)"> <label
														class="form-check-label" for="agreeMale">Nam</label>
												</div>

												<div class="form-check-inline" style="margin-left: 60px;">
													<input class="form-check-input" type="checkbox" value="Nữ"
														name="Gender" id="agreeFemale"
														${user.gender == 1 ? 'checked' : ''}
														onclick="toggleCheckbox('agreeMale', this)"> <label
														class="form-check-label" for="agreeFemale">Nữ</label>
												</div>
											</div>
											<script>
												function toggleCheckbox(
														otherId, checkbox) {
													const otherCheckbox = document
															.getElementById(otherId);
													otherCheckbox.checked = !checkbox.checked;
												}
											</script>
										</form>
									</div>
								</div>
							</div>



						</div>

						<div class="col-lg-12">
							<div class="row">
								<!-- Button Thay đổi thông tin -->
								<div class="col-md-6">
									<button
										onclick="window.location.href='updateUser?userID=${userModel.userID}'"
										class="theme-btn" style="margin-left: 150px;">
										<span class="far fa-user"></span> Thay đổi thông tin
									</button>
								</div>
								<!-- Button Đổi mật khẩu -->
								<br>
								<div class="col-md-6">
									<button
										onclick="window.location.href='updateAccount?userID=${userModel.userID}'"
										class="theme-btn" style="margin-left: 150px;">
										<span class="fas fa-key"></span> Đổi mật khẩu
									</button>
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

</html>
