package community.donBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.donBoard.model.service.DonateBoardService;
import community.donBoard.model.vo.DonateBoard;


@WebServlet("/donboard/modifyForm")
public class DonateBoardModifyFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DonateBoardModifyFormServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int donboardNo = Integer.parseInt(request.getParameter("dbNo"));
		DonateBoard donboard = new DonateBoardService().selectDonBoard(donboardNo);

		if(donboard != null) {
			request.setAttribute("donboard", donboard);
			request.getRequestDispatcher("/WEB-INF/views/donboard/donModify.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/views/donboard/error.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
