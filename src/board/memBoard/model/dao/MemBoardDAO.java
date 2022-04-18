package board.memBoard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.memBoard.model.vo.MemBoardComment;
import board.memBoard.model.vo.MemberBoard;
import common.JDBCTemplate;

public class MemBoardDAO {

	public ArrayList<MemberBoard> selectMemList(Connection conn, int currentPage, int recordCountPerPage) {

		PreparedStatement pstmt = null;

		ResultSet rset = null;
		ArrayList<MemberBoard> mList = null;

		String query = "SELECT * FROM (SELECT MEM_BOARD.*, ROW_NUMBER() OVER(ORDER BY MB_NO DESC) AS NUM FROM MEM_BOARD) WHERE NUM BETWEEN ? AND ?";

		int start = currentPage*recordCountPerPage - (recordCountPerPage-1);
		int end = currentPage*recordCountPerPage;

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			mList= new ArrayList<MemberBoard>();
			while(rset.next()) {
				// 여기에서 사용하는 noticeOne 객체는 nLIst에 
				// 데이터를 저장하기 위해 사용하는 도구(옮겨담기 위해서)
				MemberBoard memBoard = new MemberBoard();
				// noticeOne(도르레) DB(우물)
				memBoard.setMbNo(rset.getInt("MB_NO"));
				memBoard.setMbTitle(rset.getString("MB_TITLE"));
				memBoard.setMbCont(rset.getString("MB_CONT"));
				memBoard.setMbDate(rset.getDate("MB_DATE"));
				memBoard.setUserId(rset.getString("USER_ID"));

				mList.add(memBoard);
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return mList;

	}
	public ArrayList<MemBoardComment> MemBoardCommentList (Connection conn, int mbNo){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MemBoardComment> mcList = null;
		String query = "SELECT * FROM MB_COM WHERE MB_NO=?";
		
//		int start = currentPage*recordCountPerPage - (recordCountPerPage-1);
//		int end = currentPage*recordCountPerPage;

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mbNo);
//			pstmt.setInt(1, start);
//			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			mcList= new ArrayList<MemBoardComment>();
			while(rset.next()) {
				// 여기에서 사용하는 noticeOne 객체는 nLIst에 
				// 데이터를 저장하기 위해 사용하는 도구(옮겨담기 위해서)
				MemBoardComment commentOne = new MemBoardComment();
				// noticeOne(도르레) DB(우물)
				commentOne.setMemNo(rset.getInt("MB_COM_NO"));
				commentOne.setCommentContent(rset.getString("MB_COM_CONT"));
				commentOne.setUserId(rset.getString("MB_COM_USER"));
				commentOne.setRegDate(rset.getDate("MB_COM_DATE"));
//				noticeOne.setUserId(rset.getString("NOT_ADMIN"));

				mcList.add(commentOne);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return mcList;
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
			sb.append("<a href='/memboard/list?currentPage="+(startNavi-1)+"'> < </a>");

		}
		for (int i = startNavi; i <=endNavi; i++) {
			if ( i == currentPage) {
				sb.append("<a href='/memboard/list?currentPage="+i+"'><b>"+i+"</b></a>");
			}else {
				sb.append("<a href='/memboard/list?currentPage="+i+"'><b>"+i+"</b></a>");
			}
		}

		if (needNext) {
			sb.append("<a href='/memboard/list?currentPage="+(endNavi+1)+"'> > </a>");
		}
		return sb.toString();


	}

	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM MEM_BOARD";
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

