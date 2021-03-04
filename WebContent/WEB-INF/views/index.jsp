<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<!-- Site Title -->
		<base href="${pageContext.servletContext.contextPath}/">
		<title>Freelance</title>
		<script src="js/jquery-ui.js"></script>	
		<link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@100;200;300;400;500;600;700" rel="stylesheet"> 
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slim-select/1.26.2/slimselect.min.css"> 
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
						<div class="banner-content col-lg-12">
							<h1 class="text-white">
								<span>Hàng ngàn</span> việc làm đang đợi bạn!				
							</h1>	
							<form action="search.htm" class="serach-form-area" method = "post">
								<div class="row justify-content-center form-wrap">
									<div class="col-lg-4 form-cols">
										<input type="text" class="form-control" name="keySearch" placeholder="Nhập từ khóa tìm kiếm">
									</div>
									
									<div class="col-lg-3 form-cols">
										<div class="default-select" id="default-selects2">
											<select  name = "cagetoryId">
												<option value = "-1">Tất Cả Lĩnh Vực</option>
												<c:forEach var="content" items="${listCagetories}" varStatus="stt">
													<option value = "${content.getId()}">${content.getLinhVuc()}</option>
												</c:forEach>
											</select>
										</div>										
									</div>
									<div class="col-lg-2 form-cols">
									    <button name = "searchBtn" type="submit" class="btn btn-info">
									      <span class="lnr lnr-magnifier"></span> Tìm Kiếm
									    </button>
									</div>								
								</div>
							</form>	
							
							
						</div>											
					</div>
				</div>
			</section>
			<!-- End banner Area -->	

			<!-- Start features Area -->
			<section class="features-area">
				<div class="container">
					<div class="row">
						<div class="col-lg-3 col-md-6">
							<div class="single-feature">
								<h4>Tìm Kiếm</h4>
								<p>
									Tìm kiếm nhanh chóng, hàng ngàn công việc tiềm năng đang đợi bạn. 
								</p>
							</div>
						</div>
						<div class="col-lg-3 col-md-6">
							<div class="single-feature">
								<h4>Ứng Tuyển</h4>
								<p>
									Ứng tuyển ngay vào nhưng công việc phù hợp với bạn.
								</p>
							</div>
						</div>
						<div class="col-lg-3 col-md-6">
							<div class="single-feature">
								<h4>Bảo Mật</h4>
								<p>
									Thông tin cá nhân và công việc được bảo mật theo chính sách.
								</p>
							</div>
						</div>
						<div class="col-lg-3 col-md-6">
							<div class="single-feature">
								<h4>Miễn Phí</h4>
								<p>
									Đem đến cho người dùng các dịch vụ tuyệt vời hoàn toàn miễn phí.
								</p>
							</div>
						</div>																		
					</div>
				</div>	
			</section>
			<!-- End features Area -->
			
			
			
			<!-- Start feature-cat Area -->
			<section class="feature-cat-area pt-100" id="category">
				<div class="container">
					<div class="row d-flex justify-content-center">
						<div class="menu-content pb-60 col-lg-10">
							<div class="title text-center">
								<h1 class="mb-10">Lĩnh Vực Được Thuê Nhiều Nhất</h1>
							</div>
						</div>
					</div>						
					<div class="row">
						<div class="col-lg-2 col-md-4 col-sm-6">
							<div class="single-fcat">
								<a href="category.html">
									<img src="img/o1.png" alt="">
								</a>
								<p>Lập Trình</p>
							</div>
						</div>
						<div class="col-lg-2 col-md-4 col-sm-6">
							<div class="single-fcat">
								<a href="category.html">
									<img src="img/o2.png" alt="">
								</a>
								<p>Thiết Kế</p>
							</div>
						</div>
						<div class="col-lg-2 col-md-4 col-sm-6">
							<div class="single-fcat">
								<a href="category.html">
									<img src="img/o3.png" alt="">
								</a>
								<p>Dịch Thuật</p>
							</div>
						</div>
						<div class="col-lg-2 col-md-4 col-sm-6">
							<div class="single-fcat">
								<a href="category.html">
									<img src="img/o4.png" alt="">
								</a>
								<p>Marketing & Bán Hàng</p>
							</div>
						</div>
						<div class="col-lg-2 col-md-4 col-sm-6">
							<div class="single-fcat">
								<a href="category.html">
									<img src="img/o5.png" alt="">
								</a>
								<p>Nhập Liệu</p>
							</div>
						</div>
						<div class="col-lg-2 col-md-4 col-sm-6">
							<div class="single-fcat">
								<a href="category.html">
									<img src="img/o6.png" alt="">
								</a>
								<p>Kế Toán</p>
							</div>			
						</div>																											
					</div>
				</div>	
			</section>
			<!-- End feature-cat Area -->
			
			<!-- Start post Area -->
			<section class="post-area section-gap">
				<div class="container">
					<div class="row justify-content-center d-flex">
						<div class="col-lg-8 post-list">
						
							<!-- ================================ -->
							${jobsMess}
							<!-- ================================ -->
							
							<c:forEach var="jobInfo" items="${listJobs}" varStatus="stt">
								<div class="single-post d-flex flex-row">
									<div class="details maxWidth">
										<div class="title d-flex flex-row justify-content-between">
											<div class="titles">
												<a class = "jobName" href="job/${jobInfo.getId()}.htm">
												<c:choose>
													<c:when test="${jobInfo.getHoanThanh()}">Đã Hoàn Thành</c:when>
												</c:choose>
												<h3>${jobInfo.getTen()}</h3>
												</a>
												<input type="hidden" id = "jobID" value = "${jobInfo.getId()}"/>
												<div style = "display: flex;">
													<a href="user/${jobInfo.getAccount().getId()}.htm" style="margin: auto 0px;"><h5 style="margin: auto;"> ${jobInfo.getAccount().getUsers().getHoTen()}</h5></a>
													<!--   <div class = "ratingStar" title = "${userRating}">
														<c:forEach var = "i" begin = "1" end = "5">
															<c:choose>
																<c:when test="${i <= userRating}">
																	<span class="fa fa-star checked"></span>
																</c:when>
																<c:when test="${i+1 > userRating}">
																	<span class="fa fa-star-half-o"></span>
																</c:when>
																<c:otherwise >
																	<span class="fa fa-star-o"></span>
																</c:otherwise>
															</c:choose>
												      	</c:forEach>	
													</div>	-->		
												</div>				
											</div>
											<ul class="btns">
												
												<c:choose>
										         	<c:when test="${fav.contains(jobInfo.getId())}">
														<li><a><span class=" fa fa-heart favme active" id = "${jobInfo.getId()}"></span></a></li>
													</c:when>
													<c:otherwise>
														<li><a><span class=" fa fa-heart favme" id = "${jobInfo.getId()}"></span></a></li>
													</c:otherwise>
												</c:choose>
												
											</ul>
										</div>
										<p class = "show-read-more">
											${jobInfo.getThongTin()}
										</p>
										<!-- <h6>Hạn nhận: ${jobInfo.getHanChot()}</h6>  -->
										<h6> Ngân sách: 
											<span><fmt:formatNumber type = "number" pattern = ",##0" value = "${jobInfo.getNganSachTu()}" /></span>
											 - 
											 <span><fmt:formatNumber type = "number" pattern = ",##0" value = "${jobInfo.getNganSachDen()}" /> VNĐ</span>
										 </h6>
										<p>Số người chào giá: ${jobInfo.getJobsUsers().size()}</p>
										<div class="thumb">
											<ul class="tags">
													<li><a style = "color: #795fff">${jobInfo.getJobCagetory().getCongViec()}</a></li>
													<c:forEach var="jobSkill" items="${jobInfo.getJobsSkills()}" varStatus="skillStt">
														<li><a href="#">${jobSkill.getSkills().getSkill()}</a></li>
													</c:forEach>
												
											</ul>
										</div>
									</div>
								</div>	
							</c:forEach>
							
							<!-- ================================ -->
							
							<!-- ============================================ =-->
							<!-- ============================================ =-->
							<!-- ============================================ =-->
							
							
							<!-- = = ============================  -->
							

						</div>
						<div class="col-lg-4 sidebar">
							<div class="single-slidebar">
								<h4>Tổng Hợp Theo Lĩnh Vực</h4>
								<ul class="cat-list">
									<c:forEach var="content" items="${list}" varStatus="stt">
										<li><a class="justify-content-between d-flex" href="search/${content.getChaoGia()}.htm"><p>${content.getHoTen()}</p><span>${content.getHoanThanh()}</span></a></li>
									</c:forEach>
								</ul>
							</div>

						</div>
					</div>
				</div>	
			</section>
			<!-- End post Area -->
				

			<!-- Start callto-action Area -->
			<section class="callto-action-area section-gap" id="join">
				<div class="container">
					<div class="row d-flex justify-content-center">
						<div class="menu-content col-lg-9">
							<div class="title text-center">
								<h1 class="mb-10 text-white">Trải nghiệm các tiện ích tuyệt vời ngay hôm nay</h1>
								<p class="text-white">Freelance cung cấp cho Nhà Tuyển Dụng nguồn nhân lực chất lượng với mức chi phí hợp lý nhất. Cùng với đó giúp các Freelancer tìm thấy các công việc phù hợp và thu nhập hấp dẫn.</p>
								<c:choose>
							         <c:when test="${userLogin == null}">
							        		 <a class="primary-btn" href="signup.htm">Đăng Kí Ngay</a>
									</c:when>
								</c:choose>
							</div>
						</div>
					</div>	
				</div>	
			</section>
			<!-- End calto-action Area -->

			<!-- review -->
			<section class="testimonial-area section-gap" id="review">
				<div class="container">
					<div class="row d-flex justify-content-center">
						<div class="menu-content pb-60 col-lg-8">
							<div class="title text-center">
								<h1 class="mb-10">${ratingMess} Nhận Xét Từ Người Dùng Freelance</h1>
								
							</div>
						</div>
					</div>						
					<div class="row">
						<div class="active-review-carusel">
						<!-- --- -->
						
							<c:forEach var="content" items="${ratingList}" varStatus="stt">
							<div class="single-review">
								<img class = "avatarImg" src="${content.getAccount().getUsers().getAvt()}" alt="">
								<div class="title d-flex flex-row">
									<a href="user/${content.getId()}.htm"><h4>${content.getAccount().getUsers().getHoTen()}</h4></a>
									<div class="star">
										<c:forEach var = "i" begin = "1" end = "5">
											<c:choose>
													<c:when test="${i <= content.getRate()}">
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
									"${content.getCmt()}"
								</p>
							</div>	
							</c:forEach>
							
						<!--  -->					
						</div>
					</div>
				</div>	
			</section>
			<!-- End review Area -->
		
					
			<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.0/jquery.min.js"></script>
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
			<script src="https://cdnjs.cloudflare.com/ajax/libs/slim-select/1.26.2/slimselect.min.js"></script>
		</body>
	</html>



