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


@WebServlet("/getAllRegs")
public class GetAllGeoRegistrations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GetAllGeoRegistrations() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		List<GeoJob> geoJobStore = new GeoJobServiceImpl().findAll();
		
	try	{
			
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
			request.getRequestDispatcher("georegs.jsp").forward(request, response);
		}
		else {
			
			request.getSession().setAttribute("allEmpty", true);
			request.getRequestDispatcher("InitGeoList").forward(request, response);
		}
		
		
	}
	catch (IllegalStateException e) {
		
		request.getRequestDispatcher("geostart.jsp").forward(request, response);
		
	}
			
	}
	
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
