<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<body>
	<main class="main">
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
								<h5>${user.firstName}${user.lastName}</h5>
							</div>
							<ul class="sidebar-list">
								<li><a class="active"><i class="far fa-shopping-bag"></i>
										Detail Order <span class="badge badge-danger"></span></a></li>
								<li><a href="/FurSPS_Nhom5/userlistOrder"><i
										class="far fa-sign-out"></i>Back</a></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-9">
						<div class="user-wrapper">
							<div class="row">
								<div class="col-lg-12">
									<div class="user-card user-order-detail">
										<div class="user-card-header">
											<h4 class="user-card-title">Order Details</h4>
											<div class="user-card-header-right">
												<a href="userlistOrder" class="theme-btn"><span
													class="fas fa-arrow-left"></span>Order List</a>
											</div>
										</div>
										<div class="table-responsive">
											<table class="table table-borderless text-nowrap">
												<thead>
													<tr>
														<th>ProductName</th>
														<th>Type</th>
														<th>Color</th>
														<th>Quantity</th>
														<th>Material</th>
														<th>Total</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="detail" items="${details}">
														<tr>
															<td><h6>${detail.product.productName}</h6></td>
															<td>${detail.category.categoryName}</td>
															<td>${detail.item.color}</td>
															<td>${detail.quantity}</td>
															<td>${detail.product.material}</td>
															<td><fmt:formatNumber
																	value="${detail.order.totalMoney}" pattern="#,###" /></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<div class="row">
											<div class="col-lg-6">
												<div class="order-detail-content">
													<h5>Shipping Address</h5>
													<p>
														<i class="far fa-location-dot"></i> ${order.address}
													</p>
												</div>
											</div>
											<div class="col-lg-6">
												<div class="order-detail-content">
													<h5>Order Summary</h5>
													<ul>
														<li>Subtotal<span><fmt:formatNumber
																	value="${order.totalMoney}" pattern="#,###" /></span></li>
														<li>Shipping<span><fmt:formatNumber
																	value="${order.transportFee}" pattern="#,###" /></span></li>
														<li>Discount<span><fmt:formatNumber
																	value="${order.discount}" pattern="#,###" /></span></li>
														<li>Total <span><fmt:formatNumber
																	value="${order.totalMoney - order.transportFee - order.discount}"
																	pattern="#,###" /></span></li>
													</ul>
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
		</div>
		<!-- user dashboard end -->
	</main>
	<!-- scroll-top -->
	<a href="#" id="scroll-top"><i class="far fa-arrow-up-from-arc"></i></a>
	<!-- scroll-top end -->
</body>
</html>