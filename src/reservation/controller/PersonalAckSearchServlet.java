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
 * Servlet implementation class PersonalAckSearchServlet
 */
@WebServlet("/personal/ack/search")
public class PersonalAckSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalAckSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String searchKey = request.getParameter("searchKey");
		String search = request.getParameter("search");
		
		PageData pageData = new ReservationService().personalReservationList(1);
		ArrayList<PersonalReservation> pList = pageData.getPersonalPageList();
		
		if(searchKey.equals("이름으로 검색")) {
			request.setAttribute("searchKey", searchKey);
			request.setAttribute("search", search);
			request.setAttribute("pList", pList);
			request.setAttribute("personalPageNavi", pageData.getPersonalPageNavi());
			request.getRequestDispatcher("/personal/search/ack/name").forward(request, response);
		}
		else if(searchKey.equals("아이디로 검색")) {
			request.setAttribute("searchKey", searchKey);
			request.setAttribute("search", search);
			request.setAttribute("pList", pList);
			request.setAttribute("personalPageNavi", pageData.getPersonalPageNavi());
			request.getRequestDispatcher("/personal/search/ack/id").forward(request, response);
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
