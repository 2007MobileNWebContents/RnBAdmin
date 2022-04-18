package board.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.notice.model.service.NoticeService;


@WebServlet("/notice/commentDelete")
public class NoticeCommentDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NoticeCommentDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		int comNo = Integer.parseInt(request.getParameter("comNo"));
		int result = new NoticeService().deleteNoticeComment(noticeNo,comNo);
		
		if ( result> 0 ) {
			response.sendRedirect("/notice/select?noticeNo="+noticeNo);
		} else {
			request.getRequestDispatcher("/WEB-INF/views/notice/error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
