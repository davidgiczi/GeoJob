package hu.david.giczi.mvmxpert.georegister.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.david.giczi.mvmxpert.georegister.service.GeoJobPropertyStore;


@WebServlet("/addProperties")
public class AddProperties extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddProperties() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		@SuppressWarnings (value="unchecked")
		List<String> urlStore = (List<String>) request.getSession().getAttribute("fileURLStore");
		Boolean init = (Boolean) request.getSession().getAttribute("initprocess");
		
		if(!isValidInputURLStore(urlStore)) {
						
			request.setAttribute("msg", "invalid");
			
			if(init != null) {
				request.setAttribute("init", true);
			}
			
			request.getRequestDispatcher("geosetup.jsp").forward(request, response);
			return;
		}
		
		 GeoJobPropertyStore.createPropertyFile(urlStore.get(0), urlStore.get(1), urlStore.get(2), urlStore.get(3));	
		

		request.getRequestDispatcher("InitGeoJobProject").forward(request, response);
		
	}

	
	private boolean isValidInputURLStore(List<String> urlStore) {
		
		if(urlStore == null) {
			return false;
		}
		
		for (String url : urlStore) {
			if(url.isEmpty()) {
				return false;
			}
		}
		
		
		return true;
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
