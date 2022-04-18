package home.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
import home.model.vo.CheerBoard;
import home.model.vo.Notice;
import home.model.vo.TodayRCount;

public class HomeDAO {
	
	public ArrayList<CheerBoard> selectCheerList(Connection conn){
		ArrayList<CheerBoard> cmList = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM CHEER_BOARD WHERE ROWNUM <= 5 ORDER BY CB_NO DESC";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			cmList = new ArrayList<CheerBoard>();
			
			while(rset.next()) {
				CheerBoard cb = new CheerBoard();
				cb.setCbNo(rset.getInt("CB_NO"));
				cb.setCbCont(rset.getString("CB_CONT"));
				cb.setCbDate(rset.getDate("CB_DATE"));
				cb.setUserId(rset.getString("USER_ID"));
				cmList.add(cb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return cmList;
	}
	
	
	public ArrayList<Notice> selectNoticeList(Connection conn){
		ArrayList<Notice> notList = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM NOTICE WHERE ROWNUM <= 6 ORDER BY NOT_NO DESC";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			notList = new ArrayList<Notice>();
			
			while(rset.next()) {
				Notice no = new Notice();
				no.setNotNo(rset.getInt("NOT_NO"));
				no.setNotTitle(rset.getString("NOT_TITLE"));
				no.setNotCont(rset.getString("NOT_CONT"));
				no.setNotDate(rset.getDate("NOT_DATE"));
				no.setNotAdmin(rset.getString("NOT_ADMIN"));
				notList.add(no);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(rset);
		}
		
		return notList;
	}
	
	
	public ArrayList<TodayRCount> selectRCountList(Connection conn){
		ArrayList<TodayRCount> rcList = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT M.USER_ABO, COUNT(P.RP_NO) AS RCOUNT FROM MEMBER M LEFT JOIN RES_PRIV P ON (M.USER_ID = P.USER_ID) WHERE (RP_STATE !='취소') AND (RP_DATE = TO_CHAR(SYSDATE,'YYYY-MM-DD')) OR RP_DATE IS NULL GROUP BY M.USER_ABO ORDER BY 1";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			rcList = new ArrayList<TodayRCount>();
			
			while(rset.next()) {
				TodayRCount tr = new TodayRCount();
				tr.setUserABO(rset.getString("USER_ABO"));
				tr.setrCount(rset.getInt("RCOUNT"));
				rcList.add(tr);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		
		return rcList;
	}
}
