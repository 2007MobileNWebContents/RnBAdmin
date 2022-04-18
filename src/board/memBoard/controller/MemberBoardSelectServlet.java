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
import board.memBoard.model.vo.MemberBoard;
import board.notice.model.vo.PageData;

@WebServlet("/memboard/select")
public class MemberBoardSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberBoardSelectServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int mbNo = Integer.parseInt(request.getParameter("mbNo"));
		
		MemberBoard memboard = new MemberBoardService().selectMemBoard(mbNo);
		PageData pageData = new MemberBoardService().commentList(mbNo);
		ArrayList<MemBoardComment> mcList = pageData.getMcPageList();
		
		//String userId = request.getAttribute("userId");
		//String commentContent
//		if (userId != null && commentContent != null) {
//			int result = new MemberBoardService().
//		}
		if(memboard != null) {
			request.setAttribute("content",  memboard);
			request.setAttribute("mcList", mcList);
			request.getRequestDispatcher("/WEB-INF/views/memboard/memContent.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/views/memboard/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
