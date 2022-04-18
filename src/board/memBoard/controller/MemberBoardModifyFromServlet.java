package board.memBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.memBoard.model.service.MemberBoardService;
import board.memBoard.model.vo.MemberBoard;


@WebServlet("/memboard/modifyForm")
public class MemberBoardModifyFromServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public MemberBoardModifyFromServlet() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memboardNo = Integer.parseInt(request.getParameter("mbNo"));
		MemberBoard memboard = new MemberBoardService().selectMemBoard(memboardNo);

		if(memboard != null) {
			request.setAttribute("memboard", memboard);
			request.getRequestDispatcher("/WEB-INF/views/memboard/memModify.jsp").forward(request, response);

		}else {
			request.getRequestDispatcher("/WEB-INF/views/memboard/error.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
