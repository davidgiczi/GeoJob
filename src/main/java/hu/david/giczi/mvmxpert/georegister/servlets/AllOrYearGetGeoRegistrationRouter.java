package hu.david.giczi.mvmxpert.georegister.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/preRouter")
public class AllOrYearGetGeoRegistrationRouter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AllOrYearGetGeoRegistrationRouter() {
        
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String instruction = request.getParameter("year");
		
		
		if("all".equals(instruction)) {
			
			request.getSession().setAttribute("requestInstruction", instruction);
			request.getRequestDispatcher("getAllRegs").forward(request, response);
			
		}
		else {
			
			request.getSession().setAttribute("requestInstruction", instruction);
			request.getRequestDispatcher("getYearRegs").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
