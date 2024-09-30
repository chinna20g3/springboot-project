package com.getmyschool.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "blog")
public class Blog extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 797468711409315869L;

	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "ogUrl")
	private String ogUrl;
	
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
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOgUrl() {
		return ogUrl;
	}
	public void setOgUrl(String ogUrl) {
		this.ogUrl = ogUrl;
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
