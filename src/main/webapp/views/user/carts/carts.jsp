<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>


<main class="main">

	<!-- breadcrumb -->
	<div class="site-breadcrumb">
		<div class="site-breadcrumb-bg"
			style="background: url(/FurSPS_Nhom5/assets/img/breadcrumb/01.jpg)"></div>
		<div class="container">
			<div class="site-breadcrumb-wrap">
				<h4 class="breadcrumb-title">Shop Cart</h4>
				<ul class="breadcrumb-menu">
					<li><a href="index.html"><i class="far fa-home"></i> Home</a></li>
					<li class="active">Shop Cart</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- breadcrumb end -->


	<!-- shop cart -->
	<div class="shop-cart py-90">
		<div class="container">
			<div class="shop-cart-wrap">
				<div class="row">
					<div class="col-lg-8">
						<div class="cart-table">
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr>
											<th>Ảnh</th>
											<th>Tên hàng</th>
											<th>Giá bán</th>
											<th>Số lượng</th>
											<th>Thành tiền</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${carts}">
											<tr>
												<td>
													<div class="shop-cart-img">
														<a href="<c:url value='/user/products?id=${item.productID}' />"><img src="${item.image}" alt=""></a>
													</div>
												</td>
												<td>
													<div class="shop-cart-content">
														<h5 class="shop-cart-name">
															<a href="<c:url value='/user/products?id=${item.productID}' />">${item.productName}</a>
														</h5>
														<div class="shop-cart-info">
															<p>
																<span>Size:</span>${item.size}
															</p>
															<p>
																<span>Color:</span>${item.color}
															</p>
														</div>
													</div>
												</td>
												<td>
													<div class="shop-cart-price">
														<span> <fmt:formatNumber
																value="${item.promotionPrice}" currencyCode="VND"
																pattern="#,##0 VND" var="formattedPrice" />
															${formattedPrice}
														</span>
													</div>
												</td>
												<td>
													<div class="shop-cart-qty">
														<a
															href="<c:url value='/userUpdateCart?customerID=${item.customerID}&itemID=${item.itemID}&quantity=${item.quantity - 1} '/>"
															class="minus-btn"> <i class="fal fa-minus"></i>
														</a> <input class="quantity" type="text"
															value="${item.quantity}"> <a
															href="<c:url value='/userUpdateCart?customerID=${item.customerID}&itemID=${item.itemID}&quantity=${item.quantity + 1} '/>"
															class="plus-btn"> <i class="fal fa-plus"></i>
														</a>
													</div>
												</td>
												<td>
													<div class="shop-cart-subtotal">
														<span> <fmt:formatNumber value="${item.totalPrice}"
																currencyCode="VND" pattern="#,##0 VND"
																var="formattedPrice" /> ${formattedPrice}
														</span>
													</div>
												</td>
												<td><a
													href="<c:url value='/userDeleteCart?customerID=${item.customerID}&itemID=${item.itemID}' />"
													class="shop-cart-remove"><i class="far fa-times"></i></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div class="shop-cart-footer">
							<div class="row">
								<div class="col-md-6 col-lg-3">
									<div class="shop-cart-btn text-md-end">
										<a href="<c:url value='/userDeleteCarts' />" class="theme-btn"> <span></span> Xóa tất cả hàng
										</a>
									</div>
								</div>

								<div class="col-md-6 col-lg-4">
									<div class="shop-cart-btn text-md-end">
										<a href="<c:url value='/user/products' />" class="theme-btn"> <span
											class="fas fa-arrow-left"></span> Mua hàng khác
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-4">
						<div class="shop-cart-summary">
							<h5>Hóa đơn tổng</h5>
							<ul>
								<li><strong>Tổng tiền:</strong><span
									class="total-amount"> <fmt:formatNumber type="currency"
											value="${subTotal}" currencyCode="VND" pattern="#,##0 VND"
											var="formattedPrice" /> ${formattedPrice}
								</span></li>
								<li><strong>Giảm giá:</strong> <span>Chưa có</span></li>
								<li><strong>Phí vận chuyển:</strong> <span>Chưa có</span></li>
								<li class="shop-cart-total"><strong>Tổng tiền:</strong> <span
									class="total-amount"> <fmt:formatNumber type="currency"
											value="${subTotal}" currencyCode="VND" pattern="#,##0 VND"
											var="formattedPrice" /> ${formattedPrice}
								</span></li>
							</ul>
							<div class="text-end mt-40">
								<a href="<c:url value='/userCheckout' />" class="theme-btn">Thanh toán<i
									class="fas fa-arrow-right"></i></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- shop cart end -->
</main>
