<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	.table {
      border-collapse: collapse;
      border-top: 3px solid #168;
      margin-left: 40px;
    }  
    .table th {
      color: #168;
      /*background: #f0f6f9;*/
      background: #dcdcdc;
      text-align: center;
      
    }
    .table th, .table td {
      padding: 10px;
      border: 1px solid #ddd;
      text-align: center;
      width: 155px;
      height: 10px;
    }
    .table th:first-child, .table td:first-child {
      border-left: 0;
    }
    .table th:last-child, .table td:last-child {
      border-right: 0;
    }
    .table tr td:first-child{
      text-align: center;
    }
    
    #right_head>#head_title{
    	width: 200px;
    	float: left;
    }
    
    #right_head>#search{
    	width: 500px;
    	height: 72px;
    	float: right;
    }

	#right_head{
		float: left;
		
	}
	
	#right_middle{
		float: left;
	}
	
	#search>#searchname{
		float: right;
		margin-top: 25px;
	}
	.table tr{
		height: 50px;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<div id="right_head" style="width: 1200px; height: 72px;">
		<div id="head_title">
			<h1 style="font-weight: 900;">단체예약확인</h1>
		</div>
		
		<div id="search">
			<form action="/group/search/confirm" method="get" name="searchname" id="searchname">
				<select style="height: 30px;" name="searchKey">
					<option>이름으로 검색</option>
					<option>아이디로 검색</option>
				</select>
				<input type="text" name="search" style="width: 200px; height: 25px;" placeholder="이름 또는 아이디를 입력해주세요.">
				<input type="submit" value="검색" style=" height: 30px; ">
			</form>
		</div>
	</div>
	<div id="right_middle">
		<table class="table">
			<thead>
				<tr>
					<th>No.</th>
					<th>예약자</th>
					<th>날짜</th>
					<th>시간</th>
					<th>장소</th>
					<th>헌혈종류</th>
					<th>예약상태</th>
					<th>완료/취소</th>
				</tr>
			</thead>
			<tbody>
				<jsp:useBean id="now" class="java.util.Date" />
				<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />  
				<c:forEach items="${gcList }" var="groupconfirm" varStatus="index">
					<tr>
						<td>${groupconfirm.rgNo }</td>
						<td>${groupconfirm.userId }</td>
						<td>${groupconfirm.rgDate }</td>
						<td>${groupconfirm.rgTime }</td>
						<td>${groupconfirm.rgPlace }</td>
						<td>${groupconfirm.rgState }</td>
						<td>${groupconfirm.rgPeople }</td>
						<td class="btn">
							<c:if test="${groupconfirm.rgState eq '승인' }">
								<c:if test="${groupconfirm.rgDate<=today }">
									<input type="button" value="완료" onclick="location.href='/group/reservation/finish?groupReservationNo=${groupconfirm.rgNo }'">
								</c:if>
									<input type="button" value="취소" onclick="location.href='/group/reservation/cancle?groupReservationNo=${groupconfirm.rgNo }'">
							</c:if>
							
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="8" align="center">${pageConfirmNavi }</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>