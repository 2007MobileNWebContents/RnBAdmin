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
 * Servlet implementation class GroupSearchConfirmServlet
 */
@WebServlet("/group/search/confirm")
public class GroupSearchConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupSearchConfirmServlet() {
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
		PageData groupAckPageData = new ReservationService().groupAckSelectList(1);
		ArrayList<GroupReservation> gList = groupAckPageData.getGroupAckPageList();
		
		if(searchKey.equals("이름으로 검색")) {
			request.setAttribute("searchKey", searchKey);
			request.setAttribute("search", search);
			request.setAttribute("pageAckNavi", groupAckPageData.getGroupAckPageNavi());
			request.setAttribute("gList", gList);
			request.getRequestDispatcher("/group/search/confirm/name").forward(request, response);
		}
		else if(searchKey.equals("아이디로 검색")) {
			request.setAttribute("searchKey", searchKey);
			request.setAttribute("search", search);
			request.setAttribute("pageAckNavi", groupAckPageData.getGroupAckPageNavi());
			request.setAttribute("gList", gList);
			request.getRequestDispatcher("/group/search/confirm/id").forward(request, response);
		}
		else {
			
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
