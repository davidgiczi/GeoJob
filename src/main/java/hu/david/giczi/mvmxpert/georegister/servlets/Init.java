package hu.david.giczi.mvmxpert.georegister.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hu.david.giczi.mvmxpert.georegister.service.TimeStamp;


@WebServlet("/init")
public class Init extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Init() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=request.getParameter("user");
		String password=request.getParameter("pass");
		
	
		if("david.giczi".equals(username) && "localhero".equals(password)) {
			
			request.setAttribute("years", TimeStamp.getYears(5));
			request.setAttribute("msg", -1);
			request.getRequestDispatcher("InitGeoJobProject").forward(request, response);
			
			
		}
		else if("".equals(username) && "".equals(password)) {
			request.setAttribute("invalid", false);
			RequestDispatcher req=request.getRequestDispatcher("/index.jsp");
    		req.include(request, response);
		}
		else {
			request.setAttribute("invalid", true);
			RequestDispatcher req=request.getRequestDispatcher("/index.jsp");
    		req.include(request, response);
			
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
