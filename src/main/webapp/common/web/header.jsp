<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">


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
									<li><a><i
											class="far fa-envelopes"></i> <span class="__cf_email__"
											data-cfemail="a2cbccc4cde2c7dac3cfd2cec78cc1cdcf">shopfursps@gmail.com</span></a></li>
									<li><a><i
											class="far fa-headset"></i> +84 377 67 347</a></li>
								</ul>
							</div>
						</div>
						<div class="col-12 col-md-6 col-lg-6 col-xl-7">
							<div class="header-top-right">
								<ul class="header-top-list">
									<li><a href="<c:url value='/login'/>"><i
											class="far fa-sign-in"></i> Login</a></li>
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
					<a class="navbar-brand" href="/FurSPS_Nhom5/home"> <img
						src="assets/img/logo/logo.png" alt="logo">
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
								src="assets/img/logo/logo.png" alt="">
							</a>
							<button type="button" class="btn-close"
								data-bs-dismiss="offcanvas" aria-label="Close"></button>
						</div>
						<div class="offcanvas-body">
							<ul class="navbar-nav justify-content-end flex-grow-1 pe-lg-5">
								<li class="nav-item"><a
									class="nav-link <c:if test='${fn:contains(pageContext.request.requestURI, "home")}'>active</c:if>"
									href="<c:url value='/home'/>">Trang chủ</a></li>
								<li class="nav-item"><a
									class="nav-link <c:if test='${fn:contains(pageContext.request.requestURI, "home")}'>active</c:if>"
									href="<c:url value='/about'/>">Giới thiệu</a></li>
								<li class="nav-item"><a
									class="nav-link<c:if test='${fn:contains(pageContext.request.requestURI, "products")}'>active</c:if>"
									href="<c:url value='/products'/>">Sản phẩm</a></li>
									<li class="nav-item"><a
									class="nav-link"
									href="<c:url value='/login'/>">Đăng nhập</a></li>
							</ul>
							<!-- nav-right -->
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




</body>

</html>