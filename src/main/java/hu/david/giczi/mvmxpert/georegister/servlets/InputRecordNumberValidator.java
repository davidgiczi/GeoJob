package hu.david.giczi.mvmxpert.georegister.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.david.giczi.mvmxpert.georegister.service.GeoJob;
import hu.david.giczi.mvmxpert.georegister.service.GeoJobPropertyStore;
import hu.david.giczi.mvmxpert.georegister.service.GeoJobServiceImpl;
import hu.david.giczi.mvmxpert.georegister.service.InputDataValidator;
import hu.david.giczi.mvmxpert.georegister.service.MeasuringReport;


@WebServlet("/validator")
public class InputRecordNumberValidator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InputRecordNumberValidator() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String inputData = request.getParameter("recordNumber");
		String operation = request.getParameter("operation");
		int recordNumber;
		GeoJob requestedGeoJob;

		if (InputDataValidator.isNumber(inputData)) {

			recordNumber = Integer.parseInt(inputData);
		} else {

			request.getSession().setAttribute("size", null);
			request.getSession().setAttribute("notNumber", true);
			request.getRequestDispatcher("postRouter").forward(request, response);
			return;
		}

		try {

			String requestInstruction = (String) request.getSession().getAttribute("requestInstruction");
			String searchedData = (String) request.getSession().getAttribute("searchedData");
			
			if( requestInstruction == null && searchedData == null ) {
				
				request.getRequestDispatcher("/WEB-INF/geostart.jsp").forward(request, response);
				return;
			}

			List<GeoJob> geoJobStore = new GeoJobServiceImpl().getGeoJobList(requestInstruction, searchedData);

			if (InputDataValidator.containRegs(geoJobStore.size(), recordNumber)) {

				requestedGeoJob = geoJobStore.get(recordNumber - 1);
				request.setAttribute("geojob", requestedGeoJob);
				createCoordReport(operation, requestedGeoJob, request);
				forwardForOperation(operation, request, response);
			} else {
				request.getSession().setAttribute("notNumber", null);
				request.getSession().setAttribute("size", geoJobStore.size());
				request.getRequestDispatcher("postRouter").forward(request, response);

			}

		} catch (IllegalStateException e) {

			request.getRequestDispatcher("/WEB-INF/geostart.jsp").forward(request, response);

		}

	}

	private void forwardForOperation(String operation, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		switch (operation) {
		
		case "modify":
			request.getRequestDispatcher("/WEB-INF/geojobmodify.jsp").forward(request, response);
			break;
		case "del":
			request.getRequestDispatcher("/WEB-INF/geojobdel.jsp").forward(request, response);
			break;
		case "createcoord":
			request.getRequestDispatcher("/WEB-INF/postRouter").forward(request, response);
			break;
		default:
			break;
		}

	}
	
	
	private void createCoordReport(String operation, GeoJob geoJob, HttpServletRequest request)  {
		
		request.getSession().setAttribute("notNumber", null);
		request.getSession().setAttribute("size", null);
		
		if("createcoord".equals(operation)) {
			
			String jobNumber = geoJob.getInvestorCompany().replace(" ", "_");
			String place = geoJob.getPlaceOfWork().replace(" ", "_");
			String method_date = geoJob.getMethod().replace(" ", "_") + "_"+geoJob.getDate();
			
			if(MeasuringReport.createCoordReport(GeoJobPropertyStore.URL1, jobNumber, place, method_date)) {
				
				try {
					Runtime.getRuntime().exec("notepad.exe " 
				+ GeoJobPropertyStore.URL1 + jobNumber + "\\" + place + "\\" + method_date + "\\projekt\\coords_kit.txt");
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
				request.getSession().setAttribute("createcoord", true);
				
			}
			else {
				
				request.getSession().setAttribute("createcoord", false);
				
			}
			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
