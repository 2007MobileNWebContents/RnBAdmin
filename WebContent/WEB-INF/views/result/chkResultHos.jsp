<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div id="mid">
    	<div id="mid_tb">
    	<form action="/checkResult/search" method="post">
    		<input type="hidden" name="userId" value="${rOne.userId }">
	    		<fieldset>
		   			<legend>병원 기록 확인</legend>
			   			<div id="tb_div">
			   				<table>
			   					<thead>
			   						<tr>
			   							<th>병원 진료 날짜</th><th>헌혈 금지 항목</th>
			   						</tr>
			   					</thead>
			   					<tbody>
			   						<c:forEach items="${hlist }" var="hOne" varStatus="status">
			   						<tr>
			   							<td>${hOne.hpDate }</td><td>${hOne.hpDis }</td>
			   						</tr>
			   						</c:forEach>
			   					</tbody>
			   				</table>
		   				</div>
		   		</fieldset>
	   		</form>
    	</div>
    </div>
</body>
</html>