package resResult.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import resResult.model.dao.ResResultDAO;
import resResult.model.vo.ResPriv;
import resResult.model.vo.ResResult;

public class ResResultService {
	
	private JDBCTemplate factory;
	public ResResultService() {
		factory = JDBCTemplate.getConnection();
	}

	public ArrayList<ResPriv> ResList(String userId) {
		
		ArrayList<ResPriv> list = null;
			
			try {
				Connection conn = factory.createConnection();
				 list = new ResResultDAO().ResList(conn,userId);
				JDBCTemplate.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return list;
	}
	public ResResult ResResult(int num) {
		
		ResResult rResult = null;
		
		try {
			Connection conn = factory.createConnection();
			rResult = new ResResultDAO().ResResult(conn,num);
			JDBCTemplate.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return rResult;
	}
	public int insertResResult(ResResult Rresult) {
		int result=0;
		try {
			Connection conn = factory.createConnection();
			result =new ResResultDAO().insertResResult(conn,Rresult);
			JDBCTemplate.close(conn);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<ResPriv> selectResResult(String resId) {
		ArrayList<ResPriv> resList =null;
			
		
		try {
			Connection conn = factory.createConnection();
			resList = new ResResultDAO().selectResResult(conn,resId);
			JDBCTemplate.close(conn);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return resList;
	}
}
