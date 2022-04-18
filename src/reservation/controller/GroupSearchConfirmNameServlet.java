package reservation.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import reservation.model.service.ReservationService;
import reservation.model.vo.GroupReservation;
import reservation.model.vo.PageData;

/**
 * Servlet implementation class GroupSearchConfirmNameServlet
 */
@WebServlet("/group/search/confirm/name")
public class GroupSearchConfirmNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupSearchConfirmNameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String search = request.getParameter("search");
		//search/confirm/name?currentPage=4&search=신짱구
		
		int currentPage = 0;
		if(request.getParameter("currentPage") == null) {
			currentPage = 1;
		}
		else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		PageData groupPageData = new ReservationService().groupConfirmNameSearchList(currentPage, search);
		ArrayList<GroupReservation> gcList = groupPageData.getGroupConfirmPageList();
		PageData groupAckPageData = new ReservationService().groupAckSelectList(1);
		ArrayList<GroupReservation> gList = groupAckPageData.getGroupAckPageList();
		
		if(!gcList.isEmpty()) {
			request.setAttribute("gcList", gcList);
			request.setAttribute("gList", gList);
			request.setAttribute("pageConfirmNavi", groupPageData.getGroupConfirmPageNavi());
			request.setAttribute("pageAckNavi", groupAckPageData.getGroupAckPageNavi());
			request.getRequestDispatcher("/WEB-INF/views/reservation/groupReservation.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/").forward(request, response);
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
