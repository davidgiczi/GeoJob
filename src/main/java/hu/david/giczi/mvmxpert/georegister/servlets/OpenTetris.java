package hu.david.giczi.mvmxpert.georegister.servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/openTetris")
public class OpenTetris extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public OpenTetris() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Runtime run = Runtime.getRuntime();
		
		String path= "C:\\Tetris\\Tetris.jar";
		
		if(new File(path).exists()) {
			
			run.exec("java -jar " + path);
			
		}
		else {
			
		request.setAttribute("missed", "tetris");
				
		}
			
		request.getRequestDispatcher("/WEB-INF/geostart.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
