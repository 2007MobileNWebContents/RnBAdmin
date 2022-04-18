package reservation.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import reservation.model.service.ReservationService;
import reservation.model.vo.PageData;
import reservation.model.vo.PersonalReservation;

/**
 * Servlet implementation class PersonalReservationServlet
 */
@WebServlet("/personal/reservation")
public class PersonalReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		
		if(session.getAttribute("member") != null) {
			int currentTotalPage = 0;
			if(request.getParameter("currentTotalPage") == null) {
				currentTotalPage = 1;
			}
			else {
				currentTotalPage = Integer.parseInt(request.getParameter("currentTotalPage"));
			}
			
			int currentAckPage = 0;
			if(request.getParameter("currentAckPage") == null) {
				currentAckPage = 1;
			}
			else {
				currentAckPage = Integer.parseInt(request.getParameter("currentAckPage"));
			}
			
			PageData pageData = new ReservationService().personalReservationList(currentTotalPage);
			ArrayList<PersonalReservation> pList = pageData.getPersonalPageList();
			
			PageData pageAckData = new ReservationService().personalReservationAckList(currentAckPage);
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
		else {
			PrintWriter writer = response.getWriter(); 
			writer.println("<script>alert('로그인 후 이용하십시오.'); "
			+"location.href='/login/admin';</script>"); writer.close();
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
