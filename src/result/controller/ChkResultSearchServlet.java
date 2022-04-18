package result.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import result.model.service.ChkResultService;
import result.model.vo.ChkResult;
import result.model.vo.Hospital;

@WebServlet("/checkResult/search")
public class ChkResultSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChkResultSearchServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩
		request.setCharacterEncoding("UTF-8");
		//변수저장
		String userId = request.getParameter("userId");
		ArrayList<ChkResult> rlist = new ChkResultService().searchUser(userId);
		ArrayList<Hospital> hlist = new ChkResultService().selectHos(userId);
		//ArrayList<Hospital> clist = new ChkResultService().selectHosChk(userId);
		if(!rlist.isEmpty()) {
			request.setAttribute("rlist", rlist);
			request.setAttribute("hlist", hlist);
			//request.setAttribute("clist", clist);
			request.getRequestDispatcher("/WEB-INF/views/result/chkResultMain.jsp").forward(request, response);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter(); 
            writer.println("<script>alert('일치하는 회원이 없습니다.'); location.href='/result/main';</script>");
            writer.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
