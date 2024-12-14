<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ListItem</title>
<style type="text/css">
.circle {
	display: inline-block;
	width: 20px;
	height: 20px;
	border-radius: 50%;
}
</style>
</head>
<body>
	<main>
		<div class="container-fluid px-4">
			<h1 class="mt-4">Mặt hàng</h1>
			<ol class="breadcrumb mb-4">
				<li class="breadcrumb-item"><a>Bảng điều
						khiển</a></li>
				<li class="breadcrumb-item"><a
					href="<c:url value = "adminProduct"/>">Sản phẩm</a></li>
				<li class="breadcrumb-item active">Mặt hàng</li>
			</ol>
			<c:if test="${not empty message }">
				<div class="alert alert-${alert}">
					<strong>${message}!</strong>
				</div>
			</c:if>
			<c:if test="${ProID != null}">
				<div class="card mb-4">
					<div class="card-body">
						<a href="<c:url value='/admininsertItem?ProID=${ProID}'/>"
							style="margin-right: 50px;">
							<button type="button" class="btn btn-dark">
								<i class="ace-icon fa fa-pencil"></i> Thêm mặt hàng
							</button>
						</a>
					</div>
				</div>
			</c:if>
			<div class="card mb-4">
				<div class="card-header">
					<i class="fas fa-table me-1"></i> Bảng mặt hàng
				</div>
				<div class="card-body">
					<table id="datatablesSimple">
						<thead>
							<tr>
								<th>Mã mặt hàng</th>
								<th>Mã sản phẩm</th>
								<th>Màu sắc</th>
								<th>Mã màu</th>
								<th>Kích thước</th>
								<th>Số lượng tồn</th>
								<th>Giá gốc</th>
								<th>Giá khuyến mãi</th>
								<th>Hành động</th>

							</tr>
						</thead>
						<tfoot>
						<tbody>
							<c:forEach var="i" items="${listItem}">
								<tr>
									<td>${i.itemID}</td>
									<td>${i.productID}</td>
									<td>${i.color}</td>
									<td><div
											style="background-color: ${i.colorCode}; width: 25px; height: 25px;"></div>
									</td>
									<td>${i.size}</td>
									<td>${i.stock}</td>
									<td>${i.originalPrice}</td>
									<td>${i.promotionPrice}</td>
									<td><div class="hidden-sm hidden-xs btn-group">
											<a
												href="<c:url value='/adminupdateItem?ItemID=${i.itemID}'/>">
												<button class="btn btn-xs btn-info btn-sm">
													<i class="ace-icon fa fa-pencil"></i>
												</button>
											</a> 
												<!-- Nút xóa -->
												<a href="#" class="trigger-btn" data-toggle="modal" data-target="#confirmDeleteModal"
												   data-link="<c:url value='/admindeleteItem?ItemID=${i.itemID}&ProductID=${i.productID}'/>">
												   <button type="button" class="btn btn-xs btn-info btn-sm">
												      <i class="ace-icon fa fa-trash"></i>
												   </button>
												</a>
										</div></td>
								</tr>
							</c:forEach>
						</tbody>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
		
								<!-- Modal xác nhận xóa -->
		<div class="modal fade" id="confirmDeleteModal" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Xác nhận xóa</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">Bạn có chắc chắn muốn xóa item
						này không?</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Hủy</button>
						<button type="button" class="btn btn-danger" id="confirmDeleteBtn">Xóa</button>
					</div>
				</div>
			</div>
		</div>
	</main>
	
<script>
	// Lưu lại URL xóa vào biến toàn cục khi nhấn nút xóa
	var deleteUrl = '';

	$(document).on('click', '.trigger-btn', function() {
		// Lấy giá trị của data-link và lưu vào biến deleteUrl
		deleteUrl = $(this).data('link');
		console.log("Delete URL: " + deleteUrl);  // In URL ra console để kiểm tra

		// Hiển thị modal xác nhận
		$('#confirmDeleteModal').modal('show');
	});

	// Khi người dùng nhấn "Xóa" trong modal, thực hiện hành động xóa
	$('#confirmDeleteBtn').on('click', function() {
		if (deleteUrl) {
			// Nếu có URL hợp lệ, thực hiện chuyển hướng đến URL để xóa
			window.location.href = deleteUrl;
		} else {
			alert("URL không hợp lệ!");  // Nếu không có URL hợp lệ
		}
	});

	// Khi người dùng nhấn "Hủy" trong modal, đóng modal và xóa lớp overlay
	$('#confirmDeleteModal').on('hidden.bs.modal', function () {
		deleteUrl = '';  // Reset URL khi đóng modal
		$('.modal-backdrop').remove();  // Loại bỏ lớp phủ của modal
		$('body').removeClass('modal-open');  // Đảm bảo rằng body không còn bị khóa
	});
</script>

</body>
</html>