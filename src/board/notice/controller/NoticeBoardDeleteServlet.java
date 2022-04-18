package board.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.notice.model.service.NoticeService;

@WebServlet("/notice/delete")
public class NoticeBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NoticeBoardDeleteServlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		int result = new NoticeService().deleteNotice(noticeNo);
		
		if (result> 0) {
			// 공지사항 리스트 페이지 출력
			response.sendRedirect("/notice/list");
		} else {
			// 공지사항 서비스 요청 실패 페이지
			request.getRequestDispatcher("/WEB-INF/views/notice/noticeError.jsp");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
