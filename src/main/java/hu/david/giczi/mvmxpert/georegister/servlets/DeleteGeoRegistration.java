package hu.david.giczi.mvmxpert.georegister.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.david.giczi.mvmxpert.georegister.service.GeoJobServiceImpl;




@WebServlet("/deleteGeoJob")
public class DeleteGeoRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DeleteGeoRegistration() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String delRecordId = request.getParameter("geojobid");
		
		new GeoJobServiceImpl().remove(Long.valueOf(delRecordId));
		
		request.getRequestDispatcher("clearSession").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
