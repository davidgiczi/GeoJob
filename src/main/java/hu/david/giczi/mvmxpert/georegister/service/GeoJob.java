package hu.david.giczi.mvmxpert.georegister.service;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class GeoJob implements Serializable, Comparable<GeoJob> {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String settlementNameOfWork;
	private String placeOfWork;
	private String method;
	private String date;
	private String investmentManager;
	private String investorCompany;
	private String comment;
	private String measTime;
	private String measPointNumber;
	private String measDist;
	private Boolean isReady;
	private String searchedText;
	
	public GeoJob() {
		
	}
	
	public GeoJob(GeoRegistration geoReg) {
		
		this.id = geoReg.getId();
		this.settlementNameOfWork = geoReg.getSettlementNameOfWork();
		this.placeOfWork = geoReg.getPlaceOfWork();
		this.method = geoReg.getMethod();
		this.date = geoReg.getDate();
		this.investmentManager = geoReg.getInvestmentManager();
		this.investorCompany = geoReg.getInvestorCompany();
		this.comment = geoReg.getComment();
		this.isReady = geoReg.getIsReady();
		
		addMeasuredDataToGeoJob();
	}


	private void addMeasuredDataToGeoJob() {
			
		MeasuringReport report = new MeasuringReport(
				new FolderManager(investorCompany, placeOfWork, method + "_" + date).getMeasuringReportPath());
		
		this.measTime = report.getMeasData().isEmpty() ? "-" :
			report.getStartTimeOfMeasuring() + " - " + report.getStopTimeOfMeasuring() + " [" + report.getDurationOfMeasuring() + "]";
		this.measPointNumber = report.getMeasData().isEmpty() ? "-" : String.valueOf(report.getNumberOfMeasuredPoint());
		this.measDist = report.getMeasData().isEmpty() ? "-" : report.getTheLongestMeasuredDistance();
		
	}
	
		
	public Long getId() {
		return id;
	}
	
	public String getSettlementNameOfWork() {
		return settlementNameOfWork;
	}

	public void setSettlementNameOfWork(String settlementNameOfWork) {
		this.settlementNameOfWork = settlementNameOfWork;
	}

	public String getInvestorCompany() {
		return investorCompany;
	}

	public void setInvestorCompany(String investorCompany) {
		this.investorCompany = investorCompany;
	}

	public String getPlaceOfWork() {
		return placeOfWork;
	}


	public String getMethod() {
		return method;
	}


	public String getDate() {
		return date;
	}


	public String getInvestmentManager() {
		return investmentManager;
	}

	public String getComment() {
		return comment;
	}


	public String getMeasTime() {
		return measTime;
	}


	public String getMeasPointNumber() {
		return measPointNumber;
	}


	public String getMeasDist() {
		return measDist;
	}


	public Boolean getIsReady() {
		return isReady;
	}

	public void setPlaceOfWork(String placeOfWork) {
		this.placeOfWork = placeOfWork;
	}


	public void setMethod(String method) {
		this.method = method;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public void setInvestmentManager(String investmentManager) {
		this.investmentManager = investmentManager;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	public void setMeasTime(String measTime) {
		this.measTime = measTime;
	}


	public void setMeasPointNumber(String measPointNumber) {
		this.measPointNumber = measPointNumber;
	}


	public void setMeasDist(String measDist) {
		this.measDist = measDist;
	}


	public void setIsReady(Boolean isReady) {
		this.isReady = isReady;
	}

	public String getSearchedText() {
		return searchedText;
	}

	public void setSearchedText(String searchedText) {
		this.searchedText = searchedText;
	}

	@Override
	public int compareTo(GeoJob o) {
			
		
	return parseGeoJobDate(this.date) <=  parseGeoJobDate(o.getDate()) ? -1 : 1;
			
	}
	
	public static long parseGeoJobDate(String date)  {
		
		String[] data = date.split("\\s+");
		
		try {
			if(data != null && data.length != 0) {
				return new SimpleDateFormat("yyyy-MM-dd").parse(data[0]).getTime();
			}
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		return 0L;
	}

	@Override
	public int hashCode() {
		return Objects.hash(comment, date, investmentManager, investorCompany, isReady, measDist, measPointNumber,
				measTime, method, placeOfWork, settlementNameOfWork);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeoJob other = (GeoJob) obj;
		return Objects.equals(comment, other.comment) && Objects.equals(date, other.date)
				&& Objects.equals(investmentManager, other.investmentManager)
				&& Objects.equals(investorCompany, other.investorCompany) && Objects.equals(isReady, other.isReady)
				&& Objects.equals(measDist, other.measDist) && Objects.equals(measPointNumber, other.measPointNumber)
				&& Objects.equals(measTime, other.measTime) && Objects.equals(method, other.method)
				&& Objects.equals(placeOfWork, other.placeOfWork)
				&& Objects.equals(settlementNameOfWork, other.settlementNameOfWork);
	}

	@Override
	public String toString() {
		return "GeoJob [id=" + id + ", settlementNameOfWork=" + settlementNameOfWork + ", placeOfWork=" + placeOfWork
				+ ", method=" + method + ", date=" + date + ", investmentManager=" + investmentManager
				+ ", investorCompany=" + investorCompany + ", comment=" + comment + ", measTime=" + measTime
				+ ", measPointNumber=" + measPointNumber + ", measDist=" + measDist + ", isReady=" + isReady + "]";
	}

	
}
