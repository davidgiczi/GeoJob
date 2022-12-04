package hu.david.giczi.mvmxpert.georegister.service;

import java.util.List;



public interface GeoJobService {
	
	String cutInputString(String input);
	
	void createWorkFolders(String jobNumber, String place, String method_date);
	
	List<GeoJob> getGeoJobList(String request, String searchedData);
	
	GeoRegistration findById(Long id);
	
	void addGeoReg(GeoRegistration geoReg);

    void modify(GeoJob geoJob);

    void remove(Long id);

    List<GeoJob> findAll();
	
    List<GeoJob> search(String inputData);
    
    List<GeoJob> findByYear(String year);
    
    List<GeoJob> findByDate(String from, String to);
       
}
