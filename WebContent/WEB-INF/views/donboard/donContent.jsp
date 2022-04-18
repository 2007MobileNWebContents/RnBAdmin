<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헌혈증 기부게시판</title>
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

/* 덧글 부분 */
#commentBoard {
	clear: both;
	border-collapse: collapse;
	border: 1px solid #ddd;
	width: 1100px;
	margin-left: 60px;
	margin-top: 40px;
}
#commentContent{
	margin-top:10px;
	width:900px;
	height: 30px;
	margin-left:50px;
}
#writeComment {
	float: right;
	margin-top: 15px;
	margin-right:40px;
	
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
					<!-- 페이지 제목-->
					<div id="title">
						<ul>
							<h2>헌혈증 기부게시판</h2>
							<br>

						</ul>
					</div>

					<!-- content -->
					<form action="test()">
						<input type="hidden" id="noticeNo" value="${content.dbNo }">
						<input type="hidden" id="userId" value="${content.userId }">
						<div id="mainboard">
							<table class="table table-striped">
														
								<thead>
									<tr>
										<th colspan="3">&nbsp;&nbsp;글제목 : ${content.dbTitle }</th>
									</tr>
								</thead>

								<tbody>
									<tr>
										<td style="height:40px;">글번호 : ${content.dbNo }</td>
										<td style="height:40px;">작성자 : ${content.userId }</td> 
										<td style="height:40px;">작성일 : ${content.dbDate }</td>
									</tr>
									<tr>
										<td colspan="3" style="height:520px;">${content.dbCont }</td>
									</tr>
								</tbody>

						
								

							</table>
						</div>

					</form>
					<!-- 버튼-->
					<div id="btn_div">
						<c:if test="${sessionScope.member.userId != null }">
							<input class="notBtn" type="button" onclick="location.href='/donboard/delete?dbNo=${content.dbNo }'" value="삭제"> 
							<input class="notBtn2" type="button" onclick="location.href='/donboard/list'" value="목록">
						</c:if>
						<c:if test="${sessionScope.member.userId == null }">
							<input class="notBtn" type="button" onclick="location.href='/donboard/list'" value="목록">
						</c:if>
					</div>
					<br>
				<!-- 덧글 출력 폼 -->
					<div id="commentBoard">
						<div id="commentList">
							<table id="commentTable">
								<thead>
									<tr>
										<th id="comNo"
											style="width: 5%; background-color: lightgray; text-align: center;">번호</th>

										<th
											style="width: 90%; background-color: lightgray; text-align: center;">내용</th>

										<th id="userId"
											style="width: 10%; background-color: lightgray; text-align: center;">작성자</th>
									</tr>
								</thead>
								<tbody>

									<c:forEach items="${dcList }" var="comment" varStatus="index">
										<tr>
											<td style="text-align: center;">${index.count }</td>
											<td>${comment.commentContent }</td>
											<td style="text-align: center;">${comment.userId}</td>
											<td>
												

												<form action="/donboard/commentDelete" method="get">
													<input type="hidden" name="dbNo"
														value="${content.dbNo }"> 
														
														<input type="hidden"
														name="comNo" value="${comment.donNo}"> 
														<c:if test="${comment.userId  eq sessionScope.member.userId}" > 
														<input
														type="submit" value="delete">
														</c:if>
												</form>

											</td>
										</tr>
									</c:forEach>


								</tbody>
							</table>
						</div>



					<!--  덧글 입력 폼 -->
					<div id="commentInsert">
							<form action="/donboard/insertComment" method="post">
								<input type="hidden" id="noticeNo" name="noticeNo"
									value="${content.dbNo }">
					
									<!-- 세션 값이 null 아닐시 댓글작성가능 -->
									<%-- <c:if test="${session.userID != null }">
 --%>								<input type="text" name="boardCommentContent" id="commentContent"
										placeholder="댓글을 작성하세요"
										onKeyUp="javascript:fnChkByte(this,'100')">
								
									<script type="text/javascript">
									$(document).ready(function() {

										$('#commentContent').on('keyup',function() {
											if ($(this).val().length > 100) {
											$(this).val(
											$(this).val().substring(0,100));
															
											}
										});
									});
									</script>

									

									<button class="com_submit" type="submit" id="writeComment">댓글작성</button>
									<hr>
									<div class="box-footer"></div>
								
							</form>
						</div>


				</div>

			</section>

			<footer id="footers"></footer>
		</div>
	</div>


</body>
</html>