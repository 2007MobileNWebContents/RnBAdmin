<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객의소리</title>
<script src="https://cdn.ckeditor.com/4.15.0/standard-all/ckeditor.js"></script>
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

#section_right {
	position: absolute;
	display: block;
	width: 1200px;
	height: 1050px;
	margin-left: 300px;
}


.search1{
	height: 32px;
}
.search2{
	height: 25px;
}

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

/* textBox css */
.textBox{
	width:90%;
	margin-left: 5%;
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
							<h1 style="font-weight: 900;">&nbsp;&nbsp;고객의 소리</h1>
							<br>

							<h3 style="font-weight: 900;">&nbsp;&nbsp;&nbsp;&nbsp;고객의소리 수정</h3>
						</ul>
					</div>

					<form class="textBox" action="/memboard/modify" method="post">
							<!-- <select name="subject">
							<option value='' selected>==선택==</option>
							<option value='compliment'>[칭찬합니다]</option>
							<option value='complain'>[불만이에요]</option>
						</select> --> <input type="text" size="" placeholder="제목을 작성하시오" name="title"value="${memboard.mbTitle }"><br>
						<br>
						<hr style="border: 3px solid #168">
						<textarea id="editor" rows="100" cols="100"
							placeholder="내용을 작성하시오" name="content" >${memboard.mbCont }
		</textarea>
						<script type="text/javascript">
			CKEDITOR.replace('editor', {
				height : 500
							});
						</script>
						<!-- 버튼-->
					<div id="btn_div">
						<input class="notBtn" type="button" onclick="location.href='/memboard/delete?mbNo=${memboard.mbNo}'" value="삭제"></input> 
						<input class="notBtn2" type="submit" value="수정"></input> 
						<input class="notBtn2" type="button" onclick="location.href='/memboard/list'"
							value="목록"></input>
					</div>
					</form>
					
				</div>

			</section>
			<br>
			<br>
			<footer id="footers"></footer>
		</div>
	</div>
</body>
</html>