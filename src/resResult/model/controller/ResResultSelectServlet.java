package resResult.model.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resResult.model.service.ResResultService;
import resResult.model.vo.ResPriv;

/**
 * Servlet implementation class ResResultSelectServlet
 */
@WebServlet("/ResResultSelect")
public class ResResultSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResResultSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String resId = request.getParameter("resId");
		ArrayList<ResPriv> resList = new ResResultService().selectResResult(resId);
		if(resList != null) {
			request.setAttribute("resList", resList);
			request.setAttribute("resId", resId);
			request.getRequestDispatcher("/WEB-INF/views/login/bAdmin.jsp").forward(request, response);
		}else {
			response.sendRedirect("/error");
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
