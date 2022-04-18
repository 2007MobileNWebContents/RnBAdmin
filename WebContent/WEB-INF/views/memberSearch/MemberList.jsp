<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>회원 정보 조회</title>
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
 <link href="../../css/MemberList.css" rel="stylesheet" type="text/css">
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


					<div id="right_whole">
						<div id="right_head">
							<h1 style="font-weight: 900;">&nbsp;&nbsp;회원 정보 조회</h1>
						</div>
						<div id="right_middle">
							<form action="/member/search" method="post">
								<span class="searchTag">아이디 / 이름 입력</span> <input
									class="searchBox" type="text" name="idName"> <input
									class="searchBtn" type="submit" value="검색">
							</form>
							<br>
							<br>
							<table class="table">
								<thead>
									<tr>
										<th class="noTh">No.</th>
										<th class="nameTh">이름</th>
										<th class="idTh">ID</th>
										<th class="bdTh">생년월일</th>
										<th class="aboTh">혈액형</th>
										<th class="phoneTh">전화번호</th>
										<th class="emailTh">E-mail</th>
										<th class="addrTh">주소</th>
										<th class="delTh">삭제</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${memList }" var="mem" varStatus="status">
										<tr>
											<td class="count">${status.count }</td>
											<td class="userName">${mem.userName }</td>
											<td class="userId">${mem.userId }</td>
											<td class="userBD">${mem.userBD }</td>
											<td class="ABO">${mem.userABO }</td>
											<td class="phone">${mem.userPhone }</td>
											<td class="email">${mem.userEmail }</td>
											<td class="addr">${mem.userAddrRoad }  ${mem.userAddrDetail }</td>
											<td class="delBtn"><input type="button"  value="삭제" onclick="location.href='/memInfo/delete?userId=${mem.userId}'"></td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="8" align="center">${pageNavi }</td>
									</tr>
								</tbody>
							</table>
						</div>
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