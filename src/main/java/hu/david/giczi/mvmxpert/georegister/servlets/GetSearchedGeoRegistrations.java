package hu.david.giczi.mvmxpert.georegister.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.david.giczi.mvmxpert.georegister.service.GeoJob;
import hu.david.giczi.mvmxpert.georegister.service.GeoJobServiceImpl;
import hu.david.giczi.mvmxpert.georegister.service.HighlightedGeoJob;


@WebServlet("/search")
public class GetSearchedGeoRegistrations extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetSearchedGeoRegistrations() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String inputData = request.getParameter("search");
		
		try {
		
			 if (inputData != null) {
				 
				 if(inputData.isEmpty()) {
					 
					 request.getSession().setAttribute("invalid", true);
					 request.getRequestDispatcher("InitGeoList").forward(request, response);
					 return;
				 }
				
				request.getSession().setAttribute("searchedData", inputData);
			}
			else {
				
				inputData = (String) request.getSession().getAttribute("searchedData");	
			}
			
			Boolean notNumber = (Boolean) request.getSession().getAttribute("notNumber");
			Integer sizeOfList = (Integer) request.getSession().getAttribute("size");
			Boolean createCoord = (Boolean) request.getSession().getAttribute("createcoord");
			
			String[] inputComponents = inputData.split("#");
			
			List<GeoJob> geoJobStore = new ArrayList<>();
			
			for (String inputTxt : inputComponents) {
				List<GeoJob> geoJobList = new GeoJobServiceImpl().search(inputTxt);
				for (GeoJob geoJob : geoJobList) {
					geoJob.setSearchedText(inputTxt);
				}
				geoJobStore.addAll(geoJobList);
			}		
					
			if (!geoJobStore.isEmpty()) {

				if (notNumber != null) {

					request.setAttribute("msg", "notnumber");
					

				} else if (sizeOfList != null) {

					request.setAttribute("msg", sizeOfList);
				}
				else if(createCoord != null) {
					
					request.setAttribute("msg", createCoord);
					
				}
				
				if( !HighlightedGeoJob.highlightedGeoJobStore.isEmpty() ) {
					HighlightedGeoJob.clearHighlightedGeoJobStore();
				}
				
				for (GeoJob geoJob : geoJobStore) {
				new HighlightedGeoJob(geoJob, geoJob.getSearchedText()).createHighlightedGeoJob();
					
				}
				
				
				request.setAttribute("geoJobs", HighlightedGeoJob.highlightedGeoJobStore);
				request.getRequestDispatcher("/WEB-INF/georegs.jsp").forward(request, response);
			} 
			else {
				
				request.getSession().setAttribute("searchedEmpty", true);
				request.getRequestDispatcher("InitGeoList").forward(request, response);

			}

		} catch (IllegalStateException e) {

			request.getRequestDispatcher("/WEB-INF/geostart.jsp").forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
