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

<!-- title -->
<title>Fameo - Furniture Store HTML5 Template</title>

<!-- favicon -->
<link rel="icon" type="image/x-icon" href="assets/img/logo/favicon.png">

<!-- css -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/all-fontawesome.min.css">
<link rel="stylesheet" href="assets/css/animate.min.css">
<link rel="stylesheet" href="assets/css/magnific-popup.min.css">
<link rel="stylesheet" href="assets/css/owl.carousel.min.css">
<link rel="stylesheet" href="assets/css/jquery-ui.min.css">
<link rel="stylesheet" href="assets/css/nice-select.min.css">
<link rel="stylesheet" href="assets/css/style.css">


<!-- Phuc -->
<link href='<c:url value="/templates/web/css/checkout/checkout.css"/>'
	rel="stylesheet" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/MaterialDesign-Webfont/5.3.45/css/materialdesignicons.css" />
<link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
	rel="stylesheet">
	
<link rel="stylesheet" type="text/css"
	href="<c:url value="/templates/web/css/util.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/templates/web/css/main.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/templates/web/css/products/products.css"/>">
	
	<link rel="stylesheet" type="text/css"
	href="<c:url value="/templates/web/fonts/iconic/css/material-design-iconic-font.min.css"/>">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/templates/web/fonts/linearicons-v1.0.0/icon-font.min.css"/>">


</head>

