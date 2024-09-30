package com.getmyschool.common.converter;

import org.springframework.stereotype.Component;

import com.getmyschool.common.domain.GradeClassification;
import com.getmyschool.common.dto.GradeClassificationDTO;

@Component
public class GradeClassificationConverter {

	
	/** 
	 * convert GradeClassification to GradeClassificationDTO
	 * 
	 * @param GradeClassification
	 * @return
	 */
	
	public static GradeClassificationDTO getGradeClassificationDTOByGradeClassification(GradeClassification gradeClassification) {
		GradeClassificationDTO dto = new GradeClassificationDTO();
		
		dto.setId(gradeClassification.getId());
		dto.setGradeClassificationType(gradeClassification.getGradeClassificationType());
		dto.setStatus(gradeClassification.getStatus());
		dto.setCreatedBy(gradeClassification.getCreatedBy());
		dto.setCreatedDate(gradeClassification.getCreatedDate());
		dto.setUpdatedBy(gradeClassification.getUpdatedBy());
		dto.setUpdatedDate(gradeClassification.getUpdatedDate());
		
		return dto;
	}
	
	/** 
	 * convert GradeClassificationDTO to GradeClassification
	 * 
	 * @param GradeClassificationDTO
	 * @return
	 */
	public static GradeClassification getGradeClassificationByGradeClassificationDTO(GradeClassificationDTO gradeClassificationDTO) {
		GradeClassification gradeClassification = new GradeClassification();

	gradeClassification.setId(gradeClassificationDTO.getId());
	gradeClassification.setGradeClassificationType(gradeClassificationDTO.getGradeClassificationType());
	gradeClassification.setStatus(gradeClassificationDTO.getStatus());
	gradeClassification.setCreatedBy(gradeClassificationDTO.getCreatedBy());
	gradeClassification.setCreatedDate(gradeClassificationDTO.getCreatedDate());
	gradeClassification.setUpdatedBy(gradeClassificationDTO.getUpdatedBy());
	gradeClassification.setUpdatedDate(gradeClassificationDTO.getUpdatedDate());
	
	return gradeClassification;
	}
	
}


