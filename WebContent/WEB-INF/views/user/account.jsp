<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zxx" class="no-js">
<head>
<!-- Mobile Specific Meta -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Favicon-->
<link rel="shortcut icon" href="img/fav.png">
<!-- Author Meta -->
<meta name="author" content="codepixer">
<!-- Meta Description -->
<meta name="description" content="">
<!-- Meta Keyword -->
<meta name="keywords" content="">
<!-- meta character set -->
<meta charset="UTF-8">
<!-- Site Title -->
<base href="${pageContext.servletContext.contextPath}/">
<title>Freelance</title>

<link
	href="https://fonts.googleapis.com/css2?family=Quicksand:wght@100;200;300;400;500;600;700"
	rel="stylesheet">
<!--
			CSS
			============================================= -->
<link rel="stylesheet" href="css/linearicons.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/magnific-popup.css">
<link rel="stylesheet" href="css/nice-select.css">
<link rel="stylesheet" href="css/animate.min.css">
<link rel="stylesheet" href="css/owl.carousel.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/jquery-ui.css">
</head>
<body>

	<!-- header -->
	  <jsp:include page = "/WEB-INF/layout/header.jsp"/>
	<!-- header -->


	<!-- start banner Area -->
	<section class="banner-area relative" id="home">
		<div class="overlay overlay-bg"></div>
		<div class="container">
			<div class="row d-flex align-items-center justify-content-center">
				<div class="about-content col-lg-12">
					<h1 class="text-white">Thông Tin Tài Khoản</h1>
				</div>
			</div>
		</div>
	</section>
	<!-- End banner Area -->

	<!-- Start blog-posts Area -->
	<section class="blog-posts-area section-gap">
		<div class="container">
			<div class="row">

				<div class="col-lg-3 sidebar">
					<div class="single-widget category-widget">
						<ul>
							<li><a href="user/${user.id}/account.htm" class="justify-content-between align-items-center d-flex"><h6>Tài Khoản</h6></a></li>
							<li><a href="user/${user.id}/change-pass.htm" class="justify-content-between align-items-center d-flex"><h6>Đổi Mật Khẩu</h6></a></li>
							<li><a href="user/${user.id}/change-info.htm" class="justify-content-between align-items-center d-flex"><h6>Thông Tin Chung</h6></a></li>

						</ul>
					</div>

				


				</div>
				<!-- GIỚI THIỆU CHUNG -->
				<div class="col-lg-9">
					
					<form:form class="form-area contact-form" modelAttribute="user"
						id="accountForm" enctype='multipart/form-data' action="user/${user.id}/account.htm" method="post">
						<dl class="acc">
							<div class="row">
									
								<dt class="col-sm-3">
									<h5>Ảnh Đại Diện:</h5>
									<p>Kích thước < 2mb. Định dạng: jpg, jpeg, png.</p>
								</dt>
								<dd class="col-sm-9">
									<img class="avatarImg" src="${user.avt}" alt=""> <input
										type="file" name="image"> ${avata}
								</dd>
							</div>

							<spring:bind path="hoTen">
								<div class="${status.error ? 'has-error' : ''} row">
									<dt class="col-sm-3">
										<h5>Tên Của Bạn:</h5>
									</dt>
									<dd class="col-sm-9">
										<form:input path="hoTen" type="text" />
										<label for="hoTen" alt=""
											placeholder="${status.error ? 'Vui Lòng Điền Tên Của Bạn' : ''}"></label>
									</dd>
								</div>
							</spring:bind>

							<spring:bind path="email">
								<div class="${status.error ? 'has-error' : ''} row">
									<dt class="col-sm-3">
										<h5>Email:</h5>
									</dt>
									<dd class="col-sm-9">
										<form:input path="email" type="text" />
										<label for="email" alt=""
											placeholder="${status.error ? 'Vui Lòng Kiểm Tra Lại Email' : ''}"></label>
									</dd>
								</div>
							</spring:bind>

							<spring:bind path="sdt">
								<div class="${status.error ? 'has-error' : ''} row">
									<dt class="col-sm-3">
										<h5>Số Điện Thoại:</h5>
									</dt>
									<dd class="col-sm-9">
										<form:input path="sdt" type="text" />
										<label for="sdt" alt=""
											placeholder="${status.error ? 'Vui Lòng Kiểm Tra Lại Số Điện Thoại' : ''}"></label>
									</dd>
								</div>
							</spring:bind>

							<spring:bind path="ngaySinh">
								<div class="${status.error ? 'has-error' : ''} row">
									<dt class="col-sm-3">
										<h5>Ngày Sinh:</h5>
									</dt>
									<dd class="col-sm-9">
										<form:input path="ngaySinh" type="date" />
									</dd>
								</div>
							</spring:bind>

							<spring:bind path="diaChi">
								<div class="${status.error ? 'has-error' : ''} row">
									<dt class="col-sm-3">
										<h5>Địa Chỉ:</h5>
									</dt>
									<dd class="col-sm-9">
										<form:input path="diaChi" type="text" />
									</dd>
								</div>
							</spring:bind>


							<!--  
											<dt class="col-sm-3"><h5>Email:</h5></dt>
                                            <dd class="col-sm-9"><input name="name" value = "test" placeholder="Enter your name" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter your name'" class="common-input mb-20 form-control" required="" type="text"></dd>

											<dt class="col-sm-3"><h5>Số Điện Thoại:</h5></dt>
                                            <dd class="col-sm-9"><input name="name" value = "test" placeholder="Enter your name" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter your name'" class="common-input mb-20 form-control" required="" type="text"></dd>

											<dt class="col-sm-3"><h5>Ngày Sinh:</h5></dt>
                                            <dd class="col-sm-9"><input type="text" value = "13/03/2021" id="datepicker"></dd>

											<dt class="col-sm-3"><h5>Địa Chỉ:</h5></dt>
                                            <dd class="col-sm-9"><input name="name" value = "test" placeholder="Enter your name" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter your name'" class="common-input mb-20 form-control" required="" type="text"></dd>
													-->
							<div class="mt-20 alert-msg" style="text-align: left;"></div>

						</dl>
						<div style="text-align: center;">
							${message}
							<button class="primary-btn mt-20 text-white" style="float: right;">Lưu Thông Tin</button>
						</div>
                                        
                                </form:form>


					<!-- CÁC CÔNG VIỆC ĐÃ LÀM -->
					<!-- CÁC CÔNG VIỆC ĐÃ LÀM -->
					<br>
					<br>
					<br>
					<br>
					<br>
					<div class="post-list blog-post-list">
						<!-- ====================================================== -->

						<!-- ====================================================== -->
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- End blog-posts Area -->


	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="js/vendor/jquery-2.2.4.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script src="js/vendor/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBhOdIF3Y9382fqJYt5I_sswSrEw5eihAA"></script>
	<script src="js/easing.min.js"></script>
	<script src="js/hoverIntent.js"></script>
	<script src="js/superfish.min.js"></script>
	<script src="js/jquery.ajaxchimp.min.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/jquery.sticky.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/parallax.min.js"></script>
	<script src="js/mail-script.js"></script>
	<script src="js/main.js"></script>
	<script src="js/jquery-ui.js"></script>

</body>
</html>
