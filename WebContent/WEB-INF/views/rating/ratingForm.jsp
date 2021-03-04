<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
	
	<!DOCTYPE html>
	<html lang="zxx" class="no-js">
	<head>
		<!-- Mobile Specific Meta -->
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
		<link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@100;200;300;400;500;600;700" rel="stylesheet"> 
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
					<div class="row fullscreen d-flex align-items-center justify-content-center">
						<div class="col-lg-8 formRegister" style = "padding: 50px">
							<h2>Đánh Giá Freelance</h2>
							<br>
							<img class = "avatarImg" src="${userAvt}" alt="">
							
                            <h4>${userName}</h4>
                            ${message}
							<form:form class="form-area contact-form" modelAttribute="ratingContent" id="ratingForm"
							action="rating/${from}/${to}.htm" method="post">
								<div class="row">	
									<div class="col-lg-12 form-group">
                                        <div style = "display: flex">
                                            <div id="rating">
                                                <form:radiobutton path = "rate"  id="star5" name="rating" value="5" />
                                                <label class = "full" for="star5" title="Cực Kì Tuyệt Tời - 5 stars"></label>
                                            
                                                <form:radiobutton path = "rate"  id="star4" name="rating" value="4" />
                                                <label class = "full" for="star4" title="Tuyệt Vời - 4 stars"></label>
                                            
                                                <form:radiobutton path = "rate"  id="star3" name="rating" value="3" />
                                                <label class = "full" for="star3" title="Ổn - 3 stars"></label>
                                            
                                                <form:radiobutton path = "rate"  id="star2" name="rating" value="2" />
                                                <label class = "full" for="star2" title="Tệ - 2 stars"></label>
                                            
                                                <form:radiobutton path = "rate"  id="star1" name="rating" value="1" />
                                                <label class = "full" for="star1" title="Cực Kì Tệ - 1 star"></label>
                                            </div>
                                        </div>
                                        <form:textarea  path = "cmt" name="comment" type="text"/>
                                        <label for="comment" alt="Nhập Nhận Xét" placeholder="Nhận Xét Của Bạn"></label> 
										<div class="mt-20 alert-msg" style="text-align: left;"></div>
									</div>
                                    <div style = "margin: 0 auto"><button class="primary-btn mt-20 text-white">Gửi Đánh Giá</button></div>
								</div>
							</form:form>	
						</div>							
					</div>
					


			<script src="js/vendor/jquery-2.2.4.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
			<script src="js/vendor/bootstrap.min.js"></script>			
			<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBhOdIF3Y9382fqJYt5I_sswSrEw5eihAA"></script>
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



