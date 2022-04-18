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
import reservation.model.vo.GroupReservation;
import reservation.model.vo.PageData;

/**
 * Servlet implementation class GroupReservationServlet
 */
@WebServlet("/group/reservation")
public class GroupReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupReservationServlet() {
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
			ReservationService reservationService = new ReservationService();
			int currentAckPage = 0;
			if(request.getParameter("currentAckPage") == null) {
				currentAckPage = 1;
			}
			else {
				currentAckPage = Integer.parseInt(request.getParameter("currentAckPage"));
			}
			
			int currentConfirmPage = 0;
			if(request.getParameter("currentConfirmPage") == null) {
				currentConfirmPage = 1;
			}
			else {
				currentConfirmPage = Integer.parseInt(request.getParameter("currentConfirmPage"));
			}
			
//			int resultAck = reservationService.groupReservationAckUpdate();
//			int resultWait = reservationService.groupReservationWaitUpdate();
			int resultWait = reservationService.groupReservationWaitUpdate();
			PageData groupConfirmPageData = reservationService.groupConfirmSelectList(currentConfirmPage);
			ArrayList<GroupReservation> gcList = groupConfirmPageData.getGroupConfirmPageList();
			PageData groupAckPageData = reservationService.groupAckSelectList(currentAckPage);
			ArrayList<GroupReservation> gList = groupAckPageData.getGroupAckPageList();
			if(!gcList.isEmpty() && !gList.isEmpty()) {
				request.setAttribute("gcList", gcList);
				request.setAttribute("pageConfirmNavi", groupConfirmPageData.getGroupConfirmPageNavi());
				request.setAttribute("gList", gList);
				request.setAttribute("pageAckNavi", groupAckPageData.getGroupAckPageNavi());
				request.getRequestDispatcher("/WEB-INF/views/reservation/groupReservation.jsp").forward(request, response);
//				request.getRequestDispatcher("/group/reservation/acknowledgement").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/WEB-INF/views/reservation/groupReservation.jsp").forward(request, response);
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
