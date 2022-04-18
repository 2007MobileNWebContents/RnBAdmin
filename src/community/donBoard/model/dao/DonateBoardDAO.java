package community.donBoard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import community.donBoard.model.vo.DonBoardComment;
import community.donBoard.model.vo.DonateBoard;


public class DonateBoardDAO {

	public ArrayList<DonateBoard> selectDonList(Connection conn, int currentPage, int recordCountPerPage) {

		PreparedStatement pstmt = null;

		ResultSet rset = null;
		ArrayList<DonateBoard> dList = null;

		String query = "SELECT * FROM (SELECT DONAT_BOARD.*, ROW_NUMBER() OVER(ORDER BY DB_NO DESC) AS NUM FROM DONAT_BOARD) WHERE NUM BETWEEN ? AND ?";

		int start = currentPage*recordCountPerPage - (recordCountPerPage-1);
		int end = currentPage*recordCountPerPage;

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			dList= new ArrayList<DonateBoard>();
			while(rset.next()) {
				// �뿬湲곗뿉�꽌 �궗�슜�븯�뒗 noticeOne 媛앹껜�뒗 nLIst�뿉 
				// �뜲�씠�꽣瑜� ���옣�븯湲� �쐞�빐 �궗�슜�븯�뒗 �룄援�(�삷寃⑤떞湲� �쐞�빐�꽌)
				DonateBoard donBoard = new DonateBoard();
				// noticeOne(�룄瑜대젅) DB(�슦臾�)
				donBoard.setDbNo(rset.getInt("DB_NO"));
				donBoard.setDbTitle(rset.getString("DB_TITLE"));
				donBoard.setDbCont(rset.getString("DB_CONT"));
				donBoard.setDbDate(rset.getDate("DB_DATE"));
				donBoard.setUserId(rset.getString("USER_ID"));

				dList.add(donBoard);
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return dList;

	}
	public ArrayList<DonBoardComment> DonBoardCommentList (Connection conn, int noticeNo,int recordCountPerPage){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<DonBoardComment> dcList = null;
		String query = "SELECT * FROM DB_COM WHERE DB_NO=?";
		
//		int start = currentPage*recordCountPerPage - (recordCountPerPage-1);
//		int end = currentPage*recordCountPerPage;

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
//			pstmt.setInt(1, start);
//			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			dcList= new ArrayList<DonBoardComment>();
			while(rset.next()) {
				// �뿬湲곗뿉�꽌 �궗�슜�븯�뒗 noticeOne 媛앹껜�뒗 nLIst�뿉 
				// �뜲�씠�꽣瑜� ���옣�븯湲� �쐞�빐 �궗�슜�븯�뒗 �룄援�(�삷寃⑤떞湲� �쐞�빐�꽌)
				DonBoardComment commentOne = new DonBoardComment();
				// noticeOne(�룄瑜대젅) DB(�슦臾�)
				commentOne.setDonNo(rset.getInt("DB_COM_NO"));
				commentOne.setCommentContent(rset.getString("DB_COM_CONT"));
				commentOne.setUserId(rset.getString("DB_COM_USER"));
				commentOne.setRegDate(rset.getDate("DB_COM_DATE"));
//				noticeOne.setUserId(rset.getString("NOT_ADMIN"));

				dcList.add(commentOne);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return dcList;
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
		// �삤瑜섎갑吏�
		if (currentPage<1) {
			currentPage = 1;
		}else if (currentPage > pageTotalCount) {
			currentPage= pageTotalCount;
		}

		int startNavi =((currentPage - 1)/naviCountPerPage)* naviCountPerPage +1;
		int endNavi = startNavi + naviCountPerPage -1;

		// �삤瑜섎갑吏�
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
			sb.append("<a href='/donboard/list?currentPage="+(startNavi-1)+"'> < </a>");

		}
		for (int i = startNavi; i <=endNavi; i++) {
			if ( i == currentPage) {
				sb.append("<a href='/donboard/list?currentPage="+i+"'><b>"+i+"</b></a>");
			}else {
				sb.append("<a href='/donboard/list?currentPage="+i+"'><b>"+i+"</b></a>");
			}
		}

		if (needNext) {
			sb.append("<a href='/donboard/list?currentPage="+(endNavi+1)+"'> > </a>");
		}
		return sb.toString();


	}

	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM DONAT_BOARD";
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

	public int insertDonBoard(Connection conn, String title, String content, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query="INSERT INTO DONAT_BOARD VALUES (SEQ_DB_NO.NEXTVAL,?,?,SYSDATE,?)";

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
	public int insertDonBoardComment (Connection conn,int dbNo, String commentContent, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = " INSERT INTO DB_COM VALUES (SEQ_DB_COM_NO.NEXTVAL,?,SYSDATE,?,?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, commentContent);
			pstmt.setString(2, userId);
			pstmt.setInt(3, dbNo);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public DonateBoard selectDonBoard (Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DonateBoard donBoard = null;
		String query = "SELECT * FROM DONAT_BOARD WHERE DB_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				donBoard = new DonateBoard();
				donBoard.setDbNo(rset.getInt("DB_NO"));
				donBoard.setDbTitle(rset.getString("DB_TITLE"));
				donBoard.setDbCont(rset.getString("DB_CONT"));
				donBoard.setDbDate(rset.getDate("DB_DATE"));
				donBoard.setUserId(rset.getString("USER_ID"));

			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
		}
		return donBoard;
	}

	public int deleteDonBoard(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM DONAT_BOARD WHERE DB_NO =?";

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
	public int deleteDonBoardComment(Connection conn, int dbNo,int donNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM DB_COM WHERE DB_NO =? AND DB_COM_NO=?";

		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1,  dbNo);
			pstmt.setInt(2, donNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);

		}
		return result;
	}

	public int modifyDonBoard(Connection conn, String title, String content,int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE DONAT_BOARD SET DB_TITLE=?, DB_CONT=? WHERE DB_NO=?";

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

	public ArrayList<DonateBoard> searchDonBoardListBoth(Connection conn, String search,String searchSelect, int currentPage, int recordCountPerPage){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT DONAT_BOARD.*, ROW_NUMBER() OVER(ORDER BY DB_NO DESC) AS NUM FROM DONAT_BOARD WHERE DB_TITLE LIKE ? AND DB_CONT LIKE ?) WHERE NUM BETWEEN ? AND ? ";

		ArrayList<DonateBoard> dList = null;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setString(2, "%"+search+"%");
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset = pstmt.executeQuery();
			dList = new ArrayList<DonateBoard>();
			while (rset.next()) {
				DonateBoard donBoard = new DonateBoard();
				donBoard.setDbNo(rset.getInt("DB_NO"));
				donBoard.setDbTitle(rset.getString("DB_TITLE"));
				donBoard.setDbCont(rset.getString("DB_CONT"));
				donBoard.setDbDate(rset.getDate("DB_DATE"));
				donBoard.setUserId(rset.getString("USER_ID"));
				dList.add(donBoard);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(rset);
		}
		return dList;
	}

	public ArrayList<DonateBoard> searchDonBoardListContent(Connection conn, String search,String searchSelect, int currentPage, int recordCountPerPage){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT DONAT_BOARD.*, ROW_NUMBER() OVER(ORDER BY DB_NO DESC) AS NUM FROM DONAT_BOARD WHERE DB_CONT LIKE ?) WHERE NUM BETWEEN ? AND ? ";

		ArrayList<DonateBoard> mList = null;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			mList = new ArrayList<DonateBoard>();
			while (rset.next()) {
				DonateBoard donBoard = new DonateBoard();
				donBoard.setDbNo(rset.getInt("DB_NO"));
				donBoard.setDbTitle(rset.getString("DB_TITLE"));
				donBoard.setDbCont(rset.getString("DB_CONT"));
				donBoard.setDbDate(rset.getDate("DB_DATE"));
				donBoard.setUserId(rset.getString("USER_ID"));
				mList.add(donBoard);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(rset);
		}
		return mList;
	}
	
