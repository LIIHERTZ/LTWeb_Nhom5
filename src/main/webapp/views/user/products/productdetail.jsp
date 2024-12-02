<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<style>
.avatar-img {
	width: 50px; /* Kích thước ảnh nhỏ lại */
	height: 50px; /* Chiều cao bằng chiều rộng để tạo hình tròn */
	border-radius: 50%; /* Làm cho ảnh thành hình tròn */
	object-fit: cover; /* Đảm bảo ảnh không bị méo */
}
</style>
<main class="main">

	<!-- breadcrumb -->
	<div class="site-breadcrumb">
		<div class="site-breadcrumb-bg"
			style="background: url(/FurSPS_Nhom5/assets/img/breadcrumb/01.jpg)"></div>
		<div class="container">
			<div class="site-breadcrumb-wrap">
				<h4 class="breadcrumb-title">Shop Single</h4>
				<ul class="breadcrumb-menu">
					<li><a href="index.html"><i class="far fa-home"></i> Home</a></li>
					<li class="active">Shop Single</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- breadcrumb end -->


	<!-- shop single -->
	<div class="shop-single py-90">
		<div class="container">
			<div class="row">
				<div class="col-md-9 col-lg-6 col-xxl-5">
					<div class="shop-single-gallery">
						<div class="flexslider-thumbnails">
							<ul class="slides">
								<c:forEach items="${product.listItem}" var="item"
									varStatus="loop">
									<li data-thumb="${item.image}" rel="adjustX:10, adjustY:">
										<img src="${item.image}" alt="#">
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-md-12 col-lg-6 col-xxl-6">
					<div class="shop-single-info">
						<h4 class="shop-single-title">${product.productName}</h4>
						<span class="stext-105 cl3"> <c:forEach var="i" begin="1"
								end="5">
								<i class="fas fa-star"
									style="${product.avgRating >= i ? 'color: gold;' : ''}"></i>
							</c:forEach>
						</span>

						<div class="shop-single-price">
							<c:if test="${product.displayedPromotionPrice ne 0}">
								<del>
									<span class="amount"> <fmt:formatNumber
											value="${product.displayedOriginalPrice}" currencyCode="VND"
											pattern="#,##0 VND" />
									</span>
								</del>
								<span class="amount"> <fmt:formatNumber
										value="${product.displayedPromotionPrice}" currencyCode="VND"
										pattern="#,##0 VND" var="formattedPrice" /> ${formattedPrice}
								</span>
							</c:if>
							<c:if test="${product.displayedPromotionPrice eq 0}">
								<span class="amount"> <fmt:formatNumber type="currency"
										value="${product.displayedOriginalPrice}" currencyCode="VND"
										pattern="#,##0 VND" var="formattedPrice" />${formattedPrice}
								</span>
							</c:if>
						</div>
						<ul class="quickview-list">
							<li><strong>Chất liệu:</strong> ${product.material}</li>
							<li><strong>Nhà cung cấp:</strong>
								${product.getSupplierName()}</li>
							<li><strong>Xuất xứ:</strong> ${product.origin}</li>
							<li><strong>Mô tả:</strong> ${product.description}</li>
						</ul>
						<div class="shop-single-cs">
							<div class="row">
								<div class="col-md-3 col-lg-4 col-xl-3">
									<div class="shop-single-size">
										<h6>Quantity</h6>
										<div class="shop-cart-qty">
											<button class="minus-btn">
												<i class="fal fa-minus"></i>
											</button>
											<input class="quantity" type="text" value="1" disabled>
											<button class="plus-btn">
												<i class="fal fa-plus"></i>
											</button>
										</div>
									</div>
								</div>
								<div class="col-md-3 col-lg-4 col-xl-3"
									style="margin-right: 20px;">
									<div class="shop-single-size">
										<h6>Size</h6>
										<select class="select">
											<c:forEach items="${product.listItem}" var="item">
												<option value="${item.size}">${item.size}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="col-md-6 col-lg-12 col-xl-7">
									<div class="shop-single-color">
										<h6>Color</h6>
										<ul class="shop-checkbox-list color">
											<c:forEach items="${product.listItem}" var="item">
												<li>
													<div class="form-check">
														<input class="form-check-input" type="radio"
															id="color${item.itemID}" name="color"
															data-stock="${item.stock}"> <label
															class="form-check-label" for="color${item.itemID}">
															<span style="background-color: ${item.colorCode}"></span>
														</label>
													</div>
												</li>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<div class="shop-single-sortinfo">
							<ul>
								<li>Stock: <span id="stock-info">???</span></li>
							</ul>
						</div>
						<div class="shop-single-action">
							<div class="row align-items-center">
								<div class="col-md-6 col-lg-12 col-xl-6">
									<div class="shop-single-btn">
										<a href="#" class="theme-btn"><span
											class="far fa-shopping-bag"></span>Add To Cart</a>
									</div>
								</div>
								<div class="col-md-6 col-lg-12 col-xl-6">
									<div class="shop-single-share">
										<span>Share:</span> <a href="#"><i
											class="fab fa-facebook-f"></i></a> <a href="#"><i
											class="fab fa-x-twitter"></i></a> <a href="#"><i
											class="fab fa-linkedin-in"></i></a> <a href="#"><i
											class="fab fa-pinterest-p"></i></a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- shop single details -->
			<div class="shop-single-details">
				<nav>
					<div class="nav nav-tabs" id="nav-tab" role="tablist">
						<button class="nav-link active" id="nav-tab3" data-bs-toggle="tab"
							data-bs-target="#tab3" type="button" role="tab"
							aria-controls="tab3" aria-selected="true">Reviews
							(${fn:length(ratingList)})</button>
					</div>
				</nav>
				<div class="tab-content" id="nav-tabContent">
					<div class="tab-pane fade show active" id="tab3" role="tabpanel"
						aria-labelledby="nav-tab3">
						<div class="shop-single-review">
							<div class="blog-comments">
								<h5>Reviews</h5>
								<c:if test="${not empty ratingList}">
									<div class="blog-comments-wrap">
										<c:forEach items="${ratingList}" var="item" varStatus="loop">
											<div class="rating-line">
												<span> <strong>${item.numOfStar} Stars :</strong>
												</span> <span class="stext-105 cl3"> <i class="fas fa-star"
													style="${item.numOfStar >= 1 ? 'color: gold;' : ''}"></i> <i
													class="fas fa-star"
													style="${item.numOfStar >= 2 ? 'color: gold;' : ''}"></i> <i
													class="fas fa-star"
													style="${item.numOfStar >= 3 ? 'color: gold;' : ''}"></i> <i
													class="fas fa-star"
													style="${item.numOfStar >= 4 ? 'color: gold;' : ''}"></i> <i
													class="fas fa-star"
													style="${item.numOfStar >= 5 ? 'color: gold;' : ''}"></i>
												</span> <span> (${item.numOfRating})</span>
											</div>
										</c:forEach>
										<c:forEach items="${detailList}" var="item" varStatus="loop">
											<div class="blog-comments-item">
												<img src="${item.avatar}" alt="thumb" class="avatar-img">
												<div class="blog-comments-content">
													<h5>${item.name}</h5>
													<span><i class="far fa-clock"></i> <fmt:formatDate
															value="${item.evaluationDate}" pattern="dd/MM/yyyy" /> </span>
													<p>${item.content}</p>
													<a href="#"><i class="far fa-reply"></i> Reply</a>
													<div class="review-rating">
														<span class="stext-105 cl3"> <i class="fas fa-star"
															style="${item.rating >= 1 ? 'color: gold;' : ''}"></i> <i
															class="fas fa-star"
															style="${item.rating >= 2 ? 'color: gold;' : ''}"></i> <i
															class="fas fa-star"
															style="${item.rating >= 3 ? 'color: gold;' : ''}"></i> <i
															class="fas fa-star"
															style="${item.rating >= 4 ? 'color: gold;' : ''}"></i> <i
															class="fas fa-star"
															style="${item.rating >= 5 ? 'color: gold;' : ''}"></i>
														</span>
													</div>
												</div>
											</div>
										</c:forEach>
									</div>
								</c:if>
								<div class="blog-comments-form">
									<h4 class="mb-4">Leave A Review</h4>
									<form action="#">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<input type="text" class="form-control"
														placeholder="Your Name*">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<input type="email" class="form-control"
														placeholder="Your Email*">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<input type="text" class="form-control"
														placeholder="Your Subject*">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<select class="form-control form-select">
														<option value="">Your Rating</option>
														<option value="5">5 Stars</option>
														<option value="4">4 Stars</option>
														<option value="3">3 Stars</option>
														<option value="2">2 Stars</option>
														<option value="1">1 Star</option>
													</select>
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													<textarea class="form-control" rows="5"
														placeholder="Your Review*"></textarea>
												</div>
												<button type="submit" class="theme-btn">
													<span class="far fa-paper-plane"></span> Submit Review
												</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- shop single details end -->


			<!-- related item -->
			<div class="product-area related-item pt-40">
				<div class="container px-0">
					<div class="row">
						<div class="col-12">
							<div class="site-heading-inline">
								<h2 class="site-title">Sản phẩm cùng nhà cung cấp</h2>
							</div>
						</div>
					</div>
					<div class="row g-4 item-2">
						<c:forEach items="${supProList}" var="item" varStatus="loop">
							<div class="col-md-6 col-lg-3">
								<div class="product-item">
									<div class="product-img">
										<a href="shop-single.html"><img
											src="${item.displayedImage}" alt=""></a>
										<div class="product-action-wrap">
											<div class="product-action">
												<a href='<c:url value="/products?id=${item.productID}"/>'
													data-bs-toggle="modal"
													data-bs-target="#quickview-${item.productID}"
													data-tooltip="tooltip" title="Quick View"><i
													class="far fa-eye"></i></a> <a
													href='<c:url value="/user/products?id=${item.productID}"/>'
													data-tooltip="tooltip" title="View detail"><i
													class="far fa-search"></i></a>
											</div>
										</div>
									</div>
									<div class="product-content">
										<h3 class="product-title">
											<a
												href="<c:url value='/user/products?id=${item.productID}' />">${item.productName}</a>
										</h3>
										<p>${item.description}</p>
										<span class="stext-105 cl3"> <i class="fas fa-star"
											style="${item.avgRating >= 1 ? 'color: gold;' : ''}"></i> <i
											class="fas fa-star"
											style="${item.avgRating >= 2 ? 'color: gold;' : ''}"></i> <i
											class="fas fa-star"
											style="${item.avgRating >= 3 ? 'color: gold;' : ''}"></i> <i
											class="fas fa-star"
											style="${item.avgRating >= 4 ? 'color: gold;' : ''}"></i> <i
											class="fas fa-star"
											style="${item.avgRating >= 5 ? 'color: gold;' : ''}"></i>
										</span>
										<div class="product-bottom">
											<div class="product-price">
												<span><fmt:formatNumber type="currency"
														value="${item.displayedPromotionPrice}" currencyCode="VND"
														pattern="#,##0 VND" var="formattedPrice" />
													${formattedPrice}</span>
											</div>
											<button type="button" class="product-cart-btn"
												data-bs-placement="left" data-tooltip="tooltip"
												title="Add To Cart">
												<i class="far fa-shopping-bag"></i>
											</button>
										</div>
									</div>
								</div>
							</div>
							<!-- modal quick shop-->
							<div class="modal quickview fade"
								id="quickview-${item.productID}" data-bs-backdrop="static"
								data-bs-keyboard="false" tabindex="-1"
								aria-labelledby="quickview" aria-hidden="true">
								<div class="modal-dialog modal-lg modal-dialog-centered"
									role="document">
									<div class="modal-content">
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close">
											<i class="far fa-xmark"></i>
										</button>
										<div class="modal-body">
											<div class="row">
												<div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
													<img src="${item.displayedImage}" alt="IMG-PRODUCT">
												</div>
												<div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
													<div class="quickview-content">
														<h4 class="quickview-title">${item.productName}</h4>
														<span class="stext-105 cl3"> <i class="fas fa-star"
															style="${item.avgRating >= 1 ? 'color: gold;' : ''}"></i>
															<i class="fas fa-star"
															style="${item.avgRating >= 2 ? 'color: gold;' : ''}"></i>
															<i class="fas fa-star"
															style="${item.avgRating >= 3 ? 'color: gold;' : ''}"></i>
															<i class="fas fa-star"
															style="${item.avgRating >= 4 ? 'color: gold;' : ''}"></i>
															<i class="fas fa-star"
															style="${item.avgRating >= 5 ? 'color: gold;' : ''}"></i>
														</span>
														<div class="quickview-price">
															<h5>
																<c:if test="${item.displayedPromotionPrice ne 0}">
																	<del>
																		<fmt:formatNumber
																			value="${item.displayedOriginalPrice}"
																			currencyCode="VND" pattern="#,##0 VND"
																			var="formattedOriginalPrice" />
																		${formattedOriginalPrice}
																	</del>
																	<span> <fmt:formatNumber
																			value="${item.displayedPromotionPrice}"
																			currencyCode="VND" pattern="#,##0 VND"
																			var="formattedPrice" /> ${formattedPrice}
																	</span>
																</c:if>
																<c:if test="${item.displayedPromotionPrice eq 0}">
																	<span> <fmt:formatNumber type="currency"
																			value="${item.displayedOriginalPrice}"
																			currencyCode="VND" pattern="#,##0 VND"
																			var="formattedPrice" />${formattedPrice}
																	</span>
																</c:if>
															</h5>
														</div>
														<ul class="quickview-list">
															<li><strong>Chất liệu:</strong> ${item.material}</li>
															<li><strong>Nhà cung cấp:</strong>
																${item.getSupplierName()}</li>
															<li><strong>Xuất xứ:</strong> ${item.origin}</li>
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
						</c:forEach>
					</div>

				</div>
			</div>
			<!-- related item end -->
			<div class="product-area related-item pt-40">
				<div class="container px-0">
					<div class="row">
						<div class="col-12">
							<div class="site-heading-inline">
								<h2 class="site-title">Sản phẩm cùng loại</h2>
							</div>
						</div>
					</div>
					<div class="row g-4 item-2">
						<c:forEach items="${cateProList}" var="item" varStatus="loop">
							<div class="col-md-6 col-lg-3">
								<div class="product-item">
									<div class="product-img">
										<a href="shop-single.html"><img
											src="${item.displayedImage}" alt=""></a>
										<div class="product-action-wrap">
											<div class="product-action">
												<a href='<c:url value="/products?id=${item.productID}"/>'
													data-bs-toggle="modal"
													data-bs-target="#quickview-${item.productID}"
													data-tooltip="tooltip" title="Quick View"><i
													class="far fa-eye"></i></a> <a
													href='<c:url value="/user/products?id=${item.productID}"/>'
													data-tooltip="tooltip" title="View detail"><i
													class="far fa-search"></i></a>
											</div>
										</div>
									</div>
									<div class="product-content">
										<h3 class="product-title">
											<a
												href="<c:url value='/user/products?id=${item.productID}' />">${item.productName}</a>
										</h3>
										<p>${item.description}</p>
										<span class="stext-105 cl3"> <i class="fas fa-star"
											style="${item.avgRating >= 1 ? 'color: gold;' : ''}"></i> <i
											class="fas fa-star"
											style="${item.avgRating >= 2 ? 'color: gold;' : ''}"></i> <i
											class="fas fa-star"
											style="${item.avgRating >= 3 ? 'color: gold;' : ''}"></i> <i
											class="fas fa-star"
											style="${item.avgRating >= 4 ? 'color: gold;' : ''}"></i> <i
											class="fas fa-star"
											style="${item.avgRating >= 5 ? 'color: gold;' : ''}"></i>
										</span>
										<div class="product-bottom">
											<div class="product-price">
												<span><fmt:formatNumber type="currency"
														value="${item.displayedPromotionPrice}" currencyCode="VND"
														pattern="#,##0 VND" var="formattedPrice" />
													${formattedPrice}</span>
											</div>
											<button type="button" class="product-cart-btn"
												data-bs-placement="left" data-tooltip="tooltip"
												title="Add To Cart">
												<i class="far fa-shopping-bag"></i>
											</button>
										</div>
									</div>
								</div>
							</div>
							<!-- modal quick shop-->
							<div class="modal quickview fade"
								id="quickview-${item.productID}" data-bs-backdrop="static"
								data-bs-keyboard="false" tabindex="-1"
								aria-labelledby="quickview" aria-hidden="true">
								<div class="modal-dialog modal-lg modal-dialog-centered"
									role="document">
									<div class="modal-content">
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close">
											<i class="far fa-xmark"></i>
										</button>
										<div class="modal-body">
											<div class="row">
												<div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
													<img src="${item.displayedImage}" alt="IMG-PRODUCT">
												</div>
												<div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
													<div class="quickview-content">
														<h4 class="quickview-title">${item.productName}</h4>
														<span class="stext-105 cl3"> <i class="fas fa-star"
															style="${item.avgRating >= 1 ? 'color: gold;' : ''}"></i>
															<i class="fas fa-star"
															style="${item.avgRating >= 2 ? 'color: gold;' : ''}"></i>
															<i class="fas fa-star"
															style="${item.avgRating >= 3 ? 'color: gold;' : ''}"></i>
															<i class="fas fa-star"
															style="${item.avgRating >= 4 ? 'color: gold;' : ''}"></i>
															<i class="fas fa-star"
															style="${item.avgRating >= 5 ? 'color: gold;' : ''}"></i>
														</span>
														<div class="quickview-price">
															<h5>
																<c:if test="${item.displayedPromotionPrice ne 0}">
																	<del>
																		<fmt:formatNumber
																			value="${item.displayedOriginalPrice}"
																			currencyCode="VND" pattern="#,##0 VND"
																			var="formattedOriginalPrice" />
																		${formattedOriginalPrice}
																	</del>
																	<span> <fmt:formatNumber
																			value="${item.displayedPromotionPrice}"
																			currencyCode="VND" pattern="#,##0 VND"
																			var="formattedPrice" /> ${formattedPrice}
																	</span>
																</c:if>
																<c:if test="${item.displayedPromotionPrice eq 0}">
																	<span> <fmt:formatNumber type="currency"
																			value="${item.displayedOriginalPrice}"
																			currencyCode="VND" pattern="#,##0 VND"
																			var="formattedPrice" />${formattedPrice}
																	</span>
																</c:if>
															</h5>
														</div>
														<ul class="quickview-list">
															<li><strong>Chất liệu:</strong> ${item.material}</li>
															<li><strong>Nhà cung cấp:</strong>
																${item.getSupplierName()}</li>
															<li><strong>Xuất xứ:</strong> ${item.origin}</li>
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
						</c:forEach>
					</div>

				</div>
			</div>
			<!-- related item end -->
		</div>
	</div>
	<!-- shop single end -->

</main>



<script>
document.addEventListener('DOMContentLoaded', function() {
    // Lắng nghe sự kiện khi người dùng chọn màu
    document.querySelectorAll('input[name="color"]').forEach(function(radio) {
        radio.addEventListener('change', function() {
            // Lấy giá trị của stock từ data-stock của radio button được chọn
            var stock = this.getAttribute('data-stock');
            // Cập nhật giá trị stock vào phần tử span có id="stock-info"
            document.getElementById('stock-info').textContent = stock;
        });
    });
});
</script>
<!-- modal quick shop end -->
</html>