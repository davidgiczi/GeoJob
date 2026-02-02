package hu.david.giczi.mvmxpert.georegister.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.david.giczi.mvmxpert.georegister.service.TimeStamp;


@WebServlet("/initGeoRegSave")
public class InitGeoRegSave extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InitGeoRegSave() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		try {

			Integer savedJobs = (Integer) request.getSession().getAttribute("savedjobs");
			Integer validJobs = (Integer) request.getSession().getAttribute("validjobs");
			Integer loadedJobs = (Integer) request.getSession().getAttribute("loadedjobs");

			if (savedJobs != null) {

				request.setAttribute("savedjobs", savedJobs);
				request.getSession().setAttribute("savedjobs", null);
			}

			if (validJobs != null) {

				request.setAttribute("validjobs", true);
				request.getSession().setAttribute("validjobs", null);
			}
			if(loadedJobs != null) {
				
				request.setAttribute("loadedjobs", loadedJobs);
				request.setAttribute("validjobs", validJobs);
				request.getSession().setAttribute("loadedjobs", null);
				request.getSession().setAttribute("validjobs", null);
			}

			request.setAttribute("years", TimeStamp.getYears(5));
			request.getRequestDispatcher("/WEB-INF/geosave.jsp").forward(request, response);
		} catch (IllegalStateException e) {

			request.getRequestDispatcher("/WEB-INF/geostart.jsp").forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
