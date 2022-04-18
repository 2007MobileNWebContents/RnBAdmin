<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
<!DOCTYPE html>
<html>
<head>
<style>

.loginBox{
	position: absolute;
	width: 450px;
	height: 450px;
	left:40%;
	top: 20%;
}

.hea3 {
	font-size : 25px;
	background-color: rgb(243, 243, 243);
	display:inline-block;
	width: 450px;	
	height: 40px;
	text-align:center;
}
.tab{
}
.id{
	height: 50px;
	width: 380px;
	font-size : 30px;
	margin-left: 15px;
	margin-top: 10px;
}

.password{
	height:50px;
	width: 380px;
	font-size : 30px;
	margin-left: 15px;
	margin-top: 15px;
}
	

.fa-user{
	font-size : 40px;	
	margin-top: 10px;
}

.fa-key{
	font-size : 40px;
	margin-top: 15px;
}
.log{
	height:30px;
}
.select{
	text-align: right;
    font-size: 20px;
	
}
.RB1 {
	width: 200px;
	height: 110px;
	margin-left: 125px;
}
.RB2 {
	width: 200px;
	height: 110px;
}
.loginBtn {
	background-color:rgb(243, 243, 243);
	width: 450px;
	height: 70px;
	font-size: 25px;
	font-weight:bold;
	border: none;
	margin-top: 30px;
}

.enrollTag{
	float: right;
	margin-top: 10px;
    font-size: 17px;
    text-decoration: none;
	color: black;
}
</style>

 <%-- <link rel="stylesheet" href="<%=request.getContextPath() %>/common/login/login.css"> --%>
 <script src="https://kit.fontawesome.com/a332c1cec6.js" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>관리자 로그인</title>
<link rel="shortcut icon" type="image/x-icon" href="/images/R&B%20CI.png">
</head>
<body>
	
	
	<c:if test="${member eq null}">
	
	<div class="loginBox">
	<div class="RB1"><a href="/index.jsp"><img src="/images/R&B%20CI.png" class="RB2"></a></div>
	<br>
	<h2 class=hea3 >관리자 Login</h2>
	<form action="/admin/login" method="post">
	<table class="tab">
		<tr>
			<td><i class="fas fa-user"></i></td>
			<td><input type="text" placeholder="  아이디" class="id" name="adminId"></td>
		</tr>
		<tr>
			<td><i class="fas fa-key"></i></td>
			<td><input type="password" placeholder="  비밀번호" class="password" name="adminPwd"></td>
		</tr>
		<tr>
			<td class="log" colspan=2 >
				<input type="submit" value="로그인" class="loginBtn">
			</td>
		</tr>
	</table>
	</form>
		<!-- <a href="/user/enroll" class="enrollTag">회원가입</a> -->
	</div>
	</c:if>
	
</body>
</html>