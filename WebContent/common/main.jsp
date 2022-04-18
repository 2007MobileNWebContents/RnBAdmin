<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>R&B Admin</title>
<link rel="shortcut icon" type="image/x-icon" href="/images/R&B%20CI.png">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=311ded0f995354355f23560e5c83f893"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=311ded0f995354355f23560e5c83f893&libraries=services,clusterer,drawing"></script>

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

<script type="text/javascript">
	// banner script
	$(document).ready(
		
		// banner 설정 및 시간차 조정 
		function() {
			var $banner = $(".banner").find("ul");
			// 이미지 폭
			var $bannerWidth = $banner.children().outerWidth();
			// 이미지 높이
			var $bannerHeight = $banner.children().outerHeight(); 
			// 이미지 갯수
			var $length = $banner.children().length;
			var rollingId;
	
			//다음 이미지로 롤링 애니메이션 할 시간차
			rollingId = setInterval(function() {
				rollingStart();
			}, 5000);
	
			function rollingStart() {
				$banner.css("width", $bannerWidth * $length	+ "px");
				$banner.css("height", $bannerHeight + "px");
				
				// 배너의 좌측 위치 이동
				$banner.animate({
					left : -$bannerWidth + "px"
				}, 1500, function() { 
					
					// 첫번째 이미지를 마지막 끝에 복사해서 추가
					$(this).append(
							"<li>"+ $(this).find("li:first").html() + "</li>"
					);
					// 뒤로 복사 후 첫번재 이미지를 삭제
					$(this).find("li:first").remove();
					// 다음을 위해서 배너 좌측의 위치값 초기화
					$(this).css("left", 0);
		
				});
			}
	});
</script>

