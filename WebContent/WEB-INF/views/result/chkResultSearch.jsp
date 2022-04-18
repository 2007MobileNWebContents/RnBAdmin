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
	<div id="search_form">
        <form action="/checkResult/search" method="post">
            <input type="text" id="userId" name="userId" placeholder="Search UserId">
            <button type="submit">검색</button>
        </form>
    </div>
    
    <div id="top">
	    <div id="top_tb">
	    	<fieldset>
	    		<legend>자가문진 결과확인</legend>
	    		<div id="tb_div">
	    			<table>
	    				<thead>
	    					<tr>
	    						<th class="col1">No.</th><th>문진날짜</th><th>결과</th><th>상세보기</th>
	    					</tr>
	    				</thead>
	    				<tbody>
	    					<c:forEach items="${rlist}" var="rOne">
		    					<tr>
		    						<td>${rOne.crCode }</td>
		    						<td>${rOne.crDate }</td>
		    						<td><c:if test="${rOne.crRes eq 'Y'}">가능</c:if>
                                	<c:if test="${rOne.crRes eq 'N'}">불가능</c:if></td>
                                	<td><input type="button" onclick="popup('${rOne.crCode}');" value="상세확인" id="pop"></input></td>
		    					</tr>
	    					</c:forEach>
	    				</tbody>
	    			</table>
	    			</div>
	    	</fieldset>
	    </div>
    </div>


</body>
</html>