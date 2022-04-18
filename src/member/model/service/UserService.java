package member.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import common.JDBCTemplate;
import member.model.dao.UserDAO;
import member.model.vo.Member;

public class UserService {
	
	private JDBCTemplate factory;
	public UserService() {
		factory = JDBCTemplate.getConnection();
	}
	public Member selectOne(String userId, String userPwd) {
		Member user =null;
		try {
			Connection conn = factory.createConnection();
			user = new UserDAO().selectOne(conn,userId,userPwd);
			JDBCTemplate.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	public Member selectAdmin(String adminId,String adminPwd) {
		Member admin =null;
		try {
			Connection conn = factory.createConnection();
			admin = new UserDAO().selectAdmin(conn,adminId,adminPwd);
			JDBCTemplate.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}
	public int insertUser(Member user) {
		int result = 0;
		try {
			Connection conn = factory.createConnection();
			result = new UserDAO().insertUser(conn,user);
			JDBCTemplate.close(conn);
		}catch(SQLException e){
			e.printStackTrace();
		}
		 return result;
	}
	public int updateUser(Member user) {
		int result = 0;
		try {
			Connection conn = factory.createConnection();
			result = new UserDAO().updateUser(conn,user);
			JDBCTemplate.close(conn);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public int deleteUser(String userId) {
		int result = 0;
		try {
			Connection conn = factory.createConnection();
			result = new UserDAO().deleteUser(conn,userId);
			JDBCTemplate.close(conn);
		}catch(SQLException e){
			e.printStackTrace();	
		}
		return result;
	}
	public int insertAdmin(Member admin) {
		int result=0;
		try {
			Connection conn = factory.createConnection();
			result =new UserDAO().insertAdmin(conn,admin);
			JDBCTemplate.close(conn);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int idCheck(String id) {
	      int result=0;
	      try {
	         Connection conn = factory.createConnection();
	         result =new UserDAO().idCheck(conn,id);
	         JDBCTemplate.close(conn);
	      }catch(SQLException e) {
	         e.printStackTrace();
	      }
	      return result;
	   }

}
