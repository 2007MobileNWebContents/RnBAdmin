<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
</head>
<style>
    #headerDiv {
        height: 100px;
        width: 1500px;
       
    }
    
    #imgDiv{
    	width: 200px;
    	height: 100px;
    	margin: 0 auto;
    }
    
    #headerImg { 
        display: block; 
        margin: 0 auto; 
        width: 200px;
        height: 100px;
    }
    #btn_login{
        float: right;
        margin-top: -1.5%;
        display: block;
        height: 30px;
        width: 65px;
        border-top-left-radius: 10%;
        border-top-right-radius: 10%;
        border-bottom-left-radius: 10%;
        border-bottom-right-radius: 10%;
        color: black;
        border-top: 3px solid #f5f5f5;
        border-left: 3px solid #f5f5f5;
        border-right: 3px solid #d9d9d9;
        border-bottom: 3px solid #d9d9d9;
        background-color: rgb(235, 235, 235);
        font-weight: bold;
    }
    
    #btn_logOut{
        float: right;
        margin-right: 10px;
        margin-top: -1.5%;
        display: block;
        height: 30px;
        width: 85px;
        border-top-left-radius: 10%;
        border-top-right-radius: 10%;
        border-bottom-left-radius: 10%;
        border-bottom-right-radius: 10%;
        color: black;
        border-top: 3px solid #f5f5f5;
        border-left: 3px solid #f5f5f5;
        border-right: 3px solid #d9d9d9;
        border-bottom: 3px solid #d9d9d9;
        background-color: rgb(235, 235, 235);
        font-weight: bold;
    }
    
    .loginDiv{
    	float:right;
    	margin-right: 5px;
    	margin-top: -1.2%;
    	height: 35px;
    	width: 130px;
    }
    
    .loginIcon{
    	float:left;
    	margin-left: 10px;
    	margin-top: -1.5%;
    	display:block;
    	height: 25px;
    	width: 25px;
    	
    }
    
    #btn_join{
        float: right;
        margin-right: 10px;
        margin-top: -1.5%;
        display: block;
        height: 30px;
        width: 85px;
        border-top-left-radius: 10%;
        border-top-right-radius: 10%;
        border-bottom-left-radius: 10%;
        border-bottom-right-radius: 10%;
        color: black;
        border-top: 3px solid #f5f5f5;
        border-left: 3px solid #f5f5f5;
        border-right: 3px solid #d9d9d9;
        border-bottom: 3px solid #d9d9d9;
        background-color: rgb(235, 235, 235);
        font-weight: bold;
    }
</style>
<body>
	<div id="headerDiv">
	 	<div id="imgDiv"><a href="/index.jsp"><img src="/images/R&B%20CI.png" id="headerImg"></a></div>
  	 	<c:if test="${sessionScope.member eq null }">
		    <input type="button" onclick="location.href='/login/admin'" id="btn_login" value="로그인"></input>
	 	</c:if>
		<c:if test="${sessionScope.member ne null }"> 
		    <input type="button" onclick="location.href='/member/logout'" id="btn_logOut" value="로그아웃">
		    <div class="loginDiv"><img src="/images/login.png" class="loginIcon"><span>&nbsp;&nbsp;${sessionScope.member.userName }님</span></div>
	    </c:if>
	</div>
</body>
</html>