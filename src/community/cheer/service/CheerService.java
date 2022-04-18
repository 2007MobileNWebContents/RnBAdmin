package community.cheer.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import community.cheer.dao.CheerDAO;
import community.cheer.vo.PageData;
import home.model.vo.CheerBoard;

public class CheerService {
	
	private JDBCTemplate factory;

	public CheerService() {
		factory = JDBCTemplate.getConnection();
	}
	public PageData selectList(int currentPage) {
		ArrayList<CheerBoard> cheerList = null;
		Connection conn = null;
		int recordCountPerPage = 13;
		int naviCountPerPage = 5;
		PageData pd = new PageData();
		try {
			conn = factory.createConnection();
			pd.setPageList(new CheerDAO().selectList(conn, currentPage, recordCountPerPage));
			pd.setPageNavi(new CheerDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
			//			cheerList = new CheerDAO().selectList(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	public int insertCheer(String content, String userId) {
		Connection conn = null;
		int result = 0;
		try {
			//db연결생성
			conn = factory.createConnection();
			//			result = new CheerDAO().insertCheer(conn,content,userId);
			result = new CheerDAO().insertCheer(conn,content,userId);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}

		return result;
	}
	
	public int deleteCheer(int postNum, String userId) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new CheerDAO().deleteCheer(conn,postNum, userId);
			if(result >0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
}
