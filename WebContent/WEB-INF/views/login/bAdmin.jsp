<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="member.model.vo.Member"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>헌혈 검사결과 입력</title>
<link rel="shortcut icon" type="image/x-icon" href="/images/R&B%20CI.png">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<%
	Member member = (Member)session.getAttribute("member");
%>
<script>
	function checkz(){
			var BD = RegExp(/^(19|20)[0-9]{2}(0[1-9]|1[1-2])(0[1-9]|[1-2][0-9]|3[0-1])$/);
			var phone = RegExp(/^01[01][0-9]{7,8}$/);
	if(!BD.test($("#BD").val())){
		alert("생년월일을 다시 입력해주세요")
		$("#BD").focus();
		return false;
	}
	if(!phone.test($("#phone").val())){
		alert("폰번호를 다시 입력해주세요")
		$("#phone").focus();
		return false;
	}
	}
	
</script>
<script>
	function question(){
	return confirm("정말 탈퇴하시겠습니까?");
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
 <!-- <link href="/common/memberSearch/MemberList.css" rel="stylesheet" type="text/css"> -->
<style>
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
   /*border : 1px solid black;*/
   margin-left: 300px;
}

/*----------------------------------------------*/
/*section-right꾸미기*/
.table {
   border-collapse: collapse;
   border-top: 3px solid #168;
   margin-left: 10%;
   margin-top: 30px;
   position: absolute;
}

.table th {
   color: #168;
   /*background: #f0f6f9;*/
   background: #dcdcdc;
}

.table th, .table td {
   padding: 15px;
   border: 1px solid #ddd;
   width: 150px;
   height: 10px;
}

.table th:first-child, .table td:first-child {
   border-left: none;
   border-right: none;
}

.table th:last-child, .table td:last-child {
border-left: none;
   border-right: none;
}

.td_label{
   text-align: left;
}

.table tr td:first-child {
}

.table tbody tr:hover {
   background-color: #eeeeee;
   color: #168;
}


.inputBox{
   height: 20px;
   width: 300px;
}

#btn2{
   height: 30px;
   margin-left: 10px;
}

.searchBox {
   float: left;
   position: absolute;
   margin-left: 840px;
   display: block;
   margin-top: 14px;
   height: 30px;
   width: 300px;
}

.searchBtn {
   position: absolute;
   float: left;
   height: 37px;
   width: 50px;
   margin-top: 14px;
   margin-left: 1140px;
   font-size: 15px;
   font-weight: bolder;
}

.searchTag {
   position: absolute;
   float: left;
   display: block;
   height: 37px;
   font-size: 20px;
   font-weight: bold;
   margin-left: 650px;
   margin-top: 17px;
}
.td_label>span[class=idpo]{
   position:absolute;
   left : 113px;
}
.td_label>input[name=resId] 
 { 
            position:absolute;
            left:205px;
}
.td_label>input[name=search]{
position:absolute;
left:396px;
}
.td_label{
   li :none;
}
ul{
list-style:none;
}

</style>

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
                     <h1 style="font-weight: 900;">&nbsp;&nbsp;헌혈검사 정보 입력</h1>
                  </div>
                  <div id="right_middle">
                     <br>
                     <br>
                     <form action="/ResResultSelect" method="post" >
                     <ul>
                     	<li class="td_label"><span class="idpo">아이디 검색</span> <input style="margin-left:10px; height: 25px; width: 200px;" type="search" name="resId" value="${resId}"> <input style="height: 25px; margin-left:25px;" type="submit" value="검색" name="search"></li>
                     </ul>
                     </form>
                     <form action="/ResResultInsert" method="post">
                     <input type="hidden" name="resId" value="${resId}">
                        
                        <table class="ui single tdne table">
                        <tbody>
                        <tr>
                        <td class="td_label">예약 번호</td>
                        <td style="width: 200px;">예약 날짜</td>
                        <td style="width: 200px;"><span style="font-size: 16px;">상태</span> <span style="font-size: 10px;">&nbsp;(완료 = 헌혈완료)</span></td>
                     </tr>
                        <c:forEach var="rp" items="${resList}">
                        <tr>
                        <td>
                        <label for="rpNo">
                        <input type="radio" value="${rp.rpNo}" name="rpNo">
                        ${rp.rpNo}
                        </label>
                        </td>
                        <td>
                        ${rp.rpDate}
                        </td>
                        <td colspan="2">
                        ${rp.rpState}
                        </td>
                        </tr>
                        </c:forEach>
                        <tr>
                        <td class="td_label">B형간염</td>
                        <td colspan="2" class="td_input">
                        <input type="radio" value="+" name="resB" id="Bplus"> 
                        <label for="Bplus">양성</label> 
                        <input type="radio" value="-" name="resB" id="Bminus">
                        <label for="Bminus">음성</label> 
                        </td>
                        </tr>
                        <tr>
                        <td class="td_label">C형간염</td>
                        <td colspan="2" class="td_input">
                        <input type="radio" value="+" name="resC" id="Cplus"> 
                        <label for="Cplus">양성</label> 
                        <input type="radio" value="-" name="resC" id="Cminus">
                        <label for="Cminus">음성</label> 
                        </td>
                        <tr>
                           <td class="td_label">ALT</td>
                           <td colspan="2" class="td_input"><input class="inputBox" type="number" name="resAlt" required>
                           </td>
                        </tr>
                        <tr>
                           <td class="td_label">총단백</td>
                           <td colspan="2" class="td_input"><input class="inputBox" type="number" name="resPro" required>
                           </td>
                        </tr>
                        <tr>
                           <td class="td_label">AST</td>
                           <td colspan="2" class="td_input"><input class="inputBox" type="number" name="resAst" required>
                           </td>
                        </tr>
                        <tr>
                           <td class="td_label">클레스테롤</td>
                           <td colspan="2" class="td_input"><input class="inputBox" type="number" name="resCol" required>
                           </td>
                        </tr>
                        <tr>
                           <td colspan="3" align="center"><input id="btn2" type="submit" value="입력하기"><input
                              type="reset" id="btn2" value="취소">
                           </td>
                        </tr>
                     </tbody>
                     </table>
                     </form>   
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