<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<script src="/FurSPS_Nhom5/assets/js/jquery-3.7.1.min.js"></script>

<script src="/FurSPS_Nhom5/assets/js/jquery-ui.min.js"></script>

<!-- breadcrumb -->
<div class="site-breadcrumb">
	<div class="site-breadcrumb-bg"
		style="background: url(/FurSPS_Nhom5/assets/img/breadcrumb/01.jpg)"></div>
	<div class="container">
		<div class="site-breadcrumb-wrap">
			<h4 class="breadcrumb-title">Sản phẩm</h4>
			<ul class="breadcrumb-menu">
				<li><a href="/FurSPS_Nhom5/home"><i
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
						<div class="offcanvas-body">
							<ul class="shop-category-list">
								<!-- Liên kết "Tất cả sản phẩm" -->
								<li class="${rootcategory.categoryID == null ? 'active' : ''}">
									<a href="/FurSPS_Nhom5/products" data-filter="*">Tất
										cả sản phẩm</a>
								</li>

								<!-- Danh mục cha -->
								<c:forEach items="${levelCategories}" var="item">
									<li class="nav-item dropdown">
										<!-- Danh mục cha --> <a class="nav-link dropdown-toggle"
										href="<c:url value='/products?cateId=${item.categoryID}'/>"
										id="navbarDropdown${item.categoryID}" role="button"
										data-bs-toggle="dropdown" aria-expanded="false">
											${item.categoryName} </a> <!-- Nếu có danh mục con, hiển thị menu dropdown -->
										<c:if test="${not empty item.childrens}">
											<ul class="dropdown-menu fade-down"
												aria-labelledby="navbarDropdown${item.categoryID}">

												<!-- Mục "Tất cả" cho danh mục con -->
												<li><a class="dropdown-item"
													href="<c:url value='/products?cateId=${item.categoryID}'/>">
														Tất cả ${item.categoryName}</a></li>

												<!-- Duyệt qua các danh mục con -->
												<c:forEach items="${item.childrens}" var="subItem">
													<li><a class="dropdown-item"
														href="<c:url value='/products?cateId=${subItem.categoryID}'/>">
															${subItem.categoryName}</a></li>
												</c:forEach>
											</ul>
										</c:if>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<div class="shop-widget">
						<h4 class="shop-widget-title">Price Range</h4>
						<div class="price-range-box">
							<div class="price-range-input">
								<!-- Input để hiển thị giá trị chọn từ slider -->
								<input type="text" id="price-amount" readonly>
							</div>
							<div class="price-range"></div>
							<!-- Thanh trượt giá -->
						</div>
						<button type="submit" class="theme-btn" id="filter-button">
							<span class="far fa-search"></span> Lọc
						</button>
					</div>
					<div class="shop-widget">
						<h4 class="shop-widget-title">Ratings</h4>
						<ul class="shop-checkbox-list rating">
							<li>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="rating"
										id="rate1" value="5"> <label class="form-check-label"
										for="rate1"> <i class="fas fa-star"></i> <i
										class="fas fa-star"></i> <i class="fas fa-star"></i> <i
										class="fas fa-star"></i> <i class="fas fa-star"></i>
									</label>
								</div>
							</li>
							<li>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="rating"
										id="rate2" value="4"> <label class="form-check-label"
										for="rate2"> <i class="fas fa-star"></i> <i
										class="fas fa-star"></i> <i class="fas fa-star"></i> <i
										class="fas fa-star"></i> <i class="fal fa-star"></i>
									</label>
								</div>
							</li>
							<li>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="rating"
										id="rate3" value="3"> <label class="form-check-label"
										for="rate3"> <i class="fas fa-star"></i> <i
										class="fas fa-star"></i> <i class="fas fa-star"></i> <i
										class="fal fa-star"></i> <i class="fal fa-star"></i>
									</label>
								</div>
							</li>
							<li>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="rating"
										id="rate4" value="2"> <label class="form-check-label"
										for="rate4"> <i class="fas fa-star"></i> <i
										class="fas fa-star"></i> <i class="fal fa-star"></i> <i
										class="fal fa-star"></i> <i class="fal fa-star"></i>
									</label>
								</div>
							</li>
							<li>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="rating"
										id="rate5" value="1"> <label class="form-check-label"
										for="rate5"> <i class="fas fa-star"></i> <i
										class="fal fa-star"></i> <i class="fal fa-star"></i> <i
										class="fal fa-star"></i> <i class="fal fa-star"></i>
									</label>
								</div>
							</li>
						</ul>
						<button type="submit" class="theme-btn" onclick="changeRating()">
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


							<select id="sort-options" class="select"
								onchange="changSort(this.value)">
								<option value="" selected disabled>Chọn sắp xếp</option>
								<option value="asc">Giá từ thấp đến cao</option>
								<option value="desc">Giá từ cao đến thấp</option>
							</select>

						</div>
					</div>
				</div>
				<div class="shop-item-wrap item-3">
					<div class="row g-4">
						<c:if test="${products.size()== 0 && keyword!=null}">
							<div
								style="display: flex; justify-content: center; align-items: center;">

								<div>Không tìm thấy sản phẩm nào phù hợp</div>
							</div>
						</c:if>
						<!-- List Products -->
						<c:if test="${products.size() > 0}">
							<c:forEach items="${products}" var="item">
								<div class="col-md-6 col-lg-4">
									<div class="product-item">
										<div class="product-img">
											<a
												href='<c:url value="/products?id=${item.productID}"/>'><img
												src="${item.displayedImage}" alt="IMG-PRODUCT"></a>
											<div class="product-action-wrap">
												<div class="product-action">
													<a href='<c:url value="/products?id=${item.productID}"/>'
														data-bs-toggle="modal"
														data-bs-target="#quickview-${item.productID}"
														data-tooltip="tooltip" title="Quick View"><i
														class="far fa-eye"></i></a> <a
														href='<c:url value="/products?id=${item.productID}"/>'
														data-tooltip="tooltip" title="View detail"><i
														class="far fa-search"></i></a>
												</div>
											</div>
										</div>
										<div class="product-content">
											<h3 class="product-title">
												<a
													href='<c:url value="/products?id=${item.productID}"/>'>${item.productName}</a>
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
															value="${item.displayedPromotionPrice}"
															currencyCode="VND" pattern="#,##0 VND"
															var="formattedPrice" /> ${formattedPrice}</span>
												</div>
												<a class="product-cart"
													href="<c:url value='/products?id=${item.productID}' />"
													data-bs-placement="left" data-tooltip="tooltip"
													title="Add To Cart"> <i class="far fa-shopping-bag"></i>
												</a>
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
															<span class="stext-105 cl3"> <i
																class="fas fa-star"
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
																<a href="<c:url value='/products?id=${item.productID}' />" class="theme-btn">Buy This Product</a>
															</div>
															<div class="quickview-social">
																<span>Share:</span> <a ><i
																	class="fab fa-facebook-f"></i></a> <a ><i
																	class="fab fa-x-twitter"></i></a> <a ><i
																	class="fab fa-pinterest-p"></i></a> <a ><i
																	class="fab fa-instagram"></i></a> <a ><i
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
							</c:forEach>
							<!-- Pagination -->
							<div class="pagination-area mt-50">
								<ul class="pagination">
									<!-- Previous Button -->
									<li class="page-item ${page == 1 ? 'disabled' : ''}"><a
										class="page-link" href="#" aria-label="Previous"
										onclick="changePage('${page - 1}')"> <span
											aria-hidden="true"><i class="far fa-arrow-left"></i></span>
									</a></li>

									<!-- Page Number Buttons -->
									<c:forEach begin="1" end="${totalPage}" varStatus="loop">
										<li class="page-item ${page == loop.index ? 'active' : ''}">
											<a class="page-link" href="#"
											onclick="changePage('${loop.index}')">${loop.index}</a>
										</li>
									</c:forEach>

									<!-- Next Button -->
									<li class="page-item ${page == totalPage ? 'disabled' : ''}">
										<a class="page-link" href="#" aria-label="Next"
										onclick="changePage('${page + 1}')"> <span
											aria-hidden="true"><i class="far fa-arrow-right"></i></span>
									</a>
									</li>
								</ul>
							</div>
						</c:if>
					</div>
				</div>
				<br>
			</div>
		</div>
	</div>
