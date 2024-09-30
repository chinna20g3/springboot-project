package com.getmyschool.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ipTracking")
public class IpTracking extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -56739790751862086L;
	
	@Column(name ="ipAddress")
	private String ipAddress;
	
	@Column(name ="latitude")
	private Long latitude;
	
	@Column(name ="longitude")
	private Long longitude;
	
	@Column(name ="location")
	private String location;
	
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
	
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Long getLatitude() {
		return latitude;
	}
	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}
	public Long getLongitude() {
		return longitude;
	}
	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
