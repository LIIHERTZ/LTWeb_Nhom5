<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Table - Seller</title>
</head>
<body>
	<main>
		<div class="container">
			<c:if test="${not empty message }">
				<div class="alert alert-${alert}">
					<strong>${message}!</strong>
				</div>
			</c:if>

			<div class="col-4">
				<div class="col-4">
					<div class="btn btn-dark"
						style="text-align: left; display: inline-block; margin: 0;">
						<a href="${pageContext.request.contextPath}/adminSeller"> <img
							style="display: inline-block; vertical-align: middle; width: 30px; height: auto; cursor: pointer; transition: all 0.2s ease;"
							src="https://www.iconeasy.com/icon/png/Business/Pretty%20Office%205/Go%20back.png"
							alt="">
						</a>
					</div>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-lg-8">
					<div class="card shadow-lg border-0 rounded-lg mt-5">
						<div class="card-header">
							<h3 class="text-center font-weight-light my-4">Cập nhật
								thông tin người bán hàng</h3>
						</div>
						<div class="card-body">
							<form action="adminUpdateSeller" method="post">
								<div class="row">
									<div class="col-md-6">
										<div class="form-floating mb-3">
											<input class="form-control" type="text" name="userID"
												value="${seller.userID}" readonly="readonly" required /> <label>Mã</label>
										</div>
										<div class="form-floating mb-3">
											<input class="form-control" type="text" name="firstName"
												value="${seller.firstName}" required /> <label>Họ</label>
										</div>
										<div class="form-floating mb-3">
											<input class="form-control" type="text" name="lastName"
												value="${seller.lastName}" required /> <label>Tên</label>
										</div>
										<div class="form-floating mb-3">
											<input class="form-control" type="text" name="address"
												value="${seller.address}" required /> <label>Địa chỉ</label>
										</div>
										<div class="form-floating mb-3">
											<input class="form-control" type="email" name="email"
												value="${seller.email}" required /> <label>Email</label>
										</div>
										<div class="form-floating mb-3">
											<select class="form-control" name="gender" required>
												<option value="0" ${seller.gender == 0 ? 'selected' : ''}>Nam</option>
												<option value="1" ${seller.gender == 1 ? 'selected' : ''}>Nữ</option>
											</select> <label> Giới tính </label>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-floating mb-3">
											<input class="form-control" type="tel" name="phone"
												value="${seller.phone}" pattern="^[0-9]{10}$"
												title="Số điện thoại phải là 10 chữ số" required /> <label>Số
												điện thoại</label>
										</div>
										<div class="form-floating mb-3">
											<input class="form-control" type="date" name="dob"
												value="${seller.dob}" required /> <label>Ngày sinh</label>
										</div>
										<div class="form-floating mb-3">
											<input class="form-control" type="text" name="cid"
												value="${seller.cid}" pattern="^\d{12}$"
												title="Căn cước công dân phải gồm 12 chữ số" required /> <label>CCCD</label>
										</div>
										<!-- 										<div class="form-floating mb-3"> -->
										<!-- 											<input class="form-control" type="text" name="avatar" -->
										<%-- 												value="${seller.avatar}" required/> <label>Ảnh đại diện</label> --%>
										<!-- 										</div> -->
										<div class="form-floating mb-3">
											<input class="form-control" type="text" name="kpi"
												value="${seller.kpi}" required /> <label>KPI</label>
										</div>
									</div>
								</div>
								<div class="text-center mt-4">
									<input type="submit" class="btn btn-primary" value="Cập nhật" />
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