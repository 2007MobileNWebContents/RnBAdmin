package community.donBoard.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.memBoard.model.service.MemberBoardService;
import community.donBoard.model.service.DonateBoardService;
import member.model.vo.Member;

@WebServlet("/donboard/insertComment")
public class DonateBoardCommentWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DonateBoardCommentWriteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String commentContent = request.getParameter("boardCommentContent");
		int dbNo =Integer.parseInt(request.getParameter("noticeNo"));
		HttpSession session = request.getSession();

		if(session.getAttribute("member") != null) {
			String userId = ((Member)session.getAttribute("member")).getUserId();
			int result = new DonateBoardService().insertDonBoardComment(dbNo,commentContent, userId);
			if (result > 0) {
				response.sendRedirect("/donboard/select?dbNo="+dbNo);
			}else {
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/donboard/error.jsp");
				view.forward(request, response);
			}
		}
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('댓글쓰기는 회원이용 서비스입니다.');location.href='"+"/donboard/select?dbNo="+dbNo+"';</script>");
			writer.close();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
