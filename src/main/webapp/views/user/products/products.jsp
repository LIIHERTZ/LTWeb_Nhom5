<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<!-- breadcrumb -->
<div class="site-breadcrumb">
	<div class="site-breadcrumb-bg"
		style="background: url(/FurSPS_Nhom5/assets/img/breadcrumb/01.jpg)"></div>
	<div class="container">
		<div class="site-breadcrumb-wrap">
			<h4 class="breadcrumb-title">Sản phẩm</h4>
			<ul class="breadcrumb-menu">
				<li><a href="/FurSPS_Nhom5/user/home"><i
						class="far fa-home"></i> Home</a></li>
				<li class="active">Sản phẩm</li>
			</ul>
		</div>
	</div>
</div>
<!-- breadcrumb end -->


<!-- shop-area -->
<div class="shop-area bg py-90">
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<div class="shop-sidebar">
					<div class="shop-widget">
						<div class="shop-search-form">
							<h4 class="shop-widget-title">Search</h4>
							<form action="${pageContext.request.contextPath}/user/search"
								method="get">
								<div class="form-group">
									<datalist id="listHistory">
										<c:forEach var="i" items="${history}">
											<option value="${i.history}">
										</c:forEach>
									</datalist>
									<input type="text" class="form-control"
										placeholder="${keyword == null ? 'Tìm kiếm' : keyword}"
										list="listHistory" name="keyword">
									<button type="search">
										<i class="far fa-search"></i>
									</button>
								</div>
							</form>
						</div>
					</div>
					<div class="shop-widget">
						<h4 class="shop-widget-title">Category</h4>
						<ul class="shop-category-list">
							<li class="${rootcategory.categoryID == null ? 'active' : ''}">
								<a href="/FurSPS_Nhom5/user/products" data-filter="*">Tất cả
									sản phẩm</a>
							</li>
							<c:forEach items="${rootCategories}" var="item">
								<a href="/FurSPS_Nhom5/user/products?cateId=${item.categoryID}"
									class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 ${rootcategory.categoryID == item.categoryID ? 'how-active1' : ''}"
									data-filter="*">${item.categoryName}</a>
							</c:forEach>
						</ul>
					</div>

					<div class="shop-widget">
						<h4 class="shop-widget-title">Price Range</h4>
						<div class="price-range-box">
							<div class="price-range-input">
								<input type="text" id="price-amount" readonly>
							</div>
							<div class="price-range"></div>
						</div>
						<button type="submit" class="theme-btn" id="filter-button">
							<span class="far fa-search"></span> Lọc
						</button>
					</div>
					<script>
					    // Hàm định dạng số (thêm dấu ',' và hiển thị đơn vị)
					    function formatNumber(num) {
					        if (num >= 1000000000) {
					            return (num / 1000000000).toFixed(1) + " tỷ"; // Hiển thị tỷ nếu giá trị >= 1 tỷ
					        } else if (num >= 1000000) {
					            return (num / 1000000).toFixed(1) + " triệu"; // Hiển thị triệu
					        } else if (num >= 1000) {
					            return (num / 1000).toFixed(0) + " nghìn"; // Hiển thị nghìn
					        }
					        return num; // Hiển thị số đơn thuần
					    }
					
					    $(function() {
					        // Cấu hình slider
					        $(".price-range").slider({
					            range: true,
					            min: 0,
					            max: 20000000, // Giá trị tối đa là 20 triệu
					            values: [1000000, 5000000], // Giá trị mặc định
					            step: 100000, // Bước nhảy
					            slide: function(event, ui) {
					                // Hiển thị giá trị trong input với định dạng
					                $("#price-amount").val(
					                    formatNumber(ui.values[0]) + " - " + formatNumber(ui.values[1])
					                );
					            }
					        });
					
					        // Hiển thị giá trị mặc định trong input khi trang được tải
					        $("#price-amount").val(
					            formatNumber($(".price-range").slider("values", 0)) +
					            " - " +
					            formatNumber($(".price-range").slider("values", 1))
					        );
					
					        // Sự kiện click vào nút "Lọc"
					        $("#filter-button").click(function() {
					            // Lấy giá trị hiện tại của slider
					            let minPrice = $(".price-range").slider("values", 0);
					            let maxPrice = $(".price-range").slider("values", 1);
					
					            // Thực hiện hành động với giá trị (ví dụ: in ra console)
					            console.log("Giá từ:", minPrice);
					            console.log("Giá đến:", maxPrice);
					
					            // Hoặc nếu bạn muốn gửi các giá trị này đến server thông qua AJAX
					            $.ajax({
					                url: "your-server-endpoint-url", // Thay bằng URL server của bạn
					                type: "GET",
					                data: {
					                    minPrice: minPrice,
					                    maxPrice: maxPrice
					                },
					                success: function(response) {
					                    // Xử lý kết quả trả về từ server
					                    console.log(response);
					                },
					                error: function(xhr, status, error) {
					                    console.error("Có lỗi xảy ra:", error);
					                }
					            });
					        });
					    });
					</script>


					<div class="shop-widget">
						<h4 class="shop-widget-title">Ratings</h4>
						<ul class="shop-checkbox-list rating">
							<li>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" id="rate1">
									<label class="form-check-label" for="rate1"> <i
										class="fas fa-star"></i> <i class="fas fa-star"></i> <i
										class="fas fa-star"></i> <i class="fas fa-star"></i> <i
										class="fas fa-star"></i>
									</label>
								</div>
							</li>
							<li>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" id="rate2">
									<label class="form-check-label" for="rate2"> <i
										class="fas fa-star"></i> <i class="fas fa-star"></i> <i
										class="fas fa-star"></i> <i class="fas fa-star"></i> <i
										class="fal fa-star"></i>
									</label>
								</div>
							</li>
							<li>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" id="rate3">
									<label class="form-check-label" for="rate3"> <i
										class="fas fa-star"></i> <i class="fas fa-star"></i> <i
										class="fas fa-star"></i> <i class="fal fa-star"></i> <i
										class="fal fa-star"></i>
									</label>
								</div>
							</li>
							<li>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" id="rate4">
									<label class="form-check-label" for="rate4"> <i
										class="fas fa-star"></i> <i class="fas fa-star"></i> <i
										class="fal fa-star"></i> <i class="fal fa-star"></i> <i
										class="fal fa-star"></i>
									</label>
								</div>
							</li>
							<li>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" id="rate5">
									<label class="form-check-label" for="rate5"> <i
										class="fas fa-star"></i> <i class="fal fa-star"></i> <i
										class="fal fa-star"></i> <i class="fal fa-star"></i> <i
										class="fal fa-star"></i>
									</label>
								</div>
							</li>
						</ul>
						<button type="submit" class="theme-btn">
							<span class="far fa-search"></span> Lọc
						</button>
					</div>
				</div>
			</div>
			<div class="col-lg-9">
				<div class="col-md-12">
					<div class="shop-sort">
						<div class="shop-sort-box">
							<div class="shop-sorty-label">Sort By:</div>
							<select id="sort-options" class="select">
								<option value="low-to-high">Price - Low To High</option>
								<option value="high-to-low">Price - High To Low</option>
							</select>
						</div>

						<script>
					    // Đối tượng params
					    let params = {
					        sort: null // Khởi tạo giá trị sắp xếp ban đầu
					    };
					
					    // Hàm run (ví dụ, có thể là hàm đã định nghĩa để thực hiện tìm kiếm hoặc lọc sản phẩm)
					    function run(param) {
					        console.log("Đang chạy với tham số:", param);
					        console.log("Các params hiện tại:", params);
					        // Thực hiện hành động khác ở đây, ví dụ: gửi request đến server
					    }
					
					    // Lắng nghe sự kiện change trên select box
					    document.getElementById("sort-options").addEventListener("change", function() {
					        let selectedValue = this.value;
					
					        // Cập nhật params dựa trên giá trị đã chọn
					        if (selectedValue === "low-to-high") {
					            params.sort = "low-to-high"; // Sắp xếp từ thấp đến cao
					        } else if (selectedValue === "high-to-low") {
					            params.sort = "high-to-low"; // Sắp xếp từ cao đến thấp
					        }
					
					        params.page = 1; // Reset trang về 1 khi có thay đổi sort
					        run(0); // Gọi hàm run để thực hiện việc sắp xếp
					    });
					
					    // Hàm để cập nhật giao diện (nếu cần)
					    function updateSortDisplay() {
					        // Bạn có thể thêm logic để cập nhật giao diện dựa trên params.sort nếu cần
					    }
					</script>
						<div class="shop-sort-gl">
							<a href="shop-grid.html" class="shop-sort-grid active"><i
								class="far fa-grid-round-2"></i></a> <a href="shop-list.html"
								class="shop-sort-list"><i class="far fa-list-ul"></i></a>
						</div>
					</div>
				</div>
				<div class="shop-item-wrap item-3">
					<div class="row g-4">
						<div class="col-md-6 col-lg-4">
							<div class="product-item">
								<div class="product-img">
									<span class="type">Trending</span> <a href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/01.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-tooltip="tooltip"
												title="Quick View"><i class="far fa-eye"></i></a> <a
												href="#" data-tooltip="tooltip" title="Add To Wishlist"><i
												class="far fa-heart"></i></a> <a href="#" data-tooltip="tooltip"
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
						<div class="col-md-6 col-lg-4">
							<div class="product-item">
								<div class="product-img">
									<span class="type hot">Hot</span> <a href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/02.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-tooltip="tooltip"
												title="Quick View"><i class="far fa-eye"></i></a> <a
												href="#" data-tooltip="tooltip" title="Add To Wishlist"><i
												class="far fa-heart"></i></a> <a href="#" data-tooltip="tooltip"
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
						<div class="col-md-6 col-lg-4">
							<div class="product-item">
								<div class="product-img">
									<span class="type oos">Out Of Stock</span> <a
										href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/03.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-tooltip="tooltip"
												title="Quick View"><i class="far fa-eye"></i></a> <a
												href="#" data-tooltip="tooltip" title="Add To Wishlist"><i
												class="far fa-heart"></i></a> <a href="#" data-tooltip="tooltip"
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
						<div class="col-md-6 col-lg-4">
							<div class="product-item">
								<div class="product-img">
									<a href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/04.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-tooltip="tooltip"
												title="Quick View"><i class="far fa-eye"></i></a> <a
												href="#" data-tooltip="tooltip" title="Add To Wishlist"><i
												class="far fa-heart"></i></a> <a href="#" data-tooltip="tooltip"
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
						<div class="col-md-6 col-lg-4">
							<div class="product-item">
								<div class="product-img">
									<span class="type discount">10% Off</span> <a
										href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/05.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-tooltip="tooltip"
												title="Quick View"><i class="far fa-eye"></i></a> <a
												href="#" data-tooltip="tooltip" title="Add To Wishlist"><i
												class="far fa-heart"></i></a> <a href="#" data-tooltip="tooltip"
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
											<del>120.00</del>
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
						<div class="col-md-6 col-lg-4">
							<div class="product-item">
								<div class="product-img">
									<a href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/06.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-tooltip="tooltip"
												title="Quick View"><i class="far fa-eye"></i></a> <a
												href="#" data-tooltip="tooltip" title="Add To Wishlist"><i
												class="far fa-heart"></i></a> <a href="#" data-tooltip="tooltip"
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
						<div class="col-md-6 col-lg-4">
							<div class="product-item">
								<div class="product-img">
									<span class="type new">New</span> <a href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/07.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-tooltip="tooltip"
												title="Quick View"><i class="far fa-eye"></i></a> <a
												href="#" data-tooltip="tooltip" title="Add To Wishlist"><i
												class="far fa-heart"></i></a> <a href="#" data-tooltip="tooltip"
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
						<div class="col-md-6 col-lg-4">
							<div class="product-item">
								<div class="product-img">
									<a href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/08.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-tooltip="tooltip"
												title="Quick View"><i class="far fa-eye"></i></a> <a
												href="#" data-tooltip="tooltip" title="Add To Wishlist"><i
												class="far fa-heart"></i></a> <a href="#" data-tooltip="tooltip"
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
						<div class="col-md-6 col-lg-4">
							<div class="product-item">
								<div class="product-img">
									<a href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/09.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-tooltip="tooltip"
												title="Quick View"><i class="far fa-eye"></i></a> <a
												href="#" data-tooltip="tooltip" title="Add To Wishlist"><i
												class="far fa-heart"></i></a> <a href="#" data-tooltip="tooltip"
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
						<div class="col-md-6 col-lg-4">
							<div class="product-item">
								<div class="product-img">
									<a href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/10.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-tooltip="tooltip"
												title="Quick View"><i class="far fa-eye"></i></a> <a
												href="#" data-tooltip="tooltip" title="Add To Wishlist"><i
												class="far fa-heart"></i></a> <a href="#" data-tooltip="tooltip"
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
						<div class="col-md-6 col-lg-4">
							<div class="product-item">
								<div class="product-img">
									<a href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/11.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-tooltip="tooltip"
												title="Quick View"><i class="far fa-eye"></i></a> <a
												href="#" data-tooltip="tooltip" title="Add To Wishlist"><i
												class="far fa-heart"></i></a> <a href="#" data-tooltip="tooltip"
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
						<div class="col-md-6 col-lg-4">
							<div class="product-item">
								<div class="product-img">
									<span class="type hot">Hot</span> <a href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/12.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-tooltip="tooltip"
												title="Quick View"><i class="far fa-eye"></i></a> <a
												href="#" data-tooltip="tooltip" title="Add To Wishlist"><i
												class="far fa-heart"></i></a> <a href="#" data-tooltip="tooltip"
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
						<div class="col-md-6 col-lg-4">
							<div class="product-item">
								<div class="product-img">
									<a href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/13.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-tooltip="tooltip"
												title="Quick View"><i class="far fa-eye"></i></a> <a
												href="#" data-tooltip="tooltip" title="Add To Wishlist"><i
												class="far fa-heart"></i></a> <a href="#" data-tooltip="tooltip"
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
						<div class="col-md-6 col-lg-4">
							<div class="product-item">
								<div class="product-img">
									<a href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/14.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-tooltip="tooltip"
												title="Quick View"><i class="far fa-eye"></i></a> <a
												href="#" data-tooltip="tooltip" title="Add To Wishlist"><i
												class="far fa-heart"></i></a> <a href="#" data-tooltip="tooltip"
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
						<div class="col-md-6 col-lg-4">
							<div class="product-item">
								<div class="product-img">
									<a href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/15.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-tooltip="tooltip"
												title="Quick View"><i class="far fa-eye"></i></a> <a
												href="#" data-tooltip="tooltip" title="Add To Wishlist"><i
												class="far fa-heart"></i></a> <a href="#" data-tooltip="tooltip"
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
						<div class="col-md-6 col-lg-4">
							<div class="product-item">
								<div class="product-img">
									<a href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/16.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-tooltip="tooltip"
												title="Quick View"><i class="far fa-eye"></i></a> <a
												href="#" data-tooltip="tooltip" title="Add To Wishlist"><i
												class="far fa-heart"></i></a> <a href="#" data-tooltip="tooltip"
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
						<div class="col-md-6 col-lg-4">
							<div class="product-item">
								<div class="product-img">
									<a href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/17.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-tooltip="tooltip"
												title="Quick View"><i class="far fa-eye"></i></a> <a
												href="#" data-tooltip="tooltip" title="Add To Wishlist"><i
												class="far fa-heart"></i></a> <a href="#" data-tooltip="tooltip"
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
						<div class="col-md-6 col-lg-4">
							<div class="product-item">
								<div class="product-img">
									<a href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/18.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-tooltip="tooltip"
												title="Quick View"><i class="far fa-eye"></i></a> <a
												href="#" data-tooltip="tooltip" title="Add To Wishlist"><i
												class="far fa-heart"></i></a> <a href="#" data-tooltip="tooltip"
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
						<div class="col-md-6 col-lg-4">
							<div class="product-item">
								<div class="product-img">
									<span class="type new">New</span> <a href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/19.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-tooltip="tooltip"
												title="Quick View"><i class="far fa-eye"></i></a> <a
												href="#" data-tooltip="tooltip" title="Add To Wishlist"><i
												class="far fa-heart"></i></a> <a href="#" data-tooltip="tooltip"
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
						<div class="col-md-6 col-lg-4">
							<div class="product-item">
								<div class="product-img">
									<a href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/20.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-tooltip="tooltip"
												title="Quick View"><i class="far fa-eye"></i></a> <a
												href="#" data-tooltip="tooltip" title="Add To Wishlist"><i
												class="far fa-heart"></i></a> <a href="#" data-tooltip="tooltip"
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
						<div class="col-md-6 col-lg-4">
							<div class="product-item">
								<div class="product-img">
									<a href="shop-single.html"><img
										src="/FurSPS_Nhom5/assets/img/product/21.png" alt=""></a>
									<div class="product-action-wrap">
										<div class="product-action">
											<a href="#" data-bs-toggle="modal"
												data-bs-target="#quickview" data-tooltip="tooltip"
												title="Quick View"><i class="far fa-eye"></i></a> <a
												href="#" data-tooltip="tooltip" title="Add To Wishlist"><i
												class="far fa-heart"></i></a> <a href="#" data-tooltip="tooltip"
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
					</div>
				</div>
				<!-- pagination -->
				<div class="pagination-area mt-50">
					<div aria-label="Page navigation example">
						<ul class="pagination">
							<li class="page-item"><a class="page-link" href="#"
								aria-label="Previous"> <span aria-hidden="true"><i
										class="far fa-arrow-left"></i></span>
							</a></li>
							<li class="page-item active"><a class="page-link" href="#">1</a></li>
							<li class="page-item"><a class="page-link" href="#">2</a></li>
							<li class="page-item"><span class="page-link">...</span></li>
							<li class="page-item"><a class="page-link" href="#">10</a></li>
							<li class="page-item"><a class="page-link" href="#"
								aria-label="Next"> <span aria-hidden="true"><i
										class="far fa-arrow-right"></i></span>
							</a></li>
						</ul>
					</div>
				</div>
				<!-- pagination end -->
			</div>
		</div>
	</div>
</div>
<!-- shop-area end -->

</html>