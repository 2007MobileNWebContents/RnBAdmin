package board.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.notice.model.service.NoticeService;
import board.notice.model.vo.Notice;
import board.notice.model.vo.NoticeComment;
import board.notice.model.vo.PageData;


@WebServlet("/notice/select")
public class NoticeBoardSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NoticeBoardSelectServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int noticeNo= Integer.parseInt(request.getParameter("noticeNo"));
		
		Notice notice = new NoticeService().selectNotice(noticeNo);
		PageData pageData = new NoticeService().commentList(noticeNo);
		ArrayList<NoticeComment> cList = pageData.getcPageList();
		
		
		
		if (notice != null ) {
			request.setAttribute("content", notice);
			request.setAttribute("cList", cList);
			request.getRequestDispatcher("/WEB-INF/views/notice/noticeContent.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/WEB-INF/views/notice/noticeError.html").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
