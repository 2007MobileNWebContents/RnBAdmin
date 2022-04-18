package reservation.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import reservation.model.service.ReservationService;
import reservation.model.vo.PageData;
import reservation.model.vo.PersonalReservation;

/**
 * Servlet implementation class PersonalSearchTotalIdServlet
 */
@WebServlet("/personal/search/total/id")
public class PersonalSearchTotalIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalSearchTotalIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String search = request.getParameter("search");
		
		int currentPage = 0;
		if(request.getParameter("currentPage") == null) {
			currentPage = 1;
		}
		else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} 
		
		PageData pageData = new ReservationService().personalSearchTotalIdList(currentPage, search);
		ArrayList<PersonalReservation> pList = pageData.getPersonalPageList();
		PageData pageAckData = new ReservationService().personalReservationAckList(1);
		ArrayList<PersonalReservation> paList = pageAckData.getPersonalAckPageList();
		
		if(!pList.isEmpty()) {
			request.setAttribute("pList", pList);
			request.setAttribute("personalPageNavi", pageData.getPersonalPageNavi());
			request.setAttribute("paList", paList);
			request.setAttribute("personalAckPageNavi", pageAckData.getPersonalAckPageNavi());
			request.getRequestDispatcher("/WEB-INF/views/reservation/personalReservation.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/WEB-INF/views/reservation/personalReservation.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
