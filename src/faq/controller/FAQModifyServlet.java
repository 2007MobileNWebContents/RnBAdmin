package faq.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import faq.model.service.FAQService;
import board.notice.model.service.NoticeService;

/**
 * Servlet implementation class FaqModifyServlet
 */
@WebServlet("/faq/modify")
public class FAQModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FAQModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
		
		String question = request.getParameter("faq_q");
		String answer = request.getParameter("faq_a");
		int faqNo = Integer.parseInt(request.getParameter("faqNo"));
		int result = new FAQService().modifyFAQ(question, answer, faqNo);
		if (result > 0 ) {
			response.sendRedirect("/faq/list");
		}else {
			request.getRequestDispatcher("/WEB-INF/views/notice/noticeError.html");
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
