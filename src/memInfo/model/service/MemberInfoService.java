package memInfo.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import common.JDBCTemplate;
import memInfo.model.dao.MemberInfoDAO;
import memInfo.model.vo.PageData;

public class MemberInfoService {
	
	private JDBCTemplate factory;
	
	public MemberInfoService() {
		factory = JDBCTemplate.getConnection();
	}

	public PageData selectList(int currentPage) {
		Connection conn = null;
		PageData pd = new PageData();
		int recordCountPerPage = 15;
		int naviCountPerPage = 5;
		try {
			conn = factory.createConnection();
			pd.setPageList(new MemberInfoDAO().selectList(conn, currentPage, recordCountPerPage));
			pd.setPageNavi(new MemberInfoDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	
	
	public PageData searchList(String idName, int currentPage) {
		Connection conn = null;
		PageData pd = new PageData();
		int recordCountPerPage = 15;
		int naviCountPerPage = 5;
		try {
			conn = factory.createConnection();
			pd.setPageList(new MemberInfoDAO().searchList(conn, idName, currentPage, recordCountPerPage));
			pd.setPageNavi(new MemberInfoDAO().getSearchPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage, idName));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	
	public int deleteMemInfo(String userId) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new MemberInfoDAO().deleteMemInfo(conn, userId);
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
}
