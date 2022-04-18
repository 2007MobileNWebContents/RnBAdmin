package board.memBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.memBoard.model.service.MemberBoardService;

@WebServlet("/memboard/commentDelete")
public class MemBoardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemBoardCommentDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mbNo = Integer.parseInt(request.getParameter("mbNo"));
		int memNo = Integer.parseInt(request.getParameter("comNo"));
		int result = new MemberBoardService().deleteMemBoardComment(mbNo,memNo);
		
		if ( result> 0 ) {
			response.sendRedirect("/memboard/select?mbNo="+mbNo);
		} else {
			request.getRequestDispatcher("/WEB-INF/views/memboard/error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
