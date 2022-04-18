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

@WebServlet("/checkResult/detail")
public class ChkResultDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChkResultDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int crCode = Integer.parseInt(request.getParameter("crCode"));
		ArrayList<ChkResult> dlist = new ChkResultService().selectDetail(crCode);
		if(!dlist.isEmpty()) {
			request.setAttribute("dlist", dlist);
			request.getRequestDispatcher("/WEB-INF/views/result/chkResultDetail.jsp").forward(request, response);
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter(); 
            writer.println("<script>alert('정보가 없습니다.'); location.href='/main';</script>");
            writer.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
