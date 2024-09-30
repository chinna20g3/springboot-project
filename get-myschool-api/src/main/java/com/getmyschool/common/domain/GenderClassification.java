package com.getmyschool.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.Table;

@Entity
@Table(name = "genderClassification")
public class GenderClassification extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4530560373211268355L;

	@Column(name = "genderClassificationType")
	private String genderClassificationType;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "createdBy")
	private Long createdBy;
	
	@Column(name = "createdDate")
	private String createdDate;
	
	@Column(name = "updatedBy")
	private Long updatedBy;
	
	@Column(name = "updatedDate")
	private String updatedDate;

	public String getGenderClassificationType() {
		return genderClassificationType;
	}
	public void setGenderClassificationType(String genderClassificationType) {
		this.genderClassificationType = genderClassificationType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
