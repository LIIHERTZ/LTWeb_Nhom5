
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ListProduct</title>
<style>
.card-body a {
	margin-right: 10px; /* Khoảng cách giữa các phần tử <a> */
}
</style>
</head>
<body>

	<main>

		<div class="container-fluid px-4">
			<h1 class="mt-4">Sản phẩm</h1>
			<ol class="breadcrumb mb-4">
				<li class="breadcrumb-item"><a href="adminHome">Trang chủ</a></li>
				<li class="breadcrumb-item active">Sản phẩm</li>
			</ol>
			<c:if test="${not empty message }">
				<div class="alert alert-${alert}">
					<strong>${message}!</strong>
				</div>
			</c:if>
			<div class="row">
				<div class="col-xl-6">
					<div class="card mb-4">
						<div class="card-header">
							<i class="fas fa-chart-bar me-1"></i> Top 5 sản phẩm được đánh
							giá cao nhất
						</div>
						<div class="card-body">
							<canvas id="myChart" width="100%" height="40"></canvas>
							<script>
                var barChartData = {
                    labels: [
                        <c:forEach var="item" items="${productrating}">
                            "${item.get(1)}",
                        </c:forEach>
                    '',],
                    datasets: [
                        {
                            label: 'Số sao trung bình',
                            data: [
                                <c:forEach var="item" items="${productrating}">
                                    "${item.get(2)}",
                                </c:forEach>
                            0,],
                            backgroundColor: 'rgba(255, 99, 132, 0.6)'
                        }
                    ]
                };

                new Chart(document.getElementById("myChart"), {
                    type: 'bar',
                    data: barChartData
                });
            </script>
						</div>
					</div>
				</div>
			</div>
			<div class="card mb-4">
				<div class="card-body">
					<a href="<c:url value='/admininsertProduct'/>"
						style="margin-right: 50px;">
						<button type="button" class="btn btn-dark">
							<i class="ace-icon fa fa-pencil"></i> Thêm sản phẩm
						</button>
					</a> <a href="<c:url value='/adminItem'/>">
						<button type="button" class="btn btn-dark">
							<i class="ace-icon fa fa-list-ul"></i> Danh sách mặt hàng
						</button>
					</a>
				</div>
			</div>

			<div class="card mb-4">
				<div class="card-header">
					<i class="fas fa-table me-1"></i> Bảng sản phẩm
				</div>
				<div class="card-body">
					<table id="datatablesSimple">
						<thead>
							<tr>
								<th>Mã sản phẩm</th>
								<th>Tên sản phẩm</th>
								<th>Mô tả</th>
								<th>Nguồn gốc</th>
								<th>Mã nhà cung cấp</th>
								<th>Mã loại</th>
								<th>Chất liệu</th>
								<th>Hành động</th>

							</tr>
						</thead>
						<tfoot>
						<tbody>
							<c:forEach var="i" items="${listProduct}">
								<tr>
									<td>${i.productID}</td>
									<td>${i.productName}</td>
									<td>${i.description}</td>
									<td>${i.origin}</td>
									<td><c:forEach var="m" items="${listSupplier}">
											<c:if test="${i.supplierID == m.supplierID}">${m.supplierName}</c:if>
										</c:forEach></td>
									<td><c:forEach var="j" items="${listCate}">
											<c:if test="${i.categoryID == j.categoryID}">${j.categoryName}</c:if>
										</c:forEach></td>
									<td>${i.material}</td>
									<td><div class="hidden-sm hidden-xs btn-group">
											<a
												href='<c:url value = '/adminviewItem?ProductID=${i.productID}'></c:url>'>
												<button class="btn btn-xs btn-info btn-sm">
													<i class="ace-icon fa fa-eye"></i>
												</button>
											</a> <a
												href="<c:url value='/adminupdateProduct?ProductID=${i.productID}'/>">
												<button class="btn btn-xs btn-info btn-sm">
													<i class="ace-icon fa fa-pencil"></i>
												</button>
											</a>
											<a href="#" class="trigger-btn" data-toggle="modal"
												data-target="#confirmDeleteModal"
												data-link="<c:url value='/admindeleteProduct?ProductID=${i.productID}'/>">
												<button type="button" class="btn btn-xs btn-info btn-sm"
													id="liveToastBtn">
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
					<div class="modal-body">Bạn có chắc chắn muốn xóa sản phẩm
						này không? Việc xóa sẽ xóa tất cả các item có liên quan.</div>
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