package hu.david.giczi.mvmxpert.georegister.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.david.giczi.mvmxpert.georegister.service.GeoJob;
import hu.david.giczi.mvmxpert.georegister.service.GeoJobServiceImpl;


@WebServlet("/getYearRegs")
public class GetYearGeoRegistrations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GetYearGeoRegistrations() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		try {
			
			String year = (String) request.getSession().getAttribute("requestInstruction");
			List<GeoJob> geoJobStore =  new GeoJobServiceImpl().findByYear(year);
			Boolean notNumber = (Boolean) request.getSession().getAttribute("notNumber");
			Integer sizeOfList = (Integer) request.getSession().getAttribute("size");
			Boolean createCoord = (Boolean) request.getSession().getAttribute("createcoord");
			
			if( !geoJobStore.isEmpty() ) {
				
				if(notNumber != null) {
					
					request.setAttribute("msg", "notnumber");
					
				}
				else if(sizeOfList != null) {
					
					request.setAttribute("msg", sizeOfList);
				}
				else if(createCoord != null) {
					
					request.setAttribute("msg", createCoord);
					
				}
				
				request.setAttribute("geoJobs", geoJobStore);
				request.getRequestDispatcher("/WEB-INF/georegs.jsp").forward(request, response);	
			}
			else {
				
				request.getSession().setAttribute("yearEmpty", true);
				request.getRequestDispatcher("InitGeoList").forward(request, response);
			}
			
			
		}
		catch (IllegalStateException e) {
			
			request.getRequestDispatcher("/WEB-INF/geostart.jsp").forward(request, response);
			
		}	
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
