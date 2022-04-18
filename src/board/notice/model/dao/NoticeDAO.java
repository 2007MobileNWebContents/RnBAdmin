package board.notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import board.notice.model.vo.Notice;
import board.notice.model.vo.NoticeComment;

public class NoticeDAO {

	public ArrayList<Notice> selectList(Connection conn, int currentPage, int recordCountPerPage) {

		PreparedStatement pstmt = null;

		ResultSet rset = null;
		ArrayList<Notice> nList = null;

		String query = "SELECT * FROM (SELECT NOTICE.*, ROW_NUMBER() OVER(ORDER BY NOT_NO DESC) AS NUM FROM NOTICE) WHERE NUM BETWEEN ? AND ?";

		int start = currentPage*recordCountPerPage - (recordCountPerPage-1);
		int end = currentPage*recordCountPerPage;

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			nList= new ArrayList<Notice>();
			while(rset.next()) {
				// 여기에서 사용하는 noticeOne 객체는 nLIst에 
				// 데이터를 저장하기 위해 사용하는 도구(옮겨담기 위해서)
				Notice noticeOne = new Notice();
				// noticeOne(도르레) DB(우물)
				noticeOne.setNoticeNo(rset.getInt("NOT_NO"));
				noticeOne.setTitle(rset.getString("NOT_TITLE"));
				noticeOne.setContent(rset.getString("NOT_CONT"));
				noticeOne.setRegDate(rset.getDate("NOT_DATE"));
				noticeOne.setUserId(rset.getString("NOT_ADMIN"));

				nList.add(noticeOne);
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return nList;

	}
	public ArrayList<NoticeComment> noticeCommentList (Connection conn, int noticeNo){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NoticeComment> cList = null;
		String query = "SELECT * FROM NOTICE_COM WHERE NOTICE_NO=?";
		
//		int start = currentPage*recordCountPerPage - (recordCountPerPage-1);
//		int end = currentPage*recordCountPerPage;

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
//			pstmt.setInt(1, start);
//			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			cList= new ArrayList<NoticeComment>();
			while(rset.next()) {
				// 여기에서 사용하는 noticeOne 객체는 nLIst에 
				// 데이터를 저장하기 위해 사용하는 도구(옮겨담기 위해서)
				NoticeComment commentOne = new NoticeComment();
				// noticeOne(도르레) DB(우물)
				commentOne.setComNo(rset.getInt("NOTICE_COM_NO"));
				commentOne.setCommentContent(rset.getString("NOTICE_COM_CON"));
				commentOne.setUserId(rset.getString("NOTICE_COM_USER"));
				commentOne.setRegDate(rset.getDate("NOTICE_COM_DATE"));
//				noticeOne.setUserId(rset.getString("NOT_ADMIN"));

				cList.add(commentOne);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return cList;
	}
	
	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		// 
		int recordTotalCount = totalCount(conn);
		int pageTotalCount = 0;
		if(recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount/ recordCountPerPage + 1;
		}else {
			pageTotalCount = recordTotalCount/ recordCountPerPage;
		}
		// 오류방지
		if (currentPage<1) {
			currentPage = 1;
		}else if (currentPage > pageTotalCount) {
			currentPage= pageTotalCount;
		}

		int startNavi =((currentPage - 1)/naviCountPerPage)* naviCountPerPage +1;
		int endNavi = startNavi + naviCountPerPage -1;

		// 오류방지
		if (endNavi> pageTotalCount) {
			endNavi = pageTotalCount;
		}

		boolean needPrev = true;
		boolean needNext = true;

		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount ) {
			needNext = false;
		}

		StringBuilder sb = new StringBuilder();
		if (needPrev) {
			sb.append("<a href='/notice/list?currentPage="+(startNavi-1)+"'> < </a>");

		}
		for (int i = startNavi; i <=endNavi; i++) {
			if ( i == currentPage) {
				sb.append("<a href='/notice/list?currentPage="+i+"'><b>"+i+"</b></a>");
			}else {
				sb.append("<a href='/notice/list?currentPage="+i+"'><b>"+i+"</b></a>");
			}
		}

		if (needNext) {
			sb.append("<a href='/notice/list?currentPage="+(endNavi+1)+"'> > </a>");
		}
		return sb.toString();


	}

	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM NOTICE";
		int recordTotalCount = 0;
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
		}
		return recordTotalCount;

	}

	public int insertNotice(Connection conn, String title, String content, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query="INSERT INTO NOTICE VALUES (SEQ_NOT_NO.NEXTVAL,?,?,SYSDATE,?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setNString(3, userId);
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;

	}
	public int insertNoticeComment (Connection conn,int noticeNo, String commentContent, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO NOTICE_COM VALUES (?,NOTICE_COM_NO.NEXTVAL,?,SYSDATE,?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			pstmt.setString(2, commentContent);
			pstmt.setString(3,userId);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public Notice selectNotice (Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice notice = null;
		String query = "SELECT * FROM NOTICE WHERE NOT_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				notice = new Notice();
				notice.setNoticeNo(rset.getInt("NOT_NO"));
				notice.setTitle(rset.getString("NOT_TITLE"));
				notice.setContent(rset.getString("NOT_CONT"));
				notice.setRegDate(rset.getDate("NOT_DATE"));
				notice.setUserId(rset.getString("NOT_ADMIN"));

			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
		}
		return notice;
	}

	public int deleteNoticeComment(Connection conn, int noticeNo,int comNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM NOTICE_COM WHERE NOTICE_NO =? AND NOTICE_COM_NO=?";

		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1,  noticeNo);
			pstmt.setInt(2, comNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);

		}
		return result;
	}
	public int deleteNotice(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM NOTICE WHERE NOT_NO =?";

		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1,  noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);

		}
		return result;
	}

	public int modifyNotice(Connection conn, String title, String content,int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE NOTICE SET NOT_TITLE=?, NOT_CONT=? WHERE NOT_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, noticeNo);
			result= pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Notice> searchNoticeListBoth(Connection conn, String search,String searchSelect, int currentPage, int recordCountPerPage){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT NOTICE.*, ROW_NUMBER() OVER(ORDER BY NOT_NO DESC) AS NUM FROM NOTICE WHERE NOT_TITLE LIKE ? AND NOT_CONT LIKE ?) WHERE NUM BETWEEN ? AND ? ";

		ArrayList<Notice> nList = null;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setString(2, "%"+search+"%");
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset = pstmt.executeQuery();
			nList = new ArrayList<Notice>();
			while (rset.next()) {
				Notice notice = new Notice();
				notice.setNoticeNo(rset.getInt("NOT_NO"));
				notice.setContent(rset.getString("NOT_CONT"));
				notice.setTitle(rset.getString("NOT_TITLE"));
				notice.setUserId(rset.getString("NOT_ADMIN"));
				notice.setRegDate(rset.getDate("NOT_DATE"));
				nList.add(notice);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(rset);
		}
		return nList;
	}

	public ArrayList<Notice> searchNoticeListContent(Connection conn, String search,String searchSelect, int currentPage, int recordCountPerPage){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT NOTICE.*, ROW_NUMBER() OVER(ORDER BY NOT_NO DESC) AS NUM FROM NOTICE WHERE NOT_CONT LIKE ?) WHERE NUM BETWEEN ? AND ? ";

		ArrayList<Notice> nList = null;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			nList = new ArrayList<Notice>();
			while (rset.next()) {
				Notice notice = new Notice();
				notice.setNoticeNo(rset.getInt("NOT_NO"));
				notice.setContent(rset.getString("NOT_CONT"));
				notice.setTitle(rset.getString("NOT_TITLE"));
				notice.setUserId(rset.getString("NOT_ADMIN"));
				notice.setRegDate(rset.getDate("NOT_DATE"));
				nList.add(notice);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(rset);
		}
		return nList;
	}
	
	public ArrayList<Notice> searchNoticeListTitle(Connection conn, String search,String searchSelect, int currentPage, int recordCountPerPage){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT NOTICE.*, ROW_NUMBER() OVER(ORDER BY NOT_NO DESC) AS NUM FROM NOTICE WHERE NOT_TITLE LIKE ?) WHERE NUM BETWEEN ? AND ? ";

		ArrayList<Notice> nList = null;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			nList = new ArrayList<Notice>();
			while (rset.next()) {
				Notice notice = new Notice();
				notice.setNoticeNo(rset.getInt("NOT_NO"));
				notice.setContent(rset.getString("NOT_CONT"));
				notice.setTitle(rset.getString("NOT_TITLE"));
				notice.setUserId(rset.getString("NOT_ADMIN"));
				notice.setRegDate(rset.getDate("NOT_DATE"));
				nList.add(notice);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(rset);
		}
		return nList;
	}

	public String getSearchPageNavi(Connection conn
			, int currentPage, int recordCountPerPage, int naviCountPerPage
			, String search) {
		int recordTotalCount = searchTotalCount(conn, search);
		int pageTotalCount = 0;
		if (recordTotalCount % recordCountPerPage>0) {
			pageTotalCount = recordTotalCount/recordCountPerPage+1;
		} else {
			pageTotalCount = recordTotalCount/ recordCountPerPage;
		}
		if (currentPage<1) {
			currentPage = 1;
		}else if (currentPage> pageTotalCount) {
			currentPage = pageTotalCount;
		}
		int startNavi = ((currentPage - 1)/ naviCountPerPage)* naviCountPerPage +1;
		int endNavi = startNavi + naviCountPerPage - 1;

		if (endNavi > pageTotalCount) {
			endNavi= pageTotalCount;
		}
		boolean needPrev = true;
		boolean needNext = true;

		if(startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount) {
			needNext = false;
		}
		StringBuilder sb = new StringBuilder();
		if (needPrev) {
			sb.append("<a href='/notice/search?search="+search+"&currentPage="+(startNavi-1)+"'> < </a>");
		}
		for(int i = startNavi; i<= endNavi; i++) {
			if(i == currentPage) {
				sb.append("<a href='/notice/search?search="+search+"&currentPage="+i+"'><b> "+i+" </b></a>");
			}else {
				sb.append("<a href='/notice/search?search="+search+"&currentPage="+i+"'> "+i+" </a>");
			}
		}
		if(needNext) {
			sb.append("<a href='/notice/search?search="+search+"&currentPage="+(endNavi+1)+"'> > </a>");
		}
		return sb.toString();

	}

	public int searchTotalCount (Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM NOTICE";

		int recordTotalCount = 0;
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return recordTotalCount;
	}


}
