<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
		
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
					<div class="row d-flex align-items-center justify-content-center">
						<div class="about-content col-lg-12">
							<h1 class="text-white">
								Thông Tin Chung				
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
						
						<div class="col-lg-3 sidebar">
							<div class="single-widget category-widget">
								<ul>
									<li><a href="user/${user.id}/account.htm" class="justify-content-between align-items-center d-flex"><h6>Tài Khoản</h6></a></li>
									<li><a href="user/${user.id}/change-pass.htm" class="justify-content-between align-items-center d-flex"><h6>Đổi Mật Khẩu</h6></a></li>
									<li><a href="user/${user.id}/change-info.htm" class="justify-content-between align-items-center d-flex"><h6>Thông Tin Chung</h6></a></li>
									
								</ul>
							</div>

							
							<!--
							<div class="single-widget category-widget">
								<h4 class="title">Post Archive</h4>
								<ul>
									<li><a href="#" class="justify-content-between align-items-center d-flex"><h6>Dec '17</h6> <span>37</span></a></li>
									<li><a href="#" class="justify-content-between align-items-center d-flex"><h6>Nov '17</h6> <span>24</span></a></li>
									<li><a href="#" class="justify-content-between align-items-center d-flex"><h6>Oct '17</h6> <span>59</span></a></li>
									<li><a href="#" class="justify-content-between align-items-center d-flex"><h6>Sep '17</h6> <span>29</span></a></li>
									<li><a href="#" class="justify-content-between align-items-center d-flex"><h6>Aug '17</h6> <span>15</span></a></li>
									<li><a href="#" class="justify-content-between align-items-center d-flex"><h6>Jul '17</h6> <span>09</span></a></li>
									<li><a href="#" class="justify-content-between align-items-center d-flex"><h6>Jun '17</h6> <span>44</span></a></li>
								</ul>
							</div>			
							-->
											

						</div>
						<!-- GIỚI THIỆU CHUNG -->
							
							<div class="col-lg-9">
								<form:form class="form-area contact-form" modelAttribute="user" id="infoForm" action="user/${user.id}/change-info.htm" method="post">
                                    <input type="hidden" id = "userID" value = "${user.id}"/>
                                    <dl class="acc">
                                    
                                    		<spring:bind path="chucDanh">
												<div class="${status.error ? 'has-error' : ''} row">
													<dt class="col-sm-3">
														<h5>Chức Danh:</h5>
													</dt>
													<dd class="col-sm-9">
														<form:input path="chucDanh" placeholder="Bạn làm nghề gì?" type="text" />
													</dd>
												</div>
											</spring:bind>
											
											<spring:bind path="gioiThieu">
												<div class="${status.error ? 'has-error' : ''} row">
													<dt class="col-sm-3">
														<h5>Giới Thiệu Bản Thân:</h5>
													</dt>
													<dd class="col-sm-9">
														<form:textarea path="gioiThieu" placeholder="Giới thiệu đôi nét về bản thân bạn!" type="text" />
													</dd>
												</div>
											</spring:bind>
											
											<div class = "row">
												<dt class="col-sm-3"><h5>Kĩ Năng:</h5></dt>
	                                            <dd class="col-sm-9">
	                                            
	                                            	<select  name = "cagetoryId" id = "slim-select" multiple>
	                                            		
														<c:forEach var="content" items="${userSkill}" varStatus="stt">
															
															<c:choose>
														         <c:when test="${content.value}">
																		<option value = "${content.key.getId()}" selected>${content.key.getSkill()}</option>
																</c:when>
																<c:otherwise>
																		<option value = "${content.key.getId()}">${content.key.getSkill()}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
														
													</select>
													<script>
		                                            	var list = '${list}';
		                                            </script>
	                                            </dd>										
											</div>
                                            
                                            <div class="mt-20 alert-msg" style="text-align: left;"></div>
                                            <p style = "text-align: center" >${message}</p>
											<div style = "margin: 0 auto;"><button id = "submitBtn" class="primary-btn mt-20 text-white" style="float: right;">Lưu Thông Tin</button></div>
                                        </dl>
                                         
                                </form:form>	
							</div>
																											
						</div>
					</div>
				</div>	
			</section>
			<!-- End blog-posts Area -->
			
	
			<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.0/jquery.js"></script>
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
	
			<script src="js/jquery-ui.js"></script>	
			<script src="js/skillSelect.js"></script>	
			<script src="https://cdnjs.cloudflare.com/ajax/libs/slim-select/1.26.2/slimselect.min.js"></script>
			
		</body>
	</html>
