package board.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.notice.model.service.NoticeService;


@WebServlet("/notice/modify")
public class NoticeBoardModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NoticeBoardModifyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		int result = new NoticeService().modifyNotice(title, content, noticeNo);
		if (result > 0 ) {
			response.sendRedirect("/notice/select?noticeNo="+noticeNo);
		}else {
			request.getRequestDispatcher("/WEB-INF/views/notice/noticeError.html");
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
