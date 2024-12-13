<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>

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
	
								<li><a class="active"><i
										class="far fa-shopping-bag"></i> Track Order <span
										class="badge badge-danger"></span></a></li>
								<li><a href="/FurSPS_Nhom5/userlistOrder"><i class="far fa-sign-out"></i>Back</a></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-9">
						<div class="user-wrapper">
							<div class="row">
								<div class="col-lg-12">
									<div class="user-card user-track-order">
										<h4 class="user-card-title">Track My Order</h4>
										<div class="track-order-content">
											<div class="track-order-step">
												<div
													class="step-item <c:if test='${order.status >= 0 && order.status != 5}'>completed</c:if>">
													<div class="step-icon">
														<i class="fal fa-shopping-cart"></i>
													</div>
													<h6>Not Confirmed</h6>
												</div>

												<div
													class="step-item <c:if test='${order.status >= 1 && order.status != 5}'>completed</c:if>">
													<div class="step-icon">
														<i class="fal fa-cog"></i>
													</div>
													<h6>Confirmed</h6>
												</div>

												<div
													class="step-item <c:if test='${order.status >= 2 && order.status != 5}'>completed</c:if>">
													<div class="step-icon">
														<i class="fal fa-check-circle"></i>
													</div>
													<h6>Processing</h6>
												</div>

												<div
													class="step-item <c:if test='${order.status >= 3 && order.status != 5}'>completed</c:if>">
													<div class="step-icon">
														<i class="fal fa-truck-fast"></i>
													</div>
													<h6>Delivered</h6>
												</div>

												<div
													class="step-item <c:if test='${order.status >= 4 && order.status != 5}'>completed</c:if>">
													<div class="step-icon">
														<i class="fal fa-home"></i>
													</div>
													<h6>Complete</h6>
												</div>

												<!-- Nếu status == 5 (Cancelled), chỉ đánh dấu bước Canceled thôi -->
												<div
													class="step-item <c:if test='${order.status == 5}'>completed</c:if>">
													<div class="step-icon">
														<i class="fal fa-times-circle"></i>
													</div>
													<h6>Cancelled</h6>
												</div>
											</div>

										</div>


										<c:if test='${order.status == 4}'>
											<h4 class="user-card-title" style="margin-top: 40px;">List
												Order</h4>
											<div class="table-responsive">
												<table class="table table-borderless text-nowrap">
													<thead>
														<tr>
															<th>ProductName</th>
															<th>Type</th>
															<th>Color</th>
															<th class="text-center">Action</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="detail" items="${details}">
															<tr>
																<td><h6>${detail.product.productName}</h6></td>
																<td>${detail.category.categoryName}</td>
																<td>${detail.item.color}</td>
																<td
																	style="display: flex; justify-content: center; align-items: center;">
																	<!-- Nút hiển thị/ẩn form -->
																	<button class="btn btn-warning"
																		style="background-color: #FFDA74;font-weight: bold;"
																		onclick="this.parentElement.parentElement.nextElementSibling.style.display = this.parentElement.parentElement.nextElementSibling.style.display === 'none' ? 'table-row' : 'none';">
																		Review</button>
																</td>
															</tr>
															<tr style="display: none;">
																<td colspan="4">
																	<form action="${pageContext.request.contextPath}/useritemRating"
																		method="post" 
																		style="margin-top: 20px;">
																		<input type="hidden" name="orderID"
																			value="${detail.orderID}" />
																		<input type="hidden" name="itemID"
																			value="${detail.itemID}" />
																		<input type="hidden" name="quantity"
																			value="${detail.quantity}" />
																		<div class="form-group">
																			<label for="rating"
																				style="color: black; font-size: 17px; margin-bottom: 10px;">Điểm
																				đánh giá:</label> <select id="rating" name="rating"
																				style="border: 1px solid #FFDA74;"
																				class="form-control" required>
																				<option value=""></option>
																				<option value="1">Rất tệ</option>
																				<option value="2">Tệ</option>
																				<option value="3">Bình thường</option>
																				<option value="4">Tốt</option>
																				<option value="5">Rất tốt</option>
																			</select>
																		</div>

																		<div class="form-group" style="margin-top: 10px;">
																			<label for="reviewText"
																				style="color: black; font-size: 17px; margin-bottom: 10px;">Nội
																				dung đánh giá:</label>
																			<textarea id="reviewText" name="reviewText"
																				class="form-control"
																				style="border: 1px solid #FFDA74; margin-bottom: 26px;"
																				rows="4" required></textarea>
																		</div>

																		<div class="form-group" style="margin-top: 10px;">
																			<label for="reviewImage"
																				style="color: black; font-size: 17px;">Hình
																				ảnh (nếu có):</label> <input type="file" id="reviewImage"
																				name="reviewImage" class="form-control-file"
																				accept="image/*" />
																		</div>

																		<div style="text-align: center; margin-top: 15px;">
																			<button type="submit" class="theme-btn"
																				style="background-color: #FFDA74; padding: 10px 20px; border-radius: 5px; font-weight: bold;">Gửi
																				đánh giá</button>
																		</div>
																	</form>
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>										
										</c:if>

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