package hu.david.giczi.mvmxpert.georegister.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Properties;


public class GeoJobPropertyStore {
	
	
	public static String URL1;
	public static String URL2;
	public static String URL3;
	public static String URL4;
	
	public static void createPropertyFile(String url1, String url2, String url3, String url4) {
		
		Properties properties = new Properties();

		properties.setProperty("url1", url1 + "\\");
		properties.setProperty("url2", url2.substring(0, url2.lastIndexOf('.')));
		properties.setProperty("url3", url3.substring(0, url3.lastIndexOf('.')));
		properties.setProperty("url4", url4 + "\\");
		
		
		try(BufferedWriter writer = new BufferedWriter
						(new OutputStreamWriter
								(new FileOutputStream("C:\\geoprops\\geoprops.properties"), Charset.forName("UTF-8")))){
			
		   properties.store(writer, "GeoJobProperties");
		    
		} catch (IOException e) {
			
		   e.printStackTrace();
		}
			
	}
	
	public static void loadPropertiesFromFile() {
		
		Properties properties = new Properties();

		try(BufferedReader reader = new BufferedReader
				(new InputStreamReader
						(new FileInputStream
								("C:\\geoprops\\geoprops.properties"), Charset.forName("UTF-8")))){
			
		   properties.load(reader);
	  
		   GeoJobPropertyStore.URL1 = properties.getProperty("url1");
		   GeoJobPropertyStore.URL2 = properties.getProperty("url2");
		   GeoJobPropertyStore.URL3 = properties.getProperty("url3");
		   GeoJobPropertyStore.URL4 = properties.getProperty("url4");
		    
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		
	}
	
}
