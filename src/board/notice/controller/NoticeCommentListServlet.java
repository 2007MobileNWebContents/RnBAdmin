package board.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.notice.model.service.NoticeService;
import board.notice.model.vo.NoticeComment;
import board.notice.model.vo.PageData;


@WebServlet("/notice/commentList")
public class NoticeCommentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NoticeCommentListServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 0;
		if(request.getParameter("currentPage")==null) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		PageData pageData = new NoticeService().commentList(currentPage);
		ArrayList<NoticeComment> cPageList = pageData.getcPageList();   
		
		if (!cPageList.isEmpty()) {
			request.setAttribute("cPageList", cPageList);
			request.setAttribute("pageNavi", pageData.getPageNavi());
			request.getRequestDispatcher("/WEB-INF/views/notice/noticeContent.jsp").forward(request, response );
		} else {
			request.getRequestDispatcher("/WEB-INF/views/notice/noticeError.html").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
