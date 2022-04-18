<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>문진 결과 조회</title>
<link rel="shortcut icon" type="image/x-icon" href="/images/R&B%20CI.png">
<link rel="stylesheet" href="/css/ChkResultStyle.css" type="text/css">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script>
	function popup(crCode) {
		window.open("/checkResult/detail?crCode="+crCode,"상세 결과 확인", "width=1000, height=500, left=100, top=50");
		document.userId.submit();
	}
</script>
<script type="text/javascript">
   $(document).ready(
         
      // 시멘틱 구조 불러오기
      function() {

         // header.html 삽입 script
         $("#headers").load("/common/htmlTag/header.jsp");

         // nav.html 삽입 script
         $("#navs").load("/common/htmlTag/adminNav.jsp");

         // footer.html 삽입 script
         $("#footers").load("/common/htmlTag/footer.html");

      });
</script>
</head>
<body>
	<c:if test="${sessionScope.member ne null }">
		<div id="wrapping1">
			<div id="wrapping2">
				<header id="headers"></header>
				<br>
				<nav id="navs"></nav>
				<br>
	
				<section>
					<div id="section_left">
						<div id="left_mainNav"><h1 style="margin-left:20px; position: absolute;">회원관리</h1></div>
				<div class="left_subNav"><a href="/member/list" class="subA">회원 정보 조회</a></div>
	            <div class="left_subNav"><a href="/bAdmin/form" class="subA">헌혈 검사 결과 입력</a></div>
	            <div class="left_subNav"><a href="/result/main" class="subA">문진 결과 조회</a></div>
	            <div class="left_subNav"><a href="/admin/enroll" class="subA">관리자 추가</a></div>
					</div>
					<div id="section_right">
						<div id="main">
							<%@ include file="chkResultSearch.jsp" %>
							<%@ include file="chkResultHos.jsp" %>
						</div>
					</div>
	
				</section>
				<br> <br>
	
				<footer id="footers"></footer>
			</div>
		</div>
	</c:if>
	<c:if test="${sessionScope.member eq null }">
		<jsp:forward page="/login/admin"></jsp:forward>
	</c:if>
</body>
</html>