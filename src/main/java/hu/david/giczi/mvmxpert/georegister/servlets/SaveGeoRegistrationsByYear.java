package hu.david.giczi.mvmxpert.georegister.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.david.giczi.mvmxpert.georegister.service.GeoJobServiceImpl;
import hu.david.giczi.mvmxpert.georegister.service.SaveAndLoadGeoJob;


@WebServlet("/saveGeoRegsByYear")
public class SaveGeoRegistrationsByYear extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	private boolean nonExisted = true;
	
    public SaveGeoRegistrationsByYear() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		if(!nonExisted) {
			response.sendRedirect("initGeoRegSave");
			return;
		}
		
		Map<String, String[]> labels = request.getParameterMap();
		
		SaveAndLoadGeoJob save = new SaveAndLoadGeoJob(
				new GeoJobServiceImpl().findByDate(
						SaveAndLoadGeoJob.formatInputDate(labels.get("startyear")[0], labels.get("startmonth")[0],labels.get("startday")[0]),
						SaveAndLoadGeoJob.formatInputDate(labels.get("stopyear")[0], labels.get("stopmonth")[0],labels.get("stopday")[0])),
						labels);
		
		save.sortOfStringListOfGeoJobByPattern();
		
		int savedRowsNumber = save.getStringListOfGeoJob().size();
		
		if(savedRowsNumber == 0 ) {
			
			request.getSession().setAttribute("savedjobs", -2);
			response.sendRedirect("initGeoRegSave");
			return;
		}
		
		try {

			String path = (String) request.getSession().getAttribute("path");
				
			nonExisted = false;
			
			if (path != null) {
				
					savedRowsNumber = save.createFileChooser(path, true);
					
				 
			} else {
						
					savedRowsNumber = save.createFileChooser("C:\\", true);
					
			}
			
			 if(save.getjFileChooser().getSelectedFile() != null) {
				 request.getSession().setAttribute("path", save.getjFileChooser().getSelectedFile().getParent());	 
			 }
			 
			request.getSession().setAttribute("savedjobs", savedRowsNumber);
			response.sendRedirect("initGeoRegSave");
			nonExisted = true;
			
		} catch (IllegalStateException e) {

			request.getRequestDispatcher("geostart.jsp").forward(request, response);
			
		}	
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
