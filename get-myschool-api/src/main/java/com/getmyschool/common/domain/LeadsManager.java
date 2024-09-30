package com.getmyschool.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "leadsManager")
public class LeadsManager extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1122659329094507043L;
	
	@Column(name = "name")
	private	String name;
	
	@Column(name = "phoneNumber")
	private String phoneNumber;
	
	@Column(name = "email")
	private	String email; 
	
	@Column(name = "status")
	private String status;

	@Column(name = "collegeId")
	private	Long collegeId;
	
	@Column(name = "leadStatus")
	private	String leadStatus;
	
	@Column(name = "leadDate")
	private String	leadDate;
	
	@Column(name = "followUpDate")
	private	String followUpDate;
	
	@Column(name = "nextFollowUpDate")
	private	String nextFollowUpDate;
	
	@Column(name = "remarks")
	private	String remarks;
	
	@Column(name = "assignedTo")
	private Long	assignedTo;
	
	@Column(name = "assignedBy")
	private	Long assignedBy;
	
	@Column(name = "leadType")
	private String	leadType;
	
	@Column(name = "createdBy")
	private Long createdBy;
	
	@Column(name = "updatedBy")
	private Long updatedBy;
	
	@Column(name = "createdDate")
	private String createdDate;
	
	@Column(name = "updatedDate")
	private String updatedDate;
	
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}
	public String getLeadStatus() {
		return leadStatus;
	}
	public void setLeadStatus(String leadStatus) {
		this.leadStatus = leadStatus;
	}
	public String getLeadDate() {
		return leadDate;
	}
	public void setLeadDate(String leadDate) {
		this.leadDate = leadDate;
	}
	public String getFollowUpDate() {
		return followUpDate;
	}
	public void setFollowUpDate(String followUpDate) {
		this.followUpDate = followUpDate;
	}
	public String getNextFollowUpDate() {
		return nextFollowUpDate;
	}
	public void setNextFollowUpDate(String nextFollowUpDate) {
		this.nextFollowUpDate = nextFollowUpDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(Long assignedTo) {
		this.assignedTo = assignedTo;
	}
	public Long getAssignedBy() {
		return assignedBy;
	}
	public void setAssignedBy(Long assignedBy) {
		this.assignedBy = assignedBy;
	}
	public String getLeadType() {
		return leadType;
	}
	public void setLeadType(String leadType) {
		this.leadType = leadType;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}

