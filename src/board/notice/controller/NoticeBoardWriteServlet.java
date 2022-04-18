package board.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.notice.model.service.NoticeService;


@WebServlet("/notice/write")
public class NoticeBoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NoticeBoardWriteServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String subject = request.getParameter("subject");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//HttpSession session = request.getSession();
		// 통합시 활성화
//		if(session != null && (session).getAttribute("member")!= null) {
//		String userId = ((Member)session.getAttribute("member")).getUserId();
		
			String userId = "admin";
			int result = new NoticeService().insertNotice( title, content, userId);
			
			if(result>0) {
				response.sendRedirect("/notice/list");
				
			}else {
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/notice/noticeError.html");
				view.include(request, response);
			}
//		}else 
			{
//				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/notice/serviceFailed.html");
//				view.forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
