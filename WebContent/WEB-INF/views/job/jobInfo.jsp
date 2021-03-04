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
											<h2 class = "blueText">
											${jobInfo.getTen()}
											</h2>
										</div>
										<ul class="btns">
											<c:choose>
									         	<c:when test="${!jobInfo.getHoanThanh() && userLogin.getId() == userInfo.getId()}">
													<li><a href="job/edit/${jobInfo.getId()}.htm">Chỉnh Sửa</a></li>
												</c:when>
											</c:choose>
											<c:choose>
									         	<c:when test="${isFav}">
													<li><a><span class=" fa fa-heart favme active" id = "${jobInfo.getId()}"></span></a></li>
												</c:when>
												<c:otherwise>
													<li><a><span class=" fa fa-heart favme" id = "${jobInfo.getId()}"></span></a></li>
												</c:otherwise>
											</c:choose>
											
										</ul>
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
								<h4>Thông Tin Khách Hàng</h4>
								<div class="protfolio-widget">
                                    <img class = "avatarImg" src="${userInfo.getAvt()}" alt="">
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
                                    	<dt>Việc Đã Tạo</dt> <dd>${createdJobs}</dd>
                                        <dt>Đã Hoàn Thành</dt> <dd>${doneJobs}</dd>
                                        <dt>Đã Thanh Toán</dt> <dd><fmt:formatNumber type = "number" pattern = ",##0" value = "${userInfo.getThuChi()}" /> VNĐ</span></dd>
                                    </dl>
                                </div>
                                <c:choose>
									<c:when test="${jobInfo.getHoanThanh() && acceptedOffer.getId() == userLogin.getId()}">
										<div style = "text-align: center;">
											<a href = "rating/${acceptedOffer.getId()}/${userInfo.getId()}.htm" 
											class="mt-40 genric-btn info circle" style="font-size: 14px; text-align: center">Đánh Giá Khách Hàng</a>
										</div>
									</c:when>
               					</c:choose>
                                
							</div>
						</div>
					</div>
                    
				</div>	
			</section>
			
			<c:choose>
	         	<c:when test="${jobInfo.getHoanThanh()}">
		         	<div class = "menu-content pb-60 col-lg-12">
		         		<div class = "title text-center">
		         		<c:choose>
		         			<c:when test="${acceptedOffer != null}">
		         				<h1 class = "acText">Công Việc Này Đã Được Hoàn Thành</h1>
		         			</c:when>
		         			<c:otherwise>
		         				<h1 class = "rejectText">Công Việc Này Đã Bị Hủy</h1> 
		         			</c:otherwise>
		         		</c:choose>
							 
						</div>
		         	</div>
				</c:when>
			</c:choose>

            
			<!-- End post Area -->
			<c:choose>
            	<c:when test="${acceptedOffer != null}">
            		<section class=" section-gap" style = "padding-top: 0px">
						<div class="container">
							<div class="row d-flex justify-content-center formChaoGia">
								<div class="col-lg-10" style = "padding: 30px">
									<h2>Freelancer Nhận Việc</h2>
									<br>
		
									<form class="form-area " id="finishForm" action="job/finish/${jobInfo.getId()}/${acceptedOffer.getId()}.htm" 
									onsubmit="return confirm('Bạn có chắc chắn muốn xác nhận hoàn thành dự án này?') ? true : false;"
									method="post" class="contact-form text-right">
										<div class="row">	
											<div class="col-lg-12 post-list">
		                                        <div class="single-post d-flex flex-row formUserChaoGia">
		                                            <div class="thumb avatarChaoGia">
					                                    <div class="protfolio-widget">
					                                        <img src="${acceptedOffer.getAvt()}" alt="">
					                                        <div class = "ratingStar" title = "${acceptedOffer.getRating()}">
																<c:forEach var = "i" begin = "1" end = "5">
																	<c:choose>
																			<c:when test="${i <= acceptedOffer.getRating()}">
																				<span class="fa fa-star checked"></span>
																			</c:when>
																			<c:when test="${i+1 > acceptedOffer.getRating() && i-1 < acceptedOffer.getRating()}">
																				<span class="fa fa-star-half-o"></span>
																			</c:when>
																			<c:otherwise >
																				<span class="fa fa-star-o"></span>
																			</c:otherwise>
																	</c:choose>
														      	</c:forEach>	
															</div>						
					                                    </div>
					                                </div>
													<div class="details">
														<div class="title d-flex flex-row justify-content-between">
															<div class="titles">
																<a class = "jobName" href="user/${acceptedOffer.getId()}.htm"><h3>${acceptedOffer.getHoTen()}</h3></a>
															</div>
														</div>
					                                    <h4 class="single-title">${acceptedOffer.getChucDanh()}</h4>
					                                    <p class = "show-read-more">
															${acceptedOffer.getGioiThieu()}
														</p>
					                                    <ul class="tags">
					                                    	<li><h5 style = "padding-right: 5px"><b>Kỹ năng:</b> </h5><li>
					                                    	<c:forEach var ="skill" items = "${acceptedOffer.getSkills()}" varStatus="skillStt">
					                                    		<li>
																	<a>${skill.getSkills().getSkill()}
																		<c:choose>
																			<c:when test="${skillStt.count < offerInfo.getSkills().size()}">
																				, 
																			</c:when>
																		</c:choose>
																	</a>
																</li>
					                                    	</c:forEach>
					                                        
														</ul>
													</div>
					                                <div class = "thongTinChaoGia">
					                                    <dl>
					                                        <dt>Việc Đã Chào Giá</dt> <dd>${acceptedOffer.getChaoGia()}</dd>
					                                        <dt>Đã Hoàn Thành</dt> <dd>${acceptedOffer.getHoanThanh()}</dd>
					                                        <dt>Thu Nhập</dt> <dd>
					                                        <span><fmt:formatNumber type = "number" pattern = ",##0" value = "${acceptedOffer.getThuNhap()}" /> VNĐ</span>
					                                        
					                                        </dd>
					                                    </dl>					                                    
					                                </div>
					                            </div>
											</div>
											<c:choose>
												<c:when test="${jobInfo.getHoanThanh() == false && userInfo.getId() == userLogin.getId()}">
														<div style = "margin: 0 auto"><button class="primary-btn mt-20 text-white">Hoàn Tất Dự Án</button></div>
												</c:when>
												<c:when test="${jobInfo.getHoanThanh()  && userInfo.getId() == userLogin.getId()}">
														<div style = "margin: 0 auto">
														<a href = "rating/${userInfo.getId()}/${acceptedOffer.getId()}.htm" 
											class="mt-40 genric-btn info circle" style="font-size: 14px; text-align: center">Đánh Giá Freelancer</a>
											</div>
												</c:when>
                  							</c:choose>
										</div>
									</form>	
								</div>		
							</div>	
						</div>	
					</section>
            	</c:when>
            </c:choose>

			<!-- Start callto-action Area -->
			<c:choose>
				<c:when test="${userLogin != null && userLogin.getType() && acceptedOffer == null}">
					<section class=" section-gap" style = "padding-top: 0px">
						<div class="container">
							<div class="row d-flex justify-content-center formChaoGia">
								<div class="col-lg-10" style = "padding: 30px">
									<h2>Chào Giá Dự Án</h2>
									<br>
		
									<form:form modelAttribute="offer" class="form-area contact-form" id="offerForm" action="job/${jobInfo.getId()}.htm" method="post">
										<div class="row">	
											<div class="col-lg-12 form-group">
		                                        <h4>Chi Phí Đề Xuất:</h4>
	                                        	<spring:bind path = "chaoGia">
													<div class="${status.error ? 'has-error' : ''} row">
														<form:input path = "chaoGia"  type="text" placeholder="Chi phí đề xuất là chi phí mà bạn muốn nhận sau khi hoàn thành xong dự án. (VNĐ)"/>
		                                        		<label for="chaoGia"  style = "text-align: right; width: 100%" placeholder="${status.error ? 'Kiểm Tra Lại Chi Phí Mong Muốn' : ''}"></label>
													</div>
												</spring:bind>
												
		                                        <br><br><h4>Hoàn Thành Trong:</h4>
	                                        	<spring:bind path = "hoanThanhTrong">
													<div class="${status.error ? 'has-error' : ''} row">
														<form:input path = "hoanThanhTrong"  type="text" placeholder="Số ngày cần thiết để hoàn thành dự án."/>
		                                        		<label for="hoanThanhTrong" style = "text-align: right; width: 100%" placeholder="${status.error ? 'Vui Lòng Điền Số Ngày Cần Thiết' : ''}"></label>
													</div>
												</spring:bind>
		
		                                        <br><br><h4>Đề Xuất Phương Án Hoàn Thành Dự Án:</h4>
		                                        <spring:bind path = "deXuat">
													<div class="${status.error ? 'has-error' : ''} row">
														<form:textarea path = "deXuat" name = "deXuat" 
														placeholder="VD: Tôi sẽ làm trang web theo hướng..." 
														type="text" />
														<label for="deXuat" style = "text-align: right; width: 100%" placeholder="${status.error ? 'Vui Lòng Đề Xuất Phương Án' : ''}"></label>
													</div>
												</spring:bind>
												<div class="mt-20 alert-msg" style="text-align: left;"></div>
											</div>
											<c:choose>
												<c:when test="${hasOffer}">
													<div style = "margin: 0 auto"><button name = "delete" class="primary-btn mt-20 text-white">Hủy Chào Giá</button></div>
												</c:when>
											</c:choose>
		                                    
		                                    <div style = "margin: 0 auto"><button name = "submit" class="primary-btn mt-20 text-white">Gửi Chào Giá</button></div>
										</div>
									</form:form>	
								</div>		
							</div>	
						</div>	
					</section>
				</c:when>
			</c:choose>

            
			<!-- End calto-action Area -->

            <!-- Start features Area -->
			<section class="countChaoGia">
				<div class="container">
					<div class="row">
						<div class="col-lg-12">
							<div class="single-feature">
								<c:choose>
									<c:when test="${listOffers.size() == 0}">
										<h5>Dự Án Này Chưa Có Người Chào Giá!</h5>
									</c:when>
									<c:otherwise>
										<h5>Dự Án Này Hiện Có <b style = "color: #0091da; font-size: 20px">${listOffers.size()}</b> Người Chào Giá!</h5>
									</c:otherwise>
								</c:choose>
								
							</div>
						</div>															
					</div>
				</div>	
			</section>

            <!-- Start callto-action Area -->
			<section class="formChaoGia section-gap" id="join">
				<div class="container">
					<div class="row d-flex justify-content-center">
						<div class="col-lg-12 post-list">
							<c:forEach var="offerInfo" items="${listOffers}" varStatus="stt">
								<!-- -========================================================= -->
									<div class="single-post d-flex flex-row formUserChaoGia">
		                                <div class="thumb avatarChaoGia">
		                                    <div class="protfolio-widget">
		                                        <img src="${offerInfo.getAvt()}" alt="">
		                                        <div class = "ratingStar" title = "${offerInfo.getRating()}">
													<c:forEach var = "i" begin = "1" end = "5">
														<c:choose>
																<c:when test="${i <= offerInfo.getRating()}">
																	<span class="fa fa-star checked"></span>
																</c:when>
																<c:when test="${i+1 > offerInfo.getRating() && i-1 < offerInfo.getRating()}">
																	<span class="fa fa-star-half-o"></span>
																</c:when>
																<c:otherwise >
																	<span class="fa fa-star-o"></span>
																</c:otherwise>
														</c:choose>
											      	</c:forEach>	
												</div>					
		                                    </div>
		                                </div>
										<div class="details">
											<div class="title d-flex flex-row justify-content-between">
												<div class="titles">
													<a class = "jobName" href="user/${offerInfo.getId()}.htm"><h3>${offerInfo.getHoTen()}</h3></a>
												</div>
											</div>
		                                    <h4 class="single-title">${offerInfo.getChucDanh()}</h4>
		                                    <p class = "show-read-more">
												${offerInfo.getGioiThieu()}
											</p>
		                                    <ul class="tags">
		                                    	<li><h5 style = "padding-right: 5px"><b>Kỹ năng:</b> </h5><li>
		                                    	<c:forEach var ="skill" items = "${offerInfo.getSkills()}" varStatus="skillStt">
		                                    		<li>
														<a>${skill.getSkills().getSkill()}
															<c:choose>
																<c:when test="${skillStt.count < offerInfo.getSkills().size()}">
																	, 
																</c:when>
															</c:choose>
														</a>
													</li>
		                                    	</c:forEach>
		                                        
											</ul>
										</div>
		                                <div class = "thongTinChaoGia">
		                                    <dl>
		                                        <dt>Việc Đã Chào Giá</dt> <dd>${offerInfo.getChaoGia()}</dd>
		                                        <dt>Đã Hoàn Thành</dt> <dd>${offerInfo.getHoanThanh()}</dd>
		                                        <dt>Thu Nhập</dt> <dd>
		                                        <span><fmt:formatNumber type = "number" pattern = ",##0" value = "${offerInfo.getThuNhap()}" /> VNĐ</span>
		                                        
		                                        </dd>
		                                    </dl>
		                                    <c:choose>
		                                    	<c:when test="${userLogin != null && userLogin.getId() == userInfo.getId() }">
		                                    		<div style = "margin: left"><a href = "job/${jobInfo.getId()}/${offerInfo.getId()}.htm" class = "genric-btn info-border circle" style="font-size: 14px;">Xem Chào Giá</a></div>
		                                    	</c:when>
		                                    </c:choose>
		                                    
		                                </div>
									</div>
		                            <!-- ================================================================= -->
							</c:forEach>


						</div>
					</div>	
				</div>	
			</section>
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



