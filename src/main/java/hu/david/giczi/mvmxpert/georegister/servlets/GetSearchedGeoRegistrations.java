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
			
			String[] inputComponents = inputData.split("-");
			if( inputComponents.length == 1 ) {
				inputComponents = inputData.split("\\s+");
			}
			List<GeoJob> geoJobStore = new ArrayList<>();
					
			for (String inputTxt : inputComponents) {
				geoJobStore.addAll(new GeoJobServiceImpl().search(inputTxt));
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
					
					for (String searchedText : inputComponents) {
						new HighlightedGeoJob(geoJob, searchedText).createHighlightedGeoJob();
					}
				}
				
				
				request.setAttribute("geoJobs", HighlightedGeoJob.highlightedGeoJobStore);
				request.getRequestDispatcher("georegs.jsp").forward(request, response);
			} 
			else {
				
				request.getSession().setAttribute("searchedEmpty", true);
				request.getRequestDispatcher("InitGeoList").forward(request, response);

			}

		} catch (IllegalStateException e) {

			request.getRequestDispatcher("geostart.jsp").forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
