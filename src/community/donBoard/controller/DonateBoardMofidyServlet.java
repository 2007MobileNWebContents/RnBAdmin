package community.donBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.donBoard.model.service.DonateBoardService;


@WebServlet("/donboard/modify")
public class DonateBoardMofidyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DonateBoardMofidyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int noticeNo = Integer.parseInt(request.getParameter("dbNo"));
		System.out.println(noticeNo);
		int result = new DonateBoardService().modifyDonBoard(title, content, noticeNo);
		if (result > 0 ) {
			response.sendRedirect("/donboard/select?dbNo="+noticeNo);
		}else {
			request.getRequestDispatcher("/WEB-INF/views/donboard/noticeError.html");
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
