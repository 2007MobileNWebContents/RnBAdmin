package community.donBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.notice.model.vo.PageData;
import community.donBoard.model.service.DonateBoardService;
import community.donBoard.model.vo.DonBoardComment;
import community.donBoard.model.vo.DonateBoard;

@WebServlet("/donboard/select")
public class DonateBoardSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DonateBoardSelectServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

request.setCharacterEncoding("utf-8");
		
		int dbNo= Integer.parseInt(request.getParameter("dbNo"));
		
		DonateBoard donboard = new DonateBoardService().selectDonBoard(dbNo);
		PageData pageData = new DonateBoardService().commentList(dbNo);
		ArrayList<DonBoardComment> dcList = pageData.getDcPageList();
		
		
		
		if (donboard != null ) {
			request.setAttribute("content", donboard);
			request.setAttribute("dcList", dcList);
			request.getRequestDispatcher("/WEB-INF/views/donboard/donContent.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/WEB-INF/views/donboard/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
