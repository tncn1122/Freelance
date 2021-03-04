
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<script src="js/jquery-ui.js"></script>
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
			<div
				class="row fullscreen d-flex align-items-center justify-content-center">
				<div class="col-lg-8 formRegister" style="padding: 50px">
					<h2>Tạo Tài Khoản Mới</h2>
					${message}
					<br>
					<br>
					<br>
					<form:form class="form-area contact-form" modelAttribute="sign"
						id="signupForm" action="signup.htm" method="post">
						<div class="row">
							<div class="col-lg-12 form-group">
								<spring:bind path="acc">
									<div class="${status.error ? 'has-error' : ''}">
										<form:input path="acc" type="text" />
										<label for="acc" alt="Nhập Tài Khoản"
											placeholder="${status.error ? 'Vui Lòng Điền Tài Khoản Của Bạn' : 'Tài Khoản Của Bạn'}"></label>
									</div>
								</spring:bind>

								<spring:bind path="pass">
									<div class="${status.error ? 'has-error' : ''}">
										<form:input path="pass" type="password" />
										<label for="pass" alt="Nhập Mật Khẩu"
											placeholder="${status.error ? 'Vui Lòng Điền Mật Khẩu' : 'Mật Khẩu Của Bạn'}"></label>
									</div>
								</spring:bind>

								<spring:bind path="repass">
									<div class="${status.error ? 'has-error' : ''}">
										<form:input path="repass" type="password" />
										<label for="repass" alt="Nhập Lại Mật Khẩu"
											placeholder="${status.error ? 'Vui Lòng Xác Nhận Mật Khẩu' : 'Xác Nhận Mật Khẩu'}"></label>
									</div>
								</spring:bind>

								<spring:bind path="name">
									<div class="${status.error ? 'has-error' : ''}">
										<form:input path="name" type="text" />
										<label for="name" alt="Nhập Tên Của Bạn"
											placeholder="${status.error ? 'Vui Lòng Điền Tên Của Bạn' : 'Tên Của Bạn'}"></label>
									</div>
								</spring:bind>

								<spring:bind path="email">
									<div class="${status.error ? 'has-error' : ''}">
										<form:input path="email" type="text" />
										<label for="email" alt="Nhập Email Của Bạn"placeholder="${status.error ? 'Vui Lòng Điền Email Chính Xác' : 'Email Của Bạn'}"></label>
									</div>
								</spring:bind>
								
								<div class="row ">
									<div class="col-lg-4">
										<h4>Bạn Là:</h4>
									</div>
									<div class="col-lg-4">
										<form:radiobutton path = "type"  value = "true" label = "Freelancer" />
									</div>
									<div class="col-lg-4">
										<form:radiobutton path = "type"  value = "false" label = "Nhà Tuyển Dụng" />
									</div>
								</div>
								<!-- <input name="email" placeholder="Enter email address" pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter email address'" class="common-input mb-20 form-control" required="" type="email"> -->

								<!-- <input name="subject" placeholder="Enter your subject" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter your subject'" class="common-input mb-20 form-control" required="" type="text">
										<textarea class="common-textarea mt-10 form-control" name="message" placeholder="Messege" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Messege'" required=""></textarea> -->
								<div class="mt-20 alert-msg" style="text-align: left;"></div>
							</div>
							
							<div style="margin: 0 auto">
								<button type="submit" class="primary-btn mt-20 text-white">Tạo
									Tài Khoản</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
			<!--<div class="row d-flex align-items-center justify-content-center">
						<div class="about-content col-lg-12">
							<h1 class="text-white">
								Đăng Kí Tài Khoản				
							</h1>	
						</div>											
					</div> -->
		</div>
	</section>
	<!-- End banner Area -->



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
</body>
</html>



