<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Table - Voucher</title>
</head>
<body>
	<main>

		<div class="col-4">
			<div class="col-4">
				<div class="btn btn-dark"
					style="text-align: left; display: inline-block; margin: 0;">
					<a href="${pageContext.request.contextPath}/adminVoucher"> <img
						style="display: inline-block; vertical-align: middle; width: 30px; height: auto; cursor: pointer; transition: all 0.2s ease;"
						src="https://www.iconeasy.com/icon/png/Business/Pretty%20Office%205/Go%20back.png"
						alt="">
					</a>
				</div>
			</div>
		</div>

		<div class="container">
			<c:if test="${not empty message }">
				<div class="alert alert-${alert}">
					<strong>${message}!</strong>
				</div>
			</c:if>
			<div class="row justify-content-center">
				<div class="col-lg-8">
					<div class="card shadow-lg border-0 rounded-lg mt-5">
						<div class="card-header">
							<h3 class="text-center font-weight-light my-4">Thêm Mã
								Khuyến Mãi</h3>
						</div>
						<div class="card-body">
							<form action="adminInsertVoucher" method="post">
								<div class="row">
									<div class="col-md-6">
										<div class="form-floating mb-3">
											<input class="form-control" type="text" name="description"
												required /> <label>Mô tả</label>
										</div>
										<div class="form-floating mb-3">
											<input class="form-control" type="number" name="discount"
												min="0" step="1" required /> <label>Giảm giá</label>
										</div>
										<div class="form-floating mb-3">
											<input class="form-control" type="number" name="minimumPrice"
												min="0" step="1" required /> <label>Giá tiền tối
												thiểu</label>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-floating mb-3">
											<input class="form-control" type="number" name="quantity"
												min="0" step="1" required /> <label>Sổ lượng</label>
										</div>
										<div class="form-floating mb-3">
											<input class="form-control" type="date" name="mfg" required />
											<label>Ngày bắt đầu</label>
										</div>
										<div class="form-floating mb-3">
											<input class="form-control" type="date" name="exp" required />
											<label>Ngày kết thúc</label>
										</div>
									</div>
								</div>
								<div class="text-center mt-4">
									<input type="submit" class="btn btn-primary" value="Thêm" />
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
</body>
</html>