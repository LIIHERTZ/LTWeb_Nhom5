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
				<h4 class="breadcrumb-title">Shop Checkout</h4>
				<ul class="breadcrumb-menu">
					<li><a href="index.html"><i class="far fa-home"></i> Home</a></li>
					<li class="active">Shop Checkout</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- breadcrumb end -->

	<form action="${pageContext.request.contextPath}/userCheckout"
		method="post">
		<input type="hidden" id="discountInput" name="discount" value="0">
		<input type="hidden" id="TotalMoney" name="TotalMoney" value="0">
		<!-- shop checkout -->
		<div class="shop-checkout py-90">
			<div class="container">
				<div class="shop-checkout-wrap">
					<div class="row">
						<div class="col-lg-8">
							<div class="shop-checkout-step">
								<div class="accordion" id="shopCheckout">
									<div class="accordion-item">
										<h2 class="accordion-header">
											<button class="accordion-button collapsed" type="button"
												data-bs-toggle="collapse" data-bs-target="#checkoutStep2"
												aria-expanded="true" aria-controls="checkoutStep2">
												Chọn địa chỉ nhận hàng và phí vận chuyển</button>
										</h2>
										<div id="checkoutStep2"
											class="accordion-collapse collapse show"
											data-bs-parent="#shopCheckout">
											<div class="accordion-body">
												<div class="shop-checkout-form">
													<div class="row">
														<div class="col-md-12">
															<div class="form-group">
																<label>Chọn địa chỉ nhận hàng</label> <select
																	class="select" id="addressSelect"
																	onchange="updateAddressFields()" required>
																	<option value="" disabled selected>Vui lòng
																		chọn địa chỉ</option>
																	<c:forEach var="item" items="${listAddress}">
																		<option value="${item}" data-name="${item.name}"
																			data-phone="${item.phone}" data-city="${item.city}"
																			data-detail="${item.detail}">
																			${item.detail}, ${item.city}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
														<div class="col-md-6">
															<div class="form-group">
																<label>Tên người nhận</label> <input type="text"
																	class="form-control" placeholder="Tên người nhận"
																	id="name" name="name" value="${name}" readonly>
															</div>
														</div>
														<div class="col-md-6">
															<div class="form-group">
																<label>Số điện thoại</label> <input type="text"
																	class="form-control" placeholder="Số điện thoại"
																	id="phone" name="phone" value="${phone}" readonly>
															</div>
														</div>
														<div class="col-md-12">
															<div class="form-group">
																<label>Tỉnh thành</label> <input type="text"
																	class="form-control" placeholder="Khu vực" name="city"
																	id="city" value="${city}" readonly>
															</div>
														</div>
														<div class="col-md-12">
															<div class="form-group">
																<label>Địa chỉ</label> <input type="text"
																	class="form-control" placeholder="Địa chỉ cụ thể"
																	id="detail" name="detail" value="${detail}" readonly>
															</div>
														</div>
														<div class="col-md-12">
															<div class="form-group">
																<label>Ghi chú</label> <input type="text"
																	class="form-control" placeholder="Lời nhắn..."
																	id="note" name="note" value="${note}" >
															</div>
														</div>

														<div class="col-lg-12">
															<div class="shop-shipping-method">
																<h6>Chọn phương thức vận chuyển</h6>
																<div class="row">
																	<div class="col-md-6 col-lg-4 col-xxl-3">
																		<div class="form-check">
																			<input class="form-check-input" type="radio" checked
																				name="method" id="ssm-1" value="50000"> <label
																				class="form-check-label" for="ssm-1"> Tiết
																				kiệm<span>Phí - 50.000 VNĐ</span> <span>7-14
																					Ngày</span>
																			</label>
																		</div>
																	</div>
																	<div class="col-md-6 col-lg-4 col-xxl-3">
																		<div class="form-check">
																			<input class="form-check-input" type="radio"
																				name="method" id="ssm-4" value="100000"> <label
																				class="form-check-label" for="ssm-4"> Tiêu
																				chuẩn <span>Phí - 100.000 VNĐ</span> <span>4-5
																					Ngày</span>
																			</label>
																		</div>
																	</div>
																	<div class="col-md-6 col-lg-4 col-xxl-3">
																		<div class="form-check">
																			<input class="form-check-input" type="radio"
																				name="method" id="ssm-2" value="200000"> <label
																				class="form-check-label" for="ssm-2"> Nhanh
																				<span>Phí - 200.000 VNĐ</span> <span>2-3 Ngày</span>
																			</label>
																		</div>
																	</div>
																	<div class="col-md-6 col-lg-4 col-xxl-3">
																		<div class="form-check">
																			<input class="form-check-input" type="radio"
																				name="method" id="ssm-3" value="350000"> <label
																				class="form-check-label" for="ssm-3"> Giao
																				hỏa tốc <span>Phí - 350.000 VNĐ</span> <span>1-2
																					Ngày</span>
																			</label>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="accordion-item">
										<h2 class="accordion-header">
											<button class="accordion-button collapsed" type="button"
												data-bs-toggle="collapse" data-bs-target="#checkoutStep3"
												aria-expanded="true" aria-controls="checkoutStep3">
												Chọn phương thức thanh toán</button>
										</h2>
										<div id="checkoutStep3"
											class="accordion-collapse collapse show"
											data-bs-parent="#shopCheckout">
											<div class="accordion-body">
												<div class="shop-checkout-payment">
												<input type="hidden" id="paymentMethodInput" name="paymentMethod" value="1">
													<ul class="nav nav-pills mb-3" id="pills-tab"
														role="tablist">
														<li class="nav-item" role="presentation"><a
															class="nav-link active" id="pills-tab-1"
															data-bs-toggle="pill" data-bs-target="#pills-1"
															type="button" role="tab" aria-controls="pills-1"
															aria-selected="true" data-name="paymentMethod"
															data-value="1">
																<div class="checkout-card-img">
																	<img
																		src="/FurSPS_Nhom5/assets/img/payment/mastercard.svg"
																		alt=""> <img
																		src="/FurSPS_Nhom5/assets/img/payment/visa.svg" alt="">
																	<img src="/FurSPS_Nhom5/assets/img/payment/amex.svg"
																		alt=""> <img
																		src="/FurSPS_Nhom5/assets/img/payment/discover.svg"
																		alt="">
																</div> <span>Thanh toán bằng thẻ</span>
														</a></li>
														<li class="nav-item" role="presentation"><a
															class="nav-link" id="pills-tab-4" data-bs-toggle="pill"
															data-bs-target="#pills-4" type="button" role="tab"
															aria-controls="pills-4" aria-selected="false"
															data-name="paymentMethod" data-value="0">
																<div class="checkout-payment-img cod">
																	<img src="/FurSPS_Nhom5/assets/img/payment/cod-3.svg"
																		alt="">
																</div> <span>Thanh toán khi nhận hàng</span>
														</a></li>
													</ul>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4">
							<div class="shop-cart-summary mt-0">
								<h5>Hóa đơn tổng</h5>
								<ul>
									<li><strong>Tổng tiền:</strong><span class="total-amount">
											<fmt:formatNumber type="currency" value="${totalCost}"
												currencyCode="VND" pattern="#,##0 VNĐ" var="formattedPrice" />
											${formattedPrice}
									</span></li>
									<li><strong>Giảm giá:</strong> <span id="discountAmount">Chưa
											có</span></li>
									<li><strong>Phí vận chuyển:</strong> <span
										id="shippingCost">Chưa có</span></li>
									<li class="shop-cart-total"><strong>Tổng tiền:</strong> <span
										class="total-amount" id="finalTotal"> </span></li>
									<li><strong>Thanh toán:</strong> <span id="paymentMethod">Chưa
											có</span></li>
								</ul>
								<div class="d-flex justify-content-end mt-40">
									<a href="<c:url value='/userCarts'/>" class="theme-btn me-3">Back
										To Cart <i></i>
									</a> <button type="submit" class="theme-btn">Checkout Now <i></i></button>
								</div>
							</div>
							<br>
							<div class="col-md-12 col-lg-12">
								<div class="shop-cart-coupon">
									<div class="form-group">
										<label for="couponSelect" class="coupon-label ms-3">Áp
											dụng mã giảm giá</label> <select id="couponSelect"
											class="form-control" name="voucherId">
											<option value="" disabled selected>Chọn mã giảm giá</option>
											<c:forEach var="voucher" items="${listVoucher}">
												<option data-discount="${voucher.discount}"
													data-minimum-price="${voucher.minimumPrice}"
													value="${voucher.voucherID}">${voucher.description}-
													${voucher.discount}% - Tối da ${voucher.minimumPrice} VNĐ</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- shop checkout end -->
	<style>
