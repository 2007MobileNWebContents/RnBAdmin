<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
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
			<h1 style="font-weight: 900;">단체예약승인</h1>
		</div>
		
		<div id="search">
			<form action="/group/search/ack" method="get" name="searchname" id="searchname">
				<select style="height: 30px;" name="searchKey" id="searchKey" onchange="select(this)">
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
					<th>예약상태</th>
					<th>예약인원</th>
					<th>승인/거절</th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach items="${gList }" var="groupReservationAck" varStatus="index">
					<tr>
						<td>${groupReservationAck.rgNo }</td>
						<td>${groupReservationAck.userId }</td>
						<td>${groupReservationAck.rgDate }</td>
						<td>${groupReservationAck.rgTime }</td>
						<td>${groupReservationAck.rgPlace }</td>
						<td>${groupReservationAck.rgState }</td>
						<td>${groupReservationAck.rgPeople }</td>
						<td class="btn">
							<div id="btn1">
								<input type="button" value="승인" onclick="location.href='/group/reservation/complete?groupReservationNo=${groupReservationAck.rgNo }'">
								<input type="button" value="거절" onclick="location.href='/group/reservation/reject?groupReservationNo=${groupReservationAck.rgNo }'">
							</div>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="8" align="center">${pageAckNavi }</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<script>
		function select(e){
			//console.log(e.value);
			$("#searchKey option[value=" + e.value + "]").prop("selected", true);
		}
		
	</script>
</body>
</html>