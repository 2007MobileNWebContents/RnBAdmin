package community.cheer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import home.model.vo.CheerBoard;

public class CheerDAO {
	public ArrayList<CheerBoard> selectList(Connection conn, int currentPage, int recordCountPerPage) {
	      //Statement stmt = null;
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      ArrayList<CheerBoard> nList =null;
	      // ROW_NUMBER() 함수는 순번을 매기는 함수이다. NOTICENO 컬럼을 기준으로 내림차순정렬을 
	      // 해서 NUM이라는 컬럼에 1부터 124까지 순번을 매김 
	      // NUM 이라는 컬럼을 이용해서 내가 보여주고 싶은 게시물의 갯수만큼 SELECT해서 가져올 수 있게 됨
	      // 즉, 1 페이지에서는 1번부터 10번까지의 게시물, 2 페이지에서는 11번부터 20번까지의 게시물을 보여줄 수 있게 됨
	      // WHERE 조건 절에 NUM BETWEEN 뒤에 위치홀더의 값은 현재 페이지의 값에 따라가 바뀌도록 설정해주어야함
	      // 다시말해, 1페이지이면 1, 10의 값이 2페이지이면 11, 20의 값이 들어가도록 수식으로 만들어줘야 함
	      String query = "SELECT * FROM (SELECT CHEER_BOARD.*, ROW_NUMBER() OVER(ORDER BY CB_NO DESC) AS NUM FROM CHEER_BOARD) WHERE NUM BETWEEN ? AND ?";
	      
	      // 현재페이지 : 1, 한 페이지당 보여줄 게시물의 객수 : 10
	      // start : 1, end : 10
	      // start = 1 * 10 - (10 - 1) =1
	      // end = 1 * 10 = 10
	      // 현재페이지  : 2, 한 페이지당 보여줄 게시물의 갯수 : 10
	      // start : 11, end :20
	      // start = 2 * 10 - (10 - 1) = 11 , end = 2 * 10 = 20
	      int start = currentPage*recordCountPerPage - (recordCountPerPage - 1);
	      int end = currentPage*recordCountPerPage;
	      
	      try {
//	         stmt = conn.createStatement();
//	         rset = stmt.executeQuery(query);
	         pstmt = conn.prepareStatement(query);
	         pstmt.setInt(1, start);
	         pstmt.setInt(2, end);
	         rset = pstmt.executeQuery();
	         nList = new ArrayList<CheerBoard>();
	         while(rset.next()) {
	            // 여기에서 사용하는 noticeOne 객체는 nList에 
	            // 데이터를 저장하기 위해 사용되는 도구
	            CheerBoard cheerOne = new CheerBoard();
	            cheerOne.setCbNo(rset.getInt("CB_NO"));
	            cheerOne.setCbCont(rset.getString("CB_CONT"));
	            cheerOne.setCbDate(rset.getDate("CB_DATE"));
	            cheerOne.setUserId(rset.getString("USER_ID"));
	            nList.add(cheerOne);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         JDBCTemplate.close(rset);
//	         JDBCTemplate.close(stmt);
	         JDBCTemplate.close(pstmt);
	      }
	      return nList;
	}

	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {

	      // 전체 게시글의 갯수
	      int recordTotalCount = totalCount(conn);
	      // 전체 페이지의 갯수
	      // 전체 게시물의 갯수가 125개이고 10개씩 보여준다고 하면
	      // 전체 페이지의 갯수는 125/10 = 12 + 5 => 13
	      int pageTotalCount = 0;
	      
	      // 즉, 125개를 10개씩 보여줄 때 페이지 갯수,
	      // 120개를 10개씩 보여줄 때 페이지 갯수가 다르기 때문에 if문으로 처리
	      if( recordTotalCount % recordCountPerPage > 0) {
	         pageTotalCount = recordTotalCount /recordCountPerPage + 1;
	      }else {
	         pageTotalCount = recordTotalCount / recordCountPerPage;
	      }
	      
	      // 오류방지 코드
	      // 현재페이지 값이 최소값보다 작거나 최대값보다 클 때
	      // 정상값으로 셋팅해주는 코드
	      if(currentPage<1) {
	         currentPage = 1;
	      }else if(currentPage>pageTotalCount) {
	         currentPage = pageTotalCount;
	      }
	      
	      // naviCountPerPage라는 변수는 현재 페이지에서 보여줄 Navi의 수
	      // 현재 페이지에서 보여줄 navi 수가 10개
	      // 현재 페이지가 1이라고 하면
	      // 1 2 3 4 5 6 7 8 9 10 next (O)
	      // 1 2 3 4 5 next (X)   
	      // startNavi = ((1-1) / 10) * 10 + 1 = 1
	      // endNavi = 1 + 10 - 1 = 10  
	      int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
	      int endNavi = startNavi + naviCountPerPage - 1;
	      
	      // 오류방지 코드
	      if(endNavi > pageTotalCount) {
	         endNavi = pageTotalCount;
	      }
	      
	      // prev, next를 준비하기 위해서 필요한 변수
	      boolean needPrev = true;
	      boolean needNext = true;
	      // 변수의 값이 true가 되면 버튼을 클릭할 수 있는 것이고
	      // 첫번째 페이지에서는 이전버튼, 마지막 페이지에서는 다음버튼이
	      // 클릭이 안되도록 false값을 대입해줌
	      if(startNavi == 1) {
	         needPrev = false;
	      }
	      if(endNavi == pageTotalCount) {
	         needNext = false;
	      }
	      
	      // Navigation 태그를 String으로 만듬
	      // String을 연결하여 긴 String으로 만들어주는 클래스
	      // 위의 변수들은 a링크를 만들기 위해 사용하는 변수
	      // 밑에 코드는 a링크를 만드는 코드
	      StringBuilder sb = new StringBuilder();
	      // 이전버튼을 누를 수 있을 때 이전버튼을 생성하는 부분
	      if(needPrev) {
	         sb.append("<a style='text-decoration: none;color: black;' href='/cheer/list?currentPage="+(startNavi-1)+"'> < </a>");
	      }
	      // 1 ~ 10까지의 숫자를 for문을 이용해서 생성하고 만들어주는 부분
	      for (int i = startNavi; i<=endNavi; i++) {
	         if(i==currentPage) {
	            sb.append("<a style='text-decoration: none;color: black;' href='/cheer/list?currentPage="+ i +"'><b> "+i+" </b></a>");
	         }else {
	            sb.append("<a style='text-decoration: none;color: black;' href='/cheer/list?currentPage="+i+"'> "
	            		+ ""
	            		+ ""+i+" </a>");
	         }
	      }
	      // 다음버튼을 누를 수 있을 때 다음버튼을 생성하는 부분
	      if(needNext) {
	         sb.append("<a style='text-decoration: none;color: black;' href='/cheer/list?currentPage="+(endNavi+1)+"'> > </a>");
	      }
	      // 태그를 StringBuilder의 append() 메소드를 이용해서 붙인 후에 
	      // toString()메소드를 이용하여 String으로 만들어서 리턴
	      return sb.toString();
	   }
	   
	   // 전체 게시글의 갯수는 사용자가 등록, 삭재할 떄마다 달라지게 되므로
	   // DB에서 쿼리하여 그 갯수를 가져와야함. 그래서 만든 메소드가 totatCount() 메소드
	   public int totalCount(Connection conn) {
	      
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      // 게시글의 총 갯수를 알아오는 쿼리
	      String query = "SELECT COUNT (*) AS TOTALCOUNT FROM CHEER_BOARD";
	      int recordTotalCount = 0;
	      
	      try {
	         pstmt = conn.prepareStatement(query);
	         rset = pstmt.executeQuery();
	         if(rset.next()) {
	            // 쿼리한 결과 값이 하나의 컬럼에 number값이므로 1줄로 끝
	            recordTotalCount = rset.getInt("TOTALCOUNT");
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         JDBCTemplate.close(rset);
	         JDBCTemplate.close(pstmt);
	      }
	      return recordTotalCount;
	}

	public int insertCheer(Connection conn, String content, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO CHEER_BOARD VALUES(SEQ_CB_NO.NEXTVAL,?,SYSDATE,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, content);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteCheer(Connection conn, int postNum, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM CHEER_BOARD WHERE CB_NO = ? AND USER_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, postNum);
			pstmt.setString(2, userId);
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
