package member.model.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.UserService;
import member.model.vo.Member;

/**
 * Servlet implementation class EnrollServlet
 */
@WebServlet("/member/adminenroll")
public class AdminEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEnrollServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Member admin = new Member();
		admin.setUserId(request.getParameter("userId"));
		 admin.setUserPwd(request.getParameter("userPwd"));
	      admin.setUserName(request.getParameter("userName"));
	      admin.setUserBD(request.getParameter("BD"));
	      admin.setUserPhone(request.getParameter("phone"));
	      admin.setUserAddrPost(request.getParameter("postAddr"));
	      admin.setUserAddrRoad(request.getParameter("roadAddr"));
	      admin.setUserAddrDetail(request.getParameter("detailAddr"));
	      admin.setUserEmail(request.getParameter("email"));
	      admin.setUserGender(request.getParameter("gender"));
	      admin.setUserABO(request.getParameter("abo"));

		
		int result =new UserService().insertAdmin(admin);
		
		if(result >0 ) { 
			response.setContentType("text/html; charset=UTF-8");
			 PrintWriter writer = response.getWriter();
			 writer.println("<script>alert('관리자가 성공적으로 추가되었습니다.'); location.href='/admin/enroll';</script>");
			 writer.close();
			
		}else {  
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
