<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<body>
	<main class="main">
		<!-- user dashboard -->
		<div class="user-area bg pt-100 pb-80">
			<div class="container">
				<div class="row">
					<!-- sidebar -->
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
							<li><a href="/FurSPS_Nhom5/user/infoUser"><i
									class="far fa-user"></i> My Profile</a></li>
							<li><a  class="active"><i
									class="far fa-shopping-bag"></i> My Order List <span
									class="badge badge-danger"></span></a></li>
							<li><a href="/FurSPS_Nhom5/userAddress"><i
									class="far fa-location-dot"></i> Address List</a></li>
							<li><a href="/FurSPS_Nhom5/logout"><i class="far fa-sign-out"></i> Logout</a></li>
						</ul>
						</div>
					</div>
					<!-- main content -->
					<div class="col-lg-9">
						<div class="user-wrapper">
							<div class="row">
								<div class="col-lg-12">
									<div class="user-card">
										<div class="table-responsive">
											<table class="table table-borderless text-nowrap">
												<thead>
													<tr>
														<th>ID</th>
														<th>Purchased Date</th>
														<th>Delivery Time</th>
														<th class="text-center">Action</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="order" items="${listOrder}">
														<tr>
															<td>${order.orderID}</td>
															<td>${order.orderDate}</td>
															<td>${order.deliveryTime}</td>
															<td class="text-center"><a
																href="<c:url value='/userdetailOrder?id=${order.orderID}'/>"
																class="btn btn-outline-secondary btn-sm rounded-2"
																data-tooltip="tooltip" title="Details"> <i
																	class="far fa-eye"></i>
															</a> <a
																href="<c:url value='/usertrack'>
    <c:param name="id" value="${order.orderID}"/>
</c:url>"
																class="btn btn-outline-secondary btn-sm rounded-2"
																title="Track"> <i class="far fa-map-location-dot"></i>
															</a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>

										<!-- pagination -->
										<!-- ví dụ phân trang đơn giản -->
										<ul class="pagination">
											<li
												class="page-item <c:if test="${currentPage == 1}">disabled</c:if>">
												<a class="page-link" href="?page=${currentPage - 1}"
												aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
											</a>
											</li>
											<c:forEach var="i" begin="1" end="${totalPages}">
												<li
													class="page-item <c:if test="${i == currentPage}">active</c:if>">
													<a class="page-link" href="?page=${i}">${i}</a>
												</li>
											</c:forEach>
											<li
												class="page-item <c:if test="${currentPage == totalPages}">disabled</c:if>">
												<a class="page-link" href="?page=${currentPage + 1}"
												aria-label="Next"> <span aria-hidden="true">&raquo;</span>
											</a>
											</li>
										</ul>

										<!-- pagination end -->
									</div>
								</div>
							</div>
						</div>
					</div>


				</div>
			</div>
		</div>
	</main>
	<a href="#" id="scroll-top"><i class="far fa-arrow-up-from-arc"></i></a>

</body>
</html>
