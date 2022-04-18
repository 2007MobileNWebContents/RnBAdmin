package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
import member.model.vo.Member;

public class UserDAO {

   public Member selectOne(Connection conn,String userId) {
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      String query = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";
      Member user = null;

      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, userId);
         rset = pstmt.executeQuery();
         if(rset.next()) { 
            user = new Member();
            user.setUserId(rset.getString("USER_ID"));
            user.setUserPwd(rset.getString("USER_PWD"));
            user.setUserName(rset.getString("USER_NAME"));
            user.setUserBD(rset.getString("USER_BD"));
            user.setUserAddrPost(rset.getString("USER_ADDR_POST"));
            user.setUserAddrRoad(rset.getString("USER_ADDR_ROAD"));
            user.setUserAddrDetail(rset.getString("USER_ADDR_DETAIL"));
            user.setUserEmail(rset.getString("USER_EMAIL"));
            user.setUserPhone(rset.getString("USER_PHONE"));
            user.setUserGender(rset.getString("USER_GENDER"));
            user.setUserABO(rset.getString("USER_ABO"));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      finally {
         JDBCTemplate.close(pstmt);
         JDBCTemplate.close(rset);
      }
      return user;
   }
   public Member selectOne(Connection conn,String userId,String userPwd) {
      
      Statement stmt=null;
      ResultSet rset=null;
      String query="SELECT * FROM MEMBER WHERE (USER_ID='"+userId+"') AND (USER_PWD ='" + userPwd + "') AND (USER_YN ='Y')";
      System.out.println(query);
      Member user = null;
      try {
         stmt = conn.createStatement();
         rset = stmt.executeQuery(query);
         if(rset.next()) {
            user = new Member();
            user.setUserId(rset.getString("USER_ID"));
            user.setUserPwd(rset.getString("USER_PWD"));
            user.setUserName(rset.getString("USER_NAME"));
            user.setUserBD(rset.getString("USER_BD"));
            user.setUserAddrPost(rset.getString("USER_ADDR_POST"));
            user.setUserAddrRoad(rset.getString("USER_ADDR_ROAD"));
            user.setUserAddrDetail(rset.getString("USER_ADDR_DETAIL"));
            user.setUserEmail(rset.getString("USER_EMAIL"));
            user.setUserPhone(rset.getString("USER_PHONE"));
            user.setUserGender(rset.getString("USER_GENDER"));
            user.setUserABO(rset.getString("USER_ABO"));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         JDBCTemplate.close(stmt);
         JDBCTemplate.close(rset);
      }
      return user;
      
   }
   public int insertUser(Connection conn,Member user) {
      PreparedStatement pstmt = null;
      int result  = 0;
      String query ="INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?,?,?, ?, ?, 'Y')";
      try {
         System.out.println(user);
         pstmt =conn.prepareStatement(query);
         pstmt.setString(1, user.getUserId());
         pstmt.setString(2, user.getUserPwd());
         pstmt.setString(3, user.getUserName());
         pstmt.setString(4, user.getUserBD());
         pstmt.setString(5, user.getUserGender());
         pstmt.setString(6, user.getUserPhone());
         pstmt.setString(7, user.getUserAddrPost());
         pstmt.setString(8, user.getUserAddrRoad());
         pstmt.setString(9, user.getUserAddrDetail());
         pstmt.setString(10, user.getUserEmail());
         pstmt.setString(11, user.getUserABO());
         result = pstmt.executeUpdate();
         System.out.println(user.toString());
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         JDBCTemplate.close(pstmt);
      }
      return result;
   }
   public int updateUser(Connection conn,Member user) {
      int result =0;
      PreparedStatement pstmt = null;
      String query = "UPDATE MEMBER SET USER_PWD = ?, USER_NAME=?, USER_EMAIL = ?,USER_PHONE = ? ,USER_ADDR_POST = ?, USER_ADDR_ROAD = ?, USER_ADDR_DETAIL = ? WHERE USER_ID = ?";
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, user.getUserPwd());
         pstmt.setString(2, user.getUserName());
         pstmt.setString(3, user.getUserEmail());
         pstmt.setString(4, user.getUserPhone());
         pstmt.setString(5, user.getUserAddrPost());
         pstmt.setString(6, user.getUserAddrRoad());
         pstmt.setString(7, user.getUserAddrDetail());
         pstmt.setString(8, user.getUserId());
         result =pstmt.executeUpdate();
      }catch(SQLException e) {
         e.printStackTrace();
      }finally {
         JDBCTemplate.close(pstmt);
      }
      return result;
   }
   public int deleteUser(Connection conn, String userId) {
      int result =0;
      PreparedStatement pstmt =null;
      String query="DELETE FROM MEMBER WHERE USER_ID =?";
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, userId);
         result = pstmt.executeUpdate();
      }catch(SQLException e) {
         e.printStackTrace();
      }finally {
         JDBCTemplate.close(pstmt);
      }
      return result;
   }
   public int insertAdmin(Connection conn,Member admin) {
      PreparedStatement pstmt = null;
      int result  = 0;
      String query ="INSERT INTO MEMBER VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'N')";
      try {
         pstmt =conn.prepareStatement(query);
         pstmt.setString(1, admin.getUserId());
         pstmt.setString(2, admin.getUserPwd());
         pstmt.setString(3, admin.getUserName());
         pstmt.setString(4, admin.getUserBD());
         pstmt.setString(5, admin.getUserPhone());
         pstmt.setString(6, admin.getUserAddrPost());
         pstmt.setString(7, admin.getUserAddrRoad());
         pstmt.setString(8, admin.getUserAddrDetail());
         pstmt.setString(9, admin.getUserEmail());
         pstmt.setString(10, admin.getUserGender());
         pstmt.setString(11, admin.getUserABO());
         result = pstmt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         JDBCTemplate.close(pstmt);
      }
      return result;
   }
   public Member selectAdmin(Connection conn, String adminId, String adminPwd) {
      Statement stmt = null;
      ResultSet rset = null;
      String query ="SELECT * FROM MEMBER WHERE USER_ID='" + adminId + "'AND USER_PWD ='" + adminPwd + "'";
      Member admin = null;
      try {
         stmt = conn.createStatement();
         rset = stmt.executeQuery(query);
         if(rset.next()) {
            admin = new Member();
            admin.setUserId(rset.getString("USER_ID"));
            admin.setUserPwd(rset.getString("USER_PWD"));
            admin.setUserName(rset.getString("USER_NAME"));
            admin.setUserBD(rset.getString("USER_BD"));
            admin.setUserAddrPost(rset.getString("USER_ADDR_POST"));
            admin.setUserAddrRoad(rset.getString("USER_ADDR_ROAD"));
            admin.setUserAddrDetail(rset.getString("USER_ADDR_DETAIL"));
            admin.setUserEmail(rset.getString("USER_EMAIL"));
            admin.setUserPhone(rset.getString("USER_PHONE"));
            admin.setUserGender(rset.getString("USER_GENDER"));
         }
         }catch(Exception e) {
            e.printStackTrace();
         }finally {
            JDBCTemplate.close(stmt);
            JDBCTemplate.close(rset);
         }
         return admin;
      }
   
   public int idCheck(Connection conn, String id) {
         Statement stmt=null;
         ResultSet rset=null;
         int result = 0;
         String query="SELECT COUNT(*) FROM MEMBER WHERE USER_ID='"+id+"'";
         try {
            stmt = conn.createStatement();
            rset = stmt.executeQuery(query);
            
            if(rset.next()) {
               result = rset.getInt(1);
            }
         } catch (Exception e) {
            e.printStackTrace();
         }finally {
            JDBCTemplate.close(stmt);
            JDBCTemplate.close(rset);
         }
         return result;
      }
   
   public int confirmPassword(Connection conn, String beforeUserPwd, String userId) {
      int result = 0;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      String query ="SELECT * FROM MEMBER WHERE USER_ID=? AND USER_PWD=?";
      
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, userId);
         pstmt.setString(2, beforeUserPwd);
         rset = pstmt.executeQuery();
         while(rset.next()) {
            result++;
         }
         
      }
      catch (Exception e) {
         e.printStackTrace();
      }
      finally {
         JDBCTemplate.close(rset);
         JDBCTemplate.close(pstmt);
      }
      
      return result;
   }
   
}


