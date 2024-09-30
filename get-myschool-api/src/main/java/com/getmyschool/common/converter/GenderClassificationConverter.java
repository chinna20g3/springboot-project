package com.getmyschool.common.converter;

import org.springframework.stereotype.Component;

import com.getmyschool.common.domain.GenderClassification;
import com.getmyschool.common.dto.GenderClassificationDTO;

@Component
public class GenderClassificationConverter {
	
	/**
	 * convert GenderClassification to GenderClassificationDTO
	 * 
	 * @param GenderClassification
	 * @return
	 */

	public static GenderClassificationDTO getGenderClassificationDTOByGenderClassification(GenderClassification genderClassification) {
		GenderClassificationDTO dto = new GenderClassificationDTO();
		
		dto.setId(genderClassification.getId());
		dto.setGenderClassificationType(genderClassification.getGenderClassificationType());
		dto.setStatus(genderClassification.getStatus());
		dto.setCreatedBy(genderClassification.getCreatedBy());
		dto.setCreatedDate(genderClassification.getCreatedDate());
		dto.setUpdatedBy(genderClassification.getUpdatedBy());
		dto.setUpdatedDate(genderClassification.getUpdatedDate());
		
		return dto;

	}
	
	/**
	 * convert GenderClassificationDTO to GenderClassification
	 * 
	 * @param GenderClassificationDTO
	 * @return
	 */
	
	
	public static GenderClassification getGenderClassificationByGenderClassificationDTO(GenderClassificationDTO genderClassificationDTO) {
		GenderClassification genderClassification = new GenderClassification();
		
		genderClassification.setId(genderClassificationDTO.getId());
		genderClassification.setGenderClassificationType(genderClassificationDTO.getGenderClassificationType());
		genderClassification.setStatus(genderClassificationDTO.getStatus());
		genderClassification.setCreatedBy(genderClassificationDTO.getCreatedBy());
		genderClassification.setCreatedDate(genderClassificationDTO.getCreatedDate());
		genderClassification.setUpdatedBy(genderClassificationDTO.getUpdatedBy());
		genderClassification.setUpdatedDate(genderClassificationDTO.getUpdatedDate());
		
		return genderClassification;
	}
	
}
 