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
								${jobInfo.getTen()}				
							</h1>	
						</div>											
					</div>
				</div>
			</section>
			<!-- End banner Area -->	
				
			<!-- Start post Area -->
			<section class="post-area section-gap">
				<div class="container">
					<div class="row justify-content-center d-flex">
						<div class="col-lg-8 post-list">
							<div class="single-post d-flex flex-row">
								<div class="details maxWidth">
									<div class="title d-flex flex-row justify-content-between">
										<div class="titles">
											<h2 class = "blueText">${jobInfo.getTen()}</h2>
										</div>
									</div>
                                    <h4 class="single-title">Mô Tả Chi Tiết</h4>
									<p>
										${fn:replace(jobInfo.getThongTin(), newLineChar, "<br/>")}
									</p>
									<h6>Hạn nhận: ${jobInfo.getHanChot()}</h6>
									<h6> Ngân sách: 
										<span><fmt:formatNumber type = "number" pattern = ",##0" value = "${jobInfo.getNganSachTu()}" /> VNĐ</span>
										 - 
										 <span><fmt:formatNumber type = "number" pattern = ",##0" value = "${jobInfo.getNganSachDen()}" /> VNĐ</span>
									 </h6>
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
																		
						</div>
						<div class="col-lg-4 sidebar">
							<div class="single-slidebar">
								<h4>Thông Tin Freelancer</h4>
								<div class="protfolio-widget">
                                    <img class = "avatarImg" src="${userInfo.getAvt()}" alt="">
                                    <div class = "ratingStar" title = "${ratingUser}">
										<c:forEach var = "i" begin = "1" end = "5">
											<c:choose>
													<c:when test="${i <= ratingUser}">
														<span class="fa fa-star checked"></span>
													</c:when>
													<c:when test="${i+1 > ratingUser && i-1 < ratingUser}">
														<span class="fa fa-star-half-o"></span>
													</c:when>
													<c:otherwise >
														<span class="fa fa-star-o"></span>
													</c:otherwise>
											</c:choose>
								      	</c:forEach>	
									</div>		
                                    <a class = "jobName" href="user/${userInfo.getId()}.htm"><h3>${userInfo.getHoTen()}</h3></a>	
                                    <h4 class="single-title">
                                    <c:choose>
										<c:when test="${userInfo.getChucDanh().length() == 0}">Chưa Có Chức Danh</c:when>
										<c:otherwise >${userInfo.getChucDanh()}</c:otherwise>
									</c:choose>
                                   
                                    </h4>			
                                </div>
                                <div>
                                    <dl class = "infoKH">
                                    	<dt>Đã Chào Giá</dt> <dd>${createdJobs}</dd>
                                        <dt>Đã Hoàn Thành</dt> <dd>${doneJobs}</dd>
                                        <dt>Thu Nhập</dt> <dd><fmt:formatNumber type = "number" pattern = ",##0" value = "${userInfo.getThuChi()}" /> VNĐ</span></dd>
                                        
                                    </dl>
                                </div>
                                
							</div>
						</div>
					</div>
                    
				</div>	
			</section>
            
			<!-- End post Area -->


			<!-- Start callto-action Area -->
			<c:choose>
				<c:when test="${userLogin != null && userLogin.getType() == false}">
					<section class=" section-gap" style = "padding-top: 0px">
						<div class="container">
							<div class="row d-flex justify-content-center formChaoGia">
								<div class="col-lg-10" style = "padding: 30px">
									<h2>Chào Giá Dự Án</h2>
									<br>
		
									<form:form modelAttribute="offer" class="form-area contact-form" id="offerForm"  
									onsubmit="return confirm('Bạn có chắc chắn muốn nhận Freelancer này?') ? true : false;"
									action="job/${jobInfo.getId()}/${userInfo.getId()}.htm" method="post">
									
										<div class="row">	
											<div class="col-lg-12 form-group">
		                                        <h4>Chi Phí Đề Xuất:</h4>
	                                        	<spring:bind path = "chaoGia">
													<div class="${status.error ? 'has-error' : ''} row">
														<input name = "chaoGia" value = "<fmt:formatNumber type = "number" pattern = ",##0" value = "${offer.getChaoGia()}" /> VNĐ"  type="text" placeholder="Chi phí đề xuất là chi phí mà bạn muốn nhận sau khi hoàn thành xong dự án. (VNĐ)" readonly/>
		                                        		<label for="chaoGia" placeholder="${status.error ? 'Vui Lòng Mô Tả Dự Án' : ''}"></label>
													</div>
												</spring:bind>
												
		                                        <br><br><h4>Hoàn Thành Trong:</h4>
	                                        	<spring:bind path = "chaoGia">
													<div class="${status.error ? 'has-error' : ''} row">
														<input name = "hoanThanhTrong"  value = "${offer.getHoanThanhTrong()} ngày" type="text" placeholder="Số ngày cần thiết để hoàn thành dự án." readonly/>
		                                        		<label for="hoanThanhTrong" placeholder="${status.error ? 'Vui Lòng Mô Tả Dự Án' : ''}"></label>
													</div>
												</spring:bind>
		
		                                        <br><br><h4>Đề Xuất Thực Hiện Dự Án:</h4>
		                                        <spring:bind path = "deXuat">
													<div class="${status.error ? 'has-error' : ''} row">
														<textarea name = "deXuat" class = "resizable" 
														type="text" readonly>${offer.getDeXuat()}</textarea>

														<label for="deXuat" placeholder="${status.error ? 'Vui Lòng Đề Xuất Dự Án' : ''}"></label>
													</div>
												</spring:bind>
												<div class="mt-20 alert-msg" style="text-align: left;"></div>
											</div>
		                                    <div style = "margin: 0 auto"><button name = "submit"  class="primary-btn mt-20 text-white">Nhận Freelancer</button></div>
										</div>
									</form:form>	
								</div>		
							</div>	
						</div>	
					</section>
				</c:when>
			</c:choose>

            
			<!-- End calto-action Area -->


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



