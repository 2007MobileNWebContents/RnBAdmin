<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헌혈 응원의 한마디</title>
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

		//sectionleft html삽입
		$("#section_left").load("/common/htmlTag/sideNav.html");
	});
</script>

<style type="text/css">
.table {
	border-collapse: collapse;
	border-top: 3px solid #168;
	margin-left: 40px;
	margin-top: 30px;
	position: absolute;
}

.table th {
	color: #168;
	/*background: #f0f6f9;*/
	background: #dcdcdc;
	text-align: center;
}

.postNum {
	padding: 10px;
	border: 1px solid #ddd;
	text-align: center;
	width: 50px;
	height: 10px;
}

.postCont {
	padding: 10px;
	border: 1px solid #ddd;
	text-align: center;
	width: 685px;
	height: 10px;
}

.writer {
	padding: 10px;
	border: 1px solid #ddd;
	text-align: center;
	width: 100px;
	height: 10px;
}

.date {
	padding: 10px;
	border: 1px solid #ddd;
	text-align: center;
	width: 130px;
	height: 10px;
}

.table th:first-child, .table td:first-child {
	border-left: 0;
}

.table th:last-child, .table td:last-child {
	border-right: 0;
}

.table tr td:first-child {
	text-align: center;
}

.table tbody tr:hover {
	background-color: #eeeeee;
	color: #168;
}

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
	height: 1000px;
	margin-left: 300px;
	padding-left: 20px;
}

#write_btn {
	position: absolute;
	right: 100px;
	height: 28px;
	width: 40px;
}

.textBox {
	margin-left: 35px;
	padding-left: 15px;
}

#reg_text {
	position: absolute;
	width: 850px;
	padding: 5px;
	text-align: center;
	margin-left: 40px;
	margin-top: 15px;
}

#reg_btn {
	position: absolute;
	margin-left: 920px;
	margin-top: 15px;
	height: 29px;
	width: 50px;
	float: left;
}

.delBtn{
	height: 30px;
	width: 50px;
}

.util{
   padding: 10px;
   border: 1px solid #ddd;
   text-align: center;
   width: 40px;
   height: 10px;
}

#right_insertBox{
	width
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
					<div id="left_mainNav">
						<h1 style="margin-left: 20px; position: absolute;">커뮤니티</h1>
					</div>
					<div class="left_subNav">
						<a href="/donboard/list" class="subA">헌혈증 기부</a>
					</div>
					<div class="left_subNav">
						<a href="/cheer/list" class="subA">헌혈 응원의 한마디</a>
					</div>
				</div>
				<div id="section_right">
					<div id="right_head">
						<h1 style="font-weight: 900;">헌혈 응원의 한마디</h1>
					</div>
					<div id="right_insertBox">
					<br>
					<form action="/cheer/write" method="post">
						<input id="reg_text" type="text" maxlength="30" name="content"
							 placeholder="응원의 한마디를 입력해주세요~" style="width:900px; height: 20px;"> <input
							id="reg_btn" type="submit" value="등록" id="write_btn" style="margin-left:970px; margin-top:16px; height: 33px;"> <br>
						
					</form>
					</div>
					<br>
					<table class="table">
						<tr>
							<th class="postNum">글번호</th>
							<th class="postCont">글내용</th>
							<th class="writer">글쓴이</th>
							<th class="date">작성일</th>
							<th class="util">삭제</th>
						</tr>

						<c:forEach items="${nList }" var="cheer" varStatus="index">
							<tr>
								<td class="postNum">${cheer.cbNo }</td>
								<td class="postCont">${cheer.cbCont }</td>
								<td class="writer">${cheer.userId }</td>
								<td class="date">${cheer.cbDate }</td>
								<c:if test="${sessionScope.member.userId ne null}">
									<td class="util"><input class="delBtn" onclick="location.href='/cheer/del?postNo=${cheer.cbNo}'" type="submit" value="삭제"></td>
								</c:if>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="4" align="center">${pageNavi }
						</tr>

					</table>
				</div>
			</section>
			<br> <br>
			<footer id="footers"></footer>
		</div>
	</div>
</body>
</html>