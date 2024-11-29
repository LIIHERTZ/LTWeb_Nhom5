<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">


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
						<div class="text-warning mb-1 me-2">
							<span class="stext-105 cl3"> <i class="fas fa-star"
								style="${product.avgRating >= 1 ? 'color: gold;' : ''}"></i> <i
								class="fas fa-star"
								style="${product.avgRating >= 2 ? 'color: gold;' : ''}"></i> <i
								class="fas fa-star"
								style="${product.avgRating >= 3 ? 'color: gold;' : ''}"></i> <i
								class="fas fa-star"
								style="${product.avgRating >= 4 ? 'color: gold;' : ''}"></i> <i
								class="fas fa-star"
								style="${product.avgRating >= 5 ? 'color: gold;' : ''}"></i>
							</span>
						</div>
						<div class="shop-single-price">
							<c:if test="${product.displayedPromotionPrice ne 0}">
								<del>
									<fmt:formatNumber value="${product.displayedOriginalPrice}"
										currencyCode="VND" pattern="#,##0 VND"
										var="formattedOriginalPrice" />
									${formattedOriginalPrice}
								</del>
								<span> <fmt:formatNumber
										value="${product.displayedPromotionPrice}" currencyCode="VND"
										pattern="#,##0 VND" var="formattedPrice" /> ${formattedPrice}
								</span>
							</c:if>
							<c:if test="${product.displayedPromotionPrice eq 0}">
								<span> <fmt:formatNumber type="currency"
										value="${product.displayedOriginalPrice}" currencyCode="VND"
										pattern="#,##0 VND" var="formattedPrice" />${formattedPrice}
								</span>
							</c:if>
						</div>
						<ul class="quickview-list">
							<li><strong>Chất liệu:</strong> ${product.material}</li>
							<li><strong>Nhà cung cấp:</strong> ${product.getSupplierName()}</li>
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
											<input class="quantity" type="text" value="1" disabled="">
											<button class="plus-btn">
												<i class="fal fa-plus"></i>
											</button>
										</div>
									</div>
								</div>
								<div class="col-md-3 col-lg-4 col-xl-3">
									<div class="shop-single-size">
										<h6>Size</h6>
										<select class="select">
											<option value="">Choose Size</option>
											<option value="1">Extra Small</option>
											<option value="2">Small</option>
											<option value="3">Medium</option>
											<option value="4">Extra Large</option>
										</select>
									</div>
								</div>
								<div class="col-md-6 col-lg-12 col-xl-6">
									<div class="shop-single-color">
										<h6>Color</h6>
										<ul class="shop-checkbox-list color">
											<li>
												<div class="form-check">
													<input class="form-check-input" type="checkbox" id="color1">
													<label class="form-check-label" for="color1"><span
														style="background-color: #606ddd"></span></label>
												</div>
											</li>
											<li>
												<div class="form-check">
													<input class="form-check-input" type="checkbox" id="color2">
													<label class="form-check-label" for="color2"><span
														style="background-color: #4caf50"></span></label>
												</div>
											</li>
											<li>
												<div class="form-check">
													<input class="form-check-input" type="checkbox" id="color3">
													<label class="form-check-label" for="color3"><span
														style="background-color: #17a2b8"></span></label>
												</div>
											</li>
											<li>
												<div class="form-check">
													<input class="form-check-input" type="checkbox" id="color4">
													<label class="form-check-label" for="color4"><span
														style="background-color: #ffc107"></span></label>
												</div>
											</li>
											<li>
												<div class="form-check">
													<input class="form-check-input" type="checkbox" id="color5">
													<label class="form-check-label" for="color5"><span
														style="background-color: #f44336"></span></label>
												</div>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<div class="shop-single-sortinfo">
							<ul>
								<li>Stock: <span>Available</span></li>
								<li>SKU: <span>656TYTR</span></li>
								<li>Category: <span>Living Room</span></li>
								<li>Brand: <a href="#">Novak</a></li>
								<li>Tags: <a href="#">Furniture</a>,<a href="#">Chair</a>,<a
									href="#">Modern</a>,<a href="#">Shop</a></li>
							</ul>
						</div>
						<div class="shop-single-action">
							<div class="row align-items-center">
								<div class="col-md-6 col-lg-12 col-xl-6">
									<div class="shop-single-btn">
										<a href="#" class="theme-btn"><span
											class="far fa-shopping-bag"></span>Add To Cart</a> <a href="#"
											class="theme-btn theme-btn2" data-tooltip="tooltip"
											title="Add To Wishlist"><span class="far fa-heart"></span></a>
										<a href="#" class="theme-btn theme-btn2"
											data-tooltip="tooltip" title="Add To Compare"><span
											class="far fa-arrows-repeat"></span></a>
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
						<button class="nav-link active" id="nav-tab1" data-bs-toggle="tab"
							data-bs-target="#tab1" type="button" role="tab"
							aria-controls="tab1" aria-selected="true">Description</button>
						<button class="nav-link" id="nav-tab2" data-bs-toggle="tab"
							data-bs-target="#tab2" type="button" role="tab"
							aria-controls="tab2" aria-selected="false">Additional
							Info</button>
						<button class="nav-link" id="nav-tab3" data-bs-toggle="tab"
							data-bs-target="#tab3" type="button" role="tab"
							aria-controls="tab3" aria-selected="false">Reviews (05)</button>
					</div>
				</nav>
				<div class="tab-content" id="nav-tabContent">
					<div class="tab-pane fade show active" id="tab1" role="tabpanel"
						aria-labelledby="nav-tab1">
						<div class="shop-single-desc">
							<p>There are many variations of passages of Lorem Ipsum
								available, but the majority have suffered alteration in some
								form, by injected humour, or randomised words which don't look
								even slightly believable. If you are going to use a passage of
								Lorem Ipsum, you need to be sure there isn't anything
								embarrassing hidden in the middle of text. All the Lorem Ipsum
								generators on the Internet tend to repeat predefined chunks as
								necessary, making this the first true generator on the Internet.
							</p>
							<p>Sed ut perspiciatis unde omnis iste natus error sit
								voluptatem accusantium doloremque laudantium, totam rem aperiam,
								eaque ipsa quae ab illo inventore veritatis et quasi architecto
								beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem
								quia voluptas sit aspernatur aut odit aut fugit, sed quia
								consequuntur magni dolores eos qui ratione voluptatem sequi
								nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor
								sit amet, consectetur, adipisci velit.</p>
							<div class="row">
								<div class="col-lg-5 col-xl-4">
									<div class="shop-single-list">
										<h5 class="title">Features</h5>
										<ul>
											<li>Modern Art Deco Chaise Lounge</li>
											<li>Unique cylindrical design copper finish</li>
											<li>Covered in grey velvet fabric</li>
											<li>Modern Bookcase in Copper Colored Finish</li>
											<li>Use of Modern Materials</li>
											<li>Mirrored compartments and upgraded interior</li>
										</ul>
									</div>
								</div>
								<div class="col-lg-6 col-xl-5">
									<div class="shop-single-list">
										<h5 class="title">Specifications</h5>
										<ul>
											<li><span>Dimensions:</span> 4ft W x 7ft h</li>
											<li><span>Model Year:</span> 2024</li>
											<li><span>Available Sizes:</span> 8.5, 9.0, 9.5, 10.0</li>
											<li><span>Manufacturer:</span> Reebok Inc.</li>
											<li><span>Available Colors:</span>
												White/Red/Blue,Black/Orange/Green</li>
											<li><span>Made In:</span> USA</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade" id="tab2" role="tabpanel"
						aria-labelledby="nav-tab2">
						<div class="shop-single-additional">
							<p>There are many variations of passages of Lorem Ipsum
								available, but the majority have suffered alteration in some
								form, by injected humour, or randomised words which don't look
								even slightly believable. If you are going to use a passage of
								Lorem Ipsum, you need to be sure there isn't anything
								embarrassing hidden in the middle of text. All the Lorem Ipsum
								generators on the Internet tend to repeat predefined chunks as
								necessary, making this the first true generator on the Internet.
							</p>
							<p>Sed ut perspiciatis unde omnis iste natus error sit
								voluptatem accusantium doloremque laudantium, totam rem aperiam,
								eaque ipsa quae ab illo inventore veritatis et quasi architecto
								beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem
								quia voluptas sit aspernatur aut odit aut fugit, sed quia
								consequuntur magni dolores eos qui ratione voluptatem sequi
								nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor
								sit amet, consectetur, adipisci velit.</p>
							<div class="shop-single-list">
								<h5 class="title">Shipping Options:</h5>
								<ul>
									<li><span>Standard:</span> 6-7 Days, Shipping Cost - Free</li>
									<li><span>Express:</span> 1-2 Days, Shipping Cost - $20</li>
									<li><span>Courier:</span> 2-3 Days, Shipping Cost - $30</li>
									<li><span>Fastgo:</span> 1-3 Days, Shipping Cost - $15</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="tab-pane fade" id="tab3" role="tabpanel"
						aria-labelledby="nav-tab3">
						<div class="shop-single-review">
							<div class="blog-comments">
								<h5>Reviews (05)</h5>
								<div class="blog-comments-wrap">
									<div class="blog-comments-item mt-0">
										<img src="/FurSPS_Nhom5/assets/img/blog/com-1.jpg" alt="thumb">
										<div class="blog-comments-content">
											<h5>Sinkler Denola</h5>
											<span><i class="far fa-clock"></i> August 20, 2024</span>
											<p>Lorem Ipsum is simply dummy text of the printing and
												typesetting industry. Lorem Ipsum has been the industry's
												standard dummy text ever since the when an unknown printer
												took a galley of type and scrambled it to make a type
												specimen book. It has survived not only five centuries but
												also the leap electronic typesetting, remaining essentially
												unchanged. It was popularised in the with the release of
												Letraset sheets containing Lorem Ipsum passages, and more
												recently with desktop publishing software like Aldus
												PageMaker including versions of Lorem Ipsum.</p>
											<a href="#"><i class="far fa-reply"></i> Reply</a>
											<div class="review-rating">
												<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
													class="fas fa-star"></i> <i class="fas fa-star"></i> <i
													class="fas fa-star"></i>
											</div>
										</div>
									</div>
									<div class="blog-comments-item ms-md-5">
										<img src="/FurSPS_Nhom5/assets/img/blog/com-2.jpg" alt="thumb">
										<div class="blog-comments-content">
											<h5>Daniel Wellman</h5>
											<span><i class="far fa-clock"></i> August 20, 2024</span>
											<p>Lorem Ipsum is simply dummy text of the printing and
												typesetting industry. Lorem Ipsum has been the industry's
												standard dummy text ever since the when an unknown printer
												took a galley of type and scrambled it to make a type
												specimen book. It has survived not only five centuries but
												also the leap electronic typesetting, remaining essentially
												unchanged. It was popularised in the with the release of
												Letraset sheets containing Lorem Ipsum passages, and more
												recently with desktop publishing software like Aldus
												PageMaker including versions of Lorem Ipsum.</p>
											<a href="#"><i class="far fa-reply"></i> Reply</a>
											<div class="review-rating">
												<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
													class="fas fa-star"></i> <i class="fas fa-star"></i> <i
													class="far fa-star"></i>
											</div>
										</div>
									</div>
									<div class="blog-comments-item">
										<img src="/FurSPS_Nhom5/assets/img/blog/com-3.jpg" alt="thumb">
										<div class="blog-comments-content">
											<h5>Kenneth Evans</h5>
											<span><i class="far fa-clock"></i> August 20, 2024</span>
											<p>Lorem Ipsum is simply dummy text of the printing and
												typesetting industry. Lorem Ipsum has been the industry's
												standard dummy text ever since the when an unknown printer
												took a galley of type and scrambled it to make a type
												specimen book. It has survived not only five centuries but
												also the leap electronic typesetting, remaining essentially
												unchanged. It was popularised in the with the release of
												Letraset sheets containing Lorem Ipsum passages, and more
												recently with desktop publishing software like Aldus
												PageMaker including versions of Lorem Ipsum.</p>
											<a href="#"><i class="far fa-reply"></i> Reply</a>
											<div class="review-rating">
												<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
													class="fas fa-star"></i> <i class="fas fa-star-half-alt"></i>
												<i class="far fa-star"></i>
											</div>
										</div>
									</div>
								</div>
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
								<h2 class="site-title">Related Items</h2>
								<a href="#">View More <i class="fas fa-arrow-right"></i></a>
							</div>
						</div>
					</div>
					<div class="row g-4 item-2">
						<div class="col-md-6 col-lg-3">
							<div class="product-item">
								<div class="product-img">
									<span class="type new">New</span> <a href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/07.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-bs-placement="right"
												data-tooltip="tooltip" title="Quick View"><i
												class="far fa-eye"></i></a> <a href="#"
												data-bs-placement="right" data-tooltip="tooltip"
												title="Add To Wishlist"><i class="far fa-heart"></i></a> <a
												href="#" data-bs-placement="right" data-tooltip="tooltip"
												title="Add To Compare"><i class="far fa-arrows-repeat"></i></a>
										</div>
									</div>
								</div>
								<div class="product-content">
									<h3 class="product-title">
										<a href="shop-single.html">Simple Denim Chair</a>
									</h3>
									<div class="product-rate">
										<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
											class="fas fa-star"></i> <i class="fas fa-star"></i> <i
											class="far fa-star"></i>
									</div>
									<div class="product-bottom">
										<div class="product-price">
											<span>$100.00</span>
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
						<div class="col-md-6 col-lg-3">
							<div class="product-item">
								<div class="product-img">
									<span class="type hot">Hot</span> <a href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/08.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-bs-placement="right"
												data-tooltip="tooltip" title="Quick View"><i
												class="far fa-eye"></i></a> <a href="#"
												data-bs-placement="right" data-tooltip="tooltip"
												title="Add To Wishlist"><i class="far fa-heart"></i></a> <a
												href="#" data-bs-placement="right" data-tooltip="tooltip"
												title="Add To Compare"><i class="far fa-arrows-repeat"></i></a>
										</div>
									</div>
								</div>
								<div class="product-content">
									<h3 class="product-title">
										<a href="shop-single.html">Simple Denim Chair</a>
									</h3>
									<div class="product-rate">
										<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
											class="fas fa-star"></i> <i class="fas fa-star"></i> <i
											class="far fa-star"></i>
									</div>
									<div class="product-bottom">
										<div class="product-price">
											<span>$100.00</span>
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
						<div class="col-md-6 col-lg-3">
							<div class="product-item">
								<div class="product-img">
									<span class="type oos">Out Of Stock</span> <a
										href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/12.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-bs-placement="right"
												data-tooltip="tooltip" title="Quick View"><i
												class="far fa-eye"></i></a> <a href="#"
												data-bs-placement="right" data-tooltip="tooltip"
												title="Add To Wishlist"><i class="far fa-heart"></i></a> <a
												href="#" data-bs-placement="right" data-tooltip="tooltip"
												title="Add To Compare"><i class="far fa-arrows-repeat"></i></a>
										</div>
									</div>
								</div>
								<div class="product-content">
									<h3 class="product-title">
										<a href="shop-single.html">Simple Denim Chair</a>
									</h3>
									<div class="product-rate">
										<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
											class="fas fa-star"></i> <i class="fas fa-star"></i> <i
											class="far fa-star"></i>
									</div>
									<div class="product-bottom">
										<div class="product-price">
											<span>$100.00</span>
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
						<div class="col-md-6 col-lg-3">
							<div class="product-item">
								<div class="product-img">
									<span class="type discount">10% Off</span> <a
										href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/14.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-bs-placement="right"
												data-tooltip="tooltip" title="Quick View"><i
												class="far fa-eye"></i></a> <a href="#"
												data-bs-placement="right" data-tooltip="tooltip"
												title="Add To Wishlist"><i class="far fa-heart"></i></a> <a
												href="#" data-bs-placement="right" data-tooltip="tooltip"
												title="Add To Compare"><i class="far fa-arrows-repeat"></i></a>
										</div>
									</div>
								</div>
								<div class="product-content">
									<h3 class="product-title">
										<a href="shop-single.html">Simple Denim Chair</a>
									</h3>
									<div class="product-rate">
										<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
											class="fas fa-star"></i> <i class="fas fa-star"></i> <i
											class="far fa-star"></i>
									</div>
									<div class="product-bottom">
										<div class="product-price">
											<del>$120.00</del>
											<span>$100.00</span>
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
					</div>
				</div>
			</div>
			<!-- related item end -->
		</div>
	</div>
	<!-- shop single end -->

</main>



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
									class="fas fa-star"></i> <i class="fas fa-star-half-alt"></i> <i
									class="far fa-star"></i> <span class="rating-count"> (4
									Customer Reviews)</span>
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
								<span>Share:</span> <a href="#"><i class="fab fa-facebook-f"></i></a>
								<a href="#"><i class="fab fa-x-twitter"></i></a> <a href="#"><i
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
</html>