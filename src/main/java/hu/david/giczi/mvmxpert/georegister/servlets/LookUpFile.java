package hu.david.giczi.mvmxpert.georegister.servlets;

import java.awt.Component;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

@WebServlet("/lookup")
public class LookUpFile extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private boolean nonExisted = true;

	public LookUpFile() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String urlKey = request.getParameter("url");

		if (!nonExisted) {
			response.sendRedirect("postRouter");
			return;
		}
		@SuppressWarnings(value = "unchecked")
		List<String> urlStore = (List<String>) request.getSession().getAttribute("fileURLStore");
		String path = (String) request.getSession().getAttribute("path");
		Boolean init = (Boolean) request.getSession().getAttribute("initprocess");

		nonExisted = false;

		String inputURL = "";

		if ("url1".equals(urlKey) || "url4".equals(urlKey)) {

			if (path != null) {

				inputURL = lookUpFile(path, true, false);
			} else {

				inputURL = lookUpFile("C:\\", true, false);

			}

			if (!inputURL.isEmpty())
				request.getSession().setAttribute("path", inputURL);

		} else if ("url2".equals(urlKey) || "url3".equals(urlKey)) {

			if (path != null) {

				inputURL = lookUpFile(path, false, true);
			} else {

				inputURL = lookUpFile("C:\\", false, true);

			}

			if (!inputURL.isEmpty())
				request.getSession().setAttribute("path", inputURL.substring(0, inputURL.lastIndexOf("\\")));

		} else if (urlKey == null) {

			if (path != null) {

				inputURL = lookUpFile(path, false, false);
			} else {

				inputURL = lookUpFile("C:\\", false, false);

			}

			if (!inputURL.isEmpty())
				request.getSession().setAttribute("path", inputURL.substring(0, inputURL.lastIndexOf("\\")));
		}

		if (urlStore == null) {

			urlStore = new ArrayList<>();

			for (int i = 0; i < 4; i++) {
				urlStore.add("");
			}
		}

		if (urlKey == null) {

			urlStore.set(0, inputURL);
			
		} else {

			urlStore = addUrlToStore(urlStore, urlKey, inputURL);
		}

		request.getSession().setAttribute("fileURLStore", urlStore);

		if (urlKey != null) {

			request.getSession().setAttribute("setup", "setup");

			for (int i = 0; i < urlStore.size(); i++) {
				if (!urlStore.get(i).isEmpty()) {
					request.setAttribute("url" + (i + 1), urlStore.get(i));
				}
			}

			if (init != null) {
				request.setAttribute("init", true);
			}

			request.getRequestDispatcher("geosetup.jsp").forward(request, response);

		} else {
			request.getRequestDispatcher("showmail").forward(request, response);

		}

		nonExisted = true;
	}

	private String lookUpFile(String path, boolean isDirectrory, boolean isExeFile) {

		JFileChooser jf = new JFileChooser(path) {

			private static final long serialVersionUID = 1L;

			@Override
			protected JDialog createDialog(Component parent) throws HeadlessException {

				JDialog dialog = super.createDialog(parent);

				dialog.setAlwaysOnTop(true);

				return dialog;
			}
		};

		if (isDirectrory) {
			jf.setDialogTitle("Mappa kiválasztás");
		} else {
			jf.setDialogTitle("Fájl kiválasztás");
		}

		jf.setApproveButtonText("Ok");

		jf.setAcceptAllFileFilterUsed(false);

		if (isDirectrory) {

			jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		} else {

			if (isExeFile) {

				FileNameExtensionFilter filter = new FileNameExtensionFilter("exe", "exe");

				jf.addChoosableFileFilter(filter);

			} else {

				FileNameExtensionFilter filter = new FileNameExtensionFilter("pdf", "pdf");

				jf.addChoosableFileFilter(filter);

			}

		}

		int returnValue = jf.showOpenDialog(null);

		String fileUrl = "";

		if (returnValue == JFileChooser.APPROVE_OPTION) {

			fileUrl = jf.getSelectedFile().getAbsolutePath();
		}

		return fileUrl;
	}

	private List<String> addUrlToStore(List<String> urlStore, String key, String value) {

		switch (key) {

		case "url1":
			urlStore.set(0, value);
			break;
		case "url2":
			urlStore.set(1, value);
			break;

		case "url3":
			urlStore.set(2, value);
			break;
		case "url4":
			urlStore.set(3, value);

		}

		return urlStore;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	
	
}