	public ArrayList<DonateBoard> searchDonBoardListTitle(Connection conn, String search,String searchSelect, int currentPage, int recordCountPerPage){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT DONAT_BOARD.*, ROW_NUMBER() OVER(ORDER BY DB_NO DESC) AS NUM FROM DONAT_BOARD WHERE DB_TITLE LIKE ?) WHERE NUM BETWEEN ? AND ? ";

		ArrayList<DonateBoard> dList = null;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			dList = new ArrayList<DonateBoard>();
			while (rset.next()) {
				DonateBoard donBoard= new DonateBoard();
				donBoard.setDbNo(rset.getInt("DB_NO"));
				donBoard.setDbTitle(rset.getString("DB_TITLE"));
				donBoard.setDbCont(rset.getString("DB_CONT"));
				donBoard.setDbDate(rset.getDate("DB_DATE"));
				donBoard.setUserId(rset.getString("USER_ID"));
				dList.add(donBoard);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(rset);
		}
		return dList;
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
			sb.append("<a href='/donboard/list?currentPage="+(startNavi-1)+"'> < </a>");

		}
		for (int i = startNavi; i <=endNavi; i++) {
			if ( i == currentPage) {
				sb.append("<a href='/donboard/list?currentPage="+i+"'><b>"+i+"</b></a>");
			}else {
				sb.append("<a href='/donboard/list?currentPage="+i+"'><b>"+i+"</b></a>");
			}
		}

		if (needNext) {
			sb.append("<a href='/donboard/list?currentPage="+(endNavi+1)+"'> > </a>");
		}
		return sb.toString();

	}

	public int searchTotalCount (Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM DONAT_BOARD";

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
