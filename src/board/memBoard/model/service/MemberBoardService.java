package board.memBoard.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import board.memBoard.model.dao.MemBoardDAO;
import board.memBoard.model.vo.MemberBoard;
import board.notice.model.vo.PageData;
import common.JDBCTemplate;

public class MemberBoardService {

	private JDBCTemplate factory;
	public MemberBoardService() {
		factory = JDBCTemplate.getConnection();
	}
	
	
	public PageData selectMemList (int currentPage) {
		Connection conn = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		PageData pd = new PageData();
		
		try {
			conn = factory.createConnection();
			pd.setmPageList(new MemBoardDAO().selectMemList(conn, currentPage, recordCountPerPage));
			pd.setPageNavi(new MemBoardDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	
	public PageData commentList( int mbNo) {
		Connection conn = null;
//		int recordCountPerPage = 20;
//		int naviCountPerPage = 5;
		PageData pd = new PageData();
		
		try {
			conn = factory.createConnection();
			pd.setMcPageList(new MemBoardDAO().MemBoardCommentList(conn, mbNo));
			//pd.setPageNavi(new NoticeDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return pd;
		
	}
	
	public int insertMemBoard(String title, String content, String userId) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new MemBoardDAO().insertMemberBoard(conn, title, content, userId);
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
	
	
	public int insertMemBoardComment(int mbNo, String commentContent, String userId) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new MemBoardDAO().insertMemBoardComment(conn,mbNo,commentContent, userId);
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
	
	public MemberBoard selectMemBoard(int mbNo) {
		MemberBoard memBoard = null;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			memBoard = new MemBoardDAO().selectMemBoard(conn, mbNo);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return memBoard;
	}
	
	public int deleteMemBoard(int noticeNo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new MemBoardDAO().deleteMemberBoard(conn, noticeNo);
			
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
	public int deleteMemBoardComment(int mbNo, int memNo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new MemBoardDAO().deleteMemBoardComment(conn, mbNo, memNo);

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
	
	public int modifyMemBoard(String title, String content, int noticeNo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new MemBoardDAO().modifyMemberBoard(conn, title, content, noticeNo);
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
	
	public PageData searchMemBoardList (String search, int currentPage, String searchSelect ) {
		Connection conn = null;
		PageData pd = new PageData();
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		
		try {
			conn = factory.createConnection();
			if (searchSelect.equals("both")) {
				pd.setmPageList(new MemBoardDAO().searchMemBoardListBoth(conn, search, searchSelect, currentPage, recordCountPerPage));
			}else if ( searchSelect.equals("content")) {
				pd.setmPageList(new MemBoardDAO().searchMemBoardListContent(conn, search, searchSelect, currentPage, recordCountPerPage));
			}
			else if (searchSelect.equals("title")){
				pd.setmPageList(new MemBoardDAO().searchMemBoardListTitle(conn, search, searchSelect, currentPage, recordCountPerPage));
			}
			
			pd.setPageNavi(new MemBoardDAO().getSearchPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage, search));
			
		}catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
}
