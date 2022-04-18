package board.memBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.memBoard.model.service.MemberBoardService;
import board.memBoard.model.vo.MemBoardComment;
import board.notice.model.vo.PageData;

@WebServlet("/memboard/commentList")
public class MemBoardCommentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemBoardCommentListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 0;
		if(request.getParameter("currentPage")==null) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		PageData pageData = new MemberBoardService().commentList(currentPage);
		ArrayList<MemBoardComment> mcPageList = pageData.getMcPageList();   
		
		if (!mcPageList.isEmpty()) {
			request.setAttribute("mcPageList", mcPageList);
			request.setAttribute("pageNavi", pageData.getPageNavi());
			request.getRequestDispatcher("/WEB-INF/views/memboard/memContent.jsp").forward(request, response );
		} else {
			request.getRequestDispatcher("/WEB-INF/views/memboard/error.html").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
