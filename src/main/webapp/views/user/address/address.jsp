<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html>

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
							<li><a href="/FurSPS_Nhom5/user/infoUser"><i
									class="far fa-user"></i> My Profile</a></li>
							<li><a href="order-list.html"><i
									class="far fa-shopping-bag"></i> My Order List <span
									class="badge badge-danger">02</span></a></li>
							<li><a class="active" href="/FurSPS_Nhom5/userAddress"><i
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
								<div class="user-card">
									<div class="user-card-header">
										<h4 class="user-card-title">Address List</h4>
										<div class="user-card-header-right">
											<a href="/FurSPS_Nhom5/userAddAddress" class="theme-btn"><span
												class="far fa-plus-circle"></span>Add Address</a>
										</div>
									</div>
									<div class="table-responsive">
										<table class="table table-borderless text-nowrap">
											<thead>
												<tr>
													<th>Tên</th>
													<th>Địa chỉ</th>
													<th>Tỉnh</th>
													<th>Số điện thoại</th>
													<th></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${listAddress}">
													<tr>
														<td><span class="table-list-code">${item.name}</span></td>
														<td>${item.detail}</td>
														<td>${item.city}</td>
														<td>${item.phone}</td>
														<td><a href="/FurSPS_Nhom5/userUpdateAddress?userID=${user.userID}&city=${item.city}&detail=${item.detail}"
															class="btn btn-outline-secondary btn-sm rounded-2"
															data-tooltip="tooltip" title="Edit"><i
																class="far fa-pen"></i></a> 
																<a href="/FurSPS_Nhom5/userDeleteAddress?userID=${user.userID}&city=${item.city}&detail=${item.detail}"
															class="btn btn-outline-danger btn-sm rounded-2"
															data-tooltip="tooltip" title="Delete"><i
																class="far fa-trash-can"></i></a></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
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

</body>
</html>