	public int insertMemberBoard(Connection conn, String title, String content, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query="INSERT INTO MEM_BOARD VALUES (SEQ_MB_NO.NEXTVAL,?,?,SYSDATE,?)";

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
	public int insertMemBoardComment (Connection conn,int mbNo, String commentContent, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO MB_COM VALUES (SEQ_MB_COM_NO.NEXTVAL,?,SYSDATE,?,?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, commentContent);
			pstmt.setString(2,userId);
			pstmt.setInt(3, mbNo);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public MemberBoard selectMemBoard (Connection conn, int mbNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		MemberBoard memBoard = null;
		String query = "SELECT * FROM MEM_BOARD WHERE MB_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mbNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				memBoard = new MemberBoard();
				memBoard.setMbNo(rset.getInt("MB_NO"));
				memBoard.setMbTitle(rset.getString("MB_TITLE"));
				memBoard.setMbCont(rset.getString("MB_CONT"));
				memBoard.setMbDate(rset.getDate("MB_DATE"));
				memBoard.setUserId(rset.getString("USER_ID"));

			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
		}
		return memBoard;
	}
	public int deleteMemBoardComment(Connection conn, int mbNo,int memNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM MB_COM WHERE MB_NO =? AND MB_COM_NO=?";

		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1,  mbNo);
			pstmt.setInt(2, memNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);

		}
		return result;
	}

	public int deleteMemberBoard(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM MEM_BOARD WHERE MB_NO =?";

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

	public int modifyMemberBoard(Connection conn, String title, String content,int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE MEM_BOARD SET MB_TITLE=?, MB_CONT=? WHERE MB_NO=?";

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

	public ArrayList<MemberBoard> searchMemBoardListBoth(Connection conn, String search,String searchSelect, int currentPage, int recordCountPerPage){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT MEM_BOARD.*, ROW_NUMBER() OVER(ORDER BY MB_NO DESC) AS NUM FROM MEM_BOARD WHERE MB_TITLE LIKE ? AND MB_CONT LIKE ?) WHERE NUM BETWEEN ? AND ? ";

		ArrayList<MemberBoard> mList = null;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setString(2, "%"+search+"%");
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset = pstmt.executeQuery();
			mList = new ArrayList<MemberBoard>();
			while (rset.next()) {
				MemberBoard memBoard = new MemberBoard();
				memBoard.setMbNo(rset.getInt("MB_NO"));
				memBoard.setMbTitle(rset.getString("MB_TITLE"));
				memBoard.setMbCont(rset.getString("MB_CONT"));
				memBoard.setMbDate(rset.getDate("MB_DATE"));
				memBoard.setUserId(rset.getString("USER_ID"));
				mList.add(memBoard);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(rset);
		}
		return mList;
	}

	public ArrayList<MemberBoard> searchMemBoardListContent(Connection conn, String search,String searchSelect, int currentPage, int recordCountPerPage){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT MEM_BOARD.*, ROW_NUMBER() OVER(ORDER BY MB_NO DESC) AS NUM FROM MEM_BOARD WHERE MB_CONT LIKE ?) WHERE NUM BETWEEN ? AND ? ";

		ArrayList<MemberBoard> mList = null;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			mList = new ArrayList<MemberBoard>();
			while (rset.next()) {
				MemberBoard memBoard = new MemberBoard();
				memBoard.setMbNo(rset.getInt("MB_NO"));
				memBoard.setMbTitle(rset.getString("MB_TITLE"));
				memBoard.setMbCont(rset.getString("MB_CONT"));
				memBoard.setMbDate(rset.getDate("MB_DATE"));
				memBoard.setUserId(rset.getString("USER_ID"));
				mList.add(memBoard);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(rset);
		}
		return mList;
	}
	
	public ArrayList<MemberBoard> searchMemBoardListTitle(Connection conn, String search,String searchSelect, int currentPage, int recordCountPerPage){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT MEM_BOARD.*, ROW_NUMBER() OVER(ORDER BY MB_NO DESC) AS NUM FROM MEM_BOARD WHERE MB_TITLE LIKE ?) WHERE NUM BETWEEN ? AND ? ";

		ArrayList<MemberBoard> mList = null;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			mList = new ArrayList<MemberBoard>();
			while (rset.next()) {
				MemberBoard memBoard= new MemberBoard();
				memBoard.setMbNo(rset.getInt("MB_NO"));
				memBoard.setMbTitle(rset.getString("MB_TITLE"));
				memBoard.setMbCont(rset.getString("MB_CONT"));
				memBoard.setMbDate(rset.getDate("MB_DATE"));
				memBoard.setUserId(rset.getString("USER_ID"));
				mList.add(memBoard);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(rset);
		}
		return mList;
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
			sb.append("<a href='/memboard/list?currentPage="+(startNavi-1)+"'> < </a>");

		}
		for (int i = startNavi; i <=endNavi; i++) {
			if ( i == currentPage) {
				sb.append("<a href='/memboard/list?currentPage="+i+"'><b>"+i+"</b></a>");
			}else {
				sb.append("<a href='/memboard/list?currentPage="+i+"'><b>"+i+"</b></a>");
			}
		}

		if (needNext) {
			sb.append("<a href='/memboard/list?currentPage="+(endNavi+1)+"'> > </a>");
		}
		return sb.toString();

	}

	public int searchTotalCount (Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM MEM_BOARD";

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
