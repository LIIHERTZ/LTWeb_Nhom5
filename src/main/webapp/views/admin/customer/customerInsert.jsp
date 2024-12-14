<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Table - Customer</title>
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
					<div class="btn btn-dark" style="text-align: left; display: inline-block; margin: 0;">
						<a href="${pageContext.request.contextPath}/adminCustomer"> <img
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
							<h3 class="text-center font-weight-light my-4">Thêm khách
								hàng mới</h3>
						</div>
						<div class="card-body">
							<form action="adminInsertCustomer" method="post">
								<div class="row">
									<div class="col-md-6">
										<div class="form-floating mb-3">
											<input class="form-control" type="text" name="firstName"
												value="${firstName != null ? firstName : ''}" required /> <label>Tên</label>
										</div>
										<div class="form-floating mb-3">
											<input class="form-control" type="text" name="lastName"
												value="${lastName != null ? lastName : ''}" required /> <label>Họ</label>
										</div>
										<div class="form-floating mb-3">
											<input class="form-control" type="text" name="address"
												value="${address != null ? address : ''}" required /> <label>Địa
												chỉ</label>
										</div>
										<div class="form-floating mb-3">
											<select class="form-control" name="gender" required>
												<option value="0" ${customer.gender == 0 ? 'selected' : ''}>Nam</option>
												<option value="1" ${customer.gender == 1 ? 'selected' : ''}>Nữ</option>
											</select> <label> Giới tính </label>
										</div>
										<div class="form-floating mb-3">
											<input class="form-control" type="email" name="email"
												value="${email != null ? email : ''}" required /> <label>Email</label>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-floating mb-3">
											<input class="form-control" type="tel" name="phone"
												value="${phone != null ? phone : ''}" pattern="^[0-9]{10}$"
												title="Số điện thoại phải là 10 chữ số" required /> <label>Số
												điện thoại</label>
										</div>
										<div class="form-floating mb-3">
											<input class="form-control" type="date" name="dob"
												value="${dob != null ? dob : ''}" required /> <label>Ngày
												sinh</label>
										</div>
										<div class="form-floating mb-3">
											<input class="form-control" type="text" name="cid"
												value="${cid != null ? cid : ''}" pattern="^\d{12}$"
												title="Căn cước công dân phải gồm 12 chữ số" required /> <label>Căn
												cước công dân</label>
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