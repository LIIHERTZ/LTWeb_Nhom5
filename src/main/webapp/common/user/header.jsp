<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">


<!-- Mirrored from live.themewild.com/fameo/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 03 Nov 2024 06:40:06 GMT -->
<head>
<!-- meta tags -->
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="keywords" content="">

<!-- favicon -->
<link rel="icon" type="image/x-icon"
	href="/FurSPS_Nhom5/assets/img/logo/favicon.png">

<!-- css -->
<link rel="stylesheet" href="/FurSPS_Nhom5/assets/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/FurSPS_Nhom5/assets/css/all-fontawesome.min.css">
<link rel="stylesheet" href="/FurSPS_Nhom5/assets/css/animate.min.css">
<link rel="stylesheet"
	href="/FurSPS_Nhom5/assets/css/magnific-popup.min.css">
<link rel="stylesheet"
	href="/FurSPS_Nhom5/assets/css/owl.carousel.min.css">
<link rel="stylesheet" href="/FurSPS_Nhom5/assets/css/jquery-ui.min.css">
<link rel="stylesheet"
	href="/FurSPS_Nhom5/assets/css/nice-select.min.css">
<link rel="stylesheet" href="/FurSPS_Nhom5/assets/css/style.css">

</head>

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
									<li><a ><i></i> Xin chào! ${user.firstName} ${user.lastName}</a></li>
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
					<div class="mobile-menu-right">
						<div class="mobile-menu-btn">
							<a href="#" class="nav-right-link search-box-outer"><i
								class="far fa-search"></i></a> <a href="wishlist.html"
								class="nav-right-link"><i class="far fa-heart"></i><span>0</span></a>
							<a href="shop-cart.html" class="nav-right-link"><i
								class="far fa-shopping-bag"></i><span>0</span></a>
						</div>
						<button class="navbar-toggler" type="button"
							data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
							aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
							<span></span> <span></span> <span></span>
						</button>
					</div>
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
									href="about.html">Giới thiệu</a></li>
								<li class="nav-item"><a
									class="nav-link<c:if test='${fn:contains(pageContext.request.requestURI, "products")}'>active</c:if>"
									href="<c:url value='/user/products'/>">Sản phẩm</a></li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#"
									data-bs-toggle="dropdown">Tài khoản</a>
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
							<!-- nav-right -->
							<div class="nav-right">
								<ul class="nav-right-list">
									<li><a href="#" class="list-link search-box-outer"> <i
											class="far fa-search"></i>
									</a></li>
									<li><a href="wishlist.html" class="list-link"> <i
											class="far fa-heart"></i><span>0</span>
									</a></li>
									<li class="dropdown-cart"><a href="#"
										class="list-link shop-cart"> <i
											class="far fa-shopping-bag"></i><span>0</span>
									</a>
										<div class="dropdown-cart-menu">
											<div class="dropdown-cart-header">
												<span>03 Items</span> <a href="shop-cart.html">View Cart</a>
											</div>
											<ul class="dropdown-cart-list">
												<li>
													<div class="dropdown-cart-item">
														<div class="cart-img">
															<a href="#"><img
																src="/FurSPS_Nhom5/assets/img/product/06.png" alt="#"></a>
														</div>
														<div class="cart-info">
															<h4>
																<a href="#">Living Room Chair</a>
															</h4>
															<p class="cart-qty">
																1x - <span class="cart-amount">$200.00</span>
															</p>
														</div>
														<a href="#" class="cart-remove" title="Remove this item"><i
															class="far fa-times-circle"></i></a>
													</div>
												</li>
												<li>
													<div class="dropdown-cart-item">
														<div class="cart-img">
															<a href="#"><img
																src="/FurSPS_Nhom5/assets/img/product/14.png" alt="#"></a>
														</div>
														<div class="cart-info">
															<h4>
																<a href="#">Living Room Chair</a>
															</h4>
															<p class="cart-qty">
																1x - <span class="cart-amount">$120.00</span>
															</p>
														</div>
														<a href="#" class="cart-remove" title="Remove this item"><i
															class="far fa-times-circle"></i></a>
													</div>
												</li>
												<li>
													<div class="dropdown-cart-item">
														<div class="cart-img">
															<a href="#"><img
																src="/FurSPS_Nhom5/assets/img/product/15.png" alt="#"></a>
														</div>
														<div class="cart-info">
															<h4>
																<a href="#">Living Room Chair</a>
															</h4>
															<p class="cart-qty">
																1x - <span class="cart-amount">$330.00</span>
															</p>
														</div>
														<a href="#" class="cart-remove" title="Remove this item"><i
															class="far fa-times-circle"></i></a>
													</div>
												</li>
											</ul>
											<div class="dropdown-cart-bottom">
												<div class="dropdown-cart-total">
													<span>Total</span> <span class="total-amount">$650.00</span>
												</div>
												<a href="shop-checkout.html" class="theme-btn">Checkout</a>
											</div>
										</div></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</nav>
		</div>
		<!-- navbar end -->

	</header>
	<!-- header area end -->


	<!-- popup search -->
	<div class="search-popup">
		<button class="close-search">
			<span class="far fa-times"></span>
		</button>
		<form action="#">
			<div class="form-group">
				<input type="search" name="search-field" class="form-control"
					placeholder="Search Here..." required>
				<button type="submit">
					<i class="far fa-search"></i>
				</button>
			</div>
		</form>
	</div>
	<!-- popup search end -->




	<!-- scroll-top -->
	<a href="#" id="scroll-top"><i class="far fa-arrow-up-from-arc"></i></a>
	<!-- scroll-top end -->


	<!-- modal quick shop-->
	<div class="modal quickview fade" id="quickview"
		data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="quickview" aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-centered"
			role="document">
			<div class="modal-content">
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close">
					<i class="far fa-xmark"></i>
				</button>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
							<img src="/FurSPS_Nhom5/assets/img/product/04.png" alt="#">
						</div>
						<div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
							<div class="quickview-content">
								<h4 class="quickview-title">Simple Denim Chair</h4>
								<div class="quickview-rating">
									<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
										class="fas fa-star"></i> <i class="fas fa-star-half-alt"></i>
									<i class="far fa-star"></i> <span class="rating-count">
										(4 Customer Reviews)</span>
								</div>
								<div class="quickview-price">
									<h5>
										<del>$860</del>
										<span>$740</span>
									</h5>
								</div>
								<ul class="quickview-list">
									<li>Brand:<span>Ricordi</span></li>
									<li>Category:<span>Living Room</span></li>
									<li>Stock:<span class="stock">Available</span></li>
									<li>Code:<span>789FGSA</span></li>
								</ul>
								<div class="quickview-cart">
									<a href="#" class="theme-btn">Add to cart</a>
								</div>
								<div class="quickview-social">
									<span>Share:</span> <a href="#"><i
										class="fab fa-facebook-f"></i></a> <a href="#"><i
										class="fab fa-x-twitter"></i></a> <a href="#"><i
										class="fab fa-pinterest-p"></i></a> <a href="#"><i
										class="fab fa-instagram"></i></a> <a href="#"><i
										class="fab fa-linkedin-in"></i></a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- modal quick shop end -->


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

</html>