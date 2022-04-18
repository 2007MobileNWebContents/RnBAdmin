package board.memBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.memBoard.model.service.MemberBoardService;


@WebServlet("/memboard/delete")
public class MemberBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberBoardDeleteServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int memboardNo = Integer.parseInt(request.getParameter("mbNo"));
		int result = new MemberBoardService().deleteMemBoard(memboardNo);
		
		if (result > 0 ) {
			response.sendRedirect("/memboard/list");
		}else {
			request.getRequestDispatcher("/WEB-INF/views/memboard/error.jsp");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
