package com.getmyschool.common.converter;

import org.springframework.stereotype.Component;

import com.getmyschool.common.domain.School;
import com.getmyschool.common.dto.SchoolDTO;

@Component
public class SchoolConverter {

	
	/**
	 * @Convert School to SchoolDTO
	 * @param School
	 * @return
	 */

	
	public static SchoolDTO getSchoolDTOBySchool(School school) {
		SchoolDTO dto = new SchoolDTO();
		
		dto.setId(school.getId());
		dto.setStatus(school.getStatus());
		dto.setCreatedBy(school.getCreatedBy());
		dto.setCreatedDate(school.getCreatedDate());
		dto.setUpdatedBy(school.getUpdatedBy());
		dto.setUpdatedDate(school.getUpdatedDate());
		dto.setSchoolName(school.getSchoolName());
		dto.setOgUrl(school.getOgUrl());
		dto.setSchoolType(school.getSchoolType());
		dto.setBoard(school.getBoard());
		dto.setEducationType(school.getEducationType());
		dto.setGradeClassification(school.getGradeClassification());
		dto.setAmenities(school.getAmenities());
		dto.setSchoolFieldDescription(school.getSchoolFieldDescription());
		dto.setMinAgeForAdmission(school.getMinAgeForAdmission());
		dto.setMaxAgeForAdmission(school.getMaxAgeForAdmission());
		dto.setCountryStateDistrict(school.getCountryStateDistrict());
		dto.setPincodeArea(school.getPincodeArea());
		dto.setIntegrateMap(school.getIntegrateMap());
		dto.setLogo(school.getLogo());
		dto.setBanner(school.getBanner());
		dto.setFeatured(school.getFeatured());
		dto.setBrochure(school.getBrochure());
		dto.setGallery(school.getGallery());
		dto.setFaq(school.getFaq());
		dto.setLanguageInstructor(school.getLanguageInstructor());
		
		return dto;
	}
	/**
	 * @Convert SchoolDTO to School
	 * @param SchoolDTO
	 * @return
	 */

	public static School getSchoolBySchoolDTO(SchoolDTO schoolDTO) {
		School school = new School();
		
		school.setId(schoolDTO.getId());
		school.setStatus(schoolDTO.getStatus());
		school.setCreatedBy(schoolDTO.getCreatedBy());
		school.setCreatedDate(schoolDTO.getCreatedDate());
		school.setUpdatedBy(schoolDTO.getUpdatedBy());
		school.setUpdatedDate(schoolDTO.getUpdatedDate());
		school.setSchoolName(schoolDTO.getSchoolName());
		school.setOgUrl(schoolDTO.getOgUrl());
		school.setSchoolType(schoolDTO.getSchoolType());
		school.setBoard(schoolDTO.getBoard());
		school.setEducationType(schoolDTO.getEducationType());
		school.setGradeClassification(schoolDTO.getGradeClassification());
		school.setAmenities(schoolDTO.getAmenities());
		school.setSchoolFieldDescription(schoolDTO.getSchoolFieldDescription());
		school.setMinAgeForAdmission(schoolDTO.getMinAgeForAdmission());
		school.setMaxAgeForAdmission(schoolDTO.getMaxAgeForAdmission());
		school.setCountryStateDistrict(schoolDTO.getCountryStateDistrict());
		school.setPincodeArea(schoolDTO.getPincodeArea());
		school.setIntegrateMap(schoolDTO.getIntegrateMap());
		school.setLogo(schoolDTO.getLogo());
		school.setBanner(schoolDTO.getBanner());
		school.setFeatured(schoolDTO.getFeatured());
		school.setBrochure(schoolDTO.getBrochure());
		school.setGallery(schoolDTO.getGallery());
		school.setFaq(schoolDTO.getFaq());
		school.setLanguageInstructor(schoolDTO.getLanguageInstructor());
		
		return school;
	}
	
}