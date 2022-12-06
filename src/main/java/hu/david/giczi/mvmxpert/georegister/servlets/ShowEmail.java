package hu.david.giczi.mvmxpert.georegister.servlets;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/showmail")
public class ShowEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowEmail() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		@SuppressWarnings (value="unchecked")
		List<String> urlStore = (List<String>) request.getSession().getAttribute("fileURLStore");
		
		if( !urlStore.isEmpty() && urlStore.get(0).endsWith(".pdf")) {
			
		String fileURL = urlStore.get(0);
		
		try {
			Desktop.getDesktop().open(new File(fileURL));
		} catch (IOException e) {
			System.out.println(fileURL + " cannot be opened.");
		}
		
		}
		
		request.getRequestDispatcher("clearSession").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