</div>
<!-- shop-area end -->
<script>
	var params = {
		keyword : "${keyword}",
		cateId : "${cateId}",
		sort : "${sort}",
		price : "${price}",
		rating : "${rating}",
		page : "${page}",
	};

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

            // Ghép giá trị thành chuỗi theo định dạng 'minPrice-maxPrice'
            let priceRange = minPrice + '-' + maxPrice;

            // Gọi hàm changPrice với giá trị chuỗi
            changPrice(priceRange);
        });
    });
	function changeRating() {
		// Lấy tất cả các radio button với name là 'rating'
		const ratingRadios = document.getElementsByName('rating');

		// Tìm giá trị của radio button được chọn
		let selectedRating = null;
		for (let i = 0; i < ratingRadios.length; i++) {
			if (ratingRadios[i].checked) {

				params["rating"] = ratingRadios[i].value;
				params["page"] = 1
				run(0);
				break;
			}
		}

		// Kiểm tra xem có rating nào được chọn không
		if (selectedRating) {
			console.log("Giá trị rating được chọn: " + selectedRating);
			// Bạn có thể thực hiện hành động khác, ví dụ: gửi giá trị này đến server
		} else {
			console.log("Không có đánh giá nào được chọn.");
		}
	}
	function run() {
		var url = "?"
				+ Object.keys(params).map(
						function(key) {
							if (params[key] !== null
									&& params[key] !== undefined
									&& params[key] !== "") {
								return encodeURIComponent(key) + '='
										+ encodeURIComponent(params[key]);
							}
						}).filter(Boolean).join('&');

		window.location.href = url;
	}

	function changPrice(price) {
		params["price"] = price;
		params["page"] = 1
		run(0);
	}

	function changSort(sort) {
		params["sort"] = sort;
		params["page"] = 1
		run(0);
	}
	function changePage(page) {
		params["page"] = page
		run(0);
	}
	function getQueryParam(param) {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get(param); // Trả về giá trị tham số (hoặc null nếu không tồn tại)
    }

    // Hàm đặt giá trị mặc định cho radio button
    function setDefaultRating() {
        const rating = getQueryParam('rating'); // Lấy giá trị tham số rating từ URL
        if (rating) {
            const radio = document.querySelector(`input[name="rating"][value="${rating}"]`);
            if (radio) {
                radio.checked = true; // Đặt radio button có giá trị tương ứng thành checked
            }
        }
    }

    // Gọi hàm khi trang được tải
    document.addEventListener('DOMContentLoaded', setDefaultRating);
 	
    
    
   
    
</script>
</html>