.coupon-label {
	font-weight: bold; /* Làm chữ in đậm */
	font-size: 1.25rem; /* Tăng kích thước chữ */
	margin-left: 1rem; /* Tạo khoảng trắng đầu */
	color: red;
}

#couponSelect {
	width: 100%;
	max-width: none;
	white-space: nowrap;
	overflow: visible;
	padding: 0.5rem;
	border-radius: 19px; /* Bo tròn khung */
	font-size: 1rem;
	border: 1px solid #ccc; /* Đường viền nhẹ */
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Hiệu ứng bóng nhẹ */
}

/* Bo tròn viền của các option (áp dụng khi sử dụng thư viện tùy chỉnh, vì mặc định trình duyệt không hỗ trợ bo tròn option) */
#couponSelect option {
	border-radius: 8px;
	padding: 0.5rem;
	font-size: 1rem;
	white-space: normal;
}
</style>
	<script>
		function updateAddressFields() {
			var select = document.getElementById("addressSelect");
			var selectedOption = select.options[select.selectedIndex];
			var name = selectedOption.getAttribute("data-name");
			var phone = selectedOption.getAttribute("data-phone");
			var city = selectedOption.getAttribute("data-city");
			var detail = selectedOption.getAttribute("data-detail");
			document.getElementById("name").value = name;
			document.getElementById("phone").value = phone;
			document.getElementById("city").value = city;
			document.getElementById("detail").value = detail;
		}
		let shippingMethod;
		const totalCost = parseFloat(${totalCost});
		
		// Hàm định dạng tiền tệ
		function formatCurrency(amount) {
		    return amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".") + " VNĐ";
		}
		
		// Hàm tính tổng tiền sau khi áp dụng giảm giá và phí vận chuyển
		function calculateTotal(discountAmount, shippingCost) {
		    const finalTotal = totalCost - discountAmount + shippingCost;
		    document.getElementById('finalTotal').textContent = formatCurrency(finalTotal);
		    document.getElementById('TotalMoney').value = finalTotal;
		}
		// Lắng nghe sự kiện khi chọn phương thức vận chuyển
		document.querySelectorAll('.form-check-input').forEach(function (radio) {
		    radio.addEventListener('change', function () {
		        const shippingCost = parseFloat(this.value);
		        document.getElementById('shippingCost').textContent = formatCurrency(shippingCost);

		        // Lấy giá trị giảm giá hiện tại
		        const discountText = document.getElementById('discountAmount').textContent;
		        const discountAmount = discountText.includes('VNĐ') ? parseFloat(discountText.replace(/[^0-9]/g, '')) : 0;

		        // Tính toán tổng tiền
		        calculateTotal(discountAmount, shippingCost);
		    });
		});

		// Lắng nghe sự kiện khi chọn phương thức thanh toán
		document.querySelectorAll('#pills-tab .nav-link').forEach(function (link) {
		    link.addEventListener('click', function () {

		        const paymentMethod = this.getAttribute('data-value');

		        document.getElementById('paymentMethodInput').value = paymentMethod;

		        document.getElementById('paymentMethod').textContent = this.querySelector('span').textContent;
		    });
		});
		// Lắng nghe sự kiện thay đổi mã giảm giá
		document.getElementById('couponSelect').addEventListener('change', function () {
		    const selectedOption = this.options[this.selectedIndex];
		    const discountPercent = parseFloat(selectedOption.getAttribute('data-discount')); // Phần trăm giảm
		    const maxDiscount = parseFloat(selectedOption.getAttribute('data-minimum-price')); // Số tiền giảm tối đa

		    // Tính toán số tiền được giảm
		    let discountAmount = (totalCost * discountPercent) / 100;
		    if (discountAmount > maxDiscount) {
		        discountAmount = maxDiscount;
		    }

		    document.getElementById('discountAmount').textContent = '- ' + formatCurrency(discountAmount) ;
		 	// Cập nhật giá trị vào input ẩn
		    document.getElementById('discountInput').value = discountAmount;

		    // Lấy phí vận chuyển hiện tại
		    const shippingCostText = document.getElementById('shippingCost').textContent;
		    const shippingCost = shippingCostText.includes('VNĐ') ? parseFloat(shippingCostText.replace(/[^0-9]/g, '')) : 0;

		    // Tính toán tổng tiền
		    calculateTotal(discountAmount, shippingCost);
		});
		// Xác định giá trị mặc định khi trang được tải
		window.onload = function () {
		    // Lấy phí vận chuyển mặc định
		    const selectedRadio = document.querySelector('.form-check-input:checked');
		    let shippingCost = 0;

		    if (selectedRadio) {
		        shippingCost = parseFloat(selectedRadio.value);
		        document.getElementById('shippingCost').textContent = formatCurrency(shippingCost);
		    }

		    // Lấy phương thức thanh toán mặc định
		    const selectedPaymentLink = document.querySelector('#pills-tab .nav-link.active');
		    if (selectedPaymentLink) {
		        const defaultPaymentMethod = selectedPaymentLink.querySelector('span').textContent;
		        document.getElementById('paymentMethod').textContent = defaultPaymentMethod;
		    }

		    // Lấy giảm giá mặc định (nếu có)
		    const discountText = document.getElementById('discountAmount').textContent;
		    const discountAmount = discountText.includes('VNĐ') ? parseFloat(discountText.replace(/[^0-9]/g, '')) : 0;

		    // Tính toán tổng tiền ban đầu
		    calculateTotal(discountAmount, shippingCost);
		};

	</script>
</main>