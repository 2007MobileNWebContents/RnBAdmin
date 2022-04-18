package faq.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import faq.model.dao.FAQDAO;
import faq.model.vo.FAQ;
import faq.model.vo.PageData;
import common.JDBCTemplate;

public class FAQService {
	private JDBCTemplate factory;
	
	public FAQService() {
		factory = JDBCTemplate.getConnection();
	}

	public PageData faqList(int currentPage) {
		Connection conn = null;
		int recordCountPerPage = 13;
		int naviCountPerPage = 5;
		PageData pd = new PageData();

		try {
			conn = factory.createConnection();
			pd.setPageList(new FAQDAO().faqList(conn, currentPage, recordCountPerPage));
			pd.setPageNavi(new FAQDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}

	public int deleteFAQ(int faqNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new FAQDAO().deleteFAQ(conn,faqNo);
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

	public int modifyFAQ(String qusetion, String answer, int faqNo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new FAQDAO().modifyFAQ(conn, qusetion, answer, faqNo);
			if (result > 0 ) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}

		} catch(SQLException e ) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

	public FAQ selectFAQ(int faqNo) {
		FAQ faq = null;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			faq = new FAQDAO().selectFAQ(conn, faqNo);

		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return faq;
	}

	public int insertFAQ(String faq_q, String faq_a) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new FAQDAO().insertFAQ(conn,faq_q,faq_a);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

}
