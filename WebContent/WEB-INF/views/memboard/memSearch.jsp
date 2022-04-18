<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객의소리 검색 결과</title>
<link rel="shortcut icon" type="image/x-icon" href="/images/R&B%20CI.png">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


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

#section_right {
	position: absolute;
	display: block;
	width: 1200px;
	height: 1050px;
	/* 	border: 1px solid black; */
	margin-left: 300px;
}

/* 왼쪽 네브바 css */
#left_mainNav{
	position: relative;
	background-color: #f3e7e9;
	display: block;
	width: 275px;
	height: 80px;
	margin-top: 0;
}

.left_subNav{
	position: relative;
	border-bottom: 2.7px solid #cfe2ff;
	display: block;
	width: 275px;
	height: 70px;
	
}

.subA:hover{
	background-color: #e3eeff;
}
.subA{
	text-decoration: none;
	color: black;
	font-size: 17px;
	font-weight: bold;
	display: block;
	padding: 25px;
}



/* 검색기 */
#search {
	float: right;
	margin-right:40px;
	margin-bottom: 50px;  
}

.search1{
	height: 32px;
}
.search2{
	height: 25px;
}
.search3{
	top:15px;
	height: 32px;
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

.table tr {
	padding: 10px;
	border: 1px solid #ddd;
	text-align: center;
	width: 155px;
	height: 45px;
}

.table td{
	border: 1px solid #ddd;
	height:60px;
}

#mainboard thead {
	color: #168;
	padding: 10px;
}

#mainboard thead th {
	height: 40px;
}

#mainboard tbody tr:hover {
	background-color: #eeeeee;
	/* color: firebrick; */
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

/* A:hover {
	text-decoration: none;
	color: firebrick;
} */
/* 글쓰기 버튼 */
#submit_btn {
	width: 80px;
	height: 30px;
	line-height: 20px;
	float: right;
	text-align: center;
	margin-right: 42px;
	margin-top: 20px;
	background-color: gray;
	border: 2px solid gray;
	clear: both;
}

#submit_btn a {
	margin-top: 5px;
	font-size: 20px;
	display: block;
	height: 40px;
	color: white;
	text-decoration: none;
}

.notBtn{
	float: right;
	height: 30px;
	width: 55px;
	margin-top: 20px;
	margin-right: 45px;
}

/* #submit_btn:hover {
	background-color: white;
	border: 2px solid firebrick;
}

#submit_btn:hover a {
	color: firebrick;
} */
/* pagination */

 #page {
        position: relative;
        text-align: center;
       
        margin-bottom: 10px;
        height: 30px;
        align-content: center;
        clear: both;
    }
    #page>li {
        display: inline-block;
        align-content: center;
    }
    #page a {
    display: inline-block;
        color: gray;
        font-size: 1.5em;
        background-color: white; 
        margin-right: 5px;
    }
    #page>li:hover{
        /*font-style: firebrick;*/
        font-weight: bold;
        /* transform: scale(1.3); */
    }
   /*  #page a:hover {
        color: firebrick;
    } */
    
    .search2{
	height: 25px;
	margin-left: 40px;
}
.search3{
	top:15px;
	height: 32px;
}

.notBtn{
	float: right;
	height: 30px;
	width: 55px;
	margin-top: 20px;
	margin-right: 45px;
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
				<div id="section_left">
					<div id="left_mainNav"><h1 style="margin-left:20px; position: absolute;">게시판</h1></div>
					<div class="left_subNav"><a href="/memboard/list" class="subA">고객의 소리</a></div>
					<div class="left_subNav"><a href="/faq/list" class="subA">FAQ</a></div>
					<div class="left_subNav"><a href="/notice/list" class="subA">공지사항</a></div>
				</div>
				<div id="section_right">
					<!-- 페이지 제목-->
					
					<div id="title">
						<ul>
							<h2>고객의소리검색 결과</h2>
							<br>

							
						</ul>
						
					</div>
					<br>					
					<form action="test()">

						<!-- 게시글 리스트-->
						<div id="mainboard">
							<table class="table table-striped">
								<thead>
									<tr>
										<th style="background-color: lightgray; text-align: center;">번호</th>

										<th style="background-color: lightgray; text-align: center;">제목</th>

										<th style="background-color: lightgray; text-align: center;">작성자</th>

										<th style="background-color: lightgray; text-align: center;">작성일</th>




									</tr>
								</thead>
								<tbody>

									<c:forEach items="${mList }" var="memboard" varStatus="index">
										<tr>
											<td>${memboard.mbNo }</td>

											<td><a href="/memboard/select?mbNo=${memboard.mbNo}">${memboard.mbTitle }</a></td>
											<td>${memboard.userId }</td>
											<td>${memboard.mbDate }</td>
										</tr>
									</c:forEach>


								</tbody>
							</table>
						</div>


					</form>
					<!-- 버튼-->
						<input class="notBtn" type="button" onclick="location.href='/memboard/writeForm'" value="글쓰기">

					<!-- pagination -->
					<div id="pagediv">

						<ul id="page">
							<li>${pageNavi}</li>
						</ul>

					</div>



				</div>

			</section>

			<footer id="footers"></footer>
		</div>
	</div>


</body>