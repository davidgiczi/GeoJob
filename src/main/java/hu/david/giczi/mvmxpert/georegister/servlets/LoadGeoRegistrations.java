package hu.david.giczi.mvmxpert.georegister.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.david.giczi.mvmxpert.georegister.service.GeoJob;
import hu.david.giczi.mvmxpert.georegister.service.GeoJobServiceImpl;
import hu.david.giczi.mvmxpert.georegister.service.GeoRegistration;
import hu.david.giczi.mvmxpert.georegister.service.SaveAndLoadGeoJob;


@WebServlet("/loadGeoRegs")
public class LoadGeoRegistrations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	private boolean nonExisted = true;
	
    public LoadGeoRegistrations() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		if (!nonExisted) {
			response.sendRedirect("initGeoRegSave");
			return;
		}
			
		SaveAndLoadGeoJob load = new SaveAndLoadGeoJob();
		
		try {

			String path = (String) request.getSession().getAttribute("path");
			
			int numberOfInputGeoJob;
			
			nonExisted = false;
			
			if(path != null) {

				numberOfInputGeoJob = load.createFileChooser(path, false);

			} else {

				numberOfInputGeoJob = load.createFileChooser("C:\\", false);

			}
			
			
			if (load.getjFileChooser().getSelectedFile() != null) {
				
				request.getSession().setAttribute("path", load.getjFileChooser().getSelectedFile().getParent());
			}
			
			if(numberOfInputGeoJob == -1) {
				nonExisted = true;
				request.getSession().setAttribute("savedjobs", numberOfInputGeoJob);
				response.sendRedirect("initGeoRegSave");
				return;
				
			}
			else if(numberOfInputGeoJob == 0 ) {
				nonExisted = true;
				request.getSession().setAttribute("validjobs", numberOfInputGeoJob);
				response.sendRedirect("initGeoRegSave");
				return;
				
			}
			
			int loadedGeoJob = 0;
			GeoJobServiceImpl service = new GeoJobServiceImpl();
			
			for (GeoJob inputGeoJob : load.getInputGeoJobStore()) {
				
				if(isLoaded(service.findAll(), inputGeoJob)) {
					
					service.addGeoReg(new GeoRegistration(inputGeoJob.getSettlementNameOfWork(),
																	  inputGeoJob.getPlaceOfWork(),
																	  inputGeoJob.getMethod(),
																	  inputGeoJob.getDate(),
																	  inputGeoJob.getInvestmentManager(),
																	  inputGeoJob.getInvestorCompany(),
																	  inputGeoJob.getComment(),
																	  inputGeoJob.getIsReady()));
					
				service.createWorkFolders(inputGeoJob.getInvestorCompany(), inputGeoJob.getPlaceOfWork(),
						inputGeoJob.getMethod() + "_" + inputGeoJob.getDate());	
					
					loadedGeoJob++;
				}
			}	
				
				request.getSession().setAttribute("loadedjobs", loadedGeoJob);
				request.getSession().setAttribute("validjobs", numberOfInputGeoJob);
				response.sendRedirect("initGeoRegSave");
				nonExisted = true;
						
		} catch (IllegalStateException e) {

			request.getRequestDispatcher("geostart.jsp").forward(request, response);

		}
		
		
	}

	private boolean isLoaded(List<GeoJob> existedGeoJob, GeoJob inputGeoJob) {
		
		return !existedGeoJob.contains(inputGeoJob);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
