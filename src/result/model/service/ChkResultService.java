package result.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import result.model.dao.ChkResultDAO;
import result.model.vo.ChkResult;
import result.model.vo.Hospital;

public class ChkResultService {
	
	private JDBCTemplate factory;
	
	public ChkResultService() {
		factory = JDBCTemplate.getConnection();
	}
	
	//아이디 검색
	public ArrayList<ChkResult> searchUser(String userId){
		ArrayList<ChkResult> rlist = null;
		try {
			Connection conn = factory.createConnection();
			rlist = new ChkResultDAO().searchUser(conn, userId);
			JDBCTemplate.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rlist;
	}
	
	//병원 데이터
	public ArrayList<Hospital> selectHos(String userId){
		ArrayList<Hospital> hlist = null;
		try {
			Connection conn = factory.createConnection();
			hlist = new ChkResultDAO().selectHos(conn, userId);
			JDBCTemplate.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hlist;
	}
	

	//문진상세확인
	public ArrayList<ChkResult> selectDetail(int crCode) {
		ArrayList<ChkResult> dlist = null;
		try {
			Connection conn = factory.createConnection();
			dlist = new ChkResultDAO().selectDetail(conn, crCode);
			JDBCTemplate.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return dlist;
	}
	
	
	

}
