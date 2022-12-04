package hu.david.giczi.mvmxpert.georegister.servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.david.giczi.mvmxpert.georegister.service.GeoJobPropertyStore;


@WebServlet("/InitGeoJobProject")
public class InitGeoJobProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InitGeoJobProject() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		request.getSession().invalidate();
		
		File propFolder = new File("C:\\geoprops\\geoprops.properties");
		
		if(propFolder.exists()) {
			
			GeoJobPropertyStore.loadPropertiesFromFile();
			request.getRequestDispatcher("geostart.jsp").forward(request, response);
		}
		else {
			
			request.setAttribute("msg", "setup");
			request.setAttribute("init", true);
			request.getSession().setAttribute("initprocess", true);
			request.getRequestDispatcher("geosetup.jsp").forward(request, response);
		}
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
