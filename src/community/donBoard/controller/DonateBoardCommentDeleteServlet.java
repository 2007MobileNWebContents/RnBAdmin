package community.donBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.donBoard.model.service.DonateBoardService;

@WebServlet("/donboard/commentDelete")
public class DonateBoardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DonateBoardCommentDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int dbNo = Integer.parseInt(request.getParameter("dbNo"));
		int donNo = Integer.parseInt(request.getParameter("comNo"));
		System.out.println(dbNo);
		System.out.println( donNo);
		int result = new DonateBoardService().deleteDonBoardComment(dbNo,donNo);
		
		if ( result> 0 ) {
			response.sendRedirect("/donboard/select?dbNo="+dbNo);
		} else {
			request.getRequestDispatcher("/WEB-INF/views/memboard/error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
