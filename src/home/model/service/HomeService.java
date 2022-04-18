package home.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import home.model.dao.HomeDAO;
import home.model.vo.CheerBoard;
import home.model.vo.Notice;
import home.model.vo.TodayRCount;

public class HomeService {
	
	private JDBCTemplate factory;
	
	public HomeService() {
		factory = JDBCTemplate.getConnection();
	}

	public ArrayList<CheerBoard> selectCheerList(){
		ArrayList<CheerBoard> cmList = null;
		
		try {
			Connection conn = factory.createConnection();
			cmList = new HomeDAO().selectCheerList(conn);
			JDBCTemplate.close(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cmList;
	}
	
	
	public ArrayList<Notice> selectNoticeList(){
		ArrayList<Notice> notList = null;
		
		try {
			Connection conn = factory.createConnection();
			notList = new HomeDAO().selectNoticeList(conn);
			JDBCTemplate.close(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return notList;
	}
	
	
	public ArrayList<TodayRCount> selectRCountList(){
		ArrayList<TodayRCount> rcList = null;
		
		try {
			Connection conn = factory.createConnection();
			rcList = new HomeDAO().selectRCountList(conn);
			JDBCTemplate.close(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rcList;
	}
}
