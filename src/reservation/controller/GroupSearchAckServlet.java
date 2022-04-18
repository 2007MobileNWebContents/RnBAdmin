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
 * Servlet implementation class GroupSearchAckServlet
 */
@WebServlet("/group/search/ack")
public class GroupSearchAckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupSearchAckServlet() {
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
		PageData groupConfirmPageData = new ReservationService().groupConfirmSelectList(1);
		ArrayList<GroupReservation> gcList = groupConfirmPageData.getGroupConfirmPageList();
		
		if(searchKey.equals("이름으로 검색")) {
			request.setAttribute("searchKey", searchKey);
			request.setAttribute("search", search);
			request.setAttribute("pageConfirmNavi", groupConfirmPageData.getGroupConfirmPageList());
			request.setAttribute("gcList", gcList);
			request.getRequestDispatcher("/group/search/ack/name").forward(request, response);
		}
		else if(searchKey.equals("아이디로 검색")) {
			request.setAttribute("searchKey", searchKey);
			request.setAttribute("search", search);
			request.setAttribute("pageConfirmNavi", groupConfirmPageData.getGroupConfirmPageList());
			request.setAttribute("gcList", gcList);
			request.getRequestDispatcher("/group/search/ack/id").forward(request, response);
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