<body>


	<%@ include file="/common/web/header.jsp"%>

	<sitemesh:write property="body" />

	<%@ include file="/common/web/footer.jsp"%>



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
							<img src="assets/img/product/04.png" alt="#">
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
	<script src="assets/js/jquery-3.7.1.min.js"></script>
	<script src="assets/js/modernizr.min.js"></script>
	<script src="assets/js/bootstrap.bundle.min.js"></script>
	<script src="assets/js/imagesloaded.pkgd.min.js"></script>
	<script src="assets/js/jquery.magnific-popup.min.js"></script>
	<script src="assets/js/isotope.pkgd.min.js"></script>
	<script src="assets/js/jquery.appear.min.js"></script>
	<script src="assets/js/jquery.easing.min.js"></script>
	<script src="assets/js/owl.carousel.min.js"></script>
	<script src="assets/js/counter-up.js"></script>
	<script src="assets/js/jquery-ui.min.js"></script>
	<script src="assets/js/jquery.nice-select.min.js"></script>
	<script src="assets/js/countdown.min.js"></script>
	<script src="assets/js/wow.min.js"></script>
	<script src="assets/js/main.js"></script>
	
	
	
	
	
	<!-- Phuc -->
	<!--===============================================================================================-->
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<!-- Include SweetAlert library from CDN -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>


	<script
		src="<c:url value="/templates/web/vendor/jquery/jquery-3.2.1.min.js"/>"></script>
	<!--===============================================================================================-->
	<script
		src="<c:url value="/templates/web/vendor/animsition/js/animsition.min.js"/>"></script>
	<!--===============================================================================================-->
	<script
		src="<c:url value="/templates/web/vendor/bootstrap/js/popper.js"/>"></script>
	<script
		src="<c:url value="/templates/web/vendor/bootstrap/js/bootstrap.min.js"/>"></script>
	<!--===============================================================================================-->
	<script
		src="<c:url value="/templates/web/vendor/select2/select2.min.js"/>"></script>
	<script>
		$(".js-select2").each(function() {
			$(this).select2({
				minimumResultsForSearch : 20,
				dropdownParent : $(this).next(".dropDownSelect2")
			});
		})
	</script>
	<!--===============================================================================================-->
	<script
		src="<c:url value="/templates/web/vendor/MagnificPopup/jquery.magnific-popup.min.js"/>"></script>
	<!--===============================================================================================-->
	<script
		src="<c:url value="/templates/web/vendor/perfect-scrollbar/perfect-scrollbar.min.js"/>"></script>
	<script>
		$(".js-pscroll").each(function() {
			$(this).css("position", "relative");
			$(this).css("overflow", "hidden");
			var ps = new PerfectScrollbar(this, {
				wheelSpeed : 1,
				scrollingThreshold : 1000,
				wheelPropagation : false,
			});

			$(window).on("resize", function() {
				ps.update();
			})
		});
	</script>
	<!--===============================================================================================-->
	<script src="<c:url value="/templates/web/js/main.js"/>"></script>
	<script src="<c:url value="/templates/web/js/product-detail.js"/>"></script>
	<script src="<c:url value="/templates/web/js/tiny-slider.js"/>"></script>

	<!--===============================================================================================-->
	<script
		src="<c:url value="/templates/web/vendor/jquery/jquery-3.2.1.min.js"/>"></script>
	<!--===============================================================================================-->
	<script
		src="<c:url value="/templates/web/vendor/bootstrap/js/popper.js"/>"></script>
	<script
		src="<c:url value="/templates/web/vendor/bootstrap/js/bootstrap.min.js"/>"></script>
	<!--===============================================================================================-->
	<!--===============================================================================================-->
	<script
		src="<c:url value="/templates/web/vendor/daterangepicker/moment.min.js"/>"></script>
	<script
		src="<c:url value="/templates/web/vendor/daterangepicker/daterangepicker.js"/>"></script>
	<!--===============================================================================================-->
	<script src="<c:url value="/templates/web/vendor/slick/slick.min.js"/>"></script>
	<script src="<c:url value="/templates/web/js/slick-custom.js"/>"></script>
	<!--===============================================================================================-->
	<script src="<c:url value="vendor/parallax100/parallax100.js"/>"></script>
	<script>
		$('.parallax100').parallax100();
	</script>
	<!--===============================================================================================-->
	<script
		src="<c:url value="/templates/web/vendor/MagnificPopup/jquery.magnific-popup.min.js"/>"></script>
	<script>
		$('.gallery-lb').each(function() { // the containers for all your galleries
			$(this).magnificPopup({
				delegate : 'a', // the selector for gallery item
				type : 'image',
				gallery : {
					enabled : true
				},
				mainClass : 'mfp-fade'
			});
		});
	</script>
	<!--===============================================================================================-->
	<script
		src="<c:url value="/templates/web/vendor/isotope/isotope.pkgd.min.js"/>"></script>
	<!--===============================================================================================-->
	<script
		src="<c:url value="/templates/web/vendor/sweetalert/sweetalert.min.js"/>"></script>
	<script>
		$('.js-addwish-b2').on('click', function(e) {
			e.preventDefault();
		});

		$('.js-addwish-b2').each(
				function() {
					var nameProduct = $(this).parent().parent().find(
							'.js-name-b2').html();
					$(this).on(
							'click',
							function() {
								swal(nameProduct,
										"được thêm vào danh sách yêu thích !",
										"success");

								$(this).addClass('js-addedwish-b2');
								$(this).off('click');
							});
				});

		$('.js-addwish-detail').each(
				function() {
					var nameProduct = $(this).parent().parent().parent().find(
							'.js-name-detail').html();

					$(this).on(
							'click',
							function() {
								swal(nameProduct,
										"được thêm vào danh sách yêu thích !",
										"success");

								$(this).addClass('js-addedwish-detail');
								$(this).off('click');
							});
				});

		/*---------------------------------------------*/

		$('.js-addcart-detail').each(
				function() {
					var nameProduct = $(this).parent().parent().parent()
							.parent().find('.js-name-detail').html();
					$(this).on('click', function() {
						swal(nameProduct, "is added to cart !", "success");
					});
				});
	</script>
	<!--===============================================================================================-->
	<script
		src="<c:url value="/templates/web/vendor/perfect-scrollbar/perfect-scrollbar.min.js"/>"></script>
	<script>
		$('.js-pscroll').each(function() {
			$(this).css('position', 'relative');
			$(this).css('overflow', 'hidden');
			var ps = new PerfectScrollbar(this, {
				wheelSpeed : 1,
				scrollingThreshold : 1000,
				wheelPropagation : false,
			});

			$(window).on('resize', function() {
				ps.update();
			})
		});
	</script>
	<script>
		function setRating(index) {
			// Get all buttons
			var stars = document.getElementsByClassName("star");

			// Remove highlight class from all buttons
			for (var i = 0; i < stars.length; i++) {
				stars[i].classList.remove("highlight");
			}

			// Add highlight class to buttons from 1 to the clicked index
			for (var i = 0; i < index; i++) {
				stars[i].classList.add("highlight");
			}

			// Update the value of the hidden input with the selected index
			document.getElementById("ratingInput").value = index;
		}
		function handleButtonClick(button) {
			// Remove the active class from all buttons
			var buttons = document.querySelectorAll('.btn-group button');
			buttons.forEach(function(btn) {
				btn.style.borderColor = '#bdc3c7';
			});

			// Add the active class to the clicked button
			button.style.borderColor = '#4b4b4b'; // Change to your desired active border color

			// Additional logic if needed
			// For example, you can update some hidden input with the selected value
			var selectedValue = button.value;
			document.getElementById('hiddenInput').value = selectedValue;
		}
	</script>

</body>


<!-- Mirrored from live.themewild.com/fameo/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 03 Nov 2024 06:40:25 GMT -->
</html>