package hu.david.giczi.mvmxpert.georegister.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeStamp {

	
	private static SimpleDateFormat format;
	
	
	public static String timeStamp() {
		
		 format= new SimpleDateFormat("yyyy-MM-dd");
		
		return format.format(new Date(System.currentTimeMillis()));
		
	}
	
	public static String getYear() {
		
		format= new SimpleDateFormat("yyyy");
		
		return format.format(new Date(System.currentTimeMillis()));
	}
	
	
	public static List<String> getYears(int numberOfyears){
		
		int currentYear=Calendar.getInstance().get(Calendar.YEAR);
		
		List<String> years=new ArrayList<String>();
		
		years.add(String.valueOf(currentYear));
		
		if(0<numberOfyears) {
			
			for (int i = currentYear-1; i > currentYear-numberOfyears; i--) {
				
				years.add(String.valueOf(i));
				
			}
			
			return years;
		}
		
		else if (numberOfyears<0) {
			
			for (int i = currentYear+1; i <currentYear+numberOfyears; i++) {
				
				years.add(String.valueOf(i));
				
			}
			
			return years;
		
		}
		
		return years;
		
	}
	
	
}
