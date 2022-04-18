package memInfo.model.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import memInfo.model.service.MemberInfoService;
import memInfo.model.vo.MemberInfo;
import memInfo.model.vo.PageData;

/**
 * Servlet implementation class MemberInfoSearchServlet
 */
@WebServlet("/member/search")
public class MemberInfoSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInfoSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		int currentPage = 0;
		if(request.getParameter("currentPage") == null) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		String idName = request.getParameter("idName");
		PageData pageData = new MemberInfoService().searchList(idName, currentPage);
		ArrayList<MemberInfo> memList = pageData.getPageList();
		if(!memList.isEmpty()) {
			request.setAttribute("memList", memList);
			request.setAttribute("pageNavi", pageData.getPageNavi());
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/memberSearch/MemberSearchList.jsp");
			view.forward(request, response);
		}else {
			PrintWriter writer = response.getWriter(); 
			writer.println("<script>alert('일치하는 아이디나 이름이 없습니다.'); "
			+"location.href='/member/list';</script>"); writer.close();
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
