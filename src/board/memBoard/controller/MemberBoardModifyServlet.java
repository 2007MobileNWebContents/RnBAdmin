package board.memBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.memBoard.model.service.MemberBoardService;


@WebServlet("/memboard/modify")
public class MemberBoardModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberBoardModifyServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int memboardNo = Integer.parseInt(request.getParameter("memboardNo"));
		int result = new MemberBoardService().modifyMemBoard(title, content, memboardNo);
		if(result > 0 ) {
			response.sendRedirect("/memboard/select?memboardNo="+memboardNo);
		}else {
			request.getRequestDispatcher("/WEB-INF/views/memboard/error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
