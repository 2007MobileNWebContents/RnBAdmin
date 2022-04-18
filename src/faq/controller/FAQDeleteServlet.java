package faq.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import faq.model.service.FAQService;
import community.cheer.service.CheerService;
import member.model.vo.Member;

/**
 * Servlet implementation class FaqDeleteServlet
 */
@WebServlet("/faq/del")
public class FAQDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FAQDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html; charset=UTF-8");
		if(session != null) {
			
			int faqNo = Integer.parseInt(request.getParameter("faqNo"));
			int result= new FAQService().deleteFAQ(faqNo);
			if(result > 0) {
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('삭제되었습니다!'); location.href='/faq/list';</script>");
				writer.close();
			}else {		
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('��������!'); location.href='/faq/list';</script>");
			writer.close();
			}
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
