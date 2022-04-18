package board.notice.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import board.notice.model.dao.NoticeDAO;
import board.notice.model.vo.Notice;
import board.notice.model.vo.NoticeComment;
import board.notice.model.vo.PageData;

public class NoticeService {

	private JDBCTemplate factory;
	public NoticeService() {
		factory = JDBCTemplate.getConnection();
	}

	public PageData selectList (int currentPage) {
		Connection conn = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		PageData pd = new PageData();

		try {
			conn = factory.createConnection();
			pd.setPageList(new NoticeDAO().selectList(conn, currentPage, recordCountPerPage));
			pd.setPageNavi(new NoticeDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}

	public PageData commentList(  int noticeNo) {
		Connection conn = null;
		int recordCountPerPage = 20;
		int naviCountPerPage = 5;
		PageData pd = new PageData();

		try {
			conn = factory.createConnection();
			pd.setcPageList(new NoticeDAO().noticeCommentList(conn,noticeNo));
			//pd.setPageNavi(new NoticeDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return pd;

	}

	public int insertNotice(String title, String content, String userId) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new NoticeDAO().insertNotice(conn, title, content, userId);
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

	public int insertNoticeComment(int noticeNo, String commentContent, String userId) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new NoticeDAO().insertNoticeComment(conn,noticeNo,commentContent, userId);
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
	

	public Notice selectNotice(int noticeNo) {
		Notice notice = null;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			notice = new NoticeDAO().selectNotice(conn, noticeNo);

		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return notice;
	}

	public int deleteNotice(int noticeNo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new NoticeDAO().deleteNotice(conn, noticeNo);

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
	public int deleteNoticeComment(int noticeNo, int comNo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new NoticeDAO().deleteNoticeComment(conn, noticeNo, comNo);

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

	public int modifyNotice(String title, String content, int noticeNo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new NoticeDAO().modifyNotice(conn, title, content, noticeNo);
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

	public PageData searchNoticeList (String search, int currentPage, String searchSelect ) {
		Connection conn = null;
		PageData pd = new PageData();
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;

		try {
			conn = factory.createConnection();
			if (searchSelect.equals("both")) {
				pd.setPageList(new NoticeDAO().searchNoticeListBoth(conn, search, searchSelect, currentPage, recordCountPerPage));
			}else if ( searchSelect.equals("content")) {
				pd.setPageList(new NoticeDAO().searchNoticeListContent(conn, search, searchSelect, currentPage, recordCountPerPage));
			}
			else if (searchSelect.equals("title")){
				pd.setPageList(new NoticeDAO().searchNoticeListTitle(conn, search, searchSelect, currentPage, recordCountPerPage));
			}

			pd.setPageNavi(new NoticeDAO().getSearchPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage, search));

		}catch (SQLException e) {
			e.printStackTrace();

		}finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
}
