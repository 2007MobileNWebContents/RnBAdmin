package resResult.model.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resResult.model.service.ResResultService;
import resResult.model.vo.ResResult;

/**
 * Servlet implementation class ResResultInsertServlet
 */
@WebServlet("/ResResultInsert")
public class ResResultInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResResultInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ResResult Rresult = new ResResult();
		Rresult.setRpNo(Integer.parseInt(request.getParameter("rpNo")));
		Rresult.setResId(request.getParameter("resId"));
		Rresult.setResB(request.getParameter("resB"));
		Rresult.setResC(request.getParameter("resC"));
		Rresult.setResAlt(Integer.parseInt(request.getParameter("resAlt")));
		Rresult.setResPro(Integer.parseInt(request.getParameter("resPro")));
		Rresult.setResAst(Integer.parseInt(request.getParameter("resAst")));
		Rresult.setResCol(Integer.parseInt(request.getParameter("resCol")));
		
		int result =new ResResultService().insertResResult(Rresult);
		
		if(result >0 ) { 
			request.getRequestDispatcher("/WEB-INF/views/login/bAdmin.jsp").forward(request, response);
			//response.sendRedirect("/WEB-INF/views/login/bAdmin.jsp");
		}else {  
			response.sendRedirect("/index.jsp");
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
