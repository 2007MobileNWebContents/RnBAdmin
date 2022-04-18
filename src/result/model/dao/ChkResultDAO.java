package result.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import result.model.vo.ChkResult;
import result.model.vo.Hospital;

public class ChkResultDAO {
	
	//아이디 검색
	public ArrayList<ChkResult> searchUser(Connection conn, String userId){
		ArrayList<ChkResult> rlist = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM CHK_RESULT WHERE USER_ID = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			rlist = new ArrayList<ChkResult>();
			while(rset.next()) {
				ChkResult chkResult = new ChkResult();
				chkResult.setUserId(rset.getString("USER_ID"));
				chkResult.setCrCode(rset.getInt("CR_CODE"));
				chkResult.setCrDate(rset.getDate("CR_DATE"));
				chkResult.setCrRes(rset.getString("CR_RES"));
				rlist.add(chkResult);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	    	  JDBCTemplate.close(rset);
	    	  JDBCTemplate.close(pstmt);
		}
		return rlist;
	}
	
	//병원
	public ArrayList<Hospital> selectHos(Connection conn, String userId){
		ArrayList<Hospital> hlist = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT DISTINCT HP_DATE, HP_DIS FROM HOSPITAL JOIN CHK_RESULT ON CHK_RESULT.CR_NUMBER = HOSPITAL.HP_NUMBER JOIN BAN_DIS ON BAN_DIS.BI_NAME = HOSPITAL.HP_DIS WHERE CHK_RESULT.USER_ID = ? AND TO_CHAR(CHK_RESULT.CR_DATE,'YYYYMMDD') > TO_CHAR(HOSPITAL.HP_DATE,'YYYYMMDD') - BAN_DIS.BI_DATE UNION SELECT DISTINCT HP_DATE, HP_CHECK FROM HOSPITAL JOIN CHK_RESULT ON CHK_RESULT.CR_NUMBER = HOSPITAL.HP_NUMBER JOIN BAN_CHECK ON BAN_CHECK.BC_NAME = HOSPITAL.HP_CHECK WHERE CHK_RESULT.USER_ID = ? AND TO_CHAR(CHK_RESULT.CR_DATE,'YYYYMMDD') > TO_CHAR(HOSPITAL.HP_DATE,'YYYYMMDD') - BAN_CHECK.BC_DATE UNION SELECT DISTINCT HP_DATE, HP_MEDI FROM HOSPITAL JOIN CHK_RESULT ON CHK_RESULT.CR_NUMBER = HOSPITAL.HP_NUMBER JOIN BAN_DRG ON BAN_DRG.BD_NAME = HOSPITAL.HP_MEDI WHERE CHK_RESULT.USER_ID = ? AND TO_CHAR(CHK_RESULT.CR_DATE,'YYYYMMDD') > TO_CHAR(HOSPITAL.HP_DATE,'YYYYMMDD') - BAN_DRG.BD_DATE ORDER BY 1";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			pstmt.setString(3, userId);
			rset = pstmt.executeQuery();
			hlist = new ArrayList<Hospital>();
			while(rset.next()) {
				Hospital hospital = new Hospital();
				hospital.setHpDate(rset.getDate("HP_DATE"));
//				hospital.setHpCheck(rset.getString("HP_CHECK"));
//				hospital.setHpMedi(rset.getString("HP_MEDI"));
				hospital.setHpDis(rset.getString("HP_DIS"));
				hlist.add(hospital);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	    	  JDBCTemplate.close(rset);
	    	  JDBCTemplate.close(pstmt);
		}
		return hlist;
		
	}
	
//	public ArrayList<Hospital> selectHosChk(Connection conn, String userId){
//		ArrayList<Hospital> clist = null;
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		String query = "SELECT DISTINCT HP_DATE, HP_DIS FROM HOSPITAL JOIN CHK_RESULT ON CHK_RESULT.CR_NUMBER = HOSPITAL.HP_NUMBER JOIN BAN_DIS ON BAN_DIS.BI_NAME = HOSPITAL.HP_DIS WHERE CHK_RESULT.USER_ID = ? AND TO_CHAR(CHK_RESULT.CR_DATE,'YYYYMMDD') < TO_CHAR(HOSPITAL.HP_DATE,'YYYYMMDD') - BAN_DIS.BI_DATE UNION SELECT DISTINCT HP_DATE, HP_CHECK FROM HOSPITAL JOIN CHK_RESULT ON CHK_RESULT.CR_NUMBER = HOSPITAL.HP_NUMBER JOIN BAN_CHECK ON BAN_CHECK.BC_NAME = HOSPITAL.HP_CHECK WHERE CHK_RESULT.USER_ID = ? AND TO_CHAR(CHK_RESULT.CR_DATE,'YYYYMMDD') < TO_CHAR(HOSPITAL.HP_DATE,'YYYYMMDD') - BAN_CHECK.BC_DATE UNION SELECT DISTINCT HP_DATE, HP_MEDI FROM HOSPITAL JOIN CHK_RESULT ON CHK_RESULT.CR_NUMBER = HOSPITAL.HP_NUMBER JOIN BAN_DRG ON BAN_DRG.BD_NAME = HOSPITAL.HP_MEDI WHERE CHK_RESULT.USER_ID = ? AND TO_CHAR(CHK_RESULT.CR_DATE,'YYYYMMDD') < TO_CHAR(HOSPITAL.HP_DATE,'YYYYMMDD') - BAN_DRG.BD_DATE ORDER BY 1";
//		try {
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, userId);
//			rset = pstmt.executeQuery();
//			clist = new ArrayList<Hospital>();
//			while(rset.next()) {
//				Hospital hospital = new Hospital();
//				hospital.setHpDate(rset.getDate("HP_DATE"));
//				hospital.setHpCheck(rset.getString("HP_CHECK"));
//				hospital.setHpMedi(rset.getString("HP_MEDI"));
//				hospital.setHpDis(rset.getString("HP_DIS"));
//				clist.add(hospital);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//	    	  JDBCTemplate.close(rset);
//	    	  JDBCTemplate.close(pstmt);
//		}
//		return clist;
//		
//	}
	
	
	//상세출력
	public ArrayList<ChkResult> selectDetail(Connection conn, int crCode){
		ArrayList<ChkResult> dlist = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM CHK_RESULT WHERE CR_CODE = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, crCode);
			rset = pstmt.executeQuery();
			dlist = new ArrayList<ChkResult>();
			while(rset.next()) {
				ChkResult chkResult = new ChkResult();
				chkResult.setCrBody(rset.getString("CR_BODY"));
				chkResult.setCrIn(rset.getString("CR_IN"));
				chkResult.setCrOut(rset.getString("CR_OUT"));
				chkResult.setCrDrg(rset.getString("CR_DRG"));
				chkResult.setCrKor(rset.getString("CR_KOR"));
				chkResult.setCrOver(rset.getString("CR_OVER"));
				chkResult.setCrFamily(rset.getString("CR_FAMILY"));
				chkResult.setCrDate(rset.getDate("CR_DATE"));
				dlist.add(chkResult);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return dlist;
	}
	
	
	

}
