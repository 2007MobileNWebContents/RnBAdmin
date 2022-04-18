<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link rel="shortcut icon" type="image/x-icon" href="/images/R&B%20CI.png">

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
	height: 1100px;
	padding-top: 20px;
	margin: 0 auto;
	z-index: 1;
	display: block;
}

#section_left {
	position: absolute;
	display: block;
	width: 300px;
	height: 1050px;
	border-right: 1px solid black;
	box-sizing: border-box;
	float: left;
}

/* 왼쪽 네브바 css */
#left_mainNav {
	position: relative;
	background-color: #f3e7e9;
	display: block;
	width: 275px;
	height: 80px;
	margin-top: 0;
}

.left_subNav {
	position: relative;
	border-bottom: 2.7px solid #cfe2ff;
	display: block;
	width: 275px;
	height: 70px;
}

.subA:hover {
	background-color: #e3eeff;
}

.subA {
	text-decoration: none;
	color: black;
	font-size: 17px;
	font-weight: bold;
	display: block;
	padding: 25px;
}

#section_right {
	position: absolute;
	display: block;
	width: 1200px;
	height: 1050px;
	margin-left: 300px;
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
	margin-left: 60px;
}

.table tr, .table td {
	padding: 10px;
	border: 1px solid #ddd;
	text-align: left;
	width: 155px;
	height: 45px;
}

.table td {
	border: 1px solid #ddd;
	height: 40px;
}

#mainboardt thead {
	color: #168;
	padding: 10px;
}

#mainboard thead th {
	height: 40px;
}

/* #mainboard tbody tr:hover {
	background-color: #eeeeee;
	color: firebrick;
} */
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

/* A:hover {
	text-decoration: none;
	color: firebrick;
} */
/* 글쓰기 버튼 */
#submit_btn {
	width: 100px;
	height: 40px;
	line-height: 20px;
	float: right;
	text-align: center;
	margin-right: 100px;
	margin-top: 20px;
	background-color: gray;
	border: 2px solid gray;
	clear: both;
}

.notBtn{
	float: right;
	height: 30px;
	width: 55px;
	margin-top: 20px;
	margin-right: 45px;
}
.notBtn2{
	float: right;
	height: 30px;
	width: 55px;
	margin-top: 20px;
	margin-right: 10px;
}

#submit_btn a {
	margin-top: 10px;
	font-size: 1.5em;
	display: block;
	height: 40px;
	color: white;
	text-decoration: none;
}

/* #submit_btn:hover {
	background-color: white;
	border: 2px solid firebrick;
} */

/* #submit_btn:hover a {
	color: firebrick;
} */
/*commentContent*/
/* #commentBoard {
	width: 1100px;
	height: 300px;
	margin-left: 60px;
	clear: both;
	position: relative;
	border: 1px solid #ddd;
}

#commentContent{
	width: 900px;
	height: 30px;
}

#commentTable {
	width: 1000px;
	table-layout: fixed;
}

#commentList {
	overflow: scroll;
	height: 200px;
}

#commentList thead {
	height: 45px;
}

#commentList tr, #commentList td {
	border: 1px solid #ddd;
} */
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
				<div id="section_left">
					<div id="left_mainNav">
						<h1 style="margin-left: 20px; position: absolute;">게시판</h1>
					</div>
					<div class="left_subNav">
						<a href="/memboard/list" class="subA">고객의 소리</a>
					</div>
					<div class="left_subNav">
						<a href="/faq/list" class="subA">FAQ</a>
					</div>
					<div class="left_subNav">
						<a href="/notice/list" class="subA">공지사항</a>
					</div>
				</div>
				<div id="section_right">
					<!-- 페이지 제목-->
					<div id="title">
						<h2>공지사항</h2>
						<br>
					</div>

					<!-- content -->
					<form action="test()">
						<input type="hidden" id="noticeNo" value="${content.noticeNo }">
						<input type="hidden" id="userId" value="${content.userId }">
						<div id="mainboard">
							<table class="table table-striped">
								<thead>
									<tr>
										<th colspan="3">&nbsp;&nbsp;글제목 : ${content.title }</th>
									</tr>
								</thead>

								<tbody>
									<tr>
										<td style="height: 40px;">글번호 : ${content.noticeNo }</td>
										<td style="height: 40px;">작성자 : ${content.userId }</td>
										<td style="height: 40px;">작성일 : ${content.regDate }</td>
									</tr>
									<tr>
										<td colspan="3" style="height: 520px;">${content.content }</td>
									</tr>
								</tbody>

							</table>
						</div>

					</form>
					<!-- 버튼-->
					<div id="btn_div">
						<c:if test="${sessionScope.member.userId ne null }">
						<input class="notBtn" type="button" onclick="location.href='/notice/delete?noticeNo=${content.noticeNo}'" value="삭제"> 
						<input class="notBtn2" type="button" onclick="location.href='/notice/modifyForm?noticeNo=${content.noticeNo}'" value="수정">
						<input class="notBtn2" type="button" onclick="location.href='/notice/list'"	value="목록"></input>
						</c:if>
						<c:if test="${sessionScope.member.userId eq null }">
						<input class="notBtn" type="button" onclick="location.href='/notice/list'"	value="목록"></input>
						</c:if>
					</div>

					<div class="box-footer"></div>
				</div>


	</section>

	<footer id="footers"></footer>
	</div>
	</div>


</body>
</html>