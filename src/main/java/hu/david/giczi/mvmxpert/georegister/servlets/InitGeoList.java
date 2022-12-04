package hu.david.giczi.mvmxpert.georegister.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.david.giczi.mvmxpert.georegister.service.TimeStamp;


@WebServlet("/InitGeoList")
public class InitGeoList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InitGeoList() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		try {

			Boolean isAllEmpty = (Boolean) request.getSession().getAttribute("allEmpty");
			Boolean isYearEmpty = (Boolean) request.getSession().getAttribute("yearEmpty");
			Boolean isSearchedEmpty = (Boolean) request.getSession().getAttribute("searchedEmpty");
			String searchedData = (String) request.getSession().getAttribute("searchedData");
			String year = (String) request.getSession().getAttribute("requestInstruction");
			Boolean invalid = (Boolean) request.getSession().getAttribute("invalid");
			
			request.setAttribute("years", TimeStamp.getYears(5));
			
			if(isAllEmpty != null) {
				
				request.setAttribute("emptylist", "Semelyik");
			}
			if(isYearEmpty != null) {
				
				request.setAttribute("emptylist", year+".");
			}
			if(isSearchedEmpty != null) {
				
				request.setAttribute("searcheddata", searchedData);
			}
			if(invalid != null) {
				
				request.setAttribute("invalid", true);
			}
				
				
			request.getRequestDispatcher("geolist.jsp").forward(request, response);
			request.getSession().invalidate();

		} catch (IllegalStateException e) {

			request.getRequestDispatcher("geostart.jsp").forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
