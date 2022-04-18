package faq.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import faq.model.vo.FAQ;
import common.JDBCTemplate;

public class FAQDAO {

	public ArrayList<FAQ> faqList(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt = null;

		ResultSet rset = null;
		ArrayList<FAQ> fList = null;

		String query = "SELECT * FROM (SELECT FAQ.*, ROW_NUMBER() OVER(ORDER BY FAQ_NO DESC) AS NUM FROM FAQ) WHERE NUM BETWEEN ? AND ?";

		int start = currentPage*recordCountPerPage - (recordCountPerPage-1);
		int end = currentPage*recordCountPerPage;
		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			fList= new ArrayList<FAQ>();

			while(rset.next()) {
				FAQ faq = new FAQ();
				faq.setFaqNo(rset.getInt("FAQ_NO"));
				faq.setFaqQ(rset.getString("FAQ_Q"));
				faq.setFaqA(rset.getString("FAQ_A"));
				fList.add(faq);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return fList;

	}

	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		int recordTotalCount = totalCount(conn);
		int pageTotalCount = 0;
		if(recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount/ recordCountPerPage + 1;
		}else {
			pageTotalCount = recordTotalCount/ recordCountPerPage;
		}
		if (currentPage<1) {
			currentPage = 1;
		}else if (currentPage > pageTotalCount) {
			currentPage= pageTotalCount;
		}

		int startNavi =((currentPage - 1)/naviCountPerPage)* naviCountPerPage +1;
		int endNavi = startNavi + naviCountPerPage -1;

		if (endNavi> pageTotalCount) {
			endNavi = pageTotalCount;
		}

		boolean needPrev = true;
		boolean needNext = true;

		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount ) {
			needNext = false;
		}

		StringBuilder sb = new StringBuilder();
		if (needPrev) {
			sb.append("<a href='/faq/list?currentPage="+(startNavi-1)+"'> < </a>");

		}
		for (int i = startNavi; i <= endNavi; i++) {
			if ( i == currentPage) {
				sb.append("<a href='/faq/list?currentPage="+i+"'><b>"+i+"</b></a>");
			}else {
				sb.append("<a href='/faq/list?currentPage="+i+"'><b>"+i+"</b></a>");
			}
		}

		if (needNext) {
			sb.append("<a href='/faq/list?currentPage="+(endNavi+1)+"'> > </a>");
		}
		return sb.toString();
	}
	
	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM FAQ";
		int recordTotalCount = 0;
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
		}
		return recordTotalCount;

	}

	public int deleteFAQ(Connection conn, int faqNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM FAQ WHERE FAQ_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, faqNo);
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int modifyFAQ(Connection conn, String qusetion, String answer, int faqNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE FAQ SET FAQ_Q=?, FAQ_A=? WHERE FAQ_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, qusetion);
			pstmt.setString(2, answer);
			pstmt.setInt(3, faqNo);
			result= pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public FAQ selectFAQ(Connection conn, int faqNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		FAQ faq = null;
		String query = "SELECT * FROM FAQ WHERE FAQ_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, faqNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				faq = new FAQ();
				faq.setFaqNo(rset.getInt("FAQ_NO"));
				faq.setFaqQ(rset.getString("FAQ_Q"));
				faq.setFaqA(rset.getString("FAQ_A"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
		}
		return faq;
	}

	public int insertFAQ(Connection conn, String faq_q, String faq_a) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO FAQ VALUES(SEQ_FAQ_NO.NEXTVAL,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, faq_q);
			pstmt.setString(2, faq_a);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
