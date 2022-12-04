package hu.david.giczi.mvmxpert.georegister.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/clearSession")
public class ClearSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ClearSession() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		request.getSession().setAttribute("size", null);
		request.getSession().setAttribute("notNumber", null);
		request.getSession().setAttribute("createcoord", null);
		request.getSession().setAttribute("fileURLStore", null);
		request.getSession().setAttribute("setup", null);
		request.getSession().setAttribute("initprocess", null);
		request.getRequestDispatcher("postRouter").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