<link href="/common/main.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="wrapping1">
	<div id="wrapping2">
		<header id="headers"></header>
		<br>
		<nav id="navs"></nav>
		<br>
		
		<section>
			<!-- 롤링 banner 영역 -->
			<div class="banner">
				<ul>
					<li> <!-- 1번 이미지  -->
						<img src="/images/헌혈사진1.jpg" height="400px">
					</li>
					<li> <!-- 2번 이미지  -->
						<img src="/images/헌혈사진2.jpg" width="1500" height="400px">
					</li>
					<li> <!-- 3번 이미지  -->
						<img src="/images/헌혈사진3.jpg" width="1500" height="400px">
					</li>
					<li> <!-- 4번 이미지  -->
						<img src="/images/헌혈사진4.jpg"
						width="1500" height="400px">
					</li>
					<li> <!-- 5번 이미지  -->
						<img src="/images/헌혈사진5.png"
						width="1500" height="400px">
					</li>
				</ul>
			</div>


			<!-- 오늘의 헌혈량 Div -->
			<div class="todayBlood">
				<span class="tbSpan">오늘의 헌혈량</span>
				<a href="/personal/reservation"><img src="/images/물방울1.png" style="margin-top: 15px; margin-left: 50px; position: absolute;"></a>
				<div class="tbBox">
					<c:if test="${rcList[0].userABO eq 'A' }">
						<div class="blA">
						<span class="bKind">A형</span>
						<c:choose>
							<c:when test="${rcList[0].rCount <= 25}">
								<img class="hPosition" src="/images/heart25.png" >
							</c:when>
							<c:when test="${rcList[0].rCount <= 50}">
								<img class="hPosition" src="/images/heart50.png" >
							</c:when>
							<c:when test="${rcList[0].rCount <= 75}">
								<img class="hPosition" src="/images/heart75.png" >
							</c:when>
							<c:when test="${rcList[0].rCount <= 99}">
								<img class="hPosition" src="/images/heart99.png" >
							</c:when>
							<c:otherwise>
								<img class="hPosition" src="/images/heart100.png" >
							</c:otherwise>
						</c:choose>
						<span class="brCount">${rcList[0].rCount }</span>
						</div>
					</c:if>
					<c:if test="${rcList[1].userABO eq 'AB' }">
						<div class="blAB">
						<span class="bKind">AB형</span>
						<c:choose>
							<c:when test="${rcList[1].rCount <= 25}">
								<img class="hPosition" src="/images/heart25.png" >
							</c:when>
							<c:when test="${rcList[1].rCount <= 50}">
								<img class="hPosition" src="/images/heart50.png" >
							</c:when>
							<c:when test="${rcList[1].rCount <= 75}">
								<img class="hPosition" src="/images/heart75.png" >
							</c:when>
							<c:when test="${rcList[1].rCount <= 99}">
								<img class="hPosition" src="/images/heart99.png" >
							</c:when>
							<c:otherwise>
								<img class="hPosition" src="/images/heart100.png" >
							</c:otherwise>
						</c:choose>
						<span class="brCount">${rcList[1].rCount }</span>
						</div>
					</c:if>
					<c:if test="${rcList[2].userABO eq 'B' }">
						<div class="blB">
						<span class="bKind">B형</span>
						<c:choose>
							<c:when test="${rcList[2].rCount <= 25}">
								<img class="hPosition" src="/images/heart25.png" >
							</c:when>
							<c:when test="${rcList[2].rCount <= 50}">
								<img class="hPosition" src="/images/heart50.png" >
							</c:when>
							<c:when test="${rcList[2].rCount <= 75}">
								<img class="hPosition" src="/images/heart75.png" >
							</c:when>
							<c:when test="${rcList[2].rCount <= 99}">
								<img class="hPosition" src="/images/heart99.png" >
							</c:when>
							<c:otherwise>
								<img class="hPosition" src="/images/heart100.png" >
							</c:otherwise>
						</c:choose>
						<span class="brCount">${rcList[2].rCount }</span>
						</div>
					</c:if>
					<c:if test="${rcList[3].userABO eq 'O' }">
						<div class="blO">
						<span class="bKind">O형</span>
						<c:choose>
							<c:when test="${rcList[3].rCount <= 25}">
								<img class="hPosition" src="/images/heart25.png" >
							</c:when>
							<c:when test="${rcList[3].rCount <= 50}">
								<img class="hPosition" src="/images/heart50.png" >
							</c:when>
							<c:when test="${rcList[3].rCount <= 75}">
								<img class="hPosition" src="/images/heart75.png" >
							</c:when>
							<c:when test="${rcList[3].rCount <= 99}">
								<img class="hPosition" src="/images/heart99.png" >
							</c:when>
							<c:otherwise>
								<img class="hPosition" src="/images/heart100.png" >
							</c:otherwise>
						</c:choose>
						<span class="brCount">${rcList[3].rCount }</span>
						</div>
					</c:if>
				</div>
			</div>
			
			<!-- 공지사항 Div -->
			<div class="notice">
				<span class="ntSpan">공지사항</span>
				<a href="/notice/list"><img src="/images/물방울1.png" style="margin-top: 15px; margin-left: 50px; position: absolute;"></a>
				<table class="ntBox">
					<tr>
						<th>글번호</th><th>글제목</th>
					</tr>
					<c:forEach items="${notList }" var="notice" varStatus="index">
						<tr>
							<th><a href="/notice/select?noticeNo=${notice.notNo }">${notice.notNo }</a></th>
							<td><a href="/notice/select?noticeNo=${notice.notNo }">${notice.notTitle }</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			
			<!-- 헌혈 결과 조회 Div -->
			<div class="blCheck">
				<span class="blSpan">헌혈 결과 조회</span>
				<a href="/bAdmin/form"><img src="/images/물방울1.png" style="margin-top: 15px; margin-left: 50px; position: absolute;"></a>
				<div class="bChkBox">
					<div class="blcBtn"><img src="/images/돋보기.png"><a href="/bAdmin/form" style="text-decoration: none; margin-left:15px;">검사결과 입력</a></div>
					<img src="/images/의료모니터.png" style="margin-left:80px; margin-top:50px; width: 150px;">
				</div>
			</div>
									
			<!-- 지역별 헌혈 랭킹 Div -->
			<div class="rank">
				<span class="rankSpan">헌혈의 집 찾기</span>
				<a href="/find/house"><img src="/images/물방울1.png" style="margin-top: 15px; margin-left: 170px; position: absolute;"></a>
				<div id="rankBox">
					
				</div>
			</div>
			
			<!-- 응원의 한마디 Div -->
			<div class="cheerUp">
				<span class="cuSpan">응원의 한마디</span>
				<a href="/cheer/list"><img src="/images/물방울1.png" style="margin-top: 15px; margin-left: 380px; position: absolute;"></a>
				<div class="cuBox">
							<MARQUEE height="50px" vspace="20" hspace="20" direction="up" scrollamount="2" style="font-size: 25px; margin-top:60px;">
								<c:forEach items="${cmList }" var="cheer">
									<p>[${cheer.userId }] : ${cheer.cbCont }  (${cheer.cbDate })<br><br></p>
								</c:forEach>
							</MARQUEE>
				</div>
			</div>
			
			<!-- 유투브 Div -->
			<div class="yTube">
				<span class="yTSpan">YouTube</span>
				<a href="https://www.youtube.com/channel/UCcxh5nFs-nUuMTUHWzDPONA" target="_blank"><img src="/images/물방울1.png" style="margin-top: 15px; margin-left: 765px; display: block; position: absolute;;"></a>
				<iframe class="yTube1" width="250" height="140" src="https://www.youtube.com/embed/uV856RlIbdg" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
				<iframe class="yTube2" width="250" height="140" src="https://www.youtube.com/embed/hgW_kyJAeh4" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
				<iframe class="yTube3" width="250" height="140" src="https://www.youtube.com/embed/hsJZuzwRR0E" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
			</div>
		</section><br><br>
		
		<footer id="footers"></footer>
	</div>
