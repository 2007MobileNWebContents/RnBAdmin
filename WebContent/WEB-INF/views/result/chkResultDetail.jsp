<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세부 결과 확인</title>
<link rel="shortcut icon" type="image/x-icon" href="/images/R&B%20CI.png">
<link rel="stylesheet" href="../../css/ChkResultStyle.css" type="text/css">
</head>
<body>

 	<div id="bottom">
    	<div id="bottom_tb">
    		 <form action="/checkResult/detail" method="post" name="detailform">
               <input type="hidden" name="crCode" value="${rOne.crCode }">
               		<fieldset>
               			<legend>세부 결과 확인</legend>
               				<table>
               					<thead>
               						<tr>
               							<th>날짜</th><th>몸상태</th><th>발열/감기</th><th>피어싱/문신</th><th>약물복용</th><th>국내방문</th><th>국외방문</th><th>가족이력</th>
               						</tr>
               					</thead>
               					<tbody>
               						<c:forEach items="${dlist }" var="dlist"  varStatus="status">
               						<tr>
               							<td>${dlist.crDate }</td>
               							<td><c:if test="${dlist.crBody eq 'Y'}">O</c:if>
                                			<c:if test="${dlist.crBody eq 'N'}">X</c:if></td>
                                		<td><c:if test="${dlist.crIn eq 'Y'}">O</c:if>
                                			<c:if test="${dlist.crIn eq 'N'}">X</c:if></td>
                                		<td><c:if test="${dlist.crOut eq 'Y'}">O</c:if>
                                			<c:if test="${dlist.crOut eq 'N'}">X</c:if></td>
                                		<td><c:if test="${dlist.crDrg eq 'Y'}">O</c:if>
                                			<c:if test="${dlist.crDrg eq 'N'}">X</c:if></td>
                                		<td><c:if test="${dlist.crKor eq 'Y'}">O</c:if>
                                			<c:if test="${dlist.crKor eq 'N'}">X</c:if></td>
                                		<td><c:if test="${dlist.crOver eq 'Y'}">O</c:if>
                                			<c:if test="${dlist.crOver eq 'N'}">X</c:if></td>
                                		<td><c:if test="${dlist.crFamily eq 'Y'}">O</c:if>
                                			<c:if test="${dlist.crFamily eq 'N'}">X</c:if></td>
               						</tr>
               						</c:forEach>
               					</tbody>
               				</table>
               		</fieldset>
             </form>
    	</div>
    </div>
</body>
</html>