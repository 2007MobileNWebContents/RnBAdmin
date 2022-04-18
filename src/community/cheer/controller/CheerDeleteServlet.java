package community.cheer.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import community.cheer.service.CheerService;
import member.model.vo.Member;

/**
 * Servlet implementation class CheerDeleteServlet
 */
@WebServlet("/cheer/del")
public class CheerDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheerDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html; charset=UTF-8");
		if(session != null && (session.getAttribute("member") != null)) {
			
			String userId = ((Member)session.getAttribute("member")).getUserId();
			
			int postNo = Integer.parseInt(request.getParameter("postNo"));
			int result= new CheerService().deleteCheer(postNo,userId);
			if(result > 0) {
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('삭제되었습니다!'); location.href='/cheer/list';</script>");
				writer.close();
			}else {		
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('사용자가 작성한 글이 아닙니다!'); location.href='/cheer/list';</script>");
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
