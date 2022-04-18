package community.donBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.donBoard.model.service.DonateBoardService;

@WebServlet("/donboard/delete")
public class DonateBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DonateBoardDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int donboardNo = Integer.parseInt(request.getParameter("dbNo"));
		int result = new DonateBoardService().deleteDonBoard(donboardNo);
		
		if (result > 0 ) {
			response.sendRedirect("/donboard/list");
		}else {
			request.getRequestDispatcher("/WEB-INF/views/donboard/error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
