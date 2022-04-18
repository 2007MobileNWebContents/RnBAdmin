package board.memBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.memBoard.model.service.MemberBoardService;
import board.memBoard.model.vo.MemberBoard;
import board.notice.model.vo.PageData;


@WebServlet("/memboard/list")
public class MemberBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberBoardListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 0;
		if (request.getParameter("currentPage")== null) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		PageData pageData = new MemberBoardService().selectMemList(currentPage);
		ArrayList<MemberBoard> mList = pageData.getmPageList();
		
		if ( !mList.isEmpty()) {
			request.setAttribute("mList", mList);
			request.setAttribute("pageNavi", pageData.getPageNavi());
			request.getRequestDispatcher("/WEB-INF/views/memboard/memList.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/views/memboard/error.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
