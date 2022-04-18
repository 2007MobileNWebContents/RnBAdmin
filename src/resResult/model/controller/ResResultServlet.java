package resResult.model.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import resResult.model.service.ResResultService;
import resResult.model.vo.ResResult;

/**
 * Servlet implementation class ResResultServlet
 */
@WebServlet("/ResResult")
public class ResResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		
		ResResult rResult = new ResResultService().ResResult(num); 
		
		System.out.println(rResult);
		
		JSONObject userObj = null;
		if(rResult != null) {
			userObj = new JSONObject();
			userObj.put("ResB", rResult.getResB());
			userObj.put("ResC", rResult.getResC());
			userObj.put("ResCol", rResult.getResCol());
			userObj.put("ResAst", rResult.getResAst());
			userObj.put("ResAlt", rResult.getResAlt());
			userObj.put("ResPro", rResult.getResPro());
		}
		
		response.setContentType("application/json; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(userObj);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
