package hu.david.giczi.mvmxpert.georegister.service;

import java.io.File;
import java.io.FilenameFilter;

public class FolderManager {

	
	public static String GEO_PATH;
	private String jobNumber;
	private String place;
	private String method_date;
	
	
	
	public FolderManager(String jobNumber, String place, String method_date) {
		
		FolderManager.GEO_PATH = GeoJobPropertyStore.URL1;
		this.jobNumber = jobNumber.replace(" ", "_");
		this.place = place.replace(" ", "_");
		this.method_date = method_date.replace(" ", "_");
	}


	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
	
	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public String getMethod_date() {
		return method_date;
	}


	public void setMethod_date(String method_date) {
		this.method_date = method_date;
	}


	public void createJobNumberFolder() {
		
		File jobNumberFolder = new File(GEO_PATH + jobNumber);

		if( !jobNumberFolder.exists() ) {
			
			jobNumberFolder.mkdir();
			
		}
		
	}

	public void createPlaceFolder() {
		
		File placeFolder = new File(GEO_PATH + jobNumber + "\\" + place);
		
		if( !placeFolder.exists() ) {
			
			placeFolder.mkdir();
		}
		
	}
	
	public void createMethodDateFolder() {
		
		File methodDateFolder = new File(GEO_PATH + jobNumber+ "\\" + place + "\\" + method_date);
		
		if( !methodDateFolder.exists() ) {
			
			methodDateFolder.mkdir();
			
		}	
	}
	
	
	public void createSubFolders() {
		
		File methodDateFolder = new File(GEO_PATH + jobNumber+ "\\" + place + "\\" + method_date);
		
		if( methodDateFolder.exists() ) {
			
			new File(GEO_PATH + jobNumber + "\\" + place + "\\" + method_date + "\\terep").mkdir();
			new File(GEO_PATH + jobNumber + "\\" + place + "\\" + method_date + "\\projekt").mkdir();
			new File(GEO_PATH + jobNumber + "\\" + place + "\\" + method_date + "\\munka").mkdir();
		}
	}
	
	public String getMeasuringReportPath() {
		
		File measFolder = new File(GEO_PATH + jobNumber + "\\" + place + "\\" + method_date + "\\terep");
		
		if(measFolder.exists()) {
			
			String[] report = measFolder.list(new FilenameFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					
					return name.endsWith("jzk");
				}
			});
			
			if(report.length == 1) {
				return measFolder.getAbsolutePath() + "\\" + report[0];
			}
			
		}
		
		
		
		return "-";
	}
	
	
}


