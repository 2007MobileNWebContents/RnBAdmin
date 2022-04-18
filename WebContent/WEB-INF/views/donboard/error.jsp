<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>-->


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러메시지</title>

<!--  <script src="//code.jquery.com/jquery-1.11.0.min.js"></script> 
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->

<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>

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

<style type="text/css">
/* 시멘틱 구조영역 스타일 */
#wrapping1 {
	width: auto;
	height: 1410px;
	margin: 0 auto;
	background-image: linear-gradient(to top, #e3eeff 0%, #e3eeff 20%, #f3e7e9 100%);
}

#wrapping2 {
	width: 1550px;
	height: 1410px;
	margin: 0 auto;
	background-color: white;
}

header {
	position: static;
	width: 1500px;
	margin: 0 auto;
}

nav {
	position: relative;
	width: 1500px;
	z-index: 3;
	margin: 0 auto;
}

section {
	width: 1500px;
	height: 1050px;
	padding-top: 20px;
	margin: 0 auto;
	z-index: 1;
	display: block;
}

#section_left {
	position: absolute;
	display: block;
	width: 300px;
	height: 1000px;
	/*border : 1px solid black;*/
	border-right: 1px solid black;
	box-sizing: border-box;
	float: left;
}

#section_right {
	position: absolute;
	display: block;
	width: 1200px;
	height: 1000px;
	/* 	border: 1px solid black; */
	margin-left: 300px;
	clear: both;
}
/* 게시글 리스트 */
#title {
	margin-left: 30px;
}

#mainboard table {
	border-collapse: collapse;
	border: 1px solid #ddd;
	border-top: 3px solid #168;
	width: 1100px;
	height: 600px;
	margin-left: 60px;
}

.table tr, .table td {
	padding: 10px;
	border: 1px solid #ddd;
	text-align: left;
	width: 155px;
	height: 45px;
}

#mainboardt thead {
	color: #168;
	padding: 10px;
}

#mainboard thead th {
	height: 40px;
}

#mainboard tbody tr:hover {
	background-color: #eeeeee;
	color: firebrick;
}

A:link {
	text-decoration: none;
	color: #000000;
}

A:visited {
	text-decoration: none;
	color: #000000;
}

A:active {
	text-decoration: none;
	color: #000000;
}

A:hover {
	text-decoration: none;
	color: firebrick;
}
/* 글쓰기 버튼 */

/* 버튼 */
#btn {
	float: right;
	display: inline-block;
	margin-right: 52px;
	margin-top: 20px;
}

.btn {
	width: 100px;
	height: 40px;
	line-height: 20px;
	text-align: center;
	display: inline-block;
	margin: 5px;
	background-color: gray;
	border: 2px solid gray;
	clear: both;
}

.btn a {
	margin-top: 10px;
	font-size: 1.5em;
	display: block;
	height: 40px;
	color: white;
	text-decoration: none;
}

.btn:hover {
	background-color: white;
	border: 2px solid firebrick;
}

.btn:hover a {
	color: firebrick;
}

/* pagination */
#page {
	position: relative;
	text-align: center;
	margin-top: 60px;
	height: 30px;
	align-content: center;
	clear: both;
}

#page>li {
	display: inline-block;
	align-content: center;
}

#page a {
	color: gray;
	font-size: 1.5em;
	background-color: white;
	margin-right: 5px;
	text-decoration: none;
}

#page>li:hover {
	/*font-style: firebrick;*/
	font-weight: bold;
	transform: scale(1.3);
}

#page a:hover {
	color: firebrick;
}
</style>
</head>
<body>
	<div id="wrapping1">
		<div id="wrapping2">
			<header id="headers"></header>
			<br>
			<nav id="navs"></nav>
			<br>


			<section>
				<div id="section_left"></div>
				<div id="section_right">
					<!-- 페이지 제목-->
					<div id="title">
						<ul>
						<h1>ERROR</h1>
							<h2>요청하신 작업을 수행하지 못했습니다.</h2>
							<br>

							<h3>처음부터 다시 시도해주십시오</h3>
						</ul>
					</div>

					<!-- content -->

					<!-- 버튼-->
					<div id="btn">

						<div id="modify_btn" class="btn">
							<a href="/">메인</a>
						</div>

					</div>

					<!--  덧글 입력 폼 -->
					<!-- <ul id="commentList">
								</ul> -->
				</div>

			</section>
		</div>

	</div>
	<footer id="footers"></footer>


</body>
</html>