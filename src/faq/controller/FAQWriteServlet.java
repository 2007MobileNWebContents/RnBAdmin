package faq.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import community.cheer.service.CheerService;
import faq.model.service.FAQService;
import member.model.vo.Member;

/**
 * Servlet implementation class FaqWriteServlet
 */
@WebServlet("/faq/write")
public class FAQWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FAQWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String faq_q = request.getParameter("faq_q");
		String faq_a = request.getParameter("faq_a");

			int result = new FAQService().insertFAQ(faq_q,faq_a);
			if(result > 0) {
				response.sendRedirect("/faq/list");
			}else {
				RequestDispatcher view = request.getRequestDispatcher("/views/fail.jsp");
				view.forward(request, response);
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
