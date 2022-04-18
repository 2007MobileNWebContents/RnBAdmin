package board.memBoard.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/memboard/writeForm")
public class MemberBoardWriteFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberBoardWriteFormServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session != null && (session).getAttribute("member")!= null) {

			request.getRequestDispatcher("/WEB-INF/views/memboard/memWrite.jsp").forward(request, response);
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('글쓰기는 회원 서비스입니다.');location.href='"+"/memboard/list"+"';</script>");
			writer.close();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
