<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">


<!-- Mirrored from live.themewild.com/fameo/user-profile.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 03 Nov 2024 06:40:54 GMT -->
<head>
<body>



	<!-- user dashboard -->
	<div class="user-area bg pt-100 pb-80">
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="sidebar">
						<div class="sidebar-top">
							<div class="sidebar-profile-img">
								<img src="/FurSPS_Nhom5/assets/img/account/02.jpg" alt="">
								<button type="button" class="profile-img-btn">
									<i class="far fa-camera"></i>
								</button>
								<input type="file" class="profile-img-file">
							</div>
							<h5>${user.firstName} ${user.lastName}</h5>
						</div>
						<ul class="sidebar-list">
							<li><a class="active"><i
									class="far fa-user"></i> Cập nhật thông tin</a></li>
							<li><a href="<c:url value='/user/infoUser'/>"><i class="far fa-sign-out"></i> Trở về</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-9">
					<div class="user-wrapper">
						<div class="row">
							<div class="col-lg-12">
								<div class="user-card">
									<h4 class="user-card-title">Cập nhật thông tin cá nhân</h4>
									<div class="user-form">
										<form action="/FurSPS_Nhom5/user/updateUser" method="post">
											<div>
												<input type="hidden" name="UserID"
													value="${userModel.userID}"> <input type="hidden"
													name="Type" value="${userModel.type}"> <input
													type="hidden" name="KPI" value="${userModel.kpi}">
												<input type="hidden" name="image"
													value="${userModel.avatar}">
											</div>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label>Họ và đệm</label> <input type="text"
															class="form-control" value="${user.firstName}"
															name="FirstName" placeholder="First Name">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label>Tên</label> <input type="text" class="form-control"
															value="${user.lastName}" name="LastName"
															placeholder="Last Name">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label>Email</label> <input type="text"
															class="form-control" value="${user.email}" name="Email" 
															placeholder="Email" readonly>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label>Phone</label> <input type="text"
															class="form-control" name="Phone" value="${user.phone}"
															placeholder="Phone">
													</div>
												</div>

												<div class="col-md-6">
													<div class="form-group">
														<label>CCCD</label> <input type="text"
															class="form-control" name="Cid" value="${user.cid}"
															placeholder="CID">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label>BirthDay</label> <input type="date"
															class="form-control" name="Dob"
															value="${user.dob != null ? user.dob.toLocalDate().toString() : ''}"
															placeholder="DOB">
													</div>
												</div>

												<div class="col-md-6">
													<div class="form-group">
														<label>Quê quán</label> <input type="text" name="Address"
															class="form-control" value="${user.address}"
															placeholder="Address">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label>Địa chỉ</label> <input type="text" name="Area"
															class="form-control" value="${user.area}"
															placeholder="Area">
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-check-inline" style="margin-left: 60px;">
													<input class="form-check-input" type="checkbox" value="0"
														name="Gender" id="agreeMale"
														${user.gender != 1 ? 'checked' : ''}
														onclick="toggleCheckbox('agreeFemale', this)"> <label
														class="form-check-label" for="agreeMale">Nam</label>
												</div>

												<div class="form-check-inline" style="margin-left: 60px;">
													<input class="form-check-input" type="checkbox" value="1"
														name="Gender" id="agreeFemale"
														${user.gender == 1 ? 'checked' : ''}
														onclick="toggleCheckbox('agreeMale', this)"> <label
														class="form-check-label" for="agreeFemale">Nữ</label>
												</div>
											</div>
											<script>
												function toggleCheckbox(
														otherId, checkbox) {
													const otherCheckbox = document
															.getElementById(otherId);
													otherCheckbox.checked = !checkbox.checked;
												}
											</script>
											<button type="submit" class="theme-btn"
												style="margin-left: 380px;">
												<span class="far fa-user"></span> Lưu thay đổi
											</button>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- user dashboard end -->




	<!-- scroll-top -->
	<a href="#" id="scroll-top"><i class="far fa-arrow-up-from-arc"></i></a>
	<!-- scroll-top end -->
</body>


<!-- Mirrored from live.themewild.com/fameo/user-profile.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 03 Nov 2024 06:40:54 GMT -->
</html>
