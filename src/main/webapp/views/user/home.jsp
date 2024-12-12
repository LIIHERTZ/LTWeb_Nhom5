<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<main class="main">

	<!-- hero slider -->
	<div class="hero-section hs-1">
		<div class="container">
			<div class="hero-slider owl-carousel owl-theme">

				<c:forEach items="${list}" var="item">
					<div class="hero-single">
						<div class="container">
							<div class="row align-items-center">
								<div class="col-lg-6">
									<div class="hero-content">
										<h6 class="hero-sub-title" data-animation="fadeInUp"
											data-delay=".25s">Welcome to FurSPS!</h6>
										<h1 class="hero-title" data-animation="fadeInRight"
											data-delay=".50s">
											We offers modern <span>furniture</span> for you
										</h1>
										<p data-animation="fadeInLeft" data-delay=".75s">${item.Description}</p>
										<div class="hero-btn" data-animation="fadeInUp"
											data-delay="1s">
											<a
												href='<c:url value="/user/products?id=${item.ProductID}"/>'
												class="theme-btn">Detail<i class="fas fa-arrow-right"></i>
											</a> <a href="/FurSPS_Nhom5/user/products"
												class="theme-btn theme-btn2">Show More<i
												class="fas fa-arrow-right"></i>
											</a>
										</div>
									</div>
								</div>
								<div class="col-lg-6">
									<div class="hero-right" data-animation="fadeInRight"
										data-delay=".25s">
										<div class="hero-img">
											<div class="hero-img-item">
												<button type="button">
													<i class="far fa-plus"></i>
												</button>
												<div class="hero-img-content">
													<img src="${item.Image}" alt="">
													<div class="hero-img-info">
														<h6>
															<a
																href='<c:url value="/user/products?id=${item.ProductID}"/>'>${item.ProductName}</a>
														</h6>
														<p>
															Price: <span><fmt:formatNumber type="currency"
																	value="${item.PromotionPrice}" currencyCode="VND"
																	pattern="#,##0 VND" var="formattedPrice" />
																${formattedPrice}</span>
														</p>
														<a
															href="<c:url value='/user/products?id=${item.ProductID}' />"
															class="theme-btn">Add Cart<i
															class="far fa-shopping-bag"></i></a>
													</div>
												</div>
											</div>
											<img src="${item.Image}" alt="">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- hero slider end -->


	<!-- small banner -->
	<div class="small-banner pb-100">
		<div class="container wow fadeInUp" data-wow-delay=".25s">
			<div class="row g-4">
				<div class="col-12 col-md-6 col-lg-4">
					<div class="banner-item">
						<img src="/FurSPS_Nhom5/assets/img/banner/mini-banner-1.jpg"
							alt="">
						<div class="banner-content">
							<p>Lounge Chair</p>
							<h3>
								Eames Lounge Chair <br> Collectons
							</h3>
							<a>Shop Now</a>
						</div>
					</div>
				</div>
				<div class="col-12 col-md-6 col-lg-4">
					<div class="banner-item">
						<img src="/FurSPS_Nhom5/assets/img/banner/mini-banner-2.jpg"
							alt="">
						<div class="banner-content">
							<p>Hot Sale</p>
							<h3>
								Best Couch Sale <br> Collections
							</h3>
							<a>Discover Now</a>
						</div>
					</div>
				</div>
				<div class="col-12 col-md-6 col-lg-4">
					<div class="banner-item">
						<img src="/FurSPS_Nhom5/assets/img/banner/mini-banner-3.jpg"
							alt="">
						<div class="banner-content">
							<p>Best Chair</p>
							<h3>
								Best Chair <br> Up To <span>50%</span> Off
							</h3>
							<a>Discover Now</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- small banner end -->


	<!-- trending item -->
	<div class="product-area pb-100">
		<div class="container">
			<div class="row">
				<div class="col-12 wow fadeInDown" data-wow-delay=".25s">
					<div class="site-heading-inline">
						<h2 class="site-title">Trending Items</h2>
						<a href="/FurSPS_Nhom5/user/products">View More <i class="fas fa-angle-double-right"></i></a>
					</div>
				</div>
			</div>
			<div class="product-wrap wow fadeInUp" data-wow-delay=".25s">
				<div class="product-slider owl-carousel owl-theme">
					<c:forEach items="${list}" var="item">
						<div class="product-item">
							<div class="product-img">
								<a href='<c:url value="/user/products?id=${item.ProductID}"/>'><img
									src="${item.Image}" alt=""></a>
								<div class="product-action-wrap"></div>
							</div>
							<div class="product-content">
								<h3 class="product-title">
									<a href='<c:url value="/user/products?id=${item.ProductID}"/>'>${item.ProductName}</a>
								</h3>
								<p>${item.Description}</p>
								<span class="stext-105 cl3"> <i class="fas fa-star"
									style="${item.Rating >= 1 ? 'color: gold;' : ''}"></i> <i
									class="fas fa-star"
									style="${item.Rating >= 2 ? 'color: gold;' : ''}"></i> <i
									class="fas fa-star"
									style="${item.Rating >= 3 ? 'color: gold;' : ''}"></i> <i
									class="fas fa-star"
									style="${item.Rating >= 4 ? 'color: gold;' : ''}"></i> <i
									class="fas fa-star"
									style="${item.Rating >= 5 ? 'color: gold;' : ''}"></i>
								</span>
								<div class="product-bottom">
									<div class="product-price">
										<span><fmt:formatNumber type="currency"
												value="${item.PromotionPrice}" currencyCode="VND"
												pattern="#,##0 VND" var="formattedPrice" />
											${formattedPrice}</span>
									</div>
									<a class="product-cart"
										href="<c:url value='/user/products?id=${item.ProductID}' />"
										data-bs-placement="left" data-tooltip="tooltip"
										title="Add To Cart"> <i class="far fa-shopping-bag"></i>
									</a>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<!-- trending item end -->


	<!-- big banner -->
	<div class="big-banner">
		<div class="container wow fadeInUp" data-wow-delay=".25s">
			<div class="banner-wrap"
				style="background-image: url(/FurSPS_Nhom5/assets/img/banner/big-banner.jpg);">
				<div class="row">
					<div class="col-lg-8 mx-auto">
						<div class="banner-content">
							<div class="banner-info">
								<h6>Mega Collections</h6>
								<h2>
									Huge Sale Up To <span>40%</span> Off
								</h2>
								<p>at our outlet stores</p>
							</div>
							<a href="/FurSPS_Nhom5/user/products" class="theme-btn">Shop
								Now<i class="fas fa-arrow-right"></i>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- big banner end -->


	<!-- feature area -->
	<div class="feature-area py-100">
		<div class="container wow fadeInUp" data-wow-delay=".25s">
			<div class="feature-wrap">
				<div class="row g-0">
					<div class="col-12 col-md-6 col-lg-3">
						<div class="feature-item">
							<div class="feature-icon">
								<i class="fal fa-truck"></i>
							</div>
							<div class="feature-content">
								<h4>Free Delivery</h4>
								<p>Orders Over $120</p>
							</div>
						</div>
					</div>
					<div class="col-12 col-md-6 col-lg-3">
						<div class="feature-item">
							<div class="feature-icon">
								<i class="fal fa-sync"></i>
							</div>
							<div class="feature-content">
								<h4>Get Refund</h4>
								<p>Within 30 Days Returns</p>
							</div>
						</div>
					</div>
					<div class="col-12 col-md-6 col-lg-3">
						<div class="feature-item">
							<div class="feature-icon">
								<i class="fal fa-wallet"></i>
							</div>
							<div class="feature-content">
								<h4>Safe Payment</h4>
								<p>100% Secure Payment</p>
							</div>
						</div>
					</div>
					<div class="col-12 col-md-6 col-lg-3">
						<div class="feature-item">
							<div class="feature-icon">
								<i class="fal fa-headset"></i>
							</div>
							<div class="feature-content">
								<h4>24/7 Support</h4>
								<p>Feel Free To Call Us</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- feature area end -->


	<!-- choose-area -->
	<div class="choose-area pb-100">
		<div class="container">
			<div class="row g-4 align-items-center wow fadeInDown"
				data-wow-delay=".25s">
				<div class="col-lg-4">
					<span class="site-title-tagline">Why Choose Us</span>
					<h2 class="site-title">We Provide Premium Quality Furniture
						For You</h2>
				</div>
				<div class="col-lg-4">
					<p>There are many variations of passages available but the
						majority have suffered you are going to use a passage you need to
						be sure alteration in some form by injected humour randomised
						words even slightly believable</p>
				</div>
				<div class="col-lg-4">
					<div class="choose-img">
						<img src="/FurSPS_Nhom5/assets/img/choose/01.jpg" alt="">
					</div>
				</div>
			</div>
			<div class="choose-content wow fadeInUp" data-wow-delay=".25s">
				<div class="row g-4">
					<div class="col-lg-4">
						<div class="choose-item">
							<div class="choose-icon">
								<img src="/FurSPS_Nhom5/assets/img/icon/warranty.svg" alt="">
							</div>
							<div class="choose-info">
								<h4>3 Years Warranty</h4>
								<p>It is a long established fact that a reader will be
									distracted by the readable content of a page when looking at
									its layout.</p>
							</div>
						</div>
					</div>
					<div class="col-lg-4">
						<div class="choose-item">
							<div class="choose-icon">
								<img src="/FurSPS_Nhom5/assets/img/icon/price.svg" alt="">
							</div>
							<div class="choose-info">
								<h4>Affordable Price</h4>
								<p>It is a long established fact that a reader will be
									distracted by the readable content of a page when looking at
									its layout.</p>
							</div>
						</div>
					</div>
					<div class="col-lg-4">
						<div class="choose-item">
							<div class="choose-icon">
								<img src="/FurSPS_Nhom5/assets/img/icon/delivery.svg" alt="">
							</div>
							<div class="choose-info">
								<h4>Free Shipping</h4>
								<p>It is a long established fact that a reader will be
									distracted by the readable content of a page when looking at
									its layout.</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- choose-area end-->


	<!-- about area -->
	<div class="about-area pt-50 pb-120 mb-30">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-6">
					<div class="about-left wow fadeInLeft" data-wow-delay=".25s">
						<div class="about-img">
							<img class="img-1" src="/FurSPS_Nhom5/assets/img/about/01.jpg"
								alt=""> <img class="img-2"
								src="/FurSPS_Nhom5/assets/img/about/02.jpg" alt=""> <img
								class="img-3" src="/FurSPS_Nhom5/assets/img/about/03.jpg" alt="">
						</div>
						<div class="about-experience">
							<div class="about-experience-icon">
								<img src="/FurSPS_Nhom5/assets/img/icon/experience.svg" alt="">
							</div>
							<b>30 Years Of <br> Experience
							</b>
						</div>
						<div class="about-shape">
							<img src="/FurSPS_Nhom5/assets/img/shape/01.png" alt="">
						</div>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="about-right wow fadeInRight" data-wow-delay=".25s">
						<div class="site-heading mb-3">
							<span class="site-title-tagline justify-content-start"> <i
								class="flaticon-drive"></i> About Us
							</span>
							<h2 class="site-title">
								We Provide Best And Quality <span>Furniture</span> Product For
								You
							</h2>
						</div>
						<p>We are standard text ever since the when an unknown printer
							took a galley of type and scrambled it to make a type specimen
							book. It has survived not only five but also the leap into
							electronic remaining essentially by injected humour unchanged.</p>
						<div class="about-list">
							<ul>
								<li><i class="fas fa-check-double"></i> Streamlined
									Shipping Experience</li>
								<li><i class="fas fa-check-double"></i> Affordable Modern
									Design</li>
								<li><i class="fas fa-check-double"></i> Competitive Price &
									Easy To Shop</li>
								<li><i class="fas fa-check-double"></i> We Made Awesome
									Products</li>
							</ul>
						</div>
						<a href="contact.html" class="theme-btn mt-4">Discover More<i
							class="fas fa-arrow-right"></i></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- about area end -->
</main>


</html>