package hu.david.giczi.mvmxpert.georegister.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.net.URLEncoder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GeoJobServiceImpl implements GeoJobService {

	
	
	@Override
	public GeoRegistration findById(Long id) {
		
		GeoRegistration georeg = new GeoRegistration();
		
		try {
			URL url = new URL("http://localhost:9092/georeg/findById/" + id);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if(conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed: HTTP error code: "
						+ conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),
					Charset.forName("UTF-8")));
			Gson gson = new Gson();
			georeg = gson.fromJson(br.readLine(), GeoRegistration.class);
			georeg.setDate(georeg.getDate().substring(0,10));
			br.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return georeg;
	}
	
	
	
	@Override
	public void addGeoReg(GeoRegistration geoReg) {
		
		try {

	        URL url = new URL("http://localhost:9092/georeg/add");
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setDoOutput(true);
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json");
	        
	        OutputStream os = conn.getOutputStream();
	        os.write(new Gson().toJson(geoReg).getBytes("UTF-8"));
	        os.flush();

	        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
	            throw new RuntimeException("Failed : HTTP error code : "
	                + conn.getResponseCode());
	        }
	        conn.disconnect();

	      } catch (IOException e) {

	        e.printStackTrace();

	     }
		
	}

	@Override
	public List<GeoJob> findAll() {
		
		List<GeoRegistration> georegs = new ArrayList<>();
		
		try {
			URL url = new URL("http://localhost:9092/georeg/findAll");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if(conn.getResponseCode() != 200) {
				return createGeoJob(null);
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),
					Charset.forName("UTF-8")));
			Gson gson = new Gson();
			Type userListType = new TypeToken<ArrayList<GeoRegistration>>(){}.getType();
			georegs = gson.fromJson(br.readLine(), userListType); 
			georegs.forEach(g -> g.setDate(g.getDate().substring(0, 10)));
			br.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return createGeoJob(georegs);
	}
	
	
	private List<GeoJob> createGeoJob(List<GeoRegistration> geoRegs){
		
		List<GeoJob> geoJobStore = new ArrayList<>();
		if(geoRegs != null) {
		geoRegs.forEach(reg -> geoJobStore.add(new GeoJob(reg)));
		Collections.sort(geoJobStore);
	}
		return geoJobStore;
	}

	@Override
	public void modify(GeoJob geoJob) {
		
		try {
	        URL url = new URL("http://localhost:9092/georeg/modify");
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setDoOutput(true);
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json");
	        
	        OutputStream os = conn.getOutputStream();
	        os.write(new Gson().toJson(geoJob).getBytes("UTF-8"));
	        os.flush();

	        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
	            throw new RuntimeException("Failed : HTTP error code : "
	                + conn.getResponseCode());
	        }
	        conn.disconnect();

	      } catch (IOException e) {

	        e.printStackTrace();

	     }
			
	}

	@Override
	public void remove(Long id) {
		
		try {
			URL url = new URL("http://localhost:9092/georeg/deleteById/" + id);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
	        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
	            throw new RuntimeException("Failed : HTTP error code : "
	                + conn.getResponseCode());
	        }
					
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public List<GeoJob> search(String inputData)  {
		
		List<GeoRegistration> georegs = new ArrayList<>();
		
		try {
			inputData = URLEncoder.encode(inputData, StandardCharsets.UTF_8.toString());
			String path ="http://localhost:9092/georeg/search/" + inputData;
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if(conn.getResponseCode() != 200) {
				return createGeoJob(null);
			}
				
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),
					Charset.forName("UTF-8")));
			Gson gson = new Gson();
			Type userListType = new TypeToken<ArrayList<GeoRegistration>>(){}.getType();
			georegs = gson.fromJson(br.readLine(), userListType); 
			georegs.forEach(g -> g.setDate(g.getDate().substring(0, 10)));
			br.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return createGeoJob(georegs);
		
	}

	@Override
	public List<GeoJob> findByYear(String year) {
	
		List<GeoRegistration> georegs = new ArrayList<>();
		
		try {
			URL url = new URL("http://localhost:9092/georeg/findByYear/" + year);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if(conn.getResponseCode() != 200) {
				return createGeoJob(null);
			}
				
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),
					Charset.forName("UTF-8")));
			Gson gson = new Gson();
			Type userListType = new TypeToken<ArrayList<GeoRegistration>>(){}.getType();
			georegs = gson.fromJson(br.readLine(), userListType); 
			georegs.forEach(g -> g.setDate(g.getDate().substring(0, 10)));
			br.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return createGeoJob(georegs);
	
	}
	
	@Override
	public String cutInputString(String input) {

		if (input.length() > 255) {

			input = input.substring(0, 255);

		}

		return input;
	}
	
	@Override
	public void createWorkFolders(String jobNumber, String place, String method_date) {
		
		FolderManager manager = new FolderManager(jobNumber, place, method_date);
		manager.createJobNumberFolder();
		manager.createPlaceFolder();
		manager.createMethodDateFolder();
		manager.createSubFolders();
	}

	@Override
	public List<GeoJob> getGeoJobList(String request, String searchedData){
		
		List<GeoJob> geoJobList = new ArrayList<>();
		
		
		if("all".equals(request)) {
			
			geoJobList = findAll();
		}
		else if(!"all".equals(request) && searchedData == null) {
			
			geoJobList = findByYear(request);
		}
		else if(searchedData != null) {
			
			geoJobList = search(searchedData);
		}
		
		
		return geoJobList;
	}

	@Override
	public List<GeoJob> findByDate(String from, String to) {
		
		List<GeoRegistration> georegs = new ArrayList<>();
		
		try {
			URL url = new URL("http://localhost:9092/georeg/findByDate/" + from + "/" + to);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if(conn.getResponseCode() != 200) {
				return createGeoJob(null);
			}
				
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),
					Charset.forName("UTF-8")));
			Gson gson = new Gson();
			Type userListType = new TypeToken<ArrayList<GeoRegistration>>(){}.getType();
			georegs = gson.fromJson(br.readLine(), userListType); 
			georegs.forEach(g -> g.setDate(g.getDate().substring(0, 10)));
			br.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return createGeoJob(georegs);
	}

}
