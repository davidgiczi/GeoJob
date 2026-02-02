package hu.david.giczi.mvmxpert.georegister.servlets;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.david.giczi.mvmxpert.georegister.service.GeoJobPropertyStore;


@WebServlet("/openMonth")
public class OpenMonth extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public OpenMonth() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Runtime run = Runtime.getRuntime();
		String path = GeoJobPropertyStore.URL4;
		String fileName = "";
			
		switch (Calendar.getInstance().get(Calendar.MONTH)) {
		
		case 0:
			fileName = "jan.pdf";
			break;
		case 1:
			fileName = "feb.pdf";
			break;
		case 2:
			fileName = "mar.pdf";
			break;	
		case 3:
			fileName = "apr.pdf";
			break;	
		case 4:
			fileName = "maj.pdf";
			break;
		case 5:
			fileName = "jun.pdf";
			break;
		case 6:
			fileName = "jul.pdf";
			break;
		case 7:
			fileName = "aug.pdf";
			break;
		case 8:
			fileName = "sep.pdf";
			break;
		case 9:
			fileName = "oct.pdf";
			break;
		case 10:
			fileName = "nov.pdf";
			break;
		case 11:
			fileName = "dec.pdf";
			break;

		}
		
		path += fileName;
		
	
		if(new File(path).exists()) {
		
			run.exec(GeoJobPropertyStore.URL2 + path);
			
		}
		else {
			
			request.setAttribute("missed", fileName);
			
		}
			
		
		request.getRequestDispatcher("/WEB-INF/geostart.jsp").forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
