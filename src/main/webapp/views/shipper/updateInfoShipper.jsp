<%@page import="FurSPS.other.City"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<main>
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
						<h3 class="text-center font-weight-light my-4">Cập nhật thông
							tin</h3>
					</div>
					<div class="card-body">
						<form action="shipper-update-info" method="post">
							<div class="row">
								<div class="col-md-6">
									<input type="hidden" name="userID" value="${shipper.userID}" />
									<div class="form-floating mb-3">
										<input class="form-control" type="text" name="firstName"
											value="${shipper.firstName}" required /> <label>Tên</label>
									</div>
									<div class="form-floating mb-3">
										<input class="form-control" type="text" name="lastName"
											value="${shipper.lastName}" required/> <label>Họ</label>
									</div>
									<div class="form-floating mb-3">
										<input class="form-control" type="text" name="address"
											value="${shipper.address}" required/> <label>Địa chỉ</label>
									</div>
									<div class="form-floating mb-3">
										<input class="form-control" type="email" name="email"
											value="${shipper.email}" required/> <label>Email</label>
									</div>
									<div class="form-floating mb-3">
										<select class="form-control" name="gender"
											style="height: calc(3.5rem + 2px);">
											<option value="0">Nam</option>
											<c:choose>
												<c:when test="${shipper.gender==1}">
													<option value="1" selected>Nữ</option>
												</c:when>
												<c:otherwise>
													<option value="1">Nữ</option>
												</c:otherwise>
											</c:choose>
										</select> <label> Giới tính </label>
									</div>
								</div>

								<div class="col-md-6">
									<div class="form-floating mb-3">
										<input class="form-control" type="tel" name="phone"
											value="${shipper.phone}" pattern="^[0-9]{10}$"
											title="Số điện thoại phải là 10 chữ số" required/> <label>SĐT</label>
									</div>
									<div class="form-floating mb-3">
										<input class="form-control" type="date" name="dob"
											value="${shipper.dob}" required/> <label>Ngày sinh</label>
									</div>
									<div class="form-floating mb-3">
										<input class="form-control" type="text" name="cid"
											value="${shipper.cid}" pattern="^\d{12}$"
											title="Căn cước công dân phải gồm 12 chữ số" required /> <label>CCCD</label>
									</div>
									<div class="form-floating mb-3">
										<input class="form-control" name="area" readonly
											value="${shipper.area}" list="listlist" id="fruitsInput">
										<datalist id="listlist">
											<c:forEach var="city" items="${listAssign}">
												<option value="${city.toString()}">
											</c:forEach>
										</datalist>
										<label for="fruitsInput">Phân công</label> <input
											style="visibility: hidden" class="form-control"
											list="datalistOptions" id="exampleDataList"
											placeholder="Type to search...">
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
<script>
	// Activate Autocomplete.js on the input element
	new autoComplete({
		data : {
			src : [ "Apple", "Banana", "Cherry", "Date", "Grape" ]
		},
		selector : "#fruitsInput"
	});
</script>