package hu.david.giczi.mvmxpert.georegister.service;

import java.util.ArrayList;
import java.util.List;

public class HighlightedGeoJob {

	private GeoJob containerGeoJob;
	private String searchedExpression;
	private List<String> geoJobStringStore;
	private List<Integer> beginIndexStore;
	private List<Integer> endIndexStore;
	private final String preTag = "<span style=\"background-color: #FF69B4\">";
	private final String postTag = "</span>";
	public static List<GeoJob> highlightedGeoJobStore = new ArrayList<>();

	public HighlightedGeoJob() {

	}

	public HighlightedGeoJob(GeoJob containerGeoJob, String searchedExpression) {
		this.containerGeoJob = containerGeoJob;
		this.searchedExpression = searchedExpression;
	}

	public void setSearchedExpression(String searchedExpression) {
		this.searchedExpression = searchedExpression;
	}

	public List<Integer> getBeginIndexStore() {
		return beginIndexStore;
	}

	public List<Integer> getEndIndexStore() {
		return endIndexStore;
	}

	public String getPreTag() {
		return preTag;
	}

	public String getPostTag() {
		return postTag;
	}

	public void createHighlightedGeoJob() {

		createContainerTextFromGeoJob();
		
		for (int i = 0; i < geoJobStringStore.size(); i++) {

			if (geoJobStringStore.get(i).toLowerCase().contains(searchedExpression.toLowerCase())) {
				
				createBeginIndexStore(geoJobStringStore.get(i));
				createEndIndexStore();
				geoJobStringStore.set(i, createHighlightedString(geoJobStringStore.get(i)));
				addHighlightedDataToContainerGeoJob();

			}

		}

		highlightedGeoJobStore.add(containerGeoJob);
	}

	private List<String> createContainerTextFromGeoJob() {

		geoJobStringStore = new ArrayList<>();

		geoJobStringStore.add(containerGeoJob.getSettlementNameOfWork());
		geoJobStringStore.add(containerGeoJob.getPlaceOfWork());
		geoJobStringStore.add(containerGeoJob.getMethod());
		geoJobStringStore.add(containerGeoJob.getDate());
		geoJobStringStore.add(containerGeoJob.getInvestmentManager());
		geoJobStringStore.add(containerGeoJob.getInvestorCompany());
		geoJobStringStore.add(containerGeoJob.getComment());

		return geoJobStringStore;
	}

	public void createBeginIndexStore(String containerText) {

		beginIndexStore = new ArrayList<>();
		

		for (int i = 0; i <= containerText.length() - searchedExpression.length(); i++) {

			if ( containerText.substring(i, i + searchedExpression.length())
				.toLowerCase().equals(searchedExpression.toLowerCase()) || 
			
				( containerText.substring(i, i + searchedExpression.length())
					.toLowerCase().equals(searchedExpression.toLowerCase()) &&
				containerText.substring(i, i + searchedExpression.length()).charAt(0) == 
			Character.toUpperCase(searchedExpression.charAt(0))) ) 
			{

			beginIndexStore.add(i);

			}

		}

	}

	public void createEndIndexStore() {
		
		endIndexStore = new ArrayList<>();
		
		for(int i = 0; i < beginIndexStore.size(); i++) {
			
		int endIndex = beginIndexStore.get(i) + searchedExpression.length() - 1;
			
		if(i + 1 < beginIndexStore.size() && endIndex >= beginIndexStore.get(i + 1)) {
			
			continue;
		
		}
			
		endIndexStore.add(endIndex);
		
		}
			

	}

	
	
	public String createHighlightedString(String text) {

		char[] container = text.toCharArray();
		StringBuilder builder = new StringBuilder();
		boolean isOpenTag = false;

		for (int i = 0; i < container.length; i++) {

			
		 if (beginIndexStore.contains(i) && !isOpenTag) {

				builder.append(preTag);
				isOpenTag = true;

			} 
			 	 
		 builder.append(container[i]);	 
		 
		 if (endIndexStore.contains(i) && isOpenTag) {

				builder.append(postTag);
				isOpenTag = false;

			} 
					
	}


		return builder.toString();
	}

	private void addHighlightedDataToContainerGeoJob() {

		containerGeoJob.setSettlementNameOfWork(geoJobStringStore.get(0));
		containerGeoJob.setPlaceOfWork(geoJobStringStore.get(1));
		containerGeoJob.setMethod(geoJobStringStore.get(2));
		containerGeoJob.setDate(geoJobStringStore.get(3));
		containerGeoJob.setInvestmentManager(geoJobStringStore.get(4));
		containerGeoJob.setInvestorCompany(geoJobStringStore.get(5));
		containerGeoJob.setComment(geoJobStringStore.get(6));

	}

	public static void clearHighlightedGeoJobStore() {

		highlightedGeoJobStore.clear();
	}

}
