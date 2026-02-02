package hu.david.giczi.mvmxpert.georegister.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.david.giczi.mvmxpert.georegister.service.GeoJobPropertyStore;



@WebServlet("/goSetup")
public class GoSetup extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public GoSetup() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		GeoJobPropertyStore.loadPropertiesFromFile();
		
		if(GeoJobPropertyStore.URL1 != null) {
			request.setAttribute("url1", GeoJobPropertyStore.URL1);
		}
		if(GeoJobPropertyStore.URL2 != null) {
			request.setAttribute("url2", GeoJobPropertyStore.URL2);
		}
		if(GeoJobPropertyStore.URL3 != null) {
			request.setAttribute("url3", GeoJobPropertyStore.URL3);
		}
		if(GeoJobPropertyStore.URL4 != null) {
			request.setAttribute("url4", GeoJobPropertyStore.URL4);
		}
		
		request.getRequestDispatcher("/WEB-INF/geosetup.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
