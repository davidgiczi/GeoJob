package hu.david.giczi.mvmxpert.georegister.servlets;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.david.giczi.mvmxpert.georegister.service.GeoJobService;
import hu.david.giczi.mvmxpert.georegister.service.GeoJobServiceImpl;
import hu.david.giczi.mvmxpert.georegister.service.GeoRegistration;
import hu.david.giczi.mvmxpert.georegister.service.InputDataValidator;


@WebServlet("/addGeoReg")
public class AddGeoRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddGeoRegistration() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		Map<String, String[]> regParams = request.getParameterMap();

		Boolean isReady = regParams.get("ready") != null ? true : false;
		
		GeoJobService geoService = new GeoJobServiceImpl();

		GeoRegistration geo = new GeoRegistration(geoService.cutInputString(regParams.get("settlement")[0]),
				geoService.cutInputString(regParams.get("place")[0]), geoService.cutInputString(regParams.get("method")[0]),
				geoService.cutInputString(regParams.get("date")[0]), geoService.cutInputString(regParams.get("manager")[0]),
				geoService.cutInputString(regParams.get("investor")[0]), geoService.cutInputString(regParams.get("comment")[0]),
				isReady);

		if (InputDataValidator.isValidInputGeoRegistration(geo)) { 
			
			geoService.addGeoReg(geo);
			
			geoService.createWorkFolders(geo.getInvestorCompany(),
					 geo.getPlaceOfWork(), geo.getMethod() + "_" + geo.getDate());

			request.setAttribute("msg", 1);

		} else {

			request.setAttribute("msg", 0);
		}

		request.getRequestDispatcher("geoinput.jsp").forward(request, response);
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
