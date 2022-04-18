package resResult.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import resResult.model.vo.ResPriv;
import resResult.model.vo.ResResult;

public class ResResultDAO {

	public int insertResResult(Connection conn,ResResult Rresult) {
		PreparedStatement pstmt = null;
		int result  = 0;
		String query ="INSERT INTO RES_RESULT VALUES (?,?,?,?,?,?,?,?)";
		try {
			System.out.println(Rresult);
			pstmt =conn.prepareStatement(query);
			pstmt.setInt(1, Rresult.getRpNo());
			pstmt.setString(2, Rresult.getResId());
			pstmt.setString(3, Rresult.getResB());
			pstmt.setString(4, Rresult.getResC());
			pstmt.setInt(5, Rresult.getResAlt());
			pstmt.setInt(6, Rresult.getResPro());
			pstmt.setInt(7, Rresult.getResAst());
			pstmt.setInt(8, Rresult.getResCol());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<ResPriv> ResList(Connection conn, String userId) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ResPriv> list = new ArrayList<ResPriv>();
		
		String query = "SELECT * FROM RES_PRIV WHERE (ROWNUM <= 4) AND (USER_ID = ?) AND (RP_STATE = '완료') ORDER BY RP_NO DESC";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,userId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new ResPriv(rset.getInt("RP_NO"),
						              rset.getString("USER_ID"),
						              rset.getDate("RP_DATE"),
						              rset.getString("RP_PLACE"),
						              rset.getString("RP_TIME"),
						              rset.getString("RP_KIND"),
						              rset.getString("RP_STATE"),
						              rset.getString("RP_ON")
						              ));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		return list;
	}

	public ResResult ResResult(Connection conn, int num) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ResResult rResult = null;
		String query = "SELECT *  FROM RES_PRIV JOIN RES_RESULT USING (RP_NO) WHERE RP_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,num);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				rResult = new ResResult(rset.getString("RES_B"),
						                rset.getString("RES_C"),
						                rset.getInt("RES_ALT"),
						                rset.getInt("RES_PRO"),
						                rset.getInt("RES_AST"),
						                rset.getInt("RES_COL")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return rResult;
	}
	
	public ArrayList<ResPriv> selectResResult(Connection conn,String resId)  {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ResPriv> resList = null;
		String query ="SELECT * FROM (SELECT *  FROM RES_PRIV LEFT JOIN RES_RESULT ON RES_PRIV.RP_NO = RES_RESULT.RP_NO WHERE (RP_STATE = '완료') AND (ROWNUM <=4) AND (RES_PRIV.USER_ID = ?) ORDER BY RES_PRIV.RP_DATE DESC) WHERE RES_ALT IS NULL";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1,resId);
			rset = pstmt.executeQuery();
			resList = new ArrayList<ResPriv>();
			while(rset.next()) {
				ResPriv priv = new ResPriv();
				priv.setRpNo(rset.getInt("RP_NO"));
				priv.setRpDate(rset.getDate("RP_DATE"));
				priv.setRpPlace(rset.getString("RP_PLACE"));
				priv.setRpTime(rset.getString("RP_TIME"));
				priv.setRpKind(rset.getString("RP_KIND"));
				priv.setRpState(rset.getString("RP_STATE"));
				priv.setRpOn(rset.getString("RP_ON"));
				priv.setUserId(rset.getString("USER_ID"));
				resList.add(priv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return resList;
	}
	
}
