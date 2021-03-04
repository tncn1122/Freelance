<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% pageContext.setAttribute("newLineChar", "\n"); %>
	
	<!DOCTYPE html>
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
		
<header id="header" id="home">

    <div class="container">
    	<div class="row align-items-center justify-content-between d-flex">
	      <div id="logo">
	        <a href="index.htm"><img src="img/logo.png" alt="" title="" /></a>
	      </div>
	      <nav id="nav-menu-container">
	        <ul class="nav-menu">
	          <li class="menu-active"><a href="index.htm">Trang Chủ</a></li>
	          <li><a href="search.htm">Tìm Việc Làm</a></li>
	          
         	 <c:choose>
		         <c:when test="${userLogin.account.acc == 'admin'}">
						<li><a href="admin/skills.htm">Quản Trị Trang Web</a></li>
				</c:when>
			</c:choose>
         	 <c:choose>
		         <c:when test="${userLogin.getType() == false}">
						<li><a href="job/post.htm">Đăng Dự Án</a></li>
				</c:when>
			</c:choose>
				
			<c:choose>
					<c:when test="${userLogin == null}">
						<li><a class="ticker-btn" href="signup.htm">Đăng Kí</a></li>
	          			<li><a class="ticker-btn" href="login.htm">Đăng Nhập</a></li>	
					</c:when>
					<c:otherwise >
						<li class="menu-has-children avatar"><img src="${userLogin.getAvt()}" alt=""><a href="user/${userLogin.getId()}.htm">${userLogin.getHoTen()}</a>
				            <ul>
								<li><a href="user/${userLogin.getId()}/account.htm">Cập nhật thông tin</a></li>
								<!-- <c:choose>
							         <c:when test="${userLogin.getType() == false}">
											<li><a href="search.htm">Danh sách dự án</a></li>
									</c:when>
									<c:otherwise>
											<li><a href="search.htm">Danh sách chào giá</a></li>
									</c:otherwise>
								</c:choose> -->
								
								<li><a href="user/${userLogin.getId()}/rating.htm">Nhận xét</a></li>
								<li><a href="logout.htm">Đăng xuất</a></li>
				            </ul>
				         </li>
					</c:otherwise>
			</c:choose>
	          

	          		          				          
	        </ul>
	      </nav><!-- #nav-menu-container -->		    		
    	</div>
    </div>
  	</header>

