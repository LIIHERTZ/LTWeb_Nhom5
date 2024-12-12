<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<style>
.dropdown-cart-list {
	max-height: 300px; /* Chiều cao tối đa của danh sách */
	overflow-y: auto; /* Thêm thanh cuộn dọc */
	overflow-x: hidden; /* Ẩn thanh cuộn ngang nếu có */
}

/* Tùy chỉnh giao diện thanh cuộn (tùy chọn) */
.dropdown-cart-list::-webkit-scrollbar {
	width: 6px; /* Độ rộng thanh cuộn */
}

.dropdown-cart-list::-webkit-scrollbar-thumb {
	background-color: #ccc; /* Màu của thanh cuộn */
	border-radius: 3px; /* Góc bo tròn */
}

.dropdown-cart-list::-webkit-scrollbar-thumb:hover {
	background-color: #999; /* Màu khi hover thanh cuộn */
}
</style>

<body>

	<!-- header area -->
	<header class="header">

		<!-- header top -->
		<div class="header-top">
			<div class="container">
				<div class="header-top-wrapper">
					<div class="row">
						<div class="col-12 col-md-6 col-lg-6 col-xl-5">
							<div class="header-top-left">
								<ul class="header-top-list">
									<li><a><i class="far fa-envelopes"></i> <span
											class="__cf_email__"
											data-cfemail="a2cbccc4cde2c7dac3cfd2cec78cc1cdcf">shopfursps@gmail.com</span></a></li>
									<li><a><i class="far fa-headset"></i> +84 377 67 347</a></li>
								</ul>
							</div>
						</div>
						<div class="col-12 col-md-6 col-lg-6 col-xl-7">
							<div class="header-top-right">
								<ul class="header-top-list">
									<li><a><i></i> Xin chào! ${user.firstName}
											${user.lastName}</a></li>
									<li>
									<li><a href="<c:url value='/logout'/>"><i
											class="far fa-sign-in"></i> Đăng xuất</a></li>
									<li>
										<div class="dropdown">
											<a href="#" class="dropdown-toggle" data-bs-toggle="dropdown"
												aria-expanded="false"> <i class="far fa-globe-americas"></i>
												EN
											</a>
											<div class="dropdown-menu">
												<a class="dropdown-item" href="#">EN</a> <a
													class="dropdown-item" href="#">VI</a>
											</div>
										</div>
									</li>
									<li class="social">
										<div class="header-top-social">
											<span>Follow Us: </span> <a href="#"><i
												class="fab fa-facebook"></i></a> <a href="#"><i
												class="fab fa-x-twitter"></i></a> <a href="#"><i
												class="fab fa-instagram"></i></a> <a href="#"><i
												class="fab fa-linkedin"></i></a>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- header top end -->


		<!-- navbar -->
		<div class="main-navigation">
			<nav class="navbar navbar-expand-lg">
				<div class="container position-relative">
					<a class="navbar-brand" href="/FurSPS_Nhom5/user/home"> <img
						src="/FurSPS_Nhom5/assets/img/logo/logo.png" alt="logo">
					</a>

					<div class="offcanvas offcanvas-start" tabindex="-1"
						id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
						<div class="offcanvas-header">
							<a href="index.html" class="offcanvas-brand"
								id="offcanvasNavbarLabel"> <img
								src="/FurSPS_Nhom5/assets/img/logo/logo.png" alt="">
							</a>
							<button type="button" class="btn-close"
								data-bs-dismiss="offcanvas" aria-label="Close"></button>
						</div>
						<div class="offcanvas-body">
							<ul class="navbar-nav justify-content-end flex-grow-1 pe-lg-5">
								<li class="nav-item"><a
									class="nav-link <c:if test='${fn:contains(pageContext.request.requestURI, "home")}'>active</c:if>"
									href="<c:url value='/user/home'/>">Trang chủ</a></li>
								<li class="nav-item"><a
									class="nav-link <c:if test='${fn:contains(pageContext.request.requestURI, "home")}'>active</c:if>"
									href="<c:url value='/user/about'/>">Giới thiệu</a></li>
								<li class="nav-item"><a
									class="nav-link<c:if test='${fn:contains(pageContext.request.requestURI, "products")}'>active</c:if>"
									href="<c:url value='/user/products'/>">Sản phẩm</a></li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#"
									data-bs-toggle="dropdown">Tài khoản,  ${user.firstName}</a>
									<ul class="dropdown-menu fade-down">
										<li><a class="dropdown-item"
											href="<c:url value='/user/infoUser'/>">Thông tin cá nhân</a></li>
										<li class="dropdown-submenu"><a
											class="dropdown-item dropdown-toggle" href="#">Đơn hàng</a>
											<ul class="dropdown-menu">
												<li><a class="dropdown-item" href="order-list.html">Danh
														sách dơn</a></li>
												<li><a class="dropdown-item" href="order-detail.html">Chi
														tiết đơn</a></li>
											</ul></li>
										<li><a class="dropdown-item" href="wishlist.html">Danh
												sách yêu thích</a></li>
										<li><a class="dropdown-item" href="track-order.html">Theo
												dõi đơn hàng</a></li>
										<li><a class="dropdown-item"
											href="<c:url value='/logout'/>">Đăng xuất</a></li>
									</ul></li>
							</ul>
						</div>
					</div>

					<div class="nav-right">
						<ul class="nav-right-list">
							<li class="dropdown-cart"><a
								class="list-link shop-cart"> <i class="far fa-shopping-bag"></i><span>${totalQuantity}</span>
							</a>
								<div class="dropdown-cart-menu">
									<div class="dropdown-cart-header">
										<span>subTotal</span> <a href="shop-cart.html">View Cart</a>
									</div>
									<ul class="dropdown-cart-list">
										<c:forEach items="${carts}" var="item">
											<li class="cart-item-row" id="cart-item-${item.itemID}">
												<div class="dropdown-cart-item">
													<div class="cart-img">
														<a href="#"><img src="${item.image}" alt="#"></a>
													</div>
													<div class="cart-info">
														<h4>
															<a href="#">${item.productName}</a>
														</h4>
														<p class="cart-qty">
															${item.quantity} x - <span><fmt:formatNumber
																	type="currency" value="${item.totalPrice}"
																	currencyCode="VND" pattern="#,##0 VND"
																	var="formattedPrice" /> ${formattedPrice}</span>
														</p>
													</div>
													<a href="javascript:void(0);" class="cart-remove"
														data-customer-id="${user.userID}"
														data-item-id="${item.itemID}" title="Remove this item">
														<i class="far fa-times-circle"></i>
													</a>
												</div>
											</li>
										</c:forEach>
									</ul>
									<div class="dropdown-cart-bottom">
										<div class="dropdown-cart-total">
											<span>Total</span> <span class="total-amount"> <fmt:formatNumber
													type="currency" value="${subTotal}" currencyCode="VND"
													pattern="#,##0 VND" var="formattedPrice" />
												${formattedPrice}
											</span>
										</div>
										<a href="#" class="theme-btn">Checkout</a>
									</div>
								</div></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
		<!-- navbar end -->

	</header>
	<!-- header area end -->

	<!-- scroll-top -->
	<a href="#" id="scroll-top"><i class="far fa-arrow-up-from-arc"></i></a>
	<!-- scroll-top end -->
	<script src="/FurSPS_Nhom5/assets/js/jquery-3.7.1.min.js"></script>
	<script>
		$(document).on('click', '.cart-remove', function(e) {
			e.preventDefault();

			let customerID = $(this).data('customer-id');
			let itemID = $(this).data('item-id');

			$.ajax({
				url : '/FurSPS_Nhom5/userDeleteItem',
				type : 'POST',
				data : {
					customerID : customerID,
					itemID : itemID
				},
				success : function(response) {
					$('#cart-item-' + itemID).remove();
					$('.shop-cart span').text(response.totalQuantity);
					
			        const formatter = new Intl.NumberFormat('vi-VN', {
			            style: 'currency',
			            currency: 'VND'
			        });
			        const formattedSubTotal = formatter.format(response.subTotal).replace('₫', 'VNĐ');
			        $('.total-amount').text(formattedSubTotal);
				},
			});
		});
	</script>

</body>

</html>