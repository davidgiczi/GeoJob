package hu.david.giczi.mvmxpert.georegister.service;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "geodata")
public class GeoRegistration implements Serializable, Comparable<GeoRegistration> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "settlement")
	private String settlementNameOfWork;
	@Column(name = "place")
	private String placeOfWork;
	private String method;
	private String date;
	@Column(name = "manager")
	private String investmentManager;
	@Column(name = "investor")
	private String investorCompany;
	private String comment;
	private Boolean isReady;

	public GeoRegistration() {

	}

	

	public GeoRegistration(Long id, String settlementNameOfWork, String placeOfWork, String method, String date,
			String investmentManager, String investorCompany, String comment, Boolean isReady) {
		
		this.id = id;
		this.settlementNameOfWork = settlementNameOfWork;
		this.placeOfWork = placeOfWork;
		this.method = method;
		this.date = date;
		this.investmentManager = investmentManager;
		this.investorCompany = investorCompany;
		this.comment = comment;
		this.isReady = isReady;
	}

	public GeoRegistration(String settlementNameOfWork, String placeOfWork, String method, String date,
			String investmentManager, String investorCompany, String comment, Boolean isReady) {
		
		this.settlementNameOfWork = settlementNameOfWork;
		this.placeOfWork = placeOfWork;
		this.method = method;
		this.date = date;
		this.investmentManager = investmentManager;
		this.investorCompany = investorCompany;
		this.comment = comment;
		this.isReady = isReady;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSettlementNameOfWork() {
		return settlementNameOfWork;
	}

	public void setSettlementNameOfWork(String settlementNameOfWork) {
		this.settlementNameOfWork = settlementNameOfWork;
	}

	public String getPlaceOfWork() {
		return placeOfWork;
	}

	public void setPlaceOfWork(String placeOfWork) {
		this.placeOfWork = placeOfWork;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getInvestmentManager() {
		return investmentManager;
	}

	public void setInvestmentManager(String investmentManager) {
		this.investmentManager = investmentManager;
	}

	public String getInvestorCompany() {
		return investorCompany;
	}

	public void setInvestorCompany(String investorCompany) {
		this.investorCompany = investorCompany;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getIsReady() {
		return isReady;
	}

	public void setIsReady(Boolean isReady) {
		this.isReady = isReady;
	}

	@Override
	public int compareTo(GeoRegistration o) {
		
		return this.getId() < o.getId() ? -1 : this.getId() == o.getId() ? 0 : 1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((investmentManager == null) ? 0 : investmentManager.hashCode());
		result = prime * result + ((investorCompany == null) ? 0 : investorCompany.hashCode());
		result = prime * result + ((isReady == null) ? 0 : isReady.hashCode());
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((placeOfWork == null) ? 0 : placeOfWork.hashCode());
		result = prime * result + ((settlementNameOfWork == null) ? 0 : settlementNameOfWork.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeoRegistration other = (GeoRegistration) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (investmentManager == null) {
			if (other.investmentManager != null)
				return false;
		} else if (!investmentManager.equals(other.investmentManager))
			return false;
		if (investorCompany == null) {
			if (other.investorCompany != null)
				return false;
		} else if (!investorCompany.equals(other.investorCompany))
			return false;
		if (isReady == null) {
			if (other.isReady != null)
				return false;
		} else if (!isReady.equals(other.isReady))
			return false;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		if (placeOfWork == null) {
			if (other.placeOfWork != null)
				return false;
		} else if (!placeOfWork.equals(other.placeOfWork))
			return false;
		if (settlementNameOfWork == null) {
			if (other.settlementNameOfWork != null)
				return false;
		} else if (!settlementNameOfWork.equals(other.settlementNameOfWork))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GeoRegistration [id=" + id + ", settlementNameOfWork=" + settlementNameOfWork + ", placeOfWork="
				+ placeOfWork + ", method=" + method + ", date=" + date + ", investmentManager=" + investmentManager
				+ ", investorCompany=" + investorCompany + ", comment=" + comment + ", isReady=" + isReady + "]";
	}

	
	
	
	
}
