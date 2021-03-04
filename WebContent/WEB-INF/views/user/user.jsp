<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% pageContext.setAttribute("newLineChar", "\n"); %>
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
					<div class="row d-flex align-items-center justify-content-center">
						<div class="about-content col-lg-12">
							<h1 class="text-white">
								${userInfo.getHoTen()}				
								${userMess}				
							</h1>	
						</div>											
					</div>
				</div>
			</section>
			<!-- End banner Area -->	
			
			<!-- Start blog-posts Area -->
			<section class="blog-posts-area section-gap">
				<div class="container">
					<div class="row">
						
						<div class="col-lg-4 sidebar">
							<div class="single-widget">
								<div class = "protfolio-widget">
									<img class = "avatarImg" src="${userInfo.getAvt()}" alt="">
									<a href="user/${userInfo.getId()}.htm"><h4>${userInfo.getHoTen()}</h4></a>
									<h6><i class="fa fa-circle-o"></i>
										<c:choose>
											<c:when test="${userInfo.getChucDanh().length() == 0}">Chưa Có Chức Danh</c:when>
											<c:otherwise >${userInfo.getChucDanh()}</c:otherwise>
										</c:choose>
	
									
									</h6>
								<div class = "ratingStar" title = "${userRating}">
									<c:forEach var = "i" begin = "1" end = "5">
										<c:choose>
												<c:when test="${i <= userRating}">
													<span class="fa fa-star checked"></span>
												</c:when>
												<c:when test="${i+1 > userRating && i-1 < userRating}">
													<span class="fa fa-star-half-o"></span>
												</c:when>
												<c:otherwise >
													<span class="fa fa-star-o"></span>
												</c:otherwise>
										</c:choose>
							      	</c:forEach>	
								</div>
								
								</div>
								<div  class = "category-widget">
									<c:choose>
										<c:when test="${userInfo.getType()}">
											<ul>
												<li><a  class="justify-content-between align-items-center d-flex"><h6>Đã Chào Giá</h6> <span>${createdJobs}</span></a></li>
												<li><a  class="justify-content-between align-items-center d-flex"><h6>Việc Đã Hoàn Thành</h6> <span>${doneJobs}</span></a></li>
												<li><a  class="justify-content-between align-items-center d-flex"><h6>Thu Nhập</h6>
												 <span><fmt:formatNumber type = "number" pattern = ",##0" value = "${userInfo.getThuChi()}" /> VNĐ</span></a></li>
												 
											</ul>	
											
										</c:when>
										<c:otherwise >
											<ul>
												<li><a  class="justify-content-between align-items-center d-flex"><h6>Việc Đã Tạo</h6> <span>${createdJobs}</span></a></li>
												<li><a  class="justify-content-between align-items-center d-flex"><h6>Việc Đã Hoàn Thành</h6> <span>${doneJobs}</span></a></li>
												<li><a  class="justify-content-between align-items-center d-flex"><h6>Đã Thanh Toán</h6>
												 <span><fmt:formatNumber type = "number" pattern = ",##0" value = "${userInfo.getThuChi()}" /> VNĐ</span></a></li>
												 
											</ul>	
											
										</c:otherwise>
									</c:choose>
									
								</div>							
							</div>

							<div class="single-widget tags-widget">
								<h4 class="title">Kĩ Năng</h4>
								 <ul>
								 	<c:forEach var="content" items="${userSkill}" varStatus="stt">
										<li><a>${content.getSkill()}</a></li>
									</c:forEach>
								 </ul>
							</div>

							
							
							
							<!-- ====================================================================================== -->

							<div class="single-widget recent-posts-widget">
								<h4 class="title">Quan Tâm</h4>
								<!-- ====================================================================================== -->
								
								<c:forEach var="fav" items="${fav}" varStatus="stt">
									<div class="single-blog " style="background:-webkit-linear-gradient(0deg, #bfacff 0%, #795fff 100%)">
										<a href="job/${fav.getId()}.htm"><h4>${fav.getJobName()}</h4></a>
										<div class="meta justify-content-between d-flex">
											<p><span class="lnr lnr-user"></span> ${fav.getUserName()}</p>
											<p><span class="lnr lnr-bubble"></span> ${fav.getOffers()}</p>
										</div>
									</div>	
								</c:forEach>
								
								<!-- ====================================================================================== -->
												
							</div>

							<!-- ====================================================================================== -->
											

						</div>
						<!-- GIỚI THIỆU CHUNG -->
							<div class="col-lg-8">
								<div class="single-post">
										<h1>
											Đôi Nét Về Bản Thân
										</h1>
										<h5>
											<c:choose>
												<c:when test="${userInfo.getGioiThieu().toString().length() == 0}">Bí Mật!</c:when>
												<c:otherwise >${fn:replace(userInfo.getGioiThieu(), newLineChar, "<br/>")}</c:otherwise>
											</c:choose>
										</h5>
									<div class="bottom-meta">
									</div>
								</div>


							<!-- CÁC CÔNG VIỆC ĐÃ LÀM -->
							<!-- CÁC CÔNG VIỆC ĐÃ LÀM -->
							<br><br><br><br><br>
							
							<c:choose>
								<c:when test = "${userInfo.getType()}">
									<h1><span> Các Dự Án Đã Chào Giá </span></h1>
								</c:when>
								<c:otherwise>
									<h1><span> Các Dự Án Đã Tạo </span></h1>
								</c:otherwise>
							</c:choose>
								<!-- ====================================================== -->
								<!-- ====================================================== -->
								<!-- ====================================================== -->
								
								<div class = "post-list blog-post-list"> 
								<!-- ======================================================  -->
								<c:forEach var="jobInfo" items="${userJobs}" varStatus="stt">
									<div class="single-post">	
										<ul class="tags">
											<li><a>${jobInfo.getJobCagetory().getCongViec()} </a></li>
										</ul>
										<a href="job/${jobInfo.getId()}.htm">
											<h2 style = "margin-top: 0px">
												<b style = "color: #0091da;">${jobInfo.getTen()}</b>
											</h2>
										</a>
											<p>
												${jobInfo.getThongTin()}
											</p>
										<div class="bottom-meta">
											<div class="user-details row align-items-center">
												<div class="comment-wrap col-lg-6">
													<ul>
													<c:choose>
														<c:when test="${jobInfo.getHoanThanh()}">
															<li><span class="lnr lnr-checkmark-circle acText"></span> Hoàn Thành</li>
														</c:when>
														<c:otherwise >
															<li><span class="lnr lnr-circle-minus"></span> Đang Tiến Hành</li>
														</c:otherwise>
													</c:choose>
														
													</ul>
												</div>
												<div class="social-wrap col-lg-6">
													<ul>
														<li><i class="fa fa-money"></i></li>
														<li><a>
																<fmt:formatNumber type = "number" pattern = ",###" value = "${jobInfo.getNganSachTu()}"/>
																- 
																<fmt:formatNumber type = "number" pattern = ",##0" value = "${jobInfo.getNganSachDen()}"/> VNĐ</a></li>
														
														
													</ul>
													
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
								<!-- ====================================================== -->
								<!-- ====================================================== -->
								<!-- ====================================================== -->
							
							
							
							</div>																				
						</div>
					</div>
				</div>	
			</section>
			<!-- End blog-posts Area -->
			
			
			<!-- Nhận xét -->
			<section class="testimonial-area section-gap" id="review">
				<div class="container">
					<div class="row d-flex justify-content-center">
						<div class="menu-content pb-60 col-lg-8">
							<div class="title text-center">
								<h1 class="mb-10">${ratingMess} Nhận Xét Về Người Dùng Này</h1>
								
							</div>
						</div>
					</div>						
					<div class="row">
						<div class="active-review-carusel">
						<!-- --- -->
							<c:forEach var="content" items="${ratingList}" varStatus="stt">
							<div class="single-review">
								<img class = "avatarImg" src="${content.getAvt()}" alt="">
								<div class="title d-flex flex-row">
									<a href="user/${content.getId()}.htm"><h4>${content.getHoTen()}</h4></a>
									<div class="star">
										<c:forEach var = "i" begin = "1" end = "5">
											<c:choose>
													<c:when test="${i <= content.getRating()}">
														<span class="fa fa-star checked"></span>
													</c:when>
													<c:otherwise >
														<span class="fa fa-star"></span>
													</c:otherwise>
											</c:choose>
								      	</c:forEach>								
									</div>
								</div>
								<p>
									"${content.getGioiThieu()}"
								</p>
							</div>	
							</c:forEach>
							
						<!--  -->					
						</div>
					</div>
				</div>	
			</section>
				

			<script src="js/vendor/jquery-2.2.4.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
			<script src="js/vendor/bootstrap.min.js"></script>			
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
