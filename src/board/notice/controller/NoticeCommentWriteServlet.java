package board.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.notice.model.service.NoticeService;


@WebServlet("/notice/insertComment")
public class NoticeCommentWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public NoticeCommentWriteServlet() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String commentContent = request.getParameter("boardCommentContent");
		int noticeNo =Integer.parseInt(request.getParameter("noticeNo"));
		//String userId = request.getParameter("userId");

		//HttpSession session = request.getSession();
		//LoginVo loginVo = (LoginVO)session.getAttribute("loginVo");
		System.out.println(commentContent);

		String userId = "admin";
		int result = new NoticeService().insertNoticeComment(noticeNo,commentContent, userId);

		if (result > 0) {
			response.sendRedirect("/notice/select?noticeNo="+noticeNo);
		}else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/notice/serviceFailed.html");
			view.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
