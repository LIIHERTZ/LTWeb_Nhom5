<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
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
							<li><a class="active"><i class="far fa-location-dot"></i>
									Thêm địa chỉ</a></li>
							<li><a href="/FurSPS_Nhom5/userAddress"><i
									class="far fa-sign-out"></i> Trở lại</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-9">
					<div class="user-wrapper">
						<div class="row">
							<div class="col-lg-12">
								<div class="user-card">
									<div class="user-card-header">
										<h4 class="user-card-title">Thêm địa chỉ nhận hàng mới</h4>
										<div class="user-card-header-right">
											<a href="/FurSPS_Nhom5/userAddress" class="theme-btn"><span
												class="fas fa-arrow-left"></span>Address List</a>
										</div>
									</div>
									<div class="user-form">
										<form action="/FurSPS_Nhom5/userAddAddress" method="post">
											<c:if test="${not empty mess }">
												<div class="alert alert-${alert}">
													<strong>${mess}!</strong>
												</div>
											</c:if>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label>Tên người nhận</label> <input type="text"
															class="form-control" placeholder="Tên người nhận" name="name" value="${name}">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label>Số điện thoại</label> <input type="text"
															class="form-control" placeholder="Số điện thoại" name="phone" value="${phone}">
													</div>
												</div>
												<div class="col-md-12">
													<div class="form-group">
														<label>Tỉnh thành</label> <input type="text"
															class="form-control"  list="exampleList" placeholder="Khu vực" name="city" value="${city}" >
													</div>
													<datalist id="exampleList">
														<c:forEach var="city" items="${listcity}">
															<option value="${city}">
														</c:forEach>
													</datalist>
												</div>

												<div class="col-md-12">
													<div class="form-group">
														<label>Địa chỉ</label> <input type="text"
															class="form-control" placeholder="Địa chỉ cụ thể" name="detail" value="${detail}">
													</div>
												</div>
											</div>
											<button type="submit" class="theme-btn">
												<span class="far fa-save"></span> Save Address
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
	<!-- user dashboard end -->
</body>
</html>