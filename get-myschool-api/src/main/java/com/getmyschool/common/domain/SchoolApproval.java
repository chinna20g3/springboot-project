package com.getmyschool.common.domain;

import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="schoolApproval")
public class SchoolApproval extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6527535550182269916L;
	
	@Column(name ="id")
	private Long id;
	
	@Column(name ="approvedBy")
	private Long approvedBy;
	
	@Column(name ="schoolId")
	private Long schoolId;
	
	@Column(name ="status")
    private String status;
	
	@Column(name ="createdBy")
	private Long createdBy;
	
	@Column(name ="createdDate")
	private String createdDate;
	
	@Column(name ="updatedBy")
	private Long updatedBy;
	
	@Column(name ="updatedDate")
	private String updatedDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(Long approvedBy) {
		this.approvedBy = approvedBy;
	}
	public Long getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
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
