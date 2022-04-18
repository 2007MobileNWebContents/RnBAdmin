package memInfo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import memInfo.model.vo.MemberInfo;

public class MemberInfoDAO {

	public ArrayList<MemberInfo> selectList(Connection conn, int currentPage, int recordCountPerPage){

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MemberInfo> memList = null;
		String query = "SELECT * FROM (SELECT MEMBER.*, ROW_NUMBER() OVER(ORDER BY USER_NAME) AS NUM FROM MEMBER) WHERE NUM BETWEEN ? AND ?";

		int start = currentPage*recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage*recordCountPerPage;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			memList = new ArrayList<MemberInfo>();
			while(rset.next()) {
				MemberInfo mem = new MemberInfo();
				mem.setUserId(rset.getString("USER_ID"));
				mem.setUserName(rset.getString("USER_NAME"));
				mem.setUserBD(rset.getString("USER_BD"));
				mem.setUserPhone(rset.getString("USER_PHONE"));
				mem.setUserAddrRoad(rset.getString("USER_ADDR_ROAD"));
				mem.setUserAddrDetail(rset.getString("USER_ADDR_DETAIL"));
				mem.setUserEmail(rset.getString("USER_EMAIL"));
				mem.setUserABO(rset.getString("USER_ABO"));
				memList.add(mem);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return memList;
	}

	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {

		int recordTotalCount = totalCount(conn);
		int pageTotalCount = 0;

		if( recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount /recordCountPerPage + 1;
		}else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}

		// 오류방지 코드
		if(currentPage<1) {
			currentPage = 1;
		}else if(currentPage>pageTotalCount) {
			currentPage = pageTotalCount;
		}

		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;

		// 오류방지 코드
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		boolean needPrev = true;
		boolean needNext = true;
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}

		StringBuilder sb = new StringBuilder();
		if(needPrev) {
			sb.append("<a style='text-decoration: none;color: black;' href='/member/list?currentPage="+(startNavi-1)+"'> < </a>");
		}
		for (int i = startNavi; i<=endNavi; i++) {
			if(i==currentPage) {
				sb.append("<a style='text-decoration: none;color: black;' href='/member/list?currentPage="+ i +"'><b> "+i+" </b></a>");
			}else {
				sb.append("<a style='text-decoration: none;color: black;' href='/member/list?currentPage="+i+"'> "+i+" </a>");
			}
		}
		if(needNext) {
			sb.append("<a style='text-decoration: none;color: black;' href='/member/list?currentPage="+(endNavi+1)+"'> > </a>");
		}
		return sb.toString();
	}

	public int totalCount(Connection conn) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT (*) AS TOTALCOUNT FROM MEMBER";
		int recordTotalCount = 0;

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
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


	public ArrayList<MemberInfo> searchList(Connection conn, String idName, int currentPage, int recordCountPerPage){

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT MEMBER.*, ROW_NUMBER() OVER(ORDER BY USER_NAME) AS NUM FROM MEMBER WHERE (USER_ID LIKE ?) OR (USER_NAME LIKE ?) ) WHERE NUM BETWEEN ? AND ?";
		ArrayList<MemberInfo> memList = null;

		int start = currentPage*recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage*recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+idName+"%");
			pstmt.setString(2, "%"+idName+"%");
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset = pstmt.executeQuery();
			memList = new ArrayList<MemberInfo>();

			while(rset.next()) {
				MemberInfo mem = new MemberInfo();
				mem.setUserId(rset.getString("USER_ID"));
				mem.setUserName(rset.getString("USER_NAME"));
				mem.setUserBD(rset.getString("USER_BD"));
				mem.setUserPhone(rset.getString("USER_PHONE"));
				mem.setUserAddrRoad(rset.getString("USER_ADDR_ROAD"));
				mem.setUserAddrDetail(rset.getString("USER_ADDR_DETAIL"));
				mem.setUserEmail(rset.getString("USER_EMAIL"));
				mem.setUserABO(rset.getString("USER_ABO"));
				memList.add(mem);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return memList;
	}


	public String getSearchPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage, String idName) {
		int recordTotalCount = searchTotalCount(conn, idName);
		int pageTotalCount = 0;
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		}else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}

		// 오류방지용 코드
		if(currentPage <1) {
			currentPage = 1;
		}else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}

		int startNavi = ((currentPage-1)/naviCountPerPage)*naviCountPerPage+1;
		int endNavi = startNavi + naviCountPerPage-1;

		// 오류방지용 코드
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		boolean needPrev = true;
		boolean needNext = true;
		if(startNavi ==1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		StringBuilder sb = new StringBuilder();
		if(needPrev) {
			sb.append("<a style='text-decoration: none;color: black;' href='/member/search?idName="+idName+"&currentPage="+(startNavi-1)+"'> < </a>");
		}
		for(int i = startNavi; i<= endNavi; i++) {
			if(i == currentPage) {
				sb.append("<a style='text-decoration: none;color: black;' href='/member/search?idName="+idName+"&currentPage="+i+"'><b> "+i+" </b></a>");
			}else {
				sb.append("<a style='text-decoration: none;color: black;' href='/member/search?idName="+idName+"&currentPage="+i+"'> "+i+" </a>");
			}
		}
		if(needNext) {
			sb.append("<a style='text-decoration: none;color: black;' href='/member/search?idName="+idName+"&currentPage="+(endNavi+1)+"'> > </a>");
		}
		return sb.toString();
	}

	
	public int searchTotalCount(Connection conn, String idName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// 게시글의 총 갯수를 알아오는 쿼리
		String query = "SELECT COUNT (*) AS TOTALCOUNT FROM MEMBER WHERE (USER_ID LIKE ?) OR (USER_NAME LIKE ?)";
		int recordTotalCount = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+idName+"%");
			pstmt.setString(2, "%"+idName+"%");
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
	
	public int deleteMemInfo(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		int result =0;
		String query="DELETE FROM MEMBER WHERE USER_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}
}
