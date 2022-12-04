package hu.david.giczi.mvmxpert.georegister.servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
		
		PrintWriter pw= response.getWriter();
	
		String username=request.getParameter("user");
		String password=request.getParameter("pass");
		
	
		if("david.giczi".equals(username) && "localhero".equals(password)) {
			
			request.setAttribute("years", TimeStamp.getYears(5));
			request.setAttribute("msg", -1);
			request.getRequestDispatcher("InitGeoJobProject").forward(request, response);
			
			
		}
		else if("".equals(username) && "".equals(password)) {
			RequestDispatcher req=request.getRequestDispatcher("index.jsp");
    		pw.println("<h4 style=color:green>You have signed out successfully.</h4>");
    		req.include(request, response);
		}
		else {
			
			RequestDispatcher req=request.getRequestDispatcher("index.jsp");
    		pw.println("<h4 style=color:red>Invalid username and/or password, please try again!</h4>");
    		req.include(request, response);
			
			
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
