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
								Thông Tin Tài Khoản				
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
						
						<!-- GIỚI THIỆU CHUNG -->
							<div class="col-lg-12">
								<form:form modelAttribute="jobInfo" class="form-area contact-form" id="jobForm" action="job/post.htm" method="post" >
									<input type="hidden" name = "newJobID" id = "jobID" value = "${newJobId}"/>
                                    <dl class="acc">
                                    
                                    
                  <!-- Loại công việc -->
                                   	 		<spring:bind path = "jobCagetory">
												<div class="${status.error ? 'has-error' : ''} row">
													<dt class="col-sm-3">
														<h5>Loại Dự Án:</h5>
													</dt>
													<dd class="col-sm-8">
														<select  name = "jobTypeId">
															<c:forEach var="content" items="${jobTypeList}" varStatus="stt">
																<option value = "${content.getId()}">${content.getCongViec()}</option>
															</c:forEach>
														</select>
													</dd>
												</div>
											</spring:bind>
					  
											
				<!--  Tên dự án -->							
											<spring:bind path="ten">
												<div class="${status.error ? 'has-error' : ''} row">
													<dt class="col-sm-3">
														<h5>Tên Dự Án:</h5>
													</dt>
													<dd class="col-sm-8">
														<form:input name = "ten" path="ten" placeholder="VD: Làm trang web Freelance..." class="common-input mb-20 form-control" type="text"/>
														<label for="ten" placeholder="${status.error ? 'Vui Lòng Điền Tên Dự Án' : ''}"></label>
													</dd>
												</div>
											</spring:bind>
								
				<!--  Mô tả -->						
											<spring:bind path="thongTin">
												<div class="${status.error ? 'has-error' : ''} row">
													<dt class="col-sm-3">
														<h5>Mô Tả Dự Án:</h5>
													</dt>
													<dd class="col-sm-8">
														<form:textarea path="thongTin" placeholder="VD: Trang web Freelance giúp khách hàng và các freelancer hợp tác để hoàn thành các dự án..." type="text" />
														<label for="thongTin" placeholder="${status.error ? 'Vui Lòng Mô Tả Dự Án' : ''}"></label>
													</dd>
												</div>
											</spring:bind>
							
				<!--  Ngân sách-->							
											<spring:bind path="nganSachTu">
												<div class="${status.error ? 'has-error' : ''} row">
													<dt class="col-sm-3">
														<h5>Ngân Sách:</h5>
													</dt>
													<dd class="col-sm-8">
														<form:input name="nganSachTu" path = "nganSachTu" placeholder="Ngân sách tối thiểu" class="common-input mb-20 form-control" type="text"/>
														<label for="nganSachTu" placeholder="${status.error ? 'Kiểm Tra Lại' : ''}"></label>
													</dd>
												</div>
											</spring:bind> 
											<spring:bind path="nganSachDen">
												<div class="${status.error ? 'has-error' : ''} row">
													<dt class="col-sm-3">
														<h5>Đến:</h5>
													</dt>
													<dd class="col-sm-8">
														<form:input path = "nganSachDen" name="nganSachDen" placeholder="Ngân sách tối đa" class="common-input mb-20 form-control" type="text"/>
														<label for="nganSachDen" placeholder="${status.error ? 'Kiểm Tra Lại' : ''}"></label>
													</dd>
												</div>
											</spring:bind>
											
				<!--  Hạn đăng tin 							
											<spring:bind path="hanChot">
												<div class="${status.error ? 'has-error' : ''} row">
													<dt class="col-sm-3">
														<h5>Hạn Chót Nhận Chào Giá:</h5>
													</dt>
													<dd class="col-sm-8">
														<form:input path="hanChot" type="date" />
													</dd>
												</div>
											</spring:bind>
				-->
				<!--  Kỹ năng -->				
											<spring:bind path="jobsSkills">
												<div class="${status.error ? 'has-error' : ''} row">
													<dt class="col-sm-3">
														<h5>Kỹ Năng Cần Thiết:</h5>
													</dt>
													<dd class="col-sm-8" style = "padding-top: 10px; font-size: 14px">
														<select  name = "jobSkillId" id = "slim-select" multiple>
	                                            		
														<c:forEach var="content" items="${jobSkill}" varStatus="stt">
															
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
											</spring:bind>
											
											



                                            <div class="mt-20 alert-msg" style="text-align: left;"></div>
											<div style = "text-align: center">
												<button id = "submitBtn" class="primary-btn mt-20 text-white";">Tạo Dự Án</button>
											</div>
											
                                        </dl>
                                </form:form>	


							<!-- CÁC CÔNG VIỆC ĐÃ LÀM -->
							<!-- CÁC CÔNG VIỆC ĐÃ LÀM -->
							<br><br><br><br><br>
							<div class = "post-list blog-post-list"> 
							<!-- ====================================================== -->
							
							<!-- ====================================================== -->
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
			<script src="js/jobSkillSelect.js"></script>	
			<script src="https://cdnjs.cloudflare.com/ajax/libs/slim-select/1.26.2/slimselect.min.js"></script>
			
		</body>
	</html>
