package board.memBoard.controller;

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
import member.model.vo.Member;

@WebServlet("/memboard/insertComment")
public class MemBoardCommentWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemBoardCommentWrite() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String commentContent = request.getParameter("boardCommentContent");
		int mbNo =Integer.parseInt(request.getParameter("noticeNo"));
		HttpSession session = request.getSession();
		if(session.getAttribute("member") != null) {
			String userId = ((Member)session.getAttribute("member")).getUserId();
			int result = new MemberBoardService().insertMemBoardComment(mbNo,commentContent, userId);
			if (result > 0) {
				response.sendRedirect("/memboard/select?mbNo="+mbNo);
			}else {
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/memboard/error.jsp");
				view.forward(request, response);
			}
		}
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('댓글쓰기는 회원이용 서비스입니다.');location.href='"+"/memboard/select?mbNo="+mbNo+"';</script>");
			writer.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
