package community.cheer.controller;

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
import member.model.vo.Member;

/**
 * Servlet implementation class CheerWriteServlet
 */
@WebServlet("/cheer/write")
public class CheerWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheerWriteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String content = request.getParameter("content");

		//session 구현아직x
		HttpSession session = request.getSession();
		if(session != null && (session.getAttribute("member") != null)) {
			String userId = ((Member)session.getAttribute("member")).getUserId();
			//			
			int result = new CheerService().insertCheer(content,userId);
			if(result > 0) {
				response.sendRedirect("/cheer/list");
			}else {
				RequestDispatcher view = request.getRequestDispatcher("/views/fail.jsp");
				view.forward(request, response);
			}
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('로그인이 필요합니다!'); location.href='/login/admin';</script>");
			writer.close();
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