</div>

<script>
		var mapContainer = document.getElementById('rankBox'), // 지도를 표시할 div  
		mapOption = {
			center : new kakao.maps.LatLng(37.5706202,126.9797766), // 지도의 중심좌표
			level : 3
		// 지도의 확대 레벨
		};

		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

		// 지도 타입 변경 컨트롤을 생성한다
		var mapTypeControl = new kakao.maps.MapTypeControl();

		// 지도의 상단 우측에 지도 타입 변경 컨트롤을 추가한다
		map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

		// 지도에 확대 축소 컨트롤을 생성한다
		var zoomControl = new kakao.maps.ZoomControl();

		// 지도의 우측에 확대 축소 컨트롤을 추가한다
		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

		//마커 이미지주소,크기,옵션
		var imageSrc = 'https://www.bloodinfo.net/images/donating_blood/bldhouse/icon_bloodcenter_color.png', imageSize = new kakao.maps.Size(
				30, 30), imageOption = {
			offset : new kakao.maps.Point(5, 5)
		};
		
		var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
		
		// 마커를 표시할 위치와 내용을 가지고 있는 객체 배열입니다 
		var positions = [ {
			content : '<div class="img_mark"><p class="sitename">헌혈의집 연신내센터</p><p class="address">[122-809] 서울 은평구 통일로 855-8 (갈현동) Y빌딩 4층, 연신내역 6번출구</p><p class="phone">02-353-7750</p></div>'
			,
			latlng : new kakao.maps.LatLng(37.6190585,126.8502474)
		},{
			content : '<div class="img_mark"><p class="sitename">헌혈의집 수유센터</p><p class="address">서울 강북구 도봉로 325 수성타워 (지하철4호선 수유역 5번 출구)</p><p class="phone">02-900-4772</p></div>',
			latlng : new kakao.maps.LatLng(37.6372764,126.9542956)
		},{
			content : '<div class="img_mark"><p class="sitename">헌혈의집 외대앞센터</p><p class="address">서울특별시 동대문구 이문1동 휘경로 15</p><p class="phone">02-2272-1370</p></div>',
			latlng : new kakao.maps.LatLng(37.5958379,126.9918466)
		},{
			content : '<div class="img_mark"><p class="sitename">헌혈의집 고려대앞센터</p><p class="address">서울특별시 성북구 안암동 인촌로24길 11</p><p class="phone">02-967-3852</p></div>',
			latlng : new kakao.maps.LatLng(37.5856445,127.027303)
		},{
			content : '<div class="img_mark"><p class="sitename">헌혈의집 회기센터</p><p class="address">서울특별시 동대문구 휘경1동 회기로 188</p><p class="phone">02-969-6199</p></div>',
			latlng : new kakao.maps.LatLng(37.5896491,126.9867611)
		},{
			content : '<div class="img_mark"><p class="sitename">헌혈의집 대학로센터</p><p class="address">서울특별시 종로구 명륜2가 대명길 26</p><p class="phone">02-743-1972</p></div>',
			latlng : new kakao.maps.LatLng(37.5833783,126.9996926)
		},{
			content : '<div class="img_mark"><p class="sitename">헌혈의집 서울역센터</p><p class="address">서울특별시 중구 회현동 청파로 426</p><p class="phone">02-752-9020</p></div>',
			latlng : new kakao.maps.LatLng(37.557343,126.9693683)
		},{
			content : '<div class="img_mark"><p class="sitename">헌혈의집 신촌센터</p><p class="address">서울특별시 서대문구 신촌동 신촌로 107</p><p class="phone">02-312-1247</p></div>',
			latlng : new kakao.maps.LatLng(37.5555936,126.937746)
		},{
			content : '<div class="img_mark"><p class="sitename">헌혈의집 영등포센터</p><p class="address">서울특별시 영등포구 영등포동 영중로 3</p><p class="phone">02-2675-1361</p></div>',
			latlng : new kakao.maps.LatLng(37.516719,126.9059706)
		},{
			content : '<div class="img_mark"><p class="sitename">헌혈의집 잠실역센터</p><p class="address">서울특별시 송파구 잠실6동 올림픽로 265</p><p class="phone">02-2202-7479</p></div>',
			latlng : new kakao.maps.LatLng(37.5125158,127.100806)
		},{
			content : '<div class="img_mark"><p class="sitename">헌혈의집 천호센터</p><p class="address">서울특별시 강동구 천호동 천호대로 1033</p><p class="phone">02-485-3515</p></div>',
			latlng : new kakao.maps.LatLng(37.5378712,127.1268203)
		},{
			content : '<div class="img_mark"><p class="sitename">헌혈의집 노원센터</p><p class="address">서울특별시 노원구 상계6.7동 상계로 70</p><p class="phone">02-934-5340</p></div>',
			latlng : new kakao.maps.LatLng(37.6559662,127.0626811)
		},{
			content : '<div class="img_mark"><p class="sitename">헌혈의집 강남센터</p><p class="address">서울특별시 서초구 서초동 1305-3 7층</p><p class="phone">02-533-0770</p></div>',
			latlng : new kakao.maps.LatLng(37.5116895,127.0577953)
		},{
	         content : '<div class="img_mark"><p class="sitename">헌혈의집 신촌 연대앞센터</p><p class="address">서울특별시 서대문구 명물길 6 윤성빌딩 8층</p><p class="phone">02-392-6460</p></div>',
	         latlng : new kakao.maps.LatLng(37.5572881,126.937334)
	      },{
	         content : '<div class="img_mark"><p class="sitename">헌혈의집 홍대 센터</p><p class="address">서울특별시 마포구 양화로 152  대화빌딩 6층)</p><p class="phone">02-323-5420</p></div>',
	         latlng : new kakao.maps.LatLng(37.5557779,126.9228701)
	      },{
	         content : '<div class="img_mark"><p class="sitename">헌혈의집 목동 센터</p><p class="address">서울특별시 양천구 목동동로 293 현대41타워 지하1층</p><p class="phone">02-715-3105</p></div>',
	         latlng : new kakao.maps.LatLng(37.516719,126.8757773)
	      },{
	         content : '<div class="img_mark"><p class="sitename">헌혈의집 노량진역 센터</p><p class="address">서울특별시 동작구 노량진동 73-1</p><p class="phone">02-8252-916</p></div>',
	         latlng : new kakao.maps.LatLng(37.5133343,126.9429606)
	      },{
	         content : '<div class="img_mark"><p class="sitename">헌혈의집 신도림역1 센터</p><p class="address">서울특별시 구로구 신도림동 360</p><p class="phone"02-671-1802</p></div>',
	         latlng : new kakao.maps.LatLng(37.509679,126.8905342)
	      },{
	         content : '<div class="img_mark"><p class="sitename">헌혈의집 신도림테크노마트 센터</p><p class="address">서울특별시 구로구 구로동 새말로 97</p><p class="phone">02-861-0801</p></div>',
	         latlng : new kakao.maps.LatLng(37.5070449,126.8902177)
	      },{
	         content : '<div class="img_mark"><p class="sitename">헌혈의집 구로디지털단지역 센터</p><p class="address">서울특별시 구로구 구로동 도림천로 477</p><p class="phone">02-869-9415</p></div>',
	         latlng : new kakao.maps.LatLng(37.485126,126.9015312)
	      },{
	         content : '<div class="img_mark"><p class="sitename">헌혈의집 광명 센터</p><p class="address">경기도 광명시 광명동 광명로 906 현대아이타워 501호</p><p class="phone">02-2060-5473</p></div>',
	         latlng : new kakao.maps.LatLng(37.4797368,126.8548501)
	      },{
	         content : '<div class="img_mark"><p class="sitename">헌혈의집 돈암 센터</p><p class="address">서울특별시 성북구 동선동 동소문로20다길 17</p><p class="phone">02-925-3566</p></div>',
	         latlng : new kakao.maps.LatLng(37.5919274,127.0177031)
	      },{
	         content : '<div class="img_mark"><p class="sitename">헌혈의집 우장산역 센터</p><p class="address">서울특별시 강서구 화곡동 강서로45길 5</p><p class="phone">02-2603-5817</p></div>',
	         latlng : new kakao.maps.LatLng(37.5478225,126.8358225)
	      },{
	         content : '<div class="img_mark"><p class="sitename">헌혈의집 발산역 센터</p><p class="address">서울특별시 강서구 가양1동 강서로 385 우성에스비타워 5층 507호</p><p class="phone">02-777-1291</p></div>',
	         latlng : new kakao.maps.LatLng(37.5597857,126.8384135)
	      },{
	         content : '<div class="img_mark"><p class="sitename">헌혈의집 서울대역 센터</p><p class="address">서울특별시 관악구 낙성대동 1598-18</p><p class="phone">02-873-4364</p></div>',
	         latlng : new kakao.maps.LatLng(37.4786593,126.9525782)
	      },{
	         content : '<div class="img_mark"><p class="sitename">헌혈의집 이수 센터</p><p class="address">서울특별시 동작구 사당동 145-2</p><p class="phone">02-578-9811</p></div>',
	         latlng : new kakao.maps.LatLng(37.4863103,126.9816705)
	      },{
	         content : '<div class="img_mark"><p class="sitename">헌혈의집 서울남부(원내) 센터</p><p class="address">서울특별시 강남구 개포4동 개포로31길 48</p><p class="phone">02-570-0662</p></div>',
	         latlng : new kakao.maps.LatLng(37.4821297,127.0487706)
	      },{
	         content : '<div class="img_mark"><p class="sitename">헌혈의집 코엑스 센터</p><p class="address">서울특별시 강남구 삼성1동 봉은사로 524</p><p class="phone">02-551-0600</p></div>',
	         latlng : new kakao.maps.LatLng(37.5111748,127.059909)
	      },{
	         content : '<div class="img_mark"><p class="sitename">헌혈의집 강남 센터</p><p class="address">서울특별시 서초구 서초동 1305-3 7층</p><p class="phone">02-533-0770</p></div>',
	         latlng : new kakao.maps.LatLng(37.5013247,127.0254986)
	      },{
	         content : '<div class="img_mark"><p class="sitename">헌혈의집 강남2 센터</p><p class="address">서울특별시 강남구 역삼동 825-9</p><p class="phone">02-564-1525</p></div>',
	         latlng : new kakao.maps.LatLng(37.496624,127.0286635)
	      },{
	         content : '<div class="img_mark"><p class="sitename">헌혈의집 한양대역 센터</p><p class="address">서울특별시 성동구 사근동 왕십리로 206</p><p class="phone">02-2296-1076</p></div>',
	         latlng : new kakao.maps.LatLng(37.5557992,127.0438395)
	      },{
	         content : '<div class="img_mark"><p class="sitename">헌혈의집 건대역 센터</p><p class="address">서울특별시 광진구 화양동 동일로22길 115 하마빌딩 4층</p><p class="phone">02-498-4185</p></div>',
	         latlng : new kakao.maps.LatLng(37.5407217,127.0704372)
	      },{
	         content : '<div class="img_mark"><p class="sitename">헌혈의집 동서울2 센터</p><p class="address"> 서울특별시 강변역로 구의동 광진구50,114호 </p><p class="phone">02-446-3526</p></div>',
	         latlng : new kakao.maps.LatLng(37.5343168,127.0943817)
	      },{
	         content : '<div class="img_mark"><p class="sitename">헌혈의집 강동 센터</p><p class="address">서울특별시 강동구 명일동 고덕로 272</p><p class="phone">02-3427-5130</p></div>',
	         latlng : new kakao.maps.LatLng(37.5549335,127.1556383)
	      },{
	         content : '<div class="img_mark"><p class="sitename">헌혈의집 광화문 센터</p><p class="address">서울특별시 종로구 청진동 종로 33</p><p class="phone">02-732-1027</p></div>',
	         latlng : new kakao.maps.LatLng(37.5706202,126.9797766)
	      },{
	         content : '<div class="img_mark"><p class="sitename">헌혈의집 노해로 센터</p><p class="address">서울특별시 노원구 상계6.7동 노해로 480</p><p class="phone">02-935-0322</p></div>',
	         latlng : new kakao.maps.LatLng(37.6541572,127.0614199)
	      }
		];

		for (var i = 0; i < positions.length; i++) {
			// 마커를 생성합니다
			var marker = new kakao.maps.Marker({
				map : map, // 마커를 표시할 지도
				position : positions[i].latlng,
				image : markerImage,
				clickable : true
			// 마커의 위치
			});

			// 마커에 표시할 인포윈도우를 생성합니다 
			var infowindow = new kakao.maps.InfoWindow({
				content : positions[i].content,
				removable : true
				
			// 인포윈도우에 표시할 내용
			});
			kakao.maps.event.addListener(marker, 'click', makeClickListener(map,marker,infowindow));
		}
			 
		function makeClickListener(map,marker,infowindow){
			return function(){
				infowindow.open(map,marker);
			};
		}
		
		$("#mapSearch_btn").click(function(){
			var geocoder = new kakao.maps.services.Geocoder();
		       geocoder.addressSearch($("#mapSearch").val(), function(result, status) {

		           // 정상적으로 검색이 완료됐으면 
		            if (status === kakao.maps.services.Status.OK) {

		               var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		               // 지도의 중심을 결과값으로 받은 위치로 

		               map.setCenter(coords);
		           } 
		       });
		})
		
		
	    function searchAddr() {
	        new daum.Postcode({
	            oncomplete: function(data) {
		console.log(data);
	                // 주소 정보를 해당 필드에 넣는다.
	                document.getElementById("mapSearch").value = data.address;
	            }
	        }).open();
	    }

	</script>
</body>
</html>