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
import community.donBoard.model.vo.DonateBoard;

@WebServlet("/donboard/list")
public class DonateBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DonateBoardListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 0;
		if (request.getParameter("currentPage")== null) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		PageData pageData = new DonateBoardService().selectDonList(currentPage);
		ArrayList<DonateBoard> dList = pageData.getdPageList();
		
		if ( !dList.isEmpty()) {
			request.setAttribute("dList", dList);
			request.setAttribute("pageNavi", pageData.getPageNavi());
			request.getRequestDispatcher("/WEB-INF/views/donboard/donList.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/views/donboard/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
