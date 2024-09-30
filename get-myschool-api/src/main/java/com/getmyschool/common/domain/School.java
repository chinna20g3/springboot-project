package com.getmyschool.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "school")
public class School extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6280710959755149735L;

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
	
	@Column(name = "schoolName")
	private String	schoolName;
	
	@Column(name = "ogUrl")
	private String	ogUrl;
	
	@Column(name = "schoolType")
	private String	schoolType;
	
	@Column(name = "board")
	private String	board;
	
	@Column(name = "educationType")
	private String	educationType;
	
	@Column(name = "gradeClassification")
	private String	gradeClassification;
	
	@Column(name = "amenities")
	private String	amenities;
	
	@Column(name = "schoolFieldDescription")
	private String	schoolFieldDescription;
	
	@Column(name = "minAgeForAdmission")
	private String	minAgeForAdmission;
	
	@Column(name = "maxAgeForAdmission")
	private String	maxAgeForAdmission;
	
	@Column(name = "countryStateDistrict")
	private String	countryStateDistrict;
	
	@Column(name = "pincodeArea")
	private String	pincodeArea;
	
	@Column(name = "integrateMap")
	private String	integrateMap;
	
	@Column(name = "logo")
	private String	logo;
	
	@Column(name = "banner")
	private String	banner;
	
	@Column(name = "featured")
	private String	featured;
	
	@Column(name = "brochure")
	private String	brochure;
	
	@Column(name = "gallery")
	private String	gallery;
	
	@Column(name = "faq")
	private String	faq;
	
	@Column(name = "languageInstructor")
	private String	languageInstructor;
	
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
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getOgUrl() {
		return ogUrl;
	}
	public void setOgUrl(String ogUrl) {
		this.ogUrl = ogUrl;
	}
	public String getSchoolType() {
		return schoolType;
	}
	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}
	public String getBoard() {
		return board;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	public String getEducationType() {
		return educationType;
	}
	public void setEducationType(String educationType) {
		this.educationType = educationType;
	}
	public String getGradeClassification() {
		return gradeClassification;
	}
	public void setGradeClassification(String gradeClassification) {
		this.gradeClassification = gradeClassification;
	}
	public String getAmenities() {
		return amenities;
	}
	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}
	public String getSchoolFieldDescription() {
		return schoolFieldDescription;
	}
	public void setSchoolFieldDescription(String schoolFieldDescription) {
		this.schoolFieldDescription = schoolFieldDescription;
	}
	public String getMinAgeForAdmission() {
		return minAgeForAdmission;
	}
	public void setMinAgeForAdmission(String minAgeForAdmission) {
		this.minAgeForAdmission = minAgeForAdmission;
	}
	public String getMaxAgeForAdmission() {
		return maxAgeForAdmission;
	}
	public void setMaxAgeForAdmission(String maxAgeForAdmission) {
		this.maxAgeForAdmission = maxAgeForAdmission;
	}
	public String getCountryStateDistrict() {
		return countryStateDistrict;
	}
	public void setCountryStateDistrict(String countryStateDistrict) {
		this.countryStateDistrict = countryStateDistrict;
	}
	public String getPincodeArea() {
		return pincodeArea;
	}
	public void setPincodeArea(String pincodeArea) {
		this.pincodeArea = pincodeArea;
	}
	public String getIntegrateMap() {
		return integrateMap;
	}
	public void setIntegrateMap(String integrateMap) {
		this.integrateMap = integrateMap;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	public String getFeatured() {
		return featured;
	}
	public void setFeatured(String featured) {
		this.featured = featured;
	}
	public String getBrochure() {
		return brochure;
	}
	public void setBrochure(String brochure) {
		this.brochure = brochure;
	}
	public String getGallery() {
		return gallery;
	}
	public void setGallery(String gallery) {
		this.gallery = gallery;
	}
	public String getFaq() {
		return faq;
	}
	public void setFaq(String faq) {
		this.faq = faq;
	}
	public String getLanguageInstructor() {
		return languageInstructor;
	}
	public void setLanguageInstructor(String languageInstructor) {
		this.languageInstructor = languageInstructor;
	}
	
}
