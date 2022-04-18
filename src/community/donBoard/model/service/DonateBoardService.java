package community.donBoard.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import board.notice.model.vo.PageData;
import common.JDBCTemplate;
import community.donBoard.model.dao.DonateBoardDAO;
import community.donBoard.model.vo.DonateBoard;

public class DonateBoardService {

	private JDBCTemplate factory;
	public DonateBoardService() {
		factory = JDBCTemplate.getConnection();
	}
	
	public PageData selectDonList (int currentPage) {
		Connection conn = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		PageData pd = new PageData();

		try {
			conn = factory.createConnection();
			pd.setdPageList(new DonateBoardDAO().selectDonList(conn, currentPage, recordCountPerPage));
			pd.setPageNavi(new DonateBoardDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}

	public PageData commentList( int currentPage) {
		Connection conn = null;
		int recordCountPerPage = 20;
		int naviCountPerPage = 5;
		PageData pd = new PageData();

		try {
			conn = factory.createConnection();
			pd.setDcPageList(new DonateBoardDAO().DonBoardCommentList(conn, currentPage, recordCountPerPage));
			pd.setPageNavi(new DonateBoardDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return pd;

	}

	public int insertDonBoard(String title, String content, String userId) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new DonateBoardDAO().insertDonBoard(conn, title, content, userId);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

	public int insertDonBoardComment(int dbNo, String commentContent, String userId) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new DonateBoardDAO().insertDonBoardComment(conn,dbNo,commentContent, userId);
		}catch(SQLException e ) {
			e.printStackTrace();
		}
		if (result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

	public DonateBoard selectDonBoard(int noticeNo) {
		DonateBoard notice = null;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			notice = new DonateBoardDAO().selectDonBoard(conn, noticeNo);

		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return notice;
	}

	public int deleteDonBoard(int noticeNo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new DonateBoardDAO().deleteDonBoard(conn, noticeNo);

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		if (result>0 ) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}
	public int deleteDonBoardComment(int dbNo, int donNo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new DonateBoardDAO().deleteDonBoardComment(conn, dbNo, donNo);

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		if (result>0 ) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

	public int modifyDonBoard(String title, String content, int noticeNo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new DonateBoardDAO().modifyDonBoard(conn, title, content, noticeNo);
			if (result > 0 ) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}

		} catch(SQLException e ) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

	public PageData searchDonBoardList (String search, int currentPage, String searchSelect ) {
		Connection conn = null;
		PageData pd = new PageData();
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;

		try {
			conn = factory.createConnection();
			if (searchSelect.equals("both")) {
				pd.setdPageList(new DonateBoardDAO().searchDonBoardListBoth(conn, search, searchSelect, currentPage, recordCountPerPage));
			}else if ( searchSelect.equals("content")) {
				pd.setdPageList(new DonateBoardDAO().searchDonBoardListContent(conn, search, searchSelect, currentPage, recordCountPerPage));
			}
			else if (searchSelect.equals("title")){
				pd.setdPageList(new DonateBoardDAO().searchDonBoardListTitle(conn, search, searchSelect, currentPage, recordCountPerPage));
			}

			pd.setPageNavi(new DonateBoardDAO().getSearchPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage, search));

		}catch (SQLException e) {
			e.printStackTrace();

		}finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
}
