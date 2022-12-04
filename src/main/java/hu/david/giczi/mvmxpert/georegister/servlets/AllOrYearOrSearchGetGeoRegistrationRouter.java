package hu.david.giczi.mvmxpert.georegister.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/postRouter")
public class AllOrYearOrSearchGetGeoRegistrationRouter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AllOrYearOrSearchGetGeoRegistrationRouter() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		try {

			String from = (String) request.getSession().getAttribute("requestInstruction");
			String searching = (String) request.getSession().getAttribute("searchedData");
			String setup = (String) request.getSession().getAttribute("setup");
			
			
			if (from != null && searching == null && setup == null) {

				if ("all".equals(from)) {

					request.getRequestDispatcher("getAllRegs").forward(request, response);

				} else {

					request.getRequestDispatcher("getYearRegs").forward(request, response);
				}

			} else if (searching != null && from == null && setup == null) {

				request.getRequestDispatcher("search").forward(request, response);
			}
			else if(setup != null && from == null && searching == null) {
				
				request.getRequestDispatcher("geosetup.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("geostart.jsp").forward(request, response);
			}
				
				
		} catch (IllegalStateException e) {

			request.getRequestDispatcher("geostart.jsp").forward(request, response);

		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
